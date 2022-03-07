<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<jsp:include page="../include/header.jsp" />
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <style>
        body {font-family: Arial, Helvetica, sans-serif;}
        * {box-sizing: border-box;}

        input[type=text], select, textarea {
            width: 100%;
            padding: 12px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
            margin-top: 6px;
            margin-bottom: 16px;
            resize: vertical;
        }

        input[type=submit] {
            background-color: #04AA6D;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type=submit]:hover {
            background-color: #45a049;
        }

        .container {
            border-radius: 5px;
            background-color: #f2f2f2;
            padding: 20px;
        }
    </style>
</head>
<body>

<h3>Contact Form</h3>

<div class="container">
    <form action="/">
        <label for="fname">First Name</label>
        <input type="text" id="fname" name="firstname" placeholder="Your name..">

        <label for="lname">Last Name</label>
        <input type="text" id="lname" name="lastname" placeholder="Your last name..">

        <label for="phone">Contact Number</label>
        <input type="text" id="phone" name="phone" placeholder="Your contact number">

        <label for="email">Email</label>
        <input type="text" id="email" name="email" placeholder="Your email">

        <label for="country">Country</label>
        <select id="country" name="country">
            <option value="usa">USA</option>
            <option value="canada">Canada</option>
        </select>

        <label for="subject">Subject</label>
        <textarea id="subject" name="subject" placeholder="I have a query on the last order I made last week, could you please reach out to me asap. " style="height:200px"></textarea>

<%--        <input type="submit" value="Submit">--%>
        <button type="submit" href="/index"   class="btn btn-primary contact" role="button" onclick="contactusfun()">Submit</button>
    </form>
</div>

<script>
    document.getElementsByClassName("edituser").addEventListener("onclick", contactusfun);

    function contactusfun() {
        alert("Thank you for your feedback. We would get back to you shortly");
    }
</script>

</body>
</html>

