package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Modelo.Producto;

class ProductoTest {

	String nombreProducto = "Champu";
	float precio = (float) 2.15;
	int stock = 15;
	int codProducto = 54681;
	int codProducto0 = 0;
	
	Producto productoTest = new Producto(nombreProducto, precio, stock, codProducto);
	Producto productoTest1 = new Producto(nombreProducto, precio, stock, codProducto);
	Producto productoTest2 = null;
	Producto productoTest3 = new Producto(nombreProducto, precio, stock, codProducto0);

	@Test
	void test() {
		
		Producto productoTestVacio = new Producto();
		
		productoTestVacio.setNombreProducto(nombreProducto);
		productoTestVacio.setPrecio(precio);
		productoTestVacio.setStock(stock);
		productoTestVacio.setCodProducto(codProducto);
		
		productoTest.setNombreProducto(nombreProducto);
		productoTest.setPrecio(precio);
		productoTest.setStock(stock);
		productoTest.setCodProducto(codProducto);
		
		assertEquals(productoTest.getNombreProducto(),nombreProducto);
		assertEquals(productoTest.getPrecio(),precio);
		assertEquals(productoTest.getStock(),stock);
		assertEquals(productoTest.getCodProducto(),codProducto);
		
		assertEquals(productoTest.toString(),"Producto [nombreProducto=Champu, precio=2.15, stock=15, codProducto=54681]");
	}


	@Test
	public void testEquals() {
		assertTrue(productoTest.equals(productoTest1));
		assertFalse(productoTest.equals(productoTest2));
		assertTrue(productoTest.equals(productoTest));
		assertFalse(productoTest.equals(productoTest3));
		assertFalse(productoTest.equals(productoTest.getClass()));
	}
}
