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
import Controlador.MetodosAdopcion;
import Modelo.Adopcion;

class MetodosAdopcionTest extends ManagerAbstract {

	MetodosAdopcion metodosAdopcion = new MetodosAdopcion();

	String cCodAdopcion = "CodAdopcion";
	String cPrecioTotal = "PrecioTotal";
	String cCodgestionAnimal = "IdAnimal";
	String cFecha = "Fecha";
	String cCodMascota = "CodMascota";
	String cDNI = "DNI";

	// --------------------VariablesNecesariasParaElTest--------------------/
	String fechaDate = "2023-02-13";

	int codAdopcionNuevo = 900;
	float precioTotalNuevo = 416;
	Date fechaNuevo = Date.valueOf(fechaDate);

	String dniNuevo = "X7650931G";

	String nombreNuevo = "Ander";
	String apellidosNuevo = "Perex";
	String direccionNuevo = "Mi casa";
	String contrasenyaNuevo = "hola";

	int codMascotaNuevo = 900;
	String especieMascotaNuevo = "Perro";
	int stockMascotaNuevo = 10;
	float precioMascotaNuevo = 50;

	String[] precioTotalUpdate = { "416" };
	String column[] = { "PrecioTotal" };
	float precioTotalUpdatear = 416;

	Connection conexion;

	@Test
	void recogerAnimalAdoptadosTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalNuevo + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '" + dniNuevo
					+ "');");

			ArrayList<Adopcion> listaAdopcionAnimal = metodosAdopcion.recogerAnimalAdoptados();

			String resultado = "";

			for (Adopcion adopcion : listaAdopcionAnimal) {
				if (adopcion.getCodAdopcion() == codAdopcionNuevo) {
					int pos = listaAdopcionAnimal.indexOf(adopcion);
					resultado = listaAdopcionAnimal.get(pos).toString();
				}
			}

			assertEquals(resultado,
					"Adopcion [codAdopcion=900, precioTotal=416.0, fecha=2023-02-13, listaMascota=[Mascota [codMascota=3, especie=Loro, stock=5, precio=35.0], Mascota [codMascota=2, especie=Gato, stock=19, precio=15.0], Mascota [codMascota=900, especie=Perro, stock=10, precio=50.0]], cliente=Cliente [animal=null, nombre=Ander, apellido=Perex, dni=X7650931G, direccion=Mi casa, contrasenya=hola]]");

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void insertarAnimalAdoptadosTest() {

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Adopcion adopcion = new Adopcion();

			adopcion.setCodAdopcion(codAdopcionNuevo);
			adopcion.setFecha(fechaNuevo);
			adopcion.setPrecioTotal(precioTotalNuevo);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			metodosAdopcion.insertarAnimalAdoptado(adopcion, dniNuevo, codMascotaNuevo);

			Statement sacaAnimal = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " order by CodAdopcion DESC limit 1 ;";
			ResultSet resul3 = sacaAnimal.executeQuery(sql);
			while (resul3.next()) {

				assertEquals(precioTotalNuevo, resul3.getFloat(cPrecioTotal));
				assertEquals(fechaNuevo, resul3.getDate(cFecha));
				assertEquals(codMascotaNuevo, resul3.getInt(cCodMascota));
				assertEquals(dniNuevo, resul3.getString(cDNI));
			}

			Statement resul4 = conexion.createStatement();
			resul4.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void eliminarAnimalAdoptadosTest() {

		try {
			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Statement resul = conexion.createStatement();
			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalNuevo + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '" + dniNuevo
					+ "');");

			metodosAdopcion.eliminarAnimalAdoptado(codAdopcionNuevo);

			Statement sacaEmpleado = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " where CodAdopcion = '" + codAdopcionNuevo
					+ "';";
			ResultSet resul4 = sacaEmpleado.executeQuery(sql);
			assertEquals(resul4.isBeforeFirst(), false);

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	void updateAnimalAdoptadosTest() {

		try {

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);

			Adopcion adopcion = new Adopcion();

			adopcion.setCodAdopcion(codAdopcionNuevo);
			adopcion.setFecha(fechaNuevo);
			adopcion.setPrecioTotal(513);

			Statement resul = conexion.createStatement();

			resul.executeUpdate("Insert into `" + ManagerAbstract.TABLE_CLIENTE + "`"
					+ "(`DNI`, `Nombre`, `Apellidos`, `Contraseña`, `Direccion`) " + "VALUES( '" + dniNuevo + "' , '"
					+ nombreNuevo + "' , '" + apellidosNuevo + "' , '" + contrasenyaNuevo + "' , '" + direccionNuevo
					+ "');");

			Statement resul2 = conexion.createStatement();
			resul2.executeUpdate("Insert into `" + ManagerAbstract.TABLE_MASCOTA + "`"
					+ "(`CodMascota`, `Especie`, `Stock`, `Precio`) " + "VALUES( '" + codMascotaNuevo + "' , '"
					+ especieMascotaNuevo + "' , '" + stockMascotaNuevo + "' , '" + precioMascotaNuevo + "');");

			conexion = DriverManager.getConnection(DBUtils.URL, DBUtils.USER, DBUtils.PASS);
			Statement resul3 = conexion.createStatement();
			resul3.executeUpdate("Insert into `" + ManagerAbstract.TABLE_ADOPCION + "`"
					+ "(`CodAdopcion`, `PrecioTotal`, `Fecha`, `CodMascota`, `DNI`) " + "VALUES( '" + codAdopcionNuevo
					+ "' , '" + precioTotalUpdatear + "' , '" + fechaNuevo + "' , '" + codMascotaNuevo + "' , '"
					+ dniNuevo + "');");

			metodosAdopcion.updateAnimalADoptado(column, precioTotalUpdate, adopcion);

			Statement sacaCliente = conexion.createStatement();
			String sql = "select * from " + ManagerAbstract.TABLE_ADOPCION + " where CodAdopcion = '" + codAdopcionNuevo
					+ " ';";
			ResultSet resul4 = sacaCliente.executeQuery(sql);
			while (resul4.next()) {
				assertEquals(precioTotalNuevo, resul4.getFloat(cPrecioTotal));
			}

			Statement resul5 = conexion.createStatement();
			resul5.executeUpdate(
					"DELETE FROM `" + ManagerAbstract.TABLE_CLIENTE + "`" + "WHERE cliente.DNI = '" + dniNuevo + "';");

			Statement resul6 = conexion.createStatement();
			resul6.executeUpdate("DELETE FROM `" + ManagerAbstract.TABLE_MASCOTA + "`" + "WHERE CodMascota = '"
					+ codMascotaNuevo + "';");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
