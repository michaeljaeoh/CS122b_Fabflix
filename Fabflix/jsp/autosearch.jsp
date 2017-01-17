<!DOCTYPE html>
<html>
<head>
  <meta charset="ISO-8859-1">
  <title>Autocomplete in java web application using Jquery and JSON</title>
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
  <!-- User defied css -->
  <link rel="stylesheet" href="style.css">

  <style type="text/css">
    <%@include file="/css/default.css" %>
    <%@include file="/css/fonts.css" %>
    ul#genreDisplay li{
      display:inline;
    }
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
            <form>

              <input type="text" name="title" id="title"/> 
              <input type="submit"/>
            </form>
          </center>
        </div>
      </div>
    </div>
  </div>




  <script>
    $(document).ready(function() {
      $(function() {
        $("#title").autocomplete({     
          source : function(request, response) {
            $.ajax({
              url : "autosearch",
              type : "GET",
              data : {
                title : request.term
              },
              dataType : "json",
              success : function(data) {
                response(data);
              }
            });
          }
        });
      });
    });
  </script>

  <script type="text/javascript">
// Using jQuery.

$(function() {
  $('form').each(function() {
    $(this).find('input').keypress(function(e) {
// Enter pressed?
if(e.which == 10 || e.which == 13) {
  this.form.submit();
}
});

    $(this).find('input[type=submit]').hide();
  });
});
</script>
</body>
</html>