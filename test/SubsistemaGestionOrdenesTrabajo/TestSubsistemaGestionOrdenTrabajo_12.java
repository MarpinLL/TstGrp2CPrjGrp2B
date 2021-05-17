package SubsistemaGestionOrdenesTrabajo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

class TestSubsistemaGestionOrdenTrabajo_12 {
	
	private static SubsistemaGestionOrdenTrabajo sub;

	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionOrdenTrabajo();
	}

	@AfterAll
	static void tearDown() throws Exception {
	}
	
	/*public OrdenTrabajo(Integer identificador, String descripcion, 
	ArrayList<String> material, ArrayList<Presupuesto> presupuesto,
	Double coste, String responsable, Integer personal, Date fechaInicio, Integer duracion, 
	String estado, Proceso proceso)*/

	/*public Presupuesto(Integer identificador, String empresa, Double presupuesto, Date fechaInicio, Integer duracion,
			ArrayList<String> material, Integer personal)*/
	
	/*public Proceso(Integer identificador, String nombreProceso, String descripcion, 
			Double coste, Double estimado, String estado, String responsable, String servicio, 
			ArrayList<Incidencia> incidencias, ArrayList<OrdenTrabajo> ordenesTrabajo, Date fechaInicio)*/
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0057 - Introducir todos los argumentos correctos")
	void testInicializar_57() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
		
		
		OrdenTrabajo otEsperada = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso);
		
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
					material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(otEsperada, otReal, "Error al inicializar ot");
	}
	
	@Test
	@DisplayName("Caso de prueba Inicializar 0058 - Introducir todos los argumentos nulos")
	void testInicializar_58() {
		//Arrange
		OrdenTrabajo otEsperada = new OrdenTrabajo(null, null, null, null, null, null, null, null, null, null, null);
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.inicializar(null, null, null, null, null, null, null, null, null, null, null);
		} catch (CustomException e) {
			e.printStackTrace();
		}

		assertEquals(otEsperada, otReal, "Error al inicializar ot");	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0059 - Introducir identificador negativo")
	void testInicializar_59() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(-12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");	

	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0060 - Introducir descripción con más de 500 caracteres")
	void testInicializar_60() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro Se cambiarán las farolas de la Avenida Rosalía de "
						+ "Castro Se cambiarán las farolas de la Avenida Rosalía de Castro Se cambiarán las farolas de la Avenida Rosalía de Castro Se cambiarán "
						+ "las farolas de la Avenida Rosalía de CastroSe cambiarán las farolas de la Avenida Rosalía de Castro Se cambiarán las farolas de la "
						+ "Avenida Rosalía de Castro Se cambiarán las farolas de la Avenida Rosalía de Castro Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Descripcion superior a 500 chars", e.getMessage(), "Mensaje de excepcion incorrecto");	

	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0061 - Introducir responsable con caracteres no alfabéticos")
	void testInicializar_61() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe1", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe1", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Responsable no alfabetico", e.getMessage(), "Mensaje de excepcion incorrecto");	

	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0062 - Introducir personal negativo")
	void testInicializar_62() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", -10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Personal negativo", e.getMessage(), "Mensaje de excepcion incorrecto");	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@Tag("Disabled")
	@DisplayName("Caso de prueba Inicializar 0063 - Introducir fecha de inicio con formato incorrecto")
	void testInicializar_63() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", -10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Personal negativo", e.getMessage(), "Mensaje de excepcion incorrecto");	
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0064 - Introducir duración negativa")
	void testInicializar_64() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), -80, "Pendiente de asignación", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		//assertEquals("Duración negativo", e.getMessage(), "Mensaje de excepcion incorrecto");	
	}
	
	/**/
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0065 - Introducir estado 'Pendiente de asignación'")
	void testInicializar_65() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
		
		
		OrdenTrabajo otEsperada = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso);
		
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
					material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), 80, "Pendiente de asignación", proceso);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(otEsperada, otReal, "Error al inicializar ot");
	}
	
	@SuppressWarnings("deprecation")
	@Test
	@DisplayName("Caso de prueba Inicializar 0066 - Introducir estado con cadena no válida")
	void testInicializar_66() {
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		ArrayList<Presupuesto> pres = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(1, "Pepe Electricas", 350.0, new Date(2021,12,31), 2, material, 2);
		pres.add(c1);
		
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), new Date(2021,12,31));
	
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
						material, pres, 1000.0, "Pepe", 10, new Date(2021,12,31), -80, "Esperando", proceso));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
	}
	
	/*CustomException e = assertThrows(CustomException.class,
				() -> sub.inicializar(null, null, null, null, null, null, null, null, null, null, null));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Identificador negativo", e.getMessage(), "Mensaje de excepcion incorrecto");	*/

}
