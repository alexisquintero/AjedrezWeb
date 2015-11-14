package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import negocio.ControladorAjedrez;

@WebServlet("/Tablero")
public class Tablero extends HttpServlet {
	private static final long serialVersionUID = 1L;      

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();	
		
		if((request.getParameter("desde") == "" )|| (request.getParameter("hasta") == "" )){
			session.setAttribute("error", "Desde o Hasta no pueden ser vacíos");
			response.sendRedirect("/AjedrezWeb/Error.jsp");		
			return;
		}else if(request.getParameter("hasta") == request.getParameter("desde")){
			session.setAttribute("error", "Desde no puede ser igual a Hasta");
			response.sendRedirect("/AjedrezWeb/Error.jsp");	
			return;
		}		
		
		ControladorAjedrez ca = (ControladorAjedrez) session.getAttribute("ca");
		StringBuilder desde = new StringBuilder(); desde.append(request.getParameter("desde"));
		StringBuilder hasta = new StringBuilder(); hasta.append(request.getParameter("hasta"));
		boolean lado = ca.getLado();
		
		int[][] movimientosPosibles = ca.getMovimientosPosibles(desde, lado);;
		int columnaHasta = Character.getNumericValue(hasta.charAt(0))-10;
		int filaHasta = Character.getNumericValue(hasta.charAt(1))-1;
		
		if(movimientosPosibles[filaHasta][columnaHasta] == 1){ //Controla que hasta sea una posición dentro de las permitidas por array de movimientos posibles
			char[] pieza = ca.movimiento(desde, hasta);
			if(pieza[0] == 'F'){
				if(lado){
					session.setAttribute("ganador", "Blancas ganan");
				}else{
					session.setAttribute("ganador", "Negras ganan");
				}
				response.sendRedirect("/AjedrezWeb/Ganador.jsp");	
				return;
			}
		}else{
			session.setAttribute("error", "Movimiento no válido");
			response.sendRedirect("/AjedrezWeb/Error.jsp");		
			return;
		}
		
		char[][] posicion = ca.inicializarTablero();
		session.setAttribute("ca", ca);	
		session.setAttribute("posicion", posicion);
		response.sendRedirect("/AjedrezWeb/Tablero.jsp");
	}
	
}
