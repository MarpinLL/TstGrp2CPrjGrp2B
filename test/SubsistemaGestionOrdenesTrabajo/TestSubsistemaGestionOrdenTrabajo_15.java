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

@DisplayName("Pruebas sobre Asignar Empresa")
class TestSubsistemaGestionOrdenTrabajo_15 {
	
	private static SubsistemaGestionOrdenTrabajo sub;
	private Presupuesto c1 = new Presupuesto();
	private Presupuesto c2 = new Presupuesto();
	

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
		
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		//se inicializan solo dos para el caso de prueba en el que no hay 3 inicializadas
		c1 = sub.inicializar(123, "Pepe Electricas", 350.0, fechaSis.getTime(), 4, material, 2, 12345);
		c2 = sub.inicializar(234, "Paco Electricas", 450.0, fechaSis.getTime(), 2, material, 4, 12345);
		
	}

	@AfterAll
	static void tearDown() throws Exception {
		sub = null;
	}
	
	@Test
	@DisplayName("Caso de prueba Asignar 0085 - Introducir OT y Presupuesto con campos obligatorios")
	void testAsignarEmpresa_85() {
		
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);

		
		//creamos un tercer presupuesto para esa orden de trabajo, para que se cumpla la condición
		//este presupuesto es el que se pone como argumento del método asignarEmpresa
		Presupuesto presArgumento = null;
		try {
			presArgumento = sub.inicializar(345, "Pepa Electricas", 550.0, fechaSis.getTime(), 2, material, 4, 12345);
		} catch (CustomException e1) {
			//todo metodo fail
			e1.printStackTrace();
		}
		
		
		ArrayList<Presupuesto> presupuestos = new ArrayList<>();
		//c1 y c2 son los ya inicializados
		presupuestos.add(c1); presupuestos.add(c2); presupuestos.add(presArgumento);
		
		OrdenTrabajo otEsperada = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				material, presupuestos, 550.0, "Pepa Electricas", 4, fechaSis.getTime(), 2, "Asignada", null);
		
		OrdenTrabajo otArgumento = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.asignarEmpresa(otArgumento, presArgumento);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//TODO
		//assertDoesNotThrow(() -> sub.asignarEmpresa(otArgumento, presArgumento), "Lanza excepción CustomException");
		
		//Assert
		//assertEquals(otEsperada, otReal, "Error al asignar empresa");
		
		final OrdenTrabajo realFinal = otReal;
		
		//TODO
		
		assertAll(
				() -> {assertEquals(otEsperada.getIdentificador(), realFinal.getIdentificador(), "Identificador distinto");},
				() -> {assertEquals(otEsperada.getDescripcion(), realFinal.getDescripcion(), "Descripción distinta");}//,
				/*() -> {assertEquals(otEsperada, realFinal.getDescripcion(), "Descripción distinta");}
				() -> {assertEquals(otEsperada.getDescripcion(), realFinal.getDescripcion(), "Descripción distinta");}*/
				);
	}
	
	@Test
	@DisplayName("Caso de prueba Asignar 0086 - Introducir argumentos nulos")
	void testAsignarEmpresa_86() {
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.asignarEmpresa(null, null), "No salta la excepcion");

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OrdenTrabajo nula", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Asignar 0087 - Introducir presupuesto que no estaba almacenado")
	void testAsignarEmpresa_87() {
		
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//inicializamos un tercer presupuesto, para que se cumpla la condición
		try {
			sub.inicializar(345, "Pepa Electricas", 550.0, fechaSis.getTime(), 2, material, 4, 12345);
		} catch (CustomException e1) {
			e1.printStackTrace();
		}
		
		//creamos un cuarto presupuesto que pasaremos como argumento, pero no lo asignamos a la ot
		Presupuesto presArgumento = new Presupuesto(456, "Pepa Electricas", 650.0, fechaSis.getTime(), 8, material, 4);
		
		OrdenTrabajo otArgumento = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.asignarEmpresa(otArgumento, presArgumento), "No salta la excepcion");

		assertEquals(4, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Presupuesto no registrado en la OT", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Asignar 0088 - Introducir ot y presupuesto sin que haya 3 almacenados")
	void testAsignarEmpresa_88() {
		
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//solo hay dos presupuestos asignados a la ot
		//creamos un cuarto presupuesto que pasaremos como argumento, pero no lo asignamos a la ot
		Presupuesto presArgumento = new Presupuesto(456, "Pepa Electricas", 650.0, fechaSis.getTime(), 8, material, 4);
		
		OrdenTrabajo otArgumento = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.asignarEmpresa(otArgumento, presArgumento), "No salta la excepcion");

		assertEquals(3, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("La OT no presenta el mínimo de 3 presupuestos para la selección", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Asignar 0089 - Introducir ot que no exista en el sistema")
	void testAsignarEmpresa_89() {
		
		//Arrange
		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		//creamos un tercer presupuesto para esa orden de trabajo, para que se cumpla la condición
		//este presupuesto es el que se pone como argumento del método asignarEmpresa
		Presupuesto presArgumento = new Presupuesto(345, "Pepa Electricas", 550.0, fechaSis.getTime(), 2, material, 4);
		try {
			sub.inicializar(345, "Pepa Electricas", 550.0, fechaSis.getTime(), 2, material, 4, 12345);
		} catch (CustomException e1) {
			e1.printStackTrace();
		}
		
		OrdenTrabajo otArgumento = new OrdenTrabajo(123458, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.asignarEmpresa(otArgumento, presArgumento), "No salta la excepcion");

		assertEquals(4, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OT no registrada", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
}
