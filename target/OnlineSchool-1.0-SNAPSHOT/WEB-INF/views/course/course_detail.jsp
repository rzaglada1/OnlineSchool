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

<h1>Course detail</h1>
<table>
    <thead>
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Data created</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${course.ID}</td>
            <td>${course.name}</td>
            <td>${course.creationDate}</td>

        </tr>
    </tbody>
</table>

<a href="${pageContext.request.contextPath}/">Redirect to home page</a>
</body>
</html>