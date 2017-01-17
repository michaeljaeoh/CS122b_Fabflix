<!DOCTYPE html>
<head>
	<title> Browse By Title </title>
	<style type="text/css">
    <%@include file="/css/default.css" %>
    <%@include file="/css/fonts.css" %>
	ul#titleDisplay li{
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
              <li><a href="genre" accesskey="3" title="">Browse by Genre</a></li>
			  <li class="current_page_item"><a href="#" accesskey="4" title="">Browse by Title</a></li>
			<li><a href="#" accesskey="5" title="">Checkout</a></li>
			</ul>
		</div>
	</div>
	</div>
	<div id="banner">
	<div class="container">
	<div class="title">
			<h2>Select to Browse by Title</h2></div>
	<ul id="titleDisplay">
  <c:forEach var="title" items="${titles}">
    <li><font size="6"> <a href="titles?query=${title}">${title}</a></font></li>

  </c:forEach>
  </ul>
  </div>
  </div>
  </div>



</body>