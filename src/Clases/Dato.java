package Clases;

import java.io.Serializable;

public class Dato implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numeroTiquetesComprados;
	private int cantidadCambiosHorario;
	private double dineroTotalGenerado;
	
	public Dato()
	{
		this.numeroTiquetesComprados = 0;
		this.cantidadCambiosHorario = 0;
		this.numeroTiquetesComprados = 0;
	}

	public int getNumeroTiquetesComprados() {
		return numeroTiquetesComprados;
	}

	public int getCantidadCambiosHorario() {
		return cantidadCambiosHorario;
	}

	public double getDineroTotalGenerado() {
		return dineroTotalGenerado;
	}

	public void aumentarNumeroTiquetesComprados()
	{
		this.numeroTiquetesComprados ++;
	}
	
	public void aumentarCantidadCambiosHorario()
	{
		this.cantidadCambiosHorario ++;
	}
	
	public void aumentarDineroTotalGenerado(double transaccion)
	{
		this.dineroTotalGenerado += transaccion;
	}
}
