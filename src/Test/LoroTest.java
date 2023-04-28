package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Loro;
import ModeloPerfil.Cliente;

class LoroTest {

	String nombreAnimal = "Casper";
	int idAnimal = 1002;
	int idAnimal0 = 1002;
	int edad = 4;
	String especie = "Cualquiera";
	String sexo = "Macho";
	Cliente cliente;

	Loro loroTest = new Loro(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Loro loroTest1 = new Loro(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Loro loroTest2 = null;
	Loro loroTest3 = new Loro(nombreAnimal, idAnimal0, edad, especie, sexo, cliente);

	@Test
	void test() {
		
		Loro loroTestVacio = new Loro();
		
		loroTestVacio.setNombreAnimal(nombreAnimal);
		loroTestVacio.setIdAnimal(idAnimal);
		loroTestVacio.setEdad(edad);
		loroTestVacio.setEspecie(especie);
		loroTestVacio.setSexo(sexo);
		loroTestVacio.setCliente(cliente);
		
		loroTest.setNombreAnimal(nombreAnimal);
		loroTest.setIdAnimal(idAnimal);
		loroTest.setEdad(edad);
		loroTest.setEspecie(especie);
		loroTest.setSexo(sexo);
		loroTest.setCliente(cliente);

		assertEquals(loroTest.getNombreAnimal(), nombreAnimal);
		assertEquals(loroTest.getIdAnimal(), idAnimal);
		assertEquals(loroTest.getEdad(), edad);
		assertEquals(loroTest.getEspecie(), especie);
		assertEquals(loroTest.getSexo(), sexo);
		assertEquals(loroTest.getCliente(), cliente);

		assertEquals(loroTest.toString(),
				"Loro [, nombreAnimal=Casper, idAnimal=1002, edad=4, especie=Cualquiera, sexo=Macho, cliente=null]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(loroTest.equals(loroTest1));
		assertFalse(loroTest.equals(loroTest2));
		assertTrue(loroTest.equals(loroTest));
		assertTrue(loroTest.equals(loroTest3));
		assertFalse(loroTest.equals(loroTest.getClass()));
	}
}
