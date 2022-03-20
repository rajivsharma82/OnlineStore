<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />

<div style="margin-top: 2rem ; margin-left: 18rem; ">
<!-- Form  -->
<form id="form"  method="GET" action="/admin/addProductSubmit"><br>

    <table cellpadding="5" style="margin-top: 2rem">
        <tr>
            <td><label for ="productCategory" class="form-label fs-4" > Product Category: </label></td>
            <td> <input type="text" class="form-control-lg" name ="productCategory" id="productCategory"  value="${ProductFormBeanKey.productCategory}" required ></td>
        </tr>
        <tr>
            <td><label for ="sku" class="form-label fs-4" > ProdSKU: </label></td>
            <td> <input type="text" name ="sku" class="form-control-lg" id="sku" value="${ProductFormBeanKey.sku}" required></td>
        </tr>

        <tr>
            <td><label for ="name" class="form-label fs-4" > Product Name: </label></td>
            <td> <input type="text" name ="name" class="form-control-lg" id="name" value="${ProductFormBeanKey.name}" required></td>
        </tr>
        <tr>
            <td><label for ="description" class="form-label fs-4" > Product Description: </label></td>
            <td> <input type="text" name ="description" class="form-control-lg" id="description" value="${ProductFormBeanKey.description}" required></td>
        </tr>
        <tr>
            <td><label for ="unitPrice" class="form-label fs-4" > Unit Price: </label></td>
            <td> <input type="number" name ="unitPrice" class="form-control-lg" id="unitPrice" value="${ProductFormBeanKey.unitPrice}"
                        placeholder="1.00" step="0.01" min="0" max="1000" required ></td>
        </tr>
        <tr>
            <td><label for ="imageUrl" class="form-label fs-4" > Image Url: </label></td>
            <td> <input type="text" name ="imageUrl" class="form-control-lg" id="imageUrl" value="${ProductFormBeanKey.imageUrl}" required></td>
        </tr>
        <tr>
            <td><label for ="active" class="form-label fs-4" > Active: </label></td>
            <td>  <input type="checkbox" name="active"  checked class="form-control-lg" id="active" value="${ProductFormBeanKey.active}" ></td>
        </tr>
        <tr>
            <td><label for ="unitsInStock" class="form-label fs-4" > Units In Stock: </label></td>
            <td> <input type="number" name ="unitsInStock" class="form-control-lg" id="unitsInStock" value="${ProductFormBeanKey.unitsInStock}" required></td>
        </tr>



    </table>
    <button type="submit" class="btn btn-primary addproduct" onclick="addProdfun()">Submit</button>
<%--    <button type="submit" class="btn btn-primary edituser" role="button" onclick="edituserfun()">Submit</button>--%>

</form>

</div>

<script>
    document.getElementsByClassName("addproduct").addEventListener("onclick", addProdfun);

    function addProdfun() {
        alert("The Product is added into the system");
    }
</script>

<jsp:include page="../include/footer.jsp" />