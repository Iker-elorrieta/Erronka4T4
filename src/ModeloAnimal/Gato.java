package ModeloAnimal;

import java.util.Objects;

import ModeloPerfil.Cliente;

public class Gato extends Animal {

	public Gato(String nombreAnimal, int idAnimal, int edad, String especie, String sexo, Cliente cliente) {
		super(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
		// TODO Auto-generated constructor stub
	}
	
	public Gato() {
		
	}

	@Override
	public String toString() {
		return "Gato [" + " nombreAnimal=" + nombreAnimal + ", idAnimal=" + idAnimal + ", edad=" + edad + ", especie="
				+ especie + ", sexo=" + sexo + ", cliente=" + cliente + "]";
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Gato other = (Gato) obj;
		return Objects.equals(idAnimal, other.idAnimal);
	}

}
