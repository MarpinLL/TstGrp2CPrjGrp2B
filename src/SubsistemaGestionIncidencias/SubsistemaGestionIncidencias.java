package SubsistemaGestionIncidencias;

import java.util.ArrayList;
import java.util.Date;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

public class SubsistemaGestionIncidencias implements InterfaceSubsistemaGestionIncidencias {

	private ArrayList<Incidencia> incidencias;
	
	public SubsistemaGestionIncidencias() {
		incidencias = new ArrayList<>();
	}

	@Override
	public Incidencia inicializar(Integer identificador, String ciudadano, String DNI, String telefono,
			String descripcion, String localizacion, String tipoIncidencia, Proceso proceso, Date fechaInicio)
			throws CustomException {

		Incidencia incidencia = new Incidencia();
		if (identificador != null) {
			if (identificador > 0)
				incidencia.setIdentificador(identificador);
			else
				throw new CustomException("Identificador incorrecto", 1);
		}
		if (ciudadano != null) {
			if (ciudadano.length() <= 100 && !ciudadano.matches(".*\\d.*"))
				incidencia.setNombreCiudadano(ciudadano);
			else
				throw new CustomException("Nombre ciudadano incorrecto", 1);
		}
		if (DNI != null) {
			if (DNI.matches("[0-9]{8}[aA-zZ]"))
				incidencia.setDNI(DNI);
			else
				throw new CustomException("DNI formato incorrecto", 1);
		}
		if (telefono != null) {
			if (telefono.matches("^[0-9\\-\\+]{9,15}$"))
				incidencia.setTelefono(telefono);
			else
				throw new CustomException("Teléfono incorrecto", 1);
		}
		if (descripcion != null) {
			if (descripcion.length() <= 240)
				incidencia.setDescripcion(descripcion);
			else
				throw new CustomException("Descripcion incorrecta, supera 240 caracteres", 1);
		}
		if (localizacion != null) {
			if (localizacion.length() <= 150)
				incidencia.setLocalizacion(localizacion);
			else
				throw new CustomException("Localizacion incorrecta, supera 150 caracteres", 1);
		}
		if (tipoIncidencia != null) {
			for (String t : incidencia.tiposIncidencia) {
				if (tipoIncidencia.equals(t))
					incidencia.setTipoIncidencia(tipoIncidencia);
			}
			if (incidencia.getTipoIncidencia() == null)
				throw new CustomException("No se reconoce tipo de incidencia: "+tipoIncidencia, 1);
		}
		if (proceso != null)
			incidencia.setProceso(proceso);
		
		if(fechaInicio != null)// Al ser tipo Date el formato siempre va a ser correcto
			incidencia.setFechaInicio(fechaInicio);

		return incidencia;

	}

	@Override
	public Incidencia crear(Incidencia incidencia) throws CustomException {
		if (incidencia != null) {
			// No existe en el sistema una incidencia con el mismo identificador
			for (Incidencia i : this.incidencias) {
				if (i.getIdentificador().equals(incidencia.getIdentificador())) {
					throw new CustomException("Incidencia ya existe", 2);
				}
			}
			// Se almacena en el sistema
			if(!incidencia.getFechaInicio().after(new Date())) {
				this.incidencias.add(incidencia);
				return incidencia;
			}else
				throw new CustomException("La fecha" + incidencia.getFechaInicio()+" es posterior a la fecha actual: "+ new Date(),1);
		} else
			return null;

	}

	@Override
	public Incidencia actualizar(Incidencia incidencia) throws CustomException{
		if (incidencia != null) {
			for (int i = 0; i < this.incidencias.size(); i++) {
				if (this.incidencias.get(i).getIdentificador().equals(incidencia.getIdentificador())) {
					Incidencia incidenciaAlmacenada = this.incidencias.get(i);
					if (incidencia.getNombreCiudadano() != null)
						incidenciaAlmacenada.setNombreCiudadano(incidencia.getNombreCiudadano());
					if (incidencia.getDNI() != null)
						incidenciaAlmacenada.setDNI(incidencia.getDNI());
					if (incidencia.getTelefono() != null)
						incidenciaAlmacenada.setTelefono(incidencia.getTelefono());
					if (incidencia.getDescripcion() != null)
						incidenciaAlmacenada.setDescripcion(incidencia.getDescripcion());
					if (incidencia.getLocalizacion() != null)
						incidenciaAlmacenada.setLocalizacion(incidencia.getLocalizacion());
					if (incidencia.getTipoIncidencia() != null)
						incidenciaAlmacenada.setTipoIncidencia(incidencia.getTipoIncidencia());
					if (incidencia.getProceso() != null)
						incidenciaAlmacenada.setProceso(incidencia.getProceso());
					if (incidencia.getFechaInicio() != null)
						incidenciaAlmacenada.setFechaInicio(incidencia.getFechaInicio());
					return incidenciaAlmacenada;
				}
			}
			throw new CustomException("Incidencia no encontrada con identificador: "+incidencia.getIdentificador(), 4);
		}
		throw new CustomException("No se ha especificado la incidencia a actualizar ",1);
	}

	@Override
	public ArrayList<Incidencia> buscar(Incidencia filtro) throws CustomException{
		ArrayList<Incidencia> resultado = new ArrayList<>();
		if(filtro == null) {
			resultado = this.incidencias;
		}else {
			for(Incidencia incidencia:this.incidencias) {
				if(coinciden(filtro,incidencia))
					resultado.add(incidencia);
			}
		}
		if(resultado.isEmpty())
			throw new CustomException("No se han encontrado incidencias",4);
		
		return resultado;
	}
	
	private boolean coinciden(Incidencia incidencia1, Incidencia incidencia2) throws CustomException{
		boolean coinciden = true;
		if (incidencia1.getIdentificador() != null)
			coinciden = coinciden && incidencia1.getIdentificador().equals(incidencia2.getIdentificador());
		if (incidencia1.getNombreCiudadano() != null)
			coinciden = coinciden && incidencia1.getNombreCiudadano().equals(incidencia2.getNombreCiudadano());
		if (incidencia1.getDNI() != null)
			coinciden = coinciden && incidencia1.getDNI().equals(incidencia2.getDNI());
		if (incidencia1.getTelefono() != null)
			coinciden = coinciden && incidencia1.getTelefono().equals(incidencia2.getTelefono());
		if (incidencia1.getDescripcion() != null)
			coinciden = coinciden && incidencia1.getDescripcion().equals(incidencia2.getDescripcion());
		if (incidencia1.getLocalizacion() != null)
			coinciden = coinciden && incidencia1.getLocalizacion().equals(incidencia2.getLocalizacion());
		if (incidencia1.getTipoIncidencia() != null)
			coinciden = coinciden && incidencia1.getTipoIncidencia().equals(incidencia2.getTipoIncidencia());
		if(incidencia1.getProceso() != null)
			coinciden = coinciden && incidencia1.getProceso().getIdentificador().equals(incidencia2.getProceso().getIdentificador());
		if (incidencia1.getFechaInicio() != null)
			coinciden = coinciden && incidencia1.getFechaInicio().equals(incidencia2.getFechaInicio());

		return coinciden;
	}


	@Override
	public ArrayList<Incidencia> obtenerIncidenciaSinAsignar() throws CustomException{
		ArrayList<Incidencia> sinAsignar = new ArrayList<>();
		for(Incidencia i:this.incidencias) {
			if(i.getProceso() == null)
				sinAsignar.add(i);
		}
		if(sinAsignar.isEmpty())
			throw new CustomException("No se han encontrado incidencias",4);
		return sinAsignar;
	}

}
