<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<head>
    <link rel="stylesheet" href="../../../pub/css/signup.css">
</head>

<body>

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

</body>
</html>
