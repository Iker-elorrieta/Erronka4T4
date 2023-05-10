package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

public class MetodosEmpleado extends ManagerAbstract {

	final String nombreCliente = "Nombre";
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

	public ArrayList<Empleado> recogerEmpleado() throws SQLException {

		ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaEmpleado = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_EMPLEADO;
		ResultSet resul = sacaEmpleado.executeQuery(sql);
		while (resul.next()) {

			Empleado empleado = new Empleado();
			empleado.setNombre(resul.getString(nombreCliente));
			empleado.setApellido(resul.getString(apellido));
			empleado.setDni(resul.getString(dni));
			empleado.setDireccion(resul.getString(direccion));
			empleado.setContrasenya(resul.getString(contrasenya));
			empleado.setAntiguedad(resul.getInt(antiguedad));
			empleado.setSalario(resul.getFloat(salario));

			especializacion = resul.getString(especializacionTabla);
			if (especializacion.equalsIgnoreCase(perros)) {
				empleado.setEspecializacion(Especialidad.Perros);
			} else if (especializacion.equalsIgnoreCase(gatos)) {
				empleado.setEspecializacion(Especialidad.Gatos);
			} else if (especializacion.equalsIgnoreCase(loros)) {
				empleado.setEspecializacion(Especialidad.Loros);
			} else if (especializacion.equalsIgnoreCase(pez)) {
				empleado.setEspecializacion(Especialidad.Pez);
			} else if (especializacion.equalsIgnoreCase(limpieza)) {
				empleado.setEspecializacion(Especialidad.Limpieza);
			} else if(especializacion.equalsIgnoreCase(ventas)){
				empleado.setEspecializacion(Especialidad.Ventas);
			}else {
				empleado.setEspecializacion(Especialidad.Admin);
			}

			listaEmpleado.add(empleado);
		}
		return listaEmpleado;

	}

	public void insertarEmpleado(Empleado empleadoNuevo, int codClinica) throws SQLException {

		String nombre = "";
		String apellidos = "";
		String dni = "";
		String direccion = "";
		String contrasenya = "";
		float salario = 0;
		int antiguedad = 0;
		Especialidad especializacion = null;

		Empleado empleado = empleadoNuevo;

		dni = empleado.getDni();
		nombre = empleado.getNombre();
		apellidos = empleado.getApellido();
		contrasenya = empleado.getContrasenya();
		direccion = empleado.getDireccion();
		salario = empleado.getSalario();
		antiguedad = empleado.getAntiguedad();
		especializacion = empleado.getEspecializacion();

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
				+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
				+ "VALUES( '" + dni + "' , '" + nombre + "' , '" + apellidos + "' , '" + contrasenya + "' , '"
				+ direccion + "' , '" + salario + "' , '" + antiguedad + "' , '" + especializacion + "' , '"
				+ codClinica + "');");

	}

	public void eliminarEmpleado(String dni) throws SQLException {

		Connection conexion;
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate(
				"DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '" + dni + "';");

	}

	public void updateEmpleado(String[] nombreColumna, String[] UpdateColumna, Empleado empleado) throws SQLException {
		Connection conexion;
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE empleado set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where DNI = " + "'" + empleado.getDni() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}

}
