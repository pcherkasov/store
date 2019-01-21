<%@ page import="com.charkasau.store.models.User" %>
<%@ page import="com.charkasau.store.models.Item" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="com.charkasau.store.repositories.ItemsRepository" %>
<%@ page import="com.charkasau.store.repositories.ItemsRepositoryJdbc" %>
<%@ page import="java.sql.Connection" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%--
  User: Pavel Charkasau
  Date: 12.11.2018
  Time: 1:39
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../css/normalize.css" rel="stylesheet" type="text/css">
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
    <title>Shop</title>
</head>
<body>
<%
    User user = (User) application.getAttribute("user");
    ItemsRepository itemsRepository = new ItemsRepositoryJdbc((Connection) application.getAttribute("connection"));
    HashMap<String, Item> itemsFromStorage = (HashMap<String, Item>) itemsRepository.findAll();
%>
<div class="container">
    <div>
        <h1>Hello, <%=user.getName()%>!</h1>
    </div>
    <form class="orderForm" method="post" action="/bucket" id="orderForm">
        <div>
            Make your order
        </div>
        <div>
            <select name="selectedItems">
                <%
                    for (Item item : itemsFromStorage.values()) {
                %>
                <option value="<%=item.getName()%>"><%=item.getName()%>(<%=item.getPrice().toString()%> $)</option>
                <%}%>
            </select>
        </div>
    </form>
    <div class="buttons">
        <div class="button">
            <input type="submit" value="Add Item" form="orderForm">
        </div>
        <div class="button">
            <input type="submit" value="Submit" form="getReceipt">
        </div>
    </div>
    <form action="/receipt" method="post" id="getReceipt"></form>
    <div class="orderList">
        <c:forEach items="${user.getOrder().getItems()}" var="item" varStatus="counter">
            <div>
                    ${counter.count}) ${item.name} (${item.price.toString()} $)
            </div>
        </c:forEach>
        <div>
            Total: $<%=user.getOrder().getCost().toString()%>
        </div>
    </div>
</div>
</body>
</html>
