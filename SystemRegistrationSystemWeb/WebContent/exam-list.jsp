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
			<h3 class="text-center">List of Exams</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/exam?action=new" class="btn btn-success">Add
					New Exam</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Subject</th>
						<th>Description</th>
						<th>Exam Date</th>
						<th>Time</th>
						<th>Location</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="exam" items="${listExam}">

						<tr>
							<td><c:out value="${exam.examID}" /></td>
							<c:forEach var="subject" items="${listSubject}">
								<c:choose>
									<c:when test="${subject.subjectID==exam.subjectID}">
								        <td><c:out value="${subject.subjectName}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<td><c:out value="${exam.description}" /></td>
							<td><c:out value="${exam.examDate}" /></td>
							<td><c:out value="${exam.time}" /></td>
							<td><c:out value="${exam.location}" /></td>
							<td><a href="<%=request.getContextPath()%>/exam?action=edit&id=<c:out value='${exam.examID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/exam?action=delete&id=<c:out value='${exam.examID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
