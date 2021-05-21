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
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

@DisplayName("Pruebas de caja blanca sobre Crear Orden de Trabajo y Buscar Orden de Trabajo")
class SubsistemaGestionOrdenTrabajo_CajaBlanca {
	
	@Nested
	@DisplayName("Prueba de caja blanca sobre Crear -Revisor: María López")
	static class SubsistemaGestionOrdenTrabajoCajaBlancaCrear {
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
	
			OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
					null, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación", p);
	
			OrdenTrabajo real = null;
			try {
				real = sub.crear(filtro);
			} catch (CustomException e) {
				e.printStackTrace();
			}
			assertSame(filtro, real,"Resultado incorrecto");
				
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
	
			OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
					mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación", p);
	
			OrdenTrabajo real = null;
			try {
				real = sub.crear(filtro);
			} catch (CustomException e) {
				e.printStackTrace();
			}
			assertSame(filtro, real,"Resultado incorrecto");
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
	
			OrdenTrabajo filtro = new OrdenTrabajo(231456, "Reparar farola", 
					mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Pendiente de asignación",p);
	
			OrdenTrabajo real = null;
			try {
				real = sub.crear(filtro);
			} catch (CustomException e) {
				e.printStackTrace();
			}
			assertSame(filtro, real,"Resultado incorrecto");
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
			assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
			assertEquals("El estado no puede ser distinto de Pendiente deasignación", e.getMessage(), "Mensaje de excepcion incorrecto");
		
		}
		
		//AUNQUE SE EJECUTE BIEN, LA COBERTURA NO ES LA QUE QUEREMOS
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					mat, null, 5.0, "Paco meralgo", 5, fechaSis.getTime(), 2, "Asignada", p);
	
			CustomException e = assertThrows(CustomException.class,
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
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
					() -> sub.crear(filtro), "No salta la excepcion");
	
			assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
			assertEquals("OT con todos los campos nulos", e.getMessage(), "Mensaje de excepcion incorrecto");
		}
		
		@Test
		@DisplayName("Camino 13")
		void testCajaBlancaBuscar_13() {
	
			OrdenTrabajo filtro = null;
	
			//Assert
			CustomException e = assertThrows(CustomException.class,
					() -> sub.crear(filtro), "No salta la excepcion");
	
			assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
			assertEquals("OT nula", e.getMessage(), "Mensaje de excepcion incorrecto");
	
		
		}
	}
	
	@Nested
	@DisplayName("Prueba de caja blanca sobre Buscar-Revisor:Abel Mart�nez")
	static class SubsistemaGestionOrdenTrabajoCajaBlancaBuscar {

		private static InterfaceSubsistemaGestionOrdenTrabajo so;
		private static OrdenTrabajo o1;
		private static OrdenTrabajo o2;
		
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
			
			so = new SubsistemaGestionOrdenTrabajo();
			
			OrdenTrabajo o = new OrdenTrabajo();
			o.setIdentificador(12345);		
			o.setDescripcion("Reparar luz");
			ArrayList<String> m = new ArrayList<>();
			m.add("Metal");
			m.add("Bombillas");	
			o.setMaterial(m);
			
			o.setCoste(5.0);
			o.setResponsable("Paco Meralgo");
			o.setPersonal(5);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			o.setFechaInicio(fechaSis.getTime());
			o.setDuracion(2);
			o.setEstado("Pendiente de asignación");
			Proceso p = new Proceso();
			p.setIdentificador(1);
			o.setProceso(p);
			so.crear(o);
			so.inicializar(1, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
			so.inicializar(2, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
			so.inicializar(3, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
			o1 = o;
			System.out.println(o.getCoste());
			
			
			OrdenTrabajo ot = new OrdenTrabajo();
			ot.setIdentificador(123456);		
			ot.setDescripcion("Se cambiar�n las farolas de la Avenida Rosal�a de Castro");
			m = new ArrayList<>();
			m.add("Metal");
			m.add("Bombillas");	
			ot.setMaterial(m);
			
			ot.setCoste(20.0);
			ot.setResponsable("Pepe");
			ot.setPersonal(10);
			 fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 11);
			fechaSis.set(Calendar.DAY_OF_MONTH, 31);
			ot.setFechaInicio(fechaSis.getTime());
			ot.setDuracion(80);
			ot.setEstado("Pendiente de asignación");
			p = new Proceso();
			ot.setProceso(p);
			so.crear(ot);
			o2 = ot;
			
		}
		@AfterAll
		static void tearDownAfterClass() throws Exception {
		}

		@BeforeEach
		void setUp() throws Exception {
			
		
		}
		

		@AfterEach
		void tearDown() throws Exception {
		}

		@Test
		@DisplayName("Camino 1")
		void testBuscar_1() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			esperado.add(o1);
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(null);		
			filtro.setDescripcion(o1.getDescripcion());
			filtro.setMaterial(o1.getMaterial());
			filtro.setPresupuesto(o1.getPresupuesto());
			filtro.setCoste(o1.getCoste());
			filtro.setResponsable(o1.getResponsable());
			filtro.setPersonal(o1.getPersonal());
			filtro.setFechaInicio(o1.getFechaInicio());
			filtro.setDuracion(o1.getDuracion());
			filtro.setEstado(o1.getEstado());
			filtro.setProceso(o1.getProceso());
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
				);
			
		}
		
		@Test
		@DisplayName("Camino 2")
		void testBuscar_2() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(null);		
			filtro.setDescripcion("Reparar farola");
			filtro.setMaterial(o1.getMaterial());
			filtro.setPresupuesto(o1.getPresupuesto());
			filtro.setCoste(o1.getCoste());
			filtro.setResponsable(o1.getResponsable());
			filtro.setPersonal(o1.getPersonal());
			filtro.setFechaInicio(o1.getFechaInicio());
			filtro.setDuracion(o1.getDuracion());
			filtro.setEstado("Terminado");
			filtro.setProceso(o1.getProceso());
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
				);
			
		}
		
		@Test
		@DisplayName("Camino 3")
		void testBuscar_3() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			esperado.add(o1);
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(o1.getIdentificador());		
			filtro.setDescripcion(o1.getDescripcion());
			filtro.setMaterial(o1.getMaterial());
			filtro.setPresupuesto(o1.getPresupuesto());
			filtro.setCoste(o1.getCoste());
			filtro.setResponsable(o1.getResponsable());
			filtro.setPersonal(o1.getPersonal());
			filtro.setFechaInicio(o1.getFechaInicio());
			filtro.setDuracion(o1.getDuracion());
			filtro.setEstado(o1.getEstado());
			filtro.setProceso(o1.getProceso());
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
				);
		}
		
		@Test
		@DisplayName("Camino 4")
		void testBuscar_4() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12);		
			filtro.setDescripcion(o1.getDescripcion());
			filtro.setMaterial(o1.getMaterial());
			filtro.setPresupuesto(o1.getPresupuesto());
			filtro.setCoste(o1.getCoste());
			filtro.setResponsable(o1.getResponsable());
			filtro.setPersonal(o1.getPersonal());
			filtro.setFechaInicio(o1.getFechaInicio());
			filtro.setDuracion(o1.getDuracion());
			filtro.setEstado("Terminado");
			filtro.setProceso(o1.getProceso());
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
				);
			
			
		}
		
		@Test
		@DisplayName("Camino 5")
		void testBuscar_5() {
					
			ArrayList<OrdenTrabajo> esperado= new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable("Paco meralgo");
			filtro.setPersonal(5);
			filtro.setFechaInicio(fechaSis.getTime());
			filtro.setDuracion(5);
			filtro.setEstado("Terminado");
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
		}
		
		@Test
		@DisplayName("Camino 6")
		void testBuscar_6() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable("Paco meralgo");
			filtro.setPersonal(5);
			filtro.setFechaInicio(fechaSis.getTime());
			filtro.setDuracion(5);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
		}
		

		@Test
		@DisplayName("Camino 7")
		void testBuscar_7() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 9);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable("Paco meralgo");
			filtro.setPersonal(5);
			filtro.setFechaInicio(fechaSis.getTime());
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}

		@Test
		@DisplayName("Camino 8")
		void testBuscar_8() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable("Paco meralgo");
			filtro.setPersonal(5);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 9")
		void testBuscar_9() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable("Paco meralgo");
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 10")
		void testBuscar_10() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(5.0);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 11")
		void testBuscar_11() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			Calendar fechaSis = Calendar.getInstance();
			fechaSis.set(Calendar.YEAR, 2021);
			fechaSis.set(Calendar.MONTH, 6);
			fechaSis.set(Calendar.DAY_OF_MONTH, 21);
			Presupuesto ps = null;
			try {
				ps = so.inicializar(451, "Empresa", 2000.0, fechaSis.getTime(), 2, m, 5, null);
			} catch (CustomException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ArrayList<Presupuesto> psp = new ArrayList<>();
			psp.add(ps);
			filtro.setPresupuesto(psp);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 12")
		void testBuscar_12() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			ArrayList<String> m = new ArrayList<>();
			m.add("Madera");
			filtro.setMaterial(m);
			filtro.setPresupuesto(null);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 13")
		void testBuscar_13() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion("Reparar farola");
			filtro.setMaterial(null);
			filtro.setPresupuesto(null);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 14")
		void testBuscar_14() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			esperado.add(o1);
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12345);		
			filtro.setDescripcion(null);
			filtro.setMaterial(null);
			filtro.setPresupuesto(null);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 15")
		void testBuscar_15() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(12);		
			filtro.setDescripcion(null);
			filtro.setMaterial(null);
			filtro.setPresupuesto(null);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
		}
		
		@Test
		@DisplayName("Camino 16")
		void testBuscar_16() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			esperado.add(o1);
			esperado.add(o2);
			
			OrdenTrabajo filtro = new OrdenTrabajo();
			filtro.setIdentificador(null);		
			filtro.setDescripcion(null);
			filtro.setMaterial(null);
			filtro.setPresupuesto(null);
			filtro.setCoste(null);
			filtro.setResponsable(null);
			filtro.setPersonal(null);
			filtro.setFechaInicio(null);
			filtro.setDuracion(null);
			filtro.setEstado(null);
			filtro.setProceso(null);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(filtro);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
		
		@Test
		@DisplayName("Camino 17")
		void testBuscar_17() {
					
			ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
			esperado.add(o1);
			esperado.add(o2);
			
			
			ArrayList<OrdenTrabajo> real = so.buscar(null);
			
			assertAll("Listas de OTs incorrectas",
				    () -> assertEquals(esperado.size(),real.size()),
				    () -> assertTrue(real.containsAll(esperado))
			);
			
		}
	}
}
