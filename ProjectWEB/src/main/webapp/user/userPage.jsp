<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User panel</title>
</head>
<body>
	<h4>
		Hello,
		<security:authentication property="principal.username" />
	</h4>
	<a href="/Project/product/getProductsUser">Products</a>
	<br>
	<br>
	<a href="/Project/order/getCartPage">Cart</a>
	<br>
	<br>
	<a href="/Project/wishlist/getWishlistPage">Wishlist</a>
	<br>
	<br>
	<a href="/Project/order/getOrdersPage">Orders</a>
	<br>
	<br>
	<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>

</body>
</html>