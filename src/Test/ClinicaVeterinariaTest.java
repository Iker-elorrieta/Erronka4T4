package Test;

import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.ClinicaVeterinaria;
import ModeloPerfil.Empleado;

class ClinicaVeterinariaTest {

	String ubicacion = "Bilbao";
	int codVeterinaria = 3126354;
	int codVeterinaria0 = 0;
	Empleado[] empleados = new Empleado[1];
	
	ClinicaVeterinaria clinicaVeterinariaTest = new ClinicaVeterinaria(ubicacion, codVeterinaria);
	ClinicaVeterinaria clinicaVeterinariaTest1 = new ClinicaVeterinaria(ubicacion, codVeterinaria);
	ClinicaVeterinaria clinicaVeterinariaTest2 = null;
	ClinicaVeterinaria clinicaVeterinariaTest3 = new ClinicaVeterinaria(ubicacion, codVeterinaria0);
	
	@Test
	void test() {
		clinicaVeterinariaTest.setEmpleados(empleados);
		clinicaVeterinariaTest.setUbicacion(ubicacion);
		clinicaVeterinariaTest.setCodVeterinaria(codVeterinaria);

		assertEquals(clinicaVeterinariaTest.getUbicacion(), ubicacion);
		assertEquals(clinicaVeterinariaTest.getCodVeterinaria(), codVeterinaria);
		assertEquals(clinicaVeterinariaTest.getEmpleados(), empleados);

		assertEquals(clinicaVeterinariaTest.toString(),
				"ClinicaVeterinaria [ubicacion=Bilbao, codVeterinaria=3126354]");
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
