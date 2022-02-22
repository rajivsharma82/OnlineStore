<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="include/header.jsp" />

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

<form method="get" action="/productSearch">
<table>
    <tr>
        <td>
            <input type="text" name="search" style=" width: 200px ;margin-left: 300px"
            placeholder="Search Product">
        </td>
        <td>
            <button type="submit">Search</button>
        </td>
    </tr>
</table>
</form>

<section class="container content-section">
    <h2 class="section-header"> Game Console</h2>
    <div class="shop-items">
        <div class="shop-item">
            <span class="shop-item-title"> APP Playstation</span>
            <img class="shop-item-image" src="../../pub/Images/playstation.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
            </div>
        </div>

        <div class="shop-item">
            <span class="shop-item-title">XYZ Controller</span>
            <img class="shop-item-image" src="../../pub/Images/game_video.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
            </div>
        </div>


        <div class="shop-item">
            <span class="shop-item-title">XBOX</span>
            <img class="shop-item-image" src="../../pub/Images/XBOX.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
            </div>
        </div>
    </div>
</section>

<section class="container content-section">
    <h2 class="section-header">MERCH</h2>
    <div class="shop-items">

<%--        <!-- <c:forEach var="user" items="${userListKey}"> -->--%>
        <div class="shop-item">
            <span class="shop-item-title"> CFE Tshirt</span>
<%--            <!-- <span class="shop-item-title"> ${Prod name from DB}</span>  -->--%>
            <img class="shop-item-image" src="../../pub/Images/tshirt_kid.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
<%--                <!--  <td><a href="/registration-url-path/register?id=${productid}"> Edit </a>  </td> -->--%>
                <!-- backend controller - check if that prod exist and check if i have qty >0 and add to order and write to DB -->
                <!-- if user remove from cart then remove from DB ... user, product , order , order_product  -->
                <!-- user link to order and order to products ... if order exist then add product to it else create new order  -->
            </div>
        </div>
<%--        <!-- </c:forEach> -->--%>

        <div class="shop-item">
            <span class="shop-item-title">Red Brand Shoe</span>
            <img class="shop-item-image" src="../../pub/Images/shoe_women.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
            </div>
        </div>

        <div class="shop-item">
            <span class="shop-item-title">THL Tshirt</span>
            <img class="shop-item-image" src="../../pub/Images/tshirt_men.jpeg" />
            <div class="shop-item-details">
                <span class="shop-item-price">12.99</span>
                <button class="btn btn-primary shop-item-button" role="button">
                    Add To Cart
                </button>
            </div>
        </div>

    </div>
</section>

<section class="container content-section">
    <h2 class="section-header">CART</h2>
    <div class="cart-row">
        <span class="cart-item cart-header cart-column">ITEM</span>
        <span class="cart-price cart-header cart-column">PRICE</span>
        <span class="cart-quantity cart-header cart-column">QUANTITY</span>
    </div>

    <div class="cart-items">
        <!-- <div class="cart-row">
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

          </div> -->
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

