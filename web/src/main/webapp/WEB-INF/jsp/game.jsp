<%@ page import="com.rulezzz.pkr.core.engine.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="static/css/card.css">
<link rel="stylesheet" href="static/css/basePageProp.css">
</head>
<body>
    <form action="makeChanges" method="POST" name="form">
          <div class="base">
            <table class="dealer">
                <tr>
                    <td>
                        <div class="${table.dealerBox.hand.cards[0]}"></div>
                    </td>
                    <c:forEach begin="1" end="${table.dealerBox.hand.cards.size()-1}" var="loop">
                        <td>
                        <c:choose>
                            <c:when test="${table.gameStatus eq 'SHOWDOWN'}">
                                <div class="${table.dealerBox.hand.cards[loop]}"></div>
                            </c:when>
                            <c:when test="${table.gameStatus eq 'DEALER_DNQ'}">
                                <div class="${table.dealerBox.hand.cards[loop]}"></div>
                            </c:when>
                            <c:otherwise>
                                <div class="backCard"></div>
                            </c:otherwise>
                        </c:choose>
                        </td>
                    </c:forEach>
                    <td class="doSomething"><input type="submit"
                        value="do something!" name="doButton"></td>
                </tr>
            </table>
        </div>
        <table class="broll">
            <tr>
                <td class="handDiscription">Bankroll<br> ${table.bankroll}
                </td>
            </tr>
        </table>
        <div class="boxes">
        <table class="boxes">
            <c:forEach items="${table.boxes}" var="box" varStatus="loop">
                <tr>
                    <c:forEach items="${box.hand.cards}" var="card">
                        <td class="cards">
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
                    <td class="choises"><c:choose>
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
        </div>
        <br>
    </form>
</body>
</html>