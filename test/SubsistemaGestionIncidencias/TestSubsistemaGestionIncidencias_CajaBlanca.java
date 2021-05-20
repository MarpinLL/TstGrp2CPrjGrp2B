package SubsistemaGestionIncidencias;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
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

@DisplayName("Pruebas de caja blanca sobre actualizacion de incidencias")
class TestSubsistemaGestionIncidencias_CajaBlanca {
	private SubsistemaGestionIncidencias subsistemaGestionIncidencias;
	private Incidencia incidenciaAlmacenada;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		subsistemaGestionIncidencias = new SubsistemaGestionIncidencias();

		incidenciaAlmacenada = new Incidencia();
		incidenciaAlmacenada.setIdentificador(1);
		incidenciaAlmacenada.setNombreCiudadano("Jhon");
		incidenciaAlmacenada.setDNI("54101294H");
		incidenciaAlmacenada.setTelefono("34608219326");
		incidenciaAlmacenada.setDescripcion("qwertys");
		incidenciaAlmacenada.setLocalizacion("qwertys");
		incidenciaAlmacenada.setTipoIncidencia("Iluminación");
		incidenciaAlmacenada.setProceso(new Proceso());
		incidenciaAlmacenada.setFechaInicio(new Date());

		subsistemaGestionIncidencias.crear(incidenciaAlmacenada);

	}

	@AfterEach
	void tearDown() throws Exception {
		subsistemaGestionIncidencias = null;
		incidenciaAlmacenada = null;
	}

	@Test
	@DisplayName("Camino 1. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, F}")
	void actualizar_01() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");
		incidenciaActualizar.setDescripcion("qwerty");
		incidenciaActualizar.setLocalizacion("qwerty");
		incidenciaActualizar.setTipoIncidencia("Otra");
		incidenciaActualizar.setProceso(new Proceso());
		incidenciaActualizar.setFechaInicio(new Date());

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"La localizacion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTipoIncidencia(), incidenciaActualizar.getTipoIncidencia(),
						"El tipo no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"El proceso no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas no coinciden"));
	}

	@Test
	@DisplayName("Camino 2. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 21, F}")
	void actualizar_02() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");
		incidenciaActualizar.setDescripcion("qwerty");
		incidenciaActualizar.setLocalizacion("qwerty");
		incidenciaActualizar.setTipoIncidencia("Otra");
		incidenciaActualizar.setProceso(new Proceso());

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"La localizacion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTipoIncidencia(), incidenciaActualizar.getTipoIncidencia(),
						"El tipo no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"El proceso no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 3. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 19, 21, F}")
	void actualizar_03() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");
		incidenciaActualizar.setDescripcion("qwerty");
		incidenciaActualizar.setLocalizacion("qwerty");
		incidenciaActualizar.setTipoIncidencia("Otra");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"La localizacion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTipoIncidencia(), incidenciaActualizar.getTipoIncidencia(),
						"El tipo no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 4. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 17, 19, 21, F}")
	void actualizar_04() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");
		incidenciaActualizar.setDescripcion("qwerty");
		incidenciaActualizar.setLocalizacion("qwerty");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"La localizacion no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 5. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 15, 17, 19, 21, F}")
	void actualizar_05() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");
		incidenciaActualizar.setDescripcion("qwerty");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"La descripcion no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"Las localizaciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 6. {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 13, 15, 17, 19, 21, F}")
	void actualizar_06() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");
		incidenciaActualizar.setTelefono("34608219325");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"El telefono no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"Las descripciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"Las localizaciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 7. {1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 13, 15, 17, 19, 21, F}")
	void actualizar_07() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");
		incidenciaActualizar.setDNI("54101293H");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(), "El DNI no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"Los telefonos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"Las descripciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"Las localizaciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 8. {1, 2, 3, 4, 5, 6, 7, 9, 11, 13, 15, 17, 19, 21, F}")
	void actualizar_08() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);
		incidenciaActualizar.setNombreCiudadano("Jhon Doe");

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertEquals(incidenciaAlmacenada.getNombreCiudadano(), incidenciaActualizar.getNombreCiudadano(),
						"El nombre del ciudadano no coincide"),
				() -> assertNotEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(),
						"Los DNI son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"Los telefonos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"Las descripciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"Las localizaciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 9. {1, 2, 3, 4, 5, 7, 9, 11, 13, 15, 17, 19, 21, F}")
	void actualizar_09() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(1);

		subsistemaGestionIncidencias.actualizar(incidenciaActualizar);

		assertAll(("El camino no se ha recorrido correctamente"),
				() -> assertNotEquals(incidenciaAlmacenada.getNombreCiudadano(),
						incidenciaActualizar.getNombreCiudadano(), "Los nombres del los ciudadanos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getDNI(), incidenciaActualizar.getDNI(),
						"Los DNI son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTelefono(), incidenciaActualizar.getTelefono(),
						"Los telefonos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getDescripcion(), incidenciaActualizar.getDescripcion(),
						"Las descripciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getLocalizacion(), incidenciaActualizar.getLocalizacion(),
						"Las localizaciones son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getTipoIncidencia(),
						incidenciaActualizar.getTipoIncidencia(), "Los tipos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getProceso(), incidenciaActualizar.getProceso(),
						"Los procesos son iguales"),
				() -> assertNotEquals(incidenciaAlmacenada.getFechaInicio(), incidenciaActualizar.getFechaInicio(),
						"Las fechas son iguales"));
	}

	@Test
	@DisplayName("Camino 10. {1, 2, 3, 2, 23, F}")
	void actualizar_10() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		incidenciaActualizar.setIdentificador(5);

		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.actualizar(incidenciaActualizar),
				"No salta la excepcion");
		assertEquals(e.codigo, 4, "El codigo de error no coincide");

	}

	@Test
	@DisplayName("Camino 11. {1, 2, 23, F}")
	void actualizar_11() throws CustomException {
		Incidencia incidenciaActualizar = new Incidencia();
		CustomException e;

		subsistemaGestionIncidencias = new SubsistemaGestionIncidencias();
		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.actualizar(incidenciaActualizar),
				"No salta la excepcion");
		assertEquals(e.codigo, 4, "El codigo de error no coincide");

	}

	@Test
	@DisplayName("Camino 12. {1, 22, F}")
	void actualizar_12() throws CustomException {
		Incidencia incidenciaActualizar = null;
		CustomException e;

		e = assertThrows(CustomException.class, () -> subsistemaGestionIncidencias.actualizar(incidenciaActualizar),
				"No salta la excepcion");
		assertEquals(e.codigo, 1, "El codigo de error no coincide");

	}
}
