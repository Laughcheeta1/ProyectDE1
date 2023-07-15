package Main;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;

import Clases.*;
import ControladorDeFicheros.ControladorDeFicheros;

public class Cine {
	private int hora;
	// La primera dimension es el turno, la segunda es cada pelicula segun la sala
	private Pelicula[][] horario;
	
	private ClienteRegistrado[] clientes;
	private Trabajador[] trabajadores;
	private Pelicula[] peliculas;
	private Factura[] facturas;
	private SalaCine[] salas;
	private Dato datos;
	private ControladorDeFicheros ficheros;
	
	/*
	 * anadir un boton en la pag principal para anadir una sala. Esto sera solo un boton
	 * el cual se presiona y se anade automaticamente una sala extra.
	 */
	
	
	// Cada turno son 2 horas
	public static final byte numeroTurnos = 4;
	
	public Cine()
	{
		ficheros = new ControladorDeFicheros();
		try
		{
			clientes = ficheros.readClientes();
			peliculas = ficheros.readPeliculas();
			facturas = ficheros.readFacturas();
			salas = ficheros.readSalas();
			datos = ficheros.readDatos();
			horario = ficheros.readHorario();
			hora = ficheros.readHora();
			
		}
		catch (IOException | ClassNotFoundException e)
		{		
			inicializarDesdeCero();
		}
		
		try 
		{
			trabajadores = ficheros.readTrabajadores();
		} 
		catch (ClassNotFoundException | IOException e) 
		{		}
	}

	
	public ClienteRegistrado[] getClientes() {
		return clientes;
	}

	public Trabajador[] getTrabajadores() {
		return trabajadores;
	}

	public Pelicula[] getPeliculas() {
		return peliculas;
	}

	public SalaCine[] getSalas() {
		return salas;
	}

	public Dato getDatos() {
		return datos;
	}
	
	public int getHora() {
		return hora;
	}

	public Pelicula[][] getHorario() {
		return horario;
	}

	// Devuelve un objeto cliente dado el documento
	public ClienteRegistrado getCliente(String cc)
	{
		int i = 0;
		int x = clientes.length;
		
		while (i < x && clientes[i].getCc().compareTo(cc) != 0)
		{
			i++;
		}
		
		return (i == x) ? null : clientes[i];
	}
	
	// Anade una pelicula a la lista de peliculas
	public void anadirPelicula(Pelicula p)
	{
		peliculas = Arrays.copyOf(peliculas, peliculas.length + 1);
		peliculas[peliculas.length - 1] = p;
	}
	
	// Anade un cliente a la lista de clientes
	public void anadirCliente(ClienteRegistrado c)
	{
		clientes = Arrays.copyOf(clientes, clientes.length + 1);
		clientes[clientes.length - 1] = c;
	}
	
	// Anade una sala a la lista de salas
	public void anadirSala(SalaCine s)
	{
		salas = Arrays.copyOf(salas, salas.length + 1);
		ampliarHorario();
		salas[salas.length - 1] = s;
	}
	
	// Retorna verdadero si el cliente con la cedula dada, existe, de lo contrario 
		// retorna falso
	public ClienteRegistrado buscarCliente(String cc)
	{
		int i = 0;
		int x = clientes.length;
		
		while (i < x && clientes[i].getCc().compareTo(cc) != 0)
		{
			i++;
		}
		
		return (i == x) ? null : clientes[i];
	}
	
	// Escribe toda la info en los ficheros
	public void terminarEjecucion() throws IOException
	{
		ficheros.escribirClientes(clientes);
		ficheros.escribirHorario(horario);
		ficheros.escribirHora(hora); 
		ficheros.escribirPeliculas(peliculas);
		ficheros.escribirSalas(salas);
		ficheros.escribirDatos(datos);
		ficheros.escribirFactura(facturas);
	}
	
	public Factura comprarBoleto(Silla silla, double ganancia, ClienteRegistrado cliente, String metodoPago)
	{
		silla.setDisponibilidad(false);
		silla.setCliente(cliente);
		datos.aumentarDineroTotalGenerado(ganancia);
		datos.aumentarNumeroTiquetesComprados();
		
		Date fecha = new Date();
		
		if (cliente == null)
		{
			return anadirFactura(fecha.toString(), "Cliente No Registrado", silla.getPosicion(), ganancia, metodoPago);
		}
		else
		{
			return anadirFactura(fecha.toString(), cliente.getCc(), silla.getPosicion(), ganancia, metodoPago);
		}
	}
	
	public void confirmarCambioHorario(Pelicula[][] horario)
	{
		this.horario = horario;
		datos.aumentarCantidadCambiosHorario();
	}
	 
	public void horaSiguiente()
	{
		if (hora == numeroTurnos)
		{
			hora = 1;
			liberarSalas();
		}
		else
		{
			hora ++;
		}
	}
	
	private Factura anadirFactura(String fecha, String idCliente, String posicionSilla, double precioPagado, String metodoPago)
	{
		Factura f = new Factura(fecha, Integer.toString(facturas.length + 1), idCliente, posicionSilla, precioPagado, metodoPago);
		
		facturas = Arrays.copyOf(facturas, facturas.length + 1);
		facturas[facturas.length - 1] = f;
		
		return f;
	}
	
	private void inicializarDesdeCero()
	{
		inicializarHorario();
		this.clientes = new ClienteRegistrado[0];
		this.peliculas = new Pelicula[0];
		this.facturas = new Factura[0];
		this.salas = new SalaCine[0];
		this.datos = new Dato();
		this.hora = 1;
	}
	
	private void inicializarHorario()
	{
		this.horario = new Pelicula[numeroTurnos][0];
		
	}
	
	private void ampliarHorario()
	{
		for (int i = 0; i < numeroTurnos; i++)
		{
			this.horario[i] = Arrays.copyOf(this.horario[i], this.horario[i].length + 1);
		}
	}
	
	private void liberarSalas()
	{
		for (SalaCine s : salas)
		{
			s.liberarCine();
		}
	}
}