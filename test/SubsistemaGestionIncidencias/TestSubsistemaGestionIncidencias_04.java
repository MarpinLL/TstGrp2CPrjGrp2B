package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;

@DisplayName("PRU-0004 Pruebas sobre búsqueda de incidencias")
class TestSubsistemaGestionIncidencias_04 {
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
	@DisplayName("CP-0027 Introducir una Incidencia válida")
	void testBuscar_01() throws CustomException {
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

		subsistemaGestionIncidencias.crear(incidencia);
		subsistemaGestionIncidencias.crear(incidencia2);

		for (Incidencia i : subsistemaGestionIncidencias.buscar(filtro)) {
			assertEquals(i.getNombreCiudadano(), filtro.getNombreCiudadano(), "El nombre del ciudadano no coincide");
		}

	}

	@Test
	@DisplayName("CP-0028 Introducir un null")
	void testBuscar_02() throws CustomException {
		List<Incidencia> incidenciasEsperadas = new ArrayList<>();
		List<Incidencia> incidenciasActuales;

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

		subsistemaGestionIncidencias.crear(incidencia);
		subsistemaGestionIncidencias.crear(incidencia2);

		incidenciasEsperadas.add(incidencia);
		incidenciasEsperadas.add(incidencia2);

		incidenciasActuales = subsistemaGestionIncidencias.buscar(null);

		assertEquals(incidenciasEsperadas.size(), incidenciasActuales.size(), "Las listas no tienen el mismo tamaño");
		assertTrue(incidenciasEsperadas.containsAll(incidenciasActuales), "Listas no contienen los mismos elementos");
	}

}
