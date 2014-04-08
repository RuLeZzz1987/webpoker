<%@page import="com.rulezzz.pkr.core.Table"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table>
	<% Table t = (Table) request.getAttribute("table"); %>
	<% for(int i=0; i< t.getBoxes().size(); i++) { %>
	<tr>
	<% 	for(int j=0; j < t.getBox(i).getHand().getHand().size(); j++) {
		%><td> <%=t.getBox(i).getHand().getHand().get(j).toString()%> </td><%	
		} %>
	</tr> <%
	}
	%>
	</table>
</body>
</html>