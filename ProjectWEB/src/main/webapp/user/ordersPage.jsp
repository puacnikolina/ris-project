<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<title>Your Orders</title>
</head>
<body>
	<h2>Your Orders</h2>
	<c:choose>
		<c:when test="${empty orders}">
			<p>You have no orders yet.</p>
		</c:when>
		<c:otherwise>
			 <table border="1" cellpadding="5" cellspacing="0">
				<thead>
					<tr>
						<th>Order ID</th>
						<th>Order Date</th>
						<th>Delivery Date</th>
						<th>Status</th>
						<th>Total Price</th>
						<th>Download Receipt</th> 
					</tr>
				</thead>
				<tbody>
					<c:forEach var="order" items="${orders}">
                        <tr>
                            <td>${order.idOrder}</td>
                            <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd"/></td>
                            <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd"/></td>
                            <td>${order.status}</td>
                            <td>€${order.totalPrice}</td>
                            <td><a href="/Project/report/downloadOrderReport/${order.idOrder}" target="_blank">Download PDF</a></td>
                        </tr>
                    </c:forEach>
				</tbody>
			</table>
		</c:otherwise>
	</c:choose>
<br><hr>
<button onclick="window.location.href='/Project/user/userPage.jsp'">Back to Home</button>
</body>
</html>
