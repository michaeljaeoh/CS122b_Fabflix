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
</head>

<body>
	<script>
	$(document).ready(function(){
		$(function(){
			$("#movieID").InfoBox({
			source : function(request, response) {
			$.ajax({
					url : "infobox",
					type : "GET",
					data : {
							movieID : request.term
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
		<div id="info" style="display: none; position: absolute">
		<c:forEach var="e" items="${movieEntry}" varStatus="myIndex">
				<table>
					<c:if test="getCategoryIndex(${myIndex.index}) == 0">
					<tr><p>Title: ${e}</p></tr>
					</c:if>
					<c:if test="getCategoryIndex(${myIndex.index}) == 1">
					<tr><img src="${e}"></tr>
					</c:if>
					<c:if test="getCategoryIndex(${myIndex.index}) == 2">
					<tr><p>Release Date: ${e}<p></tr>
					</c:if>
					<c:if 
					<tr><p>Director: ${e}</p></tr>
					<tr><td>Genre: ${e}</td>
					</c:forEach>
					</tr>
				</table>
				</c:forEach>
		</div>
	
	
	
	
</body>