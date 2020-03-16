<%@page import="sample.model.guest.GuestDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	GuestDAO dao = GuestDAO.getInstance();
	int row = dao.getAllGuestCNT();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h3>SampleMVC Project</h3>
<h1>건수:<%=row %></h1>
</body>
</html>