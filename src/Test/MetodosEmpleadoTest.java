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

	// --------------------VariablesNecesariasParaElTest--------------------//
	String nombreNuevo = "Juan";
	String apellidosNuevo = "Calvo";
	String dniNuevo = "45648945L";
	String direccionNuevo = "AveCesas";
	String contrasenyaNuevo = "2684";
	float salarioNuevo = 5615;
	int antiguedadNuevo = 25;
	Especialidad especializacionNuevo = Especialidad.Perros;
	int codClinicaNuevo = 900;
	String ubicacionNueva = "Murcia";

	String[] nombreNuevoUpdateado = { "Rene" };
	String column[] = { "Nombre" };

	Connection conexion;

	@Test
	void recogerEmpleadoTest() {

		ArrayList<Empleado> listaEmpleado;
		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu2 = conexion.createStatement();
			resu2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			listaEmpleado = metodosEmpleado.recogerEmpleado();

			String resultado = "";

			for (Empleado empleado : listaEmpleado) {
				if (empleado.getDni().equals(dniNuevo)) {
					int pos = listaEmpleado.indexOf(empleado);
					resultado = listaEmpleado.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Empleado [antiguedad=25, salario=5615.0, especializacion=Perros, nombre=Juan, apellido=Calvo, dni=45648945L, direccion=AveCesas, contrasenya=2684]");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void insertarEmpleadoTest() {

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

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			metodosEmpleado.insertarEmpleado(empleadoNuevo, codClinicaNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where DNI = '" + dniNuevo + "';";
			ResultSet resu2 = sacaEmpleado.executeQuery(sql);
			while (resu2.next()) {
				assertEquals(dniNuevo, resu2.getString(dni));
				assertEquals(nombreNuevo, resu2.getString(nombreEmpleado));
				assertEquals(apellidosNuevo, resu2.getString(apellido));
				assertEquals(contrasenyaNuevo, resu2.getString(contrasenya));
				assertEquals(direccionNuevo, resu2.getString(direccion));
				assertEquals(salarioNuevo, resu2.getFloat(salario));
				assertEquals(antiguedadNuevo, resu2.getInt(antiguedad));
				assertEquals(especializacionNuevo.toString(), resu2.getString(especializacionTabla));
				assertEquals(codClinicaNuevo, resu2.getInt(codClinica));
			}

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void eliminarEmpleadoTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			metodosEmpleado.eliminarEmpleado(dniNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where DNI = '" + dniNuevo + "';";
			ResultSet resul3 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul3.isBeforeFirst(), false);

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void UpdateClienteTest() {

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

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevo + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			metodosEmpleado.updateEmpleado(column, nombreNuevoUpdateado, empleadoNuevo);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO + " where dni = '" + dniNuevo + " ';";
			ResultSet resul3 = sacaCliente.executeQuery(sql);
			while (resul3.next()) {
				assertEquals(nombreNuevoUpdateado[0], resul3.getString(nombreEmpleado));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
