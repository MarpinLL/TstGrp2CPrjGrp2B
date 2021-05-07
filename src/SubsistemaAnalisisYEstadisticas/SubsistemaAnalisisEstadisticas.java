package SubsistemaAnalisisYEstadisticas;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import Exception.CustomException;
import Model.Estadistica;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;
import SubsistemaGestionIncidencias.InterfaceSubsistemaGestionIncidencias;
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.InterfaceSubsistemaGestionOrdenTrabajo;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;
import SubsistemaGestionProcesos.InterfaceSubsistemaGestionProcesos;
import SubsistemaGestionProcesos.SubsistemaGestionProcesos;

public class SubsistemaAnalisisEstadisticas implements InterfaceSubsistemaAnalisisEstadisticas {
	private InterfaceSubsistemaGestionIncidencias gi;
	private InterfaceSubsistemaGestionOrdenTrabajo got;
	private InterfaceSubsistemaGestionProcesos gp;

	public SubsistemaAnalisisEstadisticas(InterfaceSubsistemaGestionIncidencias gi,
			InterfaceSubsistemaGestionOrdenTrabajo got, InterfaceSubsistemaGestionProcesos gp) {
		this.gi = gi;
		this.got = got;
		this.gp = gp;
	}

	@Override
	public Estadistica obtenerEstadisticasIncidencias(String rango, Incidencia filtro, String distribucion)
			throws CustomException {

		ArrayList<Incidencia> incidencias;
		ArrayList<Incidencia> incidenciasFiltradas = new ArrayList<>();

		if (distribucion != "dia" && distribucion != "semana") {
			throw new CustomException("Distribucion seleccionada incorrecta", 1);
		}

		String[] partes = rango.split("-");
		if (partes.length != 2) {
			throw new CustomException("Fecha invalida", 1);
		}

		String[] splittedFecha1 = partes[0].split("/");
		String[] splittedFecha2 = partes[1].split("/");
		if (splittedFecha1.length != 3 || splittedFecha2.length != 3) {
			throw new CustomException("Fecha invalida", 1);
		}

		int ano1 = Integer.parseInt(splittedFecha1[2]);
		int mes1 = Integer.parseInt(splittedFecha1[1]);
		int dia1 = Integer.parseInt(splittedFecha1[0]);

		int ano2 = Integer.parseInt(splittedFecha2[2]);
		int mes2 = Integer.parseInt(splittedFecha2[1]);
		int dia2 = Integer.parseInt(splittedFecha2[0]);

		if (ano1 < 1 || ano2 < 1 || mes1 < 1 || mes2 < 1 || mes1 > 12 || mes2 > 12 || dia1 < 1 || dia2 < 1 || dia1 > 31
				|| dia2 > 31) {
			throw new CustomException("Fecha invalida", 1);
		}

		Date fecha1 = new Date(ano1 - 1900, mes1 - 1, dia1);
		Date fecha2 = new Date(ano2 - 1900, mes2 - 1, dia2);

		if (fecha1.after(fecha2)) {
			throw new CustomException("Primera fecha menor que la segunda fecha", 1);
		}

		Date fechaMinima = new Date(200, 0, 1);
		Date fechaMaxima = new Date(0, 0, 1);
		incidencias = gi.buscar(filtro);

		if (incidencias == null || incidencias.size() == 0) {
			throw new CustomException("No existen incidencias en este rango de fechas", 1);
		}
		for (Incidencia i : incidencias) {
			Date fecha = i.getFechaInicio();
			if (fecha.after(fecha1) && fecha.before(fecha2)) {
				incidenciasFiltradas.add(i);

				// Comprueba en cada iteraccion si la fecha introducida es menor que la supuesta
				// minima
				if (i.getFechaInicio().compareTo(fechaMinima) < 0) {
					fechaMinima = i.getFechaInicio();
				}

				// Comprueba en cada iteraccion si la fecha introducida es mayor que la supuesta
				// maxima
				if (i.getFechaInicio().compareTo(fechaMaxima) > 0) {
					fechaMaxima = i.getFechaInicio();
				}
			}

		}
		Estadistica estadisticaFinal = new Estadistica();
		HashMap<String, Estadistica> HMDistribucion = new HashMap<>();
		Date fechaActual = new Date();
		fechaActual.setTime(fechaMinima.getTime());
		int nIncidencias;
		double coste;
		if (distribucion == "dia") {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				
				nIncidencias = 0;
				coste = 0.0;
				for (Incidencia i : incidenciasFiltradas) {
					
					if (i.getFechaInicio().compareTo(fechaActual) == 0) {
						nIncidencias++;
						if (i.getProceso() != null) {
							coste += i.getProceso().getCoste();
						}
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nIncidencias, coste / nIncidencias));

				fechaActual.setDate(fechaActual.getDate() + 1);
			}
		} else {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				
				nIncidencias = 0;
				coste = 0.0;
				for (Incidencia i : incidenciasFiltradas) {
					if ((i.getFechaInicio().getTime() >= fechaActual.getTime())
							&& (i.getFechaInicio().getTime() < (fechaActual.getTime() + 604800000))) {
						
						nIncidencias++;
						if (i.getProceso() != null) {
							coste += i.getProceso().getCoste();
						}
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nIncidencias, coste / nIncidencias));

				fechaActual.setDate(fechaActual.getDate() + 7);
			}
		}
		estadisticaFinal.setDistribucion(HMDistribucion);
		int nFinal = 0;
		double costeFinal = 0.0;
		for (Estadistica e : HMDistribucion.values()) {
			nFinal += e.getNumeroTotal();
			costeFinal += e.getCoste() * e.getNumeroTotal();
		}
		if ((nFinal > 0) && (costeFinal>0)) {
			estadisticaFinal.setCoste(costeFinal / nFinal);
		} else {
			estadisticaFinal.setCoste(0.0);
		}
		estadisticaFinal.setNumeroTotal(nFinal);
		return estadisticaFinal;
	}

	@Override
	public Estadistica obtenerEstadisticasProcesos(String rango, Proceso filtro, String distribucion)
			throws CustomException {

		ArrayList<Proceso> procesos;
		ArrayList<Proceso> procesosFiltrados = new ArrayList<>();

		if (distribucion != "dia" && distribucion != "semana") {
			throw new CustomException("Distribucion seleccionada incorrecta", 1);
		}

		String[] partes = rango.split("-");
		if (partes.length != 2) {
			throw new CustomException("Fecha invalida", 1);
		}

		String[] splittedFecha1 = partes[0].split("/");
		String[] splittedFecha2 = partes[1].split("/");
		if (splittedFecha1.length != 3 || splittedFecha2.length != 3) {
			throw new CustomException("Fecha invalida", 1);
		}

		int ano1 = Integer.parseInt(splittedFecha1[2]);
		int mes1 = Integer.parseInt(splittedFecha1[1]);
		int dia1 = Integer.parseInt(splittedFecha1[0]);

		int ano2 = Integer.parseInt(splittedFecha2[2]);
		int mes2 = Integer.parseInt(splittedFecha2[1]);
		int dia2 = Integer.parseInt(splittedFecha2[0]);

		if (ano1 < 1 || ano2 < 1 || mes1 < 1 || mes2 < 1 || mes1 > 12 || mes2 > 12 || dia1 < 1 || dia2 < 1 || dia1 > 31
				|| dia2 > 31) {
			throw new CustomException("Fecha invalida", 1);
		}

		Date fecha1 = new Date(ano1 - 1900, mes1 - 1, dia1);
		Date fecha2 = new Date(ano2 - 1900, mes2 - 1, dia2);

		if (fecha1.after(fecha2)) {
			throw new CustomException("Primera fecha menor que la segunda fecha", 1);
		}

		Date fechaMinima = new Date(200, 0, 1);
		Date fechaMaxima = new Date(0, 0, 1);
		procesos = gp.buscar(filtro);

		if (procesos == null || procesos.size() == 0) {
			throw new CustomException("No existen procesos en este rango de fechas", 1);
		}
		for (Proceso i : procesos) {
			Date fecha = i.getFechaInicio();
			if (fecha.after(fecha1) && fecha.before(fecha2)) {
				procesosFiltrados.add(i);

				// Comprueba en cada iteraccion si la fecha introducida es menor que la supuesta
				// minima
				if (i.getFechaInicio().compareTo(fechaMinima) < 0) {
					fechaMinima = i.getFechaInicio();
				}

				// Comprueba en cada iteraccion si la fecha introducida es mayor que la supuesta
				// maxima
				if (i.getFechaInicio().compareTo(fechaMaxima) > 0) {
					fechaMaxima = i.getFechaInicio();
				}
			}

		}
		Estadistica estadisticaFinal = new Estadistica();
		HashMap<String, Estadistica> HMDistribucion = new HashMap<>();
		Date fechaActual = new Date();
		fechaActual.setTime(fechaMinima.getTime());
		int nProcesos;
		double coste;
		if (distribucion == "dia") {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				nProcesos = 0;
				coste = 0.0;
				for (Proceso i : procesosFiltrados) {
					if (i.getFechaInicio().compareTo(fechaActual) == 0) {
						nProcesos++;
						coste += i.getCoste();
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nProcesos, coste / nProcesos));

				fechaActual.setDate(fechaActual.getDate() + 1);
			}
		} else {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				nProcesos = 0;
				coste = 0.0;
				for (Proceso i : procesosFiltrados) {
					if ((i.getFechaInicio().getTime() >= fechaActual.getTime())
							&& (i.getFechaInicio().getTime() < (fechaActual.getTime() + 604800000))) {
						nProcesos++;
						coste += i.getCoste();
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nProcesos, coste / nProcesos));

				fechaActual.setDate(fechaActual.getDate() + 7);
			}
		}
		estadisticaFinal.setDistribucion(HMDistribucion);
		int nFinal = 0;
		double costeFinal = 0.0;
		for (Estadistica e : HMDistribucion.values()) {
			nFinal += e.getNumeroTotal();
			costeFinal += e.getCoste() * e.getNumeroTotal();
		}
		if ((nFinal > 0) && (costeFinal>0)) {
			estadisticaFinal.setCoste(costeFinal / nFinal);
		} else {
			estadisticaFinal.setCoste(0.0);
		}
		estadisticaFinal.setNumeroTotal(nFinal);
		return estadisticaFinal;
	}

	@Override
	public Estadistica obtenerEstadisticasOrdenesTrabajo(String rango, OrdenTrabajo filtro, String distribucion)
			throws CustomException {

		ArrayList<OrdenTrabajo> ordenesTrabajo;
		ArrayList<OrdenTrabajo> ordenesTrabajoFiltradas = new ArrayList<>();

		if (distribucion != "dia" && distribucion != "semana") {
			throw new CustomException("Distribucion seleccionada incorrecta", 1);
		}

		String[] partes = rango.split("-");
		if (partes.length != 2) {
			throw new CustomException("Fecha invalida", 1);
		}

		String[] splittedFecha1 = partes[0].split("/");
		String[] splittedFecha2 = partes[1].split("/");
		if (splittedFecha1.length != 3 || splittedFecha2.length != 3) {
			throw new CustomException("Fecha invalida", 1);
		}

		int ano1 = Integer.parseInt(splittedFecha1[2]);
		int mes1 = Integer.parseInt(splittedFecha1[1]);
		int dia1 = Integer.parseInt(splittedFecha1[0]);

		int ano2 = Integer.parseInt(splittedFecha2[2]);
		int mes2 = Integer.parseInt(splittedFecha2[1]);
		int dia2 = Integer.parseInt(splittedFecha2[0]);

		if (ano1 < 1 || ano2 < 1 || mes1 < 1 || mes2 < 1 || mes1 > 12 || mes2 > 12 || dia1 < 1 || dia2 < 1 || dia1 > 31
				|| dia2 > 31) {
			throw new CustomException("Fecha invalida", 1);
		}

		Date fecha1 = new Date(ano1 - 1900, mes1 - 1, dia1);
		Date fecha2 = new Date(ano2 - 1900, mes2 - 1, dia2);

		if (fecha1.after(fecha2)) {
			throw new CustomException("Primera fecha menor que la segunda fecha", 1);
		}

		Date fechaMinima = new Date(200, 0, 1);
		Date fechaMaxima = new Date(0, 0, 1);
		ordenesTrabajo = got.buscar(filtro);

		if (ordenesTrabajo == null || ordenesTrabajo.size() == 0) {
			throw new CustomException("No existen ordenes de trabajo en este rango de fechas", 1);
		}
		for (OrdenTrabajo i : ordenesTrabajo) {
			if (i.getFechaInicio() != null) {
				Date fecha = i.getFechaInicio();
				if (fecha.after(fecha1) && fecha.before(fecha2)) {
					ordenesTrabajoFiltradas.add(i);

					// Comprueba en cada iteraccion si la fecha introducida es menor que la supuesta
					// minima
					if (i.getFechaInicio().compareTo(fechaMinima) < 0) {
						fechaMinima = i.getFechaInicio();
					}

					// Comprueba en cada iteraccion si la fecha introducida es mayor que la supuesta
					// maxima
					if (i.getFechaInicio().compareTo(fechaMaxima) > 0) {
						fechaMaxima = i.getFechaInicio();
					}
				}
			}
		}
		Estadistica estadisticaFinal = new Estadistica();
		HashMap<String, Estadistica> HMDistribucion = new HashMap<>();
		Date fechaActual = new Date();
		fechaActual.setTime(fechaMinima.getTime());
		int nOrdenTrabajos;
		double coste;
		if (distribucion == "dia") {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				nOrdenTrabajos = 0;
				coste = 0.0;
				for (OrdenTrabajo i : ordenesTrabajoFiltradas) {
					if (i.getFechaInicio().compareTo(fechaActual) == 0) {
						nOrdenTrabajos++;
						coste += i.getCoste();
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nOrdenTrabajos, coste / nOrdenTrabajos));

				fechaActual.setDate(fechaActual.getDate() + 1);
			}
		} else {
			while (fechaActual.compareTo(fechaMaxima) <= 0) {
				nOrdenTrabajos = 0;
				coste = 0.0;
				for (OrdenTrabajo i : ordenesTrabajoFiltradas) {
					if ((i.getFechaInicio().getTime() >= fechaActual.getTime())
							&& (i.getFechaInicio().getTime() < (fechaActual.getTime() + 604800000))) {
						nOrdenTrabajos++;
						coste += i.getCoste();
					}
				}
				HMDistribucion.put(
						fechaActual.getDay() + "/" + fechaActual.getMonth() + "/" + fechaActual.getYear() + 1900,
						new Estadistica(nOrdenTrabajos, coste / nOrdenTrabajos));

				fechaActual.setDate(fechaActual.getDate() + 7);
			}
		}
		estadisticaFinal.setDistribucion(HMDistribucion);
		int nFinal = 0;
		double costeFinal = 0.0;
		for (Estadistica e : HMDistribucion.values()) {
			nFinal += e.getNumeroTotal();
			costeFinal += e.getCoste() * e.getNumeroTotal();
		}
		if ((nFinal > 0) && (costeFinal>0)) {
			estadisticaFinal.setCoste(costeFinal / nFinal);
		} else {
			estadisticaFinal.setCoste(0.0);
		}
		estadisticaFinal.setNumeroTotal(nFinal);
		return estadisticaFinal;
	}

}
