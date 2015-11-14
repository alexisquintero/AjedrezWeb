package entidades;

public class Caballo extends Pieza {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8322912256237985670L;

	public Caballo(boolean L) {
		super(L);
		if(L){
			simbolo = '\u2658';
		}else{
			simbolo = '\u265E';
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
					x += 2; y += 1; 
					break;
				case 1:
					x += 2; y -= 1;
					break;
				case 2:
					x -= 2; y += 1;
					break;
				case 3:
					x -= 2; y -= 1;
					break;
				case 4:
					x += 1; y += 2;
					break;
				case 5:
					x -= 1; y += 2;
					break;
				case 6:
					x += 1; y -= 2;
					break;
				case 7:
					x -= 1; y -= 2;
					break;					
				}
				
				//Mientras no se salga de los límites del tablero
				if((x >= 0) && (x < 8) && (y >= 0) && (y < 8)){
					arregloPermitidos[x][y] = 1;
				}
			}		
		
		return arregloPermitidos;
	}

}
