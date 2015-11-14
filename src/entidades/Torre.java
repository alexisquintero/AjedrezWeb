package entidades;

public class Torre extends Pieza {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4807391715833782139L;

	public Torre(boolean L) {
		super(L);
		if(L){
			simbolo = '\u2656';
		}else{
			simbolo = '\u265C';
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
		
		//Como máximo 4 posibilidades de movimiento
		for(int i=0; i < 4; i++){
			
			int x = columna;
			int y = fila;
			
			//Como máximo 7 movimientos contiguos (es una palabra contiguo? Creo que si)
			for(int j = 0; j < 8; j++){
				
				switch(i){
				case 0:
					x += 0; y += 1;
					break;
				case 1:
					x += 1; y -= 0;
					break;
				case 2:
					x -= 1; y += 0;
					break;
				case 3:
					x -= 0; y -= 1;
					break;
				}
				
				//Mientras no se salga de los límites del tablero
				if((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
					arregloPermitidos[x][y] = 1;
				}
			}
			
		}

		
		return arregloPermitidos;
	}

}
