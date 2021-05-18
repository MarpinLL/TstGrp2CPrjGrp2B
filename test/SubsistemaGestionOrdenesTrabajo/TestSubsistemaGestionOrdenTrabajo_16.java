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
	
	
	
}
