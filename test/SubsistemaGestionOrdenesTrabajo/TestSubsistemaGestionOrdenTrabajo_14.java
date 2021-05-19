package SubsistemaGestionOrdenesTrabajo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

@DisplayName("Pruebas sobre Crear Orden de Trabajo")
class TestSubsistemaGestionOrdenTrabajo_14 {
	
	private static SubsistemaGestionOrdenTrabajo sub;

	@BeforeEach
	void setUp() throws Exception {
		sub = new SubsistemaGestionOrdenTrabajo();
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		OrdenTrabajo i = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				new ArrayList<>(), new ArrayList<>(), 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", null);

		sub.crear(i);
	}

	@AfterAll
	static void tearDown() throws Exception {
		sub = null;
	}
	

	@Test
	@DisplayName("Caso de prueba Crear 0079 - Introducir una Orden de Trabajo nula")
	void testInicializar_79() {
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(null));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OT nula", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear 0080 - Introducir argumentos obligatorios")
	void testInicializar_80() {
		//Arrange		
		OrdenTrabajo otEsperada = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		OrdenTrabajo otParametro = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.crear(otParametro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(otEsperada, otReal, "Error al crear ot");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear 0081 - Introducir todos los argumentos")
	void testInicializar_81() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
		OrdenTrabajo otEsperada = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, null, 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);
		
		OrdenTrabajo otParametro = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, null, 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);
		
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.crear(otParametro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(otEsperada, otReal, "Error al crear ot");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear 0082 - Introducir identificador OT ya existente")
	void testInicializar_82() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
		
		OrdenTrabajo otParametro = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, null, 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(otParametro));

		assertEquals(2, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Id ya registrado", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear 0083 - Introducir presupuestos")
	void testInicializar_83() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 3);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, fechaSis.getTime(), 2, material, 2);
		pres.add(c1);
		
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
		
		OrdenTrabajo otParametro = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, pres, 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(otParametro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("No se pueden incorporar presupuestos en la creación", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear 0084 - Introducir fecha anterior a la actual")
	void testInicializar_84() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 3);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
		
		OrdenTrabajo otParametro = new OrdenTrabajo(123, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, null, 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(otParametro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Fecha errónea", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
}
