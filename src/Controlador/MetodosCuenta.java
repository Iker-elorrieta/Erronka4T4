package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import ModeloPerfil.Cliente;
import ModeloPerfil.Cuenta;

public class MetodosCuenta extends ManagerAbstract {

	final String numeroCuenta = "NumeroCuenta";
	final String dni = "DNI";

	public ArrayList<Cuenta> recogerCuenta() throws SQLException {

		ArrayList<Cuenta> listaCuenta = new ArrayList<Cuenta>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaProducto = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_CUENTA;
		ResultSet resul = sacaProducto.executeQuery(sql);
		while (resul.next()) {
			Cuenta cuenta = new Cuenta();
			cuenta.setNumeroCuenta(resul.getInt(numeroCuenta));

			String dniTabla = resul.getString(dni);

			MetodosCliente metodosCliente = new MetodosCliente();
			ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();

			for (Cliente cliente : listaCliente) {
				if (dniTabla.equalsIgnoreCase(cliente.getDni())) {
					cuenta.setCliente(cliente);
					;
				}
			}

			listaCuenta.add(cuenta);
		}
		return listaCuenta;
	}

	public void insertarCuenta(Cuenta cuentaNueva, String dni) throws SQLException {

		int numeroCuenta = 0;

		Cuenta cuenta = cuentaNueva;

		numeroCuenta = cuenta.getNumeroCuenta();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
				+ "VALUES( '" + numeroCuenta + "' , '" + dni + "');");
	}

	public void eliminarCuenta(int numeroCuenta) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_CUENTA + "`" + "WHERE NumeroCuenta = '" + numeroCuenta + "';");

	}

	public void updateCuenta(String[] nombreColumna, String[] UpdateColumna, Cuenta cuenta) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE cuenta set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where NumeroCuenta = " + "'" + cuenta.getNumeroCuenta() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}

}
