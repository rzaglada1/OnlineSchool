<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Table courses</title>
</head>
<body>

<h1>Create new course</h1>

<form action="${pageContext.request.contextPath}/courses/new" method="post">
  <LABEL for="Name">Name</LABEL>
  <INPUT type="text" id="Name" name="Name" required> <br><br>

<button type="submit">Create</button>
</form>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>
