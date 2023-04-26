package Test;


import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import Modelo.Cuenta;
import ModeloAnimal.Animales;
class CuentaTest {
	String numeroCuenta ="121";
	String numeroCuenta0="222";
	String contrasenya = "1";

	// ---------------------------------------------------//
	Animales[] animales;


	Cuenta cuentaTest = new Cuenta(numeroCuenta,contrasenya);
	Cuenta cuentaTest1 = new Cuenta(numeroCuenta,contrasenya);
	Cuenta cuentaTest2 = null;
	Cuenta cuentaTest3 = new Cuenta(numeroCuenta,contrasenya);
	@Test
	void test() {
		cuentaTest.setContrasenya(contrasenya);
		cuentaTest.setNumeroCuenta(numeroCuenta);
		
		assertEquals(cuentaTest.getContrasenya(),contrasenya);
		assertEquals(cuentaTest.getNumeroCuenta(),numeroCuenta);
		
		assertEquals(cuentaTest.toString(),"Cuenta [numeroCuenta=" + numeroCuenta + ", contrasenya=" + contrasenya + "]");
		
		assertTrue(cuentaTest.equals(cuentaTest1));
		assertFalse(cuentaTest.equals(cuentaTest2));
		assertTrue(cuentaTest.equals(cuentaTest));
		assertTrue(cuentaTest.equals(cuentaTest3));
		assertFalse(cuentaTest.equals(numeroCuenta.getClass()));
		
	
		
	}

}
