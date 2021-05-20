package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.OrdenTrabajo;
import Model.Proceso;
import SubsistemaGestionOrdenesTrabajo.SubsistemaGestionOrdenTrabajo;

@DisplayName("Pruebas Asignar OTs a Proceso")
class TestSubsistemaGestionProcesos_10 {
	static private SubsistemaGestionProcesos sub;
	static private OrdenTrabajo o1;
	static private OrdenTrabajo o2;
	static private OrdenTrabajo o3;
	static private Proceso original;
	static private Calendar fecha;
	
	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();
		SubsistemaGestionOrdenTrabajo ots = new SubsistemaGestionOrdenTrabajo();
		o1 = ots.inicializar(12345,"Necesario arreglar un par de farolas situadas en la calle Alfonso XIII",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		o2 = ots.inicializar(12346,"Necesario arreglar un par de farolas situadas en la calle Horreo",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		o3 = ots.inicializar(12347,"Necesario arreglar un par de farolas situadas en la calle Rosa",null,null,null,null,null, null, null, "Pendiente de asignación", null);
		
		fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2020);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		original = sub.crear(new Proceso(1,"proceso-1","proceso primero", 15.00 , 0.0, "Pendiente", "Paco Meralgo","Asfalto", null, null, fecha.getTime()));
	}

	@AfterAll
	static void tearDown() throws Exception {
	
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarOrdenTrabajo 0047 - Introducir Argumentos correctos")
	void testAsignarOrdenTrabajo_47() {
		Proceso esperado = new Proceso();
		esperado.setIdentificador(1);
		esperado.setNombre("proceso-1");
		esperado.setDescripcion("proceso primero");
		esperado.setCoste(15.0);
		esperado.setEstimado(0.0);
		esperado.setEstado("Pendiente");
		esperado.setResponsable("Paco Meralgo");
		esperado.setServicio("Asfalto");

		esperado.setIncidencias(null);
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		ordenes.add(o1);
		ordenes.add(o2);
		ordenes.add(o3);
		esperado.setOrdenesTrabajo(ordenes);
		
		esperado.setFechaInicio(fecha.getTime());
		
		Proceso x = null;
		try {
			x = sub.asignarOrdenTrabajo(original, ordenes);
		} catch (CustomException ex) {
			System.out.println(ex);
		}
		
		Proceso obtenido = x;
		assertAll("Introducir argumentos correctos CP-0047: Las ordenes se han asignado incorrectamente",
			    () -> assertEquals(esperado.getIdentificador(), obtenido.getIdentificador()),
			    () -> assertEquals(esperado.getNombre(), obtenido.getNombre()),
			    () -> assertEquals(esperado.getDescripcion(), obtenido.getDescripcion()),
			    () -> assertEquals(esperado.getCoste(), obtenido.getCoste()),
			    () -> assertEquals(esperado.getEstimado(), obtenido.getEstimado()),
			    () -> assertEquals(esperado.getResponsable(), obtenido.getResponsable()),
			    () -> assertEquals(esperado.getEstado(), obtenido.getEstado()),
			    () -> assertEquals(esperado.getServicio(), obtenido.getServicio()),
			    () -> assertEquals(esperado.getIncidencias(), obtenido.getIncidencias()),
			    () -> assertEquals(esperado.getFechaInicio(), obtenido.getFechaInicio()),
			    () -> assertEquals(esperado.getOrdenesTrabajo().size(), obtenido.getOrdenesTrabajo().size()),
				() -> assertTrue(obtenido.getOrdenesTrabajo().containsAll(esperado.getOrdenesTrabajo()))
			);
		
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarOrdenTrabajo 0048 - Introducir Proceso null")
	void testAsignarOrdenTrabajo_48() {
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		ordenes.add(o1);
		ordenes.add(o2);
		ordenes.add(o3);
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarOrdenTrabajo(null, ordenes);
		}, "Introducir proceso null CP-0048: Las ordenes se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir proceso null CP-0048: Las ordenes se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarOrdenTrabajo 0049 - Introducir Ordenes vac�as")
	void testAsignarOrdenTrabajo_49() {
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarOrdenTrabajo(original, ordenes);
		}, "Introducir proceso null CP-0049: Las ordenes se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir proceso null CP-0049: Las ordenes se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarOrdenTrabajo 0050 - Introducir Ordenes null")
	void testAsignarOrdenTrabajo_50() {
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarOrdenTrabajo(original, null);
		}, "Introducir proceso null CP-0050: Las ordenes se han asignado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir proceso null CP-0050: Las ordenes se han asignado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba AsignarOrdenTrabajo 0051 - Introducir Ordenes repetidas")
	void testAsignarOrdenTrabajo_51() {
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		ordenes.add(o1);
		ordenes.add(o2);
		ordenes.add(o3);
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.asignarOrdenTrabajo(original, ordenes);
			sub.asignarOrdenTrabajo(original, ordenes);
		}, "Introducir proceso null CP-0051: Las ordenes se han asignado incorrectamente");
		
		assertEquals(2, ex.codigo, "Introducir proceso null CP-0051: Las ordenes se han asignado incorrectamente");
	}

}
