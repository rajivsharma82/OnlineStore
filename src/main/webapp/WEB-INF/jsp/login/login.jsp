
<jsp:include page="../include/header.jsp"/>

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<head>
    <link rel="stylesheet" href="../../../pub/css/signup.css">
</head>
<body>

<div class="container-login" style=" margin-top: 10% ; margin-left: 25%">
    <h1>Login</h1>
    <!-- Form  -->
<%--    <form id="form"  method="GET" action="/loginSubmit">--%>
        <form action="/login/loginSecurityPost" method="POST">

        <!-- Full Name -->
        <div class="form-group">
            <label for="email"> Email</label>
            <input type="text" id="email" name = "username" placeholder="Email" required minlength="3" maxlength="25">
        </div>

        <!-- Password-->
        <div class="form-group">
            <label for="password"> Enter Password</label>
            <input type="password" id="password" name = "password" placeholder="Enter password(min 8 char)" required >
        </div>
        <br><br>

        <button type="submit">Login</button>

        <h3 style="color: red"> ${ errormessage}  </h3>
    </form>

    <p>Don't have an account? <a href="/registration-url-path/register">signup here</a> </p>
</div>
</body>
</html>