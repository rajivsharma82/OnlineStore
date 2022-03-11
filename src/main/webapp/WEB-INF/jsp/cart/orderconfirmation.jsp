<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />

<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>
<body>

<h2>Thank you for your Order</h2>
<br>
<h4>Order Confirmation : ${orderTrackingNumber}</h4>
<p>Go back to Home Page <a href="/">Home Page</a> </p>

<jsp:include page="../include/footer.jsp" />
