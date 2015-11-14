<%@ page import="servlet.Login" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
<h3>Ingrese el DNI de los jugadores</h3>
	<form action="Login" method="post">
		DNI Jugador 1:<input type="text" name="dni1">		
  		<br>
 		DNI Jugador 2:<input type="text" name="dni2">
 		<br>
 		<input type="submit" value="Submit">
	</form>
</body>
</html>