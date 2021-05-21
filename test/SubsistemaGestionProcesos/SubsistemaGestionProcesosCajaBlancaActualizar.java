package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;

@DisplayName("Pruebas Actualizar caja blanca")
class SubsistemaGestionProcesosCajaBlancaActualizar {
	
	static private SubsistemaGestionProcesos sub;
	static private Proceso original;
	
	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();
		
		original = new Proceso();
		original.setIdentificador(0);
		original.setNombre("proceso-1");
		original.setDescripcion("proceso primero");
		original.setCoste(15.0);
		original.setEstimado(0.0);
		original.setEstado("Pendiente");
		original.setResponsable("Paco Meralgo");
		original.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		original.setIncidencias(incidencias);
		
		SubsistemaGestionOrdenTrabajo ots = new SubsistemaGestionOrdenTrabajo();
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = null, o2 = null;
		try {
			o1 = ots.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "Pendiente de asignación", null);
			o2 = ots.inicializar(12346,"Necesario arreglar un par de farolas situadas en la calle Horreo",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		ordenes.add(o1);
		ordenes.add(o2);
		original.setOrdenesTrabajo(ordenes);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 6);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		original.setFechaInicio(fecha.getTime());
		
		sub.crear(original);
	}
	

	@AfterAll
	static void tearDown() throws Exception {
	}

	@DisplayName("Camino 1")
	@Test
	void testActualizar_1() {
		Proceso actualiza = new Proceso();
		
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable("Paco Meralgo");
		actualiza.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		actualiza.setIncidencias(incidencias);
		
		SubsistemaGestionOrdenTrabajo ots = new SubsistemaGestionOrdenTrabajo();
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = null, o2 = null; 
		try {
			o1 = ots.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "Pendiente de asignación", null);
			o2 = ots.inicializar(12346,"Necesario arreglar un par de farolas situadas en la calle Horreo",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		ordenes.add(o1);
		ordenes.add(o2);
		actualiza.setOrdenesTrabajo(ordenes);
		
		
		actualiza.setFechaInicio(original.getFechaInicio());
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 1 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(actualiza2.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(actualiza2.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(actualiza2.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(actualiza2.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(actualiza2.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	
	@DisplayName("Camino 2")
	@Test
	void testActualizar_2() {
		Proceso actualiza = new Proceso();
		
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable("Paco Meralgo");
		actualiza.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		actualiza.setIncidencias(incidencias);
		
		SubsistemaGestionOrdenTrabajo ots = new SubsistemaGestionOrdenTrabajo();
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = null, o2 = null; 
		try {
			o1 = ots.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "Pendiente de asignación", null);
			o2 = ots.inicializar(12346,"Necesario arreglar un par de farolas situadas en la calle Horreo",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		ordenes.add(o1);
		ordenes.add(o2);
		actualiza.setOrdenesTrabajo(ordenes);
		
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 2 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(actualiza2.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(actualiza2.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(actualiza2.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(actualiza2.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	
	@DisplayName("Camino 3")
	@Test
	void testActualizar_3() {
		Proceso actualiza = new Proceso();
		
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable("Paco Meralgo");
		actualiza.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		actualiza.setIncidencias(incidencias);		
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 3 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(actualiza2.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(actualiza2.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(actualiza2.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	
	@DisplayName("Camino 4")
	@Test
	void testActualizar_4() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable("Paco Meralgo");
		actualiza.setServicio("Asfalto");
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 4 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(actualiza2.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(actualiza2.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	
	@DisplayName("Camino 5")
	@Test
	void testActualizar_5() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable("Paco Meralgo");
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 5 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(actualiza2.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(original.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	
	@DisplayName("Camino 6")
	@Test
	void testActualizar_6() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado("Pendiente");
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 6 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(actualiza2.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(original.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}

	@DisplayName("Camino 7")
	@Test
	void testActualizar_7() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(0.0);
		actualiza.setEstado(null);
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 7 incorrecto",
			    () -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
			    () -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
			    () -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
			    () -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
			    () -> assertEquals(actualiza2.getEstimado(), resultado2.getEstimado()),
			    () -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
			    () -> assertEquals(original.getEstado(), resultado2.getEstado()),
			    () -> assertEquals(original.getServicio(), resultado2.getServicio()),
			    () -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
			    () -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
			    () -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
			);
	}
	@DisplayName("Camino 8")
	@Test
	void testActualizar_8() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(15.0);
		actualiza.setEstimado(null);
		actualiza.setEstado(null);
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 8 incorrecto",
				() -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
				() -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
				() -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
				() -> assertEquals(actualiza2.getCoste(), resultado2.getCoste()),
				() -> assertEquals(original.getEstimado(), resultado2.getEstimado()),
				() -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
				() -> assertEquals(original.getEstado(), resultado2.getEstado()),
				() -> assertEquals(original.getServicio(), resultado2.getServicio()),
				() -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
				() -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
				() -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
				);
	}
	@DisplayName("Camino 9")
	@Test
	void testActualizar_9() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion("proceso primero");
		actualiza.setCoste(null);
		actualiza.setEstimado(null);
		actualiza.setEstado(null);
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 9 incorrecto",
				() -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
				() -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
				() -> assertEquals(actualiza2.getDescripcion(), resultado2.getDescripcion()),
				() -> assertEquals(original.getCoste(), resultado2.getCoste()),
				() -> assertEquals(original.getEstimado(), resultado2.getEstimado()),
				() -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
				() -> assertEquals(original.getEstado(), resultado2.getEstado()),
				() -> assertEquals(original.getServicio(), resultado2.getServicio()),
				() -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
				() -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
				() -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
				);
	}
	@DisplayName("Camino 10")
	@Test
	void testActualizar_10() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre("proceso-1");
		actualiza.setDescripcion(null);
		actualiza.setCoste(null);
		actualiza.setEstimado(null);
		actualiza.setEstado(null);
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 10 incorrecto",
				() -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
				() -> assertEquals(actualiza2.getNombre(), resultado2.getNombre()),
				() -> assertEquals(original.getDescripcion(), resultado2.getDescripcion()),
				() -> assertEquals(original.getCoste(), resultado2.getCoste()),
				() -> assertEquals(original.getEstimado(), resultado2.getEstimado()),
				() -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
				() -> assertEquals(original.getEstado(), resultado2.getEstado()),
				() -> assertEquals(original.getServicio(), resultado2.getServicio()),
				() -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
				() -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
				() -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
				);
	}
	
	@DisplayName("Camino 11")
	@Test
	void testActualizar_11() {
		Proceso actualiza = new Proceso();
		actualiza = new Proceso();
		actualiza.setIdentificador(0);
		actualiza.setNombre(null);
		actualiza.setDescripcion(null);
		actualiza.setCoste(null);
		actualiza.setEstimado(null);
		actualiza.setEstado(null);
		actualiza.setResponsable(null);
		actualiza.setServicio(null);
		actualiza.setIncidencias(null);
		actualiza.setOrdenesTrabajo(null);
		actualiza.setFechaInicio(null);
		
		Proceso resultado = null;
		try {
			resultado = sub.actualizar(actualiza);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		Proceso actualiza2 = actualiza;
		Proceso resultado2 = resultado;
		assertAll("Camino 11 incorrecto",
				() -> assertEquals(actualiza2.getIdentificador(), resultado2.getIdentificador()),
				() -> assertEquals(original.getNombre(), resultado2.getNombre()),
				() -> assertEquals(original.getDescripcion(), resultado2.getDescripcion()),
				() -> assertEquals(original.getCoste(), resultado2.getCoste()),
				() -> assertEquals(original.getEstimado(), resultado2.getEstimado()),
				() -> assertEquals(original.getResponsable(), resultado2.getResponsable()),
				() -> assertEquals(original.getEstado(), resultado2.getEstado()),
				() -> assertEquals(original.getServicio(), resultado2.getServicio()),
				() -> assertEquals(original.getFechaInicio(), resultado2.getFechaInicio()),
				() -> assertEquals(original.getIncidencias(), resultado2.getIncidencias()),
				() -> assertEquals(original.getOrdenesTrabajo(), resultado2.getOrdenesTrabajo())
				);
	}
	
	@DisplayName("Camino 12")
	@Test
	void testActualizar_12() {

		CustomException exc = assertThrows(CustomException.class, () -> {
			Proceso actualiza = new Proceso();
			actualiza = new Proceso();
			actualiza.setIdentificador(-1);
			actualiza.setNombre(null);
			actualiza.setDescripcion(null);
			actualiza.setCoste(null);
			actualiza.setEstimado(null);
			actualiza.setEstado(null);
			actualiza.setResponsable(null);
			actualiza.setServicio(null);
			actualiza.setIncidencias(null);
			actualiza.setOrdenesTrabajo(null);
			actualiza.setFechaInicio(null);
			sub.actualizar(actualiza);
		});
		
		assertEquals(4, exc.codigo, "Camino 12 incorrecto");
	}
	
	@DisplayName("Camino 13")
	@Test
	void testActualizar_13() {
		CustomException exc = assertThrows(CustomException.class, () -> {
			Proceso actualiza = new Proceso();
			actualiza = new Proceso();
			actualiza.setIdentificador(null);
			actualiza.setNombre(null);
			actualiza.setDescripcion(null);
			actualiza.setCoste(null);
			actualiza.setEstimado(null);
			actualiza.setEstado(null);
			actualiza.setResponsable(null);
			actualiza.setServicio(null);
			actualiza.setIncidencias(null);
			actualiza.setOrdenesTrabajo(null);
			actualiza.setFechaInicio(null);
			sub.actualizar(actualiza);
		});
		
		assertEquals(4, exc.codigo, "Camino 13 incorrecto");
	}
}
