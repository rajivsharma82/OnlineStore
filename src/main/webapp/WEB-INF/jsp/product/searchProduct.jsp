<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>The Online | Store</title>
</head>

<script type="text/javascript">
    function testBtnClick(val1){
        console.log("inside test button click");
        console.log(val1 );


        $.ajax({
            type: "GET",
            url: "/addToSessionCart",
            data: "id=" + val1,
            // data: "name=" + val1 + "&imageUrl="+ val2 + "&price=" + val3,
            success: function(msg){
                // window.location.href= "addproduct";
                 window.location.href= "/productSearch?search=${searchKey}";
            }
        });

    }

</script>

<body>

<form method="get" action="/productSearch">
    <table>
        <tr>
            <td>
                <input type="text" name="search" style=" width: 200px ;margin-left: 300px"
                       placeholder="Search Product" value="${searchKey}">
            </td>
            <td>
                <button type="submit">Search</button>
            </td>
            <td>
                Cart:
<%--                <span class="badge">${cartSize}</span>--%>
            </td>

            <td>
            <%
                if(session.getAttribute("totalOrderQuantity") != null)
                {
                    out.print(session.getAttribute("totalOrderQuantity").toString());
                }
            %>
            </td>
        </tr>
    </table>
</form>



<section class="container content-section">
    <h2 class="section-header">MERCH</h2>

    <div class="shop-items">
        <c:forEach items="${productSearchList}" var="product">
        <%--        <!-- <c:forEach var="user" items="${userListKey}"> -->--%>
        <div class="shop-item">
            <span class="shop-item-title"> ${product.name}</span>
            <%--            <!-- <span class="shop-item-title"> ${Prod name from DB}</span>  -->--%>
            <img class="shop-item-image" src="${product.imageUrl}" />
            <div class="shop-item-details">
                <span class="shop-item-price">${product.unitPrice}</span>
                            <a class="btn  btn-primary btn-sm "  role="button" href="/addToSessionCart?id=${product.id}&searchKey=${searchKey}">
                            Add To Cart </a>
            </div>
        </div>
        </c:forEach>
    </div>


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
</body>
</html>
