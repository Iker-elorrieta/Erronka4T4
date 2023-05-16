package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import B.D_Util.DBUtils;
import B.D_Util.ManagerAbstract;
import Controlador.MetodosGestion;
import Modelo.Gestion;
import ModeloPerfil.Especializacion.Especialidad;

class MetodosGestionTest extends ManagerAbstract {

	MetodosGestion metodosGestion = new MetodosGestion();
	// -------------------------------------------//
	final String codGestion = "CodGestion";
	final String cantidad = "Cantidad";
	final String fecha = "Fecha";
	final String hora = "Hora";
	final String empleado = "Empleado";
	final String productos = "Productos";
	// -------------------------------------------//
	final String nombreProducto = "Nombre";
	final String precio = "Precio";
	final String stock = "Stock";
	final String codProducto = "CodProducto";
	// -------------------------------------------//
	final String dni = "DNI";

	// --------------------VariablesNecesariasParaElTest--------------------//
	String fechaDate = "2023-02-13";
	String dniNuevo = "26285531G";
	int codGestionNueva = 900;
	Date fechaNueva = Date.valueOf(fechaDate);
	int cantidadNueva = 5;
	String[] cantidadUpdate = { "15" };
	int cantidadUpdateada = 15;
	int cantidadUpdatear = 2654;
	String column[] = { "Cantidad" };

	int codProductoNuevo = 900;
	String nombreNuevoProducto = "Bolsas";
	int stockNuevo = 16;
	float precioNuevo = 18;

	String nombreNuevoEmpleado = "Juan";
	String apellidosNuevo = "Calvo";
	String direccionNuevo = "AveCesas";
	String contrasenyaNuevo = "2684";
	float salarioNuevo = 5615;
	int antiguedadNuevo = 25;
	Especialidad especializacionNuevo = Especialidad.Perros;
	int codClinicaNuevo = 900;
	String ubicacionNueva = "Murcia";

	Connection conexion;

	@Test
	void recogerGestionTest() {

		ArrayList<Gestion> listaGestion;
		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu3 = conexion.createStatement();
			resu3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevoEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			listaGestion = metodosGestion.recogerGestion();

			String resultado = "";

			for (Gestion gestion : listaGestion) {
				if (gestion.getCodGestion() == codGestionNueva) {
					int pos = listaGestion.indexOf(gestion);
					resultado = listaGestion.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Gestion [codGestion=900, cantidad=5, fecha=2023-02-13, empleado=Empleado [antiguedad=25, salario=5615.0, especializacion=Perros, nombre=Juan, apellido=Calvo, dni=26285531G, direccion=AveCesas, contrasenya=2684], productos=[Producto [nombreProducto=Bolsas, precio=18.0, stock=16, codProducto=900]]]");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void insertarGestionTest() {

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(cantidadNueva);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu3 = conexion.createStatement();
			resu3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevoEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			metodosGestion.insertarGestion(gestionNueva, dniNuevo, codProductoNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul5 = sacaEmpleado.executeQuery(sql);
			while (resul5.next()) {
				assertEquals(codGestionNueva, resul5.getInt(codGestion));
				assertEquals(fechaNueva, resul5.getDate(fecha));
				assertEquals(cantidadNueva, resul5.getInt(cantidad));
				assertEquals(codProductoNuevo, resul5.getInt(codProducto));
				assertEquals(dniNuevo, resul5.getString(dni));
			}

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void eliminarGestionTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu3 = conexion.createStatement();
			resu3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevoEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			metodosGestion.eliminarGestion(codGestionNueva);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where gestion.CodGestion = '"
					+ codGestionNueva + "';";
			ResultSet resul5 = sacaEmpleado.executeQuery(sql);

			assertEquals(resul5.isBeforeFirst(), false);

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void updateGestionTest() {

		Gestion gestionNueva = new Gestion();

		gestionNueva.setCantidad(2654);
		gestionNueva.setCodGestion(codGestionNueva);
		gestionNueva.setFecha(fechaNueva);

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_PRODUCTOS + "`"
					+ "(`CodProducto`, `Nombre`, `Stock`, `Precio`) " + "VALUES( '" + codProductoNuevo + "' , '"
					+ nombreNuevoProducto + "' , '" + stockNuevo + "' , '" + precioNuevo + "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("INSERT INTO " + ManagerAbstract.TABLE_CLINICAVETERINARIA
					+ "(`CodClinica`, `Ubicacion`) VALUES ('" + codClinicaNuevo + "','" + ubicacionNueva + "');");

			Statement resu3 = conexion.createStatement();
			resu3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_EMPLEADO + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`, `Salario`, `Antiguedad`, `Especializacion`, `CodClinica`) "
					+ "VALUES( '" + dniNuevo + "' , '" + nombreNuevoEmpleado + "' , '" + apellidosNuevo + "' , '"
					+ contrasenyaNuevo + "' , '" + direccionNuevo + "' , '" + salarioNuevo + "' , '" + antiguedadNuevo
					+ "' , '" + especializacionNuevo + "' , '" + codClinicaNuevo + "');");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate("Insert into `" + ManagerAbstract.TABLE_GESTION + "`"
					+ "(`CodGestion`, `Fecha`, `Cantidad`, `CodProducto`, `DNI`) " + "VALUES( '" + codGestionNueva
					+ "' , '" + fechaNueva + "' , '" + cantidadNueva + "' , '" + codProductoNuevo + "' , '" + dniNuevo
					+ "');");

			metodosGestion.updateGestion(column, cantidadUpdate, gestionNueva);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_GESTION + " where CodGestion = '" + codGestionNueva
					+ " ';";
			ResultSet resul5 = sacaCliente.executeQuery(sql);
			while (resul5.next()) {
				assertEquals(cantidadUpdateada, resul5.getInt(cantidad));
			}

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_EMPLEADO + "`" + "WHERE empleado.DNI = '"
					+ dniNuevo + "';");

			Statement resul7 = conexion.createStatement();
			resul7.executeUpdate("DELETE FROM " + ManagerAbstract.TABLE_CLINICAVETERINARIA + " WHERE CodClinica = "
					+ "'" + codClinicaNuevo + "'");

			Statement resul8 = conexion.createStatement();
			resul8.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_PRODUCTOS + "`" + "WHERE CodProducto = '"
					+ codProductoNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
