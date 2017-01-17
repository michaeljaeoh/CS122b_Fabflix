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
  <title> Metadata </title>
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
      <h1><a href="/jsp/dashboard_tools.jsp">Fabflix Dashboard</a></h1>
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

<div style="color:#F8F8FF">
<%
  try {
      Context initCtx = new InitialContext();
      Context envCtx = (Context) initCtx.lookup("java:comp/env");
      DataSource ds = (DataSource) envCtx.lookup("jdbc/TestDB");
      Connection connection = ds.getConnection();

      Statement select = connection.createStatement();
      ResultSet result = select.executeQuery("show tables;");
      
      while (result.next()) {
        String tableName = result.getString(1);

        initCtx = new InitialContext();
        envCtx = (Context) initCtx.lookup("java:comp/env");
        ds = (DataSource) envCtx.lookup("jdbc/TestDB");
        connection = ds.getConnection();
        select = connection.createStatement();

        ResultSet tableResult = select.executeQuery("select * from "
            + tableName);
        ResultSetMetaData metadata = tableResult.getMetaData();
        int columnCount = metadata.getColumnCount();
        out.println("<hr>");
        out.println("<br>");
        out.println("TABLE: " + tableName + " WITH "
            + columnCount + " COLUMN(S)");
        out.println("<br>");
        for (int i = 1; i <= columnCount; i++){
          out.println("COLUMN " + i + ": "
              + metadata.getColumnLabel(i) + " TYPE "
              + metadata.getColumnTypeName(i));
          out.println("<br>");
        }
        out.println("<br>");
      }
    } catch (Exception e) {
      out.println("Error message: " + e.getMessage());
    }
%>
</div>
</body>
</html>