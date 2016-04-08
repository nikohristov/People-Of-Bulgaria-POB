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
        <li><a href="http://localhost:8080/FinalProject/homepage">Home</a></li>
        <li class="active"><a href="http://localhost:8080/FinalProject/viewProfile">Profile</a></li>
        <li><a href="http://localhost:8080/FinalProject/upload">Upload</a></li>
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
        		<c:choose>
		    		<c:when test="${isFollowers == true}">
		        			<li class="active"><a href="">Followers</a></li>
        					<li><a href="following">Following</a></li>
		    		</c:when>    
		    		<c:otherwise>
		        			<li><a href="followers">Followers</a></li>
        					<li class="active"><a href="">Following</a></li>
		    		</c:otherwise>
				</c:choose>
				<li><a href="myposts">My posts</a></li>
    		</c:when>    
    		<c:otherwise>
    			<c:choose>
		    		<c:when test="${isFollowers == true}">
		        			<li class="active"><a href="">Followers</a></li>
        					<li><a href="following?Id=${userProfileToView.id}">Following</a></li>
		    		</c:when>    
		    		<c:otherwise>
		        			<li><a href="followers?Id=${userProfileToView.id}">Followers</a></li>
        					<li class="active"><a href="">Following</a></li>
		    		</c:otherwise>
				</c:choose>
        		<li><a href="myposts">Posts</a></li>
    		</c:otherwise>
		</c:choose>       
      </ul><br>
     </div>
  <div class="container bootstrap snippet">   
   <div class="col-sm-9">
     <section class="mainContent text-center"> 
	  <section class="section1">
	  	<c:choose>
		    		<c:when test="${userProfileToView.id == loggedUser.getLoggedUser().id}">
		        			<c:if test="${isFollowers == false}"><h2 class="sectionTitle">Users who you are following</h2></c:if>
		        			<c:if test="${isFollowers == true}"><h2 class="sectionTitle">Users who followed you</h2></c:if>
		    		</c:when>    
		    		<c:otherwise>
		    				<c:if test="${isFollowers == false}"><h2 class="sectionTitle">Users who ${userProfileToView.username} is following</h2></c:if>
		        			<c:if test="${isFollowers == true}"><h2 class="sectionTitle">Users who followed ${userProfileToView.username}</h2></c:if>
		        			
		    		</c:otherwise>
		</c:choose>
	    <div class="section1Content ">
	    <div class="jumbotron list-content">
		    <ul class="list-group">
		    <c:choose>
		    <c:when test="${fn:length(usersToView) gt 0}">
			<c:forEach  varStatus="status" begin="0" end="${fn:length(usersToView)-1}">
				<fmt:parseNumber var="index" type="number" value="${status.getIndex()}" />
				<c:set var="user" value="${usersToView[index]}"/>
				<li href="#" class="list-group-item text-left">
			          <img class="img-thumbnail" src="http://bootdey.com/img/Content/user_1.jpg">
			         
			            <font size="4" color="blue">${user.username}</font>
			         <br> 
			        <label class="right">
			            <a type="button" class="btn btn-info" href="/FinalProject/viewProfile?Id=${user.id}" title="View" size="">View profile</a>
			        </label>
			        <div class="break"></div>
			     </li>
			</c:forEach>
				 <li href="#" class="list-group-item text-left">
			        <a class="btn btn-block btn-primary">
			            <i class="glyphicon glyphicon-refresh"></i>
			            Load more...
		        	</a>
		      	 </li>	
			</c:when>
			<c:otherwise>
				<c:if test="${isFollowers == false}"><font size="6" color="red">No users who is following</font></c:if>
		        <c:if test="${isFollowers == true}"><font size="6" color="red">No users who followed</font></c:if>
			</c:otherwise>
			</c:choose>
		    </ul>
  		</div>
	    </div>
	  </section>        
    </section>
  </div>
 </div> 
</body>
</html>
