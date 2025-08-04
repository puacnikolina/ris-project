<html>
<head>
    <title>Edit Artist</title>
</head>
<body>
    <h2>Edit Artist</h2>

    <form action="${pageContext.request.contextPath}/artist/update" method="post">
        <input type="hidden" name="idArtist" value="${artist.idArtist}" />

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${artist.name}" required />
        <br/><br/>
        <input type="submit" value="Update Artist" />
    </form>

    
<hr><br>
<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back to Admin Page</button>
</body>
</html>
