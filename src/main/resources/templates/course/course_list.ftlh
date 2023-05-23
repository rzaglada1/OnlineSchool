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

                <#list courses as course>
                <tr>
                    <td>${course.name}</td>
                    <td><a href="/course_detail/${course.ID}">details...</a></td>
                </tr>
                </#list>

            </tbody>

        </table>
        <br>
        <a href="/">Redirect to home page</a>

    </main>
</div>
</body>
</html>
