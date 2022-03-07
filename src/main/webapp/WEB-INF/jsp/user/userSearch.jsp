<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<form method="get" action="/registration-url-path/userSearch" >
   <table style="margin-top: 2rem">
       <tr>
           <td>
               <input type="text" name="search" value="${searchKey}">
           </td>
           <td>
               <button type="submit">Search</button>
           </td>
       </tr>
   </table>


</form>

<%--<form method="get" action="/registration-url-path/userList">--%>
<%--    First Name <input type="text" name="firstName">--%>
<%--    <br>--%>
<%--    Last Name <input type="text" name="lastName">--%>
<%--    <button type="submit">Search</button>--%>
<%--</form>--%>


<%--<%--%>
<%--    if(session.getAttribute("username") != null) {--%>

<%--        out.println("username inside different page ");--%>
<%--        out.println(session.getAttribute("username"));--%>

<%--    }--%>
<%--    session.setAttribute("cartItems","123-1,899-1");--%>

<%--%>--%>

<%--<table border="1">--%>
<%--    <tr>--%>
<%--        <td><b>Id</b></td>--%>
<%--        <td><b>Username</b></td>--%>
<%--        <td><b>Email</b></td>--%>
<%--        <td><b>First Name</b></td>--%>
<%--        <td><b>Last Name</b></td>--%>
<%--        <td><b>Password</b></td>--%>
<%--        <td><b>Edit</b></td>--%>
<%--        <td><b>Delete</b></td>--%>
<%--    </tr>--%>
<%--    <c:forEach items="${userSearchList}" var="user">--%>
<%--        <tr>--%>
<%--            <td>${user.id}</td>--%>
<%--            <td>${user.username}</td>--%>
<%--            <td>${user.email}</td>--%>
<%--            <td>${user.firstName}</td>--%>
<%--            <td>${user.lastName}</td>--%>
<%--            <td>${user.password}</td>--%>
<%--            <td><a class="btn btn-primary"  role="button" href="/registration-url-path/register?id=${user.id}">Edit</a></td>--%>
<%--            <td><a class="btn btn-danger" role="button" href="/registration-url-path/deleteUser?id=${user.id}">Delete</a></td>--%>
<%--        </tr>--%>
<%--    </c:forEach>--%>
<%--</table>--%>


<table class="table table-bordered" style="margin-top: 4rem">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">First Name</th>
        <th scope="col">Last Name</th>
        <th scope="col">Email</th>
        <th scope="col">User Name</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${userSearchList}" var="user">
        <tr>
            <td>${user.id}</td>

            <td>${user.firstName}</td>
            <td>${user.lastName}</td>

            <td>${user.email}</td>
            <td>${user.username}</td>

            <td><a class="btn btn-primary btn-sm"  role="button" href="/registration-url-path/editUser?id=${user.id}">Edit</a></td>
            <td><a class="btn btn-danger btn-sm deleteUser" role="button" onclick="deleteUserFun()" href="/registration-url-path/deleteUser?id=${user.id}&searchKey=${searchKey}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<script>
    document.getElementsByClassName("deleteUser").addEventListener("onclick", deleteUserFun);

    function deleteUserFun() {
        alert("Please button to confirm user deletion from the system");
    }
</script>

<jsp:include page="../include/footer.jsp" />