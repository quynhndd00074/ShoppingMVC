<%-- 
    Document   : cart
    Created on : Mar 8, 2017, 4:41:27 PM
    Author     : Duc Quynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<jsp:useBean id="prod" class="model.ProductCart" scope="session"/>
<center>
    <table bgcolor="lightgreen" border="1" cellspacing="0" cellpadding="0">
        <tr>
            <td><b>Product ID</b></td>
            <td><b>Product Name</b></td>
            <td><b>Product Type</b></td>
            <td><b>Price</b></td>
            <td><b>Quantity</b></td>
            <td></td>
        </tr>
        <c:forEach var="item" items="${prod.cartItems}">
            <tr>
                <td>${item.productId}</td>
                <td>${item.productName}</td>
                <td>${item.productType}</td>
                <td>${item.price}</td>
                <td>${item.quantity}</td>
                <td>
                    <form action="ShoppingServlet" name="deleteForm" method="post">
                        <input type="submit" value="Delete">
                        <input type="hidden" name="delItem" value="${item.productId}">
                        <input type="hidden" name="action" value="DELETE">
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <form name="checkOutForm" action="ShoppingServlet" method="post">
        <input type="hidden" name="action" value="CHECKOUT">
        <input type="submit" name="Checkout" value="Checkout">
        
    </form>
</center>
