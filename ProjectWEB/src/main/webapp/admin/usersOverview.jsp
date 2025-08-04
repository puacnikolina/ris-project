<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Manage Users</title>
</head>
<body>
    <h2>All Users</h2>

    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Name</th>
            <th>Lastname</th>
            <th>Address</th>
            <th>Role</th>
            <th>Action</th>
        </tr>
        <c:forEach var="user" items="${users}">
            <tr>
                <td>${user.idUser}</td>
                <td>${user.username}</td>
                <td>${user.name}</td>
                <td>${user.lastname}</td>
                <td>${user.address}</td>
                <td>${user.role.name}</td>
                <td>
                    <form action="/Project/users/changeRole" method="post" style="margin:0;">
                        <input type="hidden" name="id" value="${user.idUser}" />
                        <input type="submit" value="${user.role.name == 'USER' ? 'Promote to ADMIN' : 'Demote to USER'}" />
                    </form>
                </td>
            </tr>
        </c:forEach>
    </table>
    <br><br>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
