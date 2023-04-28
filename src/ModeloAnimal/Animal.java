package ModeloAnimal;

import ModeloPerfil.Cliente;

public abstract class Animal {
	protected String nombreAnimal;
	protected int idAnimal;
	protected int edad;
	protected String especie;
	protected String sexo;
	protected Cliente cliente;
	
	public Animal(String nombreAnimal, int idAnimal, int edad, String especie, String sexo,

			Cliente cliente) {
		this.nombreAnimal = nombreAnimal;
		this.idAnimal = idAnimal;
		this.edad = edad;
		this.especie = especie;
		this.sexo = sexo;
		this.cliente = cliente;
	}
	
	public Animal() {
		
	}

	public String getNombreAnimal() {
		return nombreAnimal;
	}

	public void setNombreAnimal(String nombreAnimal) {
		this.nombreAnimal = nombreAnimal;
	}

	public int getIdAnimal() {
		return idAnimal;
	}

	public void setIdAnimal(int idAnimal) {
		this.idAnimal = idAnimal;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public abstract boolean equals(Object obj);

}
