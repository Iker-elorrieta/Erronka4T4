package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Perro;
import ModeloPerfil.Cliente;

class PerroTest {

	String nombreAnimal = "Casper";
	int idAnimal = 1002;
	int idAnimal0 = 0;
	int edad = 4;
	String especie = "Cualquiera";
	String sexo = "Macho";
	Cliente cliente;

	Perro perroTest = new Perro(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Perro perroTest1 = new Perro(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Perro perroTest2 = null;
	Perro perroTest3 = new Perro(nombreAnimal, idAnimal0, edad, especie, sexo, cliente);

	@Test
	void test() {
		perroTest.setNombreAnimal(nombreAnimal);
		perroTest.setIdAnimal(idAnimal);
		perroTest.setEdad(edad);
		perroTest.setEspecie(especie);
		perroTest.setSexo(sexo);
		perroTest.setCliente(cliente);

		assertEquals(perroTest.getNombreAnimal(), nombreAnimal);
		assertEquals(perroTest.getIdAnimal(), idAnimal);
		assertEquals(perroTest.getEdad(), edad);
		assertEquals(perroTest.getEspecie(), especie);
		assertEquals(perroTest.getSexo(), sexo);
		assertEquals(perroTest.getCliente(), cliente);

		assertEquals(perroTest.toString(),
				"Perro [, nombreAnimal=Casper, idAnimal=1002, edad=4, especie=Cualquiera, sexo=Macho, cliente=null]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(perroTest.equals(perroTest1));
		assertFalse(perroTest.equals(perroTest2));
		assertTrue(perroTest.equals(perroTest));
		assertFalse(perroTest.equals(perroTest3));
		assertFalse(perroTest.equals(perroTest.getClass()));
	}
}
