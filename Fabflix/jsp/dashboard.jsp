<!DOCTYPE html>
<head>
  <title> Dashboard </title>
  <style type="text/css">
    <%@include file="/css/default.css" %>
    <%@include file="/css/fonts.css" %>
  </style>
  <script src='https://www.google.com/recaptcha/api.js'></script>
</head>

<html>
<body>
  <div id="wrapper">
    <div id="header-wrapper">
      <div id="header" class="container">
        <div id="logo">
          <h1>Fabflix</h1>
        </div>
        
      </div>
    </div>
    <div id="banner">
      <div class="container">
        <div class="title">
          <h2>Dashboard Sign In</h2>
        </div>
        <div class="title"> 
          <center>
            <form method="POST" action="_dashboard">
              <table>
                <tbody>
                  <tr>
                    <th>Email:</th>
                    <td><input type="text" name="email"></td>
                  </tr>
                  <tr>
                    <th align="right">Password:</th>
                    <td align="left"><input type="password" name="password"></td>

                  </tr>
                  <tr><td></td><td align="right"><input type="submit" value="Log In"></td>
                  </tr>
                </tbody>
              </table>
            </form></center></div>
          </div>
        </div>
      </div>
</body>
</html>