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

<h1>Count AddMaterials by category</h1>
<table>
  <thead>
  <tr>
    <th>Category</th>
    <th>Count</th>
  </tr>
  </thead>
  <tbody>
  <c:forEach var="mapMaterial" items="${mapMaterials}">
    <tr>
      <td>${mapMaterial.key}</td>
      <td>${mapMaterial.value}</td>

    </tr>
  </c:forEach>

  </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>