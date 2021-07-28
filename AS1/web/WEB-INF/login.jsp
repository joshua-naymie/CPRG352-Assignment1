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
        <link rel="stylesheet" type="text/css" href="styles/styles.css" />
        <title>Login</title>
    </head>
    <body>
        <h1 class="header">Home Inventory</h1>
        <div class="loginsection">
            <div class="login">
                <h2 style="text-align: center;">Login</h2>
                <form method="POST">
                    <div class="input">
                        <div style="text-align: right;">
                            <label for="username">Username:</label>
                            <label for="password">Password:</label>
                        </div>
                        <div>
                            <input id="username" type="text" name="username">
                            <input id="password" type="password" name="password">
                        </div>
                    </div>

                    <div class="loginbutton">
                        <input type="submit" value="Login">
                    </div>
                    
                    <c:if test="${message != null}"><p>${message}</p></c:if>
                </form>
            </div>
        </div>
        
    </body>
</html>