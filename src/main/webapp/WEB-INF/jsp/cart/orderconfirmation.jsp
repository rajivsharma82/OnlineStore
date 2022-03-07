<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<h1>Order Confirmation : ${orderTrackingNumber}</h1>


<footer class="main-footer">
    <div class="container main-footer-container">
        <h3 class="band-name">The Online Store</h3>
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

