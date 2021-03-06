<%@page import="org.springframework.ui.Model"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
        <li class=""><a href="http://localhost:8084/FinalProject/homepage">Home</a></li>
        <li><a href="http://localhost:8084/FinalProject/viewProfile">Profile</a></li>
        <li class=""><a href="http://localhost:8084/FinalProject/upload">Upload</a></li>
        <li class="active"><a href="http://localhost:8084/FinalProject/category">Category</a></li>
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
<form action="searchBy" method="get">
<table>
<tr>
<td>
 &nbsp&nbsp&nbsp&nbsp&nbsp
</td>
<td>
<div class="form-group">
  <label for="sel1">Choose category</label>
  <select class="form-control" id="sel1" name="category">
    <option>All</option>
    <option>cec</option>
    <option>3</option>
    <option>4</option>
  </select>
</div>
</td>
<td>
 &nbsp&nbsp&nbsp&nbsp&nbsp
</td>
<td>
<div class="form-group" >
  <label for="sel1">Order By</label>
  <select class="form-control" id="sel1" name="sortBy">
    <option>Date</option>
    <option>Likes</option>
    <option>Popular</option>
  </select>
</div>
</td>
<td>
 &nbsp&nbsp&nbsp&nbsp&nbsp
</td>
<td>
 <button type="submit" class="btn btn-default">Search</button>
</td>
</tr>
</table>
</form>
<br><br>
<c:if test="${fn:length(tags) gt 0}">
&nbsp&nbsp&nbsp&nbsp&nbsp<font size="6">Tags:<c:if test="${tagMessage != null}">&nbsp<c:out value="${tagMessage}"/></c:if></font><br>
	<c:forEach var="tag" items="${tags}">
		&nbsp<font size="4">#<c:out value="${tag}"/></font>&nbsp
	</c:forEach>
</c:if>
<c:if test="${fn:length(showByTags) gt 0}">
	<fmt:parseNumber var="end" type="number" value="${fn:length(showByTags)}" />
	<c:forEach  varStatus="status" begin="0" end="${end}" step="1">
				<div class="container">
				<c:if test="${status.getIndex() % 3 == 0}"><br></c:if>
				<div class="row">
		
					<div class="col-md-4">
					<fmt:parseNumber var="index" type="number" value="${status.getIndex()}" />
		
					<c:if test="${fn:length(showByTags) gt index}">
				
					      <c:set var="post" value="${showByTags[index]}"/>
					      <a href="getPost?picId=${post.id}" class="thumbnail">
					      <c:set var="title" value="${post.title}"/>
					        <p><c:out value="${title}" /></p>
					        <c:set var="title" value="${title}"/>    
					        <img alt="image"  src="<c:url value="resources/${title}.png"/>">
					        Likes:<c:out value="${post.countsOfLikes}" />&nbsp&nbspViews:<c:out value="${post.countsOfViews}" />
					      </a>
				     </c:if>
				    </div>

				</div>
				</div>	    
			</c:forEach>	
	
</c:if>
<center>
	
		<c:if test="${fn:length(toShow) gt 0}">
			<c:forEach  varStatus="status" begin="0" end="7" step="3">
				<div class="container">
				<div class="row">
				  <c:forEach varStatus="status2" begin="${status.getIndex()}" end="${status.getIndex()+2}">	
					<div class="col-md-4">
					<fmt:parseNumber var="index" type="number" value="${status2.getIndex()}" />
					<c:if test="${fn:length(toShow) gt index}">
					      <c:set var="post" value="${toShow[index]}"/>
					      <a href="getPost?picId=${post.id}" class="thumbnail">
					      <c:set var="title" value="${post.title}"/>
					        <p><c:out value="${title}" /></p>
					        <c:set var="title" value="${title}"/>    
					        <img alt="image"  src="<c:url value="resources/${title}.png"/>">
					        Likes:<c:out value="${post.countsOfLikes}" />&nbsp&nbspViews:<c:out value="${post.countsOfViews}" />
					      </a>
				     </c:if>
				    </div>
				  </c:forEach>	 
				</div>
				</div>	    
			</c:forEach>	
		</c:if>
	<c:if test="${begin != null}">
		<ul class="pagination pagination-lg">
		<c:if test="${begin > 1}">
			 <li><a href="searchByPage?pageId=${begin-1}&b=${begin}&e=${end}" aria-label="Previous">
		        <span aria-hidden="true">&laquo;</span>
		      	</a>
	    	</li>
	    </c:if>
	    	<c:set var="page" value="${page}"/> 
	    	<c:forEach var="i" begin="${begin}" end="${end}" step="1"> 
	  			<c:choose>
    				<c:when test="${page == i}">
        				<li class="active">
	  						<a id="${i}"><c:out value="${i}"/>
	  						</a>
	  					</li>
    				</c:when>    
    				<c:otherwise>
    					<li>
	  						<a href="searchByPage?pageId=${i}&b=${begin}&e=${end}&next=${next}" id="${i}"><c:out value="${i}"/>
	  						</a>
	  					</li>
    				</c:otherwise>
				</c:choose>
	  		</c:forEach>
	  		<c:if test="${next == true}">
  			<li>
		      <a href="searchByPage?pageId=${end+1}&b=${begin}&e=${end}" aria-label="Next">
		        <span aria-hidden="true">&raquo;</span>
      		</a>
    		</li>
    		</c:if>
		</ul>
	</c:if>
</center>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>

</body>
</html>