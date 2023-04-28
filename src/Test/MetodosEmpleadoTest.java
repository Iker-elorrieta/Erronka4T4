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
		ArrayList<Empleado> listaEmpleado;
		try {
			listaEmpleado = metodosEmpleado.recogerEmpleado();
			String resultado = listaEmpleado.get(0).toString();
			assertEquals(resultado,
					"Empleado [antiguedad=4, salario=1300.0, especializacion=limpieza, nombre=Hilario, apellido=Quintana, dni=05491864Q, direccion=Avenida Cotto, 747, 2º E, contrasenya=1]");
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
			
			ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();
			metodosEmpleado.eliminarEmpleado(listaEmpleado, dniNuevo);
			
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
			ArrayList<Empleado> listaEmpleado = metodosEmpleado.recogerEmpleado();
			metodosEmpleado.eliminarEmpleado(listaEmpleado, dniNuevo);
			
			Connection conexion;
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where DNI = '" + dniNuevo + "';";
			ResultSet resul = sacaEmpleado.executeQuery(sql);

			assertEquals(resul.isBeforeFirst(), false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

		
	}
}
