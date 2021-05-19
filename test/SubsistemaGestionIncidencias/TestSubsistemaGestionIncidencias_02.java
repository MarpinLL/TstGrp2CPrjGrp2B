package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;

@DisplayName("PRU-0002 Pruebas sobre creación de incidencias")
class TestSubsistemaGestionIncidencias_02 {
	private SubsistemaGestionIncidencias subsistemaGestionIncidencias;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {

	}

	@BeforeEach
	void setUp() throws Exception {
		subsistemaGestionIncidencias = new SubsistemaGestionIncidencias();
	}

	@AfterEach
	void tearDown() throws Exception {
		subsistemaGestionIncidencias = null;
	}

	@Test
	@DisplayName("CP-0021 Introducir una Incidencia válida")
	void testCrear_01() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("Ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Otra");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.crear(incidenciaEsperada);

		assertEquals(incidenciaEsperada, incidenciaActual, "Las incidencias no son iguales");
	}

	@Test
	@DisplayName("CP-0022 Introducir un null")
	void testCrear_02() throws CustomException {
		assertThrows(CustomException.class, () -> {
			subsistemaGestionIncidencias.crear(null);
		}, "No salta la excepcion");

	}

	@Test
	@DisplayName("CP-0023 Introducir identificador repetido")
	void testCrear_03() throws CustomException {
		Incidencia incidencia = new Incidencia();
		incidencia.setIdentificador(1);
		incidencia.setFechaInicio(new Date());

		subsistemaGestionIncidencias.crear(incidencia);

		assertThrows(CustomException.class, () -> {
			subsistemaGestionIncidencias.crear(incidencia);
		}, "No salta la excepcion");

	}

	@Test
	@DisplayName("CP-0023 Introducir fechaInicio < hoy")
	void testCrear_04() throws CustomException {
		Incidencia incidencia = new Incidencia();
		incidencia.setIdentificador(1);
		incidencia.setFechaInicio(new GregorianCalendar(1995, Calendar.OCTOBER, 27).getTime());


		assertThrows(CustomException.class, () -> {
			subsistemaGestionIncidencias.crear(incidencia);
		}, "No salta la excepcion");

	}

}
