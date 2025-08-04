<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="/Prodavnica/css/style.css">
    <title>Product Details</title>
</head>
<body>

        <h2>${productDetails.name} - ${productDetails.artist.name}</h2>
        <p><img src="/Project/product/image/${productDetails.idProduct}" alt="image" width="90" height="90" /></p>
        <p><strong>Rating:</strong> ${productDetails.rating}</p>
        <p><strong>Release Date:</strong> <fmt:formatDate value="${productDetails.releaseDate}" pattern="yyyy-MM-dd" /></p>
        <p><strong>Category:</strong> ${productDetails.category.name}</p>
        <p><strong>Price:</strong> €${productDetails.price}</p>
        
        <h3>Tracks:</h3>
		<c:if test="${not empty productDetails.songs}">
		    <ul>
		        <c:forEach var="song" items="${productDetails.songs}">
		            <li>${song.name} ${song.duration}</li>
		        </c:forEach>
		    </ul>
		</c:if>
		<c:if test="${empty productDetails.songs}">
		    <p>No tracks available.</p>
		</c:if>

        
        <p><strong>Description:</strong> ${productDetails.description}</p>

<form action="/Project/order/addToCart" method="post">
    <input type="hidden" name="idProduct" value="${productDetails.idProduct}" />

    <label for="quantity">Quantity:</label>
    <input type="number"
           name="quantity"
           value="1"
           min="1"
           max="${productDetails.quantity}"
           required />

    <button type="submit" class="back">Add to cart</button>
</form>
<br><br>
<form action="/Project/wishlist/addToWishlist" method="post">
    <input type="hidden" name="idProduct" value="${productDetails.idProduct}">
    <button type="submit" class="back">Add to Wishlist</button>
</form>
<br>
<c:if test="${productDetails.quantity == 0}">
    <div style="color: red; font-weight: bold; margin-top: 10px;">
        OUT OF STOCK
    </div>
</c:if>
<br><hr>
<button onclick="window.location.href='/Project/user/userPage.jsp'">Back to Home</button>
	<br>
	<br>
	<br>
	<c:if test="${not empty success}">
    <div style="color: green;">${success}</div>
	</c:if>
	
	<c:if test="${not empty info}">
	    <div style="color: blue;">${info}</div>
	</c:if>
	
	<c:if test="${not empty error}">
	    <div style="color: red;">${error}</div>
	</c:if>
	
	
</body>
</html>
