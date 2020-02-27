<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="langSet.jsp" %>

<%
	int idx = Integer.valueOf(request.getParameter("idx"));
	GuestDAO dao = GuestDAO.getInstance();

	List<Object> iInfos = new ArrayList<Object>();

	iInfos.add(request.getParameter("email"));
	iInfos.add(request.getParameter("subject"));
	iInfos.add(request.getParameter("contents"));
	iInfos.add(idx);
	iInfos.add(request.getParameter("pass"));
	
	List<String> infos = dao.inputListInfos(iInfos);
	
	int row = 0;
	
	row = dao.modifyPost(infos);
	
	if(row > 0){
%>
	<script type="text/javascript">
		alert("수정 성공");
		location.href="board_view.jsp?idx=<%= idx %>";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("수정 실패");
		history.back();
	</script>
<%	}
%>