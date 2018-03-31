<%-- 
    Document   : checkout
    Created on : Mar 8, 2017, 4:52:54 PM
    Author     : Duc Quynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="prod" class="model.ProductCart" scope="session"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction</title>
    </head>
    <body bgcolor="#FFFFcc">
        <h1>Transaction Details.</h1>
    <center>
        <table>
            <tr>
                <td><b>Product ID</b></td>
                <td><b>Product Name</b></td>
                <td><b>Product Type</b></td>
                <td><b>Price</b></td>
                <td><b>Quantity</b></td>
            </tr>
            <c:forEach var="item" items="${prod.cartItems}">
            <tr>
                <td>${item.productId}</td>
                <td>${item.productName}</td>
                <td>${item.productType}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                
            </tr>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td><b>Total</b></td>
                <td><b>${prod.amount}</b></td>
                <td></td>
            </tr>
        </table>
        <br>
        <a href="index.jsp">Home</a>       
    </center>
    </body>
</html>
