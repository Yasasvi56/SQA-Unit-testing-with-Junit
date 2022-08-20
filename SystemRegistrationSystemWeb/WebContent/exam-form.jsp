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
				<c:if test="${exam != null}">
					<form action="<%=request.getContextPath()%>/exam?action=update" method="post">
				</c:if>
				<c:if test="${exam == null}">
					<form action="<%=request.getContextPath()%>/exam?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${exam != null}">
            			Edit Exam
            		</c:if>
						<c:if test="${exam == null}">
            			Add New Exam
            		</c:if>
					</h2>
				</caption>

				<c:if test="${exam != null}">
					<input type="hidden" name="id" value="<c:out value='${exam.examID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Subject</label>
					<select name="subjectID" class="form-control">
					    <c:forEach items="${listSubject}" var="subject">
					        <c:choose>
								<c:when test="${exam.subjectID==subject.subjectID}">
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
					<label>Description</label> <input type="text"
						value="<c:out value='${exam.description}' />" class="form-control"
						name="description">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Exam Date</label> <input type="date"
						value="<c:out value='${exam.examDate}' />" class="form-control"
						name="examDate">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Time</label> <input type="text"
						value="<c:out value='${exam.time}' />" class="form-control"
						name="time">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Location</label> <input type="text"
						value="<c:out value='${exam.location}' />" class="form-control"
						name="location">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>