<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
      <a class="navbar-brand" href="index">BOB</a>
    </div>
    <div class="collapse navbar-collapse" id="myNavbar">
      <ul class="nav navbar-nav navbar-right">
        <li><a href="login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li><a href=""><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
      </ul>
    </div>
  </div>
</nav>
  <div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
		<br><br><br><br>
    		<div class="panel panel-primary">
    		<c:set var="ErrorMessage" scope="request" value="${ErrorRegMessage}"/>
		  	<c:if test="${ErrorRegMessage != null && ErrorRegMessage != ' '}">
   					<font color="red" size="3"><c:out value="${ErrorRegMessage}"/></font>
		  	</c:if>
				<div class="panel-body">
					<form:form action="register" commandName="userForm" method="post" modelAttribute="userForm">
						<div class="form-group">
							<h2>Create account</h2>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupName">
							First name<span class="req">*</span></label>
							<br>
							<form:input path="firstName"/>
							<br>
			  				<form:errors path="firstName" cssClass="error"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupName">
							 Last name<span class="req">*</span></label>
							 <br>
							<form:input path="lastName"/>
							<br>
			 				<form:errors path="lastName" cssClass="error"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupName">
							Username<span class="req">*</span>&nbsp&nbsp</label>
							<form:input path="username"/>
							<br>
			  				<form:errors path="username" cssClass="error"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupEmail">
							Email<span class="req">*</span></label>
							<br>
							<form:input path="email"/>
							<br>
							<form:errors path="email" cssClass="error"/>
						</div>
						<div class="form-group">
							<label class="control-label" for="signupPassword">
							Password<span class="req">*</span></label>
							<br>
							<form:password path="password"/>
							<br>
							<form:errors path="password" cssClass="error"/>
						</div>
						<div class="form-group">
							 <button id="signupSubmit" type="submit" class="btn btn-info btn-block">Create your account</button>
						</div>
						<p>
						</p>
					</form:form>
				</div>
			</div>
	
		</div>
	</div>
</div>
<br><br>
<footer class="container-fluid text-center">
  <p>Already have an account?
  <a href="login"><button type="button" class="btn btn-primary">Log in</button></a>
  </p>
</footer>

</body>
</html>