package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidades.Jugador;
import entidades.Partida;
import excepciones.ApplicationException;
import negocio.ControladorAjedrez;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
		ControladorAjedrez ca = new ControladorAjedrez();
		try {
			Class.forName("com.mysql.jdbc.Driver"); //Tira error de jdbc sin esto
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		
		try {
			jugadores = ca.buscar(Integer.parseInt(request.getParameter("dni1")), Integer.parseInt(request.getParameter("dni2")));
		} catch (NumberFormatException e) {			
			out.println("Number exception");
			e.printStackTrace();
		} catch (ApplicationException e) {
			out.println("Se rompió todo");
		}	
		HttpSession session = request.getSession();
		session.setAttribute("jugador1", jugadores.get(0).getNombre() + " " + jugadores.get(0).getApellido());
		session.setAttribute("jugador2", jugadores.get(1).getNombre() + " " + jugadores.get(1).getApellido());
		session.setAttribute("ca", ca);	
		ca.inicializaJugadores(jugadores);
		try {
			Partida partida = ca.buscaJugadorPartida();
			if(partida.getIdPartida() != -1){
				session.setAttribute("flagContinuar", true);
			}else{
				session.setAttribute("flagContinuar", false);
			}
		} catch (ApplicationException e) {
			e.printStackTrace();
		}
		response.sendRedirect("/AjedrezWeb/Login2ndStep.jsp");		
	}

}
