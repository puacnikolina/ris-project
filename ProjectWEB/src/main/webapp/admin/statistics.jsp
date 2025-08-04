<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Statistics Overview</title>
</head>
<body>

<h2>Admin Statistics Overview</h2>

<table border="1" cellpadding="8" cellspacing="0">
    <tr>
        <th>Statistic</th>
        <th>Value</th>
    </tr>
    <tr>
        <td>Total Users</td>
        <td>${userCount}</td>
    </tr>
    <tr>
        <td>Total Products</td>
        <td>${productCount}</td>
    </tr>
    <tr>
        <td>Total Orders</td>
        <td>${orderCount}</td>
    </tr>
    <tr>
        <td>Total Revenue (€)</td>
        <td>${totalRevenue}</td>
    </tr>
</table>
<br>
<hr>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>