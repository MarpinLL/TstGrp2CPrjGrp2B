package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

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
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;

@DisplayName("Pruebas Asignar Incidencias a Proceso")
class TestSubsistemaGestionProcesos_11 {
	static private SubsistemaGestionProcesos sub;
	static private Incidencia i1;
	static private Incidencia i2;
	static private Incidencia i3;
	static private Proceso original;
	static private Calendar fecha;
	
	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();
		SubsistemaGestionIncidencias incs = new SubsistemaGestionIncidencias();
		
		i1 = incs.inicializar(1, "ciudadano", "53199271H", "34608564255",
				"descrpcion de prueba", "localizacion de prueba", "Otra", null, new Date());
		i2 = incs.inicializar(2, "ciudadano", "53199271H", "34608564255",
				"descrpcion de prueba", "localizacion de prueba", "Otra", null, new Date());
		i3 = incs.inicializar(3, "ciudadano", "53199271H", "34608564255",
				"descrpcion de prueba", "localizacion de prueba", "Otra", null, new Date());
		
		fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2020);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		original = sub.crear(new Proceso(1,"proceso-1","proceso primero", 15.00 , 0.0, "Pendiente", "Paco Meralgo","Asfalto", null, null, fecha.getTime()));
	}

	@AfterAll
	static void tearDown() throws Exception {
	
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarIncidencia 0052 - Introducir Argumentos correctos")
	void testAsignarIncidencia_52() {
		Proceso esperado = new Proceso();
		esperado.setIdentificador(1);
		esperado.setNombre("proceso-1");
		esperado.setDescripcion("proceso primero");
		esperado.setCoste(15.0);
		esperado.setEstimado(0.0);
		esperado.setEstado("Pendiente");
		esperado.setResponsable("Paco Meralgo");
		esperado.setServicio("Asfalto");

		esperado.setIncidencias(null);
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
		incidencias.add(i1);
		incidencias.add(i2);
		incidencias.add(i3);
		esperado.setIncidencias(incidencias);
		
		esperado.setFechaInicio(fecha.getTime());
		
		Proceso x = null;
		try {
			x = sub.asignarIncidencia(original, incidencias);
		} catch (CustomException ex) {
			System.out.println(ex);
		}
		
		Proceso obtenido = x;
		assertAll("Introducir argumentos correctos CP-0047: Las incidencias se han asignado incorrectamente",
			    () -> assertEquals(esperado.getIdentificador(), obtenido.getIdentificador()),
			    () -> assertEquals(esperado.getNombre(), obtenido.getNombre()),
			    () -> assertEquals(esperado.getDescripcion(), obtenido.getDescripcion()),
			    () -> assertEquals(esperado.getCoste(), obtenido.getCoste()),
			    () -> assertEquals(esperado.getEstimado(), obtenido.getEstimado()),
			    () -> assertEquals(esperado.getResponsable(), obtenido.getResponsable()),
			    () -> assertEquals(esperado.getEstado(), obtenido.getEstado()),
			    () -> assertEquals(esperado.getServicio(), obtenido.getServicio()),
			    () -> assertEquals(esperado.getOrdenesTrabajo(), obtenido.getOrdenesTrabajo()),
			    () -> assertEquals(esperado.getFechaInicio(), obtenido.getFechaInicio()),
			    () -> assertEquals(esperado.getIncidencias().size(), obtenido.getIncidencias().size()),
				() -> assertTrue(obtenido.getIncidencias().containsAll(esperado.getIncidencias()))
			);
		
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarIncidencia 0053 - Introducir Proceso null")
	void testAsignarIncidencia_53() {
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
		incidencias.add(i1);
		incidencias.add(i2);
		incidencias.add(i3);
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarIncidencia(null, incidencias);
		}, "Introducir proceso null CP-0053: Las incidencias se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir proceso null CP-0053: Las incidencias se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarIncidencia 0054 - Introducir incidencias vacías")
	void testAsignarIncidencia_54() {
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarIncidencia(original, incidencias);
		}, "Introducir proceso null CP-0054: Las incidencias se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir incidencias vacias CP-0054: Las incidencias se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarIncidencia 0055 - Introducir incidencias null")
	void testAsignarIncidencia_55() {
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarIncidencia(original, null);
		}, "Introducir proceso null CP-0055: Las incidencias se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir incidencias null CP-0055: Las incidencias se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarIncidencia 0056 - Introducir incidencias repetidas")
	void testAsignarIncidencia_56() {
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();
		incidencias.add(i1);
		incidencias.add(i2);
		incidencias.add(i3);
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarIncidencia(original, incidencias);
			sub.asignarIncidencia(original, incidencias);
		}, "Introducir proceso null CP-0056: Las incidencias se han asignado incorrectamente");
		
		assertEquals(2, ex.codigo, "Introducir incidencias repetidas CP-0056: Las incidencias se han asignado incorrectamente");
	}

}
