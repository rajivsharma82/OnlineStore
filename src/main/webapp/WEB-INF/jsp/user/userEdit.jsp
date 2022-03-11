<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />
<div style="align-content: center; margin-left: 20rem; margin-right: 3rem ; margin-top: 5rem">

<form method="POST" action="/registration-url-path/editUserSubmit">

    <input type="hidden" name="userEditId" value="${userEditId}">

    <table cellpadding="5">

        <tr>
            <td> <label for ="Username" class="form-label fs-4" > Username </label></td>
            <td><input type="text"  class="form-control-lg" id="Username"   name="userName" value="${formBeanKey.userName}" required><td>
        </tr>
        <tr>
            <td><label for ="email" class="form-label fs-4" > Email </label></td>
            <td><input type="text" class="form-control-lg" name="email" value="${formBeanKey.email}" id="email"  required><td>
        </tr>
        <tr>
            <td><label for ="firstName" class="form-label fs-4" > First Name </label></td>
            <td><input type="text" class="form-control-lg" name="firstName" value="${formBeanKey.firstName}" id="firstName" required><td>
        </tr>
        <tr>
            <td><label for ="lastName" class="form-label fs-4" > Last Name </label></td>
            <td><input type="text" class="form-control-lg" name="lastName" value="${formBeanKey.lastName}" id="lastName"><td>
        </tr>

        <tr>
            <td><label for ="password" class="form-label fs-4" > Password </label></td><td>
            <input type="password" class="form-control-lg" name="password" value="${formBeanKey.password}" id="password" required><td>
        </tr>
        <tr>
            <td><label for ="confirmPassword" class="form-label fs-4" > Confirm Password </label></td>
            <td><input type="password" class="form-control-lg" name="confirmPassword" value="${formBeanKey.confirmPassword}" id="confirmPassword" required><td>
        </tr>

        <tr>
            <td><label for ="phone" class="form-label fs-4" > Phone </label></td>
            <td><input type="text" class="form-control-lg" name="phone" value="${formBeanKey.phone}" id="phone"><td>
        </tr>

    </table>


	<button type="submit" class="btn btn-primary edituser" role="button" onclick="edituserfun()">Submit</button>

</form>

<div>
    <c:forEach items="${formBeanKey.errorMessages}" var="message">
        <span style="color:red">${message}</span><br>
    </c:forEach>
</div>

</div>

<script>
    document.getElementsByClassName("edituser").addEventListener("onclick", edituserfun);

    function edituserfun() {
        alert("The user details are updated in system");
    }
</script>

<jsp:include page="../include/footer.jsp" />