<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Choose Game</title>
	</head>
	<body>
		<%=request.getAttribute("gametype") %>
		<form action="start" method="POST" name="form">
		<p><strong>Choose game</strong><br>
			<input type="radio" name="gametype" value="FIVECARD"> Five Card<br>
			<input type="radio" name="gametype" value="TEXAS"> Texas<br>
			<input type="radio" name="gametype" value="OMAHA"> Omaha<br>
		</p>
		<p>
			<input type="submit" value="Play!">
		</p>
		</form>
	</body>
</html>