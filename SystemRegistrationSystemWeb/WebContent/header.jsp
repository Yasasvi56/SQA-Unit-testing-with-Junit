<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark"
			style="background-color: #2874A6">
			<div>
				<a href="<%=request.getContextPath()%>/" class="navbar-brand"> Student Registration App </a>
			</div>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/attendance"
					class="nav-link">Attendance</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/class"
					class="nav-link">Classes</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/exam"
					class="nav-link">Exams</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/payment"
					class="nav-link">Payments</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/result"
					class="nav-link">Results</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/student"
					class="nav-link">Students</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/subject"
					class="nav-link">Subjects</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/teacher"
					class="nav-link">Teachers</a></li>
			</ul>
			<ul class="navbar-nav">
				<li><a href="/SystemRegistrationSystemWeb/list"
					class="nav-link">Users</a></li>
			</ul>
		</nav>
	</header>
</body>
</html>