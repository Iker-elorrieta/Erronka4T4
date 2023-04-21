package ModeloPerfil;

import java.util.Arrays;
import java.util.Objects;

import ModeloAnimal.Animales;

public class Cliente extends Perfil {

	private Animales[] animales;

	public Cliente(String nombre, String apellido, String dni, String direccion, String contrasenya, Animales[] animales) {
		super(nombre, apellido, dni, direccion, contrasenya);
		// TODO Auto-generated constructor stub
		this.animales = animales;
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
		return "Cliente [animales=" + Arrays.toString(animales) + ", nombre=" + nombre + ", apellido=" + apellido
				+ ", dni=" + dni + ", direccion=" + direccion + ", contrasenya=" + contrasenya + "]";
	}



}
