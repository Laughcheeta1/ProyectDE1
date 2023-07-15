package Interfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import Clases.Factura;

public class ShowFactura {

	private JFrame frame;
	private Factura factura;

	/**
	 * Create the application.
	 */
	public ShowFactura(Factura factura) {
		this.factura = factura;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(800, 200, 450, 300);
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel tituloLabel = new JLabel("Factura Compra");
		tituloLabel.setBounds(10, 11, 414, 14);
		frame.getContentPane().add(tituloLabel);
		
		JLabel fechaLabel = new JLabel("");
		fechaLabel.setBounds(10, 36, 414, 14);
		frame.getContentPane().add(fechaLabel);
		
		JLabel lblNewLabel = new JLabel("--------------------------------------------------------------");
		lblNewLabel.setBounds(10, 61, 414, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel numeroFacturaLabel = new JLabel("");
		numeroFacturaLabel.setBounds(10, 86, 414, 14);
		frame.getContentPane().add(numeroFacturaLabel);
		
		JLabel idClienteLabel = new JLabel("");
		idClienteLabel.setBounds(10, 111, 414, 14);
		frame.getContentPane().add(idClienteLabel);
		
		JLabel posicionSillaLabel = new JLabel("");
		posicionSillaLabel.setBounds(10, 136, 414, 14);
		frame.getContentPane().add(posicionSillaLabel);
		
		JLabel precioPagadoLabel = new JLabel("");
		precioPagadoLabel.setBounds(10, 161, 414, 14);
		frame.getContentPane().add(precioPagadoLabel);
		
		JLabel lblNewLabel_5 = new JLabel("--------------------------------------------------------------");
		lblNewLabel_5.setBounds(10, 211, 414, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("Muchas Gracias Por Confiar En Nosotros.");
		lblNewLabel_6.setBounds(10, 236, 414, 14);
		frame.getContentPane().add(lblNewLabel_6);
		
		JLabel metodoPagoLabel = new JLabel("");
		metodoPagoLabel.setBounds(10, 186, 414, 14);
		frame.getContentPane().add(metodoPagoLabel);
		
		fechaLabel.setText("Fecha: " + factura.getFecha());
		numeroFacturaLabel.setText("Numero Factura: " + factura.getNumero());
		idClienteLabel.setText("Id cliente: " + factura.getIdCliente());
		posicionSillaLabel.setText("Posicion Silla: " + factura.getPosicionSilla());
		precioPagadoLabel.setText("Precio pagado: " + Double.toString(factura.getPrecioPagado()));
		metodoPagoLabel.setText("Metodo Pago: " + factura.getMetodoPago());
	}
	
	public JFrame getFrame()
	{
		return this.frame;
	}
}
