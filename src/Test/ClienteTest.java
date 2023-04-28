package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Animal;
import ModeloPerfil.Cliente;

class ClienteTest {

	String dni = "3687951L";
	String dni0 = "0";
	String nombre = "Raul";
	String apellido = "Monterel";
	String contrasenya = "dashdasijd55";
	String direccion = "Calle Ave del Paraiso 8";
	// ---------------------------------------------------//
	ArrayList<Animal> animal;

	Cliente clienteTest = new Cliente(dni, nombre, apellido, direccion, contrasenya, animal);
	Cliente clienteTest1 = new Cliente(dni, nombre, apellido, direccion, contrasenya, animal);
	Cliente clienteTest2 = null;
	Cliente clienteTest3 = new Cliente(dni0, nombre, apellido, direccion, contrasenya, animal);

	@Test
	void test() {
		
		Cliente clienteTestVacio = new Cliente();
		
		clienteTestVacio.setDni(dni);
		clienteTestVacio.setNombre(nombre);
		clienteTestVacio.setApellido(apellido);
		clienteTestVacio.setDireccion(direccion);
		clienteTestVacio.setContrasenya(contrasenya);
		clienteTestVacio.setAnimal(animal);
		
		clienteTest.setDni(dni);
		clienteTest.setNombre(nombre);
		clienteTest.setApellido(apellido);
		clienteTest.setDireccion(direccion);
		clienteTest.setContrasenya(contrasenya);
		clienteTest.setAnimal(animal);

		assertEquals(clienteTest.getDni(), dni);
		assertEquals(clienteTest.getNombre(), nombre);
		assertEquals(clienteTest.getApellido(), apellido);
		assertEquals(clienteTest.getDireccion(), direccion);
		assertEquals(clienteTest.getContrasenya(), contrasenya);
		assertEquals(clienteTest.getAnimal(), animal);

		assertEquals(clienteTest.toString(),
				"Cliente [animal=null, nombre=Raul, apellido=Monterel, dni=3687951L, direccion=Calle Ave del Paraiso 8, contrasenya=dashdasijd55]");
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
