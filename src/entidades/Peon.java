package entidades;

public class Peon extends Pieza {
	
//TODO:	private Bool sinMover = True;  
//TODO: crear getter y setter
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8962839842493574976L;

	public Peon(boolean L) {
		super(L);
		if(L){
			simbolo = '\u2659';
		}else{
			simbolo = '\u265F';
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
		
		int x = columna; //Al revez son
		int y = fila;
		
		if(lado){		
			switch(comer){
			case 'A':arregloPermitidos[x+1][y+1] = 1; arregloPermitidos[x+1][y-1] = 1;break;
			case 'I':arregloPermitidos[x+1][y-1] = 1;break;
			case 'D':arregloPermitidos[x+1][y+1] = 1;break;
			}
		}else{
			switch(comer){
			case 'A':arregloPermitidos[x-1][y+1] = 1; arregloPermitidos[x-1][y-1] = 1;break;
			case 'I':arregloPermitidos[x-1][y-1] = 1;break;
			case 'D':arregloPermitidos[x-1][y+1] = 1;break;
			}
		}	
		
		if(lado){
			x += 1;// y += 1;
		}
		else{
			x -= 1;// y -= 1;
		}
		
		if((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
			arregloPermitidos[x][y] = 1;
		}
		
		if(sinMover){
			
			if(lado){
				x += 1;// y += 1;
			}
			else{
				x -= 1;// y -= 1;
			}
			
			if((x >= 0) && (x <= 7) && (y >= 0) && (y <= 7)){
				arregloPermitidos[x][y] = 1;
			}
			
		}

		
		return arregloPermitidos;
	}
	
	/**
	 * 
	 */
	public void promocion(){
		
	}
	
	/**
	 * 
	 */
	public void enPassant(){
		
	}

}
