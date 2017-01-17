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

<head>
  <title> Add Movie </title>
  <style type="text/css">
    <%@include file="/css/default.css" %>
    <%@include file="/css/fonts.css" %>
    </style>
</head>

<%@page import="
java.sql.*,
javax.sql.*,
java.io.IOException,
javax.servlet.http.*,
javax.servlet.*,
javax.naming.Context,
javax.naming.InitialContext,
javax.servlet.ServletException,
javax.servlet.http.HttpServlet,
javax.servlet.http.HttpServletRequest,
javax.servlet.http.HttpServletResponse,
javax.sql.DataSource"
%>

<html>
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
</div>
<div id="banner">
  <div class="container">
    <div class="title">
      <h2>Add Movie</h2>
    </div>
  <div class="title"> 
    <center>
      <form method="POST" action="/fabflix/addmovie">
        <table>
          <tbody>
            <tr>
              <th align="right">Title:</th>
              <td align="left"><input type="text" name="title"></td>
            </tr>
            <tr>
              <th align="right">Year:</th>
              <td align="left"><input type="number" name="year"></td>
            </tr>
            <tr>
              <th align="right">Director:</th>
              <td align="left"><input type="text" name="director"></td>
            </tr>
            <tr>
              <th align="right">Banner_url:</th>
              <td align="left"><input type="url" name="banner_url"></td>
            </tr>
            <tr>
              <th align="right">Trailer_url:</th>
              <td align="left"><input type="url" name="trailer_url"></td>
            </tr>
            <tr>
              <th align="right">Star's first name:</th>
              <td align="left"><input type="text" name="first"></td>
            </tr>
            <tr>
              <th align="right">Star's last name:</th>
              <td align="left"><input type="text" name="last"></td>
            </tr>
            <tr>
              <th align="right">Genre:</th>
              <td align="left"><input type="text" name="genre"></td>
            </tr>
          </tbody>
        </table>
        <input type="submit" value="Add" class="button"/>
      </form>
    </center>
    </div>
</div>
</body>
</html>