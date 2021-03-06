package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;

@DisplayName("PRU-0001 Pruebas sobre inicialización de incidencias")
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
	@DisplayName("CP-0001 Introducir argumentos correctos")
	void testInicializar_01() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Otra");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Otra", null, new Date());

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
	@DisplayName("CP-0002 Introducir argumentos a null")
	void testInicializar_02() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(null, null, null, null, null, null, null,
				null, null);

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
	@DisplayName("CP-0003 Introducir identificador negativo")
	void testInicializar_03() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(-1, "ciudadano", "53199271H", "34608564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0004 Introducir nombre ciudadano no alfabetico")
	void testInicializar_04() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano1", "53199271H", "34608564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0005 Introducir nombre ciudadano > 100")
	void testInicializar_05() {
		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.inicializar(1,
				"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa",
				"53199271H", "34608564255", "descripción de prueba", "localización de prueba", "Otra", null,
				new Date()), "No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0006 Introducir DNI con formato incorrecto")
	void testInicializar_06() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "531992719", "34608564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0007 Introducir DNI > 9")
	void testInicializar_07() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "513199271H", "34608564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");

	}

	@Test
	@DisplayName("CP-0008 Introducir telefono sin prefijo")
	void testInicializar_08() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H", "608564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0009 Introducir telefono con caracteres no numericos")
	void testInicializar_09() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H", "3460a564255",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0010 Introducir telefono > 15")
	void testInicializar_10() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H", "3460856425512341",
						"descripción de prueba", "localización de prueba", "Otra", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0011 Introducir descripcion > 240")
	void testInicializar_11() {
		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.inicializar(1, "ciudadano",
				"53199271H", "34608564255",
				"aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa "
						+ "aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa "
						+ "aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa",
				"localización de prueba", "Otra", null, new Date()), "No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0012 Introducir localización > 150")
	void testInicializar_12() {
		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.inicializar(1, "ciudadano",
				"53199271H", "34608564255", "descripción de prueba",
				"aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa "
						+ "aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa",
				"Otra", null, new Date()), "No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}

	@Test
	@DisplayName("CP-0013 Introducir tipoIncidencia = Iluminación")
	void testInicializar_13() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Iluminación");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Iluminación", null, new Date());

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
	@DisplayName("CP-0014 Introducir tipoIncidencia = Parques")
	void testInicializar_14() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Parques");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Parques", null, new Date());

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
	@DisplayName("CP-0015 Introducir tipoIncidencia = Asfalto")
	void testInicializar_15() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Asfalto");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Asfalto", null, new Date());

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
	@DisplayName("CP-0016 Introducir tipoIncidencia = Alcantarillado")
	void testInicializar_16() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Alcantarillado");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Alcantarillado", null, new Date());

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
	@DisplayName("CP-0017 Introducir tipoIncidencia = Tráfico")
	void testInicializar_17() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Tráfico");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Tráfico", null, new Date());

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
	@DisplayName("CP-0018 Introducir tipoIncidencia = Suministro")
	void testInicializar_18() throws CustomException {
		Incidencia incidenciaEsperada = new Incidencia();
		incidenciaEsperada.setIdentificador(1);
		incidenciaEsperada.setNombreCiudadano("ciudadano");
		incidenciaEsperada.setDNI("53199271H");
		incidenciaEsperada.setTelefono("34608564255");
		incidenciaEsperada.setDescripcion("descripción de prueba");
		incidenciaEsperada.setLocalizacion("localización de prueba");
		incidenciaEsperada.setTipoIncidencia("Suministro");
		incidenciaEsperada.setFechaInicio(new Date());

		Incidencia incidenciaActual = subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H",
				"34608564255", "descripción de prueba", "localización de prueba", "Suministro", null, new Date());

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
	@DisplayName("CP-0019 Introducir tipoIncidencia no valido")
	void testInicializar_19() {
		CustomException e;

		e = assertThrows(CustomException.class,
				() -> subsistemaGestionIncidencias.inicializar(1, "ciudadano", "53199271H", "34608564255",
						"descripción de prueba", "localización de prueba", "qwerty", null, new Date()),
				"No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion erroneo");
	}
	
	@Test
	@Disabled("Esta prueba no se realiza porque las pruebas sobre el objeto Date son responsabilidad de Java")
	@DisplayName("CP-0020 Introducir fechaInicio con formato no valido")
	void testInicializar_20() {
	}

}
