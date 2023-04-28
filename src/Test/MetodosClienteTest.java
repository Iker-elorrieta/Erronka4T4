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
import Controlador.MetodosGenerales;
import Excepciones.DniInvalidoException;
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
	
	@Test
	void recogerClienteYSusAnimalestest() throws SQLException {
		ArrayList<Cliente> listaClientes = metodosCliente.recogerClienteYSusAnimales();
		String resultado = listaClientes.get(1).toString();
		assertEquals(resultado,
				"Cliente [animal=[Perro [, nombreAnimal=Agallas, idAnimal=1, edad=5, especie=Perro, sexo=H, cliente=null], Gato [ nombreAnimal=Garfield, idAnimal=2, edad=12, especie=Gato, sexo=H, cliente=null]], nombre=juan, apellido=Galera Frias, dni=45894650J, direccion=Travesía Gamboa, 618, 4º C, contrasenya=1234567890]");
	}

	@Test
	void insertarClientetest() {
		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		int cantidadMascotasNuevo = 0;
		
		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);
		
		try {
			MetodosGenerales.comprobarDni(dniNuevo);
			try {
				metodosCliente.insertarCliente(clienteNuevo);
				Connection conexion;
					try {
						conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
						Statement sacaCliente = conexion.createStatement();
						String sql = "select * from " + ManagerAbstract.TABLE_CLIENTE + " where dni = '" + dniNuevo + " ';";
						ResultSet resul = sacaCliente.executeQuery(sql);
						while (resul.next()) {
				
							assertEquals(dniNuevo, resul.getString(dni));
							assertEquals(nombreNuevo, resul.getString(nombreCliente));
							assertEquals(apellidosNuevo, resul.getString(apellido));
							assertEquals(direccionNuevo, resul.getString(direccion));
							assertEquals(contrasenyaNuevo, resul.getString(contrasenya));
							assertEquals(cantidadMascotasNuevo, resul.getInt(6));
							
							ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();
							metodosCliente.eliminarCliente(listaCliente, dniNuevo);
						}
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
	void eliminarClientetest() {
		String nombreNuevo = "Ander";
		String apellidosNuevo = "Perex";
		String dniNuevo = "22761890D";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "hola";
		
		Cliente clienteNuevo = new Cliente();

		clienteNuevo.setDni(dniNuevo);
		clienteNuevo.setNombre(nombreNuevo);
		clienteNuevo.setApellido(apellidosNuevo);
		clienteNuevo.setContrasenya(contrasenyaNuevo);
		clienteNuevo.setDireccion(direccionNuevo);
		
		try {
			try {
				metodosCliente.insertarCliente(clienteNuevo);
				ArrayList<Cliente> listaCliente = metodosCliente.recogerClienteYSusAnimales();
				metodosCliente.eliminarCliente(listaCliente, dniNuevo);
				
				Connection conexion;
				conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
				Statement sacaCliente = conexion.createStatement();
				String sql = "DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dni + "';";

				
			} catch (DniInvalidoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			
	}
}
