package datos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.*;
import excepciones.ApplicationException;

public class DatosJugadorPartida {
	
	MySQL sql = MySQL.getInstance();	
	private Connection myConn = null;
	private Statement stm = null;
	private PreparedStatement pstm = null;
	private ResultSet rsl = null;
	
	public Partida buscarJugadorPartida(Partida p) throws ApplicationException{
		
		myConn = sql.Connect();
		String query = "SELECT * FROM JugadorPartida WHERE (dniBlancas = " + p.getBlancas().getDni() + " and dniNegras = " + p.getNegras().getDni() + ")";
//		String query = "SELECT MAX(idPartida) FROM JugadorPartida WHERE (dniBlancas = " + p.getBlancas().getDni() + " and dniNegras = " + p.getNegras().getDni() + ")";
		
		try {
			pstm = myConn.prepareStatement(query);
			stm = myConn.createStatement();
				 
			rsl = stm.executeQuery(query);
			while(rsl.next()){
				
				p.setIdPartida(rsl.getInt("idPartida"));

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error al buscar JugadorPartida", e);
		}
		
		return p;
	
	}
	
	public void creaJugadorPartida(Connection conn, int idPartida, int dniBlancas, int dniNegras) throws ApplicationException{	
		try {
			myConn = conn;
			String query = "INSERT INTO jugadorPartida(idPartida, dniBlancas, dniNegras) VALUES (?, ?, ?)";
			pstm = myConn.prepareStatement(query);
			
			pstm.setInt(1, idPartida);
			pstm.setInt(2, dniBlancas);
			pstm.setInt(3, dniNegras);
			
			pstm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error al crear  JugadorPartida", e);
		}
			 
		
	}
}
