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
				<c:if test="${teacher != null}">
					<form action="<%=request.getContextPath()%>/teacher?action=update" method="post">
				</c:if>
				<c:if test="${teacher == null}">
					<form action="<%=request.getContextPath()%>/teacher?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${teacher != null}">
            			Edit Teacher
            		</c:if>
						<c:if test="${teacher == null}">
            			Add New Teacher
            		</c:if>
					</h2>
				</caption>

				<c:if test="${teacher != null}">
					<input type="hidden" name="id" value="<c:out value='${teacher.teacherID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Name</label> <input type="text"
						value="<c:out value='${teacher.name}' />" class="form-control"
						name="name" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Qualifications</label> <input type="text"
						value="<c:out value='${teacher.qualification}' />" class="form-control"
						name="qualifications">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
