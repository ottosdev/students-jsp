<%@page import="com.otto.web.jdbc.Email"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<% List<Email> emails = (List<Email>) request.getAttribute("EMAIL_LIST");%>
<body>
<%= emails %>
</body>
</html>