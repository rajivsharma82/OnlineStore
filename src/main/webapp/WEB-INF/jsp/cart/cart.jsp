<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
<%--    <link rel="stylesheet" href="styles.css" />--%>
    <link rel="stylesheet" href="../../../pub/css/styles.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />

    <title>The Corrs | Store</title>
</head>
<body>
<%--<header class="main-header">--%>
<%--    <nav class="main-nav nav">--%>
<%--        <ul>--%>
<%--            <li><a href="index.html"> HOME</a></li>--%>
<%--            <li><a href="store.html"> STORE</a></li>--%>
<%--            <li><a href="about.html"> ABOUT</a></li>--%>
<%--            <li><a href="cart.html"> Cart</a></li>--%>
<%--        </ul>--%>
<%--    </nav>--%>

<%--    <h1 class="band-name band-name-large">The Corrs</h1>--%>
<%--</header>--%>


<section class="container content-section">
    <h2 class="section-header">CART</h2>
    <div class="cart-row">
        <span class="cart-item cart-header cart-column">ITEM</span>
        <span class="cart-price cart-header cart-column">PRICE</span>
        <span class="cart-quantity cart-header cart-column">QUANTITY</span>
    </div>

    <div class="cart-items">
        <div class="cart-row">
          <div class="cart-item cart-column">
            <img class="cart-item-image" src="../Images/Shirt.png" width="20" height="20"/>
            <span class="cart-item-title">T Shirt</span>
          </div>
          <span class="cart-price cart-column">$19.99</span>
          <div class="cart-quantity cart-column">
            <input class="cart-quantity-input" type="text" value="1" />
            <button class="btn btn-danger " type="button">REMOVE</button>
          </div>

        </div>


          <div class="cart-row">
            <div class="cart-item cart-column">
              <img class="cart-item-image" src="../Images/Cofee.png" width="20" height="20"/>
              <span class="cart-item-title">T Shirt</span>
            </div>
            <span class="cart-price cart-column">$12.99</span>
            <div class="cart-quantity cart-column">
              <input class="cart-quantity-input" type="text" value="1" />
              <button class="btn btn-danger " type="button">REMOVE</button>
            </div>

          </div>
    </div>



    <div class="cart-total">
        <strong class="cart-total-title">Total</strong>
        <span class="cart-total-price">$0.00</span>
    </div>

    <button class= "btn btn-primary btn-purchase" type="button">PURCHASE</button>

</section>

<footer class="main-footer">
    <div class="container main-footer-container">
        <h3 class="band-name">The Corrs</h3>
        <ul class="nav footer-nav">
            <li>
                <a href="https://youtube.com" target="_blank">
                    <img src="../../pub/Images/YouTubeLogo.jpeg" style="height: 35px" />
                </a>
            </li>
            <li>
                <a href="https://Spotify.com" target="_blank">
                    <img src="../../pub/Images/SpotityLogo.png" style="height: 45px" />
                </a>
            </li>
            <li>
                <a href="https://facebook.com" target="_blank">
                    <img src="../../pub/Images/FBlogo.png" style="height: 35px" />
                </a>
            </li>
        </ul>
    </div>
</footer>
<%--<script src="store.js" async></script>--%>
</body>
</html>

