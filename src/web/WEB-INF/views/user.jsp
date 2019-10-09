<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Назар
  Date: 06.10.2019
  Time: 20:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row col-md-12 custyle">
        <table class="table table-striped custab">
            <thead>
            <form action="/filter_by_date" method="post">
                Date:<br>
                <%--                <input type="datetime-local" name = "currentDate">--%>
                <input required type="date" name="currentDate" value= ${currentDate}>
                <input type="submit" value="Filter">
            </form>
            <a href="/item/add" class="btn btn-primary btn-xs pull-right"><b>+</b> Add</a>
            <tr>
                <th class="row col-md-2">Date begin</th>
                <th class="row col-md-2">Date end</th>
                <th class="row col-md-6">Description</th>
                <th class="text-center row col-md-2">Action</th>
            </tr>
            </thead>
            <c:forEach items="${toDoList}" var="item">
                <tr>
                    <td>${item.getDateBegin()}</td>
                    <td>${item.getDateEnd()}</td>
                    <td>${item.getDescription()}</td>
                    <td class="text-center"><a id = "edit" class='btn btn-info btn-xs' href="#"><span
                            class="glyphicon glyphicon-edit"></span> Edit</a> <a href="/item/delete/${item.getId()}" class="btn btn-danger btn-xs"><span
                            class="glyphicon glyphicon-remove"></span> Del</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
