<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String test1 = request.getRemoteHost();
	String test2 = request.getRemoteAddr();
	
	out.print(test1 + "<br>" + test2);
	
%>