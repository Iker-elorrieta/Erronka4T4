package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Modelo.Gestion;
import Modelo.Producto;
import ModeloPerfil.Empleado;

class GestionTest {

	int codGestion = 351561;
	int codGestion0 = 0;
	int cantidad = 20;
	Date fecha;
	Time hora;
	Empleado empleado;
	ArrayList<Producto> productos;

	Gestion gestionTest = new Gestion(codGestion, cantidad, fecha, hora, empleado, productos);
	Gestion gestionTest1 = new Gestion(codGestion, cantidad, fecha, hora, empleado, productos);
	Gestion gestionTest2 = null;
	Gestion gestionTest3 = new Gestion(codGestion0, cantidad, fecha, hora, empleado, productos);

	@Test
	void test() {
		
		Gestion gestionTestVacio = new Gestion();;
		
		gestionTestVacio.setCodGestion(codGestion);
		gestionTestVacio.setCantidad(cantidad);
		gestionTestVacio.setFecha(fecha);
		gestionTestVacio.setHora(hora);
		gestionTestVacio.setEmpleado(empleado);
		gestionTestVacio.setProductos(productos);
		
		gestionTest.setCodGestion(codGestion);
		gestionTest.setCantidad(cantidad);
		gestionTest.setFecha(fecha);
		gestionTest.setHora(hora);
		gestionTest.setEmpleado(empleado);
		gestionTest.setProductos(productos);

		assertEquals(gestionTest.getCodGestion(), codGestion);
		assertEquals(gestionTest.getCantidad(), cantidad);
		assertEquals(gestionTest.getFecha(), fecha);
		assertEquals(gestionTest.getHora(), hora);
		assertEquals(gestionTest.getEmpleado(), empleado);
		assertEquals(gestionTest.getProductos(), productos);

		assertEquals(gestionTest.toString(),
				"Gestion [idGestion=351561, cantidad=20, fecha=null, hora=null, empleado=null, productos=null]");

	}

	@Test
	public void testEquals() {
		assertTrue(gestionTest.equals(gestionTest1));
		assertFalse(gestionTest.equals(gestionTest2));
		assertTrue(gestionTest.equals(gestionTest));
		assertFalse(gestionTest.equals(gestionTest3));
		assertFalse(gestionTest.equals(gestionTest3.getClass()));
	}
}
