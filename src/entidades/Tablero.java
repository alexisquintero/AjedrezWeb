package entidades;

import java.util.ArrayList;

public class Tablero{
	
	private Pieza[][] piezas = new Pieza[8][8];
	public Pieza[][] getPiezas() {
		return piezas;
	}

	public void setPiezas(Pieza[][] piezas) {
		this.piezas = piezas;
	}

	private int columnaHasta, filaHasta;
	public int getColumnaHasta() {
		return columnaHasta;
	}

	public void setColumnaHasta(int columnaHasta) {
		this.columnaHasta = columnaHasta;
	}

	public int getFilaHasta() {
		return filaHasta;
	}

	public void setFilaHasta(int filaHasta) {
		this.filaHasta = filaHasta;
	}

	public boolean isFlagEnroque() {
		return flagEnroque;
	}

	public void setFlagEnroque(boolean flagEnroque) {
		this.flagEnroque = flagEnroque;
	}

	public boolean isFlagEnroqueLado() {
		return flagEnroqueLado;
	}

	public void setFlagEnroqueLado(boolean flagEnroqueLado) {
		this.flagEnroqueLado = flagEnroqueLado;
	}

	public void setComidasBlancas(ArrayList<Pieza> comidasBlancas) {
		this.comidasBlancas = comidasBlancas;
	}

	public void setComidasNegras(ArrayList<Pieza> comidasNegras) {
		this.comidasNegras = comidasNegras;
	}

	private boolean flagEnroque = false;		
	private boolean flagEnroqueLado = false;
	private ArrayList<Pieza> comidasBlancas = new ArrayList<Pieza>();
	private ArrayList<Pieza> comidasNegras = new ArrayList<Pieza>();
	
	public ArrayList<Pieza> getComidasBlancas() {
		return comidasBlancas;
	}

	public ArrayList<Pieza> getComidasNegras() {
		return comidasNegras;
	}
	
	public Tablero(){
		
		boolean lado = true;	//Empieza con blancas
		
		for (int j = 0; j < 2; j++) {
			
			int fila = lado ? 1 : 6;			//Creo los peones
			
			for (int i = 0; i < 8; i++) {
				piezas[fila][i] = new Peon(lado);
			}
			fila = lado ? 0 : 7;
			
			piezas[fila][0] = new Torre(lado);
			piezas[fila][7] = new Torre(lado);
			
			piezas[fila][1] = new Caballo(lado);
			piezas[fila][6] = new Caballo(lado);
			
			piezas[fila][2] = new Alfil(lado);
			piezas[fila][5] = new Alfil(lado);
			
			int columna = 3;
			piezas[fila][columna] = new Reina(lado);
			
			columna = 4;
			piezas[fila][columna] = new Rey(lado);
			
			lado = false;
		}
	}
		
	/**Devuelve un arreglo 8x8 con 2 en las posiciones libres, 4 en las posiciones ocupadas por piezas de distinto color , 6 para peones en passant y
	 * 	-2 en las posciones ocupadas por piezas del mismo color
	 */
	public int[][] lugaresLibres(boolean Lado){		
		int[][] arregloLibres = new int[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(piezas[i][j] == null){ //Vacio
					arregloLibres[i][j] = 2;
				}else{
					if(piezas[i][j].getLado() == Lado){ //Propias
						arregloLibres[i][j] = -2;
					}else{	//Enemigas
						arregloLibres[i][j] = 4;
					}
				}
			}
			
		}
		
