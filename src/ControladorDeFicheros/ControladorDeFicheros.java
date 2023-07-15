package ControladorDeFicheros;

import java.util.Arrays;

import javax.swing.JOptionPane;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import Clases.*;


public class ControladorDeFicheros {
	private String ficheroClientes;
	private String ficheroTrabajadores;
	private String ficheroPeliculas;
	private String ficheroSalas;
	private String ficheroDatos;
	private String ficheroHora;
	private String ficheroFacturas;
	private String ficheroHorario;
	
	public ControladorDeFicheros()
	{
		String direccionG = System.getProperty("user.dir");
		ficheroClientes = direccionG + "\\Grupo2Proyecto1ED\\Personas\\Clientes";
		ficheroTrabajadores = direccionG + "\\Grupo2Proyecto1ED\\Personas\\Trabajadores\\trabajadores.txt";
		ficheroPeliculas = direccionG + "\\Grupo2Proyecto1ED\\Peliculas";
		ficheroSalas = direccionG + "\\Grupo2Proyecto1ED\\Salas";
		ficheroDatos = direccionG + "\\Grupo2Proyecto1ED\\Datos\\0.dato";
		ficheroHorario = direccionG + "\\Grupo2Proyecto1ED\\Horario\\0.horario";
		ficheroHora = direccionG + "\\Grupo2Proyecto1ED\\Horario\\0.hora";
		ficheroFacturas = direccionG + "\\Grupo2Proyecto1ED\\Facturas";
	}
	
	public void escribirFactura(Factura[] facturas) throws IOException
	{
		int x = facturas.length;
		for (int i = 0; i < x; i++)
		{
				FileOutputStream f = new FileOutputStream(ficheroFacturas + "\\" + facturas[i].getNumero() + ".factura");
				ObjectOutputStream o = new ObjectOutputStream(f);
				
				o.writeObject(facturas[i]);
				
				o.close();
				f.close();
		}
	}
	
	public Factura[] readFacturas() throws IOException, ClassNotFoundException
	{
		File f = new File(ficheroFacturas);
		File[] listF = f.listFiles(new Filtro(".factura"));
		Factura[] facturas;
		
		try
		{
			facturas = new Factura[listF.length];
			
			int x = listF.length;
			
			for (int i = 0; i < x; i++)
			{
				FileInputStream in = new FileInputStream(listF[i]);
				ObjectInputStream o = new ObjectInputStream(in);
				
				facturas[i] = (Factura)o.readObject();
				
				o.close();
				in.close();
			}
		}
		catch (NullPointerException e)
		{
			facturas = new Factura[0];
		}
		
		return facturas;
	}
	
	public void escribirClientes(ClienteRegistrado[] clientes) throws IOException
	{
		int x = clientes.length;
		for (int i = 0; i < x; i++)
		{
				FileOutputStream f = new FileOutputStream(ficheroClientes + "\\" + clientes[i].getCc() + ".cliente");
				ObjectOutputStream o = new ObjectOutputStream(f);
				
				o.writeObject(clientes[i]);
				
				o.close();
				f.close();
		}
	}
	
	public ClienteRegistrado[] readClientes() throws IOException, ClassNotFoundException
	{
		File f = new File(ficheroClientes);
		File[] listF = f.listFiles(new Filtro(".cliente"));
		ClienteRegistrado[] clientes;
		
		try
		{
			clientes = new ClienteRegistrado[listF.length];
			
			int x = listF.length;
			
			for (int i = 0; i < x; i++)
			{
				FileInputStream in = new FileInputStream(listF[i]);
				ObjectInputStream o = new ObjectInputStream(in);
				
				clientes[i] = (ClienteRegistrado)o.readObject();
				
				o.close();
				in.close();
			}
		}
		catch (NullPointerException e)
		{
			clientes = new ClienteRegistrado[0];
		}
		
		return clientes;
	}

	
	public Trabajador[] readTrabajadores() throws IOException, ClassNotFoundException
	{
		Trabajador[] trabajadores = new Trabajador[0];
		
		File f = new File(ficheroTrabajadores);
		FileReader fr = new FileReader(f);
		BufferedReader b = new BufferedReader(fr);
		
		String linea;
		String [] valores;
		
		while ((linea = b.readLine()) != null)
		{	
			valores = linea.split("---");
			
			String[] fecha;
			fecha = valores[2].split("/");
			String nombre = valores[0];
			String cc = valores[1];
			String numeroTelefono = valores[3];
			String codigo = valores[4];
			int salario = Integer.parseInt(valores[5]);
			Trabajador t = new Trabajador(nombre, cc, Integer.parseInt(fecha[0]), Integer.parseInt(fecha[1]), Integer.parseInt(fecha[2]), numeroTelefono, codigo, salario);
			
			trabajadores = ampliarTrabajadores(trabajadores, t);
		}
		
		b.close();
		fr.close();
		
		return trabajadores;
	}

