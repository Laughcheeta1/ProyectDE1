package Clases;

import java.io.Serializable;

public class Silla implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String posicion;
	private boolean disponibilidad;
	private double precio;
	private ClienteRegistrado cliente;
	
	public Silla(String posicion, boolean disponibilidad) {
		super();
		this.posicion = posicion;
		this.disponibilidad = disponibilidad;
		this.precio = calcularPrecio();
	}

	public void setCliente(ClienteRegistrado cliente)
	{
		this.cliente = cliente;
	}
	
	public ClienteRegistrado getCliente()
	{
		return this.cliente;
	}
	
	public String getPosicion() {
		return posicion;
	}
	
	public String getFila() {
		//int iseparador = this.posicion.indexOf("-") + 1;
		//String fila = this.posicion.substring(1, iseparador);
		String fila = Character.toString(this.posicion.charAt(0));
		return fila;
	}
	
	public String getColumna() {
		int iseparador = this.posicion.indexOf("-") +1;
		String columna = Character.toString(this.posicion.charAt(iseparador));
		return columna;
	}
	
	public boolean isDisponibilidad() {
		return disponibilidad;
	}

	public void setDisponibilidad(boolean disponibilidad) {
		this.disponibilidad = disponibilidad;
	}
			
	public double getPrecio() {
		return this.precio;
	}
	
	private double calcularPrecio() {
		//pensado para salas del mismo tamaño, si se va a variar el tamaño de las salas se debe replantear
		String fila = getFila();
		double precio;
		
		if(fila.compareTo("A") == 0 || fila.compareTo("B") == 0 || fila.compareTo("C") == 0 || fila.compareTo("C") == 0 || fila.compareTo("D") == 0 || fila.compareTo("E") == 0 || fila.compareTo("F") == 0) {
			precio = 22000;
		}else if(fila.compareTo("X") == 0 || fila.compareTo("Y") == 0 || fila.compareTo("Z") == 0 ) {
			precio = 8000; 
		}else {
			precio = 14000;
		}
		
		return precio;
	}
	
}
