package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Modelo.GestionAnimal;
import ModeloAnimal.Mascota;
import ModeloPerfil.Empleado;

class GestionAnimalTest {

	int codGestionAnimal = 345;
	int codGestionAnimal0 = 0;
	Date fecha;
	int cantidad = 465;
	ArrayList<Mascota> listaMascota;
	Empleado empleado;

	GestionAnimal gestionAnimalTest = new GestionAnimal(codGestionAnimal, fecha, cantidad, listaMascota, empleado);
	GestionAnimal gestionAnimalTest1 = new GestionAnimal(codGestionAnimal, fecha, cantidad, listaMascota, empleado);
	GestionAnimal gestionAnimalTest2 = null;
	GestionAnimal gestionAnimalTest3 = new GestionAnimal(codGestionAnimal0, fecha, cantidad, listaMascota, empleado);


	@Test
	void test() {
		
		GestionAnimal gestionAnimalTestVacio = new GestionAnimal();
		
		gestionAnimalTestVacio.setCantidad(cantidad);
		gestionAnimalTestVacio.setCodGestionAnimal(codGestionAnimal);
		gestionAnimalTestVacio.setFecha(fecha);
		gestionAnimalTestVacio.setListaMAscota(listaMascota);
		gestionAnimalTestVacio.setEmpleado(empleado);
		
		gestionAnimalTest.setCantidad(cantidad);
		gestionAnimalTest.setCodGestionAnimal(codGestionAnimal);
		gestionAnimalTest.setFecha(fecha);
		gestionAnimalTest.setListaMAscota(listaMascota);
		gestionAnimalTest.setEmpleado(empleado);
		
		assertEquals(gestionAnimalTest.getCantidad(), cantidad);
		assertEquals(gestionAnimalTest.getCodGestionAnimal(), codGestionAnimal);
		assertEquals(gestionAnimalTest.getEmpleado(), empleado);
		assertEquals(gestionAnimalTest.getFecha(), fecha);
		assertEquals(gestionAnimalTest.getListaMAscota(), listaMascota);
		
		assertEquals(gestionAnimalTest.toString(), "GestionAnimal [codGestionAnimal=345, fecha=null, cantidad=465, listaMAscota=null, empleado=null]");
	}
	
	@Test
	public void testEquals() {
		assertTrue(gestionAnimalTest.equals(gestionAnimalTest1));
		assertFalse(gestionAnimalTest.equals(gestionAnimalTest2));
		assertTrue(gestionAnimalTest.equals(gestionAnimalTest));
		assertFalse(gestionAnimalTest.equals(gestionAnimalTest3));
		assertFalse(gestionAnimalTest.equals(gestionAnimalTest3.getClass()));
	}

}
