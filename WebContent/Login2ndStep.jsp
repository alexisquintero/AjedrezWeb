<%@ page import ="servlet.JuegoNuevo" %>
<%@ page import ="servlet.Continuar" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
 	<h2><%=session.getAttribute("jugador1") %></h2><br/>
 	<h2><%=session.getAttribute("jugador2") %></h2><br/>
	<form action="JuegoNuevo" method="post">
		<input type="submit" value="Juego Nuevo">
	</form>
	<%if((Boolean)session.getAttribute("flagContinuar")){ %>
	<form action="Continuar" method="post">
		<input type="submit" value="Continuar">
	</form>
	<%} %>
</body>
</html>