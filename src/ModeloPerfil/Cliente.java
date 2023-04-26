package ModeloPerfil;

import java.util.Arrays;
import java.util.Objects;

import Modelo.Cuenta;
import ModeloAnimal.Animales;

public class Cliente extends Perfil {

	private Animales[] animales;
	private Cuenta cuenta;


	
	public Cliente(String nombre, String apellido, String dni, String direccion, String contrasenya,
			Animales[] animales, Cuenta cuenta) {
		super(nombre, apellido, dni, direccion, contrasenya);
		this.animales = animales;
		this.setCuenta(cuenta);
	}

	public Animales[] getAnimales() {
		return animales;
	}

	public void setAnimales(Animales[] animales) {
		this.animales = animales;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Perfil other = (Perfil) obj;
		return Objects.equals(dni, other.dni);
	}



	@Override
	public String toString() {
		return "Cliente [animales=" + Arrays.toString(animales) + ", cuenta=" + cuenta + ", nombre=" + nombre
				+ ", apellido=" + apellido + ", dni=" + dni + ", direccion=" + direccion + ", contrasenya="
				+ contrasenya + "]";
	}

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}



}
