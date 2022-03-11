<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<div style="margin-left: 3rem; margin-right: 3rem; alignment: center">


<form >
    <table style="margin-top: 2rem">
        <tr>
            <td>
                <a class="btn btn-primary btn-sm"  role="button"
                   href="/admin/addProduct">Add New Product</a>
            </td>
        </tr>
    </table>


</form>

<table class="table table-striped" style="margin-top: 4rem">
    <thead>
    <tr>
        <th scope="col">#</th>
        <th scope="col">Product Name</th>
        <th scope="col">SKU</th>
        <th scope="col">Unit Price</th>
        <th scope="col">Unit In Stock</th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>

    <c:forEach items="${productList}" var="product">
        <tr>
            <td>${product.id}</td>

            <td>${product.name}</td>
            <td>${product.category.categoryName}</td>

            <td>${product.unitPrice}</td>
            <td>${product.unitsInStock}</td>

            <td><a class="btn btn-primary btn-sm"  role="button" href="/admin/editProduct?id=${product.id}">Edit</a></td>
            <td><a class="btn btn-danger btn-sm deleteProduct" role="button" onclick="deleteProductFun()" href="/admin/deleteProduct?id=${product.id}">Delete</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

</div>

<script>
    document.getElementsByClassName("deleteProduct").addEventListener("onclick", deleteProductFun);

    function deleteProductFun() {
        alert("Please button to confirm product deletion from the system");
    }
</script>

<jsp:include page="../include/footer.jsp" />