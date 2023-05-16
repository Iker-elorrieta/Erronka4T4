package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Modelo.Adopcion;
import ModeloAnimal.Mascota;
import ModeloPerfil.Cliente;

class AdopcionTest {

	int codAdopcion = 4531;
	int codAdopcion0 = 0;
	float precioTotal = 5331;
	Date fecha;
	ArrayList<Mascota> mascota;
	Cliente cliente;
	
	Adopcion adopcionTest = new Adopcion(codAdopcion, precioTotal, fecha, codAdopcion, mascota, cliente);
	Adopcion adopcionTest1 = new Adopcion(codAdopcion, precioTotal, fecha, codAdopcion, mascota, cliente);
	Adopcion adopcionTest2 = null;
	Adopcion adopcionTest3 = new Adopcion(codAdopcion0, precioTotal, fecha, codAdopcion, mascota, cliente);
	
	@Test
	void test() {
		Adopcion adopcionTestVacio = new Adopcion();
		
		adopcionTestVacio.setCliente(cliente);
		adopcionTestVacio.setCodAdopcion(codAdopcion);
		adopcionTestVacio.setFecha(fecha);
		adopcionTestVacio.setListaMascota(mascota);
		adopcionTestVacio.setPrecioTotal(precioTotal);
		
		adopcionTest.setCliente(cliente);
		adopcionTest.setCodAdopcion(codAdopcion);
		adopcionTest.setFecha(fecha);
		adopcionTest.setListaMascota(mascota);
		adopcionTest.setPrecioTotal(precioTotal);
		
		assertEquals(adopcionTest.getCliente(), cliente);
		assertEquals(adopcionTest.getCodAdopcion(), codAdopcion);
		assertEquals(adopcionTest.getFecha(), fecha);
		assertEquals(adopcionTest.getListaMascota(), mascota);
		assertEquals(adopcionTest.getPrecioTotal(), precioTotal);
		
		assertEquals(adopcionTest.toString(), "Adopcion [codAdopcion=4531, precioTotal=5331.0, fecha=null, listaMascota=null, cliente=null]");
		
	}
	
	@Test
	public void testEquals() {
		assertTrue(adopcionTest.equals(adopcionTest1));
		assertFalse(adopcionTest.equals(adopcionTest2));
		assertTrue(adopcionTest.equals(adopcionTest));
		assertFalse(adopcionTest.equals(adopcionTest3));
		assertFalse(adopcionTest.equals(adopcionTest3.getClass()));
	}

}
