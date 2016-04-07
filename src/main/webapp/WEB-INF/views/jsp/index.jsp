<%@page import="org.springframework.ui.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>BOB</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
    /* Remove the navbar's default margin-bottom and rounded borders */ 
    .navbar {
      margin-bottom: 0;
      border-radius: 0;
    }
    
    /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
    .row.content {height: 450px}
    
    /* Set gray background color and 100% height */
    .sidenav {
      padding-top: 20px;
      background-color: #f1f1f1;
      height: 100%;
    }
    
    /* Set black background color, white text and some padding */
    footer {
      background-color: #555;
      color: white;
      padding: 15px;
    }
    
    /* On small screens, set height to 'auto' for sidenav and grid */
    @media screen and (max-width: 767px) {
      .sidenav {
        height: auto;
        padding: 15px;
      }
      .row.content {height:auto;} 
    }
  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="">BOB</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li><a href="register"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<div class="container-fluid text-center">    
  <div class="row content">
    <div class="col-sm-1 ">

    </div>
    <div class="col-sm-10 text-center"> 
      <h1>Welcome</h1>
      <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
       <div class="center-block">
       <h2>Image Gallery</h2>

		<c:if test="${fn:length(toShow) gt 0}">
			<c:forEach  varStatus="status" begin="0" end="2">
				<div class="container">
				<div class="row">
				  <c:forEach varStatus="status2" begin="${status.getIndex()*2}" end="${status.getIndex()*2+2}">	
					<div class="col-md-4">
					<fmt:parseNumber var="index" type="number" value="${status2.getIndex()}" />
					<c:if test="${fn:length(toShow) gt index}">
					      <a  class="thumbnail">
					      <c:set var="post" value="${toShow[index]}"/>
					      <c:set var="title" value="${post.title}"/>
					        <p><c:out value="${title}" /></p>
					        <c:set var="title" value="${title}"/>    
					        <img alt="image"  src="<c:url value="resources/${title}.png"/>">
					      </a>
				     </c:if>
				    </div>
				  </c:forEach>	 
				</div>
				</div>	    
			</c:forEach>	
		</c:if>
</div>
    </div>
    <div class="col-sm-1">
      <p></p>
    </div>
  </div>
</div>

<footer class="container-fluid text-center">
  <p>For more please sign in
  	<a href="register"><button type="button" class="btn btn-primary">Sign in</button></a>
  </p>
</footer>
</body>
</html>