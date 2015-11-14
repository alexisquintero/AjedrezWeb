package entidades;

public class Rey extends Pieza {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7031654293599417454L;

	public Rey(boolean L) {
		super(L);
		if(L){
			simbolo = '\u2654';
		}else{
			simbolo = '\u265A';
		}		
	}

	/**Devuelve un Array 8x8 con 1 en las posiciones permitidas para esa pieza, ignorando las demas piezas
	 * 
	 */
	public int[][] movimientosPermitidos(int fila, int columna, char comer){
		
		//Inicializo el arreglo
		int[][] arregloPermitidos = new int[8][8];
		for(int m = 0; m < 8; m++){
			for(int n = 0; n < 8; n++){
				arregloPermitidos[m][n] = 0;
			}
		} 
		
		//Como máximo 8 posibilidades de movimiento
			for(int i=0; i < 8; i++){
					
				int x = columna;
				int y = fila;
					
						
				switch(i){
					case 0:
						x += 1; y += 1; 
						break;
					case 1:
						x += 1; y -= 1;
						break;
					case 2:
						x -= 1; y += 1;
						break;
					case 3:
						x -= 1; y -= 1;
						break;
					case 4:
						x += 0; y += 1;
						break;
					case 5:
						x -= 1; y += 0;
						break;
					case 6:
						x += 1; y -= 0;
						break;
					case 7:
						x -= 0; y -= 1;
						break;					
					}
						
					//Mientras no se salga de los límites del tablero
					if((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
						arregloPermitidos[x][y] = 1;
					}
				}	
			
			if(sinMover){	//Para enroque
				arregloPermitidos[columna][fila + 2] = 1;
				arregloPermitidos[columna][fila - 2] = 1;
			}

		return arregloPermitidos;
	}
	
	/**
	 * 
	 */
	public void jaqueMate(){
		
	}

}
