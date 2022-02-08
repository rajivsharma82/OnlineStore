
<%--<jsp:include page="../include/header.jsp"/>--%>

<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body>
<div class="container-login">
    <h1>Login</h1>
    <!-- Form  -->
    <form id="form"  method="GET" action="/loginSubmit">

        <!-- Full Name -->
        <div class="form-group">
            <label for="name"> Full Name</label>
            <input type="text" id="name" name = "name" placeholder="Full Name" required minlength="3" maxlength="100">
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

    <p>Don't have an account? <a href="signup">signup here</a> </p>
</div>
</body>
</html>