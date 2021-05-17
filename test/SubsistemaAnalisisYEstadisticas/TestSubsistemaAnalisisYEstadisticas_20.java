package SubsistemaAnalisisYEstadisticas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Estadistica;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;
import SubsistemaGestionIncidencias.InterfaceSubsistemaGestionIncidencias;
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.InterfaceSubsistemaGestionOrdenTrabajo;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;
import SubsistemaGestionProcesos.SubsistemaGestionProcesos;

class TestSubsistemaAnalisisYEstadisticas_20 {

	private static SubsistemaAnalisisEstadisticas s;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		InterfaceSubsistemaGestionOrdenTrabajo so = new SubsistemaGestionOrdenTrabajo();
		s = new SubsistemaAnalisisEstadisticas(new SubsistemaGestionIncidencias(), so,
				new SubsistemaGestionProcesos());

		OrdenTrabajo o = new OrdenTrabajo();
		o.setIdentificador(12345);		
		o.setDescripcion("Se cambiarán las farolas de la Avenida Rosalía de Castro");
		ArrayList<String> m = new ArrayList<>();
		m.add("Metal");
		m.add("Bombillas");	
		o.setMaterial(m);
		
		o.setCoste(20.0);
		o.setResponsable("Pepe");
		o.setPersonal(10);
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 11);
		fechaSis.set(Calendar.DAY_OF_MONTH, 31);
		o.setFechaInicio(fechaSis.getTime());
		o.setDuracion(80);
		o.setEstado("Pendiente de asignaciÃ³n");
		Proceso p = new Proceso();
		o.setProceso(p);		

		so.crear(o);
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Caso de prueba-0106 Argumentos correctos")
	void testObtenerEstadisticasIncidencias_106() {

		// Arrange - Preparacion
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasOrdenesTrabajo("1/1/2000-1/1/3000", null, "dia");
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		});

		// Assert - Afirmación
		assertEquals(esperado, real, "Error al contar estadisticas");

	}

	@Test
	@DisplayName("Caso de prueba-0107 Rango de fechas incorrecto")
	void testObtenerEstadisticasIncidencias_107() {

		// Assert - Afirmación
		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasOrdenesTrabajo("1/1/2000-1/1/-3000", null, "dia"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Fecha invalida", e.getMessage(), "Mensaje de excepcion incorrecto");

	}

	@Test
	@DisplayName("Caso de prueba-0108 Distribucion semana")
	void testObtenerEstadisticasIncidencias_108() {

		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasOrdenesTrabajo("1/1/2000-1/1/3000", null, "semana");
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		});

		// Assert - Afirmación
		assertEquals(esperado, real, "Error al contar estadisticas");

	}
	
	@Test
	@DisplayName("Caso de prueba-0109 Distribucion incorrecta")
	void testObtenerEstadisticasIncidencias_109() {

		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasOrdenesTrabajo("1/1/2000-1/1/-3000", null, "pepe"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Distribucion seleccionada incorrecta", e.getMessage(), "Mensaje de excepcion incorrecto");

	}
	
	@Test
	@DisplayName("Caso de prueba-0110 Aplicando un filtro")
	void testObtenerEstadisticasIncidencias_110() {

		// Arrange - Prepacion
		InterfaceSubsistemaGestionOrdenTrabajo so = new SubsistemaGestionOrdenTrabajo();

		OrdenTrabajo o = new OrdenTrabajo();
		o.setIdentificador(12345);		
		o.setDescripcion("Se cambiarán las farolas de la Avenida Rosalía de Castro");
		ArrayList<String> m = new ArrayList<>();
		m.add("Metal");
		m.add("Bombillas");	
		o.setMaterial(m);
		o.setCoste(20.0);
		o.setResponsable("Pepe1");
		o.setPersonal(10);
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 11);
		fechaSis.set(Calendar.DAY_OF_MONTH, 31);
		o.setFechaInicio(fechaSis.getTime());
		o.setDuracion(80);
		o.setEstado("Pendiente de asignaciÃ³n");
		Proceso p = new Proceso();
		o.setProceso(p);		

		

		try {
			so.crear(o);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);
		OrdenTrabajo filtro = new OrdenTrabajo();
		filtro.setResponsable("Pepe");

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasOrdenesTrabajo("1/1/2000-1/1/3000", filtro, "dia");
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;

		});

		// Assert - Afirmación
		assertEquals(esperado, real, "Error al contar estadisticas");

	}

}
