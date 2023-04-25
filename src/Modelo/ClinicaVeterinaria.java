package Modelo;

import java.util.Arrays;

import ModeloPerfil.Empleado;

public class ClinicaVeterinaria {
	private String ubicacion;
	private int codVeterinaria;
	private Empleado[] empleados;
	private Producto[] productos;

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public int getCodVeterinaria() {
		return codVeterinaria;
	}

	public void setCodVeterinaria(int codVeterinaria) {
		this.codVeterinaria = codVeterinaria;
	}


	public ClinicaVeterinaria(String ubicacion, int codVeterinaria, Empleado[] empleados, Producto[] productos) {
		super();
		this.ubicacion = ubicacion;
		this.codVeterinaria = codVeterinaria;
		this.empleados = empleados;
		this.productos = productos;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClinicaVeterinaria other = (ClinicaVeterinaria) obj;
		return codVeterinaria == other.codVeterinaria;
	}


	@Override
	public String toString() {
		return "ClinicaVeterinaria [ubicacion=" + ubicacion + ", codVeterinaria=" + codVeterinaria + ", empleados="
				+ Arrays.toString(empleados) + ", productos=" + Arrays.toString(productos) + "]";
	}

	public Empleado[] getEmpleados() {
		return empleados;
	}

	public void setEmpleados(Empleado[] empleados) {
		this.empleados = empleados;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}
}
