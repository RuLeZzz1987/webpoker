<%@ page import="com.rulezzz.pkr.core.engine.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Choose deal</title>
<link rel="stylesheet" href="static/css/card.css">
<link rel="stylesheet" href="static/css/basePageProp.css">
</head>
<body>
    <form action="chooseParam" method="POST" name="form">
        <table>
            <tr>
                <td>
                    <p>
                        <strong>
                            Choose boxes count
                        </strong>
                        <br>
                        <input type="radio" name="boxCount" value="1" checked="checked"> 1
                        <br>
                        <input type="radio" name="boxCount" value="2"> 2
                        <br>
                        <input type="radio" name="boxCount" value="3"> 3
                        <br>
                        <input type="radio" name="boxCount" value="4"> 4
                        <br>
                    </p>
                </td>
                <td>
                    <p>
                        <strong>
                            Choose 1st bet value
                        </strong>
                        <br> 
                        <input type="number" name="bet0" min="5" max="100" step="5" value="10" >
                        <input type="number" name="bet1" min="5" max="100" step="5" value="10" >
                        <input type="number" name="bet2" min="5" max="100" step="5" value="10" >
                        <input type="number" name="bet3" min="5" max="100" step="5" value="10" >
                    </p>
                </td>
            </tr>
        </table>
    </form>

</body>
</html>