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
			<h3 class="text-center">List of Results</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/result?action=new" class="btn btn-success">Add
					New Result</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Exam</th>
						<th>Student</th>
						<th>Marks</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="result" items="${listResult}">

						<tr>
							<td><c:out value="${result.resultID}" /></td>
							<c:forEach var="exam" items="${listExam}">
								<c:choose>
									<c:when test="${result.examID==exam.examID}">
								        <td><c:out value="${exam.description}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<c:forEach var="student" items="${listStudent}">
								<c:choose>
									<c:when test="${result.studentID==student.studentID}">
								        <td><c:out value="${student.FName}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<td><c:out value="${result.marks}" /></td>
							<td><a href="<%=request.getContextPath()%>/result?action=edit&id=<c:out value='${result.resultID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/result?action=delete&id=<c:out value='${result.resultID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
