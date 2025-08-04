<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Overview</title>
<link rel="stylesheet" href="/Project/css/style.css">
</head>
<body>

	<h2>Product catalog</h2>
	<c:choose>
		<c:when test="${empty products}">
			<p>No products yet.</p>
		</c:when>
		<c:otherwise>
			<c:forEach var="product" items="${products}">

				<h3>${product.name}</h3>
				<p>
					<img src="/Project/product/image/${product.idProduct}" alt="image"
						width="90" height="90" />
				</p>
				<p>
					<strong>Artist:</strong> ${product.artist.name}
				</p>
				<p>
					<strong>Category:</strong> ${product.category.name}
				</p>
				<p>
					<strong>Price:</strong> €${product.price}
				</p>

			</c:forEach>
		</c:otherwise>
	</c:choose>
	<br>
	<hr>
	<button onclick="window.location.href='/Project/index.jsp'">Back
		to Home</button>
</body>
</html>
