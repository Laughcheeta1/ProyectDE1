package Clases;

import java.io.Serializable;
import java.util.Date;

public abstract class Persona implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private String cc;
	private Date fechaNacimiento;
	private int edad;
	private String numeroTelefono;
	
	@SuppressWarnings("deprecation")
	public Persona(String nombre, String cc, int anio, int mes, int dia, String numeroTelefono) {
		super();
		this.nombre = nombre;
		this.cc = cc;
		this.fechaNacimiento = new Date(anio, mes, dia);
		this.edad = calcularEdad();
		this.numeroTelefono = numeroTelefono;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getCc() {
		return cc;
	}
	
	public void setCc(String cc) {
		this.cc = cc;
	}
	
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public String getNumeroTelefono() {
		return numeroTelefono;
	}
	
	public void setNumeroTelefono(String numeroTelefono) {
		this.numeroTelefono = numeroTelefono;
	}
	
	public int getEdad() {
		return edad;
	}
	
	private int calcularEdad() {
		
		Date fechaActual = new Date();
		long diferencia = fechaActual.getTime() - fechaNacimiento.getTime();
	    double anios = diferencia / (1000 * 60 * 60 * 24 * 365.25);
	    return (int) Math.round(anios);
	}
}
