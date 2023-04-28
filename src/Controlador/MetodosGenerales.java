package Controlador;

import Excepciones.DniInvalidoException;


public class MetodosGenerales {
	
	public static void comprobarDni(String dni) throws DniInvalidoException ,NumberFormatException {
		final int longitudDNI = 9;
		final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";

		if (dni.length() != longitudDNI) {
			throw new DniInvalidoException("El DNI debe tener una longitud de " + longitudDNI + " caracteres");
		}

		String numerosDni = dni.substring(0, 8);
		char letraDni = dni.charAt(8);

		int numeros = Integer.parseInt(numerosDni);
		int resto = numeros % 23;
		char letraCalculada = LETRAS_DNI.charAt(resto);

		if (letraCalculada != letraDni) {	
			throw new DniInvalidoException("La letra del DNI no es correcta");
		}
	}
	
}
