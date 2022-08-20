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
				<c:if test="${result != null}">
					<form action="<%=request.getContextPath()%>/result?action=update" method="post">
				</c:if>
				<c:if test="${result == null}">
					<form action="<%=request.getContextPath()%>/result?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${result != null}">
            			Edit Result
            		</c:if>
						<c:if test="${result == null}">
            			Add New Result
            		</c:if>
					</h2>
				</caption>

				<c:if test="${result != null}">
					<input type="hidden" name="id" value="<c:out value='${result.resultID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Exam</label>
					<select name="examID" class="form-control">
					    <c:forEach items="${listExam}" var="exam">
					        <c:choose>
								<c:when test="${result.examID==exam.examID}">
							        <option value="${exam.examID}" selected="selected">${exam.description}</option>
							    </c:when>    
							    <c:otherwise>
							        <option value="${exam.examID}">${exam.description}</option>
							    </c:otherwise>
							</c:choose>
					    </c:forEach>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Student</label>
					<select name="studentID" class="form-control">
					    <c:forEach items="${listStudent}" var="student">
					        <c:choose>
								<c:when test="${result.studentID==student.studentID}">
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
					<label>Marks</label> <input type="text"
						value="<c:out value='${result.marks}' />" class="form-control"
						name="marks">
				</fieldset>
				
				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>