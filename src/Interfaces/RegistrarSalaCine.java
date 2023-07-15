package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.SalaCine;
import Main.Cine;

public class RegistrarSalaCine {

	private Cine cine;
	private JPanel contentPane;
	private JTextField filasBox;
	private JTextField columnasBox;
	private JFrame frame;

	


	/**
	 * Create the frame.
	 */
	public RegistrarSalaCine(Cine cine) {
		this.cine = cine;
		initialize();
	}
	
	private void initialize()
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel filasLabel = new JLabel("Filas");
		filasLabel.setBounds(51, 70, 89, 20);
		contentPane.add(filasLabel);
		
		
		JLabel columnasLabel = new JLabel("Columnas");
		columnasLabel.setBounds(51, 100, 89, 20);
		contentPane.add(columnasLabel);
		
		
		JLabel titulo = new JLabel("REGISTRAR SALA CINE");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		titulo.setBounds(110, 40, 191, 13);
		contentPane.add(titulo);
		
		
		filasBox = new JTextField();
		filasBox.setBounds(150, 70, 155, 20);
		contentPane.add(filasBox);
		filasBox.setColumns(10);
		
		
		columnasBox = new JTextField();
		columnasBox.setBounds(150, 100, 155, 19);
		contentPane.add(columnasBox);
		columnasBox.setColumns(10);
		
		
		JButton doneButton = new JButton("Registrar Sala");
		doneButton.setBounds(116, 230, 140, 21);
		contentPane.add(doneButton);
		doneButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int filas = 0;
				int columnas = 0;
				int indicator = 0;
				try 
				{
					filas = Integer.parseInt(filasBox.getText());
					columnas = Integer.parseInt(columnasBox.getText());
					indicator = validateData(filas, columnas);
				}
				catch (NumberFormatException e1)
				{
					indicator = -2;
				}
				
				if (indicator == 1) 
				{
					SalaCine cliente = new SalaCine(cine.getSalas().length + 1, filas, columnas);
					cine.anadirSala(cliente);
					JOptionPane.showMessageDialog(null, "Sala Cine Registrada");
				}
				else if(indicator == -1) 
				{
					JOptionPane.showMessageDialog(null, "Las filas y/o las columnas no pueden ser mas de 26");
				}
				else if(indicator == -2) 
				{
					JOptionPane.showMessageDialog(null, "Las filas y columnas tienen que ser numeros");
				} 
				else 
				{
					JOptionPane.showMessageDialog(null, "No pueden haber filas ni columnas cero o negativas");
				}
				
				PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		
		JButton principalButton = new JButton("Cancelar");
		principalButton.setBounds(10, 16, 85, 21);
		contentPane.add(principalButton);
		principalButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				frame.dispose();
			}
		});
		
		//JCheckBox salaPremium = 
	}
	
	public int validateData (int filas, int columnas){
		int indicator = 1;
		
		if (filas <= 0 || columnas <= 0)
		{
			indicator = 0;
		}
		else if (filas > 26 || columnas > 26)
		{
			indicator = -1;
		}
		
		return indicator;
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}
}
