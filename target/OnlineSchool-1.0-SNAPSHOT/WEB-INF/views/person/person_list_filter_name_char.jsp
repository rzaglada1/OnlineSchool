<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>List teachers selected by last name begin with <"N" or "Н"</title>
  <style>
    td, table, th {
      border: 1px solid black;
    }
  </style>
</head>
<body>

<h1>List teachers selected by last name begin with <"N" or "Н"</h1>
<table>
  <thead>
  <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Last name</th>
    <th>Phone</th>
    <th>Email</th>
    <th>Role</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="person" items="${persons}">
    <tr>
      <td>${person.ID}</td>
      <td>${person.name}</td>
      <td>${person.lastName}</td>
      <td>${person.phone}</td>
      <td>${person.email}</td>
      <td>${person.role}</td>
    </tr>
  </c:forEach>

  </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>


