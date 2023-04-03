<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New course</title>
    <style>
        td, table, th {
            border: 1px solid black;
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

        <h1>Create new course</h1>

        <form action="${pageContext.request.contextPath}/courses/new" method="post">
            <LABEL for="Name">Name</LABEL>
            <INPUT type="text" id="Name" name="Name" required> <br><br>

            <button type="submit">Create</button>
        </form>
        <br>
        <a href="${pageContext.request.contextPath}/">Redirect to home page</a>

    </main>
</div>


</body>
</html>
