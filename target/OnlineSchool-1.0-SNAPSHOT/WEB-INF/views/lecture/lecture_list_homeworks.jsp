<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List lectures with max homework</title>
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
            font-size: 18px;
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

        <h1>List lectures with max homework</h1>
        <table class="bold">
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

    </main>
</div>
</body>
</html>