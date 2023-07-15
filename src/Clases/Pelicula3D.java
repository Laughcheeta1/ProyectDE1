package Clases;

import java.io.Serializable;

public class Pelicula3D extends Pelicula implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Pelicula3D(String nombre, String codigo, short duracionMinutos, String genero) {
		
		super(nombre, codigo, duracionMinutos, genero);
		this.setNombre(getNombre().concat("-3D"));
	}

}

