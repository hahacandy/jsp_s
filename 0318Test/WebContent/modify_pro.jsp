<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="db.jsp" %>

<%
	
	int bun = Integer.valueOf(request.getParameter("bun"));
	String name = request.getParameter("name");
	String tel = request.getParameter("tel");
	String addr = request.getParameter("addr");
	String date = request.getParameter("date");
	String grade = request.getParameter("grade");
	String code = request.getParameter("code");
	
	boolean result = modifyMember(bun, name, tel, addr, date, grade, code);

	if(result){
%>
<script type="text/javascript">
	alert("회원 수정 성공");
	location.href="search.jsp";
</script>
<%}else{ %>
<script type="text/javascript">
	alert("회원 수정 실패");
	history.back();
</script>
<%} %>
