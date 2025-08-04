<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Song List</title>
</head>
<body>

<h2>All Songs</h2>

<c:choose>
    <c:when test="${empty songs}">
        <p>No songs available.</p>
    </c:when>
    <c:otherwise>
        <table border="1" cellpadding="8" cellspacing="0">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Duration</th>
                    <th>Product (Album)</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="song" items="${songs}">
                    <tr>
                        <td>${song.idSong}</td>
                        <td>${song.name}</td>
                        <td>${song.duration}</td>
                        <td>${song.product.name}</td>
                        <td>
                            <a href="/Project/song/getEditPage?id=${song.idSong}">Edit</a> |
                            <form action="/Project/song/delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${song.idSong}" />
                                <button type="submit" onclick="return confirm('Are you sure you want to delete this product?');">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </c:otherwise>
</c:choose>

<br><br>
<button onclick="window.location.href='/Project/song/getAddSongPage'">Add song</button>
<br>
<br>
<hr>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
