package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controlador.MetodosGestion;
import Modelo.Gestion;

class MetodosGestionTest {

	MetodosGestion metodosGestion = new MetodosGestion();
	
	@Test
	void test() throws SQLException {
		ArrayList<Gestion> listaGestion = metodosGestion.recogerGestion();
		String resultado = listaGestion.get(0).toString();
		assertEquals(resultado,
				"Gestion [idGestion=1, cantidad=3, fecha=2023-04-24, hora=10:55:36, empleado=Empleado [antiguedad=7, salario=2000.0, especializacion=ventas, nombre=Gorka, apellido=De la Iglesia Perex, dni=22761891X, direccion=Carrer Marcos, 0, 6º A, contrasenya=3], productos=[Producto [nombreProducto=Correa, precio=2.0, stock=34, codProducto=1]]]");
	}

}
