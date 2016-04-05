<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
        <li><a href=""><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
        <li><a href="register"><span class="glyphicon glyphicon-log-in"></span> Sign in</a></li>
      </ul>
    </div>
  </div>
</nav>
<div class="container">
	<div class="row">
		<p><br><br><br><br><br><br>
		</p>
	</div>
</div>
<div class="container">
    <div class="row">
		<div class="col-md-4 col-md-offset-4">
		<c:set var="ErrorMessage" scope="request" value="${ErrorMessage}"/>
					<c:if test="${ErrorMessage != null && ErrorMessage != ' '}">
   						<font color="red" size="6"><c:out value="${ErrorMessage}"/></font>
					</c:if>
    		<div class="panel panel-default">
    		
			  	<div class="panel-heading">
			    	<h3 class="panel-title">
				    	Log in
			    	</h3>
			 	</div>
			  	<div class="panel-body">
			    	<form action="login" method="post">
                    <fieldset>
			    	  	<div class="form-group">
			    	  		<label class="control-label" for="signupName">
							Username<span class="req">*</span></label>
							<br>
			    		    <input class="form-control" type="text" name="username" value="">
			    		</div>
			    		<div class="form-group">
			    		<label class="control-label" for="signupName">
							Password<span class="req">*</span></label>
							<br>
			    			<input class="form-control" type="password" name="password" value="">
			    		</div>
			    		<div class="checkbox">
			    	    	<label>
			    	    		<input name="remember" type="checkbox" value="Remember Me"> Remember Me
			    	    	</label>
			    	    </div>
			    		<input class="btn btn-lg btn-success btn-block" type="submit" value="Login">
			    	</fieldset>
			      	</form>
			    </div>
			</div>
		</div>
	</div>
</div>
<div class="container">
	<div class="row">
		<p><br><br><br><br><br><br>
		</p>
	</div>
</div>
<footer class="container-fluid text-center">
  <p>You do not have an account?
  <a href="register"><button type="button" class="btn btn-primary">Sign in</button></a>
  </p>
</footer>

</body>
</html>