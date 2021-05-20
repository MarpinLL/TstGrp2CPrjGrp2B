package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;

@DisplayName("PRU-0003 Pruebas sobre actualización de incidencias")
class TestSubsistemaGestionIncidencias_03 {
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
	@DisplayName("CP-0025 Introducir una Incidencia válida")
	void testActualizar_01() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("Ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Otra");
		incidenciaEsperada.setFechaInicio(new Date());

		subsistemaGestionIncidencias.crear(incidenciaEsperada);

		incidenciaEsperada.setNombreCiudadano("Marcos");

		Incidencia incidenciaActual = subsistemaGestionIncidencias.actualizar(incidenciaEsperada);

		assertAll(("Las incidencias son distintas"),
				() -> assertEquals(incidenciaEsperada.getIdentificador(), incidenciaActual.getIdentificador(),
						"El id no coincide"),
				() -> assertEquals(incidenciaEsperada.getNombreCiudadano(), incidenciaActual.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaEsperada.getDNI(), incidenciaActual.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaEsperada.getTelefono(), incidenciaActual.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaEsperada.getDescripcion(), incidenciaActual.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertEquals(incidenciaEsperada.getLocalizacion(), incidenciaActual.getLocalizacion(),
						"La localizacion no coincide"),
				() -> assertEquals(incidenciaEsperada.getTipoIncidencia(), incidenciaActual.getTipoIncidencia(),
						"El tipo no coincide"),
				() -> assertEquals(incidenciaEsperada.getProceso(), incidenciaActual.getProceso(),
						"El proceso no coincide"),
				() -> assertEquals(incidenciaEsperada.getFechaInicio(), incidenciaActual.getFechaInicio(),
						"Las fechas no coinciden"));
	}

	@Test
	@DisplayName("CP-0026 Introducir un null")
	void testActualizar_02() throws CustomException {
		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.actualizar(null),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

}
