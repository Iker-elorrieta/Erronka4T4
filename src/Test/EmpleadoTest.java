package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import Modelo.Consulta;
import Modelo.Gestion;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

class EmpleadoTest {

	String dni = "3687951L";
	String dni0 = "0";
	String nombre = "Raul";
	String apellido = "Monterel";
	String direccion = "Calle Ave del Paraiso 8";
	String contrasenya = "dashdasijd55";
	// ---------------------------------------------------//
	int antiguedad = 2;
	float salario = (float) 1200.3;
	Especialidad especializacion;
	Gestion[] gestiones = new Gestion[1];
	Consulta[] consultas = new Consulta[1];

	Empleado empleadoTest = new Empleado(dni, nombre, apellido, direccion, contrasenya, antiguedad, salario, consultas,
			gestiones, especializacion);
	Empleado empleadoTest1 = new Empleado(dni, nombre, apellido, direccion, contrasenya, antiguedad, salario, consultas,
			gestiones, especializacion);
	Empleado empleadoTest2 = null;
	Empleado empleadoTest3 = new Empleado(dni0, nombre, apellido, direccion, contrasenya, antiguedad, salario,
			consultas, gestiones, especializacion);

	@Test
	void test() {
		empleadoTest.setDni(dni);
		empleadoTest.setNombre(nombre);
		empleadoTest.setApellido(apellido);
		empleadoTest.setDireccion(direccion);
		empleadoTest.setAntiguedad(antiguedad);
		empleadoTest.setSalario(salario);
		empleadoTest.setConsultas(consultas);
		empleadoTest.setGestiones(gestiones);
		empleadoTest.setEspecializacion(Especialidad.Loros);

		assertEquals(empleadoTest.getDni(), dni);
		assertEquals(empleadoTest.getConsultas(), consultas);
		assertEquals(empleadoTest.getGestiones(), gestiones);
		assertEquals(empleadoTest.getDni(), dni);
		assertEquals(empleadoTest.getNombre(), nombre);
		assertEquals(empleadoTest.getApellido(), apellido);
		assertEquals(empleadoTest.getDireccion(), direccion);
		assertEquals(empleadoTest.getAntiguedad(), antiguedad);
		assertEquals(empleadoTest.getSalario(), salario);
		assertEquals(empleadoTest.getEspecializacion(), Especialidad.Loros);

		assertEquals(empleadoTest.toString(),
				"Empleado [antiguedad=" + antiguedad + ", salario=" + salario + ", consultas="
						+ Arrays.toString(consultas) + ", gestiones=" + Arrays.toString(gestiones)
						+ ", especializacion=" + especializacion + ", nombre=" + nombre + ", apellido=" + apellido
						+ ", dni=" + dni + ", direccion=" + direccion + ", contrasenya=" + contrasenya + "]");
	}

	@Test
	public void testEquals() {
		assertTrue(empleadoTest.equals(empleadoTest1));
		assertFalse(empleadoTest.equals(empleadoTest2));
		assertTrue(empleadoTest.equals(empleadoTest));
		assertTrue(empleadoTest.equals(empleadoTest3));
		assertFalse(empleadoTest.equals(empleadoTest.getClass()));
	}

	@Test
	void testCalcularSalarioProgramador() {

		float salarioEsperado = empleadoTest.calcularSalario(Especialidad.limpieza);
		assertEquals(salarioEsperado, 0);

	}

	@Test
	void testBonoAntiguedadProgramador() {
		float bonoAntiguedad = empleadoTest.bonoAntiguedad(antiguedad, Especialidad.ventas);
		assertEquals(bonoAntiguedad, 0);
	}
}
