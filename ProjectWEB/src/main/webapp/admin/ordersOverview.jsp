<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <title>Orders Overview</title>
</head>
<body>
    <h2>All Orders</h2>

    <c:choose>
        <c:when test="${empty orders}">
            <p>No orders available.</p>
        </c:when>
        <c:otherwise>
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>Order ID</th>
                    <th>Order Date</th>
                    <th>Delivery Date</th>
                    <th>Status</th>
                    <th>Total Price</th>
                    <th>Username</th>
                    <th>Change status</th>
                </tr>
                <c:forEach var="order" items="${orders}">
                    <tr>
                        <td>${order.idOrder}</td>
                        <td><fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td><fmt:formatDate value="${order.deliveryDate}" pattern="yyyy-MM-dd HH:mm" /></td>
                        <td>${order.status}</td>
                        <td>${order.totalPrice}</td>
                        <td>${order.user.username}</td>    
                        <td>
                            <form action="/Project/order/updateStatus" method="post">
                                <input type="hidden" name="orderId" value="${order.idOrder}" />
                                <select name="newStatus">
                                    <option value="PROCESSING" ${order.status == 'PROCESSING' ? 'selected' : ''}>PROCESSING</option>
                                    <option value="SHIPPED" ${order.status == 'SHIPPED' ? 'selected' : ''}>SHIPPED</option>
                                    <option value="CANCELLED" ${order.status == 'CANCELLED' ? 'selected' : ''}>CANCELLED</option>
                                </select>
                                <button type="submit">Update</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>

    <br><hr><br>
    <button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
