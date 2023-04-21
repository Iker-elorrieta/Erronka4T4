package Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.sql.Time;

import org.junit.jupiter.api.Test;

import Modelo.Pedido;
import Modelo.Producto;
import ModeloPerfil.Cliente;

class PedidoTest {

	Producto[] productos;
	Date fecha;
	Time hora;
	int cantidadProducto = 25;
	int codPedido = 3615651;
	int codPedido0 = 0;
	Cliente cliente;
	float preciototal = (float) 2.15;

	Pedido pedidoTest = new Pedido(productos, fecha, hora, cantidadProducto, codPedido, cliente, preciototal);
	Pedido pedidoTest1 = new Pedido(productos, fecha, hora, cantidadProducto, codPedido, cliente, preciototal);
	Pedido pedidoTest2 = null;
	Pedido pedidoTest3 = new Pedido(productos, fecha, hora, cantidadProducto, codPedido0, cliente, preciototal);

	@Test
	void test() {

		pedidoTest.setProductos(productos);
		pedidoTest.setFecha(fecha);
		pedidoTest.setHora(hora);
		pedidoTest.setCantidadProducto(cantidadProducto);
		pedidoTest.setCodPedido(codPedido);
		pedidoTest.setCliente(cliente);
		pedidoTest.setPreciototal(preciototal);

		assertEquals(pedidoTest.getProductos(), productos);
		assertEquals(pedidoTest.getFecha(), fecha);
		assertEquals(pedidoTest.getHora(), hora);
		assertEquals(pedidoTest.getCantidadProducto(), cantidadProducto);
		assertEquals(pedidoTest.getCodPedido(), codPedido);
		assertEquals(pedidoTest.getCliente(), cliente);
		assertEquals(pedidoTest.getPreciototal(), preciototal);

		assertEquals(pedidoTest.toString(),
				"Pedido [productos=null, fecha=null, hora=null, cantidadProducto=25, codPedido=3615651, cliente=null, preciototal=2.15]");
	}

	@Test
	public void testEquals() {
		assertTrue(pedidoTest.equals(pedidoTest1));
		assertFalse(pedidoTest.equals(pedidoTest2));
		assertTrue(pedidoTest.equals(pedidoTest));
		assertFalse(pedidoTest.equals(pedidoTest3));
		assertFalse(pedidoTest.equals(pedidoTest.getClass()));
	}
}
