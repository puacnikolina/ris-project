<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Artists Overview</title>
</head>
<body>
    <h2>Artists List</h2>

    <c:choose>
        <c:when test="${not empty artists}">
            <table border="1" cellpadding="5" cellspacing="0">
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Actions</th>
                </tr>
                <c:forEach var="artist" items="${artists}">
                    <tr>
                        <td>${artist.idArtist}</td>
                        <td>${artist.name}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/artist/getEditPage?id=${artist.idArtist}">Edit</a>
                            <form action="${pageContext.request.contextPath}/artist/delete" method="post" style="display:inline;">
                                <input type="hidden" name="id" value="${artist.idArtist}" />
                                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this artist?');" />
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </c:when>
        <c:otherwise>
            <p>No artists available.</p>
        </c:otherwise>
    </c:choose>

    <br><br>
    <button onclick="window.location.href='/Project/artist/getAddArtistPage'">Add artist</button>
    <br><br>
    <hr>
    <button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
