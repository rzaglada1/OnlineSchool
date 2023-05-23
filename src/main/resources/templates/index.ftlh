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


    <main>
        <h1>Menu</h1>

        <ol>
            <li>
                <a href="/courses">General entity information Course </a>
            </li>
            <li>
                <a href="/courses/new">New Course</a>
            </li>
            <li>
                <a href="/persons/students">List students sorted by last name</a>
            </li>
            <li>
                <a href="/lectures/homeworks">List lectures with max homework</a>
            </li>
            <li>
                <a href="/materials/category">Count AddMaterials by category</a>
            </li>
            <li>
                <a href="/persons/sort">List teachers selected by last name begin with
                    <"N" or "Н"</a>
            </li>
            <li>
                <a href="/persons/courses">List Persons sorted by last name and
                    courses</a>
            </li>
            <li>
                <a href="/tests">Tests</a>
            </li>

            <li>
                <a href="/users/new">New user registration</a>
            </li>
        </ol>
    </main>
    <footer>

    </footer>
</div>
</body>
</html>