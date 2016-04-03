<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>

<h2>Post</h2>
   <table>
    <tr>
        <td>Title</td>
        <td>${title}</td>
    </tr>
    <tr>
        <td>Description</td>
        <td>${description}</td>
    </tr>
    <tr>
        <td>ID</td>
        <td>${id}</td>
    </tr>
     <tr>
        <td>Picture</td>
        <td>${pic}</td>
        <img src="${path}" width="200" height="200">
    </tr>
</table>  
    
</body>
</html>