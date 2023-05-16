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
import Controlador.MetodosAnimal;
import Controlador.MetodosCliente;
import ModeloPerfil.Cliente;

class MetodosClienteTest extends ManagerAbstract {
	final String nombreCliente = "Nombre";
	final String apellido = "Apellidos";
	final String dni = "DNI";
	final String direccion = "Direccion";
	final String contrasenya = "Contraseña";
	// -------------------------------------------//
	String especie = "";
	final String perro = "Perro";
	final String gato = "Gato";
	final String loro = "Loro";
	final String pez = "Pez";
	// -------------------------------------------//
	final String idAnimal = "IdAnimal";
	final String nombreAnimal = "Nombre";
	final String edad = "Edad";
	final String especieTabla = "especie";
	final String sexo = "Sexo";

	MetodosCliente metodosCliente = new MetodosCliente();
	MetodosAnimal metodosAnimal = new MetodosAnimal();

	// --------------------VariablesNecesariasParaElTest--------------------/
	String nombreNuevo = "Ander";
	String apellidosNuevo = "Perex";
	String dniNuevo = "22761890D";
	String direccionNuevo = "Mi casa";
	String contrasenyaNuevo = "hola";
	String column[] = { "Nombre" };
	String[] nombreNuevoUpdateado = { "Ander" };
	String nombreUpdatear = "hector";

	Connection conexion;

	@Test
	void recogerClienteYSusAnimalesTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();

			String resultado = "";

			for (Cliente cliente : listaCliente) {
				if (cliente.getDni().equals(dniNuevo)) {
					int pos = listaCliente.indexOf(cliente);
					resultado = listaCliente.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Cliente [animal=null, nombre=Ander, apellido=Perex, dni=22761890D, direccion=Mi casa, contrasenya=hola]");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}

	@Test
	void insertarClientetest() {

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);
		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			metodosCliente.insertarCliente(clienteNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLIENTE + " where dni = '" + dniNuevo + " ';";
			ResultSet resul = sacaCliente.executeQuery(sql);
			while (resul.next()) {

				assertEquals(dniNuevo, resul.getString(dni));
				assertEquals(nombreNuevo, resul.getString(nombreCliente));
				assertEquals(apellidosNuevo, resul.getString(apellido));
				assertEquals(direccionNuevo, resul.getString(direccion));
				assertEquals(contrasenyaNuevo, resul.getString(contrasenya));

			}

			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarClienteTest() {

		try {
			Connection conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			metodosCliente.eliminarCliente(dniNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLIENTE + " where dni = '" + dniNuevo + " ';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul2.isBeforeFirst(), false);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void UpdateClienteTest() {

		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre("hector");
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreUpdatear + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			metodosCliente.updateCliente(column, nombreNuevoUpdateado, clienteNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_CLIENTE + " where dni = '" + dniNuevo + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(nombreNuevoUpdateado[0], resul2.getString(nombreCliente));
			}

			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
