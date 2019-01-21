<%@ page import="com.charkasau.store.models.User" %>
<%@ page import="com.charkasau.store.repositories.OrdersRepositoryJdbc" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.charkasau.store.models.Order" %>
<%@ page import="com.charkasau.store.models.Item" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Pavel Charkasau
  Date: 24.11.2018
  Time: 3:05
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../css/normalize.css" rel="stylesheet" type="text/css">
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
    <title>History</title>
</head>
<body>

<div class="bodyContainer">
    <%
        User user = (User) request.getAttribute("user");
        OrdersRepositoryJdbc ordersRepository = new OrdersRepositoryJdbc((Connection) application.getAttribute("connection"));
    %>
    <div class="container">
        <div>
            <h1>Dear <%=user.getName()%>, your history:</h1>
        </div>
    </div>

    <%
        int i1 = 0;
        for (Order order : ordersRepository.getOrdersByUser(user)) {
    %>
    <div class="container">
        <h2>(<%=++i1%>)</h2>
        <div>
            <div class="order">
                <div>
                    <%
                        int i2 = 0;
                        for (Item item : order.getItems()) {
                    %>
                    <div>
                        <%=++i2%>) <%=item.getName()%> ($ <%=item.getPrice().toString()%>)
                    </div>
                    <%}%>
                </div>
            </div>
        </div>
    </div>
    <%}%>

</div>

</body>
</html>
