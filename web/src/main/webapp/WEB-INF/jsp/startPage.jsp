<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Choose Game</title>
		<link rel="stylesheet" href="static/css/card.css">
	</head>
	<body>
		<form action="start" method="POST" name="form">
		<p><strong>Choose game</strong><br>
			<input type="radio" name="gametype" value="FIVECARD"> Five Card<br>
			<input type="radio" name="gametype" value="TEXAS"> Texas<br>
			<input type="radio" name="gametype" value="OMAHA"> Omaha<br>
		</p>
		
			<input type="submit" value="Play!"> <br>

		
		</form>
			<form action="hi" method="GET" name="form1">
				<input type="submit" value="go test!">
			</form>
			<img class="Ac" src="static/images/empty.png">
	</body>
</html>