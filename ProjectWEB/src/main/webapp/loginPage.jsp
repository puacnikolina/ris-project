<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <div>
        <h2>Login</h2>
         <form action="${pageContext.request.contextPath}/auth" method="post">
            <table>
                <tr>
                    <td><label>Username:</label></td>
                     <td><input type="text" id="username" name="username" required></td>
                </tr>
                <tr>
                    <td><label>Password:</label></td>
                    <td><input type="password" id="password" name="password" required></td>
               </tr>
            </table>
            <br>
            <input type="submit" value="Login">
        </form>
    </div>
</body>
</html>
