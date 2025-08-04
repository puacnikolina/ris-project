<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Overview</title>
<link rel="stylesheet" href="/Project/css/style.css">
</head>
<body>

<h2>Product List</h2>

<c:choose>
    <c:when test="${empty products}">
        <p>No products available.</p>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Artist</th>
                    <th>Category</th>
                    <th>Price (€)</th>
                    <th>Quantity</th>
                    <th>Rating</th>
                    <th>Description</th>
                    <th>Release Date</th>
                    <th>Product image</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="product" items="${products}">
                    <tr>
                        <td>${product.idProduct}</td>
                        <td>${product.name}</td>
                        <td>${product.artist.name}</td>
                        <td>${product.category.name}</td>
                        <td>€${product.price}</td>
                        <td>${product.quantity}</td>
                        <td>${product.rating}</td>
                        <td>${product.description}</td>
                        <td><p><fmt:formatDate value="${product.releaseDate}" pattern="yyyy-MM-dd"/></p></td>
                        <td><img src="/Project/product/image/${product.idProduct}" alt="image" width="90" height="90" /></td>
                        <td>
                            <a href="/Project/product/getEditPage?id=${product.idProduct}">Edit</a> |
                            <form action="/Project/product/delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${product.idProduct}" />
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>
 <br><br>
    <button onclick="window.location.href='/Project/product/getAddProductsPage'">Add product</button>
    <br><br>
    <hr>
    <button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
