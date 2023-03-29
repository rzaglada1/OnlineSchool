<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table courses</title>
</head>
<body>

<h1>Table courses</h1>
<table border="1">
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


</body>
</html>
