<%-- 
    Document   : index
    Created on : Mar 8, 2017, 4:26:42 PM
    Author     : Duc Quynh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Product</title>
    </head>
    <body bgcolor="#FFFFCC">
        <h1>Welcome to Shop Stop!!</h1>
        <hr/>
        <jsp:useBean id="prod" class="model.ProductCart" scope="session"/>
        <form action="ShoppingServlet" name="shoppingForm" method="post">
            <b>Product</b><br>
            <select name="products">
                <c:forEach var="item" items="${prod.products}">
                    <option>
                        ${item.productId}${"|"}${item.productName}${"|"}${item.productType}${"|"}${item.price}
                    </option>
                </c:forEach>
            </select>
            <br><br>
            <b>Quantity</b><input type="text" name="qty" value="1"><br>
            <input type="hidden" name="action" value="ADD">
            <input type="submit" name="Submit" value="Add to Cart">
            
        </form>    
        <p> ${message}</p>
        <jsp:include page="cart.jsp" flush="true"/>
        
    </body>
</html>
