<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Categories Overview</title>
</head>
<body>
    <h2>Categories List</h2>

 
    <table border="1" cellpadding="5" cellspacing="0">
        <tr>
            <th>ID</th>
            <th>Name</th>
        </tr>
        <c:forEach var="category" items="${categories}">
            <tr>
                <td>${category.idCategory}</td>
                <td>${category.name}</td>
            </tr>
        </c:forEach>
    </table>
<br><br><hr>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
