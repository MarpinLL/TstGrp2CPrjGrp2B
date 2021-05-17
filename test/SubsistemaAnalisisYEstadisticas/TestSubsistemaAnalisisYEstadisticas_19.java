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
import Model.Proceso;
import SubsistemaGestionIncidencias.InterfaceSubsistemaGestionIncidencias;
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;
import SubsistemaGestionProcesos.InterfaceSubsistemaGestionProcesos;
import SubsistemaGestionProcesos.SubsistemaGestionProcesos;

class TestSubsistemaAnalisisYEstadisticas_19 {

	private static SubsistemaAnalisisEstadisticas s;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		InterfaceSubsistemaGestionProcesos sp = new SubsistemaGestionProcesos();
		s = new SubsistemaAnalisisEstadisticas(new SubsistemaGestionIncidencias(), new SubsistemaGestionOrdenTrabajo(),
				sp);

		Proceso p = new Proceso();
		p.setIdentificador(0);
		p.setDescripcion("proceso-1");
		p.setNombre("Proceso-1");
		p.setCoste(20.0);
		p.setEstimado(0.0);
		p.setResponsable("Paco Meralgo");
		p.setServicio("Asfalto");
		p.setEstado("Pendiente");
		ArrayList<Incidencia> inc = new ArrayList<>();
		inc.add(new Incidencia());
		inc.add(new Incidencia());
		p.setIncidencias(inc);
		
		ArrayList<OrdenTrabajo> ots = new ArrayList<>();
		ots.add(new OrdenTrabajo());
		ots.add(new OrdenTrabajo());
		p.setOrdenesTrabajo(ots);
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 5);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		p.setFechaInicio(fechaSis.getTime());

		sp.crear(p);
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
	@DisplayName("Caso de prueba-0101 Argumentos correctos")
	void testObtenerEstadisticasProcesos_101() {

		// Arrange - Preparacion
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasProcesos("1/1/2000-1/1/3000", null, "dia");
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
	@DisplayName("Caso de prueba-0102 Rango de fechas incorrecto")
	void testObtenerEstadisticasProcesos_102() {

		// Assert - Afirmación
		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasProcesos("1/1/2000-1/1/-3000", null, "dia"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Fecha invalida", e.getMessage(), "Mensaje de excepcion incorrecto");

	}

	@Test
	@DisplayName("Caso de prueba-0103 Distribucion semana")
	void testObtenerEstadisticasProcesos_103() {

		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasProcesos("1/1/2000-1/1/3000", null, "semana");
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
	@DisplayName("Caso de prueba-0104 Distribucion incorrecta")
	void testObtenerEstadisticasProcesos_104() {

		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasProcesos("1/1/2000-1/1/-3000", null, "pepe"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Distribucion seleccionada incorrecta", e.getMessage(), "Mensaje de excepcion incorrecto");

	}
	
	@Test
	@DisplayName("Caso de prueba-0105 Aplicando un filtro")
	void testObtenerEstadisticasProcesos_105() {

		// Arrange - Prepacion
		SubsistemaGestionProcesos sp = new SubsistemaGestionProcesos();

		Proceso p = new Proceso();
		p.setIdentificador(0);
		p.setDescripcion("proceso-1");
		p.setNombre("Proceso-1");
		p.setCoste(20.0);
		p.setEstimado(0.0);
		p.setResponsable("Paco Meralgo2");
		p.setServicio("Asfalto");
		p.setEstado("Pendiente");
		ArrayList<Incidencia> inc = new ArrayList<>();
		inc.add(new Incidencia());
		inc.add(new Incidencia());
		p.setIncidencias(inc);
		
		ArrayList<OrdenTrabajo> ots = new ArrayList<>();
		ots.add(new OrdenTrabajo());
		ots.add(new OrdenTrabajo());
		p.setOrdenesTrabajo(ots);
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 5);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		p.setFechaInicio(fechaSis.getTime());

		

		try {
			sp.crear(p);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);
		Proceso filtro = new Proceso();
		filtro.setResponsable("Paco Meralgo");

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasProcesos("1/1/2000-1/1/3000", filtro, "dia");
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
