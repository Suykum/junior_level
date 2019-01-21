<%--
  Created by IntelliJ IDEA.
  User: Toshiba
  Date: 21-Jan-19
  Time: 14:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <title>Reserved Seats</title>
</head>
<body>
    <h2>Customer Seats</h2>
    <table class="table table-bordered">
        <tr>
            <th style="width: 120px;">ID</th>
            <th>Name</th>
            <th>Phone</th>
            <th>Seat ID</th>
        </tr>
        <c:forEach var="account" items="${accounts}">
            <tr>
                <td>${account.id}</td>
                <td>${account.name}</td>
                <td>${account.telNumber}</td>
                <td>${account.seatID}</td>
            </tr>
        </c:forEach>
    </table>
    <br><a href='/cinema.html'> to main page</a>
</body>
</html>
