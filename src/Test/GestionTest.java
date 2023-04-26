package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Time;
import java.util.Date;

import org.junit.jupiter.api.Test;

import Modelo.Gestion;
import Modelo.Producto;
import ModeloPerfil.Empleado;

class GestionTest {

	int idGestion = 351561;
	int idGestion0 = 0;
	int cantidad = 20;
	Date fecha;
	Time hora;
	Empleado empleado;
	Producto[] productos;

	Gestion gestionTest = new Gestion(idGestion, cantidad, fecha, hora, empleado, productos);
	Gestion gestionTest1 = new Gestion(idGestion, cantidad, fecha, hora, empleado, productos);
	Gestion gestionTest2 = null;
	Gestion gestionTest3 = new Gestion(idGestion0, cantidad, fecha, hora, empleado, productos);

	@Test
	void test() {
		gestionTest.setIdGestion(idGestion);
		gestionTest.setCantidad(cantidad);
		gestionTest.setFecha(fecha);
		gestionTest.setHora(hora);
		gestionTest.setEmpleado(empleado);
		gestionTest.setProductos(productos);

		assertEquals(gestionTest.getIdGestion(), idGestion);
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
