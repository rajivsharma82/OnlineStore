<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        body {
            font-family: Arial;
            font-size: 17px;
            padding: 8px;
        }

        * {
            box-sizing: border-box;
        }

        .row {
            display: -ms-flexbox; /* IE10 */
            display: flex;
            -ms-flex-wrap: wrap; /* IE10 */
            flex-wrap: wrap;
            margin: 0 -16px;

        }

        .col-25 {
            -ms-flex: 25%; /* IE10 */
            flex: 25%;
        }

        .col-50 {
            -ms-flex: 50%; /* IE10 */
            flex: 50%;
        }

        .col-75 {
            -ms-flex: 75%; /* IE10 */
            flex: 75%;

        }

        .col-25,
        .col-50,
        .col-75 {
            padding: 0 16px;

        }

        .container-billing {
            background-color: #f2f2f2;
            padding: 5px 20px 15px 20px;
            border: 1px solid lightgrey;
            border-radius: 3px;
        }

        input[type=text] {
            width: 100%;
            margin-bottom: 20px;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }

        label {
            margin-bottom: 10px;
            display: block;
        }

        .icon-container {
            margin-bottom: 20px;
            padding: 7px 0;
            font-size: 24px;
        }

        .btn-2 {
            background-color: #04AA6D;
            color: white;
            padding: 12px;
            margin: 10px 0;
            border: none;
            width: 100%;
            border-radius: 3px;
            cursor: pointer;
            font-size: 17px;
        }

        .btn-2:hover {
            background-color: #45a049;
        }

        a {
            color: #2196F3;
        }

        hr {
            border: 1px solid lightgrey;
        }

        span.price {
            float: right;
            color: grey;
        }

        /* Responsive layout - when the screen is less than 800px wide, make the two columns stack on top of each other instead of next to each other (also change the direction - make the "cart" column go on top) */
        @media (max-width: 800px) {
            .row {
                flex-direction: column-reverse;
            }
            .col-25 {
                margin-bottom: 20px;
            }
        }
    </style>
</head>
<body>


<div class="row">
    <div class="col-75">
        <div class="container-billing">
            <form action="/orderconfirmation" method="POST">
                <input type="hidden" name = "userIdInSession"   value="${userIdInSession}" />
                <input type="hidden" name = "orderIdInSession"   value="${orderIdInSession}" />
                <div class="row">
                    <div class="col-50">
                        <h3>Billing Address</h3>
                        <label for="fullname"><i class="fa fa-user"></i> Full Name</label>
                        <input type="text" id="fullname" name="fullname" placeholder="John M. Doe" value= "${userBilling.fullname}" required>

                        <label for="email"><i class="fa fa-envelope"></i> Email</label>
                        <input type="text" id="email" name="email" placeholder="john@example.com" value="${userBilling.email}" required>

                        <label for="adr"><i class="fa fa-address-card-o"></i> Address</label>
                        <input type="text" id="adr" name="address" placeholder="542 W. 15th Street" value="${userBilling.address}" required>

                        <label for="city"><i class="fa fa-institution"></i> City</label>
                        <input type="text" id="city" name="city" placeholder="New York" value="${userBilling.city}" required>

                        <div class="row">
                            <div class="col-50">
                                <label for="state">State</label>
                                <input type="text" id="state" name="state" placeholder="NY" value="${userBilling.state}" required>
                            </div>
                            <div class="col-50">
                                <label for="zip">Zip</label>
                                <input type="text" id="zip" name="zip" placeholder="10001" value="${userBilling.zip}" required>
                            </div>
                        </div>
                    </div>

                    <div class="col-50">
                        <h3>Payment</h3>
                        <label for="fullname">Accepted Cards</label>
                        <div class="icon-container">
                            <i class="fa fa-cc-visa" style="color:navy;"></i>
                            <i class="fa fa-cc-amex" style="color:blue;"></i>
                            <i class="fa fa-cc-mastercard" style="color:red;"></i>
                            <i class="fa fa-cc-discover" style="color:orange;"></i>
                        </div>

                        <label for="cname">Name on Card</label>
                        <input type="text" id="cname" name="cardname" placeholder="John More Doe" value="${userBilling.cardname}" required>

                        <label for="ccnum">Credit card number</label>
                        <input type="text" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required>

                        <label for="expmonth">Exp Month</label>
                        <input type="text" id="expmonth" name="expmonth" placeholder="September" required>

                        <div class="row">
                            <div class="col-50">
                                <label for="expyear">Exp Year</label>
                                <input type="text" id="expyear" name="expyear" placeholder="2018" required>
                            </div>
                            <div class="col-50">
                                <label for="cvv">CVV</label>
                                <input type="text" id="cvv" name="cvv" placeholder="352" required>
                            </div>
                        </div>
                    </div>


                </div>

                <input type="submit" value="PURCHASE" class="btn-2">
            </form>
        </div>
    </div>
</div>


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


<%--    <button class= "btn btn-primary btn-purchase" type="submit">PURCHASE</button>--%>
<%--    <a class="btn btn-primary shop-item-button"  role="button" href="/orderconfirmation">--%>
<%--    PURCHASE </a>--%>

<%--    <button type="submit">Submit</button>--%>


<jsp:include page="../include/footer.jsp" />

<%--<footer class="main-footer">--%>
<%--&lt;%&ndash;    <footer class="main-footer">&ndash;%&gt;--%>
<%--    <div class="container main-footer-container">--%>
<%--        <h3 class="band-name">The Online Store</h3>--%>
<%--        <ul class="nav footer-nav">--%>
<%--            <li>--%>
<%--                <a href="https://youtube.com" target="_blank">--%>
<%--                    <img src="../../pub/Images/YouTubeLogo.jpeg" style="height: 35px" />--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="https://Spotify.com" target="_blank">--%>
<%--                    <img src="../../pub/Images/SpotityLogo.png" style="height: 45px" />--%>
<%--                </a>--%>
<%--            </li>--%>
<%--            <li>--%>
<%--                <a href="https://facebook.com" target="_blank">--%>
<%--                    <img src="../../pub/Images/FBlogo.png" style="height: 35px" />--%>
<%--                </a>--%>
<%--            </li>--%>
<%--        </ul>--%>
<%--    </div>--%>
<%--</footer>--%>
<%--&lt;%&ndash;<script src="store.js" async></script>&ndash;%&gt;--%>
<%--</body>--%>
<%--</html>--%>

