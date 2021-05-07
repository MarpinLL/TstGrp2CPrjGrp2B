package SubsistemaGestionIncidencias;

import java.util.ArrayList;
import java.util.Date;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

public interface InterfaceSubsistemaGestionIncidencias {

	// Devuelve un objeto incidencia con los campos inicializados que se indiquen
	public Incidencia inicializar(Integer identificador, String ciudadano, String DNI, String telefono,
			String descripcion, String localizacion, String tipoIncidencia, Proceso proceso, Date fechaInicio) throws CustomException;

	// Crea una nueva incidencia y la almacena en el sistema
	public Incidencia crear(Incidencia incidencia) throws CustomException;

	/* Modifica una incidencia existente en el sistema, se modifican solo los campos indicados, es decir,
	 * si no se especifican se mantienen. 
	 * se puede modificar todo menos el identificador-
	 */
	public Incidencia actualizar(Incidencia incidencia) throws CustomException;

	// Devuelve el conjunto de incidencias que coincidan con los campos de
	// incidencia filtro, se puede especificar nulo
	public ArrayList<Incidencia> buscar(Incidencia filtro) throws CustomException;

	// Obtener el conjunto de incidencias sin asignar
	public ArrayList<Incidencia> obtenerIncidenciaSinAsignar() throws CustomException;
}
