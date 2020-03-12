<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="db.jsp" %>

<% 
	int bun = Integer.valueOf(request.getParameter("bun"));
	String name = request.getParameter("name");
	String gender = request.getParameter("gender");
	int kor = Integer.valueOf(request.getParameter("kor"));
	int eng = Integer.valueOf(request.getParameter("eng"));
	int mat = Integer.valueOf(request.getParameter("mat"));
	String regdate = request.getParameter("regdate");
	
	writeStudent(bun, name, gender, kor, eng, mat, regdate);
	
	response.sendRedirect("list.jsp");

%>