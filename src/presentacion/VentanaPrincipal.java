package presentacion;

import java.awt.Checkbox;
import java.awt.EventQueue;






import java.awt.Font;

import javax.swing.JFrame;

import negocio.ControladorAjedrez;
import net.miginfocom.swing.MigLayout;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.JRadioButton;

import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.awt.BorderLayout;

import javax.swing.JTextField;
import javax.swing.plaf.FontUIResource;

import entidades.Jugador;
import entidades.Pieza;
import excepciones.ApplicationException;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaPrincipal {
	
	private int estado = 0; //Indica el estado del sistema respecto a los clicks, ninguno == 0, 1ro == 1, 2do == 2
							//El primer click indica la posición de inicio, el segundo click indica la posición de destino
	
	private static ControladorAjedrez ca = new ControladorAjedrez();
	
	private StringBuilder desde = new StringBuilder();
	private StringBuilder hasta = new StringBuilder();
	private static HashMap<String, JButton> botones = new HashMap<String, JButton>();
	private Color colorDesde;
	private int[][] movimientosPosibles = new int[8][8];
	private boolean mostrarPosibles = false;
	private static boolean lado = true;	//Empiezan blancas

	private JFrame frame;
	private JTextField txtInfo;
	private static JTextField txtAyNNegras;
	private static JTextField txtAyNBlancas;
	private JTextField txtNegrasComidas;
	private JTextField txtBlancasComidas;

	/**
	 * Launch the application.
	 * @param ca2 
	 */
	public static void main(ArrayList<Jugador> jugadores, ControladorAjedrez ca2) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				ca = ca2;

				VentanaPrincipal window = new VentanaPrincipal();
				window.frame.setVisible(true);
				window.frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				
				sacarFocusPainted();
				inicializaJugadores(jugadores);
				if(ca.getIdPartida() != -1){
					continuarTablero();
					lado = ca.getLado();
					window.frame.setTitle("Juego número: " + ca.getIdPartida());
				}else{
					inicializarTablero();	
					window.frame.setTitle("Juego número: " + String.valueOf(incializaPartida()));	
				}							
														
			}
			
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					ca.guardarPartida();
				} catch (ApplicationException ae) {
					JOptionPane.showMessageDialog(null, ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		frame.setBounds(100, 100, 719, 531);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow,left][grow,right]", "[30.00,top][60.00,top][59.00,grow,center][60.00,bottom][30.00,bottom]"));		
		
		setUIFont (new javax.swing.plaf.FontUIResource("Serif",Font.PLAIN,60));	//Cambia el tamaño de los caracteres de los botones	
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, "cell 0 0 1 5,grow");
		panel.setLayout(new GridLayout(0, 8, 0, 0));
		
		JButton btnA8 = new JButton("a8");
		btnA8.setActionCommand("A8");	
		btnA8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA8.setForeground(new Color(255, 255, 255));
		btnA8.setBackground(new Color(204, 153, 51));
		panel.add(btnA8);
		
		JButton btnB8 = new JButton("b8");
		btnB8.setActionCommand("B8");
		btnB8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB8.setForeground(new Color(255, 255, 255));
		btnB8.setBackground(new Color(102, 51, 0));
		panel.add(btnB8);

		JButton btnC8 = new JButton("c8");
		btnC8.setActionCommand("C8");
		btnC8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC8.setForeground(new Color(255, 255, 255));
		btnC8.setBackground(new Color(204, 153, 51));
		panel.add(btnC8);
		
		JButton btnD8 = new JButton("d8");
		btnD8.setActionCommand("D8");
		btnD8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD8.setForeground(new Color(255, 255, 255));
		btnD8.setBackground(new Color(102, 51, 0));
		panel.add(btnD8);
		
		JButton btnE8 = new JButton("e8");
		btnE8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE8.setActionCommand("E8");
		btnE8.setForeground(new Color(255, 255, 255));
		btnE8.setBackground(new Color(204, 153, 51));
		panel.add(btnE8);
		
		JButton btnF8 = new JButton("f8");
		btnF8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF8.setActionCommand("F8");
		btnF8.setForeground(new Color(255, 255, 255));
		btnF8.setBackground(new Color(102, 51, 0));
		panel.add(btnF8);
		
		JButton btnG8 = new JButton("g8");
		btnG8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG8.setActionCommand("G8");
		btnG8.setForeground(new Color(255, 255, 255));
		btnG8.setBackground(new Color(204, 153, 51));
		panel.add(btnG8);
		
		JButton btnH8 = new JButton("h8");
		btnH8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH8.setActionCommand("H8");
		btnH8.setForeground(new Color(255, 255, 255));
		btnH8.setBackground(new Color(102, 51, 0));
		panel.add(btnH8);
		
		JButton btnA7 = new JButton("a7");
		btnA7.setActionCommand("A7");
		btnA7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA7.setForeground(new Color(255, 255, 255));
		btnA7.setBackground(new Color(102, 51, 0));
		panel.add(btnA7);
		
		JButton btnB7 = new JButton("b7");
		btnB7.setActionCommand("B7");
		btnB7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB7.setForeground(new Color(255, 255, 255));
		btnB7.setBackground(new Color(204, 153, 51));
		panel.add(btnB7);
		
		JButton btnC7 = new JButton("c7");
		btnC7.setActionCommand("C7");
		btnC7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC7.setForeground(new Color(255, 255, 255));
		btnC7.setBackground(new Color(102, 51, 0));
		panel.add(btnC7);
		
		JButton btnD7 = new JButton("d7");
		btnD7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD7.setActionCommand("D7");
		btnD7.setForeground(new Color(255, 255, 255));
		btnD7.setBackground(new Color(204, 153, 51));
		panel.add(btnD7);
		
		JButton btnE7 = new JButton("e7");
		btnE7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE7.setActionCommand("E7");
		btnE7.setForeground(new Color(255, 255, 255));
		btnE7.setBackground(new Color(102, 51, 0));
		panel.add(btnE7);
		
		JButton btnF7 = new JButton("f7");
		btnF7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF7.setActionCommand("F7");
		btnF7.setForeground(new Color(255, 255, 255));
		btnF7.setBackground(new Color(204, 153, 51));
		panel.add(btnF7);
		
		JButton btnG7 = new JButton("g7");
		btnG7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG7.setActionCommand("G7");
		btnG7.setForeground(new Color(255, 255, 255));
		btnG7.setBackground(new Color(102, 51, 0));
		panel.add(btnG7);
		
		JButton btnH7 = new JButton("h7");
		btnH7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH7.setActionCommand("H7");
		btnH7.setForeground(new Color(255, 255, 255));
		btnH7.setBackground(new Color(204, 153, 51));
		panel.add(btnH7);
		
		JButton btnA6 = new JButton("a6");
		btnA6.setActionCommand("A6");
		btnA6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA6.setForeground(new Color(255, 255, 255));
		btnA6.setBackground(new Color(204, 153, 51));
		panel.add(btnA6);
		
		JButton btnB6 = new JButton("b6");
		btnB6.setActionCommand("B6");
		btnB6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB6.setForeground(new Color(255, 255, 255));
		btnB6.setBackground(new Color(102, 51, 0));
		panel.add(btnB6);
		
		JButton btnC6 = new JButton("c6");
		btnC6.setActionCommand("C6");
		btnC6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC6.setForeground(new Color(255, 255, 255));
		btnC6.setBackground(new Color(204, 153, 51));
		panel.add(btnC6);
		
		JButton btnD6 = new JButton("d6");
		btnD6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD6.setActionCommand("D6");
		btnD6.setForeground(new Color(255, 255, 255));
		btnD6.setBackground(new Color(102, 51, 0));
		panel.add(btnD6);
	
		JButton btnE6 = new JButton("e6");
		btnE6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE6.setActionCommand("E6");
		btnE6.setForeground(new Color(255, 255, 255));
		btnE6.setBackground(new Color(204, 153, 51));
		panel.add(btnE6);
		
		JButton btnF6 = new JButton("f6");
		btnF6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF6.setActionCommand("F6");
		btnF6.setForeground(new Color(255, 255, 255));
		btnF6.setBackground(new Color(102, 51, 0));
		panel.add(btnF6);
		
		JButton btnG6 = new JButton("g6");
		btnG6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG6.setActionCommand("G6");
		btnG6.setForeground(new Color(255, 255, 255));
		btnG6.setBackground(new Color(204, 153, 51));
		panel.add(btnG6);
		
		JButton btnH6 = new JButton("h6");
		btnH6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH6.setActionCommand("H6");
		btnH6.setForeground(new Color(255, 255, 255));
		btnH6.setBackground(new Color(102, 51, 0));
		panel.add(btnH6);
		
		JButton btnA5 = new JButton("a5");
		btnA5.setActionCommand("A5");
		btnA5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA5.setForeground(new Color(255, 255, 255));
		btnA5.setBackground(new Color(102, 51, 0));
		panel.add(btnA5);
		
		JButton btnB5 = new JButton("b5");
		btnB5.setActionCommand("B5");
		btnB5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB5.setForeground(new Color(255, 255, 255));
		btnB5.setBackground(new Color(204, 153, 51));
		panel.add(btnB5);
		
		JButton btnC5 = new JButton("c5");
		btnC5.setActionCommand("C5");
		btnC5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC5.setForeground(new Color(255, 255, 255));
		btnC5.setBackground(new Color(102, 51, 0));
		panel.add(btnC5);
		
		JButton btnD5 = new JButton("d5");
		btnD5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD5.setActionCommand("D5");
		btnD5.setForeground(new Color(255, 255, 255));
		btnD5.setBackground(new Color(204, 153, 51));
		panel.add(btnD5);
		
		JButton btnE5 = new JButton("e5");
		btnE5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE5.setActionCommand("E5");
		btnE5.setForeground(new Color(255, 255, 255));
		btnE5.setBackground(new Color(102, 51, 0));
		panel.add(btnE5);
		
		JButton btnF5 = new JButton("f5");
		btnF5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF5.setActionCommand("F5");
		btnF5.setForeground(new Color(255, 255, 255));
		btnF5.setBackground(new Color(204, 153, 51));
		panel.add(btnF5);
		
		JButton btnG5 = new JButton("g5");
		btnG5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG5.setActionCommand("G5");
		btnG5.setForeground(new Color(255, 255, 255));
		btnG5.setBackground(new Color(102, 51, 0));
		panel.add(btnG5);
		
		JButton btnH5 = new JButton("h5");
		btnH5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH5.setActionCommand("H5");
		btnH5.setForeground(new Color(255, 255, 255));
		btnH5.setBackground(new Color(204, 153, 51));
		panel.add(btnH5);
		
		JButton btnA4 = new JButton("a4");
		btnA4.setActionCommand("A4");
		btnA4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA4.setForeground(new Color(255, 255, 255));
		btnA4.setBackground(new Color(204, 153, 51));
		panel.add(btnA4);
		
		JButton btnB4 = new JButton("b4");
		btnB4.setActionCommand("B4");
		btnB4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB4.setForeground(new Color(255, 255, 255));
		btnB4.setBackground(new Color(102, 51, 0));
		panel.add(btnB4);
		
		JButton btnC4 = new JButton("c4");
		btnC4.setActionCommand("C4");
		btnC4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC4.setForeground(new Color(255, 255, 255));
		btnC4.setBackground(new Color(204, 153, 51));
		panel.add(btnC4);
		
		JButton btnD4 = new JButton("d4");
		btnD4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD4.setActionCommand("D4");
		btnD4.setForeground(new Color(255, 255, 255));
		btnD4.setBackground(new Color(102, 51, 0));
		panel.add(btnD4);
		
		JButton btnE4 = new JButton("e4");
		btnE4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE4.setActionCommand("E4");
		btnE4.setForeground(new Color(255, 255, 255));
		btnE4.setBackground(new Color(204, 153, 51));
		panel.add(btnE4);
		
		JButton btnF4 = new JButton("f4");
		btnF4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF4.setActionCommand("F4");
		btnF4.setForeground(new Color(255, 255, 255));
		btnF4.setBackground(new Color(102, 51, 0));
		panel.add(btnF4);
		
		JButton btnG4 = new JButton("g4");
		btnG4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG4.setActionCommand("G4");
		btnG4.setForeground(new Color(255, 255, 255));
		btnG4.setBackground(new Color(204, 153, 51));
		panel.add(btnG4);
		
		JButton btnH4 = new JButton("h4");
		btnH4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH4.setActionCommand("H4");
		btnH4.setForeground(new Color(255, 255, 255));
		btnH4.setBackground(new Color(102, 51, 0));
		panel.add(btnH4);
		
		JButton btnA3 = new JButton("a3");
		btnA3.setActionCommand("A3");
		btnA3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnA3.setForeground(new Color(255, 255, 255));
		btnA3.setBackground(new Color(102, 51, 0));
		panel.add(btnA3);
		
		JButton btnB3 = new JButton("b3");
		btnB3.setActionCommand("B3");
		btnB3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB3.setForeground(new Color(255, 255, 255));
		btnB3.setBackground(new Color(204, 153, 51));
		panel.add(btnB3);
		
		JButton btnC3 = new JButton("c3");
		btnC3.setActionCommand("C3");
		btnC3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC3.setForeground(new Color(255, 255, 255));
		btnC3.setBackground(new Color(102, 51, 0));
		panel.add(btnC3);
		
		JButton btnD3 = new JButton("d3");
		btnD3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD3.setActionCommand("D3");
		btnD3.setForeground(new Color(255, 255, 255));
		btnD3.setBackground(new Color(204, 153, 51));
		panel.add(btnD3);
		
		JButton btnE3 = new JButton("e3");
		btnE3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE3.setActionCommand("E3");
		btnE3.setForeground(new Color(255, 255, 255));
		btnE3.setBackground(new Color(102, 51, 0));
		panel.add(btnE3);
		
		JButton btnF3 = new JButton("f3");
		btnF3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF3.setActionCommand("F3");
		btnF3.setForeground(new Color(255, 255, 255));
		btnF3.setBackground(new Color(204, 153, 51));
		panel.add(btnF3);
		
		JButton btnG3 = new JButton("g3");
		btnG3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG3.setActionCommand("G3");
		btnG3.setForeground(new Color(255, 255, 255));
		btnG3.setBackground(new Color(102, 51, 0));
		panel.add(btnG3);
		
		JButton btnH3 = new JButton("h3");
		btnH3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH3.setActionCommand("H3");
		btnH3.setForeground(new Color(255, 255, 255));
		btnH3.setBackground(new Color(204, 153, 51));
		panel.add(btnH3);
		
		JButton btnA2 = new JButton("a2");
		btnA2.setActionCommand("A2");
		btnA2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				click(e);			
			}
		});
		btnA2.setForeground(new Color(255, 255, 255));
		btnA2.setBackground(new Color(204, 153, 51));
		panel.add(btnA2);
		
		JButton btnB2 = new JButton("b2");
		btnB2.setActionCommand("B2");
		btnB2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB2.setForeground(new Color(255, 255, 255));
		btnB2.setBackground(new Color(102, 51, 0));
		panel.add(btnB2);
		
		JButton btnC2 = new JButton("c2");
		btnC2.setActionCommand("C2");
		btnC2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC2.setForeground(new Color(255, 255, 255));
		btnC2.setBackground(new Color(204, 153, 51));
		panel.add(btnC2);
		
		JButton btnD2 = new JButton("d2");
		btnD2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD2.setActionCommand("D2");
		btnD2.setForeground(new Color(255, 255, 255));
		btnD2.setBackground(new Color(102, 51, 0));
		panel.add(btnD2);
		
		JButton btnE2 = new JButton("e2");
		btnE2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE2.setActionCommand("E2");
		btnE2.setForeground(new Color(255, 255, 255));
		btnE2.setBackground(new Color(204, 153, 51));
		panel.add(btnE2);
		
		JButton btnF2 = new JButton("f2");
		btnF2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF2.setActionCommand("F2");
		btnF2.setForeground(new Color(255, 255, 255));
		btnF2.setBackground(new Color(102, 51, 0));
		panel.add(btnF2);
		
		JButton btnG2 = new JButton("g2");
		btnG2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG2.setActionCommand("G2");
		btnG2.setForeground(new Color(255, 255, 255));
		btnG2.setBackground(new Color(204, 153, 51));
		panel.add(btnG2);
		
		JButton btnH2 = new JButton("h2");
		btnH2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH2.setActionCommand("H2");
		btnH2.setForeground(new Color(255, 255, 255));
		btnH2.setBackground(new Color(102, 51, 0));
		panel.add(btnH2);
		
		JButton btnA1 = new JButton("a1");
		btnA1.setActionCommand("A1");
		btnA1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {				
				click(e);			
			}
		});
		btnA1.setBackground(new Color(102, 51, 0));
		btnA1.setForeground(new Color(255, 255, 255));
		panel.add(btnA1);
		
		JButton btnB1 = new JButton("b1");
		btnB1.setActionCommand("B1");
		btnB1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnB1.setForeground(new Color(255, 255, 255));
		btnB1.setBackground(new Color(204, 153, 51));
		panel.add(btnB1);
		
		JButton btnC1 = new JButton("c1");
		btnC1.setActionCommand("C1");
		btnC1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnC1.setForeground(new Color(255, 255, 255));
		btnC1.setBackground(new Color(102, 51, 0));
		panel.add(btnC1);
		
		JButton btnD1 = new JButton("d1");
		btnD1.setActionCommand("D1");
		btnD1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnD1.setForeground(new Color(255, 255, 255));
		btnD1.setBackground(new Color(204, 153, 51));
		panel.add(btnD1);
		
		JButton btnE1 = new JButton("e1");
		btnE1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnE1.setActionCommand("E1");
		btnE1.setForeground(new Color(255, 255, 255));
		btnE1.setBackground(new Color(102, 51, 0));
		panel.add(btnE1);
		
		JButton btnF1 = new JButton("f1");
		btnF1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnF1.setActionCommand("F1");
		btnF1.setForeground(new Color(255, 255, 255));
		btnF1.setBackground(new Color(204, 153, 51));
		panel.add(btnF1);
		
		JButton btnG1 = new JButton("g1");
		btnG1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnG1.setActionCommand("G1");
		btnG1.setForeground(new Color(255, 255, 255));
		btnG1.setBackground(new Color(102, 51, 0));
		panel.add(btnG1);
		
		JButton btnH1 = new JButton("h1");
		btnH1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				click(e);
			}
		});
		btnH1.setActionCommand("H1");
		btnH1.setForeground(new Color(255, 255, 255));
		btnH1.setBackground(new Color(204, 153, 51));
		panel.add(btnH1);
		
		JPanel panelJugador2 = new JPanel();
		frame.getContentPane().add(panelJugador2, "cell 1 0,grow");
		panelJugador2.setLayout(new BorderLayout(0, 0));
		
		txtAyNNegras = new JTextField();
		txtAyNNegras.setBackground(UIManager.getColor("Button.background"));
		txtAyNNegras.setEditable(false);
		panelJugador2.add(txtAyNNegras, BorderLayout.CENTER);
		txtAyNNegras.setColumns(10);
		txtAyNNegras.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
		
		JPanel panelComidas2 = new JPanel();
		frame.getContentPane().add(panelComidas2, "cell 1 1,grow");
		panelComidas2.setLayout(new BorderLayout(0, 0));
		
		txtNegrasComidas = new JTextField();
		txtNegrasComidas.setBackground(Color.WHITE);
		txtNegrasComidas.setEditable(false);
		panelComidas2.add(txtNegrasComidas, BorderLayout.CENTER);
		txtNegrasComidas.setColumns(10);
		txtNegrasComidas.setForeground(Color.BLACK);
		
		JPanel panelInformacionAdicional = new JPanel();
		frame.getContentPane().add(panelInformacionAdicional, "cell 1 2,grow");
		panelInformacionAdicional.setLayout(new BorderLayout(0, 0));
		
		txtInfo = new JTextField();
		txtInfo.setEditable(false);
		panelInformacionAdicional.add(txtInfo, BorderLayout.CENTER);
		txtInfo.setColumns(10);
		
		JButton btnOpciones = new JButton("Opciones");
		btnOpciones.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				clickOpciones();				
			}
		});
		panelInformacionAdicional.add(btnOpciones, BorderLayout.SOUTH);
		
		JPanel panelComidas1 = new JPanel();
		frame.getContentPane().add(panelComidas1, "cell 1 3,grow");
		panelComidas1.setLayout(new BorderLayout(0, 0));
		
		txtBlancasComidas = new JTextField();
		txtBlancasComidas.setBackground(Color.BLACK);
		txtBlancasComidas.setEditable(false);
		panelComidas1.add(txtBlancasComidas, BorderLayout.CENTER);
		txtBlancasComidas.setColumns(10);
		txtBlancasComidas.setForeground(Color.WHITE);
		
		JPanel panelJugador1 = new JPanel();
		frame.getContentPane().add(panelJugador1, "cell 1 4,grow");
		panelJugador1.setLayout(new BorderLayout(0, 0));
		
		txtAyNBlancas = new JTextField();
		txtAyNBlancas.setBackground(new Color(204, 0, 0));
		txtAyNBlancas.setEditable(false);
		panelJugador1.add(txtAyNBlancas, BorderLayout.CENTER);
		txtAyNBlancas.setColumns(10);
		txtAyNBlancas.setHorizontalAlignment((int) JTextField.CENTER_ALIGNMENT);
		
		botones.put("A1",btnA1);botones.put("B1",btnB1);botones.put("C1",btnC1);botones.put("D1",btnD1);botones.put("E1",btnE1);botones.put("F1",btnF1);botones.put("G1",btnG1);botones.put("H1",btnH1);
		botones.put("A2",btnA2);botones.put("B2",btnB2);botones.put("C2",btnC2);botones.put("D2",btnD2);botones.put("E2",btnE2);botones.put("F2",btnF2);botones.put("G2",btnG2);botones.put("H2",btnH2);
		botones.put("A3",btnA3);botones.put("B3",btnB3);botones.put("C3",btnC3);botones.put("D3",btnD3);botones.put("E3",btnE3);botones.put("F3",btnF3);botones.put("G3",btnG3);botones.put("H3",btnH3);
		botones.put("A4",btnA4);botones.put("B4",btnB4);botones.put("C4",btnC4);botones.put("D4",btnD4);botones.put("E4",btnE4);botones.put("F4",btnF4);botones.put("G4",btnG4);botones.put("H4",btnH4);
		botones.put("A5",btnA5);botones.put("B5",btnB5);botones.put("C5",btnC5);botones.put("D5",btnD5);botones.put("E5",btnE5);botones.put("F5",btnF5);botones.put("G5",btnG5);botones.put("H5",btnH5);
		botones.put("A6",btnA6);botones.put("B6",btnB6);botones.put("C6",btnC6);botones.put("D6",btnD6);botones.put("E6",btnE6);botones.put("F6",btnF6);botones.put("G6",btnG6);botones.put("H6",btnH6);
		botones.put("A7",btnA7);botones.put("B7",btnB7);botones.put("C7",btnC7);botones.put("D7",btnD7);botones.put("E7",btnE7);botones.put("F7",btnF7);botones.put("G7",btnG7);botones.put("H7",btnH7);
		botones.put("A8",btnA8);botones.put("B8",btnB8);botones.put("C8",btnC8);botones.put("D8",btnD8);botones.put("E8",btnE8);botones.put("F8",btnF8);botones.put("G8",btnG8);botones.put("H8",btnH8);
		
		
	}
	
	private void click(ActionEvent e){
		
		estado = estado == 0 ? 1 : estado;
		
		switch (estado) {
		
		case 1:
			desde.append(e.getActionCommand());
			estado = 2;
			colorDesde = botones.get(desde.toString()).getBackground();
			botones.get(desde.toString()).setBackground(Color.RED);		
			movimientosPosibles = ca.getMovimientosPosibles(desde, lado);
			if(movimientosPosibles.length == 1){	//No deja mover desde una posición sin pieza
				botones.get(desde.toString()).setBackground(colorDesde);
				desde.delete(0, 2);
				estado = 0;
				break;
			}
			if(movimientosPosibles.length == 2){
				txtInfo.setText("Le toca al otro jugador"); 
				botones.get(desde.toString()).setBackground(colorDesde);
				desde.delete(0, 2);
				estado = 0;
			}else{
				actualizarTablero(movimientosPosibles);
			}			
			break;
		
		case 2:
			hasta.append(e.getActionCommand());	
			if(desde.toString().equals(hasta.toString())){	//Si hago click en la misma posición se cancela el movimiento
				txtInfo.setText("");
				botones.get(desde.toString()).setBackground(colorDesde);
				hasta.delete(0, 2);
				desde.delete(0, 2);
				estado = 0;
				despintarTablero();
				break;
			}
			int columnaHasta = Character.getNumericValue(hasta.charAt(0))-10;
			int filaHasta = Character.getNumericValue(hasta.charAt(1))-1;
			if(movimientosPosibles[filaHasta][columnaHasta] == 1){ //Controla que hasta sea una posición dentro de las permitidas por array de movimientos posibles
				char[] pieza = ca.movimiento(desde, hasta);
				if(pieza[0] == 'F'){
					String texto = lado ? "Blancas ganan" : "Negras ganan";
					txtInfo.setText(texto);
				}else if(pieza[0] == 'P'){
					pieza[0] = promocion();
					txtInfo.setText("Promoción");
					actualizarTablero(pieza[0], desde, hasta, '\u0000');  //Cambiar pieza por lo que devuelva promocion
					botones.get(desde.toString()).setBackground(colorDesde);
					estado = 0;		
					lado = lado ? false : true;
					desde.delete(0, 2);
				}else{
					actualizarTablero(pieza[0], desde, hasta, '\u0000');
					botones.get(desde.toString()).setBackground(colorDesde);
					estado = 0;		
					lado = lado ? false : true;
					desde.delete(0, 2);
					if(txtInfo.getText().length() != 0){
						txtInfo.setText("");
					}					
				}
				actualizarComidas();
				actualizarActivo();
				if(pieza[1] != '\u0000'){
					StringBuilder desde1 = new StringBuilder();
					StringBuilder hasta1 = new StringBuilder();
					if(pieza[2] == '0'){	//Lado blancas	1 + ?
						if(pieza[3] == '3'){	//Izquierda ? + A			3 derecha 5 izquierda ? NO
							desde1.append("A1");
						}else if(pieza[3] == '5'){				//Derecha ? + H
							desde1.append("H1");
						}			
					}else if(pieza[2] == '7'){					//Lado negras 8 + ?
						if(pieza[3] == '3'){	//Izquierda ? + A
							desde1.append("A8");
						}else if(pieza[3] == '5'){				//Derecha ? + H
							desde1.append("H8");
						}
					}
					
					switch(desde1.toString()){
					case "A1": hasta1.append("D1"); break;
					case "A8": hasta1.append("D8"); break;
					case "H1": hasta1.append("F1"); break;
					case "H8": hasta1.append("F8"); break;
					}
					actualizarTablero(pieza[1], desde1, hasta1, 'E'); 
				}
			}else{
				txtInfo.setText("Movimiento ilegal");				
			}
			hasta.delete(0, 2);
			
			break;

		default:
			break;
		}
		
		ca.actualizarTablero(estado);
		
	}

	private static void inicializarTablero(){
		
		char[][] posicion = ca.inicializarTablero();
		StringBuilder nombre = new StringBuilder();
		Color color = Color.WHITE;
	
		for (char i = 'A'; i < 'I'; i++) {
			for (int j = 1; j < 9; j++) {
				nombre.delete(0, 2);	
				nombre.append("" + i + j);
				if((j == 1) || (j == 2)){
					color = Color.WHITE;
				}
				if((j == 7) || (j == 8)){
					color = Color.BLACK;
				}
				botones.get(nombre.toString()).setForeground(color);
				botones.get(nombre.toString()).setText(Character.toString(posicion[j-1][Character.getNumericValue(i)-10]));
			}									
		}
	}
	
	private void actualizarTablero(char movimiento, StringBuilder desde, StringBuilder hasta, char enroque){
		botones.get(desde.toString()).setText(Character.toString('\u0000'));
		Color color = lado ? Color.WHITE : Color.BLACK;
		if(enroque != '\u0000'){
			color = !lado ? Color.WHITE : Color.BLACK;
		}	
		botones.get(hasta.toString()).setForeground(color);
		botones.get(hasta.toString()).setText(Character.toString(movimiento));
	}
	
	private void actualizarTablero(int[][] arregloPermitidos){
		StringBuilder nombre = new StringBuilder();
		if (mostrarPosibles) {
			for (char i = 'A'; i < 'I'; i++) {
				for (int j = 1; j < 9; j++) {
					if (arregloPermitidos[j - 1][Character.getNumericValue(i) - 10] == 1) {
						nombre.delete(0, 2);
						nombre.append("" + i + j);
						botones.get(nombre.toString()).setBackground(Color.YELLOW);
					}
				}
			}
		}
	}
	
	private void despintarTablero(){
//TODO: para despintar el amarillo guardar una máscara con los colores default de cada cuadro y 
//		guardar el array de movimientos posibles como variable global si se necesita
	}
	
	private void actualizarComidas() {
		ArrayList<Pieza> comidas = new ArrayList<Pieza>();
		if(lado){	//Ya se cambió el lado
			comidas = ca.getComidasBlancas();
		}else{
			comidas = ca.getComidasNegras();
		}
		
		if(!comidas.isEmpty()){
			StringBuffer comida = new StringBuffer();
			for (int i = 0; i < comidas.size(); i++) {
				comida.append(comidas.get(i).getSimbolo());
			}
			if(lado){
				txtBlancasComidas.setText(comida.toString());
			}else{
				txtNegrasComidas.setText(comida.toString());
			}
		}
		
	}
	
	private void actualizarActivo() {
		if(lado){
			txtAyNBlancas.setBackground(new Color(204, 0, 0));
			txtAyNNegras.setBackground(UIManager.getColor("Button.background"));
		}else{
			txtAyNBlancas.setBackground(UIManager.getColor("Button.background"));
			txtAyNNegras.setBackground(new Color(204, 0, 0));
		}
		
		
	}
	
	private void clickOpciones(){
	
		JPanel myPanel = new JPanel();
//		myPanel.setFont(new Font("Serif",Font.PLAIN,20));
		Checkbox cbMovimientosPosibles = new Checkbox("Mostrar movimientos posibles", false);
		cbMovimientosPosibles.setFont(new Font("Serif",Font.PLAIN,20));
	    myPanel.add(cbMovimientosPosibles);
	    
	    UIManager.put("OptionPane.buttonFont", new FontUIResource(new Font("Serif",Font.PLAIN,20)));
	    int result = JOptionPane.showConfirmDialog(null, myPanel,"Opciones", JOptionPane.OK_CANCEL_OPTION);
	    if (result == JOptionPane.OK_OPTION) {
	    	mostrarPosibles = cbMovimientosPosibles.getState();
	    }
	}
	
	public static void setUIFont (javax.swing.plaf.FontUIResource f){	//Cambia el tamaño de los caracteres de los botones
	    @SuppressWarnings("rawtypes")
		java.util.Enumeration keys = UIManager.getDefaults().keys();
	    while (keys.hasMoreElements()) {
	      Object key = keys.nextElement();
	      Object value = UIManager.get (key);
	      if (value != null && value instanceof javax.swing.plaf.FontUIResource)
	        UIManager.put (key, f);
	   }
	} 
	
	private static void sacarFocusPainted(){
		StringBuilder nombre = new StringBuilder();
		for (char i = 'A'; i < 'I'; i++) {
			for (int j = 1; j < 9; j++) {
				nombre.delete(0, 2);	
				nombre.append("" + i + j);
				botones.get(nombre.toString()).setFocusPainted(false);
			}									
		}
		
	}
	
	private char promocion(){
	    
	    char alfil, torre, caballo, reina;
	    
	    if(lado){
	    	alfil = '\u2657';
	    	torre = '\u2656';
	    	caballo = '\u2658';
	    	reina = '\u2655';
	    }else{
	    	alfil = '\u265D';
	    	torre = '\u265C';
	    	caballo = '\u265E';
	    	reina = '\u265B';
	    }
	    
	    JRadioButton rbAlfil = new JRadioButton(Character.toString(alfil));
	    JRadioButton rbTorre = new JRadioButton(Character.toString(torre));
	    JRadioButton rbCaballo = new JRadioButton(Character.toString(caballo));
	    JRadioButton rbReina =  new JRadioButton(Character.toString(reina), true);
	    
	    rbAlfil.setActionCommand("Alfil"); rbTorre.setActionCommand("Torre"); rbCaballo.setActionCommand("Caballo"); rbReina.setActionCommand("Reina");
	    
	    ButtonGroup group = new ButtonGroup();
	    
	    group.add(rbReina); group.add(rbCaballo); group.add(rbTorre); group.add(rbAlfil);
	    
		JPanel myPanel = new JPanel();
	    myPanel.add(new JLabel("Promocionar peon a:"));
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(rbAlfil);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(rbCaballo);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(rbTorre);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    myPanel.add(rbReina);
	    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
	    
	    String[] options = {"OK"};
	    int result = JOptionPane.showOptionDialog(null, myPanel,"Opciones", JOptionPane.NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options , options[0]);
	    if (result == 0) {
	    	return ca.promocion(group.getSelection().getActionCommand().toString());    	
	    }
	    return 'P';   
	}
	/**Pone los nombres en la ventana a la derecha
	 * 
	 * @param jugadores
	 */
	private static void inicializaJugadores(ArrayList<Jugador> jugadores) {	
		txtAyNBlancas.setText(jugadores.get(0).getNombre() + " " + jugadores.get(0).getApellido());
		txtAyNNegras.setText(jugadores.get(1).getNombre() + " " + jugadores.get(1).getApellido());
	}

	private static int incializaPartida() {
		try {
			return ca.inicializaPartida();
		} catch (ApplicationException ae) {
			JOptionPane.showMessageDialog(null, ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		return 0;
		
	}	
	
	private static void continuarTablero(){
		Pieza[][] piezas = ca.getPiezas();
		StringBuilder posicion = new StringBuilder();
		for (char i = 'A'; i < 'I'; i++) {
			for (int j = 1; j < 9; j++) {	
				posicion.append("" + i + j);
				if (piezas[j - 1][Character.getNumericValue(i) - 10] != null) {									
					botones.get(posicion.toString()).setText(Character.toString(piezas[j - 1][Character.getNumericValue(i) - 10].getSimbolo()));
					if(piezas[j - 1][Character.getNumericValue(i) - 10].getLado()){
						botones.get(posicion.toString()).setForeground(Color.WHITE);
					}else{
						botones.get(posicion.toString()).setForeground(Color.BLACK);
					}
				}else{
					botones.get(posicion.toString()).setText(Character.toString('\u0000'));
				}
				posicion.delete(0, 2);
			}
		}
	}

}

