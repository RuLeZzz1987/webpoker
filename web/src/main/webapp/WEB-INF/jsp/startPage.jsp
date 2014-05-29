<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Choose Game</title>
<link rel="stylesheet" href="static/css/card.css">
</head>
<body>
	<form action="start" method="POST" name="form">
		<table>
			<tr>
				<td>
					<p>
						<strong>Choose game</strong><br> <input type="radio"
							name="gametype" value="FIVECARD" checked="checked"> Five
						Card<br> <input type="radio" name="gametype" value="TEXAS">
						Texas<br> <input type="radio" name="gametype" value="OMAHA">
						Omaha<br>
					</p>
				</td>
				<td>
					<p>
						<strong>Choose boxes count</strong><br> <input type="radio"
							name="boxCount" value="1" checked="checked"> 1<br> <input
							type="radio" name="boxCount" value="2"> 2<br> <input
							type="radio" name="boxCount" value="3"> 3<br> <input
							type="radio" name="boxCount" value="4"> 4<br>
					</p>
				</td>
				<td>
					<p>
						<strong>Enter bankroll</strong><br> <input type="number"
							name="bankroll" min="500" max="100000" step="50" value="1000"><br>
					</p>
				</td>
				<td>
					<p>
						<strong>Choose 1st bet value</strong><br> <input
							type="number" name="bet" min="5" max="100" step="5" value="10">
					</p>
				</td>
			</tr>
		</table>
		<input type="submit" value="Play!"> <br>
	</form>
</body>
</html>