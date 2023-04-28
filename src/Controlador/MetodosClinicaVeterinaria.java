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

	public ArrayList<ClinicaVeterinaria> recogerClinicaVeterinaria() throws SQLException {

		ArrayList<ClinicaVeterinaria> listaClinicaVeterinaria = new ArrayList<ClinicaVeterinaria>();
		Connection conexion;
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
}
