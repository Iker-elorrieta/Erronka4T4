package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import ModeloAnimal.Animal;
import ModeloAnimal.Gato;
import ModeloAnimal.Loro;
import ModeloAnimal.Perro;
import ModeloAnimal.Pez;
import ModeloPerfil.Cliente;

public class MetodosCliente extends ManagerAbstract {
	final String nombreCliente = "Nombre";
	final String apellido = "Apellidos";
	final String dni = "DNI";
	final String direccion = "Direccion";
	final String contrasenya = "Contraseña";
	final String cantidadMascotas = "CantidadMascotas";
	// -------------------------------------------//
	String especie = "";
	final String perro = "Perros";
	final String gato = "Gatos";
	final String loro = "Loros";
	final String pez = "Pez";
	// -------------------------------------------//
	final String idAnimal = "IdAnimal";
	final String nombreAnimal = "Nombre";
	final String edad = "Edad";
	final String especieTabla = "especie";
	final String sexo = "Sexo";

	public ArrayList<Cliente> recogerClienteYSusAnimales() throws SQLException {

		ArrayList<Cliente> listaClientes = new ArrayList<Cliente>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaCliente = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_CLIENTE;
		ResultSet resul = sacaCliente.executeQuery(sql);
		while (resul.next()) {

			Cliente cliente = new Cliente();
			cliente.setNombre(resul.getString(nombreCliente));
			cliente.setApellido(resul.getString(apellido));
			cliente.setDni(resul.getString(dni));
			cliente.setDireccion(resul.getString(direccion));
			cliente.setContrasenya(resul.getString(contrasenya));

			ArrayList<Animal> listaAnimales = new ArrayList<Animal>();
			Statement sacaAnimal = conexion.createStatement();
			String sql2 = "select distinct * from " + ManagerAbstract.TABLE_ANIMAL + " join "
					+ ManagerAbstract.TABLE_CLIENTE + " on cliente.DNI = animal.DNI where cliente.DNI = '"
					+ resul.getString(dni) + "'";
			ResultSet resul2 = sacaAnimal.executeQuery(sql2);
			while (resul2.next()) {
				especie = resul2.getString(especieTabla);

				if (especie.equalsIgnoreCase(perro)) {
					Perro perro = new Perro();
					perro.setIdAnimal(resul2.getInt(idAnimal));
					perro.setNombreAnimal(resul2.getString(nombreAnimal));
					perro.setEdad(resul2.getInt(edad));
					perro.setEspecie(especie);
					perro.setSexo(resul2.getString(sexo));
					listaAnimales.add(perro);

				} else if (especie.equalsIgnoreCase(gato)) {
					Gato gato = new Gato();
					gato.setIdAnimal(resul2.getInt(idAnimal));
					gato.setNombreAnimal(resul2.getString(nombreAnimal));
					gato.setEdad(resul2.getInt(edad));
					gato.setEspecie(especie);
					gato.setSexo(resul2.getString(sexo));
					listaAnimales.add(gato);

				} else if (especie.equalsIgnoreCase(loro)) {
					Loro loro = new Loro();
					loro.setIdAnimal(resul2.getInt(idAnimal));
					loro.setNombreAnimal(resul2.getString(nombreAnimal));
					loro.setEdad(resul2.getInt(edad));
					loro.setEspecie(especie);
					loro.setSexo(resul2.getString(sexo));
					listaAnimales.add(loro);
				} else {
					Pez pez = new Pez();
					pez.setIdAnimal(resul2.getInt(idAnimal));
					pez.setNombreAnimal(resul2.getString(nombreAnimal));
					pez.setEdad(resul2.getInt(edad));
					pez.setEspecie(especie);
					pez.setSexo(resul2.getString(sexo));
					listaAnimales.add(pez);
				}
				cliente.setAnimal(listaAnimales);
			}

			listaClientes.add(cliente);
		}

		return listaClientes;

	}

	public void insertarCliente(Cliente clienteNuevo) throws SQLException{

		String nombre = "";
		String apellidos = "";
		String dni = "";
		String direccion = "";
		String contrasenya = "";

		Cliente cliente = clienteNuevo;

		dni = cliente.getDni();
		nombre = cliente.getNombre();
		apellidos = cliente.getApellido();
		contrasenya = cliente.getContrasenya();
		direccion = cliente.getDireccion();
		
		
		Connection conexion;

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
				+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dni + "' , '" + nombre
				+ "' , '" + apellidos + "' , '" + contrasenya + "' , '" + direccion + "');");
		
	}

	public void eliminarCliente(String dni) throws SQLException {
		
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dni + "';");

	}

	public void updateCliente(String[] nombreColumna, String[] UpdateColumna, Cliente cliente) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE cliente set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where DNI = " + "'" + cliente.getDni() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
}
