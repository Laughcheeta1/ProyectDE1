package Clases;

import java.io.Serializable;

public class Pelicula implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nombre;
	private String codigo;
	private int duracionMinutos;
	private String genero;
	
	public Pelicula(String nombre, String codigo, short duracionMinutos, String genero) {
		super();
		this.nombre = nombre;
		this.codigo = codigo;
		this.duracionMinutos = duracionMinutos;
		this.genero = genero;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getDuracionMinutos() {
		return duracionMinutos;
	}

	public void setDuracionMinutos(short duracionMinutos) {
		this.duracionMinutos = duracionMinutos;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
	
	@Override
	public String toString()
	{
		return this.nombre;
	}
}
