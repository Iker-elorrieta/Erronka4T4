package Test;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Modelo.Cuenta;
import ModeloAnimal.Animales;
import ModeloPerfil.Cliente;

class CuentaTest {
	int numerotarjeta =121;
	int numerotarjeta0=222;
	String dni = "3687951L";
	String dni0 = "0";
	String nombre = "Raul";
	String apellido = "Monterel";
	String contrasenya = "dashdasijd55";
	String direccion = "Calle Ave del Paraiso 8";

	
	// ---------------------------------------------------//
	Animales[] animales;

	Cliente clienteTest = new Cliente(dni, nombre, apellido, direccion, contrasenya, animales);
	Cuenta cuentaTest = new Cuenta(numerotarjeta,clienteTest);
	Cuenta cuentaTest1 = new Cuenta(numerotarjeta,clienteTest);
	Cuenta cuentaTest2 = null;
	Cuenta cuentaTest3 = new Cuenta(numerotarjeta0,clienteTest);
	@Test
	void test() {
		cuentaTest.setCliente(clienteTest);
		cuentaTest.setNumeroTarjeta(numerotarjeta);
		assertEquals(cuentaTest.getCliente(),clienteTest);
		assertEquals(cuentaTest.getNumeroTarjeta(),numerotarjeta);
		assertEquals(cuentaTest.toString(),"Cuenta [numeroTarjeta=" + numerotarjeta + ", cliente=" + clienteTest + "]");
		
		assertTrue(cuentaTest.equals(cuentaTest1));
		assertFalse(cuentaTest.equals(cuentaTest2));
		assertTrue(cuentaTest.equals(cuentaTest));
		assertTrue(cuentaTest.equals(cuentaTest3));
		assertFalse(cuentaTest.equals(clienteTest.getClass()));
		
	}

}
