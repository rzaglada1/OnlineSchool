<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of tables in the database</title>
    <style>
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
        <h1>Menu</h1>

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
                <a href="${pageContext.request.contextPath}/lectures/homeworks">List lectures with max homework</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/materials/category">Count AddMaterials by category</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/persons/sort">List teachers selected by last name begin with
                    <"N" or "Н"</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/persons/courses">List Persons sorted by last name and
                    courses</a>
            </li>
        </ol>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>
