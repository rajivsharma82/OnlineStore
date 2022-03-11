<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <title>Online Store Case Study</title>
<%--        <link rel="stylesheet" href="../../../pub/css/signup.css">--%>
    <link rel="stylesheet" href="../../../pub/css/styles.css">

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
          rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <!-- Font Awesome for icons  -->
    <link  rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">

    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"

            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p"
            crossorigin="anonymous"></script>

</head>
<body>
<header class="main-header">

    <nav class="navbar navbar-expand-lg navbar-dark" >
        <div class="container-fluid">
            <a class="navbar-brand" href="/">Mall Of America</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0 navbar-margin">
                    <li class="nav-item">
                        <a class="nav-link active" aria-current="page" href="/">Home</a>
                    </li>


                    <sec:authorize access="hasAnyAuthority('ADMIN','USER')">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                My Account
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <li><a class="dropdown-item" href="/orderHistory">Orders</a></li>
                                <li><a class="dropdown-item" href="/registration-url-path/editUser">Edit Account</a></li>
                                <li><a class="dropdown-item" href="/admin/userQuery">Query Status</a></li>
                            </ul>


                        </li>
                    </sec:authorize>

                    <sec:authorize access="hasAuthority('ADMIN')">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Admin
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
<%--                            <li><a class="dropdown-item" href="/addproduct">Manage Product</a></li>--%>
                            <li><a class="dropdown-item" href="/admin/manageProduct">Manage Product</a></li>
                            <li><hr class="dropdown-divider"></li>
                            <li><a class="dropdown-item" href="/registration-url-path/manageUser">Manage Users</a></li>
                            <li><a class="dropdown-item" href="/manageOrderHistory">Manage Orders</a></li>
                            <li><a class="dropdown-item" href="/admin/manageUserQuery">Manage Queries</a></li>
                        </ul>
                    </li>
                    </sec:authorize>

                    <sec:authorize access="hasAnyAuthority('ADMIN','USER')">
                    <li class="nav-item">
                        <a class="nav-link" href="/goToCart">Cart</a>
                    </li>
                    </sec:authorize>

                    <li class="nav-item">
                        <a class="nav-link" href="/about">About</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="/admin/contactus">Contact us</a>
                    </li>


                </ul>
                <div style="white-space: nowrap;">

                    <sec:authorize access="isAuthenticated()">
                        <i class="fas fa-user"></i> <sec:authentication property="principal.username" /></a>
                    </sec:authorize>

                    <sec:authorize access="isAuthenticated()">
                        <a class="btn btn-outline-success btn-auth-margin" href="/login/logout">Logout</a>
                    </sec:authorize>

                    <sec:authorize access="!isAuthenticated()">
                        <a class="btn btn-outline-success btn-auth-margin" href="/login/login">Login</a>
                    </sec:authorize>



                </div>

            </div>
        </div>
    </nav>

</header>

<div style="margin: 2rem">
