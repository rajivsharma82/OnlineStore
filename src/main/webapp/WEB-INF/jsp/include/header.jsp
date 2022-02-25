<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Online Store Case Study</title>
<%--        <link rel="stylesheet" href="../../../pub/css/signup.css">--%>
    <link rel="stylesheet" href="../../../pub/css/styles.css">

    <!-- Font Awesome for icons  -->
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

<%--    <link rel="stylesheet" href="../css/styles.css" />--%>
<%--    <script--%>
<%--            src="https://code.jquery.com/jquery-3.6.0.js"--%>
<%--            integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="--%>
<%--            crossorigin="anonymous"></script>--%>

<%--    <!-- Bootstrap CSS -->--%>
<%--    <link rel="stylesheet"--%>
<%--          href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"--%>
<%--          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"--%>
<%--          crossorigin="anonymous">--%>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"

            crossorigin="anonymous"></script>


<%--    <script--%>
<%--            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"--%>
<%--            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"--%>
<%--            crossorigin="anonymous"></script>--%>
<%--    <script--%>
<%--            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"--%>
<%--            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"--%>
<%--            crossorigin="anonymous"></script>--%>




</head>
<body>
<header class="main-header">
    <nav class="main-nav nav">
        <ul>
            <li><a href=""> HOME</a></li>
            <li><a href="/"> STORE</a></li>
            <li><a href="about.html"> ABOUT</a></li>
            <li><a href="/goToCart"> Cart</a></li>



            <sec:authorize access="!isAuthenticated()">
                <i class="fas fa-user"></i> My Account</a>
            </sec:authorize>

            <sec:authorize access="isAuthenticated()">
                <i class="fas fa-user"></i> <sec:authentication property="principal.username" /></a>
            </sec:authorize>
        </ul>
    </nav>

    <h1 class="band-name band-name-large">The Online Store</h1>
</header>

<!-- implement any html you need to show on every page as a header here -->
<table>


    <tr>
        <sec:authorize access="isAuthenticated()">
        <td><a href="/login/logout">Logout</a></td>
        </sec:authorize>
        <td> | </td>
        <td><a href="/registration-url-path/register">User Registration</a></td>
        <td> | </td>
        <td><a href="/registration-url-path/userSearch">Search User</a></td>
        <td> | </td>
        <td><a href="/login/login">Login</a></td>
        <td> | </td>
        <td><a href="/addproduct">Add Product</a></td>


<%--        <td> | </td>--%>
<%--        <td><a href="/registration-url-path/AdvancedSearchuserList">Advanced Search</a></td>--%>
    </tr>

</table>
<hr>
<div class="container">