<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<%--<c:choose>--%>
<%--  <c:when test="${empty formBeanKey.id}">--%>
<%--    <h1>Create New User</h1>--%>
<%--  </c:when>--%>
<%--  <c:otherwise>--%>
<%--    <h1>Edit User</h1>--%>
<%--  </c:otherwise>--%>
<%--</c:choose>--%>

<form method="POST" action="/registration-url-path/editUserSubmit">

    <input type="hidden" name="userEditId" value="${userEditId}">

    <table cellpadding="5">

        <tr>
            <td>Username</td>
            <td><input type="text" name="userName" value="${formBeanKey.userName}" required><td>
        </tr>
        <tr>
            <td>Email</td>
            <td><input type="text" name="email" value="${formBeanKey.email}" required><td>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type="text" name="firstName" value="${formBeanKey.firstName}" required><td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type="text" name="lastName" value="${formBeanKey.lastName}" ><td>
        </tr>

        <tr>
            <td>Password</td><td>
            <input type="password" name="password" value="${formBeanKey.password}" required><td>
        </tr>
        <tr>
            <td>Confirm Password</td>
            <td><input type="password" name="confirmPassword" value="${formBeanKey.confirmPassword}" required><td>
        </tr>

        <tr>
            <td>Phone</td>
            <td><input type="text" name="phone" value="${formBeanKey.phone}"><td>
        </tr>

    </table>


	<button type="submit" class="btn btn-primary edituser" role="button" onclick="edituserfun()">Submit</button>

<%--    <c:if test="${not empty formBeanKey.id}">--%>
<%--        <a class="btn btn-danger" role="button" href="/registration-url-path/deleteUser?id=${formBeanKey.id}">Delete</a>--%>
<%--    </c:if>--%>


</form>

<div>
    <c:forEach items="${formBeanKey.errorMessages}" var="message">
        <span style="color:red">${message}</span><br>
    </c:forEach>
</div>

<script>
    document.getElementsByClassName("edituser").addEventListener("onclick", edituserfun);

    function edituserfun() {
        alert("The user details are updated in system");
    }
</script>

<jsp:include page="../include/footer.jsp" />