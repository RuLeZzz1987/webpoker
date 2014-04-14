<%@ page import="com.rulezzz.pkr.core.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="static/css/card.css">
	</head>
	<body>
		<table>
		<% int i = 0; %>
		<% Table t = (Table) request.getAttribute("table"); %>
		<% for(PlayerBox box: t.getBoxes()) { %>
		<% i++; %>
		<tr>
			<% for(Card card: box.getHand().getCards()) { %>
				<td>
					<div class="<%=card.getStringCard()%>"></div><br> 
					<input type="checkbox" name="<%=card.toString()%>"><br> hold 
				</td>
		
			<% } %>
			<td>
				<input type="radio" name="choise<%=i%>" value="fold" > fold<br>
				<input type="radio" name="choise<%=i%>" value="bet" > bet<br>
				<input type="radio" name="choise<%=i%>" value="draw" > draw<br>
				<input type="radio" name="choise<%=i%>" value="buy" > buy
			</td>
		</tr> <% } %>
		</table>
	</body>
</html>