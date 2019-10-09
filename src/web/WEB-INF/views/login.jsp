<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Назар
  Date: 06.10.2019
  Time: 13:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style><%@include file="/resources/css/mystyle.css"%></style>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Application<br> Login Page</h2>
        <p>Login from here to access.</p>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="/login" method="post">
                <div class="form-group">
                    <p style="color:red;" >${failed}</p>
                    <label>User Name</label>
                    <input type="text" class="form-control" name="user_name">
                </div>
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password">
                </div>
                <button type="submit" class="btn btn-black">Login</button>
                <p>
                    New Member? <a href="register" class="">Sign up</a>
                </p>
            </form>
        </div>
    </div>
</div>

</body>
</html>
