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
			<h3 class="text-center">List of Classes</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/class?action=new" class="btn btn-success">Add
					New Class</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Subject</th>
						<th>Teacher</th>
						<th>Description</th>
						<th>Schedule</th>
						<th>Time</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="classx" items="${listClass}">

						<tr>
							<td><c:out value="${classx.classID}" /></td>
							<c:forEach var="subject" items="${listSubject}">
								<c:choose>
									<c:when test="${subject.subjectID==classx.subjectID}">
								        <td><c:out value="${subject.subjectName}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<c:forEach var="teacher" items="${listTeacher}">
								<c:choose>
									<c:when test="${teacher.teacherID==classx.teacherID}">
								        <td><c:out value="${teacher.name}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<td><c:out value="${classx.description}" /></td>
							<td><c:out value="${classx.schedule}" /></td>
							<td><c:out value="${classx.time}" /></td>
							<td><a href="<%=request.getContextPath()%>/class?action=edit&id=<c:out value='${classx.classID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/class?action=delete&id=<c:out value='${classx.classID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
