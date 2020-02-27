<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page import="model.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="langSet.jsp" %>

<%

	GuestDAO dao = GuestDAO.getInstance();

	List<Object> iInfos = new ArrayList<Object>();

	iInfos.add(request.getParameter("name"));
	iInfos.add(request.getParameter("email"));
	iInfos.add(request.getParameter("subject"));
	iInfos.add(request.getParameter("contents"));
	iInfos.add(request.getParameter("pass"));
	iInfos.add(request.getRemoteAddr());
	List<String> infos = dao.inputListInfos(iInfos);
	
	int row = 0;
	
	row = dao.writePost(infos);
	
	if(row > 0){
%>
	<script type="text/javascript">
		alert("등록성공");
		location.href="board_list.jsp";
	</script>
<%
	}else{
%>
	<script type="text/javascript">
		alert("등록실패");
		history.back();
	</script>
<%	}
%>