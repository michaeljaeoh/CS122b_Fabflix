<%
  String dashboard = (String) session.getAttribute("dashboard");
  if(dashboard == null)
  {
%>
  <jsp:forward page="/jsp/dashboard.jsp">
    <jsp:param name="FailReason" value="Wrong Password"/>
  </jsp:forward> 
<% 
  } 
%>

<!DOCTYPE html>
<head>
	<title> Dashboard Tools </title>
	<style type="text/css">
    <%@include file="/css/default.css" %>
    <%@include file="/css/fonts.css" %>
    </style>
</head>


<body>
<div id="wrapper">
	<div id="header-wrapper">
	<div id="header" class="container">
		<div id="logo">
			<h1><a href="/fabflix/jsp/dashboard_tools.jsp">Fabflix Dashboard</a></h1>
		</div>
		<div id="menu">
			<ul>
			<li><a href="/fabflix/jsp/add_movie.jsp" accesskey="1">Add Movie</a></li>
			<li><a href="/fabflix/jsp/add_star.jsp" accesskey="2">Add Star</a></li>
			<li><a href="/fabflix/jsp/metadata.jsp" accesskey="3">Metadata</a></li>
			</ul>
		</div>
	</div>
	</div>
	<div id="banner">
		<div class="container">
			<div class="title">
			<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
			<c:if test="${not empty message}">
			    <h4>${message}</h4>
			</c:if>
				<h2>Action</h2>
			</div>
			<ul class="actions">
				<li><a href="/fabflix/jsp/add_movie.jsp" class="button">Add Movie</a></li>
				<li><a href="/fabflix/jsp/add_star.jsp" class="button">Add Star</a></li>
				<li><a href="/fabflix/jsp/metadata.jsp" class="button">Metadata</a></li>
			</ul>
		</div>
	</div>
</div>
</body>