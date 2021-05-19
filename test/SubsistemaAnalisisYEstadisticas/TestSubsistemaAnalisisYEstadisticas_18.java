package SubsistemaAnalisisYEstadisticas;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
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
import Model.Proceso;
import SubsistemaGestionIncidencias.InterfaceSubsistemaGestionIncidencias;
import SubsistemaGestionIncidencias.SubsistemaGestionIncidencias;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;
import SubsistemaGestionProcesos.SubsistemaGestionProcesos;

@DisplayName("PRU-0018 Pruebas sobre obtener estadisticas de incidencias")
class TestSubsistemaAnalisisYEstadisticas_18 {

	private static SubsistemaAnalisisEstadisticas s;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

		InterfaceSubsistemaGestionIncidencias si = new SubsistemaGestionIncidencias();
		s = new SubsistemaAnalisisEstadisticas(si, new SubsistemaGestionOrdenTrabajo(),
				new SubsistemaGestionProcesos());

		Incidencia i = new Incidencia();
		i.setIdentificador(0);
		i.setNombreCiudadano("Ciudadano");
		i.setDNI("53199271H");
		i.setTelefono("34608564255");
		i.setDescripcion("Descripcion de prueba");
		i.setLocalizacion("Localizacion de la prueba");
		i.setTipoIncidencia("Asfalto");
		Proceso p = new Proceso();
		p.setCoste(20.0);
		i.setProceso(p);

		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 3);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		i.setFechaInicio(fechaSis.getTime());

		si.crear(i);
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
	@DisplayName("CP-0096 Argumentos correctos")
	void testObtenerEstadisticasIncidencias_96() {

		// Arrange - Preparacion
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasIncidencias("1/1/2000-1/1/3000", null, "dia");
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
	@DisplayName("CP-0097 Rango de fechas incorrecto")
	void testObtenerEstadisticasIncidencias_97() {

		// Assert - Afirmación
		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasIncidencias("1/1/2000-1/1/-3000", null, "dia"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Fecha invalida", e.getMessage(), "Mensaje de excepcion incorrecto");

	}

	@Test
	@DisplayName("CP-0098 Distribucion semana")
	void testObtenerEstadisticasIncidencias_98() {

		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasIncidencias("1/1/2000-1/1/3000", null, "semana");
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
	@DisplayName("CP-0099 Distribucion incorrecta")
	void testObtenerEstadisticasIncidencias_99() {

		CustomException e = assertThrows(CustomException.class,
				() -> s.obtenerEstadisticasIncidencias("1/1/2000-1/1/-3000", null, "pepe"));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Distribucion seleccionada incorrecta", e.getMessage(), "Mensaje de excepcion incorrecto");

	}
	
	@Test
	@DisplayName("CP-0100 Aplicando un filtro")
	void testObtenerEstadisticasIncidencias_100() {

		// Arrange - Prepacion
		InterfaceSubsistemaGestionIncidencias si = new SubsistemaGestionIncidencias();

		Incidencia i = new Incidencia();
		i.setIdentificador(0);
		i.setNombreCiudadano("Ciudadano");
		i.setDNI("53199272H");
		i.setTelefono("34608564255");
		i.setDescripcion("Descripcion de prueba");
		i.setLocalizacion("Localizacion de la prueba");
		i.setTipoIncidencia("Asfalto");
		Proceso p = new Proceso();
		p.setCoste(20.0);
		i.setProceso(p);

		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 3);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		i.setFechaInicio(fechaSis.getTime());

		try {
			si.crear(i);
		} catch (CustomException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		Estadistica esperado = new Estadistica();
		esperado.setCoste(20.0);
		esperado.setNumeroTotal(1);
		Incidencia filtro = new Incidencia();
		filtro.setDNI("53199271H");

		// Act - Ejecucion
		Estadistica real = null;

		// Comprobacion de que no se pase de 1 segundo
		real = assertTimeout(Duration.ofSeconds(1), () -> {
			try {
				return s.obtenerEstadisticasIncidencias("1/1/2000-1/1/3000", filtro, "dia");
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
