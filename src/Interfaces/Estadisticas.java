package Interfaces;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Clases.Dato;
import Main.Cine;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Estadisticas{

	private JFrame frame;
	private JPanel contentPane;
	private Cine cine;
	private Dato dato;
	private JLabel tiquetesCompradosLabel;
	private JLabel cantidadPersonasRegistradasLabel;
	private JLabel cantidadPeliculasLabel;
	private JLabel cantidadCambiosHorarioLabel;
	private JLabel dineroTotalGeneradoLabel;
	private JLabel cantidadDeSalasLabel;

	/**
	 * Create the application.
	 */
	public Estadisticas(Cine cine) {
		this.cine = cine;
		this.dato = cine.getDatos();
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(0, 0, 2900, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = (JPanel) frame.getContentPane();
		frame.getContentPane().setLayout(null);
		
		JButton ppButton = new JButton("Pagina Principal");
		ppButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 30));
		ppButton.setBounds(10, 11, 313, 64);
		contentPane.add(ppButton);
		ppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		JLabel lblCantidadDeTiquetesComprados = new JLabel("Cantidad De Tiquetes Comprados");
		lblCantidadDeTiquetesComprados.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCantidadDeTiquetesComprados.setBounds(10, 107, 486, 98);
		contentPane.add(lblCantidadDeTiquetesComprados);
		
		JLabel lblCantidadDePersonas = new JLabel("Cantidad De Personas Registradas");
		lblCantidadDePersonas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCantidadDePersonas.setBounds(10, 216, 486, 98);
		contentPane.add(lblCantidadDePersonas);
		
		JLabel lblCantidadDePeliculas = new JLabel("Cantidad De Peliculas Registradas");
		lblCantidadDePeliculas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCantidadDePeliculas.setBounds(10, 331, 486, 98);
		contentPane.add(lblCantidadDePeliculas);
		
		JLabel lblCantidadDeCambios = new JLabel("Cantidad De Cambios A Horario");
		lblCantidadDeCambios.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCantidadDeCambios.setBounds(10, 440, 486, 98);
		contentPane.add(lblCantidadDeCambios);
		
		JLabel lblDineroTotalGenerado = new JLabel("Dinero Total Generado");
		lblDineroTotalGenerado.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblDineroTotalGenerado.setBounds(10, 552, 486, 98);
		contentPane.add(lblDineroTotalGenerado);
		
		tiquetesCompradosLabel = new JLabel("");
		tiquetesCompradosLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		tiquetesCompradosLabel.setBounds(759, 107, 195, 98);
		contentPane.add(tiquetesCompradosLabel);
		
		cantidadPersonasRegistradasLabel = new JLabel("");
		cantidadPersonasRegistradasLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cantidadPersonasRegistradasLabel.setBounds(759, 216, 195, 98);
		contentPane.add(cantidadPersonasRegistradasLabel);
		
		cantidadPeliculasLabel = new JLabel("");
		cantidadPeliculasLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cantidadPeliculasLabel.setBounds(759, 331, 195, 98);
		contentPane.add(cantidadPeliculasLabel);
		
		cantidadCambiosHorarioLabel = new JLabel("");
		cantidadCambiosHorarioLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cantidadCambiosHorarioLabel.setBounds(759, 440, 195, 98);
		contentPane.add(cantidadCambiosHorarioLabel);
		
		dineroTotalGeneradoLabel = new JLabel("");
		dineroTotalGeneradoLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		dineroTotalGeneradoLabel.setBounds(759, 552, 195, 98);
		contentPane.add(dineroTotalGeneradoLabel);
		
		JLabel lblCantidadDeSalas = new JLabel("Cantidad De Salas");
		lblCantidadDeSalas.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblCantidadDeSalas.setBounds(964, 107, 486, 98);
		frame.getContentPane().add(lblCantidadDeSalas);
		
		cantidadDeSalasLabel = new JLabel("0");
		cantidadDeSalasLabel.setFont(new Font("Tahoma", Font.PLAIN, 30));
		cantidadDeSalasLabel.setBounds(1460, 107, 243, 98);
		frame.getContentPane().add(cantidadDeSalasLabel);
		
		generarDatos();
	}
	
	private void generarDatos() 
	{
		this.tiquetesCompradosLabel.setText(Integer.toString(dato.getNumeroTiquetesComprados()));
		this.cantidadPersonasRegistradasLabel.setText(Integer.toString(cine.getClientes().length));
		this.cantidadPeliculasLabel.setText(Integer.toString(cine.getPeliculas().length));
		this.cantidadCambiosHorarioLabel.setText(Integer.toString(dato.getCantidadCambiosHorario()));
		this.dineroTotalGeneradoLabel.setText(Double.toString(Math.round(dato.getDineroTotalGenerado())));
		this.cantidadDeSalasLabel.setText(Integer.toString(cine.getSalas().length));
	}

	public JFrame getFrame()
	{
		return this.frame;
	}
}
