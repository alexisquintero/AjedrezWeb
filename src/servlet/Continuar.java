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

@WebServlet("/Continuar")
public class Continuar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Continuar() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		ControladorAjedrez ca = (ControladorAjedrez) session.getAttribute("ca");
		try {
			ca.continuar();
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		char[][] posicion = ca.inicializarTablero();
		int id = ca.getIdPartida();
		session.setAttribute("ca", ca);	
		session.setAttribute("posicion", posicion);	
		session.setAttribute("id", id);	
		session.setAttribute("flagError", true);
		response.sendRedirect("/AjedrezWeb/Tablero.jsp");
	}

}
