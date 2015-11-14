<%@ page import="servlet.Tablero" %>
<%@page import="javax.websocket.Session"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Juego número:  <%=session.getAttribute("id") %></title>
</head>
<body>
<%char[][] posicion = (char[][])session.getAttribute("posicion"); 
	for (int i = 0; i < 8; i++){ %>
	<table border="1">
		<tr>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][0])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][1])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][2])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][3])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][4])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][5])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][6])); %></td>
			<td height="25px" height="25px"><% out.println(Character.toString(posicion[i][7])); %></td>
		</tr>
	</table>
	<%}; %>
	<form action="Tablero" method="post">
		Desde:<input type="text" name="desde" value="">		
  		<br>
 		Hasta:<input type="text" name="hasta" value="">
 		<br>
 		<input type="submit" value="Submit">
	</form>
</body>
</html>