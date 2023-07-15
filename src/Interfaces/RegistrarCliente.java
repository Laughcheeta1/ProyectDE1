package Interfaces;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Clases.ClienteRegistrado;
import Main.Cine;

public class RegistrarCliente {

	private Cine cine;
	private final int yearFormat = 1900;
	private final int monthFormat = 1;
	private JPanel contentPane;
	private JTextField nameBox;
	private JTextField ccBox;
	private JTextField dayBox;
	private JTextField telBox;
	private JTextField monthBox;
	private JTextField yearBox;
	private JFrame frame;

	/**
	 * Create the application.
	 */
	public RegistrarCliente(Cine cine) {
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
		contentPane = (JPanel) frame.getContentPane();
		contentPane.setLayout(null);
		
		
		JLabel name = new JLabel("Nombre");
		name.setBounds(51, 70, 45, 20);
		contentPane.add(name);
		
		
		JLabel cc = new JLabel("Cédula");
		cc.setBounds(51, 100, 45, 20);
		contentPane.add(cc);
		
		
		JLabel birthday = new JLabel("Fecha de nacimiento");
		birthday.setBounds(51, 160, 120, 20);
		contentPane.add(birthday);
	
		
		JLabel day = new JLabel("Dia");
		day.setBounds(189, 130, 45, 13);
		contentPane.add(day);
		
		
		JLabel month = new JLabel("Mes");
		month.setBounds(234, 130, 35, 13);
		contentPane.add(month);
		
		
		JLabel year = new JLabel("Año");
		year.setBounds(279, 130, 35, 13);
		contentPane.add(year);
		
		
		JLabel telefono = new JLabel("Telefono");
		telefono.setBounds(51, 190, 100, 20);
		contentPane.add(telefono);
		
		
		JLabel userRegister = new JLabel("REGISTRAR USUARIOS");
		userRegister.setFont(new Font("Tahoma", Font.BOLD, 16));
		userRegister.setBounds(110, 40, 220, 13);
		contentPane.add(userRegister);
		
		
		nameBox = new JTextField();
		nameBox.setBounds(150, 70, 155, 20);
		contentPane.add(nameBox);
		nameBox.setColumns(10);
		
		
		ccBox = new JTextField();
		ccBox.setBounds(150, 100, 155, 19);
		contentPane.add(ccBox);
		ccBox.setColumns(10);
		
		
		dayBox = new JTextField();
		dayBox.setBounds(180, 160, 35, 19);
		contentPane.add(dayBox);
		dayBox.setColumns(10);
		
		dayBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numero = key >= 48 && key <= 57;
				
				if(!numero || dayBox.getText().length() >=2) 
					e.consume();
				
			
			}
		});
		
		
		monthBox = new JTextField();
		monthBox.setBounds(225, 160, 35, 19);
		contentPane.add(monthBox);
		monthBox.setColumns(10);
		
		monthBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numero = key >= 48 && key <= 57;
				
				if(!numero || monthBox.getText().length() >=2) 
					e.consume();
				
			}
		});
		
		
		yearBox = new JTextField();
		yearBox.setBounds(270, 160, 35, 19);
		contentPane.add(yearBox);
		yearBox.setColumns(10);
		
		yearBox.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				int key = e.getKeyChar();
				boolean numero = key >= 48 && key <= 57;
				
				if(!numero || yearBox.getText().length() >=4) 
					e.consume();
				
			}
		});
		
		
		telBox = new JTextField();
		telBox.setBounds(150, 190, 155, 19);
		contentPane.add(telBox);
		telBox.setColumns(10);
		
		
		JButton doneButton = new JButton("Registrar usuario");
		doneButton.setBounds(116, 230, 140, 21);
		contentPane.add(doneButton);
		
		
		doneButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				String name = nameBox.getText();
				String cc = ccBox.getText();
				String tel = telBox.getText();
				int year;
				int month;
				int day;
				

				if (yearBox.getText().length() < 4) 					
					year = -1;
				else 
					year = Integer.parseInt(yearBox.getText()) - yearFormat;
				
				
				
				if (monthBox.getText().length() == 0) 
					month = -1;
				else 					
					month = Integer.parseInt(monthBox.getText()) - monthFormat;
				
				
				
				if (dayBox.getText().length() ==0) 
					day = 0;
				else 					
					day = Integer.parseInt(dayBox.getText());
				
				
				
				int indicator = validateData(name, cc, tel, year, month, day);
					
				
				if (indicator == 1) {
					ClienteRegistrado cliente = new ClienteRegistrado (name, cc, year, month, day, tel);
					cine.anadirCliente(cliente);
					JOptionPane.showMessageDialog(null, "Usuario registrado");
					
					PaginaPrincipal m = new PaginaPrincipal(cine);
					m.getFrame().setVisible(true);
					frame.dispose();
				} else if(indicator == -1) {
					JOptionPane.showMessageDialog(null, "Fecha no válida");
				} else {
					JOptionPane.showMessageDialog(null, "Datos incompletos");
				}
				
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
	}
	
	public int validateData (String name, String cc, String tel, int year, int month, int day){
		int indicator = 0;
		
		if(name != null && cc != null && tel != null && year != -1 && month != -1 && day != 0) {
			if (month >= 0 && month < 12 && day > 0 && day <=31) {				
				indicator = 1;
			} else {
				indicator = -1;
			}
		} 
		
		return indicator;
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}

}
