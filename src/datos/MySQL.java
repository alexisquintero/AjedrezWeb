package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import excepciones.ApplicationException;



public class MySQL {
	

	
	
	private static MySQL instance = null;

	public static MySQL getInstance() {
		if(instance == null) {
			
			instance = new MySQL();
			
			}
		
		return instance;
		
	}
	 
	 public Connection Connect() throws ApplicationException{   

		 Connection conn = null;
			 
			 String url = "jdbc:mysql://localhost:3306/Ajedrez";
//		 	 String url = "jdbc:sqlserver://127.0.0.1:1433/Ajedrez";
			 String user = "Java";
			 String password = "Java";
//TODO: Usar la url, user y password desde un .ini
		 
			 try {	
				conn = DriverManager.getConnection(url, user, password);		
			} catch (SQLException e) {
				e.printStackTrace();
				throw new ApplicationException("Error al actualizar datos de persona", e);
			}
			 
		 return conn;
	 }

	 
	 protected void Close(ResultSet resultSet, Statement statement, Connection connect){
		    try {
		      if (resultSet != null) {
		        resultSet.close();
		      }

		      if (statement != null) {
		        statement.close();
		      }

		      if (connect != null) {
		        connect.close();
		      }
		    } catch (Exception e) {
		    	e.printStackTrace();
		    }
		  }
	 
}

/*
 
	Base de datos			->	Ajedrez
	Usuario					->	Java
	Password				->	Java
		Jugador
			dni				->	char(8)					->clave primaria
			nombre			->	varchar(20)
			apellido		->  varchar(20)				
		Partida
			idPartida		->	int(Autoincremental)	->Clave primaria
			tablero			->	blob / varbinary(max)
		Jugador-Partida
			dniBlancas		->	char(8)
			dniNegras		->	char(8)
			idPartida		->	int	
					
*/