<%-- 
    Document   : admin
    Created on : Jul 25, 2021, 12:49:15 PM
    Author     : Main
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        
        <h2>Manage Users</h2>
        
        <table>
            <tr>
                <th>Username</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Delete</th>
                <th>Edit</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <form method="POST">
                        <input name="userkey" type="hidden" value="${user.getUsername()}">
                        <td name="username" value="${user.getUsername()}">${user.getUsername()}</td>
                        <td name="firstname" value="${user.getFirstName()}">${user.getFirstName()}</td>
                        <td name="lastname" value="${user.getLastName()}">${user.getLastName()}</td>
                        <td><input type="submit" name="action" value="Delete"></td>
                        <td><input type="submit" name="action" value="Edit"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        
        <c:if test="${isEdit == true}"><h2>Edit User</h2></c:if>
        <c:if test="${isEdit == false}"><h2>Add User</h2></c:if>
        
        <form method="POST">
            <label>Username:</label>
            <c:if test="${isEdit == true}">
                <input type="hidden" name="username" value="${editUser.getUsername()}">
                <label>${editUser.getUsername()}</label>
            </c:if>
            <c:if test="${isEdit == false}">
                <input type="text" name="username" value="${editUser.getUsername()}">
            </c:if>
            
            <br>
            <label>Password:</label>
            <input type="text" name="password" value="${editUser.getPassword()}">
            <br>
            <label>Email:</label>
            <input type="text" name="email" value="${editUser.getEmail()}">
            <br>
            <label>First Name:</label>
            <input type="text" name="firstname" value="${editUser.getFirstName()}">
            <br>
            <label>Last Name:</label>
            <input type="text" name="lastname" value="${editUser.getLastName()}">
            <br>
            <c:if test="${isEdit == true}">
                <button type="submit" name="action" value="Save">Save</button>
                <button type="submit" name="action" value="Cancel">Cancel</button>
            </c:if>
            <c:if test="${isEdit == false}">
                <button type="submit" name="action" value="Add">Save</button>
            </c:if>
            
        </form>
    </body>
</html>
