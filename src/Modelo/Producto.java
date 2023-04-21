package Modelo;

public class Producto {

	private String nombreProducto;
	private float precio;
	private int stock;
	private int codProducto;

	public Producto(String nombreProducto, float precio, int stock, int codProducto) {
		this.nombreProducto = nombreProducto;
		this.precio = precio;
		this.stock = stock;
		this.codProducto = codProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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

	public int getCodProducto() {
		return codProducto;
	}

	public void setCodProducto(int codProducto) {
		this.codProducto = codProducto;
	}

	@Override
	public String toString() {
		return "Producto [nombreProducto=" + nombreProducto + ", precio=" + precio + ", stock=" + stock
				+ ", codProducto=" + codProducto + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return codProducto == other.codProducto;
	}

}
