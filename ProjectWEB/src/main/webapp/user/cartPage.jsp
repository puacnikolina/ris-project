<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Your Shopping Cart</title>
</head>
<body>
	<h2>Your Shopping Cart</h2>

	<c:choose>
		<c:when test="${empty cart}">
			<p>Your cart is empty.</p>
		</c:when>
		<c:otherwise>
			<table>
				<tr>
					<th>Product Name</th>
					<th>Price</th>
					<th>Quantity</th>
					<th>Total</th>
					<th>Action</th>
				</tr>
				<c:forEach var="entry" items="${cartItems}">
					<tr>
						<td>${entry.key.name}</td>
						<td>€${entry.key.price}</td>
						<td>${entry.value}</td>
						<td>€${entry.key.price * entry.value}</td>
						<td>
							<form
								action="${pageContext.request.contextPath}/order/removeFromCart"
								method="post">
								<input type="hidden" name="productId"
									value="${entry.key.idProduct}" />
								<button type="submit">Remove</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>

			<br>
			<form action="/Project/order/checkout" method="post">
				<button type="submit">Proceed to Checkout</button>
			</form>
		</c:otherwise>
	</c:choose>
<br><hr>
<button onclick="window.location.href='/Project/user/userPage.jsp'">Back to Home</button>
	
	
</body>
</html>
