package Interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Main.Cine;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

public class PaginaPrincipal {

	
	private Cine cine;
	private JFrame frame;
	private JPanel contentPane;
	private JLabel turnoLabel;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	
	// Este es para inicializarlo por medio de las otras interfaces
	public PaginaPrincipal(Cine cine)
	{
		this.cine = cine;
		initialize();
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		
		JButton userRegister = new JButton("Registrar cliente");
		userRegister.setBounds(30, 120, 150, 21);
		userRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarCliente m = new RegistrarCliente(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(userRegister);
		
		JButton movieRegister = new JButton("Registrar película");
		movieRegister.setBounds(200, 120, 150, 21);
		movieRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarPelicula m = new RegistrarPelicula(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(movieRegister);
		
		JButton ticketBuy = new JButton("Comprar boleto");
		ticketBuy.setBounds(200, 160, 150, 21);
		ticketBuy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idCliente = JOptionPane.showInputDialog("Ingresa la cédula del cliente");
				if (idCliente != null) {
					ComprarBoleto m = new ComprarBoleto(cine, idCliente);
					m.setVisible(true);
					frame.dispose();
		        }
			}
		});
		contentPane.add(ticketBuy);
		
		
		
		JLabel cineLabel = new JLabel("CINE");
		cineLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		cineLabel.setBounds(165, 50, 60, 20);
		contentPane.add(cineLabel);
		
		JButton changeSchedule = new JButton("Cambiar horario");
		changeSchedule.setBounds(30, 160, 150, 21);
		changeSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ModificarHorario m = new ModificarHorario(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(changeSchedule);
		
		JButton btnNewButton = new JButton("Ver estadísticas");
		btnNewButton.setBounds(200, 200, 150, 21);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Estadisticas m = new Estadisticas(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(btnNewButton);
		
		JButton addSala = new JButton("Añadir sala de cine");
		addSala.setBounds(30, 200, 150, 21);
		addSala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegistrarSalaCine m = new RegistrarSalaCine(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		contentPane.add(addSala);
		
		JButton guardarInfoBtn = new JButton("Guardar Informacion");
		guardarInfoBtn.setBounds(105, 232, 164, 23);
		guardarInfoBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{
					cine.terminarEjecucion();
					JOptionPane.showMessageDialog(null, "Se ha guardado la informacion con exito", "Yay", JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (IOException e1) 
				{
					JOptionPane.showMessageDialog(null, e1.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		frame.getContentPane().add(guardarInfoBtn);
		
		turnoLabel = new JLabel("");
		turnoLabel.setBounds(304, 50, 46, 14);
		frame.getContentPane().add(turnoLabel);
		setTurnoLabel();
		
		JButton turnoSiguienteBtn = new JButton("Turno Siguiente");
		turnoSiguienteBtn.setBounds(279, 232, 145, 23);
		turnoSiguienteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cine.horaSiguiente();
				setTurnoLabel();
			}
		});
		frame.getContentPane().add(turnoSiguienteBtn);
		
		// Codigo por @Ravindra Gullapalli - StackOverflow
		frame.addWindowListener(new WindowAdapter() 
		{
			@Override
			public void windowClosing(WindowEvent windowEvent)
			{
				try {
					cine.terminarEjecucion();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		}
				
		);
	}
	
	private void setTurnoLabel()
	{
		this.turnoLabel.setText("Turno " + cine.getHora());
	}
}
