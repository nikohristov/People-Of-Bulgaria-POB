<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <li class=""><a href="homepage">Home</a></li>
        <li><a href="viewProfile">Profile</a></li>
        <li class=""><a href="#">Upload</a></li>
        <li class="active"><a href="category">Category</a></li>
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
    <option>2</option>
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
&nbsp&nbsp&nbsp&nbsp&nbsp TAGS:

<center>
<table>
	<tr>
		<td>
			<img src="cinqueterre.jpg" alt="Cinque Terre" style="width:150px;height:150px">
		</td>
	</tr>
</table>
	<form>
		<ul class="pagination pagination-lg">
  			<li><a href="#">1</a></li>
  			<li><a href="#">2</a></li>
  			<li><a href="#">3</a></li>
  			<li><a href="#">4</a></li>
  			<li><a href="#">5</a></li>
		</ul>
	</form>
</center>

<footer class="container-fluid text-center">
  <p>Footer Text</p>
</footer>
<nav>
  <ul class="pagination">
    <li>
      <a href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    <li><a href="#">1</a></li>
    <li><a href="#">2</a></li>
    <li><a href="#">3</a></li>
    <li><a href="#">4</a></li>
    <li><a href="#">5</a></li>
    <li>
      <a href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
  </ul>
</nav>
</body>
</html>
