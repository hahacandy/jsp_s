<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="langSet.jsp" %>

<%
	int idx = Integer.valueOf(request.getParameter("idx"));
	String pass = request.getParameter("pass");
	
	GuestDAO dao = GuestDAO.getInstance();

	int row = 0;
	
	row = dao.deletePost(idx, pass);
	
	if(row > 0){
%>
	<script type="text/javascript">
		alert("삭제 성공");
		location.href="board_list.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("삭제 실패");
		history.back();
	</script>
<%	}
%>