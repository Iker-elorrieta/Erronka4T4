package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controlador.MetodosProducto;
import Modelo.Producto;

class MetodosProductoTest {

	MetodosProducto metodosProducto = new MetodosProducto();
	
	@Test
	void test() throws SQLException {
		
		ArrayList<Producto> listaProducto = metodosProducto.recogerProducto();
		String resultado = listaProducto.get(0).toString();
		assertEquals(resultado, "Producto [nombreProducto=Correa, precio=2.0, stock=34, codProducto=1]");
	}

}
