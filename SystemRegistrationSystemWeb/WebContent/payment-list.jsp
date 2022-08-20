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
			<h3 class="text-center">List of Payments</h3>
			<hr>
			<div class="container text-left">

				<a href="<%=request.getContextPath()%>/payment?action=new" class="btn btn-success">Add
					New Payment</a>
			</div>
			<br>
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>ID</th>
						<th>Student</th>
						<th>Reason</th>
						<th>Method</th>
						<th>Amount</th>
					</tr>
				</thead>
				<tbody>
					<!--   for (Todo todo: todos) {  -->
					<c:forEach var="payment" items="${listPayment}">

						<tr>
							<td><c:out value="${payment.paymentID}" /></td>
							<c:forEach var="student" items="${listStudent}">
								<c:choose>
									<c:when test="${student.studentID==payment.studentID}">
								        <td><c:out value="${student.FName}" /></td>
								    </c:when>
								</c:choose>
							</c:forEach>
							<td><c:out value="${payment.reason}" /></td>
							<td><c:out value="${payment.method}" /></td>
							<td><c:out value="${payment.amount}" /></td>
							<td><a href="<%=request.getContextPath()%>/payment?action=edit&id=<c:out value='${payment.paymentID}' />">Edit</a>
								&nbsp;&nbsp;&nbsp;&nbsp; <a
								href="<%=request.getContextPath()%>/payment?action=delete&id=<c:out value='${payment.paymentID}' />">Delete</a></td>
						</tr>
					</c:forEach>
					<!-- } -->
				</tbody>

			</table>
		</div>
	</div>
</body>
</html>
