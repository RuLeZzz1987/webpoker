<%@ page import="com.rulezzz.pkr.core.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/card.css">
</head>
<body>
    <form action="makeChanges" method="POST" name="form">
        <table class="broll">
            <c:forEach items="${table.boxes}" var="box" varStatus="loop">
                <tr>
                    <c:forEach items="${box.hand.cards}" var="card">
                        <td>
                            <div class="${card}"></div> <br> 
                            <c:if test="${table.gameStatus eq 'DRAWS'}">
                                <input type="checkbox" name="${card}"><br> hold
                            </c:if>
                        </td>
                    </c:forEach>
                    <td class="handDiscription">
                        <div class="whiteChip">
                            <div class="mid">${box.ante}</div>
                        </div> <br> ${box.hand.handICombination.name}
                    </td>
                    <td><c:choose>
                            <c:when test="${table.gameStatus eq 'DRAWS'}">
                                <input type="radio" checked="checked" name="choise${loop.index}"
                                    value="fold"> fold<br>
                                <input type="radio" name="choise${loop.index}" value="bet"> bet<br>
                                <input type="radio" name="choise${loop.index}" value="draw"> draw<br>
                                <input type="radio" name="choise${loop.index}" value="buy"
                                    disabled="disabled"> buy
                            </c:when>
                            <c:when test="${table.gameStatus eq 'DEALER_DNQ'}">
                                <input type="radio" checked="checked" name="choise${loop.index}"
                                    value="ante"> ante<br>
                                <input type="radio" name="choise${loop.index}" value="buy_game"> buy game<br>
                            </c:when>
                            <c:when test="${table.gameStatus eq 'DETERMINATION'}">
                                <c:if test="${box.status ne 'BET'}">
                                    <input type="radio" checked="checked" name="choise${loop.index}"
                                        value="fold"> fold<br>
                                    <input type="radio" name="choise${loop.index}" value="bet"> bet<br>
                                </c:if>
                            </c:when>
                    </c:choose></td>
                </tr>
            </c:forEach>
        </table>
        <table class="broll">
            <tr>
                <td class="handDiscription">Bankroll<br> ${table.bankroll}
                </td>
            </tr>
        </table>
        <br>
        <div class="base">
            <table>
                <tr>
                    <c:forEach begin="1" end="${table.dealerBox.hand.cards.size()-1}">
                        <td>
                            <div class="backCard"></div>
                        </td>
                    </c:forEach>
                    <td>
                        <div class="${table.dealerBox.hand.cards[0]}"></div>
                    </td>
                    <td class="doSomething"><input type="submit"
                        value="do something!" name="doButton"></td>
                </tr>
            </table>
        </div>
    </form>
</body>
</html>