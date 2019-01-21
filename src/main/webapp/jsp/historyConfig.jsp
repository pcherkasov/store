<%@ page import="com.charkasau.store.repositories.UsersRepository" %>
<%@ page import="com.charkasau.store.repositories.UsersRepositoryJdbc" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="com.charkasau.store.models.User" %><%--
  Created by IntelliJ IDEA.
  User: Pavel Charkasau
  Date: 24.11.2018
  Time: 3:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <link href="../css/normalize.css" rel="stylesheet" type="text/css">
    <link href="../css/styles.css" rel="stylesheet" type="text/css">
    <title>Chose User</title>
</head>
<body>
<%
    UsersRepository usersRepository = new UsersRepositoryJdbc((Connection) application.getAttribute("connection"));
%>
<div class="container">
    <div>
        <h1>Please, choose User</h1>
    </div>
    <form action="/showHistory" method="post">
        <div>
            <select name="selectedName">
                <%
                    for (User user : usersRepository.getAll()) {
                %>
                <option value="<%=user.getName()%>"><%=user.getName()%></option>
                <%}%>
            </select>
        </div>
        <div class="button">
            <input type="submit" value="Show History">
        </div>
    </form>
</div>
</body>
</html>
