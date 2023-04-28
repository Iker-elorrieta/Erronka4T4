package ModeloAnimal;

import java.util.Objects;

import ModeloPerfil.Cliente;

public class Loro extends Animal {

	public Loro(String nombreAnimal, int idAnimal, int edad, String especie, String sexo,
			Cliente cliente) {
		super( nombreAnimal, idAnimal, edad, especie, sexo, cliente);
		// TODO Auto-generated constructor stub
	}
	
	public Loro() {
		
	}

	@Override
	public String toString() {
		return "Loro ["+ ", nombreAnimal=" + nombreAnimal + ", idAnimal=" + idAnimal
				+ ", edad=" + edad + ", especie=" + especie + ", sexo=" + sexo + ", cliente=" + cliente + "]";
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
		Loro other = (Loro) obj;
		return Objects.equals(idAnimal, other.idAnimal);
	}
	

}
