package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.management.Query;

import org.junit.Ignore;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.*;

@DisplayName("Pruebas Inicializar Proceso")
class TestSubsistemaGestionProcesos_06 {

	private static SubsistemaGestionProcesos sub;

	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();
	}

	@AfterAll
	static void tearDown() throws Exception {
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0031 - Introducir todos los argumentos correctos")
	void testInicializar_31() {
		Proceso p = new Proceso();
		p.setIdentificador(0);
		p.setNombre("proceso-1");
		p.setDescripcion("proceso primero");
		p.setCoste(15.0);
		p.setEstimado(0.0);
		p.setEstado("Pendiente");
		p.setResponsable("Paco Meralgo");
		p.setServicio("Asfalto");
		
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
		p.setIncidencias(incidencias);
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		p.setOrdenesTrabajo(ordenes);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		p.setFechaInicio(fecha.getTime());
		
		
		try {
			Proceso p2 = sub.inicializar(0, "proceso-1", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
			assertAll("qwrqrqwfqwefqwefwqefe",
				    () -> assertEquals(p.getIdentificador(), p2.getIdentificador()),
				    () -> assertEquals(p.getNombre(), p2.getNombre()),
				    () -> assertEquals(p.getDescripcion(), p2.getDescripcion()),
				    () -> assertEquals(p.getCoste(), p2.getCoste()),
				    () -> assertEquals(p.getEstimado(), p2.getEstimado()),
				    () -> assertEquals(p.getResponsable(), p2.getResponsable()),
				    () -> assertEquals(p.getEstado(), p2.getEstado()),
				    () -> assertEquals(p.getServicio(), p2.getServicio()),
				    () -> assertEquals(p.getFechaInicio(), p2.getFechaInicio()),
				    () -> assertEquals(p.getIncidencias(), p2.getIncidencias()),
				    () -> assertEquals(p.getOrdenesTrabajo(), p2.getOrdenesTrabajo())
				);
		}
		catch(CustomException ex){
			System.out.println(ex);
		}
		finally {
			
		}
		
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0032 - Introducir todos los argumentos null")
	void testInicializar_32() {
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(null, null, null, null, null, null, null, null, null, null, null);
		}, "Introducir argumentos null CP-0032: El proceso se ha inicializado incorrectamente");
		assertEquals(ex.codigo, 1, "Introducir argumentos null CP-0032: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0033 - Introducir identificador erróneo")
	void testInicializar_33() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021); 
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(-75, "proceso-1", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir identificador incorrecto CP-0033: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir identificador incorrecto CP-0033: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0034 - Introducir nombreProceso erroneo")
	void testInicializar_34() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021); 
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir nombreProceso incorrecto CP-0034: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir nombreProceso incorrecto CP-0034: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0035 - Introducir coste erroneo")
	void testInicializar_35() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021); 
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "proceso-1", "proceso primero", -55.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir coste incorrecto CP-0035: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir coste incorrecto CP-0035: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0036 - Introducir estado no existente")
	void testInicializar_36() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021); 
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "proceso-1", "proceso primero", 15.0, 0.0,  "Error", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir estado no existente CP-0036: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir estado no existente CP-0036: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0037 - Introducir incidencias null")
	void testInicializar_37() {		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021); 
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "proceso-1", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", null, ordenes, fecha.getTime());
		}, "Introducir incidencias null CP-0037: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir incidencias null CP-0037: El proceso se ha inicializado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0038 - Introducir fechaInicio pasada")
	void testInicializar_38() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2019); 
		fecha.set(Calendar.MONTH, 6);
		fecha.set(Calendar.DAY_OF_MONTH, 22);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "proceso-1", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir fechaInicio pasada CP-0038: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir fechaInicio pasada CP-0038: El proceso se ha inicializado incorrectamente");
	}
	
	@Disabled("No se puede probar la clase Date")
	@Test
	@DisplayName("Caso de prueba Inicializar proceso 0039 - Introducir fechaInicio erronea")
	void testInicializar_39() {
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
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 0); 
		fecha.set(Calendar.MONTH, 0);
		fecha.set(Calendar.DAY_OF_MONTH, 0);

		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.inicializar(5, "proceso-1", "proceso primero", 15.0, 0.0,  "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
		}, "Introducir fechaInicio erronea CP-0039: El proceso se ha inicializado incorrectamente");
		
		assertEquals(ex.codigo, 1, "Introducir fechaInicio erronea CP-0039: El proceso se ha inicializado incorrectamente");
	}

}
