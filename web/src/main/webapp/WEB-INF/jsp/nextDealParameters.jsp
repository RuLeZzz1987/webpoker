<%@ page import="com.rulezzz.pkr.core.engine.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose deal</title>
<link rel="stylesheet" href="static/css/dealProps.css">
</head>
<body>
	<form action="chooseParam" method="POST" name="form">

		<div class="boxChooseLabel">
			<div>Choose boxes count</div>
			<br>
			<div class="radio">
				<input type="radio" name="boxCount" value="1" checked="checked">
				1
			</div>
			<br>
			<div class="radio">
				<input type="radio" name="boxCount" value="2"> 2
			</div>
			<br>
			<div class="radio">
				<input type="radio" name="boxCount" value="3"> 3
			</div>
			<br>
			<div class="radio">
				<input type="radio" name="boxCount" value="4"> 4
			</div>
			<br>
		</div>
		<div class="anteProp">
			<div>
				Choose 1st bet value 
			</div>
			<br>
			<div class="number" style="margin-top: -22px">
				<input type="number" name="bet0" min="5" max="100" step="5"
					value="10">
			</div>
			<div class="number">
				<input type="number" name="bet1" min="5" max="100" step="5"
					value="10">
			</div>
			<div class="number">
				<input type="number" name="bet2" min="5" max="100" step="5"
					value="10">
			</div>
			<div class="number">
				<input type="number" name="bet3" min="5" max="100" step="5"
					value="10">
			</div>
		</div>
		<div class="brollStatsAndDeal">
			<div class="broll">Bankroll</div>
            <div class="brollCount">${table.bankroll}</div>
			<div class="deal">
				<input type="submit" value="Deal!">
			</div>
		</div>
	</form>

</body>
</html>