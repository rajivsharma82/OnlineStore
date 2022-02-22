<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<%--<!DOCTYPE html>--%>
<%--<html lang="en">--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--    <meta http-equiv="X-UA-Compatible" content="IE=edge">--%>
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
<%--    <title>Form Validation</title>--%>
<%--    <link rel="stylesheet" href="../../../pub/css/signup.css">--%>
<%--    <link rel="icon" type="image/png" href="https://www.google.com/s2/u/0/favicons?domain=css-tricks.com">--%>
<%--    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">--%>
<%--</html>--%>
<head>
    <link rel="stylesheet" href="../../../pub/css/signup.css">
</head>

<body>
<%--    <link rel="stylesheet" href="../../../pub/css/signup.css">--%>

<div class="container-signup" style="margin-left: 30%" >
<%--    <div class="container-signup" style="width: 80%">--%>
    <h1>Sign Up Today!</h1>
    <!-- Form  -->
    <form id="form" method="POST" action="/registration-url-path/registerSubmit">
        <!-- First Name -->
        <div class="form-group">
            <label for="firstName"> First Name</label>
            <input type="text" id="firstName" name = "firstName" placeholder="First Name"
                   value="${formBeanKey.firstName}" required minlength="3" maxlength="20">
        </div>

        <!-- Last Name -->
        <div class="form-group">
            <label for="lastName"> Last Name</label>
            <input type="text" id="lastName" name = "lastName" placeholder="Last Name"
                   value="${formBeanKey.lastName}" required minlength="3" maxlength="20">
        </div>

        <!-- Phone -->
        <div class="form-group">
            <label for="phone"> Phone Number</label>
            <input type="tel" id="phone" name = "phone" placeholder="555-555-5555" required
                   value="${formBeanKey.phone}" pattern="[0-9]{3}-[0-9]{3}-[0-9]{4}">
        </div>

        <!-- User Name -->
        <div class="form-group">
            <label for="userName"> User Name</label>
            <input type="text" id="userName" name = "userName" placeholder="User Name"
                   value="${formBeanKey.userName}" required minlength="3" maxlength="20">
        </div>

        <!-- Email Address -->
        <div class="form-group">
            <label for="email"> Email</label>
            <input type="email" id="email" name = "email" placeholder="email@address.com"
                   value="${formBeanKey.email}" required>
        </div>

<%--        <!-- Password-->--%>
        <div class="form-group">
            <label for="password"> Enter Password</label>
            <input type="password" id="password" name = "password" placeholder="Enter password(min 8 char)" required
<%--                   title="please include 1 uppercase, 1 lowercase and 1 number."--%>
<%--                   pattern="^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$"--%>
                   value="${formBeanKey.password}" >
        </div>

        <!-- Confirm Password-->
        <div class="form-group">
            <label for="confirmPassword"> Confirm Password</label>
            <input type="password" id="confirmPassword" name = "confirmPassword" placeholder="Confirm password"
                   value="${formBeanKey.confirmPassword}" required>
        </div>

<%--        <!-- Confirm Password-->--%>
<%--        <div class="form-group">--%>
<%--            <label for="confirmPassword"> Confirm Password</label>--%>
<%--            <input type="password" id="confirmPassword" name = "confirmPassword" placeholder="Confirm password"--%>
<%--                   value="${formBeanKey.confirmPassword}" required>--%>
<%--        </div>--%>

        <button type = "submit"> Register </button>
    </form>

    <!-- Error/Success Message -->
    <div class="message-container">
<%--        <h3 id="message">Don't Hesitate!</h3>--%>
        <div>
            <c:forEach items="${formBeanKey.errorMessages}" var="message">
                <span style="color:red">${message}</span><br>
            </c:forEach>
        </div>
    </div>

    <p>Already have an account? <a href="/login/login">login here</a> </p>

</div>


<%--<!-- Script -->--%>
<%--<script src="../../../pub/js/signup.js"></script>--%>
</body>
</html>
