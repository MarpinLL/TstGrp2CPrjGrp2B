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

@DisplayName("Pruebas sobre Inicializar Presupuesto")
class TestSubsistemaGestionOrdenTrabajo_13 {
	
	private static SubsistemaGestionOrdenTrabajo sub;

	@BeforeEach
	void setUp() throws Exception {
		sub = new SubsistemaGestionOrdenTrabajo();
		
		/*ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");*/
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		/*ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, fechaSis.getTime(), 2, material, 2);
		pres.add(c1);*/
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
		OrdenTrabajo i = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				new ArrayList<>(), new ArrayList<>(), 1000.0, "Pepe", 10, fechaSis.getTime(), 80, "Pendiente de asignación", proceso);

		sub.crear(i);
	}

	@AfterAll
	static void tearDown() throws Exception {
		sub = null;
	}

	@Test
	@DisplayName("Caso de prueba Inicializar 0067 - Introducir todos los argumentos correctos")
	void testInicializar_67() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);

		Presupuesto presEsperado = new Presupuesto(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10);
		
		//Act
		Presupuesto presReal = null;
		try {
			presReal = sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(presEsperado, presReal, "Error al inicializar Presupuesto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0068 - Introducir todos los argumentos nulos")
	void testInicializar_68() {
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(null, null, null, null, null, null, null, null));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Campos nulos", e.getMessage(), "Mensaje de excepcion incorrecto");
		
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0069 - Introducir identificador de OT inexistente")
	void testInicializar_69() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 1234));

		assertEquals(4, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("NotFound, OT desconocida", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0070 - Introducir identificador repetido")
	void testInicializar_70() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//todo revisar esto
		try {
			sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345);
		} catch (CustomException e1) {
			e1.printStackTrace();
		}

		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(2, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Id Presupuesto ya registrado", e.getMessage(), "Mensaje de excepcion incorrecto");	
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0071 - Introducir identificador negativo")
	void testInicializar_71() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		//todo
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(-123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Campos nulos", e.getMessage(), "Mensaje de excepcion incorrecto");

	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0072 - Introducir empresa de más de 100 caracteres")
	void testInicializar_72() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee",
						5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//todo el mensaje de error está mal escrito
		assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0073 - Introducir presupuesto negativo")
	void testInicializar_73() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", -5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//todo el mensaje de error está mal escrito
		assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	
	/****************************************************************************************************/
	@Test
	@Tag("Disabled")
	@DisplayName("Caso de prueba Inicializar 0074 - Introducir fecha con formato incorrecto")
	void testInicializar_74() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//todo
		assertEquals(" ", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0075 - Introducir fecha anterior a la actual")
	void testInicializar_75() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 3);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Fecha erronea", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0076 - Introducir duración negativa")
	void testInicializar_76() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), -80, material, 10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//nombre excepción mal escrito
		assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0077 - Introducir ArrayList material vacío")
	void testInicializar_77() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);

		Presupuesto presEsperado = new Presupuesto(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10);
		
		//Act
		Presupuesto presReal = null;
		try {
			presReal = sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, 10, 12345);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(presEsperado, presReal, "Error al inicializar Presupuesto");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0078 - Introducir personal negativo")
	void testInicializar_78() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(123, "Eléctricas Pepe", 5000.0, fechaSis.getTime(), 80, material, -10, 12345));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//nombre excepción mal escrito
		assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
}
