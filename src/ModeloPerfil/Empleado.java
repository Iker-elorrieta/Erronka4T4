package ModeloPerfil;

import java.util.Objects;

public class Empleado extends Perfil implements Especializacion {


	private int antiguedad;
	private float salario;
	private Especialidad especializacion;

	public Empleado(String nombre, String apellido, String dni, String direccion, String contrasenya, int antiguedad, float salario, Especialidad especializacion) {
		super(nombre, apellido, dni, direccion, contrasenya);
		// TODO Auto-generated constructor stub
		this.antiguedad = antiguedad;
		this.salario = salario;
		this.especializacion = especializacion;
	}
	
	public int getAntiguedad() {
		return antiguedad;
	}

	public void setAntiguedad(int antiguedad) {
		this.antiguedad = antiguedad;
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
	public float calcularSalario(Especialidad especialidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float bonoAntiguedad(int anyos, Especialidad especialidad) {
		// TODO Auto-generated method stub
		return 0;
	}

	public float getSalario() {
		return salario;
	}

	public void setSalario(float salario) {
		this.salario = salario;
	}

	public Especialidad getEspecializacion() {
		return especializacion;
	}

	public void setEspecializacion(Especialidad especializacion) {
		this.especializacion = especializacion;
	}

	@Override
	public String toString() {
		return "Empleado [antiguedad=" + antiguedad + ", salario=" + salario + ", especializacion=" + especializacion
				+ ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + ", direccion=" + direccion
				+ ", contrasenya=" + contrasenya + "]";
	}





}
