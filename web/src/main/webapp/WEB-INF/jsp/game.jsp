<%@ page import="com.rulezzz.pkr.core.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Insert title here</title>
		<link rel="stylesheet" href="static/css/card.css">
	</head>
	<body>
		<table class="broll">

		<% int i = 0; %>
		<% Table t = (Table) request.getAttribute("table"); %>
		<% for(PlayerBox box: t.getBoxes()) { %>  
		<% i++; %>
		<c:forEach items="${table.boxes}" var="box" varStatus="loop">
		<tr>
			<% for(Card card: box.getHand().getCards()) { %>
				<td>
					<div class="<%=card.getStringCard()%>"></div><br> 
					<input type="checkbox" name="<%=card.toString()%>"><br> hold 
				</td>
		
			<% } %>
			<td class="handDiscription"> <div class="whiteChip"><div class="mid"> <%=box.getBet()%> </div></div><br>
				 <%=box.getHand().getCombinationOnFiveCards().toString()%>
			</td>
			<td>
				<% switch ( t.getGameStatus()  )  { 
					case DRAWS : {%>
						<input type="radio" checked="checked" name="choise<%=i%>" value="fold" > fold<br>
						<input type="radio" name="choise<%=i%>" value="bet" > bet<br>
						<input type="radio" name="choise<%=i%>" value="draw" > draw<br>
						<input type="radio" name="choise<%=i%>" value="buy" > buy
					<% break;
					}  
					case DEALER_DNQ : { %>
						<input type="radio" checked="checked" name="choise<%=i%>" value="ante"> ante<br>
						<input type="radio" name="choise<%=i%>" value="buy_game"> buy game<br>
					<% break;
					}
					 } %>
			</td>
		</tr> <% } %>
		</c:forEach>
		</table>
		<table class="broll">
			<tr>
				<td class="handDiscription">
					Bankroll<br>
					<%=t.getBankroll()%>
				</td>
			</tr>
		</table>
		<div class="base">
			<table>
				<tr>
					<% for(i=0; i< t.getHand().getCards().size()-1; i++) { %>
						<td>
							<div class="backCard"></div>
						</td>
					<%}%>
					<td> 
						<div class="<%= t.getHand().getCards().get(i).getStringCard() %>"></div>
					</td>
					<td class="doSomething">
						<form action="makeChanges" method="POST" name="form"> 
							<input type="submit" value="do something!" name="doButton">	
						</form>
					</td>
				</tr>
			</table> 
		</div>
	</body>
</html>