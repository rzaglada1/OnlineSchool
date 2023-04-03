<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>List Persons sorted by last name and courses</title>
  <style>
    td, table, th {
      border: 1px solid black;
    }
  </style>
</head>
<body>

<h1>List Persons sorted by name and courses</h1>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Last name</th>
    <th>Count courses</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="person" items="${personIntegerMap}">
    <tr>
      <td>${person.key.ID}</td>
      <td>${person.key.name}</td>
      <td>${person.key.lastName}</td>
      <td>${person.value}</td>
    </tr>
  </c:forEach>

  </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>
