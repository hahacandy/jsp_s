<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int row = (int)request.getAttribute("row");

	if(row > 0){
%>
	<script type="text/javascript">
		alert("삭제 완료");
		opener.location.replace("board_list");
		self.close();
	</script>
<%	}else{%>
	<script type="text/javascript">
		alert("삭제 실패");
		history.back();
	</script>
<%	}%>