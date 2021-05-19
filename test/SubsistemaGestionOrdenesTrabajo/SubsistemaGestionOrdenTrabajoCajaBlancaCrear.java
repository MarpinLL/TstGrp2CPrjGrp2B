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

@DisplayName("Pruebas de caja blanca sobre Crear Orden de Trabajo")
class SubsistemaGestionOrdenTrabajoCajaBlancaCrear {
	
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
	@DisplayName("Camino 1")
	void testCajaBlancaBuscar_1() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		Proceso p = new Proceso();
		p.setIdentificador(1);
		
		OrdenTrabajo esperado = new OrdenTrabajo(231456, "Reparar farola", 
				null, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				null, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo real = null;
		try {
			real = sub.crear(filtro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		assertEquals(esperado, real,"Resultado incorrecto");
	}
	
	@Test
	@DisplayName("Camino 2")
	void testCajaBlancaBuscar_2() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);
		
		OrdenTrabajo esperado = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo real = null;
		try {
			real = sub.crear(filtro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		assertEquals(esperado, real,"Resultado incorrecto");
	}
	
	@Test
	@DisplayName("Camino 3")
	void testCajaBlancaBuscar_3() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);
		
		OrdenTrabajo esperado = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		OrdenTrabajo real = null;
		try {
			real = sub.crear(filtro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		assertEquals(esperado, real,"Resultado incorrecto");
	}
	
	@Test
	@DisplayName("Camino 4")
	void testCajaBlancaBuscar_4() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);
		
		OrdenTrabajo filtro = new OrdenTrabajo(12345, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(2, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Id ya registrado", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	@Test
	@DisplayName("Camino 5")
	void testCajaBlancaBuscar_5() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Asignada", p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("El estado no puede ser distinto de Pendiente deasignación", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	//TODO ESTO ESTÁ MAL AUNQUE SE EJECUTE BIEN, LA COBERTURA NO ES LA QUE QUEREMOS
	@Test
	@DisplayName("Camino 6")
	void testCajaBlancaBuscar_6() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(12345, "Reparar farola",
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación", p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(2, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Id ya registrado", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	@Test
	@DisplayName("Camino 7")
	void testCajaBlancaBuscar_7() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);
		
		Presupuesto ps = new Presupuesto(1, "Empresa", 20.0, fechaSis.getTime(), 2, mat, 5);

		ArrayList<Presupuesto> pres = new ArrayList<>();
		pres.add(ps);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, pres, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación", p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("No se pueden incorporar presupuestos en la creación", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	@Test
	@DisplayName("Camino 8")
	void testCajaBlancaBuscar_8() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Asignado", p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("El estado no puede ser distinto de Pendiente deasignación", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	@Test
	@DisplayName("Camino 9")
	void testCajaBlancaBuscar_9() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, null, p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Faltan campos Obligatorios {id, descripcion ,estado}", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Camino 10")
	void testCajaBlancaBuscar_10() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(231456, null, 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, null, p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Faltan campos Obligatorios {id, descripcion ,estado}", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Camino 11")
	void testCajaBlancaBuscar_11() {
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 4);
		fechaSis.set(Calendar.DAY_OF_MONTH, 21);
		
		ArrayList<String> mat = new ArrayList<>();
		mat.add("Metal");
		mat.add("Bombillas");	
		
		Proceso p = new Proceso();
		p.setIdentificador(1);

		OrdenTrabajo filtro = new OrdenTrabajo(null, null, 
				mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, null, p);

		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Faltan campos Obligatorios {id, descripcion ,estado}", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
	@Test
	@DisplayName("Camino 12")
	void testCajaBlancaBuscar_12() {

		OrdenTrabajo filtro = new OrdenTrabajo(null, null, 
				null, null, null, null, null, null, null, null, null);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OT con todos los campos nulos", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Camino 13")
	void testCajaBlancaBuscar_13() {

		OrdenTrabajo filtro = null;

		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.crear(filtro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OT nula", e.getMessage(), "Mensaje de excepcion incorrecto");

	
	}
}