	private Trabajador[] ampliarTrabajadores(Trabajador[] trabajadores, Trabajador t) 
	{
		trabajadores = Arrays.copyOf(trabajadores, trabajadores.length + 1);
		trabajadores[trabajadores.length - 1] = t;
		return trabajadores;
	}

	public void escribirPeliculas(Pelicula[] peliculas) throws IOException
	{
		int x = peliculas.length;
		for (int i = 0; i < x; i++)
		{
				FileOutputStream f = new FileOutputStream(ficheroPeliculas + "\\" + peliculas[i].getCodigo() + ".pelicula");
				ObjectOutputStream o = new ObjectOutputStream(f);
				
				o.writeObject(peliculas[i]);
				
				o.close();
				f.close();
		}
	}
	
	public Pelicula[] readPeliculas() throws IOException, ClassNotFoundException
	{
		File f = new File(ficheroPeliculas);
		File[] listF = f.listFiles(new Filtro(".pelicula"));
		
		Pelicula[] peliculas;
		
		try
		{
			peliculas = new Pelicula[listF.length];
			int x = listF.length;
			
			for (int i = 0; i < x; i++)
			{
				FileInputStream in = new FileInputStream(listF[i]);
				ObjectInputStream o = new ObjectInputStream(in);
				
				peliculas[i] = (Pelicula)o.readObject();
				
				o.close();
				in.close();
			}
		}
		catch (NullPointerException e)
		{
			peliculas = new Pelicula[0];
		}
		
		return peliculas;
	}
	
	public void escribirSalas(SalaCine[] salas) throws IOException
	{
		int x = salas.length;
		for (int i = 0; i < x; i++)
		{
				FileOutputStream f = new FileOutputStream(ficheroSalas + "\\" + salas[i].getNumeroSala() + ".sala");
				ObjectOutputStream o = new ObjectOutputStream(f);
				
				o.writeObject(salas[i]);
				
				o.close();
				f.close();
		}
	}
	
	public SalaCine[] readSalas() throws IOException, ClassNotFoundException
	{
		File f = new File(ficheroSalas);
		File[] listF = f.listFiles(new Filtro(".sala"));
		
		SalaCine[] salas;
		
		try 
		{
			salas = new SalaCine[listF.length];
			
			int x = listF.length;
			
			for (int i = 0; i < x; i++)
			{
				FileInputStream in = new FileInputStream(listF[i]);
				ObjectInputStream o = new ObjectInputStream(in);
				
				salas[i] = (SalaCine)o.readObject();
				
				o.close();
				in.close();
			}
		}
		catch (NullPointerException e)
		{
			salas = new SalaCine[0];
		}
		
		
		return salas;
	}
	
	public void escribirDatos(Dato datos) throws IOException
	{

			FileOutputStream f = new FileOutputStream(ficheroDatos);
			ObjectOutputStream o = new ObjectOutputStream(f);
			
			o.writeObject(datos);
				
			o.close();
			f.close();
		
	}
	
	public Dato readDatos() throws IOException, ClassNotFoundException
	{				
		FileInputStream in = new FileInputStream(ficheroDatos);
		ObjectInputStream o = new ObjectInputStream(in);
			
		Dato datos = (Dato)o.readObject();
			
		o.close();
		in.close();
		
		return datos;
	}

	public void escribirHorario(Pelicula[][] horario) throws IOException
	{
		FileOutputStream f = new FileOutputStream(ficheroHorario);
		ObjectOutputStream o = new ObjectOutputStream(f);
		
		o.writeObject(horario);
		
		o.close();
		f.close();
	}
	
	public Pelicula[][] readHorario() throws ClassNotFoundException, IOException {
		FileInputStream in = new FileInputStream(ficheroHorario);
		ObjectInputStream o = new ObjectInputStream(in);
		
		Pelicula[][] horario = (Pelicula[][]) o.readObject();
		
		o.close();
		in.close();
		
		return horario;
	}
	
	public void escribirHora(int hora) throws IOException
	{
		File f = new File(ficheroHora);
		FileWriter fr = new FileWriter(f);
		BufferedWriter b = new BufferedWriter(fr);
		
		b.write(Integer.toString(hora));
		
		b.close();
		fr.close();
	}
	
	public int readHora() throws IOException
	{
		File f = new File(ficheroHora);
		FileReader fr = new FileReader(f);
		BufferedReader b = new BufferedReader(fr);
		
		int result;
		try
		{
			result = Integer.parseInt(b.readLine());
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
			result = 0;
		}
		
		b.close();
		fr.close();
		
		return result;
	}
}