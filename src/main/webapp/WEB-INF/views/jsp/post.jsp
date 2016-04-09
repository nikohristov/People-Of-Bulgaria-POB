<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
     <%@ page import="com.example.spring.model.post.*" %>
    <%@ page import="com.example.spring.model.comment.*" %>
    
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
/*Header */
	background-color: #581A1B;
	overflow: auto;
	font-family: montserrat, sans-serif;
	font-style: normal;
	font-weight: 400;
}
#mainwrapper header #logo {
	/* Company Logo text */
	width: 28%;
	float: left;
	padding-left: 2%;
	padding-top: 12px;
	padding-bottom: 12px;
	color: rgba(146,146,146,1.00);
}
#mainwrapper header nav {
	/*Nav bar containing links in header */
	text-align: right;
	padding-top: 12px;
	padding-bottom: 12px;
	padding-right: 2%;
	width: 68%;
	float: left;
	color: rgba(146,146,146,1.00);
}
header nav a {
	/* Links in header */
	padding-right: 2%;
}
#content #mainContent h1, #content #mainContent h2    {
	/* Styling for main headings */
	color: rgba(146,146,146,1.00);
}
#content #mainContent h3 {
	/*Captions ot Taglines */
	font-family: source-sans-pro, sans-serif;
	font-style: normal;
	font-weight: 200;
	color: rgba(146,146,146,1.00);
}
#content #mainContent #bannerImage {
	/*Container for main banner image */
	width: 100%;
	background-color: rgba(208,207,207,1.00);
}
#content #mainContent p {
	/* All paragraphs under maincontent */
	color: rgba(146,146,146,1.00);
	font-family: source-sans-pro, sans-serif;
	font-style: normal;
	font-weight: 200;
	text-align: justify;
}
#content #mainContent #authorInfo {
	/* Author info section */
	background-color: rgba(208,207,207,1.00);
}
#content #mainContent #authorInfo h2, #content #mainContent #authorInfo p {
	color: rgba(255,255,255,1.00);
	padding-left: 2%;
	padding-top: 11px;
}
#content #mainContent #authorInfo p {
	color: rgba(255,255,255,1.00);
	padding-left: 2%;
	padding-top: 0px;
	padding-bottom: 11px;
	padding-right: 2%;
}
footer article  {
	/* Footer articles */
	width: 46%;
	float: left;
	padding-left: 2%;
	padding-right: 2%;
	text-align: justify;
	font-family: source-sans-pro, sans-serif;
	font-style: normal;
	font-weight: 200;
	color: rgba(146,146,146,1.00);
}
footer article h3  {
	/* Footer article titles */
	text-align: center;
	font-family: montserrat, sans-serif;
	font-style: normal;
	font-weight: 400;
}
#mainContent {
	/* Container for the blog post in individal blog view */
	padding-left: 2%;
	width: 71%;
	float: left;
	padding-right: 2%;
	padding-top: 41px;
}
#mainwrapper #content #sidebar {
	/* Sidebar*/
	width: 19%;
	padding-left: 2%;
	padding-right: 2%;
	float: left;
	background-color: rgba(246,246,246,1.00);
	margin-top: 150px;
	padding-top: 32px;
}
#mainwrapper {
	/* Container of all content */
	width: 80%;
	overflow: auto;
	margin-left: 10%;
}
#content #sidebar input {
	/* Search box in sidebar */
	width: 98%;
	height: 32px;
}
#content #sidebar #adimage {
	/* Container for Image in sidebar */
	width: 100%;
	background-color: rgba(208,207,207,1.00);
	margin-top: 46px;
	float: none;
	overflow: auto;
}
nav ul li {
	list-style-type: none;
	padding-top: 8px;
	padding-bottom: 8px;
}
nav ul {
	padding-left: 0%;
}
nav ul li a {
	color: rgba(146,146,146,1.00);
	text-decoration: none;
}
#footerbar {
	/* Footer bar at the bottom of the page */
	height: 18px;
	clear: both;
	background-color: rgba(208,207,207,1.00);
	width: 100%;
}
footer {
	/* Container for footer artices */
	width: 71%;
	padding-left: 2%;
	padding-right: 2%;
}
.notOnDesktop {
	/*element to be displayed only in mobile view and tabet view */
	display: none;
}
#mainContent #bannerImage img {
	/* Actual banner image */
	width: 100%;
}
#sidebar #adimage img {
	/* Image in sidebar */
	width: 100%;
	float: left;
}
#mainwrapper header nav a {
	/* Links in header */
	color: rgba(146,146,146,1.00);
	text-decoration: none;
}
/* Tablet view */
@media screen and (max-width:769px){
.notOnDesktop {
	/* Search box shown only in mobile view and Tablet view */
	display: block;
	text-align: right;
	padding-right: 8px;
	padding-top: 8px;
	padding-bottom: 8px;
	width: 96%;
}
#content .notOnDesktop input {
	height: 28px;
}
#mainContent {
	/* Container for the blog post */
	padding-top: 0px;
	float: none;
	width: 96%;
}
#sidebar input {
	/* Search box in sidebar */
	display: none;
}
#mainwrapper #content #sidebar {
	/* Sidebar*/
	float: none;
	width: 92%;
	padding-top: 13px;
	overflow: auto;
	margin-top: 3px;
	margin-left: 2%;
	padding-bottom: 13px;
}
#content #sidebar #adimage {
	/* Image in sidebar */
	width: 60%;
	margin-top: 0px;
	float: left;
}
#content #sidebar nav {
	/* Navigation links in sidebar */
	width: 36%;
	float: left;
	padding-left: 4%;
}
#sidebar nav ul {
	margin-top: 0px;
}
footer {
	/* Footer region */
	width: 96%;
	padding-left: 2%;
	padding-right: 2%;
}
#content footer article {
	/*Each footer article */
	width: 46%;
}
#mainwrapper header {
	/* Header */
	width: 100%;
}
}
/* Mobile view */
@media screen and (max-width:480px){
#mainwrapper header #logo {
	/* Company Logo text in header */
	width: 96%;
	margin-left: 2%;
}
#mainwrapper header nav {
	/*navigation links in header */
	text-align: center;
	background-color: rgba(255,255,255,1.00);
	width: 98%;
}
#content #sidebar #adimage {
	/* Container for image in sidebar */
	width: 100%;
}
#content #sidebar nav {
	/* Navigation bar for links in sidebar */
	width: 96%;
	padding-top: 7px;
}
#sidebar nav ul li {
	display: inline-block;
	width: 32%;
	text-align: center;
}
#mainwrapper #content #sidebar {
	/* sidebar */
	padding-bottom: 0px;
}
#content .notOnDesktop {
	/* Search box shown only in mobile and tablet view */
	width: 100%;
	text-align: center;
	padding-left: 0px;
	padding-right: 0px;
}
#content .notOnDesktop input {
	width: 80%;
	text-align: center;
}
#content #mainContent h3 {
	/* Title under maincontent, if any */
	font-size: 14px;
}
#content footer article {
	/* Each foter article */
	width: 96%;
}
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
      <a class="navbar-brand" href="#">BOB</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav">
        <li class="active"><a href="http://localhost:8084/FinalProject/homepage">Home</a></li>
        <li><a href="http://localhost:8084/FinalProject/viewProfile">Profile</a></li>
        <li><a href="http://localhost:8084/FinalProject/upload">Upload</a></li>
