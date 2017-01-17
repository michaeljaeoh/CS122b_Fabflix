<!DOCTYPE html>
<head>
	<title> Browse by Genre </title>
	<style type="text/css">
		<%@include file="/css/default.css" %>
		<%@include file="/css/fonts.css" %>
		ul#movieList li{
			display:inline;
		}
	</style>
</head>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<body>
	<div id="wrapper">
		<div id="header-wrapper">
			<div id="header" class="container">
				<div id="logo">
					<h1><a href="home">Fabflix</a></h1>
				</div>
				<div id="menu">
					<ul>
						<li><a href="home" accesskey="1">Homepage</a></li>
						<li><a href="search" accesskey="2" title="">Search</a></li>
						<li class="current_page_item"><a href="#" accesskey="3" title="">Browse by Genre</a></li>
						<li><a href="title" accesskey="4" title="">Browse by Title</a></li>
						<li><a href="#" accesskey="5" title="">Checkout</a></li>
					</ul>
				</div>
			</div>
		</div>

		<div id="banner">
			<div class="container">
				<div class="title">
					<h2>Search Results</h2>
				</div>
				<table>
					<c:forEach var="movie" items="${movies}">
					<tr>
     			      <td>${movie.id}</td>
				      <td><a href="movies?query=${movie.id}"> ${movie.title}</td>
				      <td>${movie.year}</td>
				      <td>${movie.director}</td>
				    
				      <c:forEach var="star" items="${movie.starList}">
				    	   <td><a href="star?query=${star.id}"> ${star.firstName}${star.lastName}</td>
				       </c:forEach>
				       <c:forEach var="genre" items="${movie.genreList}">
				    	   <td>${genre.name}</td>
				</c:forEach>
      
      <td><img src ="${movie.banner}"></td>
      <td><a href="${movie.trailer}">trailer</a></td>         
    
    </tr>
					</c:forEach>
				</table>
				<ul class="actions">
					<li><a href="home" class="button">Home</a></li>
				</ul>
			</div>
		</div>
	</div>
</body>
