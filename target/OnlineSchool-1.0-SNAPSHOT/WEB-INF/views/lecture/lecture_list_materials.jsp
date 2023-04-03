<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>List students sorted by last name</title>
  <style>
    td, table, th {
      border: 1px solid black;
      text-align: center;
    }
  </style>
</head>
<body>

<h1>List lectures sorted by date</h1>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Count AddMaterials</th>
    <th>Lecture date</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="lecture" items="${lectures}">
    <tr>
      <td>${lecture.ID}</td>
      <td>${lecture.name}</td>
      <td>${lecture.addMaterialsCount}</td>
      <td>${lecture.lectureDate}</td>
    </tr>
  </c:forEach>

  </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>