package SubsistemaGestionProcesos;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

@DisplayName("Pruebas Buscar Proceso")
class TestSubsistemaGestionProcesos_09 {

	static private SubsistemaGestionProcesos sub;
	static private Proceso proceso1;
	static private Proceso proceso2;
	

	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();

		proceso1 = new Proceso();
		proceso1.setIdentificador(0);
		proceso1.setNombre("proceso-1");
		proceso1.setDescripcion("proceso primero");
		proceso1.setCoste(15.0);
		proceso1.setEstimado(0.0);
		proceso1.setEstado("Pendiente");
		proceso1.setResponsable("Paco Meralgo");
		proceso1.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias1 = new ArrayList<Incidencia>();  
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
		incidencias1.add(i1);
		incidencias1.add(i2);
		proceso1.setIncidencias(incidencias1);
		
		Calendar fecha1 = Calendar.getInstance();
		fecha1.set(Calendar.YEAR, 2021);
		fecha1.set(Calendar.MONTH, 7);
		fecha1.set(Calendar.DAY_OF_MONTH, 22);
		proceso1.setFechaInicio(fecha1.getTime());
		
		proceso2 = new Proceso();
		proceso2.setIdentificador(1);
		proceso2.setNombre("proceso-2");
		proceso2.setDescripcion("proceso segundo");
		proceso2.setCoste(15.0);
		proceso2.setEstimado(0.0);
		proceso2.setEstado("Pendiente");
		proceso2.setResponsable("Paco Meralgo");
		proceso2.setServicio("Asfalto");
		
		ArrayList<Incidencia> incidencias2 = new ArrayList<Incidencia>();  
		Incidencia i3 = new Incidencia();
		i3.setIdentificador(2);
		i3.setNombreCiudadano("Ciudadano");
		i3.setDNI("53199271H");
		i3.setTelefono("34608564255");
		i3.setDescripcion("descripcion de prueba");
		i3.setLocalizacion("localizacion de prueba");
		i3.setTipoIncidencia("Otra");
		Incidencia i4 = new Incidencia();
		i4.setIdentificador(3);
		i4.setNombreCiudadano("Ciudadano");
		i4.setDNI("53199271H");
		i4.setTelefono("34608564255");
		i4.setDescripcion("descripcion de prueba");
		i4.setLocalizacion("localizacion de prueba");
		i4.setTipoIncidencia("Otra");
		incidencias2.add(i3);
		incidencias2.add(i4);
		proceso2.setIncidencias(incidencias2);
		
		Calendar fecha2 = Calendar.getInstance();
		fecha2.set(Calendar.YEAR, 2021);
		fecha2.set(Calendar.MONTH, 7);
		fecha2.set(Calendar.DAY_OF_MONTH, 22);
		proceso2.setFechaInicio(fecha2.getTime());
		
		try {
		sub.crear(proceso1);
		sub.crear(proceso2);
		}
		catch(CustomException ex) {
			System.out.println("Error al crear proceso: " + ex);
		}
	}

	@AfterAll
	static void tearDown() throws Exception {
	}
	
	@Test
	@DisplayName("Caso de prueba Buscar proceso 0045 - Introducir Proceso a null")
	void testBuscar_45() {
		ArrayList<Proceso> expected = new ArrayList<Proceso>();
		expected.add(proceso1);
		expected.add(proceso2);
		
		ArrayList<Proceso> obtained = sub.buscar(null);
		assertEquals(expected.size(), obtained.size(),"Introducir Proceso correcto CP-0046: El proceso se ha bsucado incorrectamente");
		assertTrue(obtained.containsAll(expected), "Introducir Proceso correcto CP-0046: El proceso se ha bsucado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Buscar proceso 0046 - Introducir Proceso correcto")
	void testBuscar_46() {
		Proceso p = new Proceso();
		p.setResponsable("Paco Meralgo");
		
		ArrayList<Proceso> expected = new ArrayList<Proceso>();
		expected.add(proceso1);
		expected.add(proceso2);
		
		ArrayList<Proceso> obtained = sub.buscar(p);
		assertEquals(expected.size(), obtained.size(),"Introducir Proceso correcto CP-0046: El proceso se ha bsucado incorrectamente");
		assertTrue(obtained.containsAll(expected), "Introducir Proceso correcto CP-0046: El proceso se ha bsucado incorrectamente");
	}

}
