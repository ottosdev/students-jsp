<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link type="text/css" rel="stylesheet" href="css/style.css">
<title>Students</title>
</head>



<body>

	<div id="wrapper">
		<div id="header">
			<h2>FooBar University</h2>
		</div>
	</div>
	
	<div id="container">
	
	<input class="add-student-button" type="button" value="Add Student" onclick="window.location.href='add-student-form.jsp; return false'"/>
		<table>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>	
				<th>Action</th>			
			</tr>	
			
			<c:forEach var="s" items="${STUDENTS_LIST}">
			<c:url var="link" value="StudentControllerServelet">
			<c:param name="command" value="LOAD"/>
					<c:param name="studentID" value="${s.id }"/>
			</c:url>
			 
			<c:url var="linkDelete" value="StudentControllerServelet">
			<c:param name="command" value="DELETE"/>
					<c:param name="studentID" value="${s.id }"/>
			</c:url>
				<tr>
					<td>${ s.firstName }</td>
					<td>${ s.lastname }</td>
					<td>${ s.email }</td>
					<td><a href="${link}">Update</a>
					|
					<a href="${linkDelete}" onclick="if(!(confirm('Are u sure you want to delete this?'))) return false">Delete</a>
					</td>
					
				</tr>	
		</c:forEach>
		</table>
	</div>

</body>
</html>