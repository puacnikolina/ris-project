<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<header>
    	<nav>
    	<h2>Hello, <security:authentication property="principal.username" /></h2>
    	<hr>
    	</nav>
    </header>
    <div>
    	<h3>Products</h3>
    	<a href="/Project/product/getAddProductsPage">Add new product</a><br>
    	<a href="/Project/product/getProductsAdmin">Products overview</a><br>
    	<a href="/Project/song/getAddSongPage">Add new song</a><br>
    	<a href="/Project/song/getSongsOverview">Songs overview</a><br>
    	
    	<h3>Artists</h3>
    	<a href="/Project/artist/getAddArtistPage">Add new artist</a><br>
    	<a href="/Project/artist/getArtistsOverview">Artists overview</a><br>
    	
    	<h3>Categories</h3>
       	<a href="/Project/category/getCategoriesOverview">Overview</a><br>
    	
    	<h3>Users</h3>
    	<a href="/Project/users/getUsersOverview">Overview</a><br>
    	
    	<h3>Orders</h3>
    	<a href="/Project/order/getOrdersOverview">Overview</a><br>
    	
    	<h3>Statistics</h3>
    	<a href="/Project/statistics/getStatisticsPage">Overview</a><br>
    	<br>
    	<hr>
    	<a href="${pageContext.request.contextPath}/auth/logout">Logout</a>
    </div>
    
</body>
</html>