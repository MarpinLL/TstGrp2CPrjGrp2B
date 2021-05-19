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
import Model.Proceso;

@DisplayName("PRU-0005 Pruebas sobre obtener incidencias sin asignar")
class TestSubsistemaGestionIncidencias_05 {
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
	@DisplayName("CP-0029 Llamar a obtenerIncidenciasSinAsignar() con incidencias sin asignar almacenadas")
	void testObtenerIncidenciaSinAsignar_01() throws CustomException {
		Incidencia filtro = new Incidencia();
		filtro.setNombreCiudadano("Ciudadano");

		Incidencia incidencia = new Incidencia();
		incidencia.setIdentificador(1);
		incidencia.setNombreCiudadano("Ciudadano");
		incidencia.setDNI("53199271H");
		incidencia.setTelefono("34608564255");
		incidencia.setDescripcion("descripción de prueba");
		incidencia.setLocalizacion("localización de prueba");
		incidencia.setTipoIncidencia("Otra");
		incidencia.setFechaInicio(new Date());

		Incidencia incidencia2 = new Incidencia();
		incidencia2.setIdentificador(2);
		incidencia2.setNombreCiudadano("Ciudadano");
		incidencia2.setDNI("53199271H");
		incidencia2.setTelefono("34608564255");
		incidencia2.setDescripcion("descripción de prueba");
		incidencia2.setLocalizacion("localización de prueba");
		incidencia2.setTipoIncidencia("Otra");
		incidencia2.setFechaInicio(new Date());
		incidencia2.setProceso(new Proceso());

		subsistemaGestionIncidencias.crear(incidencia);
		subsistemaGestionIncidencias.crear(incidencia2);

		for (Incidencia i : subsistemaGestionIncidencias.obtenerIncidenciaSinAsignar()) {
			assertNull(i.getProceso(), "La incidencia está asignada");
			System.out.println(i.getProceso());
		}

	}

	@Test
	@DisplayName("CP-0028 Introducir un null")
	void testObtenerIncidenciaSinAsignar_02() throws CustomException {
		assertThrows(CustomException.class, () -> {
			subsistemaGestionIncidencias.obtenerIncidenciaSinAsignar();
		}, "No salta la excepcion");
	}
}
