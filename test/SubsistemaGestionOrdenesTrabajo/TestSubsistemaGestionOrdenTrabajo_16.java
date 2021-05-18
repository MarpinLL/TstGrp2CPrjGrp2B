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

@DisplayName("Pruebas sobre Gestionar Recursos")
class TestSubsistemaGestionOrdenTrabajo_16 {
	
	private static SubsistemaGestionOrdenTrabajo sub;

	@BeforeEach
	void setUp() throws Exception {
		sub = new SubsistemaGestionOrdenTrabajo();
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 8);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		/*Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());*/
		
		OrdenTrabajo i = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);

		sub.crear(i);

		ArrayList<String> material = new ArrayList<>();
		material.add("Metal");
		material.add("Bombillas");
		
		//se inicializan tres presupuestos, y se asigna uno de ellos
		Presupuesto c1 = sub.inicializar(123, "Pepe Electricas", 350.0, fechaSis.getTime(), 4, material, 2, 12345);
		material.add("Piedra");
		Presupuesto c2 = sub.inicializar(234, "Paco Electricas", 450.0, fechaSis.getTime(), 2, material, 4, 12345);
		Presupuesto c3 = sub.inicializar(345, "Pepa S. L.", 550.0, fechaSis.getTime(), 2, material, 2, 12345);
		
		sub.asignarEmpresa(i, c3);
		
	}

	@AfterAll
	static void tearDown() throws Exception {
		sub = null;
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
	
	@Test
	@DisplayName("Caso de prueba Gestionar Recursos 0090 - Introducir ot que no exista en el sistema")
	void testInicializar_90() {
		
		//Arrange

		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
		OrdenTrabajo otArgumento = new OrdenTrabajo(123458, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				null, null, null, null, null, null, null, "Pendiente de asignación", null);
		
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.gestionarRecursos(otArgumento));

		assertEquals(4, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("OT no registrada", e.getMessage(), "Mensaje de excepcion incorrecto");
	}
	
	@Test
	@DisplayName("Caso de prueba Gestionar Recursos 0091 - Introducir ot con campos modificados")
	void testInicializar_91() {
		
		//Arrange
		ArrayList<String> materialAntiguo = new ArrayList<>();
		materialAntiguo.add("Metal");
		materialAntiguo.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
			//proceso
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
			//presupuestos
		ArrayList<Presupuesto> presup = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(123, "Pepe Electricas", 350.0, fechaSis.getTime(), 4, materialAntiguo, 2);
		materialAntiguo.add("Piedra");
		Presupuesto c2 = new Presupuesto(234, "Paco Electricas", 450.0, fechaSis.getTime(), 2, materialAntiguo, 4);
		Presupuesto c3 = new Presupuesto(345, "Pepa S. L.", 550.0, fechaSis.getTime(), 2, materialAntiguo, 2);
		presup.add(c1);presup.add(c2);presup.add(c3);
		
		ArrayList<String> materialModificado = new ArrayList<>();
		materialModificado.add("Metal");
		
		OrdenTrabajo otEsperada = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				materialModificado, presup, 550.0, "Pepa S. L.", 2, fechaSis.getTime(), 2, "En curso", null);
				
		OrdenTrabajo otParametro = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				materialModificado, presup, 550.0, "Pepa S. L.", 2, fechaSis.getTime(), 2, "En curso", null);
		
		//Act
		OrdenTrabajo otReal = null;
		try {
			otReal = sub.gestionarRecursos(otParametro);
		} catch (CustomException e) {
			e.printStackTrace();
		}
		
		//Assert
		assertEquals(otEsperada, otReal, "Error al inicializar ot");
	}
	
	@Test
	@DisplayName("Caso de prueba Gestionar Recursos 0092 - Introducir ot sin campos modificados")
	void testInicializar_92() {
		
		//Arrange
		ArrayList<String> materialAntiguo = new ArrayList<>();
		materialAntiguo.add("Metal");
		materialAntiguo.add("Bombillas");
		
		Calendar fechaSis = Calendar.getInstance();
		fechaSis.set(Calendar.YEAR, 2021);
		fechaSis.set(Calendar.MONTH, 9);
		fechaSis.set(Calendar.DAY_OF_MONTH, 22);
		
			//proceso
		Proceso proceso = new Proceso(12, "Proceso 1", "Descripcion de proceso", 420.0, 230.5, 
				"Pendiente de asignación", "Pepe", "Electricidad", new ArrayList<>(), 
				new ArrayList<>(), fechaSis.getTime());
		
			//presupuestos
		ArrayList<Presupuesto> presup = new ArrayList<>();
		Presupuesto c1 = new Presupuesto(123, "Pepe Electricas", 350.0, fechaSis.getTime(), 4, materialAntiguo, 2);
		materialAntiguo.add("Piedra");
		Presupuesto c2 = new Presupuesto(234, "Paco Electricas", 450.0, fechaSis.getTime(), 2, materialAntiguo, 4);
		Presupuesto c3 = new Presupuesto(345, "Pepa S. L.", 550.0, fechaSis.getTime(), 2, materialAntiguo, 2);
		presup.add(c1);presup.add(c2);presup.add(c3);
		
				
		OrdenTrabajo otParametro = new OrdenTrabajo(12345, "Se cambiarán las farolas de la Avenida Rosalía de Castro",
				materialAntiguo, presup, 550.0, "Pepa S. L.", 2, fechaSis.getTime(), 2, "Asignada", null);
		
		//Act
		//Assert
		CustomException e = assertThrows(CustomException.class,
				() -> sub.gestionarRecursos(otParametro));

		assertEquals(1, e.codigo, "Codigo de excepcion incorrecto");
		assertEquals("Estado no valido", e.getMessage(), "Mensaje de excepcion incorrecto");
	
	}
	
}
