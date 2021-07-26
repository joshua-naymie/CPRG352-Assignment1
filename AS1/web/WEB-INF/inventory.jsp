<%-- 
    Document   : inventory
    Created on : Jul 25, 2021, 2:55:21 PM
    Author     : Main
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inventory</title>
    </head>
    <body>
        <h1>Home Inventory</h1>
        <h3>Menu</h3>
        <ul>
            <li><a href="inventory">Inventory</a></li>
            <li><a href="admin">Admin</a></li>
            <li><a href="login?logout">Logout</a></li>
        </ul>
        
        <h2>Inventory for ${user.getFirstName()} ${user.getLastName()}</h2>
        
        <table>
            <tr>
                <th>Category</th>
                <th>Name</th>
                <th>Price</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${user.getItemList()}" var="item">
                <tr>
                    <form method="POST">
                        <input name="itemkey" type="hidden" value="${item.getItemID()}">
                        <td name="itemcategory" value="${item.getCategory().getCategoryName()}">${item.getCategory().getCategoryName()}</td>
                        <td name="itemname" value="${item.getItemName()}">${item.getItemName()}</td>
                        <td name="itemprice" value="${item.getItemPrice()}">${item.getItemPrice()}</td>
                        <td><input type="submit" name="action" value="Delete"></td>
                    </form>
                </tr>
            </c:forEach>
        </table>
        
        <h2>Add User</h2>
        
        <form method="POST">
            <label>Category:</label>
            <select name="category">
                <c:forEach items="${categories}" var="category">
                    <option value="${category.getCategoryID()}">${category.getCategoryName()}</option>
                </c:forEach>
            </select>
            <br>
            <label>Name:</label>
            <input type="text" name="itemname">
            <br>
            <label>Price:</label>
            <input type="number" name="itemprice">
            <br>
            <button type="submit" name="action" value="Add">Save</button>
        </form>
    </body>
</html>