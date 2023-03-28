<%--
  Created by IntelliJ IDEA.
  User: ZRuslan
  Date: 26.03.2023
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table courses</title>
</head>
<body>
<c:forEach var = "course" items = "${courses}">

<p>${course.name}</p>

</c:forEach>
<p>Чому не працює цикл ?</p>


</body>
</html>
