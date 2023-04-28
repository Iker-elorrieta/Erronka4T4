package ModeloAnimal;

import java.util.Objects;

import ModeloPerfil.Cliente;

public class Perro extends Animal  implements Vacuna{

	public Perro(String nombreAnimal, int idAnimal, int edad, String especie, String sexo, Cliente cliente) {
		super(nombreAnimal, idAnimal, edad, especie, sexo, cliente);
		// TODO Auto-generated constructor stub
	}
	
	public Perro() {
		
	}

	@Override
	public String toString() {
		return "Perro [" + ", nombreAnimal=" + nombreAnimal + ", idAnimal=" + idAnimal + ", edad=" + edad + ", especie="
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
		Perro other = (Perro) obj;
		return Objects.equals(idAnimal, other.idAnimal);
	}

	@Override
	public float CosteVacuna(Animal animal) {
		// TODO Auto-generated method stub
		return 0;
	}

}
