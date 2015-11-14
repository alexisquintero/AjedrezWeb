package entidades;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import excepciones.ApplicationException;

public class Serializador {

	public ByteArrayOutputStream serializar(Partida p) throws ApplicationException {
		try{
			TableroDatos tableroDatos = new TableroDatos(p);
			
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			ObjectOutputStream oos = new ObjectOutputStream(byteArray);
			oos.writeObject(tableroDatos);
			return byteArray;
		}catch(IOException e){
			throw new ApplicationException("Error al actualizar datos de persona", e);
		}
		
	}
	
	public void deserializar(Partida partida, Blob blob) throws ApplicationException{
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(blob.getBinaryStream());		
			TableroDatos tableroDatos = (TableroDatos) ois.readObject();
			partida.setTurno(tableroDatos.lado);
			partida.getTablero().setPiezas(tableroDatos.piezas);
			partida.getTablero().setFlagEnroque(tableroDatos.flagEnroque);
			partida.getTablero().setFlagEnroqueLado(tableroDatos.flagEnroqueLado);
			partida.getTablero().setComidasBlancas(tableroDatos.comidasBlancas);
			partida.getTablero().setComidasNegras(tableroDatos.comidasNegras);
			partida.getTablero().setColumnaHasta(tableroDatos.columnaHasta);
			partida.getTablero().setFilaHasta(tableroDatos.filaHasta);
			
		} catch (IOException | SQLException | ClassNotFoundException e) {
			throw new ApplicationException("Error al actualizar datos de persona", e);
		}
	}
}
