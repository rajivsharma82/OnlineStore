<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
<%--    <link rel="stylesheet" href="styles.css" />--%>
    <link rel="stylesheet" href="../../../pub/css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>The Online | Store</title>

<script type="text/javascript">
    function getCartRowProductQuantity(val){
       return document.getElementById(val).value;
    }

</script>
</head>


<body>

<section class="container content-section">
    <h2 class="section-header">CART</h2>
    <div class="cart-row">
        <span class="cart-item cart-header cart-column">ITEM</span>
        <span class="cart-price cart-header cart-column">PRICE</span>
        <span class="cart-quantity cart-header cart-column">QUANTITY</span>
    </div>

    <div class="cart-items">
<%--        <div class="cart-row">--%>
<%--          <div class="cart-item cart-column">--%>
<%--            <img class="cart-item-image" src="../Images/Shirt.png" width="20" height="20"/>--%>
<%--            <span class="cart-item-title">T Shirt</span>--%>
<%--          </div>--%>
<%--          <span class="cart-price cart-column">$19.99</span>--%>
<%--          <div class="cart-quantity cart-column">--%>
<%--            <input class="cart-quantity-input" type="text" value="1" />--%>
<%--            <button class="btn btn-danger " type="button">REMOVE</button>--%>
<%--          </div>--%>

<%--        </div>--%>

<c:forEach items="${orderItemListKey}" var="cartItem">
          <div class="cart-row">

            <div class="cart-item cart-column">
              <img class="cart-item-image" src= "${cartItem.product.imageUrl}"  width="20" height="20"/>
              <span class="cart-item-title">${cartItem.product.name}</span>
            </div>
            <span class="cart-price cart-column">$ ${cartItem.unitPrice}</span>
            <div class="cart-quantity cart-column">
              <form method="GET" action="/updateItemFromCart">
                  <input type="hidden" name = "cartItemId"   value=${cartItem.id} />
                  <input type="hidden" name = "cartItemOrderId"   value=${cartItem.order.id} />
                <input class="cart-quantity-input" type="text" id = "id-${cartItem.id}" name = "quantityField" value=${cartItem.quantity} />
<%--              <button class="btn btn-danger " type="button">REMOVE</button>--%>
<%--                <a class="btn btn-danger"  role="button" href="/updateItemFromCart?id=${cartItem.id}&quantity=" + getCartRowProductQuantity(`id-${cartItem.id}`)>--%>
<%--                    <a class="btn btn-danger"  type="submit" role="button" > UPDATE </a>--%>
                  <button class="btn btn-danger btn-sm"  type="submit">UPDATE</button>
<%--                  <a class="btn "  role="button" href="/updateItemFromCart?id=${cartItem.id}">--%>
                  <a class="btn btn-danger btn-sm"  role="button" href="/deleteItemFromCart?id=${cartItem.id}&orderId=${cartItem.order.id}">
                      REMOVE </a>
              </form>
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

    <a button class= "btn btn-primary btn-purchase" type="button" href="/checkOutCart?orderId=${orderIdInSession}">
        Checkout </a>
<%--    How to get the order id from the list ${orderItemListKey} --%>


<%--    <button class= "btn btn-primary btn-purchase" role="button" href="/checkOutCart?orderId=${cartItem.order.id}">PURCHASE</button>--%>

</section>
<jsp:include page="../include/footer.jsp" />
<%--<footer class="main-footer">--%>
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

