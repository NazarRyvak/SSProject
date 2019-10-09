<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/fontawesome.min.css">
<link rel="stylesheet" href="/resources/css/style.css">
<link rel="stylesheet" href="/resources/css/table.css">

<link href='https://use.fontawesome.com/releases/v5.0.6/css/all.css'
	rel='stylesheet' />


</head>
<body>
	<%--<div class="containerForMySite">
		<div class="divAboveHeader">
			<div class="containerForUser">
&lt;%&ndash;
				<sec:authorize access="!isAuthenticated()">
					<div class="login">
						<form action="/user/login" method="get">
							<button type="submit" class="btn btn-danger btn-md">LOG
								IN</button>
						</form>
					</div>
					<div class="registration">
						<form action="/user/register" method="get">
							<button type="submit" class="btn btn-danger btn-md">REGISTRY</button>
						</form>
					</div>
				</sec:authorize>

				<sec:authorize access="isAuthenticated()">
					<sec:authentication property="principal.username" var="name" />
					<div class="logout">
						<form:form action="/logout" method="post">
							<button type="submit" class="btn btn-danger btn-md">LOG
								OUT</button>
						</form:form>
					</div>
					<div class="profile">
						<h5>Welcome, ${ name }</h5>
						<sec:authorize access="hasRole('ADMIN')">
							<p>
								<a href="/admin/profile">My Profile</a>
							</p>
						</sec:authorize>
						<sec:authorize access="hasRole('USER')">
							<p>
								<a href="/user/profile/${ name }">My Profile</a>
							</p>
						</sec:authorize>

					</div>
				</sec:authorize>&ndash;%&gt;


			</div>
		</div>
		<tiles:insertAttribute name="header" />
		<div class="container-fluid text-center">
			<div class="row content">
				<div class="sidebar">
					<tiles:insertAttribute name="sidebar" />
				</div>
				<div class="body">
					<tiles:insertAttribute name="body" />
				</div>
			</div>
		</div>
		<tiles:insertAttribute name="footer" />
	</div>--%>
</body>
</html>