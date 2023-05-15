package Controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Modelo.ClinicaVeterinaria;
import ModeloPerfil.Empleado;
import ModeloPerfil.Especializacion.Especialidad;

/**
 * *MetodosClinicaVeterinaria esta clase contiene los metodos crud de la clase
 * ClinicaVeterinaria
 */
public class MetodosClinicaVeterinaria extends ManagerAbstract {

	final String ubicacion = "Ubicacion";
	final String codClinica = "CodClinica";
	// -------------------------------------------//
	final String nombreCliente = "Nombre";
	final String apellido = "Apellidos";
	final String dni = "DNI";
	final String direccion = "Direccion";
	final String contrasenya = "Contraseña";
	// -------------------------------------------//
	final String antiguedad = "Antiguedad";
	final String salario = "salario";
	final String especializacionTabla = "Especializacion";
	// -------------------------------------------//
	String especializacion = "";
	// -------------------------------------------//
	final String perros = "Perros";
	final String gatos = "Gatos";
	final String loros = "Loros";
	final String pez = "Peces";
	final String limpieza = "Limpieza";
	final String ventas = "Ventas";

	Connection conexion;
	/**
	*recogerClinicaVeterinaria recoge de la bd las clinicas en aun arraylist de clinicaveterinaria y lo devuelve renueva
	*/
	public ArrayList<ClinicaVeterinaria> recogerClinicaVeterinaria() throws SQLException {

		ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria = new ArrayList<ClinicaVeterinaria>();
		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement sacaClinicaVeterinaria = conexion.createStatement();
		String sql = "select * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA;
		ResultSet resul = sacaClinicaVeterinaria.executeQuery(sql);
		while (resul.next()) {
			ClinicaVeterinaria clinicaVeterinaria = new ClinicaVeterinaria();
			clinicaVeterinaria.setUbicacion(resul.getString(ubicacion));
			clinicaVeterinaria.setCodVeterinaria(resul.getInt(codClinica));

			ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
			Statement sacaEmpleado = conexion.createStatement();
			String sql2 = "select distinct * from " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " join "
					+ ManagerAbstract.TABLE_EMPLEADO
					+ " on clinica.CodClinica = empleado.CodClinica where clinica.CodClinica = '"
					+ resul.getInt(codClinica) + "'";
			ResultSet resul2 = sacaEmpleado.executeQuery(sql2);
			while (resul2.next()) {

				Empleado empleado = new Empleado();
				empleado.setNombre(resul2.getString(nombreCliente));
				empleado.setApellido(resul2.getString(apellido));
				empleado.setDni(resul2.getString(dni));
				empleado.setDireccion(resul2.getString(direccion));
				empleado.setContrasenya(resul2.getString(contrasenya));
				empleado.setAntiguedad(resul2.getInt(antiguedad));
				empleado.setSalario(resul2.getFloat(salario));

				especializacion = resul2.getString(especializacionTabla);
				if (especializacion.equalsIgnoreCase(perros)) {
					empleado.setEspecializacion(Especialidad.Perros);
				} else if (especializacion.equalsIgnoreCase(gatos)) {
					empleado.setEspecializacion(Especialidad.Gatos);
				} else if (especializacion.equalsIgnoreCase(loros)) {
					empleado.setEspecializacion(Especialidad.Loros);
				} else if (especializacion.equalsIgnoreCase(pez)) {
					empleado.setEspecializacion(Especialidad.Pez);
				} else if (especializacion.equalsIgnoreCase(limpieza)) {
					empleado.setEspecializacion(Especialidad.limpieza);
				} else {
					empleado.setEspecializacion(Especialidad.ventas);
				}
				listaEmpleado.add(empleado);
			}
			clinicaVeterinaria.setEmpleados(listaEmpleado);
			listaClinicaVeterinaria.add(clinicaVeterinaria);
		}
		return listaClinicaVeterinaria;
	}
	/**
	*insertarClinicaVeterinaria inserta en la bd una clinica
	*/
	public void insertarClinicaVeterinaria(ClinicaVeterinaria clinicaVeterinariaNueva) throws SQLException {

		int codVeterinaria = 0;
		String ubicacion = "";

		ClinicaVeterinaria clinicaVeterinaria = clinicaVeterinariaNueva;

		codVeterinaria = clinicaVeterinaria.getCodVeterinaria();
		ubicacion = clinicaVeterinaria.getUbicacion();

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
				+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codVeterinaria + "','" + ubicacion + "');");
	}
	/**
	*eliminarClinicaVeterinaria elimina en la bd una clinica
	*/
	public void eliminarClinicaVeterinaria(int codVeterinaria) throws SQLException {

		conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
		Statement resul = conexion.createStatement();

		resul.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = " + "'"
				+ codVeterinaria + "'");
	}
	/**
	*updateClinicaVeterinaria renueva  los datos de un cliente de la bd
	*/
	public void updateClinicaVeterinaria(String[] nombreColumna, String[] UpdateColumna,
			ClinicaVeterinaria clinicaVeterinaria) throws SQLException {
		int cont = 0;
		do {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			String sql = "UPDATE clinica set " + nombreColumna[cont] + " = " + "'" + UpdateColumna[cont] + "'"
					+ " where CodClinica = " + "'" + clinicaVeterinaria.getCodVeterinaria() + "'";
			Statement statement = conexion.createStatement();
			statement.executeUpdate(sql);
			cont++;
		} while (cont < nombreColumna.length);
	}
}
