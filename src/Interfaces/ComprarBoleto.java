package Interfaces;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.Random;
import Main.Cine;
import Clases.Pelicula;
import Clases.ClienteRegistrado;
import Clases.Factura;
import Clases.SalaCine;
import Clases.Silla;


public class ComprarBoleto extends JFrame {

    private static final long serialVersionUID = 1L;
    private Cine cine;
    private JPanel panelPrincipal;
    private JComboBox<Pelicula> peliculasDisponibles;
    private JComboBox<String> horariosDisponibles;
    private JButton botonPagado;
    private JButton botonPaginaPrincipal;
    private JLabel informacionSilla;
    private JLabel precioSilla;
    private JLabel clienteLabel;
    private JLabel precioSillaDescuento;
    private JRadioButton qrRB;
    private JRadioButton tarjetaRB;
    private JRadioButton efectivoRB;
    private String metodoPago;
    private Pelicula pelicula;
    private double precio = 0;
    private double descuento = 0;
    private String nombreCliente = "Cliente no registrado";
    private String turno = "Turno 1";
    private Pelicula [] pelisTurno = new Pelicula[0];
    private int filas = 1;
    private int columnas = 1;
    private Silla silla;
    private ClienteRegistrado cliente;

    public ComprarBoleto(Cine cine, String idCliente) {
        // Inicializa la ventana principal
    	super("Comprar Boleta");
    	this.cine = cine;
        
        cliente = cine.buscarCliente(idCliente);
    	
        if (cliente != null) {
        	nombreCliente = this.cine.getCliente(idCliente).getNombre();
        	descuento = 0.02 + (0.1 - 0.02) * (new Random()).nextDouble();
        }
  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1000, 600));

        // Inicializa el panel principal, superior, derecho e inferior
        panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelDerecha = new JPanel(new GridLayout(0, 1));
        panelPrincipal.add(panelDerecha , BorderLayout.EAST);
        JPanel panelSuperior = new JPanel(new GridLayout(0, 1));
        panelPrincipal.add(panelSuperior , BorderLayout.NORTH);
        JPanel panelCentral = new JPanel(new BorderLayout());
        panelPrincipal.add(panelCentral , BorderLayout.CENTER);
        JPanel panelSillas = new JPanel(new GridLayout(filas, columnas));
        panelCentral.add(panelSillas);
        add(panelPrincipal);
        
        
        // Agrega la lista desplegable de horarios disponibles
        horariosDisponibles = generarComboBoxTurnos();
        panelSuperior.add(horariosDisponibles);
        horariosDisponibles.setSelectedItem("");
        // Agrega la lista desplegable de películas disponibles
       
        
        peliculasDisponibles = new JComboBox<>(pelisTurno);
        pelicula = (Pelicula) peliculasDisponibles.getSelectedItem();
        panelSuperior.add(peliculasDisponibles);
        
        // Agrega los botones 
        botonPaginaPrincipal = new JButton("Cancelar");
        informacionSilla = new JLabel("Info Silla");
        informacionSilla.setBounds(getX(), getY(), 100, 100);
        precioSilla = new JLabel("Precio: " + precio);
        clienteLabel = new JLabel(nombreCliente);
        precioSillaDescuento = new JLabel("Precio con descuento: " + precio*(1-descuento));
        botonPagado = new JButton("Finalizar transacción");
        botonPagado.setEnabled(false);
        panelDerecha.add(botonPaginaPrincipal);
        panelDerecha.add(informacionSilla);
        panelDerecha.add(precioSilla);
        panelDerecha.add(clienteLabel);
        panelDerecha.add(precioSillaDescuento);
        panelDerecha.add(botonPagado);

        
        // Crear los radio buttons
        qrRB = new JRadioButton("QR");
        tarjetaRB = new JRadioButton("Tarjeta");
        efectivoRB = new JRadioButton("Efectivo");
        ButtonGroup groupBotones = new ButtonGroup();
        groupBotones.add(qrRB);
        groupBotones.add(tarjetaRB);
        groupBotones.add(efectivoRB);
        JPanel panelRadios = new JPanel(new GridLayout(3, 1));
        panelRadios.add(qrRB);
        panelRadios.add(tarjetaRB);
        panelRadios.add(efectivoRB);
        
        panelDerecha.add(panelRadios,5);
       
     
        //Pantalla 
        JPanel panelPantalla = new JPanel();  
        panelPantalla.setBackground(Color.BLUE);
        panelCentral.add(panelPantalla , BorderLayout.SOUTH);
        panelPantalla.add(new JLabel("Pantalla"));
        
        //Listeners
        //Listeners de botones silla
        botonPaginaPrincipal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí va el código que se va a ejecutar cuando se presione cancelar
            	PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				setVisible(false);
            }
        });
        botonPagado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Aquí es donde se realizarán todas las acciones al back
            	
            	peliculasDisponibles.setSelectedItem(null);
            	panelSillas.removeAll();
            	panelSillas.validate();
            	panelSillas.repaint();
            	
            	Factura f = cine.comprarBoleto(silla, precio*(1-descuento), cliente, getMetodoPago());
            	
            	ShowFactura s = new ShowFactura(f);
            	s.getFrame().setVisible(true);
            	
 				PaginaPrincipal m = new PaginaPrincipal(cine);
				m.getFrame().setVisible(true);
				setVisible(false);
                
            }
        });
        
        // Listeners de Botones
        qrRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                metodoPago = "QR";
                
            }
        });
        tarjetaRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                metodoPago = "Tarjeta";
            }
        });
        efectivoRB.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                metodoPago = "Efectivo";
            }
        });
        efectivoRB.setSelected(true);
        
        horariosDisponibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                turno = (String) horariosDisponibles.getSelectedItem();
                pelisTurno = actualizarTurno();
                peliculasDisponibles.setModel(new DefaultComboBoxModel<> (pelisTurno));
                peliculasDisponibles.setSelectedItem(null);
                horariosDisponibles.removeItem("");
                panelSillas.removeAll();
                panelSillas.validate();
                panelSillas.repaint();
            }
        });
        
        peliculasDisponibles.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if (peliculasDisponibles.getSelectedItem() != null) {
            		 pelicula = (Pelicula) peliculasDisponibles.getSelectedItem();
                     int k = 0;
                     while(k<pelisTurno.length && pelisTurno[k] !=pelicula) k++;  
                     SalaCine sala = cine.getSalas()[k];
                      filas = sala.getSillas().length;
                      columnas = sala.getSillas()[0].length;
                      panelSillas.removeAll();
                      panelSillas.setLayout(new GridLayout(filas, columnas));
                      
                      JButton[][] sillas = new JButton[filas][columnas];
                      for (int i = 0; i < filas; i++) {
                          for ( int j = 0; j < columnas; j++) {
                          	final int fila = i;
                              final int columna = j;
                              JButton boton = new JButton();
                              if(!sala.getSillas()[getTurnoEscogido()][fila][columna].isDisponibilidad()) {
                            	  boton.setBackground(Color.RED);
                              }
                              // Configurar el botón aquí según el estado de la silla
                              panelSillas.add(boton);
                              sillas[i][j] = boton;
                              
                              // Agregar ActionListener al botón
                              boton.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      // Actualizar la información de la silla seleccionada y habilitar/deshabilitar botón de pago
                                	  
                                	  silla = sala.getSillas()[getTurnoEscogido()][fila][columna];
                                	  actualizarInfoSilla();
                                      
                                      precio = silla.getPrecio();
                                      precioSilla.setText("Precio: $" + precio);
                                      precioSillaDescuento.setText("Precio con descuento: $" + Math.round(precio*(1-descuento)));
                                      if (silla.isDisponibilidad()) {
                                    	  botonPagado.setEnabled(true);
                                      }
                                      else
                                      {
                                    	  botonPagado.setEnabled(false);
                                      }
                                  }
                              });
                          }
                      }
                      panelSillas.validate();
                      panelSillas.repaint();
            	}
            }
        });
        
        // Muestra la ventana
        pack();
        
    }
    
    private JComboBox<String> generarComboBoxTurnos()
    {
    	switch(cine.getHora())
    	{
    		case 1:
    			return new JComboBox<>(new String[]{"","Turno 1", "Turno 2", "Turno 3","Turno 4"});
    			
    		case 2:
    			return new JComboBox<>(new String[]{"","Turno 2", "Turno 3","Turno 4"});
    			
    		case 3:
    			return new JComboBox<>(new String[]{"", "Turno 3","Turno 4"});
    			
    		default:
    			return new JComboBox<>(new String[]{"", "Turno 4"});
    	}
    }
    
    private void actualizarInfoSilla()
    {
    	if (!(silla.isDisponibilidad()) && silla.getCliente() == null)
  	  {
  		  informacionSilla.setText("Posicion: " + silla.getPosicion() + "\n    Ocupada por: *Cliente No Registrado*");
  	  }
  	  else if (!(silla.isDisponibilidad()))
  	  {
  		  informacionSilla.setText("Posicion: " + silla.getPosicion() + "\n    Ocupada por: " + silla.getCliente().getNombre());
  	  }
  	  else
  	  {
  		  informacionSilla.setText("Posicion: " + silla.getPosicion());
  	  }
    }
   
    private String getMetodoPago()
    {
	    return this.metodoPago;
    }
    
    private int getTurnoEscogido()
    {
    	switch(turno)
    	{
    		case "Turno 1":
    			return 0;
    		case "Turno 2":
    			return 1;
    		case "Turno 3":
    			return 2;
    		default:
    			return 3;
    	}
    }

    private Pelicula [] actualizarTurno() {
	    switch(turno) {
        case "Turno 1":
        	return cine.getHorario()[0];
        case "Turno 2":
        	return cine.getHorario()[1];
        case "Turno 3":
        	return cine.getHorario()[2];
        case "Turno 4":
        	return cine.getHorario()[3];
        default:
        	return new Pelicula[0];
       }
   }
}
