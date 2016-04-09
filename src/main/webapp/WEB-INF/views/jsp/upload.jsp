<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
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
        <li><a href="homepage">Home</a></li>
        <li><a href="viewprofile">Profile</a></li>
        <li class="active"><a href="upload">Upload</a></li>
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
      <h2>My profile</h2><br>
      <ul class="nav nav-pills nav-stacked">
        <li ><a href="http://localhost:8084/FinalProject/viewProfile">View Profile</a></li>
        <li><a href="http://localhost:8084/FinalProject/changeprofile">Change Profile</a></li>
        <li class="active"><a href="">Upload</a></li>
        <li><a href="followers">Followers</a></li>
        <li><a href="following">Following</a></li>
        <li><a href="myposts">My posts</a></li>
        
      </ul><br>
     </div>
    <div class="col-sm-9">
    <!-- action="gcs/western-figure-126418/" -->
  <form:form method="POST" action="post" enctype="multipart/form-data">
   <table>
    <tr>
        <td><form:label path="title">Title</form:label></td>
        <td><form:input path="title" /></td>
    </tr>
    <tr>
        <td><form:label path="description">Description</form:label></td>
        <td><form:input path="description" /></td>
    </tr>
     <tr>
        <td><form:select path="category">
			<form:option value="NONE" label="--- Select ---" />
			<form:options items="${categories}" />
			</form:select>
            </td>
    </tr>
    <tr>
        <td>
        Tags:
        <input type="text" name="tags">
        </td>
    </tr>
    <tr>
     <tr>
    <td>File to upload: <input type="file" name="file"></td>
    </tr>
        <td colspan="2">
            <input type="submit" value="Submit"/>
        </td>
    </tr>
</table>  
</form:form>
	
 
    

      
         
   
  </div>


</div>
</div>
</body>
</html>
