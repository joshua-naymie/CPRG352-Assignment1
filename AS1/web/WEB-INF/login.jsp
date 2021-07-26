<%-- 
    Document   : login
    Created on : Jul 25, 2021, 12:05:59 AM
    Author     : Main
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Login</h3>
        
        <form method="POST">
            <label>Username:</label>
            <input type="text" name="username">
            <br>
            <label>Password:</label>
            <input type="text" name="password">
            <br>
            <input type="submit" value="Login">
            <c:if test="${message != null}"><h2>${message}</h2></c:if>
        </form>
    </body>
</html>