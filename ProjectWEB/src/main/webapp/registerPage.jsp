<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>Register Page</title>
</head>
<body>
	<h2>Register</h2>
	<form:form action="/Project/auth/saveUser" method="post" modelAttribute="user">
		<table>
			<tr>
				<td><label>Username:</label></td>
				<td><input type="text" id="username" name="username" required></td>
			</tr>
			<tr>
				<td><label>Password:</label></td>
				<td><input type="password" id="password" name="password"
					required></td>
			</tr>
			<tr>
				<td><label>Name:</label></td>
				<td><input type="text" id="name" name="name" required></td>
			</tr>
			<tr>
				<td><label>Lastname:</label></td>
				<td><input type="text" id="lastname" name="lastname" required></td>
			</tr>
			<tr>
				<td><label>Address:</label></td>
				<td><input type="text" id="address" name="address" required></td>
			</tr>
		</table>
		<br>
		<input type="submit" value="Register" class="back">
	</form:form>
	
	<c:if test="${not empty errorMessage}">
	    <div class="error">${errorMessage}</div>
	</c:if>
</body>
</html>