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
import Controlador.MetodosEmpleado;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

class MetodosEmpleadoTest extends ManagerAbstract {

	MetodosEmpleado metodosEmpleado = new MetodosEmpleado();

	final String nombreEmpleado = "Nombre";
	final String apellido = "Apellidos";
	final String dni = "DNI";
	final String direccion = "Direccion";
	final String contrasenya = "Contraseña";
	// -------------------------------------------//
	final String antiguedad = "Antiguedad";
	final String salario = "salario";
	final String especializacionTabla = "Especializacion";
	final String codClinica = "CodClinica";
	// -------------------------------------------//
	String especializacion = "";
	// -------------------------------------------//
	final String perros = "Perros";
	final String gatos = "Gatos";
	final String loros = "Loros";
	final String pez = "Peces";
	final String limpieza = "Limpieza";
	final String ventas = "Ventas";

	@Test
	void recogerEmpleadoTest() {
		
		String nombreNuevo = "Juan";
		String apellidosNuevo = "Calvo";
		String dniNuevo = "45648945L";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;
		
		int codClinicaNuevo = 3;
		
		ArrayList<Empleado> listaEmpleado;
		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo + "' , '" + especializacionNuevo + "' , '"
					+ codClinicaNuevo + "');");
			
			listaEmpleado = metodosEmpleado.recogerEmpleado();
			
			String resultado = "";

			for (Empleado empleado : listaEmpleado) {
				if (empleado.getDni().equals(dniNuevo)) {
					int pos = listaEmpleado.indexOf(empleado);
					resultado = listaEmpleado.get(pos).toString();
				}
			}
			assertEquals(resultado, "Empleado [antiguedad=25, salario=5615.0, especializacion=Perros, nombre=Juan, apellido=Calvo, dni=45648945L, direccion=AveCesas, contrasenya=2684]");
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '" + dniNuevo + "';");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarEmpleadoTest() {

		String nombreNuevo = "Juan";
		String apellidosNuevo = "Calvo";
		String dniNuevo = "45648945L";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;

		Empleado empleadoNuevo = new Empleado();

		empleadoNuevo.setDni(dniNuevo);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre(nombreNuevo);
		empleadoNuevo.setSalario(salarioNuevo);

		try {
			metodosEmpleado.insertarEmpleado(empleadoNuevo, 1);

			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where DNI = '" + dniNuevo + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);
			while (resul.next()) {
				assertEquals(dniNuevo, resul.getString(dni));
				assertEquals(nombreNuevo, resul.getString(nombreEmpleado));
				assertEquals(apellidosNuevo, resul.getString(apellido));
				assertEquals(contrasenyaNuevo, resul.getString(contrasenya));
				assertEquals(direccionNuevo, resul.getString(direccion));
				assertEquals(salarioNuevo, resul.getFloat(salario));
				assertEquals(antiguedadNuevo, resul.getInt(antiguedad));
				assertEquals(especializacionNuevo.toString(), resul.getString(especializacionTabla));
				assertEquals(1, resul.getInt(codClinica));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul2 = conexion.createStatement();

			resul2.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void eliminarEmpleadoTest() {

		String nombreNuevo = "Juan";
		String apellidosNuevo = "Calvo";
		String dniNuevo = "45648945L";
		String direccionNuevo = "AveCesas";
		String contrasenyaNuevo = "2684";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;
		int codClinicaNuevo = 3;

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo + "' , '" + especializacionNuevo + "' , '"
					+ codClinicaNuevo + "');");

			metodosEmpleado.eliminarEmpleado(dniNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where DNI = '" + dniNuevo + "';";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul2.isBeforeFirst(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void UpdateClienteTest() {
		String[] nombreNuevo = { "Rene" };
		String nombreUpdatear = "Manuel";
		String apellidosNuevo = "Barrios";
		String dniNuevo = "79438242Z";
		String direccionNuevo = "Mi casa";
		String contrasenyaNuevo = "Adios";
		float salarioNuevo = 5615;
		int antiguedadNuevo = 25;
		Especialidad especializacionNuevo = Especialidad.Perros;
		String column[] = { "Nombre" };
		int codClinicaNuevo = 3;
		
		Empleado empleadoNuevo = new Empleado();
		
		empleadoNuevo.setDni(dniNuevo);
		empleadoNuevo.setAntiguedad(antiguedadNuevo);
		empleadoNuevo.setApellido(apellidosNuevo);
		empleadoNuevo.setContrasenya(contrasenyaNuevo);
		empleadoNuevo.setDireccion(direccionNuevo);
		empleadoNuevo.setEspecializacion(especializacionNuevo);
		empleadoNuevo.setNombre("Manuel");
		empleadoNuevo.setSalario(salarioNuevo);

		try {
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreUpdatear + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '"
					+ direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo + "' , '" + especializacionNuevo + "' , '"
					+ codClinicaNuevo + "');");
			
			
			metodosEmpleado.updateEmpleado(column, nombreNuevo, empleadoNuevo);

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where dni = '" + dniNuevo + " ';";
			ResultSet resul2 = sacaCliente.executeQuery(sql);
			while (resul2.next()) {
				assertEquals(nombreNuevo[0], resul2.getString(nombreEmpleado));
			}
			
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();

			resul3.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '" + dniNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
