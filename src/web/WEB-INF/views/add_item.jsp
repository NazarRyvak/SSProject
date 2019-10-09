<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Назар
  Date: 08.10.2019
  Time: 14:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
    <%@include file="/resources/css/mystyle.css" %>
</style>
<html>
<head>
    <title>Title</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
<body>
<div class="sidenav">
    <div class="login-main-text">
        <h2>Application<br> Add item to schedule</h2>
    </div>
</div>
<div class="main">
    <div class="col-md-6 col-sm-12">
        <div class="login-form">
            <form action="/item/add" method="post">
                <div class="form-group">
                    <label>Description</label>
                    <input required type="text" class="form-control" name="description">
                </div>
                <div class="form-group">
                    <p style="color:red">${beginDateIsNotFree}</p>
                    <label>Date begin</label>
                    <input id="date_begin" required type="datetime-local" name="dateBegin" onchange="dateBeginChange()">
                </div>
                <div class="form-group">
                    <p style="color:red">${endDateIsNotFree}</p>
                    <label>Date end</label>
                    <input id="date_end" required type="datetime-local" name="dateEnd" onchange="dateEndChange()">
                   <p style="color:red">${betweenDatesAreNotItem}</p>
                </div>
                <button type="submit" class="btn btn-black">ADD</button>
            </form>
        </div>
    </div>
</div>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<script>
    function dateBeginChange() {
        var dateEnd = document.getElementById("date_end");
        dateEnd.min = document.getElementById("date_begin").value;
    }

    function dateEndChange() {
        var dateBegin = document.getElementById("date_begin");
        dateBegin.max = document.getElementById("date_end").value;
    }
</script>
</body>
</html>
