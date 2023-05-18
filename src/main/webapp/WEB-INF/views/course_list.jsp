<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table courses</title>
    <style>
        td, table, th {
            border: 1px solid black;
        }

        .bold tbody tr:nth-child(2n) {
            font-weight: bold;
        }

         body {
             display: flex;
             justify-content: start;
             font-family: "Montserrat";
             font-weight: 500;
             font-size: 16px;
         }

        .container {
            display: flex;
            flex-direction: column;
            max-width: 1160px;
            min-width: 400px;
            width: 900px;
        }

        .nav {
            display: flex;
            gap: 30px;
            font-weight: 600;
            font-size: 18px;
        }

        a:hover {
            color: brown;
            font-size: 18px ;
        }
    </style>

</head>
<body>
<div class="container">
    <header>
        <h1>Online school</h1>
    </header>

    <nav>
        <%--        <ul class="nav">--%>
        <%--            <li>Опція1</li>--%>
        <%--            <li>Опція2</li>--%>
        <%--        </ul>--%>
    </nav>

    <main>

<h1>Table courses</h1>
<table class="bold">
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

    </main>
</div>
</body>
</html>
