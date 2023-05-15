package Modelo;

public class Producto extends ObjetosComprables{

	private String nombreProducto;
	private int codProducto;

	public Producto(String nombreProducto, float precio, int stock, int codProducto) {
		super(precio, stock);
		this.nombreProducto = nombreProducto;
		this.codProducto = codProducto;
	}
	
	public Producto() {
		
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
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
