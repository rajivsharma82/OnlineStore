<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />
<div style="align-content: center; margin-left: 3rem; margin-right: 3rem">
    <h2 style="margin-top: 2rem; align-self: center">User Queries</h2>

<table class="table table-striped" style="margin-top: 4rem">
    <thead>
    <tr>
        <th scope="col">Order#</th>
        <th scope="col">Status</th>
        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Query Raised</th>
        <th scope="col">Admin Response</th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${userQueryList}" var="query">
        <tr>
            <td>${query.orderTrackingNumber}</td>
            <td>${query.status}</td>
            <td>${query.user.email}</td>
            <td>${query.user.phone}</td>
            <td>${query.userQuery}</td>
            <td>${query.adminResponse}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</div>

<jsp:include page="../include/footer.jsp" />