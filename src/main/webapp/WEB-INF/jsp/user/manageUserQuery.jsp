<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<jsp:include page="../include/header.jsp" />
<div style="align-content: center; margin-left: 3rem; margin-right: 3rem">
    <h2 style="margin-top: 2rem; align-self: center">Manage User Queries</h2>
<form method="get" action="/manageOrderHistory" >
   <table style="margin-top: 2rem">
       <tr>
           <td>
               <input type="text" name="search" placeholder="Order Tracking Number">
           </td>
           <td>
               <button type="submit">View Order Details</button>
           </td>
       </tr>
   </table>
</form>

    <form method="get" action="/admin/manageUserQuery" >
        <table style="margin-top: 2rem">
            <tr>
                <td>
                    <input type="text" name="search" placeholder="Order Tracking Number">
                </td>
                <td>
                    <button type="submit">Search By Tracking #</button>
                </td>
            </tr>
        </table>

        <table style="margin-top: 2rem">
            <tr><td><h4>Search Queries by Status</h4></td></tr>
            <tr>
                <td>
<%--                    <button class="btn btn-primary btn-lg"  type="submit">Open</button>--%>
                    <a class="btn btn-primary btn-lg"  role="button" href="/admin/manageUserQuery?queryStatus=open">Open</a>
                    <a class="btn btn-primary btn-lg"  role="button" href="/admin/manageUserQuery?queryStatus=closed">Closed</a>
                    <a class="btn btn-primary btn-lg"  role="button" href="/admin/manageUserQuery">All</a>
                </td>
            </tr>


        </table>
    </form>

<%--    <form method="get" action="/admin/manageUserQuery" >--%>
<%--        <table style="margin-top: 2rem">--%>
<%--            <tr>--%>
<%--                <td>--%>
<%--                    <input type="text" name="search" placeholder="Order Tracking Number">--%>
<%--                </td>--%>
<%--                <td>--%>
<%--                    <button type="submit">Search Query by Order Tracking Number</button>--%>
<%--                </td>--%>
<%--            </tr>--%>
<%--        </table>--%>
<%--    </form>--%>

<table class="table table-striped" style="margin-top: 4rem">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Status</th>

        <th scope="col">Email</th>
        <th scope="col">Phone</th>
        <th scope="col">Query</th>
        <th scope="col">Admin Response</th>
        <th scope="col">Update</th>
        <th scope="col">Previous Orders</th>

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

<%--            <td><input type="text" name="adminResponse" placeholder="adminResponse"  value="${query.adminResponse}"></td>--%>
<%--            <td><a class="btn btn-primary btn-sm"  role="button" href="/manageOrderHistory?userId=${query.user.id}">Previous Orders</a></td>--%>
<%--            <td><a class="btn btn-primary btn-sm"  role="button" href="/admin/manageUserQuery?id=${query.id}&adminResponse=${}">Admin Response</a></td>--%>
        <td>
            <form method="GET" action="/admin/manageUserQuery">
                <input type="hidden" name = "id"   value=${query.id} />
                <input type="text" name="adminResponse" placeholder="adminResponse"  value="${query.adminResponse}">
<%--                <td><a class="btn btn-primary btn-sm"  role="button" href="/admin/manageUserQuery?id=${query.id}&adminResponse=${}">Admin Response</a></td>--%>
                <td><button class="btn btn-primary btn-sm"  type="submit">Admin Response</button></td>
            </form>
        </td>
            <td><a class="btn btn-primary btn-sm"  role="button" href="/manageOrderHistory?userId=${query.user.id}">Previous Orders</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</div>

<jsp:include page="../include/footer.jsp" />