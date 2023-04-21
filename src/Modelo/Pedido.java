package Modelo;

import java.sql.Time;
import java.util.Arrays;
import java.util.Date;
import ModeloPerfil.Cliente;

public class Pedido {
	private Producto[] productos;
	private Date fecha;
	private Time hora;
	private int cantidadProducto;
	private int codPedido;
	private Cliente cliente;
	private float preciototal;

	public Pedido(Producto[] productos, Date fecha, Time hora, int cantidadProducto, int codPedido, Cliente cliente,
			float preciototal) {
		super();
		this.productos = productos;
		this.fecha = fecha;
		this.hora = hora;
		this.cantidadProducto = cantidadProducto;
		this.codPedido = codPedido;
		this.cliente = cliente;
		this.preciototal = preciototal;
	}

	public Producto[] getProductos() {
		return productos;
	}

	public void setProductos(Producto[] productos) {
		this.productos = productos;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public int getCantidadProducto() {
		return cantidadProducto;
	}

	public void setCantidadProducto(int cantidadProducto) {
		this.cantidadProducto = cantidadProducto;
	}

	public int getCodPedido() {
		return codPedido;
	}

	public void setCodPedido(int codPedido) {
		this.codPedido = codPedido;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pedido other = (Pedido) obj;
		return codPedido == other.codPedido;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public float getPreciototal() {
		return preciototal;
	}

	public void setPreciototal(float preciototal) {
		this.preciototal = preciototal;
	}

	@Override
	public String toString() {
		return "Pedido [productos=" + Arrays.toString(productos) + ", fecha=" + fecha + ", hora=" + hora
				+ ", cantidadProducto=" + cantidadProducto + ", codPedido=" + codPedido + ", cliente=" + cliente
				+ ", preciototal=" + preciototal + "]";
	}

}
