<!DOCTYPE html>
<html lang="en">
<head>
  <title>User login</title>

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

<form class="form"  action="/login" method="post">
  <table>

    <tr>
      <td>Enter your email</td>
      <td><input type="text" name="email" id="email"></td>
    </tr>

    <tr>
      <td>Enter your password</td>
      <td><input type="text" name="password" id="password"></td>
    </tr>

    <tr>
      <td></td>
      <td><input type="submit" value="Sign in"/></td>
    </tr>

  </table>
</form>
</body>
</html>
