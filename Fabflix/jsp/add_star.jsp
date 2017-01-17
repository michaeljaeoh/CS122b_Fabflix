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
  <title> Add Star </title>
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
      <h2>Add Star</h2>
    </div>
  <div class="title"> 
    <center>
      <form method="POST" action="/fabflix/addstar">
        <table>
          <tbody>
            <tr>
              <th align="right">Star's last name:</th>
              <td align="left"><input type="text" name="last"></td>
            </tr>
            <tr>
              <th align="right">Star's first name:</th>
              <td align="left"><input type="text" name="first"></td>
            </tr>
            <tr>
              <th align="right">Star's dob:</th>
              <td align="left"><input type="datetime" name="dob"></td>
            </tr>
            <tr>
              <th align="right">Star's photo_url:</th>
              <td align="left"><input type="text" name="photo_url"></td>
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