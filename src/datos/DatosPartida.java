package datos;

import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entidades.*;
import excepciones.ApplicationException;


public class DatosPartida {
	
	MySQL sql = MySQL.getInstance();	
	private Connection myConn = null;
	private Statement stm = null;
	private PreparedStatement pstm = null;
	private ResultSet rsl = null;
	
	public void nuevaPartida(Partida p) throws ApplicationException{
		
		Jugador j1 = p.getBlancas();
		Jugador j2 = p.getNegras();
		
		try{		
			Serializador serializador = new Serializador();
			ByteArrayOutputStream byteArrayOutputStream = serializador.serializar(p);
			myConn = sql.Connect();	
			//Crea nueva partida
			String query = "INSERT INTO partida(tablero) VALUES (?)";
			pstm = myConn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
				 
			pstm.setBytes(1, byteArrayOutputStream.toByteArray());
			 
			pstm.executeUpdate();
			rsl = pstm.getGeneratedKeys();
			rsl.next();	
			
			p.setIdPartida(rsl.getInt(1));	//Actualiza p 
			
			DatosJugadorPartida datosJugadorPartida = new DatosJugadorPartida();
			datosJugadorPartida.creaJugadorPartida(myConn, rsl.getInt(1), j1.getDni(), j2.getDni());
														
		}
		catch(SQLException e){
			e.printStackTrace();
			throw new ApplicationException("Error al crear la partida", e);
			
		}
		finally{			
			sql.Close(rsl, stm, myConn);			
		}							

	}
	
	public void actualizaPartida(Partida p) throws ApplicationException{
		
		try {
				Serializador serializador = new Serializador();
				
				myConn = sql.Connect();
				String query = "UPDATE partida SET tablero = ? WHERE ( idPartida = " + String.valueOf(p.getIdPartida()) + ")" ;
			
				pstm = myConn.prepareStatement(query);
				
				pstm.setBytes(1, serializador.serializar(p).toByteArray());
			
				pstm.executeUpdate();                      
			
		} catch (SQLException e) {
			throw new ApplicationException("Error al actualizar la partida", e);
		} finally{
			sql.Close(rsl, stm, myConn);
		}
		
	}
	
	public void buscarPartida(Partida partida) throws ApplicationException{
		
		myConn = sql.Connect();
		String query = "SELECT * FROM Partida WHERE ( idPartida = " + partida.getIdPartida() + " )";
//		Partida partida = new Partida(null, null);
		try {
			pstm = myConn.prepareStatement(query);
			stm = myConn.createStatement();
				 
			rsl = stm.executeQuery(query);
			while(rsl.next()){
				Serializador serializador = new Serializador();
				Blob blob = rsl.getBlob("tablero");
				serializador.deserializar(partida, blob);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error al buscar partida", e);
		}
//		return partida;
	}

}
