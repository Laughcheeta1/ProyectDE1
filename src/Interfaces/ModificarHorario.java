package Interfaces;

import Main.Cine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

import Clases.Pelicula;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Font;


/**
 * @author santi
 *
 */
public class ModificarHorario{
	
	private JFrame frame;
	private JPanel contentPane;
	private Cine cine;
	@SuppressWarnings("rawtypes")
	private JComboBox[][] horarioJ;

	public JFrame getFrame()
	{
		return this.frame;
	}
	
	public ModificarHorario(Cine cine)
	{
		this.cine = cine;
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
		contentPane.setLayout(null);
		
		JButton mainMenuButton = new JButton("Cancelar");
		mainMenuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				PaginaPrincipal p = new PaginaPrincipal(cine);
				p.getFrame().setVisible(true);
				frame.dispose();;
			}
		});
		mainMenuButton.setBounds(1196, 627, 164, 23);
		contentPane.add(mainMenuButton);
		
		JButton acceptButton = new JButton("Confirmar Cambios");
		acceptButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				confirmarCambios();
				PaginaPrincipal p = new PaginaPrincipal(cine);
				p.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		acceptButton.setBounds(606, 627, 189, 23);
		contentPane.add(acceptButton);
		
		JLabel lblNewLabel = new JLabel("Turnos");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(39, 257, 100, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblSalasdeIzquierda = new JLabel("Salas (de izquierda a derecha)");
		lblSalasdeIzquierda.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 25));
		lblSalasdeIzquierda.setBounds(426, 22, 447, 52);
		frame.getContentPane().add(lblSalasdeIzquierda);
		
		crearJComboBox();
		
		
		
	}

	/*
	 * Aplica los cambios al horario a la clase Cine
	 */
	private void confirmarCambios()
	{
		Pelicula[][] horario = cine.getHorario();
		int x = cine.getSalas().length;
		
		for (int i = 0; i < x; i++)
		{
			for (int j = 0; j < Cine.numeroTurnos; j++)
			{
				horario[j][i] = (Pelicula)horarioJ[j][i].getSelectedItem();
			}
		}
		cine.confirmarCambioHorario(horario);
		JOptionPane.showMessageDialog(null, "Cambios Efectuados");
	}
	
	/*
	 * Aqui se van a crear los JCombo Box, las filas son los turnos, y las Columnas son
	 * las salas
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void crearJComboBox() {
		horarioJ = new JComboBox[Cine.numeroTurnos][cine.getSalas().length];
		int numeroSalas = cine.getSalas().length;
		
		Pelicula[] peliculas = cine.getPeliculas();
		
		Pelicula[][] horario = cine.getHorario();
		
		int x = 200;
		int y = 150;
				
		/*
		 * primero creamos las columnas, y despues las filas
		 */		
		// Vector j stands for the Y axis (number of working shifts)
		for (int i = 0; i < numeroSalas; i++)
		{
			// Vector i stands for the X axis (number of rooms)
			for (int j = 0; j < Cine.numeroTurnos; j++)
			{
				horarioJ[j][i] = new JComboBox(peliculas);
				horarioJ[j][i].setBounds(x, y, 150, 22);
				
				// Auto selecciona la pelicula en el horario actual
				try
				{
					horarioJ[j][i].setSelectedIndex(encontrarPosicionPelicula(peliculas, horario[j][i]));
				}
				catch (IllegalArgumentException e)
				{	
					
				}
				
				frame.getContentPane().add(horarioJ[j][i]);
				
				y += 70;
			}
			y = 150;
			x += 300;
		}
	}
	
	//Encuentra la posicion de la pelicula en cuestion en el arreglo de peliculas
	private int encontrarPosicionPelicula(Pelicula[] pls, Pelicula p)
	{
		if (p == null)
		{
			return -1;
		}
		
		int x = pls.length;
		int i = 0;
		
		while (i < x && pls[i].getCodigo().compareTo(p.getCodigo()) != 0)
		{
			i++;
		}
		
		return i;
	}
}
