<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../include/header.jsp" />


<!-- Form  -->
<form id="form"  method="GET" action="/addProduct"><br>

    <table cellpadding="5">
        <tr>
            <td>ProdSKU:</td>
            <td> <input type="text" name ="ProdSKU" ></td>
        </tr>

        <tr>
            <td>Product Name:</td>
            <td> <input type="text" name ="email" ></td>
        </tr>
        <tr>
            <td>FirstName:</td>
            <td> <input type="text" name ="firstName" ></td>
        </tr>
        <tr>
            <td>LastName:</td>
            <td> <input type="text" name ="lastName"  ></td>
        </tr>
        <tr>
            <td>Age:</td>
            <td> <input type="text" name ="age" ></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td> <input type="password" name ="password"  ></td>
        </tr>
        <tr>
            <td>Confirm Password:</td>
            <td> <input type="password" name ="confirmPassword" ></td>
        </tr>



    </table>
    <button type="submit">Submit</button>
</form>

<jsp:include page="../include/footer.jsp" />