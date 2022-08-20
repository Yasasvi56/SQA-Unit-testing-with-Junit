<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Student Registration Application</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="login-header.jsp"></jsp:include>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<form action="login" method="post">
				<caption>
					<h2>
						Login Screen
					</h2>
				</caption>
				<fieldset class="form-group">
					<label>Username</label> <input type="text" class="form-control"
						name="username" required>
				</fieldset>
				<fieldset class="form-group">
					<label>Password</label> <input type="password" class="form-control"
						name="password" required>
				</fieldset>
				<button type="submit" class="btn btn-success">Login</button>
				</form>
				<c:if test="${errorMessage != null}">
					<label>${errorMessage}</label>
				</c:if>
			</div>
		</div>
	</div>
</body>
</html>