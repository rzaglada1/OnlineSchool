
<html>
<head>
    <title>List Persons sorted by last name and courses</title>
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

        <h1>List Persons sorted by name and courses</h1>
        <table class="bold">
            <thead>
            <tr>
                <th>Name</th>
                <th>Last name</th>
                <th>Count courses</th>
            </tr>
            </thead>
            <tbody>
                <#list personIntegerMap as key, value>
                <tr>
                    <td>${key.name}</td>
                    <td>${key.lastName}</td>
                    <td>${value}</td>
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
