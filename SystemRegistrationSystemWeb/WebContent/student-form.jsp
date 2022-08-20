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
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${student != null}">
					<form action="<%=request.getContextPath()%>/student?action=update" method="post">
				</c:if>
				<c:if test="${student == null}">
					<form action="<%=request.getContextPath()%>/student?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${student != null}">
            			Edit Student
            		</c:if>
						<c:if test="${student == null}">
            			Add New Student
            		</c:if>
					</h2>
				</caption>

				<c:if test="${student != null}">
					<input type="hidden" name="id" value="<c:out value='${student.studentID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>First Name</label> <input type="text"
						value="<c:out value='${student.FName}' />" class="form-control"
						name="fname" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Last Name</label> <input type="text"
						value="<c:out value='${student.LName}' />" class="form-control"
						name="lname">
				</fieldset>

				<fieldset class="form-group">
					<label>Age</label> <input type="text"
						value="<c:out value='${student.age}' />" class="form-control"
						name="age">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Date Of Birth</label> <input type="date"
						value="<c:out value='${student.DOB}' />" class="form-control"
						name="dob">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Phone Number</label> <input type="text"
						value="<c:out value='${student.phoneNo}' />" class="form-control"
						name="phonenumber">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Email</label> <input type="text"
						value="<c:out value='${student.email}' />" class="form-control"
						name="email">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
