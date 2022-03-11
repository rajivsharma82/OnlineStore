<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<div style="align-content: center; margin-left: 3rem; margin-right: 3rem">



<form method="get" action="/registration-url-path/userSearch" >

    <table style="margin-top: 2rem">
        <tr>
            <td>
                <a class="btn btn-primary btn-sm"  role="button"
                   href="/registration-url-path/manageUser">All Users</a>
            </td>
        </tr>
    </table>

    <table style="margin-top: 2rem">
         <tr>
            <td>
                <input type="text" name="search"  placeholder="Enter first or last name"  value="${searchKey}">
            </td>
            <td>
                <button type="submit">Search</button>
            </td>
        </tr>


    </table>

</form>

<table class="table table-striped" style="margin-top: 4rem">
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

</div>

<script>
    document.getElementsByClassName("deleteUser").addEventListener("onclick", deleteUserFun);

    function deleteUserFun() {
        alert("Please button to confirm user deletion from the system");
    }
</script>

<jsp:include page="../include/footer.jsp" />