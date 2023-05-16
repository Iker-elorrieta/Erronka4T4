package ModeloPerfil;

import java.util.ArrayList;

import java.util.Objects;

import ModeloAnimal.Animal;

public class Cliente extends Perfil {

	private ArrayList<Animal> animal;

	public Cliente(String nombre, String apellido, String dni, String direccion, String contrasenya, ArrayList<Animal> animal) {
		super(nombre, apellido, dni, direccion, contrasenya);
		// TODO Auto-generated constructor stub
		this.animal = animal;
	}
	
	public Cliente() {
		
	}
	

	public ArrayList<Animal> getAnimal() {
		return animal;
	}

	public void setAnimal(ArrayList<Animal> animal) {
		this.animal = animal;
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
		return "Cliente [animal=" + animal + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni
				+ ", direccion=" + direccion + ", contrasenya=" + contrasenya + "]";
	}





}
