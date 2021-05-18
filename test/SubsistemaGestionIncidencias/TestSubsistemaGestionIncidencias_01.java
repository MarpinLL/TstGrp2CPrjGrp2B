package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

class TestSubsistemaGestionIncidencias_01 {
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
	void testInicializar_01() {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(0);
		incidenciaEsperada.setNombreCiudadano("Ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Otra");
		
		Incidencia incidenciaActual = null;
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 3);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		
		try {
			incidenciaActual = subsistemaGestionIncidencias.inicializar(0, "ciudadano", "53199271H", "34608564255", "descripción de prueba", "localización de prueba", "Otra", null, fecha.getTime());
		} catch (CustomException e) {
		}

		assertEquals(incidenciaEsperada, incidenciaActual, "Introducir argumentos correctos CP-0001: No se a podido inicializar la incidencia");
	}


}
