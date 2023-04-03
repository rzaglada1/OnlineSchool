<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table courses</title>
    <style>
        td, table, th {
            border: 1px solid black;
        }
    </style>

</head>
<body>

<h1>Table courses</h1>
<table>
    <thead>
    <tr>
        <th>Name</th>
        <th>Details</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.name}</td>
            <td><a href="${pageContext.request.contextPath}/course_detail?Id=${course.ID}">details...</a></td>
        </tr>
    </c:forEach>

    </tbody>

</table>
<br>
<a href="${pageContext.request.contextPath}/">Redirect to home page</a>

</body>
</html>
