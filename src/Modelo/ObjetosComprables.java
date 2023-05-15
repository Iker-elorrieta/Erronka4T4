package Modelo;

public abstract class ObjetosComprables {
	
	protected float precio;
	protected int stock;
	
	public ObjetosComprables(float precio, int stock) {
		this.precio = precio;
		this.stock = stock;
	}

	public ObjetosComprables() {
		
	}
	
	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
}