<li><form>
  <input type="text" name="search" placeholder="Search..">
</form>
</li>
      </ul>
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </ul>
    </div>
  </div>
</nav>

   

 
  <div id="content">
    <div class="notOnDesktop"> 
      <!-- This search box is displayed only in mobile and tablet laouts and not in desktop layouts -->
      <input type="text" placeholder="Search">
    </div>
    <section id="mainContent"> 
      <h4>Title: ${post.title} </h4>
      <div id="bannerImage">
        <img alt="image"  src="<c:url value="resources/${post.id}.jpg"/>">
        </div>
      <p class="lead"> Description: ${post.description}</p>
      <p class="lead">${post.dateOfUpload} by <a href="/FinalProject/viewProfile?Id=${userOfPost.id}">${userOfPost.username}</a> </p>
      <form action="/FinalProject/likePost/${post.id}" method="get">
           <input type="submit" value="Like" style="background: ${color}">
       </form>
    </section>
    
   
    
    <form:form action="/FinalProject/getPost/comment/${post.id}" method="get" modelAttribute="comment">
        <div class="form-group">
          <form:label path="description">Comment</form:label>
          <form:input path="description" />
          <form:errors path="description" cssClass="error"/>
         
        </div>
        <button type="submit" class="btn btn-success" >Submit</button>
      </form:form>
      <br><br>
      
      <div class="row">
       <%
       if(request.getSession().getAttribute("post")!= null){
	       Post currentPost=(Post)request.getSession().getAttribute("post");
	       if(currentPost.getCommentsOfPost()!=null){
		       for(Comment c: currentPost.getCommentsOfPost()){ %>
			        <div class="col-sm-10">
			          <h4> <%= c.getUsername() %><small>   Date: <%=c.getDateOfUpload() %></small></h4>
			          <p> <%=c.getDescription() %></p>
			          <br>
			        </div>
          <%}} }%>
          <div class="row">
          </div>
        </div>
      </div>
     
    
   
 
      
      
     
        </div>
      </div>
    </div>
  </div>
</div>



</body>
</html>
