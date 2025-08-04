<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Product</title>
</head>
<body>

<h2>Edit Product</h2>

<form:form method="post" action="/Project/product/update" modelAttribute="product" enctype="multipart/form-data">
    <form:hidden path="idProduct" />

    <p>
        Name:<br>
        <form:input path="name" />
    </p>

    <p>
        Price (€):<br>
        <form:input path="price" type="number" step="0.01" />
    </p>

    <p>
        Quantity:<br>
        <form:input path="quantity" type="number" />
    </p>

    <p>
        Category:<br>
        <form:select path="category.idCategory">
            <form:options items="${categories}" itemValue="idCategory" itemLabel="name" />
        </form:select>
    </p>

    <p>
        Artist:<br>
        <form:select path="artist.idArtist">
            <form:options items="${artists}" itemValue="idArtist" itemLabel="name" />
        </form:select>
    </p>

    <p>
        Rating:<br>
        <form:input path="rating" type="number" step="0.1" min="0" max="5" />
    </p>

    <p>
        Description:<br>
        <form:textarea path="description" rows="4" cols="40" />
    </p>

    <p>
        Release Date:<br>
        <form:input path="releaseDate" type="date" />
    </p>
	
	<p>
	    <label for="image">Image:</label><br>
	    <input type="file" name="image" id="image" accept="image/*" />
	</p>
	<p>
	    <strong>Current Image:</strong><br>
	    <img src="/Project/product/image/${product.idProduct}" width="150" height="150" alt="Current image"/>
	</p>
	
    <p>
        <input type="submit" value="Update Product" />
    </p>
</form:form>
<hr><br>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
