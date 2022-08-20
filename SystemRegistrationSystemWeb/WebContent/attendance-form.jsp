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
				<c:if test="${attendance != null}">
					<form action="<%=request.getContextPath()%>/attendance?action=update" method="post">
				</c:if>
				<c:if test="${attendance == null}">
					<form action="<%=request.getContextPath()%>/attendance?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${attendance != null}">
            			Edit Attendance
            		</c:if>
						<c:if test="${attendance == null}">
            			Add New Attendance
            		</c:if>
					</h2>
				</caption>

				<c:if test="${attendance != null}">
					<input type="hidden" name="id" value="<c:out value='${attendance.attendanceID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student</label>
					<select name="studentID" class="form-control">
					    <c:forEach items="${listStudent}" var="student">
					        <c:choose>
								<c:when test="${attendance.studentID==student.studentID}">
							        <option value="${student.studentID}" selected="selected">${student.FName}</option>
							    </c:when>    
							    <c:otherwise>
							        <option value="${student.studentID}">${student.FName}</option>
							    </c:otherwise>
							</c:choose>
					    </c:forEach>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Class</label>
					<select name="classID" class="form-control">
					    <c:forEach items="${listClass}" var="classx">
					        <c:choose>
								<c:when test="${attendance.classID==classx.classID}">
							        <option value="${classx.classID}" selected="selected">${classx.description}</option>
							    </c:when>    
							    <c:otherwise>
							        <option value="${classx.classID}">${classx.description}</option>
							    </c:otherwise>
							</c:choose>
					    </c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Date</label> <input type="date"
						value="<c:out value='${attendance.attendanceDate}' />" class="form-control"
						name="attendancedate">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>