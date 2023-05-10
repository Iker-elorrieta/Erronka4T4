package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ModeloAnimal.Mascota;

class MascotaTest {

	int codMascota = 900;
	int codMascota0 = 0;
	String especieMascota = "Perro";
	int stockMascota = 10;
	float precioMascota = 50;
	
	Mascota mascotaTest = new Mascota(codMascota, especieMascota, stockMascota, precioMascota);
	Mascota mascotaTest1 = new Mascota(codMascota, especieMascota, stockMascota, precioMascota);
	Mascota mascotaTest2 = null;
	Mascota mascotaTest3 = new Mascota(codMascota0, especieMascota, stockMascota, precioMascota);
	
	
	@Test
	void test() {
		Mascota mascotaTestVacio = new Mascota();
		mascotaTestVacio.setCodMascota(codMascota);
		mascotaTestVacio.setEspecie(especieMascota);
		mascotaTestVacio.setPrecio(precioMascota);
		mascotaTestVacio.setStock(stockMascota);
		
		mascotaTest.setCodMascota(codMascota);
		mascotaTest.setEspecie(especieMascota);
		mascotaTest.setPrecio(precioMascota);
		mascotaTest.setStock(stockMascota);
		
		assertEquals(mascotaTest.getEspecie(),especieMascota);
		assertEquals(mascotaTest.getPrecio(),precioMascota);
		assertEquals(mascotaTest.getStock(),stockMascota);
		assertEquals(mascotaTest.getCodMascota(),codMascota);
		
		assertEquals(mascotaTest.toString(), "Mascota [codMascota=900, especie=Perro, stock=10, precio=50.0]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(mascotaTest.equals(mascotaTest1));
		assertFalse(mascotaTest.equals(mascotaTest2));
		assertTrue(mascotaTest.equals(mascotaTest));
		assertFalse(mascotaTest.equals(mascotaTest3));
		assertFalse(mascotaTest.equals(mascotaTest.getClass()));
	}

}
