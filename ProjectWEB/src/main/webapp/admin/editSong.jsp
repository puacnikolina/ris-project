<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Song</title>
</head>
<body>

<h2>Edit Song</h2>

<form:form method="post" action="/Project/song/update" modelAttribute="song">
    <form:hidden path="idSong" />

    <p>
        Name:<br>
        <form:input path="name" required="true" />
    </p>

    <p>
        Duration:<br>
        <form:input path="duration" type="text" required="true" />
    </p>

    <p>
        Product (Album):<br>
        <form:select path="product.idProduct">
            <form:options items="${products}" itemValue="idProduct" itemLabel="name" />
        </form:select>
    </p>

    <p>
        <input type="submit" value="Update Song" />
    </p>
</form:form>

<hr><br>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>

</body>
</html>
