package entidades;

import java.io.Serializable;
import java.util.ArrayList;
/**Sirve para la serialización del tablero, contiene los datos necesarios para guardar una partida
 * 
 * @author Jahn
 *
 */
public class TableroDatos implements Serializable{
	
	private static final long serialVersionUID = 4038042831803190241L;
	public Pieza[][] piezas;
	public int columnaHasta, filaHasta;
	public boolean flagEnroque;		
	public boolean flagEnroqueLado;
	public ArrayList<Pieza> comidasBlancas;
	public ArrayList<Pieza> comidasNegras;
	public boolean lado;
	
	public TableroDatos(Partida p) {
		
		piezas = p.getTablero().getPiezas();
		columnaHasta = p.getTablero().getColumnaHasta();
		filaHasta = p.getTablero().getFilaHasta();
		flagEnroque = p.getTablero().isFlagEnroque();
		flagEnroqueLado = p.getTablero().isFlagEnroqueLado();
		comidasBlancas = p.getTablero().getComidasBlancas();
		comidasNegras = p.getTablero().getComidasNegras();
		lado = p.isTurno();
	}
	
}
