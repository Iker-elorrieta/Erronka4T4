package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Gato;
import ModeloPerfil.Cliente;

class GatoTest {

	String nombreAnimal = "Casper";
	int idAnimal = 1002;
	int idAnimal0 = 0;
	int edad = 4;
	String especie = "Cualquiera";
	String sexo = "Macho";
	Cliente cliente;

	Gato gatoTest = new Gato(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Gato gatoTest1 = new Gato(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
	Gato gatoTest2 = null;
	Gato gatoTest3 = new Gato(nombreAnimal, idAnimal0, edad, especie, sexo, cliente);

	@Test
	void test() {
		
		Gato gatoTestVacio = new Gato();
		
		gatoTestVacio.setNombreAnimal(nombreAnimal);
		gatoTestVacio.setIdAnimal(idAnimal);
		gatoTestVacio.setEdad(edad);
		gatoTestVacio.setEspecie(especie);
		gatoTestVacio.setSexo(sexo);
		gatoTestVacio.setCliente(cliente);
		
		gatoTest.setNombreAnimal(nombreAnimal);
		gatoTest.setIdAnimal(idAnimal);
		gatoTest.setEdad(edad);
		gatoTest.setEspecie(especie);
		gatoTest.setSexo(sexo);
		gatoTest.setCliente(cliente);

		assertEquals(gatoTest.getNombreAnimal(), nombreAnimal);
		assertEquals(gatoTest.getIdAnimal(), idAnimal);
		assertEquals(gatoTest.getEdad(), edad);
		assertEquals(gatoTest.getEspecie(), especie);
		assertEquals(gatoTest.getSexo(), sexo);
		assertEquals(gatoTest.getCliente(), cliente);

		assertEquals(gatoTest.toString(),
				"Gato [ nombreAnimal=Casper, idAnimal=1002, edad=4, especie=Cualquiera, sexo=Macho, cliente=null]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(gatoTest.equals(gatoTest1));
		assertFalse(gatoTest.equals(gatoTest2));
		assertTrue(gatoTest.equals(gatoTest));
		assertFalse(gatoTest.equals(gatoTest3));
		assertFalse(gatoTest.equals(gatoTest.getClass()));
	}
	
	@Test
	public void testCosteVacuna() {
		float resultado = gatoTest.CosteVacuna(gatoTest);
		
		assertEquals(resultado, 0);
	}

}
