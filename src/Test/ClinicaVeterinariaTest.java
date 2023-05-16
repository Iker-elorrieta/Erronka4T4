package Test;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Modelo.ClinicaVeterinaria;
import ModeloPerfil.Empleado;

class ClinicaVeterinariaTest {

	String ubicacion = "Bilbao";
	int codVeterinaria = 3126354;
	int codVeterinaria0 = 0;
	ArrayList<Empleado> empleados = new ArrayList<Empleado>();
	
	ClinicaVeterinaria clinicaVeterinariaTest = new ClinicaVeterinaria(ubicacion, codVeterinaria, empleados);
	ClinicaVeterinaria clinicaVeterinariaTest1 = new ClinicaVeterinaria(ubicacion, codVeterinaria, empleados);
	ClinicaVeterinaria clinicaVeterinariaTest2 = null;
	ClinicaVeterinaria clinicaVeterinariaTest3 = new ClinicaVeterinaria(ubicacion, codVeterinaria0, empleados);
	
	@Test
	void test() {
		
		ClinicaVeterinaria clinicaVeterinariaTestVacio = new ClinicaVeterinaria();
		
		clinicaVeterinariaTestVacio.setEmpleados(empleados);
		clinicaVeterinariaTestVacio.setUbicacion(ubicacion);
		clinicaVeterinariaTestVacio.setCodVeterinaria(codVeterinaria);
		
		clinicaVeterinariaTest.setEmpleados(empleados);
		clinicaVeterinariaTest.setUbicacion(ubicacion);
		clinicaVeterinariaTest.setCodVeterinaria(codVeterinaria);

		assertEquals(clinicaVeterinariaTest.getUbicacion(), ubicacion);
		assertEquals(clinicaVeterinariaTest.getCodVeterinaria(), codVeterinaria);
		assertEquals(clinicaVeterinariaTest.getEmpleados(), empleados);

		assertEquals(clinicaVeterinariaTest.toString(),
				"ClinicaVeterinaria [ubicacion=Bilbao, codVeterinaria=3126354, empleados=[]]");
	}

	@Test
	public void testEquals() {
		assertTrue(clinicaVeterinariaTest.equals(clinicaVeterinariaTest1));
		assertFalse(clinicaVeterinariaTest.equals(clinicaVeterinariaTest2));
		assertTrue(clinicaVeterinariaTest.equals(clinicaVeterinariaTest));
		assertFalse(clinicaVeterinariaTest.equals(clinicaVeterinariaTest3));
		assertFalse(clinicaVeterinariaTest.equals(clinicaVeterinariaTest.getClass()));
	}

}
