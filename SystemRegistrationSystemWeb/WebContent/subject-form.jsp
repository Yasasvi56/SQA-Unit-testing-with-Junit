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
				<c:if test="${subject != null}">
					<form action="<%=request.getContextPath()%>/subject?action=update" method="post">
				</c:if>
				<c:if test="${subject == null}">
					<form action="<%=request.getContextPath()%>/subject?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${subject != null}">
            			Edit Subject
            		</c:if>
						<c:if test="${subject == null}">
            			Add New Subject
            		</c:if>
					</h2>
				</caption>

				<c:if test="${subject != null}">
					<input type="hidden" name="id" value="<c:out value='${subject.subjectID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Subject Code</label> <input type="text"
						value="<c:out value='${subject.subjectCode}' />" class="form-control"
						name="subjectCode" required="required">
				</fieldset>

				<fieldset class="form-group">
					<label>Subject Name</label> <input type="text"
						value="<c:out value='${subject.subjectName}' />" class="form-control"
						name="subjectName">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
