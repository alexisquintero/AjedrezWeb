package entidades;

import java.util.ArrayList;

public class Partida {
	
	private Tablero tablero;
	
	private Jugador blancas;
	public Partida(Jugador jugador, Jugador jugador2) {
		blancas = jugador;
		negras = jugador2;
		tablero = new Tablero();
		turno = true;//Empiezan blancas
		idPartida = -1;
	}
	public Jugador getBlancas() {
		return blancas;
	}
	
	public Tablero getTablero() {
		return tablero;
	}
	public void setTablero(Tablero tablero) {
		this.tablero = tablero;
	}

	private Jugador negras;
	public Jugador getNegras() {
		return negras;
	}
	
	private int idPartida;
	public int getIdPartida() {
		return idPartida;
	}
	public void setIdPartida(int idPartida) {
		this.idPartida = idPartida;
	}
	
	private boolean turno; 
	
	public boolean isTurno() {
		return turno;
	}
	public void setTurno(boolean turno) {
		this.turno = turno;
	}
	public char[][] Posiciones() {		
		return tablero.Posiciones();
	}
	public char[] movimiento(StringBuilder desde, StringBuilder hasta) {		
		char[] pieza = tablero.movimiento(desde, hasta);
		if(pieza[0] == 'F'){
			if(turno){
				//sumar una victoria a blancas y finalizar partida
			}else{
				//sumar una victoria a negras y finalizar partida
			}
		}
		turno = turno ? false : true;
		return pieza;		
	}
	public int[][] movimientosPosibles(StringBuilder desde, boolean lado) {
		if (!tablero.esNulo(desde)) {
			if (tablero.getLado(desde) == lado) {
				if (turno == lado) {
					return tablero.movimientosLegales(
							tablero.movimientosPosibles(desde), lado, desde);
				} else {
					return new int[2][2];
				}
			} else {
				return new int[2][2];
			}
		}else{
			return new int[1][1];
		}
	}
	public char promocion(String string) {
		boolean lado = turno ? false : true; //debido a que ya se hizo el movimiento
		return tablero.promocion(string, lado);
		
	}
	public ArrayList<Pieza> getComidasBlancas() {
		return tablero.getComidasBlancas();		
	}
	public ArrayList<Pieza> getComidasNegras() {
		return tablero.getComidasNegras();
	}
	
}
