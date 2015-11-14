package entidades;

import java.io.Serializable;

public class Pieza implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8189179161057033205L;

	// True == blancas; False == negras
	protected boolean lado;
	public boolean getLado() {
		return lado;
	}

	public void setLado(boolean lado) {
		this.lado = lado;
	}
	
	protected int x, y;
	
	protected boolean sinMover = true; //Me interesa en peon para en passant, y en torre y rey para enroque
	
	public boolean isSinMover() {
		return sinMover;
	}

	public void mover(){
		sinMover = false;
	}
	
	protected char simbolo;  //Simbolo unicode de la pieza
	
	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	/**Inicializa la pieza 
	 * 
	 * @param L Lado para el que juega
	 */
	public Pieza(boolean L){
		lado = L;
	}

	/**Devuelve un Array 8x8 con 1 en las posiciones permitidas para esa pieza, ignorando las demas piezas
	 * 
	 */
	public int[][] movimientosPermitidos(int columna, int fila, char comer){  //Comer sirve para los peones solamente
		
		int[][] arregloPermitidos = new int[8][8];
			for(int m = 0; m < 8; m++){
				for(int n = 0; n < 8; n++){
					arregloPermitidos[m][n] = 0;
				}
			}
		
		return arregloPermitidos;
	}
	
	

	/**
	 * 
	 */
	
	public void mueve(){
		sinMover = true;
	}

}
