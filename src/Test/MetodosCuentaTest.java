package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosCliente;
import Controlador.MetodosCuenta;
import Excepciones.DniInvalidoException;
import ModeloPerfil.Cliente;
import ModeloPerfil.Cuenta;

class MetodosCuentaTest extends ManagerAbstract {

	final String numeroCuenta = "NumeroCuenta";
	final String dni = "DNI";

	MetodosCuenta metodosCuenta = new MetodosCuenta();
	MetodosCliente metodosCliente = new MetodosCliente();

	// --------------------VariablesNecesariasParaElTest--------------------//
	int numeroCuentaNuevo = 900;

	String nombreNuevo = "Ander";
	String apellidosNuevo = "Perex";
	String dniNuevo = "22761890D";
	String direccionNuevo = "Mi casa";
	String contrasenyaNuevo = "hola";

	String[] numCuentaNueva = { "1000" };
	int numCuentaNuevaUpdate = 1000;
	String column[] = { "NumeroCuenta" };

	Connection conexion;

	@Test
	void recogerCuentaTest() {

		ArrayList<Cuenta> listaCuenta;
		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
					+ "VALUES( '" + numeroCuentaNuevo + "' , '" + dniNuevo + "');");

			listaCuenta = metodosCuenta.recogerCuenta();

			String resultado = "";

			for (Cuenta cuenta2 : listaCuenta) {
				if (cuenta2.getNumeroCuenta() == numeroCuentaNuevo) {
					int pos = listaCuenta.indexOf(cuenta2);
					resultado = listaCuenta.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Cuenta [numeroCuenta=900, cliente=Cliente [animal=null, nombre=Ander, apellido=Perex, dni=22761890D, direccion=Mi casa, contrasenya=hola]]");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void insertarCuentaTest() {

		Cuenta cuentaNueva = new Cuenta();
		cuentaNueva.setNumeroCuenta(numeroCuentaNuevo);

		try {
			Controlador.MetodosGenerales.comprobarDni(dniNuevo);
			try {

				conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
				Statement resul = conexion.createStatement();
				resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
						+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo
						+ "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
						+ direccionNuevo + "');");

				metodosCuenta.insertarCuenta(cuentaNueva, dniNuevo);

				Statement sacaCliente = conexion.createStatement();
				String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where dni = '" + dniNuevo + " ';";
				ResultSet resul2 = sacaCliente.executeQuery(sql);
				while (resul2.next()) {

					assertEquals(dniNuevo, resul2.getString(dni));
					assertEquals(numeroCuentaNuevo, resul2.getInt(numeroCuenta));
				}

				Statement resul3 = conexion.createStatement();

				resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '"
						+ dniNuevo + "';");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (NumberFormatException | DniInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void eliminarCuentaTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
					+ "VALUES( '" + numeroCuentaNuevo + "' , '" + dniNuevo + "');");

			metodosCuenta.eliminarCuenta(numeroCuentaNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where NumeroCuenta = '" + numeroCuentaNuevo
					+ " ';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul3.isBeforeFirst(), false);

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateCuentaTest() {

		Cliente clienteNuevo = new Cliente();
		clienteNuevo.setDni(dni);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		Cuenta cuentaNueva = new Cuenta();
		cuentaNueva.setNumeroCuenta(numeroCuentaNuevo);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
					+ "VALUES( '" + numeroCuentaNuevo + "' , '" + dniNuevo + "');");

			metodosCuenta.updateCuenta(column, numCuentaNueva, cuentaNueva);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where NumeroCuenta = '"
					+ numCuentaNuevaUpdate + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {

				System.out.println(numCuentaNuevaUpdate);
				System.out.println(resul3.getInt(numeroCuenta));
				assertEquals(numCuentaNuevaUpdate, resul3.getInt(numeroCuenta));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '"
					+ numCuentaNuevaUpdate + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
