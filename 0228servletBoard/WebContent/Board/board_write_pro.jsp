<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int)request.getAttribute("row");

	if(row > 0){
%>
	<script>
		alert("등록 되었습니다.\n감사");
		location.href="board_list";
	</script>
<%}else{%>
	<script>
		alert("등록 실패하였습니다.\n 다음에 다시 시도해주세요.");
		location.back();
	</script>
<%}%>