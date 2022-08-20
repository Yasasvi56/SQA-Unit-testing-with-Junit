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
				<c:if test="${payment != null}">
					<form action="<%=request.getContextPath()%>/payment?action=update" method="post">
				</c:if>
				<c:if test="${payment == null}">
					<form action="<%=request.getContextPath()%>/payment?action=insert" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${payment != null}">
            			Edit Payment
            		</c:if>
						<c:if test="${payment == null}">
            			Add New Payment
            		</c:if>
					</h2>
				</caption>

				<c:if test="${payment != null}">
					<input type="hidden" name="id" value="<c:out value='${payment.paymentID}' />" />
				</c:if>

				<fieldset class="form-group">
					<label>Student ID</label>
					<select name="studentID" class="form-control">
					    <c:forEach items="${listStudent}" var="student">
					        <c:choose>
								<c:when test="${payment.studentID==student.studentID}">
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
					<label>Reason</label> <input type="text"
						value="<c:out value='${payment.reason}' />" class="form-control"
						name="reason">
				</fieldset>
				
				<fieldset class="form-group">
					<label>Method</label> 
					<select class="form-control" name="method">
						<c:choose>
						    <c:when test="${payment.method=='cash'}">
						        <option value="cash" selected="selected">cash</option>
						    </c:when>    
						    <c:otherwise>
						        <option value="cash" >cash</option>
						    </c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${payment.method=='online'}">
						        <option value="online" selected="selected">online</option>
						    </c:when>    
						    <c:otherwise>
						        <option value="online" >online</option>
						    </c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${payment.method=='visa'}">
						        <option value="visa" selected="selected">visa</option>
						    </c:when>    
						    <c:otherwise>
						        <option value="visa" >visa</option>
						    </c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${payment.method=='other'}">
						        <option value="other" selected="selected">other</option>
						    </c:when>    
						    <c:otherwise>
						        <option value="other" >other</option>
						    </c:otherwise>
						</c:choose>
					</select>
				</fieldset>
				
				<fieldset class="form-group">
					<label>Amount</label> <input type="text"
						value="<c:out value='${payment.amount}' />" class="form-control"
						name="amount">
				</fieldset>

				<button type="submit" class="btn btn-success">Save</button>
				</form>
			</div>
		</div>
	</div>
</body>
</html>