<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <title>Profile</title>
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
      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>                        
      </button>
      <a class="navbar-brand">BOB</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li><a href="http://localhost:8084/FinalProject/homepage">Home</a></li>
        <li class="active"><a href="http://localhost:8084/FinalProject/viewProfile">Profile</a></li>
        <li><a href="http://localhost:8084/FinalProject/upload">Upload</a></li>
<li><form>
  <input type="text" name="search" placeholder="Search..">
</form>
</li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container-fluid">
  <div class="row content">
   	 <div class="col-sm-3 sidenav">
   	 <c:choose>
    		<c:when test="${userProfileToView.id == loggedUser.getLoggedUser().id}">
        			<h2>My profile</h2><br>		
    		</c:when>    
    		<c:otherwise>
    				<h2>${userProfileToView.username}</h2><br>		
    		</c:otherwise>
	 </c:choose>
      <ul class="nav nav-pills nav-stacked">
        <li><a href="/FinalProject/viewProfile?Id=${userProfileToView.id}">View Profile</a></li>
       	<c:choose>
    		<c:when test="${userProfileToView.id == loggedUser.getLoggedUser().id}">
        		<li><a href="changeProfile">Change Profile</a></li>
        		<li><a href="upload">Upload</a></li>
        		<li><a href="followers">Followers</a></li>
        		<li><a href="following">Following</a></li>
        		<li class="active"><a href="">My posts</a></li>
    		</c:when>    
    		<c:otherwise>
    			<li><a href="followers?Id=${userProfileToView.id}">Followers</a></li>
        		<li><a href="following?Id=${userProfileToView.id}">Following</a></li>
        		<li class="active"><a href="">Posts</a></li>
    		</c:otherwise>
		</c:choose>       
      </ul><br>
     </div>
  <div class="container bootstrap snippet">   
   <div class="col-sm-9">
     <section class="mainContent text-left">
     			<c:choose> 
     				<c:when test="${userProfileToView.id == loggedUser.getLoggedUser().id}">
		        			<h2 class="sectionTitle">My posts<c:if test="${categoryToShow != null}"> by category: <c:out value="${categoryToShow}"/></c:if></h2>
		    		</c:when>    
		    		<c:otherwise>
		    				<h2 class="sectionTitle">Posts of ${userProfileToView.username} <c:if test="${categoryToShow != null}"> by category: <c:out value="${categoryToShow}"/></c:if></h2>
		    		</c:otherwise>
		    	</c:choose> 
	  <section class="section1">
	  		<form action="myPosts" method="get">
	  			<div class="form-group">
					  <label for="sel1">Choose category</label>
					  <select class="form-control" id="sel1" name="category">
					    <option>All</option>
					    <option>Nature</option>
					    <option>People</option>
					    <option>Pets</option>
					  </select>
					  <input type="hidden" value="${userProfileToView.id}" name="Id">
					  <button type="submit" class="btn btn-default">Search</button>
				</div>
			</form>		
	    <div class="section1Content ">
		    <c:choose>
		    <c:when test="${fn:length(showPartList) gt 0}">
			<c:forEach  varStatus="status" begin="0" end="6" step="2">
				<div class="container">
				<div class="row">
				  <c:forEach varStatus="status2" begin="${status.getIndex()}" end="${status.getIndex()+1}">	
					<div class="col-md-4">
					<fmt:parseNumber var="index" type="number" value="${status2.getIndex()}" />
					<c:if test="${fn:length(showPartList) gt index}">
						 <c:set var="post" value="${showPartList[index]}"/>
					      <a href="getPost?picId=${post.id}" class="thumbnail">
					      <c:set var="title" value="${post.title}"/>
					        <p><c:out value="${title}" /></p>
					        <c:set var="title" value="${title}"/>    
					        <img alt="image"  src="<c:url value="resources/${title}.png"/>">
					        <center>Likes:<c:out value="${post.countsOfLikes}" />&nbsp&nbspViews:<c:out value="${post.countsOfViews}" /></center>
					      </a>
				     </c:if>
				    </div>
				  </c:forEach>
				  </div>
				</div>	 
			</c:forEach>
		<center><ul class="pagination pagination-lg">
			<c:if test="${begin > 1}">
				 <li><a href="myPostsPage?pageId=${begin-1}&b=${begin}&e=${end}&Id=${userProfileToView.id}" aria-label="Previous">
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
		  						<a href="myPostsPage?pageId=${i}&b=${begin}&e=${end}&next=${next}&Id=${userProfileToView.id}" id="${i}"><c:out value="${i}"/>
		  						</a>
		  					</li>
	    				</c:otherwise>
					</c:choose>
		  		</c:forEach>
		  		<c:if test="${next == true}">
	  			<li>
			      <a href="myPostsPage?pageId=${end+1}&b=${begin}&e=${end}&Id=${userProfileToView.id}" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
	      		</a>
	    		</li>
	    		</c:if>
			</ul></center>
			</c:when>
			<c:otherwise>
				<font size="6" color="red">No posts to show</font>
			</c:otherwise>
			</c:choose>
	    </div>
	  </section>        
    </section>
  </div>
 </div>
</div>  
</body>
</html>
