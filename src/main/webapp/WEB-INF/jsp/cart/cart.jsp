<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
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
                  <button class="btn btn-danger btn-sm"  type="submit">UPDATE</button>

                  <a class="btn btn-danger btn-sm"  role="button" href="/deleteItemFromCart?id=${cartItem.id}&orderId=${cartItem.order.id}">
                      REMOVE </a>
              </form>
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

</section>
<jsp:include page="../include/footer.jsp" />


