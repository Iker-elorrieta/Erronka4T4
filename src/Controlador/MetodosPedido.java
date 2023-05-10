package Controlador;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.Pedido;
import Modelo.Producto;
import ModeloPerfil.Cliente;

public class MetodosPedido extends ManagerAbstract {

	final String productos = "CodProducto";
	final String fecha = "Fecha";
	final String hora = "Hora";
	final String cantidadProducto = "CantidadProductos";
	final String codPedido = "CodPedido";
	final String preciototal = "PrecioTotal";
	// -------------------------------------------//
	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";

	public ArrayList<Pedido> recogerPedido() throws SQLException {

		ArrayList<Pedido> listaPedido = new ArrayList<Pedido>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaPedido = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_PEDIDO;
		ResultSet resul = sacaPedido.executeQuery(sql);
		while (resul.next()) {

			Pedido pedido = new Pedido();

			pedido.setFecha(resul.getDate(fecha));
			pedido.setHora(resul.getTime(hora));
			pedido.setCantidadProducto(resul.getInt(cantidadProducto));
			pedido.setCodPedido(resul.getInt(codPedido));
			pedido.setPreciototal(resul.getFloat(preciototal));

			ArrayList<Producto> listaProducto = new ArrayList<Producto>();
			Statement sacaProducto = conexion.createStatement();
			String sql2 = "select distinct * from " + ManagerAbstract.TABLE_PRODUCTOS + " join "
					+ ManagerAbstract.TABLE_PEDIDO
					+ " on productos.CodProducto = pedidos.CodProducto where pedidos.CodProducto = '"
					+ resul.getInt(codProducto) + "'";
			ResultSet resul2 = sacaProducto.executeQuery(sql2);
			while (resul2.next()) {
				Producto producto = new Producto();
				producto.setNombreProducto(resul2.getString(nombreProducto));
				producto.setPrecio(resul2.getFloat(precio));
				producto.setStock(resul2.getInt(stock));
				producto.setCodProducto(resul2.getInt(codProducto));
				listaProducto.add(producto);
			}
			pedido.setProductos(listaProducto);
			listaPedido.add(pedido);
		}

		return listaPedido;
	}

	public void insertarPedido(Pedido pedidoNuevo, String dni, int codProducto, int codPedidoNuevo)
			throws SQLException {

		MetodosCliente metodosCliente = new MetodosCliente();

		Date fecha = null;
		Time hora = null;
		float precioTotal = 0;
		int cantidadProductos = 0;

		Pedido pedido = pedidoNuevo;

		fecha = pedido.getFecha();
		hora = pedido.getHora();
		precioTotal = pedido.getPreciototal();
		cantidadProductos = pedido.getCantidadProducto();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();
		for (Cliente cliente : listaCliente) {
			if (cliente.getDni().equalsIgnoreCase(dni)) {
				resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PEDIDO + "`"
						+ "(`CodPedido`, `PrecioTotal`, `Fecha`, `Hora`, `CantidadProductos`, `CodProducto`, `DNI`) "
						+ "VALUES( '" + codPedidoNuevo + "' , '" + precioTotal + "' , '" + fecha + "' , '" + hora
						+ "' , '" + cantidadProductos + "' , '" + codProducto + "' , '" + dni + "');");
			}
		}
	}

	public void eliminarPedido(int codPedido) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PEDIDO + "`" + "WHERE pedidos.CodPedido = '"
				+ codPedido + "';");

	}

	public void updatePedido(String[] nombreColumna, String[] UpdateColumna, Pedido pedido) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE " + ManagerAbstract.TABLE_PEDIDO + " set " + nombreColumna[cont] + " = " + "'"
					+ UpdateColumna[cont] + "'" + " where CodPedido = " + "'" + pedido.getCodPedido() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}

	public ArrayList<Pedido> ArrayListTxt(ArrayList<Pedido> listaPedido) throws SQLException {
		ArrayList<Pedido> listaPedidotxt = listaPedido;

		String nombreArchivo = "lista.txt";

		try {
			FileWriter escritor = new FileWriter(nombreArchivo);
			for (Pedido pedido : listaPedidotxt) {
				escritor.write(pedido.toString() + "\n");
			}
			escritor.close();
			System.out.println("Los elementos se han guardado ");
		} catch (IOException e) {
			System.out.println("error");
			e.printStackTrace();

		}

		return listaPedido;
	}
}
