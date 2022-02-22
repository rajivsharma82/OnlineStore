<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />


<!-- Form  -->
<form id="form"  method="GET" action="/addProductSubmit"><br>

    <table cellpadding="5">
        <tr>
            <td>Product Category:</td>
            <td> <input type="text" name ="productCategory" value="${ProductFormBeanKey.productCategory}" required ></td>
        </tr>
        <tr>
            <td>ProdSKU:</td>
            <td> <input type="text" name ="sku" value="${ProductFormBeanKey.sku}" required></td>
        </tr>

        <tr>
            <td>Product Name:</td>
            <td> <input type="text" name ="name" value="${ProductFormBeanKey.name}" required></td>
        </tr>
        <tr>
            <td>Product Description:</td>
            <td> <input type="text" name ="description" value="${ProductFormBeanKey.description}" required></td>
        </tr>
        <tr>
            <td>Unit Price:</td>
            <td> <input type="number" name ="unitPrice" value="${ProductFormBeanKey.unitPrice}" required ></td>
        </tr>
        <tr>
            <td>Image Url:</td>
            <td> <input type="text" name ="imageUrl" value="${ProductFormBeanKey.imageUrl}" required></td>
        </tr>
        <tr>
            <td>Active:</td>
            <td>  <input type="checkbox" name="active"  checked value="${ProductFormBeanKey.active}" ></td>
        </tr>
        <tr>
            <td>Units In Stock:</td>
            <td> <input type="number" name ="unitsInStock" value="${ProductFormBeanKey.unitsInStock}" required></td>
        </tr>



    </table>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />