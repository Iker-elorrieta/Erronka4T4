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
import Controlador.MetodosGenerales;
import Excepciones.DniInvalidoException;
import ModeloPerfil.Cliente;
import ModeloPerfil.Cuenta;

class MetodosCuentaTest extends ManagerAbstract {

	final String numeroCuenta = "NumeroCuenta";
	final String dni = "DNI";

	MetodosCuenta metodosCuenta = new MetodosCuenta();
	MetodosCliente metodosCliente = new MetodosCliente();

	@Test
	void recogerCuentaTest() {

		int numeroCuentaNuevo = 900;

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "23361399T";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";

		ArrayList<Cuenta> listaCuenta;
		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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
					"Cuenta [numeroCuenta=900, cliente=Cliente [animal=null, nombre=Ander, apellido=Perex, dni=23361399T, direccion=Mi casa, contrasenya=hola]]");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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
		int numeroCuentaNuevo = 900;
		String dniNuevoCuenta = "23361399T";

		Cuenta cuentaNueva = new Cuenta();
		cuentaNueva.setNumeroCuenta(numeroCuentaNuevo);

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "23361399T";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";


		try {
			MetodosGenerales MetodosGenerales = new MetodosGenerales();
			MetodosGenerales.comprobarDni(dniNuevo);
			try {

				Connection conexion;
				conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
				Statement resul = conexion.createStatement();

				resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
						+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo
						+ "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
						+ direccionNuevo + "');");

				metodosCuenta.insertarCuenta(cuentaNueva, dniNuevo);
				try {
					conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
					Statement sacaCliente = conexion.createStatement();
					String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where dni = '" + dniNuevoCuenta
							+ " ';";
					ResultSet resul2 = sacaCliente.executeQuery(sql);
					while (resul2.next()) {

						assertEquals(dniNuevo, resul2.getString(dni));
						assertEquals(numeroCuentaNuevo, resul2.getInt(numeroCuenta));
					}

					conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
					Statement resul3 = conexion.createStatement();

					resul3.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '"
							+ dniNuevo + "';");

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (NumberFormatException | DniInvalidoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void eliminarCuentaTest() {
		int numeroCuentaNuevo = 900;

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "23361399T";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";


		try {

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
					+ "VALUES( '" + numeroCuentaNuevo + "' , '" + dniNuevo + "');");

			metodosCuenta.eliminarCuenta(numeroCuentaNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where NumeroCuenta = '" + numeroCuentaNuevo
					+ " ';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul3.isBeforeFirst(), false);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
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

		int numeroCuentaNuevo = 900;
		String[] dniNuevoCuenta = { "X3096746A" };
		String dniUpadate = "X3096746A";

		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "48038965S";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		// -------------------------------------------//
		Cliente clienteNuevo = new Cliente();
		clienteNuevo.setDni("48038965S");
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		Cuenta cuentaNueva = new Cuenta();
		cuentaNueva.setNumeroCuenta(numeroCuentaNuevo);

		String column[] = { "DNI" };

		try {
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CUENTA + "`" + "(`NumeroCuenta`, `DNI`) "
					+ "VALUES( '" + numeroCuentaNuevo + "' , '" + dniNuevo + "');");

			int cont = 0;
			do {
				conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
				String sql = "UPDATE cliente set " + column[cont] + " = " + "'" + dniNuevoCuenta[cont] + "'"
						+ " where DNI = " + "'" + clienteNuevo.getDni() + "'";
				Statement statement = conexion.createStatement();
				statement.executeUpdate(sql);
				cont++;
			} while (cont < column.length);

			metodosCuenta.updateCuenta(column, dniNuevoCuenta, cuentaNueva);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CUENTA + " where NumeroCuenta = '" + numeroCuentaNuevo
					+ " ';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(dniNuevoCuenta[0].toString(), resul3.getString(dni));
			}

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul4 = conexion.createStatement();

			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniUpadate + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

}
