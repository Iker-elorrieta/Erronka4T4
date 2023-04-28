package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controlador.MetodosPedido;
import Modelo.Pedido;

class MetodosPedidoTest {

	MetodosPedido metodosPedido = new MetodosPedido();
	
	@Test
	void test() throws SQLException {
		
		ArrayList<Pedido> listaPedido = metodosPedido.recogerPedido();
		String resultado = listaPedido.get(0).toString();
		assertEquals(resultado,"Pedido [productos=[Producto [nombreProducto=Pienso para perros, precio=5.0, stock=15, codProducto=2]], fecha=2023-04-26, hora=08:38:42, cantidadProducto=2, codPedido=1, cliente=null, preciototal=10.0]");
	}

}
