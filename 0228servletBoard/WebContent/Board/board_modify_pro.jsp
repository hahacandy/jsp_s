<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int)request.getAttribute("row");

	int idx =  (int)request.getAttribute("idx");

	if(row > 0){
%>
	<script>
		alert("수정 되었습니다.\n감사");
		location.href="board_view?idx=<%=idx%>";
	</script>
<%}else{%>
	<script>
		alert("수정 실패하였습니다.\n 다음에 다시 시도해주세요.");
		location.href="board_modify?idx=<%=idx%>";
	</script>
<%}%>