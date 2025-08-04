<html>
<head>
<title>Add New Artist</title>
</head>
<body>
	<h2>Add Artist</h2>

	<form action="${pageContext.request.contextPath}/artist/saveArtist"
		method="post">
		<label for="name">Name:</label> <input type="text" id="name"
			name="name" required /> <br />
		<br /> <input type="submit" value="Save Artist" />
	</form>
	<button
		onclick="window.location.href='/Project/artist/getArtistsOverview'">Artist
		List</button>
	<br>
	<br>
	<hr>
	<br>
	<button onclick="window.location.href='/Project/admin/adminPage.jsp'">Back
		to Admin Page</button>
</body>
</html>
