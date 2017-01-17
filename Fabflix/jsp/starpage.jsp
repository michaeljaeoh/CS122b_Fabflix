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
          <h2>Results</h2>
        </div>
        <table>
         <tr>

          <td>${chosen.id}</td>
          <td>${chosen.firstName}</td>
          <td>${chosen.lastName}</td>
          <td>${chosen.dob}</td>
          <td>  <img src ="${chosen.photo_url}"></td>    

        </tr>


      </table>
    </div>
  </div>
</div>
</body>
