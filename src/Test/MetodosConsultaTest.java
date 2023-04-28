package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controlador.MetodosConsulta;
import Modelo.Consulta;

class MetodosConsultaTest {

	MetodosConsulta metodosConsulta = new MetodosConsulta();
	
	@Test
	void test() throws SQLException {
		
		ArrayList<Consulta> listaConsultas= metodosConsulta.recogerConsulta();
		String resultado = listaConsultas.get(0).toString();
		assertEquals(resultado, "Consulta [idConsulta=1, precio=5.0, animal=Loro [, nombreAnimal=loro, idAnimal=4, edad=2, especie=loro, sexo=m, cliente=null], empleado=Empleado [antiguedad=5, salario=1300.0, especializacion=limpieza, nombre=Sandra Maria , apellido=Alcaide, dni=61556765W, direccion=Carrer Laura, 373, Bajos, contrasenya=13], fecha=2023-04-24, hora=10:55:36]");
	}

}
