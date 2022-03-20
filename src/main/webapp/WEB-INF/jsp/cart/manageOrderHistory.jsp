<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<div style="align-content: center; margin-left: 3rem; margin-right: 3rem">

    <h2 style="margin-top: 2rem; align-self: center">Order History</h2>

<form method="get" action="/manageOrderHistory" >
   <table style="margin-top: 2rem">
       <tr>
           <td>
               <input type="text" name="search" placeholder="Order Tracking Number"  value="${searchKey}">
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


<table class="table table-striped" style="margin-top: 4rem">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">User Id</th>
        <th scope="col">Email</th>
        <th scope="col">Total Quantity</th>
        <th scope="col">Total Price</th>
        <th scope="col">Status</th>
        <th scope="col">Order Date</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${orderHistoryList}" var="order">
        <tr>
            <td>${order.orderTrackingNumber}</td>
            <td>${order.user.id}</td>
            <td>${order.user.email}</td>
            <td>${order.totalQuantity}</td>
            <td>${order.totalPrice}</td>

            <td>${order.status}</td>
            <td>${order.lastUpdated}</td>

            <td><a class="btn btn-primary btn-sm"  role="button" href="/manageOrderHistory?orderId=${order.id}">View</a></td>
<%--            <td><a class="btn btn-danger btn-sm" role="button" href="/registration-url-path/deleteUser?id=${user.id}&searchKey=${searchKey}">Delete</a></td>--%>
        </tr>
    </c:forEach>
    </tbody>
</table>

<c:if test="${ not empty orderId}">

<section class="container content-section" style="margin-top: 20px">
    <h2 class="section-header">Order Summary</h2>
    <div class="cart-row">
        <span class="cart-item cart-header cart-column">ITEM</span>
        <span class="cart-price cart-header cart-column">PRICE</span>
        <span class="cart-quantity cart-header cart-column">QUANTITY</span>
    </div>

    <div class="cart-items">

        <c:forEach items="${orderItemListKey}" var="cartItem">
            <div class="cart-row">

                <div class="cart-item cart-column">
                    <img class="cart-item-image" width="10" height="10" src= "${cartItem.product.imageUrl}" />
                    <span class="cart-item-title">${cartItem.product.name}</span>
                </div>
                <span class="cart-price cart-column">$ ${cartItem.unitPrice}</span>
                <div class="cart-quantity cart-column">

                    <input type="hidden" name = "cartItemId"   value=${cartItem.id} />
                    <input type="hidden" name = "cartItemOrderId"   value=${cartItem.order.id} />
                    <input class="cart-quantity-input" type="text" id = "id-${cartItem.id}" name = "quantityField" value=${cartItem.quantity} />
                        <%--

                        <%--                <a class="btn btn-danger"  role="button" href="/deleteItemFromCart?id=${cartItem.id}&orderId=${cartItem.order.id}">--%>
                        <%--                    REMOVE </a>--%>

                </div>

            </div>
        </c:forEach>

    </div>



    <div class="cart-total">
        <strong class="cart-total-title">Total</strong>
        <span class="cart-total-price">${totalOrderPrice}</span>
    </div>

    <%--    <button class= "btn btn-primary btn-purchase" type="button">PURCHASE</button>--%>

</section>
</c:if>

</div>

<jsp:include page="../include/footer.jsp" />