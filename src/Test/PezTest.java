package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Pez;
import ModeloPerfil.Cliente;

class PezTest {

	String nombreAnimal = "Casper";
	int idAnimal = 1002;
	int idAnimal0 = 0;
	int edad = 4;
	String especie = "Cualquiera";
	String sexo = "Macho";
	Cliente cliente;

	Pez pezTest = new Pez(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Pez pezTest1 = new Pez(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Pez pezTest2 = null;
	Pez pezTest3 = new Pez(nombreAnimal, idAnimal0, edad, especie, sexo, cliente);

	@Test
	void test() {
		
		Pez pezTestVacio = new Pez();
		
		pezTestVacio.setNombreAnimal(nombreAnimal);
		pezTestVacio.setIdAnimal(idAnimal);
		pezTestVacio.setEdad(edad);
		pezTestVacio.setEspecie(especie);
		pezTestVacio.setSexo(sexo);
		pezTestVacio.setCliente(cliente);
		
		pezTest.setNombreAnimal(nombreAnimal);
		pezTest.setIdAnimal(idAnimal);
		pezTest.setEdad(edad);
		pezTest.setEspecie(especie);
		pezTest.setSexo(sexo);
		pezTest.setCliente(cliente);

		assertEquals(pezTest.getNombreAnimal(), nombreAnimal);
		assertEquals(pezTest.getIdAnimal(), idAnimal);
		assertEquals(pezTest.getEdad(), edad);
		assertEquals(pezTest.getEspecie(), especie);
		assertEquals(pezTest.getSexo(), sexo);
		assertEquals(pezTest.getCliente(), cliente);

		assertEquals(pezTest.toString(),
				"Pez [, nombreAnimal=Casper, idAnimal=1002, edad=4, especie=Cualquiera, sexo=Macho, cliente=null]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(pezTest.equals(pezTest1));
		assertFalse(pezTest.equals(pezTest2));
		assertTrue(pezTest.equals(pezTest));
		assertFalse(pezTest.equals(pezTest3));
		assertFalse(pezTest.equals(pezTest.getClass()));
	}
}
