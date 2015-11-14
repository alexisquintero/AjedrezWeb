package presentacion;

import java.awt.EventQueue;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import negocio.ControladorAjedrez;
import net.miginfocom.swing.MigLayout;

import javax.swing.JTextField;
import javax.swing.JLabel;

import entidades.Jugador;
import entidades.Partida;
import excepciones.ApplicationException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;

public class Login {
	
	private ControladorAjedrez ca = new ControladorAjedrez();
	private int crearJugadores = 0;
	ArrayList<Jugador> jugadores = new ArrayList<Jugador>();

	private JFrame frame;
	private JTextField txtBlancas;
	private JTextField txtNegras;
	private JButton btnContinuar;
	private JButton btnBuscar;
	private JButton btnCrearJugadores;
	private JLabel lblJugador;
	private JLabel lblJugador_1;
	private JLabel lblBlancasNyA;
	private JLabel lblNegrasNyA;
	private JLabel lblBlancasNombreyapellido;
	private JLabel lblNegrasNombreYApellido;
	private JButton btnJuegoNuevo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setEnabled(false);
		frame.setBounds(100, 100, 429, 239);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[grow,left][40px][40px,grow][10px,center][40px][right]", "[top][fill][10,center][center][10px,center][center][10][][grow,bottom]"));
		
		lblJugador = new JLabel("Jugador 1");
		frame.getContentPane().add(lblJugador, "cell 0 0,alignx trailing");
		
		txtBlancas = new JTextField();
		txtBlancas.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtBlancas.setText(txtBlancas.getText().equals("DNI Blancas")?"":txtBlancas.getText());
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtBlancas.setText(txtBlancas.getText().length() == 0?"DNI Blancas":txtBlancas.getText());
			}
		});
		txtBlancas.setText("DNI Blancas");
		frame.getContentPane().add(txtBlancas, "cell 1 0 2 1,growx");
		txtBlancas.setColumns(10);
		
		lblBlancasNyA = new JLabel("");
		frame.getContentPane().add(lblBlancasNyA, "flowx,cell 4 0");
		
		lblJugador_1 = new JLabel("Jugador 2");
		frame.getContentPane().add(lblJugador_1, "cell 0 1,alignx right");
		
		txtNegras = new JTextField();
		txtNegras.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				txtNegras.setText(txtNegras.getText().equals("DNI Negras")?"":txtNegras.getText());
			}
			@Override
			public void focusLost(FocusEvent e) {
				txtNegras.setText(txtNegras.getText().length() == 0?"DNI Negras":txtNegras.getText());
			}
		});
		txtNegras.setText("DNI Negras");
		frame.getContentPane().add(txtNegras, "cell 1 1 2 1,growx");
		txtNegras.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				buscar(txtBlancas.getText(), txtNegras.getText());				
			}
		});
		
		lblNegrasNyA = new JLabel("");
		frame.getContentPane().add(lblNegrasNyA, "flowx,cell 4 1");
		frame.getContentPane().add(btnBuscar, "cell 1 3,grow");
		
		btnCrearJugadores = new JButton("Crear jugador");
		btnCrearJugadores.setEnabled(false);
		btnCrearJugadores.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				crearJugador();			
			}	
		});
		frame.getContentPane().add(btnCrearJugadores, "cell 4 3,grow");
		
		btnJuegoNuevo = new JButton("Juego nuevo");
		btnJuegoNuevo.setEnabled(false);
		btnJuegoNuevo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ca.setIdPartida();
				VentanaPrincipal.main(jugadores, ca);			
			}
		});
		frame.getContentPane().add(btnJuegoNuevo, "cell 1 5,grow");
		
		btnContinuar = new JButton("Continuar");
		btnContinuar.setEnabled(false);
		btnContinuar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clickContinuar();
			}		
		});
		frame.getContentPane().add(btnContinuar, "cell 1 7,grow");
		
		lblBlancasNombreyapellido = new JLabel("");
		lblBlancasNombreyapellido.setEnabled(false);
		frame.getContentPane().add(lblBlancasNombreyapellido, "cell 4 0,grow");
		
		lblNegrasNombreYApellido = new JLabel("");
		lblNegrasNombreYApellido.setEnabled(false);
		frame.getContentPane().add(lblNegrasNombreYApellido, "cell 4 1,grow");
	}
	
	private void buscar(String j1, String j2){
		try {
			
			jugadores = ca.buscar(Integer.parseInt(txtBlancas.getText()), Integer.parseInt(txtNegras.getText()));
			
			if(!(jugadores.get(0) == null)){
				lblBlancasNombreyapellido.setText(jugadores.get(0).getNombre() + " " + jugadores.get(0).getApellido());				
			}else{
				lblBlancasNombreyapellido.setText("No existe");
				btnCrearJugadores.setEnabled(true);
				crearJugadores += 1;
			}
			
			if(!(jugadores.get(1) == null)){
				lblNegrasNombreYApellido.setText(jugadores.get(1).getNombre() + " " + jugadores.get(1).getApellido());
			}else{
				lblNegrasNombreYApellido.setText("No existe");
				btnCrearJugadores.setEnabled(true);
				crearJugadores += 1;
			}
			
			if((jugadores.get(0) == null) && (jugadores.get(1) == null)){
				btnCrearJugadores.setText("Crear jugadores");
			}
			
			if(!(jugadores.get(0) == null) && !(jugadores.get(1) == null)){
				btnJuegoNuevo.setEnabled(true);
				ca.inicializaJugadores(jugadores);
				Partida partida = ca.buscaJugadorPartida();
				if(partida.getIdPartida() != -1){
					btnContinuar.setEnabled(true);
				}
			}
								
		} catch (ApplicationException | NumberFormatException ae) {
			JOptionPane.showMessageDialog(null, ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void crearJugador() {
		try{
			for (int i = 0; i < crearJugadores; i++) {
				Jugador j = new Jugador();
				
				JTextField jtfDni = new JTextField(5);
				JTextField jtfNombre = new JTextField(5);
				JTextField jtfApellido = new JTextField(5);				
			
				JPanel myPanel = new JPanel();
			    myPanel.add(new JLabel("DNI:"));
			    myPanel.add(jtfDni);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Nombre:"));
			    myPanel.add(jtfNombre);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			    myPanel.add(new JLabel("Apellido:"));
			    myPanel.add(jtfApellido);
			    myPanel.add(Box.createHorizontalStrut(15)); // a spacer

			    int result = JOptionPane.showConfirmDialog(null, myPanel, "Opciones", JOptionPane.OK_CANCEL_OPTION);
			    if (result == JOptionPane.OK_OPTION) {
			    	j.setDni(Integer.parseInt(jtfDni.getText()));
			    	j.setNombre(jtfNombre.getText());
			    	j.setApellido(jtfApellido.getText());
			    	
			    	ca.crearJugador(j);
			    }
														
			}
			crearJugadores = 0;
			btnCrearJugadores.setEnabled(false);
		}catch(ApplicationException ae){
			JOptionPane.showMessageDialog(null, ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void clickContinuar() {
		try {
			ca.continuar();
			VentanaPrincipal.main(jugadores, ca);
		} catch (ApplicationException ae) {
			JOptionPane.showMessageDialog(null, ae.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
		}
		
	}

}
