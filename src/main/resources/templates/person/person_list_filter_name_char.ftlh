<html>
<head>
    <title>List teachers selected by last name begin with <"N" or "Н"</title>
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

    <main>

        <h1>List teachers selected by last name begin with <"N" or "Н"</h1>
        <table class="bold">
            <thead>
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Last name</th>
                <th>Phone</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
            </thead>
            <tbody>
                <#list persons as person>
                <tr>
                    <td>${person.ID}</td>
                    <td>${person.name}</td>
                    <td>${person.lastName}</td>
                    <td>${person.phone}</td>
                    <td>${person.email}</td>
                    <td>${person.role}</td>
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


