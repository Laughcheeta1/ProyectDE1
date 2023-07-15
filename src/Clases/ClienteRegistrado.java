package Clases;

import java.io.Serializable;

public class ClienteRegistrado extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ClienteRegistrado(String nombre, String cc, int anio, int mes, int dia, String numeroTelefono) {
		super(nombre, cc, anio, mes, dia, numeroTelefono);
		// TODO Auto-generated constructor stub
	}
}
