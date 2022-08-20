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
				<c:if test="${classx != null}">
					<form action="<%=request.getContextPath()%>/class?action=update" method="post">
				</c:if>
				<c:if test="${classx == null}">
					<form action="<%=request.getContextPath()%>/class?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${classx != null}">
            			Edit Class
            		</c:if>
						<c:if test="${classx == null}">
            			Add New Class
            		</c:if>
					</h2>
				</caption>

				<c:if test="${classx != null}">
					<input type="hidden" name="id" value="<c:out value='${classx.classID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Subject</label> 
					<select name="subjectID" class="form-control" required="required">
					    <c:forEach items="${listSubject}" var="subject">
					        <c:choose>
								<c:when test="${classx.subjectID==subject.subjectID}">
							        <option value="${subject.subjectID}" selected="selected">${subject.subjectName}</option>
							    </c:when>    
							    <c:otherwise>
							        <option value="${subject.subjectID}">${subject.subjectName}</option>
							    </c:otherwise>
							</c:choose>
					    </c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Teacher</label>
					<select name="teacherID" class="form-control" required="required">
					    <c:forEach items="${listTeacher}" var="teacher">
					        <c:choose>
								<c:when test="${classx.teacherID==teacher.teacherID}">
							        <option value="${teacher.teacherID}" selected="selected">${teacher.name}</option>
							    </c:when>    
							    <c:otherwise>
							        <option value="${teacher.teacherID}">${teacher.name}</option>
							    </c:otherwise>
							</c:choose>
					    </c:forEach>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Description</label> <input type="text"
						value="<c:out value='${classx.description}' />" class="form-control"
						name="description">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Schedule</label> <input type="text"
						value="<c:out value='${classx.schedule}' />" class="form-control"
						name="schedule">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Time</label> <input type="text"
						value="<c:out value='${classx.time}' />" class="form-control"
						name="time">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>
