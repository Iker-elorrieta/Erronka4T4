package ModeloAnimal;

public class Mascota {

	private int codMascota;
	private String especie;
	private int stock;
	private float precio;

	public Mascota(int codMascota, String especie, int stock, float precio) {
		this.codMascota = codMascota;
		this.especie = especie;
		this.stock = stock;
		this.precio = precio;
	}

	public Mascota() {

	}

	public int getCodMascota() {
		return codMascota;
	}

	public void setCodMascota(int codMascota) {
		this.codMascota = codMascota;
	}

	public String getEspecie() {
		return especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mascota other = (Mascota) obj;
		return codMascota == other.codMascota;
	}

	@Override
	public String toString() {
		return "Mascota [codMascota=" + codMascota + ", especie=" + especie + ", stock=" + stock + ", precio=" + precio
				+ "]";
	}

}