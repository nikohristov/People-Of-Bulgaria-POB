<%@page import="org.springframework.ui.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.spring.model.post.*" %>
<%! int count = 0;
	List<Post> toShow = null;
	Post post = null;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
  <title>Bootstrap Example</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <style>
 input[type=text] {
    width: 130px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 4px;
    font-size: 16px;
    background-color: white;
    background-image: url('searchicon.png');
    background-position: 10px 10px; 
    background-repeat: no-repeat;
    padding: 12px 20px 12px 40px;
    -webkit-transition: width 0.4s ease-in-out;
    transition: width 0.4s ease-in-out;
}

input[type=text]:focus {
    width: 100%;
}
    /* Set height of the grid so .sidenav can be 100% (adjust if needed) */
    .row.content {height: 1500px}
    
    /* Set gray background color and 100% height */
    .sidenav {
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
      .row.content {height: auto;} 
    }
	

.mainContent .section1 .section1Content {

	font-family: ProximaNova;

	font-size: 14px;

	font-weight: 100;

	color: rgba(208,207,207,1.00);

}

.mainContent .section1 .section1Content span {

	color: rgba(146,146,146,1.00);

	font-family: sans-serif;

}

  </style>
</head>
<body>

<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand">BOB</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
<form action="searchByTags" method="get">
      <ul class="nav navbar-nav">
        <li class="active"><a href="">Home</a></li>
        <li><a href="viewProfile">Profile</a></li>
        <li class=""><a href="#">Upload</a></li>
        <li class=""><a href="category">Category</a></li>
<li> <a class="navbar-brand"></a>  </li>
<li> <a class="navbar-brand"></a>  </li>
<li> <a class="navbar-brand"></a>  </li>
<li> <a class="navbar-brand"></a>  </li>
<li> <a class="navbar-brand"></a>  </li>
<li> <a class="navbar-brand"></a>  </li>
<li>
  <input type="text" name="searchTags" placeholder="Search..">
</li>
<li>
  <button type="submit" class="btn btn-default">Search</button>
</li>
</ul>
</form>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
  
<center>

<%toShow =(List<Post>)request.getAttribute("toShow");%>       
<c:forEach var="i" begin="0" end="2" step="1">
 
 <div class="container">
	  <div class="row">
	  <c:forEach var="i" begin="0" end="2" step="1"> 
	  <%
	  	if(count < toShow.size()){
	  	   post = toShow.get(count);
	  	%>
	  		<div class="col-md-4">
	      <a  class="thumbnail">
	        <p><%=post.getTitle()%></p>
	        <c:set var="title" value="<%=post.getTitle()%>"/>    
	        <img alt="image"  src="<c:url value="resources/${title}.png"/>">
	      </a>
	    </div>
	  		
	  <%count++;}
	  %>
	    </c:forEach>
	  </div>
</div>
</c:forEach>




	<form>
		<ul class="pagination pagination-lg">
		<c:if test="${begin > 1}">
			 <li><a href="previous" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		        <input type="hidden" name="begin" value="${begin}">
		      	</a>
	    	</li>
	    </c:if>
	    	<c:set var="page" value="${page}"/> 
	    	<c:forEach var="i" begin="${begin}" end="${begin+4}" step="1"> 
	    	
	  			<li class=" <c:if test="${page == i}"> active </c:if>">
	  				<a href="viewPage?pageId=${i}" id="${i}"><c:out value="${i}"/>
	  					
	  				</a>
	  			</li>
	  		
	  		</c:forEach>
  			<li>
		      <a href="#" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
      		</a>
    		</li>
		</ul>
	</form>
</center>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
<%count = 0;%>
</body>
</html>
