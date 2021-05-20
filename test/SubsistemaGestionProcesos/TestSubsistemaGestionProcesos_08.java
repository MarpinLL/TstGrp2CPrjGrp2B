package SubsistemaGestionProcesos;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import Exception.CustomException;
import Model.Incidencia;
import Model.Proceso;

@DisplayName("Pruebas Actualizar Proceso")
class TestSubsistemaGestionProcesos_08 {
	static private SubsistemaGestionProcesos sub;
	static private Proceso proceso;

	@BeforeAll
	static void setUp() throws Exception {
		sub = new SubsistemaGestionProcesos();

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
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		proceso.setFechaInicio(fecha.getTime());
		
		try {
		sub.crear(proceso);
		}
		catch(CustomException ex) {
			System.out.println("Error al crear proceso: " + ex);
		}
	}

	@AfterAll
	static void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Caso de prueba Actualizar proceso 0043 - Introducir proceso null")
	void testActualizar_43() {
			
		CustomException ex = assertThrows(CustomException.class, () -> {
		sub.actualizar(null);
		}, "Introducir proceso null CP-0043: El proceso se ha actualizado incorrectamente");
		
		assertEquals(1, ex.codigo, "Introducir proceso null CP-0043: El proceso se ha actualizado incorrectamente");
	}
	
	@Test
	@DisplayName("Caso de prueba Actualizar proceso 0044 - Introducir proceso correcto")
	void testActualizar_44() {
		Proceso p = new Proceso();
		p.setIdentificador(0);
		p.setNombre("proceso-2");
		p.setDescripcion("proceso segundo");
		p.setCoste(15.0);
		p.setEstimado(0.0);
		p.setEstado("Pendiente");
		p.setResponsable("Paco Meralgo");
		p.setServicio("Asfalto");
		
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
		p.setIncidencias(incidencias);
		
		Calendar fecha = Calendar.getInstance();
		fecha.set(Calendar.YEAR, 2021);
		fecha.set(Calendar.MONTH, 7);
		fecha.set(Calendar.DAY_OF_MONTH, 22);
		p.setFechaInicio(fecha.getTime());
		
		Proceso p2 = null;
		try {
			p2 = sub.actualizar(p);
		}
		catch(CustomException ex) {
			System.out.println(ex);
		}
		
		Proceso p3 = p2;
		assertAll("Introducir argumentos correctos CP-0031: El proceso se ha actualizado incorrectamente",
			    () -> assertEquals(p.getIdentificador(), p3.getIdentificador()),
			    () -> assertEquals(p.getNombre(), p3.getNombre()),
			    () -> assertEquals(p.getDescripcion(), p3.getDescripcion()),
			    () -> assertEquals(p.getCoste(), p3.getCoste()),
			    () -> assertEquals(p.getEstimado(), p3.getEstimado()),
			    () -> assertEquals(p.getResponsable(), p3.getResponsable()),
			    () -> assertEquals(p.getEstado(), p3.getEstado()),
			    () -> assertEquals(p.getServicio(), p3.getServicio()),
			    () -> assertEquals(p.getFechaInicio(), p3.getFechaInicio()),
			    () -> assertEquals(p.getIncidencias(), p3.getIncidencias()),
			    () -> assertEquals(p.getOrdenesTrabajo(), p3.getOrdenesTrabajo())
			);
	}

}
