<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
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
        <li class="active"><a href="/FinalProject/viewProfile?Id=${userProfileToView.id}">View Profile</a></li>
       	<c:choose>
    		<c:when test="${userProfileToView.id == loggedUser.getLoggedUser().id}">
        		<li><a href="changeProfile">Change Profile</a></li>
        		<li><a href="upload">Upload</a></li>
        		<li><a href="followers">Followers</a></li>
        		<li><a href="following">Following</a></li>
        		<li><a href="myPosts">My posts</a></li>
    		</c:when>    
    		<c:otherwise>
        		<li><a href="followers?Id=${userProfileToView.id}">Followers</a></li>
        		<li><a href="following?Id=${userProfileToView.id}">Following</a></li>
        		<li><a href="myPosts?Id=${userProfileToView.id}">Posts</a></li>
    		</c:otherwise>
		</c:choose>       
      </ul><br>
     </div>
     
   <div class="col-sm-9">
     <section class="mainContent"> 
	  <section class="section1">
	    <h2 class="sectionTitle">Content Holder 1</h2>
	    <hr class="sectionTitleRule">
	    <hr class="sectionTitleRule2">
	    <div class="section1Content">
	    
	      <p><span>Username :</span><c:out value="${userProfileToView.username}"/></p>		
	      <p><span>Email :</span><c:out value="${userProfileToView.email}"/></p>
	      <p><span>First Name :</span><c:out value="${userProfileToView.firstName}"/></p>
	      <p><span>Last Name :</span><c:out value="${userProfileToView.lastName}"/></p>
	      <c:if test="${userProfileToView.biography != null}">
	       	 <p><span>Biography :</span><c:out value="${userProfileToView.biography}"/></p>
	      </c:if>
	      <c:if test="${userProfileToView.id != loggedUser.getLoggedUser().id}">
	       	 <c:choose>
    			<c:when test="${isFollow == false}">
    				<a href="/FinalProject/follow?Id=${userProfileToView.id}" role="button" class="btn btn-primary btn-lg">
        				Follow
        			</a>	
    			</c:when>    
    			<c:otherwise>
    				<a href="/FinalProject/unfollow?Id=${userProfileToView.id}" role="button" class="btn btn-success active btn-lg">
        				Followed
        			</a>	
    			</c:otherwise>
			  </c:choose>
	      </c:if>
	    </div>
	  </section>        
    </section>
  </div>
</body>
</html>
