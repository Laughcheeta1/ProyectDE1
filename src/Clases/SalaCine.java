package Clases;

import java.io.Serializable;

import Main.Cine;

public class SalaCine implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int numeroSala;
	private final int numeroSillas;
	// Primera dimension son Filas, segunda son Columnas
	private Silla[][][] sillas;
	private Pelicula peliculaEnReproduccion;
	
	public SalaCine(int numeroSala, int filas, int columnas)
	{
		super();
		this.numeroSala = numeroSala;
		this.numeroSillas = filas * columnas;
		this.sillas = new Silla[Cine.numeroTurnos][filas][columnas];
		llenarSillas();
		this.peliculaEnReproduccion = null;
	}
	
	public SalaCine(int numeroSala, int filas, int columnas, Pelicula peliculaEnReproduccion) {
		super();
		this.numeroSala = numeroSala;
		this.numeroSillas = filas * columnas;
		inicializarSillas(filas, columnas);
		llenarSillas();
		this.peliculaEnReproduccion = peliculaEnReproduccion;
	}
	
	private void llenarSillas()
	{
		String f = "A";
		int tamanoPrimeraDimension = sillas.length;
		int tamanoSegundaDimension = sillas[0].length;
		
		for (int z = 0; z < Cine.numeroTurnos; z++)
		{
			for (int j = 0; j < tamanoPrimeraDimension; j++)
			{
				for (int i = 1; i <= tamanoSegundaDimension; i++)
				{
					sillas[z][j][i - 1] = new Silla(f + "-" + i, true);
				}	
			
				byte[] fb = f.getBytes();
				fb[0] += 1;
				f = new String(fb);
			}
		}
	}
	
	private void inicializarSillas(int filas, int columnas)
	{
		for (int i = 0; i < Cine.numeroTurnos; i++)
		{
			sillas[i] = new Silla[filas][columnas];
		}
	}

	public int getNumeroSala() {
		return numeroSala;
	}

	public void setNumeroSala(int numeroSala) {
		this.numeroSala = numeroSala;
	}

	public Silla[][][] getSillas() {
		return sillas;
	}

	//public void setSillas(Silla[][] sillas) {
		//this.sillas = sillas;
	//}

	public Pelicula getPeliculaEnReproduccion() {
		return peliculaEnReproduccion;
	}

	public void setPeliculaEnReproduccion(Pelicula peliculaEnReproduccion) {
		this.peliculaEnReproduccion = peliculaEnReproduccion;
	}

	public int getNumeroSillas() {
		return numeroSillas;
	}
	
	public void liberarCine() {
		for (Silla[][] z : sillas) 
		{
			for(Silla[] filas : z) 
			{
				for(Silla s : filas) 
				{
					s.setDisponibilidad(true);
				}
			}
		}
		
	}
	
	public String toString()
	{
		return Integer.toString(this.numeroSala);
	}
}