		return arregloLibres;
	}
	
	/**Toma las posiciones permitidas y devuelve un Array 8x8 con 0 en las pocisiones inválidas, 
	 *  1 en las pocisiones válidas para esa pieza
	 * @param arregloPermitidos arreglo 8x8
	 * @return arregloPosibles arreglo 8x8 con 0 en las posiciones no posibles, 1 en las pocisiones libres posibles 
	 */
	public int[][] movimientosLegales(int[][] arregloPermitidos, boolean Lado, StringBuilder desde){
		
		int[][] arregloPosibles = new int[8][8];
		
		for(int m = 0; m < 8; m++){
			for(int n = 0; n < 8; n++){
				arregloPosibles[m][n] = 0;
			}
		} 
		
		int[][] arregloLibres = lugaresLibres(Lado);
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				arregloPosibles[i][j] = arregloPermitidos[i][j] + arregloLibres[i][j];
			}
			
		}								
		return controlarMovimientos(arregloPosibles, desde);
	}
	
	public int[][] movimientosPosibles(StringBuilder desde){	
		char comer = 'N';	//N: no come|A:come 2 lados|D: come derecha|I:come izquierda
		int columnaDesde = Character.getNumericValue(desde.charAt(0))-10;
		int filaDesde = Character.getNumericValue(desde.charAt(1))-1;
		if ((piezas[filaDesde][columnaDesde].getSimbolo() == '\u2659') || (piezas[filaDesde][columnaDesde].getSimbolo() == '\u265F')) {
			//Si es peon y puede comer también se puede mover diagonalmente
			boolean derecha = false, izquierda = false;
			if (piezas[filaDesde][columnaDesde].getLado()) {
				if ((columnaDesde+1 < 8) && (filaDesde+1 < 8)) { 									//Son necesarios estos 4 if iguales
					derecha = (piezas[filaDesde + 1][columnaDesde + 1] == null) ? false	: true;		//por lo menos si hace de esta forma
				}																					//porque si no no controla si se sale
				if ((columnaDesde-1 > -1) && (filaDesde+1 < 8)) {									//de bounds de un lado 
					izquierda = (piezas[filaDesde + 1][columnaDesde - 1] == null) ? false : true;
				}
			} else {
				if ((columnaDesde+1 < 8) && (filaDesde-1 > -1)) {
					derecha = (piezas[filaDesde - 1][columnaDesde + 1] == null) ? false	: true;
				}
				if ((columnaDesde-1 > -1) && (filaDesde-1 > -1)) {
					izquierda = (piezas[filaDesde - 1][columnaDesde - 1] == null) ? false : true;
				}
			}
				if (derecha && izquierda) {
				comer = 'A';
			} else {
				if (derecha) {
					comer = 'D';
				} else {
					if (izquierda) {
						comer = 'I';
					}
				}
			}
		}
//		}
		return piezas[filaDesde][columnaDesde].movimientosPermitidos(columnaDesde, filaDesde, comer); 
	}
	
	
	/**Comprueba si hay algún rey en jaque
	 * 
	 */
	public boolean jaque(){
		return false; //TODO: arreglar el método
		//controlar que entre el rey y las posiciones horizontales + verticales + diagonales + caballo no haya alguna pieza enemiga 
		//con línea directa (sin interrupción) que pueda atacarlo
		// o
		//variables globales acerca de la posición de los reyes
		//actualizandolas cuando se muevan y controlando si algun movimiento posible de alguna pieza enemiga pueda atacar al rey, si puede
		//controlar que que no haya otras piezas de por medio, tanto propias como enemigas que puedan haber sido agregadas despue
		
	}
	
	public char[][] Posiciones(){
		char[][] posicion = new char[8][8];
		
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				posicion[i][j] = piezas[i][j] == null? '\u0000' : piezas[i][j].getSimbolo(); //\u200A
			}
			
		}
		
		return posicion;
	}
	
	
	public char[] movimiento(StringBuilder desde, StringBuilder hasta){
		
		char[] simbolo = new char[4];
		simbolo[0] = 'F';
		boolean flagJaqueMate = false; 
		columnaHasta = Character.getNumericValue(hasta.charAt(0)) - 10;
		filaHasta = Character.getNumericValue(hasta.charAt(1)) - 1;

		if (piezas[filaHasta][columnaHasta] != null) { //Controla que no se coma al Rey
			if ((piezas[filaHasta][columnaHasta].getSimbolo() == '\u265A') || (piezas[filaHasta][columnaHasta].getSimbolo() == '\u2654')) { 
				flagJaqueMate = true;
			}
		}
		if(!flagJaqueMate){					
			int columnaDesde = Character.getNumericValue(desde.charAt(0)) - 10;
			int filaDesde = Character.getNumericValue(desde.charAt(1)) - 1;
			
			if(piezas[filaHasta][columnaHasta] != null){
				if(piezas[filaHasta][columnaHasta].getLado()){
					comidasBlancas.add(piezas[filaHasta][columnaHasta]);
				}else{
					comidasNegras.add(piezas[filaHasta][columnaHasta]);
				}
			}
			
			piezas[filaHasta][columnaHasta] = piezas[filaDesde][columnaDesde]; //mueve la pieza desde a hasta
			piezas[filaDesde][columnaDesde] = null; //pone null en desde
			piezas[filaHasta][columnaHasta].mover();
			simbolo[0] = piezas[filaHasta][columnaHasta].getSimbolo();	
		}
		
		if(((filaHasta == 0) || (filaHasta == 7)) && ((simbolo[0] == '\u265F') || (simbolo[0]== '\u2659'))){
			simbolo[0] = 'P';
		}
		
		if(flagEnroque){
			flagEnroque = false;
			int ix = flagEnroqueLado ? 0 : 7;	//Blancas 0 Negras 7
			int jx =  columnaHasta == 2 ? 0 : 7;	//Izquierda 0 Derecha 7
			piezas[ix][jx] = null;

			int dj = 0;
			if(columnaHasta == 2){ //Izquierda
				dj = 3;
			}else if(columnaHasta == 6){
				dj = -2;
			}
				
			piezas[ix][jx + dj] = new Torre(flagEnroqueLado); 
			simbolo[1] = piezas[ix][jx + dj].getSimbolo();
			simbolo[2] = Integer.toString(ix).toCharArray()[0];
			simbolo[3] = Integer.toString(jx + dj).toCharArray()[0];
		}			
				
		return simbolo;
	}

	private int[][] controlarMovimientos(int[][] arregloPosibles, StringBuilder desde){ //Controla el movimiento en las 8 direcciones para que no atraviese otra pieza
//TODO: controlar que ningún movimiento deje al rey propio en jaque 
		int dx = 0, dy = 0, x = 0, y = 0;
		boolean flagPiezaEnemiga = false, flagVacio = true, flagPiezaPropia = false;
		boolean flagJaque = jaque();//TODO: controlar si hay jaque
		int[][] posiblesControlado = new int[8][8];
		posiblesControlado = arregloPosibles;
		int columnaDesde = Character.getNumericValue(desde.charAt(0))-10;
		int filaDesde = Character.getNumericValue(desde.charAt(1))-1;
		
		for (int i = 0; i < 8; i++) {
			
			for (int j = 0; j < 8; j++) {		
				
				switch(posiblesControlado[filaDesde + dx][columnaDesde + dy]){
				case -2:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 0; flagVacio = false; break;							//mismo color & no posible 	->NO
				case -1:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 0; flagVacio = false; flagPiezaPropia = true; break;	//mismo color & posible		->NO
				case  2:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 0;	break;												//libre & no posible		->NO
				case  3:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 1;	break;												//libre & posible			->SI, solo si hay "3" al origen
				case  4:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 0; flagVacio = false; flagPiezaEnemiga = true; break;	//enemiga & no posible		->NO
				case  5:posiblesControlado[filaDesde + dx][columnaDesde + dy] = 1; flagVacio = false; flagPiezaEnemiga = true; break;	//enemiga & posible			->SI, solo si hay "3" al origen
				}
				
				if((!flagVacio && flagPiezaEnemiga) || (flagPiezaPropia)){
					break;
				}
				
				switch(i){
				case 0 :dy+=1;break;			//N
				case 1 :dx+=1;dy+=1;break;		//NE
				case 2 :dx+=1;break;			//E
				case 3 :dx+=1;dy-=1;break;		//SE
				case 4 :dy-=1;break;			//S
				case 5 :dx-=1;dy-=1;break;		//SO
				case 6 :dx-=1;break;			//O
				case 7 :dx-=1;dy+=1;break;		//NO
				}	
				
				if((columnaDesde+dy >= 8) || (columnaDesde+dy <= -1) || (filaDesde+dx >= 8) || (filaDesde+dx <= -1)){
					j = 8;
				}
			}			
			
			
			
									
			switch(i){	//Para el caballo
				case 0:			
					x = 2; y = 1; 
					break;
				case 1:
					x = 2; y = -1;
					break;
				case 2:
					x = -2; y = 1;
					break;
				case 3:
					x = -2; y = -1;
					break;
				case 4:
					x = 1; y = 2;
					break;
				case 5:
					x = -1; y = 2;
					break;
				case 6:
					x = 1; y = -2;
					break;
				case 7:
					x = -1; y = -2;
					break;					
				}
			
			if ((columnaDesde+y < 8) && (columnaDesde+y > -1) && (filaDesde+x < 8) && (filaDesde+x > -1)) {
				//Para el caballo
				if ((posiblesControlado[filaDesde + x][columnaDesde + y] == 3) || (posiblesControlado[filaDesde + x][columnaDesde+ y] == 5)) {
					posiblesControlado[filaDesde + x][columnaDesde + y] = 1;
				}
			}
			dx = dy = 0;
			flagPiezaEnemiga = flagPiezaPropia = false; flagVacio = true;
			
		}
		
		boolean flagLibre = false;
		int de = 0;
		
		if(!flagJaque){		//Para el rey con enroque						
			if (((piezas[filaDesde][columnaDesde].getSimbolo() == '\u2654') || (piezas[filaDesde][columnaDesde].getSimbolo() == '\u265A')) && piezas[filaDesde][columnaDesde].isSinMover()){
				if (arregloPosibles[filaDesde][columnaDesde + 2] == 1) { //a la derecha
					{
						de = 3;
					}
				} else if (arregloPosibles[filaDesde][columnaDesde - 2] == 1) { //a la izquierda
					de = -4;
				}
				if ((de != 0) && (piezas[filaDesde][columnaDesde + de].isSinMover())) { //Controla que la torre con la que va a enrocar esté sin mover		
					if(de == 3){														//y que pueda enrocar
						if(piezas[filaDesde][columnaDesde + 1] == null){
							if(piezas[filaDesde][columnaDesde + 2] == null){
								flagLibre = true;
							}
						}
					}else{
						if(piezas[filaDesde][columnaDesde - 1] == null){
							if(piezas[filaDesde][columnaDesde - 2] == null){
								if(piezas[filaDesde][columnaDesde - 3] == null){
									flagLibre = true;
								}
							}									
						}
						
					}
				}
				if((!flagLibre) || (flagJaque)){  //El rey en jaque										
					
					if(de == 3){
						de -= 1;
					}else{
						de += 1;
					}
					arregloPosibles[filaDesde][columnaDesde + de] = 0;
				}else if((flagLibre) && (!flagJaque)){
					flagEnroque = true;
					if(filaDesde == 0){  //Lado blancas	
						flagEnroqueLado = true;
					}else if(filaDesde == 7){  //Lado negras
						flagEnroqueLado = false;																	
					}
				}
			}
		}					
		
		
		int peon = piezas[filaDesde][columnaDesde].getLado() ? 1 : -1; //Para el peon, no puede mover para adelante si está ocupada
		
		if ((piezas[filaDesde][columnaDesde].getSimbolo() == '\u2659') || (piezas[filaDesde][columnaDesde].getSimbolo() == '\u265F')) { 
			if (piezas[filaDesde + peon][columnaDesde ] != null) { 
				posiblesControlado[filaDesde + peon][columnaDesde] = 0;
			}
		}
		return posiblesControlado;
	}

	public boolean getLado(StringBuilder desde) {
		int columnaDesde = Character.getNumericValue(desde.charAt(0))-10;
		int filaDesde = Character.getNumericValue(desde.charAt(1))-1;
		return piezas[filaDesde][columnaDesde].getLado();
	}

	public boolean esNulo(StringBuilder desde) {
		int columnaDesde = Character.getNumericValue(desde.charAt(0))-10;
		int filaDesde = Character.getNumericValue(desde.charAt(1))-1;	
		return piezas[filaDesde][columnaDesde] == null ? true : false;
	}

	public char promocion(String string, boolean lado) {
		
		switch(string){
    	case "Alfil": piezas[filaHasta][columnaHasta] = new Alfil(lado); break;
    	case "Torre": piezas[filaHasta][columnaHasta] = new Torre(lado);break;
    	case "Caballo": piezas[filaHasta][columnaHasta] = new Caballo(lado);break;
    	case "Reina": piezas[filaHasta][columnaHasta] = new Reina(lado);break;
    	};
    	//TODO: controlar jaque
    	return piezas[filaHasta][columnaHasta].getSimbolo();	
		
	}

}
