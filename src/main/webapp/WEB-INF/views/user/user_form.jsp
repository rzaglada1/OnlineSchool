<!DOCTYPE html>
<html lang="en">
<head>
    <%--    <script defer charset="UTF-8" src="user_form.js"></script>--%>
    <title>User form</title>

    <style>
        td, table, th {
            border: 1px solid black;
        }

        td {
            text-align: left;
        }
    </style>
</head>
<body>

<form class="form" >
    <table>

        <tr>
            <td>Enter your email</td>
            <td><input type="text" name="email" id="email"></td>
        </tr>
        <tr style="color: red" id="emailError" hidden>
            <td>email is not valid</td>
        </tr>

        <tr>
            <td>Enter your first name</td>
            <td><input type="text" name="firstName" id="firstName"></td>
        </tr>
        <tr style="color: red" id="firstNameError" hidden>
            <td>first name is not valid</td>
        </tr>

        <tr>
            <td>Enter your last name</td>
            <td><input type="text" name="lastName" id="lastName"></td>
        </tr>
        <tr style="color: red" id="lastNameError" hidden>
            <td>last name is not valid</td>
        </tr>

        <tr>
            <td>Enter your password</td>
            <td><input type="text" name="password" id="password"></td>
        </tr>
        <tr style="color: red" id="passwordError" hidden>
            <td>password is not valid</td>
        </tr>


        <tr>
            <td></td>
            <td><input type="submit" value="Registration"/></td>
        </tr>

    </table>
</form>
<script >

        const form = document.querySelector('.form');
        console.log(form)

        form.addEventListener('submit', (event) => {

            event.preventDefault();
            let isCheck = true;

            const emailInput = document.getElementById('email');
            const email = emailInput.value;
            if (email == null || email === '') {
                document.getElementById('emailError').hidden = false;
                isCheck = false;
            } else {
                document.getElementById('emailError').hidden = true;
            }

            const lastNameInput = document.getElementById('lastName');
            const lastName = lastNameInput.value;
            if (lastName == null || lastName === '') {
                document.getElementById('lastNameError').hidden = false;
                isCheck = false;
            } else {
                document.getElementById('lastNameError').hidden = true;
            }

            const firstNameInput = document.getElementById('firstName');
            const firstName = firstNameInput.value;
            if (firstName == null || firstName === '') {
                document.getElementById('firstNameError').hidden = false;
                isCheck = false;
            } else {
                document.getElementById('firstNameError').hidden = true;
            }

            const passwordInput = document.getElementById('password');
            const password = passwordInput.value;
            if (password == null || password === '') {
                document.getElementById('passwordError').hidden = false;
                isCheck = false;
            } else {
                document.getElementById('passwordError').hidden = true;
            }

            let xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8080/auth/registration')
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");

            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4) {
                    console.log(xhr.status);
                    console.log(xhr.responseText);
                }};

            var data = JSON.stringify({"email": email, "firstName":firstName, "lastName":lastName, "password": password});

            if (isCheck) {
                xhr.send(data);
            }


        })



</script>
</body>
</html>