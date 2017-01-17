<!DOCTYPE html>
<html>
<head>
  <title> Search Page </title>
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <!-- User defied css -->
  <link rel="stylesheet" href="style.css">
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
      <h2>Search</h2>
    </div>
    <div class="title"> 
      <center>
        <li><a href="autosearch" accesskey="1">Auto Search</a></li>
        
        <form method=get action="search" id ="myForm" name="myform">
          <script src="js/reset_form.js"></script>
          <table border="0" cellspacing="5">
            <tbody>
              <tr>
                <th align="right">Title:</th>
                <td align="left"><input type="text" name="title"></td>
              </tr>

              <tr>
                <th align="right">Year:</th>
                <td align="left"><input type="text" name="year"></td>
              </tr>


              <tr>
                <th align="right">Director:</th>
                <td align="left"><input type="text" name="director"></td>
              </tr>
              <tr>

               <tr>
                <th align="right">Star's first name:</th>
                <td align="left"><input type="text" name="firstname"></td>
              </tr>   

              <tr>
                <th align="right">Star's last name:</th>
                <td align="left"><input type="text" name="lastname"></td>
              </tr>

              <tr>
                <td align="right"><input type="submit" value="search" name="search"></td>
                <td align="middle"> <input type="button" onclick="resetForm()" value="Reset form"></td>
              </tr>
            </tbody>   
          </table>
        </form>
      </center>
    </div>
  </div>
</div>
</div>

</body>
</html>
