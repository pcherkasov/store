<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.charkasau.store.models.User" %>
<%@ page import="com.charkasau.store.models.Order" %>
<%--
  User: Pavel Charkasau
  Date: 12.11.2018
  Time: 14:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../css/normalize.css" rel="stylesheet" type="text/css">
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
    <title>Receipt</title>
</head>
<body>
<%
    User user = (User) application.getAttribute("user");
    Order usersOrder = user.getOrder();
%>
<div class="container">
    <div>
        <h1>Dear <%=user.getName()%>, your order:</h1>
    </div>
    <div class="order">
        <div>
        <c:forEach items="${user.order.items}" var="item" varStatus="counter">
            <div>
                ${counter.count}) ${item.name} (${item.price.toString()} $)
            </div>
        </c:forEach>
        </div>
        <div>
            <h3>Total: $<%=usersOrder.getCost().toString()%></h3>
        </div>
    </div>
    <form action="/goToHistoryConfig" method="post">
        <input type="submit" value="See History">
    </form>
</div>
</body>
</html>
