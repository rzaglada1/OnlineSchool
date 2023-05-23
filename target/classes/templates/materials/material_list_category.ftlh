
<html>
<head>
    <title>List students sorted by last name</title>
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

        <h1>Count AddMaterials by category</h1>
        <table class="bold">
            <thead>
            <tr>
                <th>Category</th>
                <th>Count</th>
            </tr>
            </thead>
            <tbody>

                <#list mapMaterials as key, value>
                <tr>
                    <td>${key}</td>
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