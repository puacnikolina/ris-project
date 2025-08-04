<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Product</title>
</head>
<body>

<h1>Add New Product</h1>

<form:form method="post" action="/Project/product/save" modelAttribute="newProduct" enctype="multipart/form-data">

    <p>
        <label for="name">Name:</label><br>
        <form:input path="name" id="name" required="true"/>
    </p>

    <p>
        <label for="price">Price (€):</label><br>
        <form:input path="price" id="price" type="number" step="0.01" required="true"/>
    </p>

    <p>
        <label for="quantity">Quantity:</label><br>
        <form:input path="quantity" id="quantity" type="number" required="true"/>
    </p>

    <p>
        <label for="description">Description:</label><br>
        <form:textarea path="description" id="description" rows="4" cols="50" required="true"/>
    </p>

    <p>
        <label for="releaseDate">Release Date:</label><br>
        <form:input path="releaseDate" id="releaseDate" type="date" required="true"/>
    </p>

    <p>
        <label for="rating">Rating (0-5):</label><br>
        <form:input path="rating" id="rating" type="number" min="0" max="5" step="0.1" required="true"/>
    </p>

    <p>
        <label for="category">Category:</label><br>
        <form:select path="category.idCategory" id="category" required="true">
            <form:options items="${categories}" itemValue="idCategory" itemLabel="name"/>
        </form:select>
    </p>

    <p>
        <label for="artist">Artist:</label><br>
        <form:select path="artist.idArtist" id="artist" required="true">
            <form:options items="${artists}" itemValue="idArtist" itemLabel="name"/>
        </form:select>
    </p>
	<p>
	    <label for="image">Image:</label><br>
	    <input type="file" name="image" id="image" accept="image/*" required />
	</p>
    <p>
        <input type="submit" value="Add Product"/>
    </p>

</form:form>

<hr>
<br>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>

</body>
</html>
