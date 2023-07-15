package Clases;

import java.io.Serializable;

public class Trabajador extends Persona implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String codigo;
	private int salario;
	
	public Trabajador(String nombre, String cc, int anio, int mes, int dia, String numeroTelefono, String codigo, int salario) {
		super(nombre, cc, anio, mes, dia, numeroTelefono);
		// TODO Auto-generated constructor stub
		this.codigo = codigo;
		this.salario = salario;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public int getSalario() {
		return salario;
	}

	public void setSalario(int salario) {
		this.salario = salario;
	}
}
