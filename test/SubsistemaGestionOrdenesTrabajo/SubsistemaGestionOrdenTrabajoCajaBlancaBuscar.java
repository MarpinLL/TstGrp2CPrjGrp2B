package SubsistemaGestionOrdenesTrabajo;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.OrdenTrabajo;
import Model.Presupuesto;
import Model.Proceso;

class SubsistemaGestionOrdenTrabajoCajaBlancaBuscar {

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
		o.setEstado("Pendiente de asignaci√≥n");
		Proceso p = new Proceso();
		p.setIdentificador(1);
		o.setProceso(p);
		System.out.println(o.getCoste());
		so.crear(o);
		System.out.println(o.getCoste());
		Presupuesto ps = so.inicializar(1, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
		Presupuesto ps1 = so.inicializar(2, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
		Presupuesto ps2 = so.inicializar(3, "Empresa", 20.0, fechaSis.getTime(), 2, m, 5, 12345);
		o1 = o;
		System.out.println(o.getCoste());
		
		
		OrdenTrabajo ot = new OrdenTrabajo();
		ot.setIdentificador(123456);		
		ot.setDescripcion("Se cambiar·n las farolas de la Avenida RosalÌa de Castro");
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
		ot.setEstado("Pendiente de asignaci√≥n");
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
	void testBuscar_5() {
				
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
		filtro.setEstado("Terminado");
		filtro.setProceso(null);
		
		
		ArrayList<OrdenTrabajo> real = so.buscar(filtro);
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	

	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}

	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(real.size(),esperado.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
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
		
		assertEquals(real.size(),esperado.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
	
	@Test
	void testBuscar_17() {
				
		ArrayList<OrdenTrabajo> esperado = new ArrayList<>();
		esperado.add(o1);
		esperado.add(o2);
		
		
		ArrayList<OrdenTrabajo> real = so.buscar(null);
		
		assertEquals(esperado.size(),real.size(),"Listas resultadas incorrectas");
		assertTrue(real.containsAll(esperado),"Listas resultadas incorrectas");
		
	}
}


