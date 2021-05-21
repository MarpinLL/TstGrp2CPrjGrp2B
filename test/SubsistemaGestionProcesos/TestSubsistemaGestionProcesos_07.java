package SubsistemaGestionProcesos;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import Exception.CustomException;
import Model.Incidencia;
import Model.OrdenTrabajo;
import Model.Proceso;

@DisplayName("Pruebas Crear Proceso")
class TestSubsistemaGestionProcesos_07 {
	private static SubsistemaGestionProcesos sub;
	private static Proceso proceso;
	@Mock
	private static SubsistemaGestionProcesos subMock;
	
	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();
		subMock = mock(SubsistemaGestionProcesos.class);
		
		proceso = new Proceso();
		proceso.setIdentificador(0);
		proceso.setNombre("proceso-1");
		proceso.setDescripcion("proceso primero");
		proceso.setCoste(15.0);
		proceso.setEstimado(0.0);
		proceso.setEstado("Pendiente");
		proceso.setResponsable("Paco Meralgo");
		proceso.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		proceso.setIncidencias(incidencias);
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		proceso.setOrdenesTrabajo(ordenes);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		proceso.setFechaInicio(fecha.getTime());
	}

	@AfterAll
	static void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Caso de prueba Crear proceso 0040 - Introducir proceso null")
	void testCrear_40() {
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.crear(null);
		}, "Introducir proceso null CP-0040: El proceso se ha creado incorrectamente");
		assertEquals(ex.codigo, 1, "Introducir proceso null CP-0040: El proceso se ha creado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Crear proceso 0041 - Introducir proceso correcto")
	void testCrear_41() {
		Proceso p = null;
		proceso.setIdentificador(1);
		try {
			p = sub.crear(proceso);
		} catch (CustomException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		assertSame("Introducir proceso correcto CP-0041: El proceso se ha creado incorrectamente", proceso, p);
	}
	
	@Test
	@DisplayName("Caso de prueba Crear proceso 0042 - Introducir identificador repetido")
	void testCrear_42() {
		
		proceso.setIdentificador(2);
		
		Proceso mocked = new Proceso();
		mocked.setIdentificador(2);
		mocked.setNombre("proceso-1");
		mocked.setDescripcion("proceso primero");
		mocked.setCoste(15.0);
		mocked.setEstimado(0.0);
		mocked.setEstado("Pendiente");
		mocked.setResponsable("Paco Meralgo");
		mocked.setServicio("Asfalto");

		ArrayList<Incidencia> incidencias = new ArrayList<Incidencia>();  
		Incidencia i1 = new Incidencia();
		i1.setIdentificador(0);
		i1.setNombreCiudadano("Ciudadano");
		i1.setDNI("53199271H");
		i1.setTelefono("34608564255");
		i1.setDescripcion("descripcion de prueba");
		i1.setLocalizacion("localizacion de prueba");
		i1.setTipoIncidencia("Otra");
		Incidencia i2 = new Incidencia();
		i2.setIdentificador(1);
		i2.setNombreCiudadano("Ciudadano");
		i2.setDNI("53199271H");
		i2.setTelefono("34608564255");
		i2.setDescripcion("descripcion de prueba");
		i2.setLocalizacion("localizacion de prueba");
		i2.setTipoIncidencia("Otra");
		incidencias.add(i1);
		incidencias.add(i2);
		mocked.setIncidencias(incidencias);
		
		ArrayList<OrdenTrabajo> ordenes = new ArrayList<OrdenTrabajo>();
		OrdenTrabajo o1 = new OrdenTrabajo();
		OrdenTrabajo o2 = new OrdenTrabajo();
		ordenes.add(o1);
		ordenes.add(o2);
		mocked.setOrdenesTrabajo(ordenes);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		mocked.setFechaInicio(fecha.getTime());
		Proceso prueba = null;
		
		try {
			when(subMock.inicializar(2, "proceso-1", "proceso primero", 15.0, 0.0, "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime())).thenReturn(mocked);
			prueba = subMock.inicializar(2, "proceso-1", "proceso primero", 15.0, 0.0, "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime());
			sub.crear(subMock.inicializar(2, "proceso-1", "proceso primero", 15.0, 0.0, "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime()));
		} 
		catch (CustomException ex) {
			System.out.println(ex);
		}
		
		CustomException ex = assertThrows(CustomException.class, () -> {
			sub.crear(subMock.inicializar(2, "proceso-1", "proceso primero", 15.0, 0.0, "Pendiente", "Paco Meralgo", "Asfalto", incidencias, ordenes, fecha.getTime()));
		}, "Introducir proceso null CP-0042: El proceso se ha creado incorrectamente");
		assertEquals(2, ex.codigo, "Introducir identificador repetido CP-0042: El proceso se ha creado incorrectamente");
	}
	

}
