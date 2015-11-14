package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import excepciones.ApplicationException;
import negocio.ControladorAjedrez;

@WebServlet("/JuegoNuevo")
public class JuegoNuevo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public JuegoNuevo() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ControladorAjedrez ca = (ControladorAjedrez) session.getAttribute("ca");
		int id = 0;
		
		char[][] posicion = ca.inicializarTablero();

		try {
			id = ca.inicializaPartida();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		ca.setIdPartida();			
		session.setAttribute("ca", ca);	
		session.setAttribute("posicion", posicion);	
		session.setAttribute("id", id);	
		session.setAttribute("flagError", true);
		
		response.sendRedirect("/AjedrezWeb/Tablero.jsp");	
	}

}
