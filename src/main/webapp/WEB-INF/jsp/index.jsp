<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="include/header.jsp" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <%--    <link rel="stylesheet" href="styles.css" />--%>
<%--    <link rel="stylesheet" href="../../../../pub/css/styles.css">--%>
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
// From the test button click, call a servlet with the information (product name, price and image url)
    // implement the servlet controller to add concetenated data inside a List<String>
    // Also add the list to session variable
    // create a new scriplet beside the cart variable to print the size of the list
    // inside the scriplet i can refer session variable and so the size to get the total

    <%--    $(document).ready(function() {--%>
<%--        $('#cartBtnId').click(function (){--%>
<%--            console.log("inside click JSP")--%>

<%--&lt;%&ndash;            &lt;%&ndash;%>--%>
<%--&lt;%&ndash;                out.print(list.size());&ndash;%&gt;--%>
<%--&lt;%&ndash;          %>&ndash;%&gt;--%>


<%--        });--%>
<%--    });--%>
</script>

<body>


<%--<form method="get" action="/productSearch">--%>
    <table>
        <tr>

            <td>

                <div >

                    <div>
                        <!-- Example single danger button -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                                Category
                            </button>
                            <ul class="dropdown-menu">
<%--                                <li><a class="dropdown-item" href="/productCatSearch?search=BOOKS">BOOKS</a></li>--%>
                                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=BOOKS">BOOKS</a></li>

<%--                                <li><a class="dropdown-item" href="/productCatSearch?search=TOY">TOY</a></li>--%>
                                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=TOY">TOY</a></li>
                                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=MERCH">MERCH</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=GAME">GAME</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </td>

<%--                <form method="get" action="/productSearch">--%>
<%--                    /showProducts/page?currentPage=1&category=BOOKS--%>
                <form method="get" action="/showProducts/page">
                    <td>
                        <input type="text" name="search" style=" width: 200px ;margin-left: 300px"
                               placeholder="Search Product" value="${searchKey}">

                        <input type="hidden" name="currentPage" value="1" />

                    </td>
                    <td>
                        <button type="submit">Search</button>
                    </td>

                </form>

            <td>

<%--                Cart1:--%>
                <div>
<%--                    <i style="font-size:30px; margin-left: 15rem" class="fa">&#xf07a;</i>--%>
                    <a class="nav-link" href="/goToCart">
                        <i style="font-size:30px; margin-left: 10rem" class="fa">&#xf07a;</i>
                    </a>
                </div>
<%--                <span class="badge">${cartSize}</span>--%>
            </td>

            <td>
<%--                <%!--%>

<%--                    List<String> list = new ArrayList();--%>
<%--                    public List<String> updateCart(String name, String image, String price) {--%>
<%--                        String addToCart = name + "|" + image + "|" + price;--%>
<%--                        list.add(addToCart);--%>
<%--                        System.out.println("inside the cart method");--%>
<%--                        return list;--%>
<%--                    }%>--%>

            <%
                if(session.getAttribute("totalOrderQuantity") != null)
                {
                    out.print(session.getAttribute("totalOrderQuantity").toString());
//                    System.out.println("inside the cart info if condition ");

                }

//                out.print("abc");


            %>

<%--                <%!--%>

<%--                    List<String> list = new ArrayList();--%>
<%--                    public List<String> updateCart(String name, String image, String price) {--%>
<%--                        String addToCart = name + "|" + image + "|" + price;--%>
<%--                        list.add(addToCart);--%>
<%--                        System.out.println("inside the cart method");--%>
<%--                        return list;--%>
<%--                    }%>--%>

            </td>



        </tr>
    </table>
<%--</form>--%>



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
<%--                <button class="btn btn-primary shop-item-button" role="button" id = "cartBtnId" onClick="updateCart( ${product.name}, ${product.imageUrl}, ${product.unitPrice} )">--%>

<%--                    <button class="btn btn-primary shop-item-button" role="button" id = "cartBtnId" onclick="testBtnClick(`${product.id}`,`${product.name}`, `${product.imageUrl}`, `${product.unitPrice}`)">--%>
<%--                        <button class="btn btn-primary shop-item-button" role="button" id = "cartBtnId" onclick="testBtnClick(`${product.id}`)">--%>
                            <a class="btn btn-primary shop-item-button"  role="button" href="/addToSessionCart?id=${product.id}&searchKey=${searchKey}">
<%--                <a class="btn btn-primary shop-item-button"  role="button" href="/showProducts/page?currentPage=${currentPage}&category=${searchCategory}&search=${searchKey}">--%>
                            Add To Cart </a>
<%--                    </button>--%>


                <%--                <!--  <td><a href="/registration-url-path/register?id=${productid}"> Edit </a>  </td> -->--%>
                <!-- backend controller - check if that prod exist and check if i have qty >0 and add to order and write to DB -->
                <!-- if user remove from cart then remove from DB ... user, product , order , order_product  -->
                <!-- user link to order and order to products ... if order exist then add product to it else create new order  -->
            </div>
        </div>
        <%--        <!-- </c:forEach> -->--%>
        </c:forEach>
    </div>

    <div>
        Total Items : ${totalItems} - Page ${currentPage} of ${totalPages}

        <span>
<%--        <c:forEach items="${totalPages}" var="page">--%>
<%--            ${page} &nbsp;--%>

<%--        </c:forEach>--%>

               <c:forEach var = "i" begin = "1" end = "${totalPages}">
                   &nbsp;&nbsp;

<%--                   <a href="/showProducts/page?currentPage=${i}&" > <c:out value = "${i}" /> </a>--%>
                   <a href="/showProducts/page?currentPage=${i}&category=${searchCategory}&search=${searchKey}" > <c:out value = "${i}" /> </a>
                </c:forEach>

        </span>

    </div>

<%--    <form method="get" action="/showProduct">--%>
<%--        <button type="submit" name = "previous">previous</button>--%>
<%--        <button type="submit" name = "next">next</button>--%>
<%--    </form>--%>

<%--    <% String prodPage = session.getAttribute("showProductPage").toString();--%>
<%--        out.print("something "+prodPage);--%>
<%--    %>--%>

<%--    <a class="btn btn-primary shop-item-button"  role="button" href="/showProducts?previous=${pageStart}">--%>
<%--        previous </a>--%>
<%--    <a class="btn btn-primary shop-item-button"  role="button" href="/showProducts?next=${pageStart}" >--%>
<%--        next </a>--%>


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

<%--<form action="add" method="post">--%>
<%--https://stackoverflow.com/questions/5401366/how-to-add-these-the-items-in-a-cart-jsp--%>
<%--    <input type="hidden" name="id" value="${id}" />--%>
<%--    <input type="submit" value="Add to cart" />--%>
<%--</form>--%>