<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int result = (int)request.getAttribute("result");

	if(result == -1){
%>
	<script type="text/javascript">
		alert("아이디가 틀렸습니다.");
		history.back();
	</script>
<%	}else if(result == 0){%>
	<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.");
		history.back();
	</script>
<%	}%>
