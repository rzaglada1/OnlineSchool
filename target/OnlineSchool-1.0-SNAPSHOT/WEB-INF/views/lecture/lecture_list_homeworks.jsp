<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>List lectures with max homework</title>
  <style>
    td, table, th {
      border: 1px solid black;
      text-align: center;
    }
  </style>
</head>
<body>

<h1>List lectures with max homework</h1>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Lecture create date</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="lecture" items="${lectures}">
    <tr>
      <td>${lecture.ID}</td>
      <td>${lecture.name}</td>
      <td>${lecture.creationDate}</td>
    </tr>
  </c:forEach>

  </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>