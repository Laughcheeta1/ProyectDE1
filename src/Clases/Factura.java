package Clases;

import java.io.Serializable;

public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String fecha;
	private String numero;
	private String idCliente;
	private String posicionSilla;
	private double precioPagado;
	private String metodoPago;
	
	public Factura(String fecha, String numero, String idCliente, String posicionSilla, double precioPagado, String metodoPago)
	{
		this.fecha = fecha;
		this.numero = numero;
		this.idCliente = idCliente;
		this.posicionSilla = posicionSilla;
		this.precioPagado = precioPagado;
		this.metodoPago = metodoPago;
	}

	public String getNumero() {
		return numero;
	}

	public String getIdCliente() {
		return idCliente;
	}
	
	public String getPosicionSilla() {
		return posicionSilla;
	}

	public double getPrecioPagado() {
		return precioPagado;
	}
	
	public String getMetodoPago()
	{
		return metodoPago;
	}
	
	public String getFecha()
	{
		return this.fecha;
	}
	
}
