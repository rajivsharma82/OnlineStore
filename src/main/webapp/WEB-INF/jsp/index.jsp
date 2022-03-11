<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="include/header.jsp" />


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

<div class="row align-items-center">
    <div class="col-2">
        <div class="btn-group">
            <button type="button" class="btn btn-info dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                Category
            </button>
            <ul class="dropdown-menu">
                <%--                                <li><a class="dropdown-item" href="/productCatSearch?search=BOOKS">BOOKS</a></li>--%>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=BOOKS">BOOKS</a></li>
                <%--                                <li><a class="dropdown-item" href="/productCatSearch?search=TOY">TOY</a></li>--%>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=TOY">TOY</a></li>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=ALBUM">ALBUM</a></li>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=MOVIE">MOVIE</a></li>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=MERCH">MERCH</a></li>
                <li><hr class="dropdown-divider"></li>
                <li><a class="dropdown-item" href="/showProducts/page?currentPage=1&category=GAME">GAME</a></li>
            </ul>
        </div>
    </div>

    <div class="col-6">
        <form method="get" action="/showProducts/page">

            <div class="input-group mb-3">
                <input type="text"  class="form-control" name="search"
                       placeholder="Search Product" value="${searchKey}">
                <input type="hidden" name="currentPage" value="1" />
                <button type="submit" class="btn btn-lg btn-info">Search</button>
            </div>

             </form>
    </div>
    <div class="col-4" >
        <div>
            <a class="nav-link fs-4" href="/goToCart" style="color: darkslategray ">
                <i style="font-size:30px; margin-left: 10rem" class="fa">&#xf07a;</i>
                <%
                    if(session.getAttribute("totalOrderQuantity") != null)
                    {
                        out.print(session.getAttribute("totalOrderQuantity").toString());
                    }
                %>
            </a>
        </div>
    </div>
</div>

<section class="container content-section">
    <div class="shop-items">
        <c:forEach items="${productSearchList}" var="product">
        <%--        <!-- <c:forEach var="user" items="${userListKey}"> -->--%>
        <div class="shop-item align-items-center">
            <span class="shop-item-title"> ${product.name}</span>
            <%--            <!-- <span class="shop-item-title"> ${Prod name from DB}</span>  -->--%>
            <img class="shop-item-image" src="${product.imageUrl}" />
            <div class="shop-item-details">
                <span class="shop-item-price text-center" >${product.unitPrice}</span>
                <br>
                           <a class="btn btn-primary shop-item-button"  role="button" href="/addToSessionCart?id=${product.id}&searchKey=${searchKey}">
                            Add To Cart </a>
            </div>
        </div>
        <%--        <!-- </c:forEach> -->--%>
        </c:forEach>
    </div>

    <div class="fs-3" style="margin-left: 20rem">
        Total Items : ${totalItems} - Page ${currentPage} of ${totalPages}

        <span >
               <c:forEach var = "i" begin = "1" end = "${totalPages}">
                      <a href="/showProducts/page?currentPage=${i}&category=${searchCategory}&search=${searchKey}" style="color: cornflowerblue; font-size: 25px"> <c:out value = "${i}" /> </a>
                </c:forEach>
        </span>

    </div>
</section>
<jsp:include page="include/footer.jsp" />