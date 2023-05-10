package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

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

	Empleado empleadoTest = new Empleado(dni, nombre, apellido, direccion, contrasenya, antiguedad, salario, especializacion);
	Empleado empleadoTest1 = new Empleado(dni, nombre, apellido, direccion, contrasenya, antiguedad, salario, especializacion);
	Empleado empleadoTest2 = null;
	Empleado empleadoTest3 = new Empleado(dni0, nombre, apellido, direccion, contrasenya, antiguedad, salario, especializacion);

	@Test
	void test() {
		
		Empleado empleadoTestVacio = new Empleado();
		
		empleadoTestVacio.setDni(dni);
		empleadoTestVacio.setNombre(nombre);
		empleadoTestVacio.setApellido(apellido);
		empleadoTestVacio.setDireccion(direccion);
		empleadoTestVacio.setAntiguedad(antiguedad);
		empleadoTestVacio.setSalario(salario);
		empleadoTestVacio.setEspecializacion(Especialidad.Loros);
		
		empleadoTest.setDni(dni);
		empleadoTest.setNombre(nombre);
		empleadoTest.setApellido(apellido);
		empleadoTest.setDireccion(direccion);
		empleadoTest.setAntiguedad(antiguedad);
		empleadoTest.setSalario(salario);
		empleadoTest.setEspecializacion(Especialidad.Loros);

		assertEquals(empleadoTest.getDni(), dni);
		assertEquals(empleadoTest.getNombre(), nombre);
		assertEquals(empleadoTest.getApellido(), apellido);
		assertEquals(empleadoTest.getDireccion(), direccion);
		assertEquals(empleadoTest.getAntiguedad(), antiguedad);
		assertEquals(empleadoTest.getSalario(), salario);
		assertEquals(empleadoTest.getEspecializacion(), Especialidad.Loros);
		
		assertEquals(empleadoTest.toString(),
				"Empleado [antiguedad=2, salario=1200.3, especializacion=Loros, nombre=Raul, apellido=Monterel, dni=3687951L, direccion=Calle Ave del Paraiso 8, contrasenya=dashdasijd55]");
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

		float salarioEsperado = empleadoTest.calcularSalario(Especialidad.Limpieza);
		assertEquals(salarioEsperado, 0);

	}

	@Test
	void testBonoAntiguedadProgramador() {
		float bonoAntiguedad = empleadoTest.bonoAntiguedad(antiguedad, Especialidad.Ventas);
		assertEquals(bonoAntiguedad, 0);
	}
}
