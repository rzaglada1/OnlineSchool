<html>
<head>
    <title>Table courses</title>
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

    <main>
        <h1>Course detail</h1>
        <table class="courses">
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

        <a href="/">Redirect to home page</a>
    </main>
</div>
</body>
</html>