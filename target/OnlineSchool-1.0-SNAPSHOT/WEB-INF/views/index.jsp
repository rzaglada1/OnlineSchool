<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of tables in the database</title>
</head>
<body>

<h1>Menu</h1>
<h1>Online school</h1>

<ol>

    <li>
        <a href="${pageContext.request.contextPath}/courses">General entity information Course </a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/courses/new">New Course</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/persons/students">List students sorted by last name</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/lectures/materials">List lectures sorted by date</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/lectures/homeworks">List lectures with max homework</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/materials/category">Count AddMaterials by category</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/persons/sort">List teachers selected by last name begin with <"N" or "Н"</a>
    </li>
    <li>
        <a href="${pageContext.request.contextPath}/persons/courses">List Persons sorted by last name and courses</a>
    </li>



</ol>


</body>
</html>
