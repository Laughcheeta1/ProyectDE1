package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.Pelicula;
import Clases.Pelicula3D;
import Main.Cine;

public class RegistrarPelicula {

	private Cine cine;
	private JFrame frame;
	private JPanel contentPane;
	private JTextField titleBox;
	private JTextField codeBox;
	private JTextField genreBox;
	private JTextField durationBox;
	private final JCheckBox movie3D = new JCheckBox("Película 3D");

	/**
	 * Create the application.
	 */
	public RegistrarPelicula(Cine cine) {
		this.cine = cine;
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setTitle("Registrar películas");
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(null);
		
		
		JLabel movieRegister = new JLabel("REGISTRAR PELÍCULAS");
		movieRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		movieRegister.setBounds(110, 40, 220, 13);
		contentPane.add(movieRegister);
		
		
		JLabel movieTitle = new JLabel("Título");
		movieTitle.setBounds(51, 70, 45, 13);
		contentPane.add(movieTitle);
		
		
		JLabel code = new JLabel("Código");
		code.setBounds(51, 100, 45, 13);
		contentPane.add(code);
		
		
		JLabel duration = new JLabel("Duración");
		duration.setBounds(51, 130, 60, 13);
		contentPane.add(duration);
		
		
		JLabel genre = new JLabel("Género");
		genre.setBounds(51, 160, 45, 13);
		contentPane.add(genre);
		

		titleBox = new JTextField();
		titleBox.setBounds(130, 70, 150, 19);
		contentPane.add(titleBox);
		titleBox.setColumns(10);
		
		
		codeBox = new JTextField();
		codeBox.setBounds(130, 100, 150, 19);
		contentPane.add(codeBox);
		codeBox.setColumns(10);
		
		genreBox = new JTextField();
		genreBox.setBounds(130, 160, 150, 19);
		contentPane.add(genreBox);
		genreBox.setColumns(10);
		
		
		durationBox = new JTextField();
		durationBox.setBounds(130, 130, 150, 19);
		contentPane.add(durationBox);
		durationBox.setColumns(10);
		
		durationBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numero = key >= 48 && key <= 57;
				if (!numero) 
					e.consume();
				
			}
		});
		
		
		JButton ppButton = new JButton("Cancelar");
		ppButton.setBounds(10, 16, 85, 21);
		ppButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(ppButton);
		
		
		JButton doneButton = new JButton("Registrar película");
		doneButton.setBounds(116, 230, 140, 21);
		contentPane.add(doneButton);
		
		
		movie3D.setBounds(130, 190, 188, 36);
		contentPane.add(movie3D);
		
		doneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String title = titleBox.getText();
				String code = codeBox.getText();
				String genre = genreBox.getText();
				short duration;
				
				
				if (durationBox.getText().length() == 0) 
					duration = -1;
				else 					
					duration = Short.parseShort(durationBox.getText());
				
				
				int indicator = validateData(title, code, duration, genre);
				
				if (indicator == 1) {
					
					Pelicula pelicula;
					if(movie3D.isSelected()) {
						pelicula = new Pelicula3D (title, code, duration, genre);
					}
					pelicula = new Pelicula (title, code, duration, genre);
					
					cine.anadirPelicula(pelicula);
					
					JOptionPane.showMessageDialog(null, "Película registrada");
					PaginaPrincipal m = new PaginaPrincipal(cine);
					m.getFrame().setVisible(true);
					frame.dispose();
				} else {
					JOptionPane.showMessageDialog(null, "Datos incompletos");
				}
			}
		});
	}
	
	public int validateData (String title, String code, short duration, String genre){
		int indicator = 0;
		
		if (title != null && code != null && genre != null && duration != -1) 
			indicator = 1;
		
		
		return indicator;
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}

}
