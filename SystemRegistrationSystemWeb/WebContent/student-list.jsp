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

	<jsp:include page="header.jsp"></jsp:include>
	<br>

	<div class="row">
		<!-- <div class="alert alert-success" *ngIf='message'>{{message}}</div> -->

		<div class="container">
			<h3 class="text-center">List of Students</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/student?action=new" class="btn btn-success">Add
					New Student</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>FName</th>
						<th>LName</th>
						<th>Age</th>
						<th>DOB</th>
						<th>Phone Number</th>
						<th>Email</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="student" items="${listStudent}">

						<tr>
							<td><c:out value="${student.studentID}" /></td>
							<td><c:out value="${student.FName}" /></td>
							<td><c:out value="${student.LName}" /></td>
							<td><c:out value="${student.age}" /></td>
							<td><c:out value="${student.DOB}" /></td>
							<td><c:out value="${student.phoneNo}" /></td>
							<td><c:out value="${student.email}" /></td>
							<td><a href="<%=request.getContextPath()%>/student?action=edit&id=<c:out value='${student.studentID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/student?action=delete&id=<c:out value='${student.studentID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
