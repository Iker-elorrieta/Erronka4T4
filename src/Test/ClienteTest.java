package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Animales;
import ModeloPerfil.Cliente;

class ClienteTest {

	String dni = "3687951L";
	String dni0 = "0";
	String nombre = "Raul";
	String apellido = "Monterel";
	String contrasenya = "dashdasijd55";
	String direccion = "Calle Ave del Paraiso 8";

	
	// ---------------------------------------------------//
	Animales[] animales;

	Cliente clienteTest = new Cliente(dni, nombre, apellido, direccion, contrasenya, animales);
	Cliente clienteTest1 = new Cliente(dni, nombre, apellido, direccion, contrasenya, animales);
	Cliente clienteTest2 = null;
	Cliente clienteTest3 = new Cliente(dni0, nombre, apellido, direccion, contrasenya, animales);

	@Test
	void test() {
		clienteTest.setDni(dni);
		clienteTest.setNombre(nombre);
		clienteTest.setApellido(apellido);
		clienteTest.setDireccion(direccion);
		clienteTest.setContrasenya(contrasenya);
		clienteTest.setAnimales(animales);

		assertEquals(clienteTest.getDni(), dni);
		assertEquals(clienteTest.getNombre(), nombre);
		assertEquals(clienteTest.getApellido(), apellido);
		assertEquals(clienteTest.getDireccion(), direccion);
		assertEquals(clienteTest.getContrasenya(), contrasenya);
		assertEquals(clienteTest.getAnimales(), animales);

		assertEquals(clienteTest.toString(),
				"Cliente [animales=null, nombre=Raul, apellido=Monterel, dni=3687951L, direccion=Calle Ave del Paraiso 8, contrasenya=dashdasijd55]");
	}

	@Test
	public void testEquals() {
		assertTrue(clienteTest.equals(clienteTest1));
		assertFalse(clienteTest.equals(clienteTest2));
		assertTrue(clienteTest.equals(clienteTest));
		assertTrue(clienteTest.equals(clienteTest3));
		assertFalse(clienteTest.equals(dni.getClass()));
	}
}
