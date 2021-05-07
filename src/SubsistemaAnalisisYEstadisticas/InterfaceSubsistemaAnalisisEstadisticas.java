package SubsistemaAnalisisYEstadisticas;

import Exception.CustomException;
import Model.Estadistica;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;

public interface InterfaceSubsistemaAnalisisEstadisticas {
    
    /*Permite el filtrado por rango de fechas, por campos de incidencia y distribuir los datos en dias (por defecto) o meses
    Devuelve un objeto estadisticas que contiene numero, coste y distribucion de los datos obtenidos*/
    public Estadistica obtenerEstadisticasIncidencias(String rango, Incidencia filtro, String distribucion) throws CustomException;
    
    /*Permite el filtrado por rango de fechas, por campos de proceso y distribuir los datos en dias (por defecto) o meses
    Devuelve un objeto estadisticas que contiene numero, coste y distribucion de los datos obtenidos*/
    public Estadistica obtenerEstadisticasProcesos(String rango, Proceso filtro, String distribucion) throws CustomException;
    
    /*Permite el filtrado por rango de fechas, por campos de orden de trabajo y distribuir los datos en dias (por defecto) o meses
    Devuelve un objeto estadisticas que contiene numero, coste y distribución de los datos obtenidos*/
    public Estadistica obtenerEstadisticasOrdenesTrabajo(String rango, OrdenTrabajo filtro, String distribucion) throws CustomException;
        
}
