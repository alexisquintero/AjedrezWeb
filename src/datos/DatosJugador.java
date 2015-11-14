package datos;

import entidades.*;
import excepciones.ApplicationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatosJugador {
	
	MySQL sql = MySQL.getInstance();	
	private Connection myConn = null;
	private Statement stm = null;
	private PreparedStatement pstm = null;
	private ResultSet rsl = null;
	

	public void creaJugador(Jugador j) throws ApplicationException{
		
		try{
				
				myConn = sql.Connect();
				String query = "INSERT INTO Jugador(dni, nombre, apellido) VALUES (?,?,?)";
				pstm = myConn.prepareStatement(query);
				 
				pstm.setInt(1, j.getDni());
				pstm.setString(2, j.getNombre());
				pstm.setString(3, j.getApellido());
			 
				pstm.executeUpdate();			
						
		}
		catch(SQLException e){
			
			throw new ApplicationException("Error al crear jugador", e);
			
		}
		finally{			
			sql.Close(rsl, stm, myConn);			
		}	
							
	}
	
	
	public Jugador buscaJugador(int i) throws ApplicationException{
	
		Jugador ju = null;
		
		myConn = sql.Connect();
		String query = "SELECT * FROM Jugador WHERE ( dni = " + i + " )";
			
		try {
			pstm = myConn.prepareStatement(query);
			stm = myConn.createStatement();
				 
			rsl = stm.executeQuery(query);
			while(rsl.next()){
				ju = new Jugador();
				
				ju.setNombre(rsl.getString("nombre"));
				ju.setApellido(rsl.getString("apellido"));
				ju.setDni(rsl.getInt("dni"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ApplicationException("Error al buscar jugador", e);
		}
		
		return ju;
	}

}
