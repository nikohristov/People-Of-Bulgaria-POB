<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
 <link href="resources/css/registerstyles.css" rel="stylesheet" />
</head>

<body>
<div class="form">
      
      
      
      <div class="tab-content">
      	<form:form action="register" commandName="userForm" method="post" modelAttribute="userForm">
        <div id="signup">   
          <h1>Sign Up for Free</h1>
          <c:set var="ErrorMessage" scope="request" value="${ErrorRegMessage}"/>
		  <c:if test="${ErrorRegMessage != null && ErrorRegMessage != ' '}">
   					<font color="red" size="3"><c:out value="${ErrorRegMessage}"/></font>
		  </c:if>
          <div class="top-row">
           
            <div class="field-wrap">
              <label>
                First Name<span class="req">*</span>
              </label>
              <form:input path="firstName"/>
			  <form:errors path="firstName" cssClass="error"/>
            </div>
        
            <div class="field-wrap">
              <label>
                Last Name<span class="req">*</span>
              </label>
              <form:input path="lastName"/>
			  <form:errors path="lastName" cssClass="error"/>
            </div>
          </div>
           <div class="field-wrap">
              <label>
                Username<span class="req">*</span>
              </label>
              <form:input path="username"/>
			  <form:errors path="username" cssClass="error"/>
            </div>
          </div>

          <div class="field-wrap">
            <label>
              Email Address<span class="req">*</span>
            </label>
            <form:input path="email"/>
			<form:errors path="email" cssClass="error"/>
          </div>
          
          <div class="field-wrap">
            <label>
              Set A Password<span class="req">*</span>
            </label>
            <form:password path="password"/>
			<form:errors path="password" cssClass="error"/>
          </div>
          
          <button type="submit" class="button button-block">Get Started</button>
          
          </form:form>
        </div> 
        </div><!-- tab-content -->
      

</body>
</html>