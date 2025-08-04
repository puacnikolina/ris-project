<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Wishlist</title>
</head>
<body>
	<h2>Your Wishlist</h2>
	<c:choose>
		<c:when test="${empty products}">
			<p>Your wishlist is empty.</p>
		</c:when>
		<c:otherwise>
			<ul>
				<c:forEach var="product" items="${products}">
					<li>${product.name} - €${product.price}

						<form
							action="${pageContext.request.contextPath}/wishlist/removeFromWishlist"
							method="post" style="display: inline;">
							<input type="hidden" name="productId"
								value="${product.idProduct}" />
							<button type="submit">Remove</button>
						</form>
						<form action="${pageContext.request.contextPath}/order/addToCart"
							method="post" style="display: inline; margin-left: 10px;">
							<input type="hidden" name="idProduct"
								value="${product.idProduct}" /> <input type="hidden"
								name="quantity" value="1" />
							<button type="submit">Add to Cart</button>
						</form>
					</li>
				</c:forEach>
			</ul>
		</c:otherwise>
	</c:choose>
	<br>
	<hr>
	<button onclick="window.location.href='/Project/user/userPage.jsp'">Back
		to Home</button>
</body>
</html>
