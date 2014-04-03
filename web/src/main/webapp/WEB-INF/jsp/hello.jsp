<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>1st_jsp</title>
	</head>
	<body>
		<%=request.getAttribute("mytxt") %>
		<form action="hello" method="POST" name="form">
		<p><strong>bets</strong>
			<input type="text" width="15" name="bets">
			<strong>hands</strong>
			<input type="text" width="15" name="hands">
		</p>
		<p>
			<input type="submit" value="print">
		</p>
		</form>
	</body>
</html>