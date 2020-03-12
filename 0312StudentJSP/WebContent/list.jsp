<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="db.jsp" %>
<%
	List<List> list = getStudents();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/my.css"/>
<script type="text/javascript" src="./js/list.js"></script>


<script type="text/javascript">

	
	window.onload = function () {
		autoInputDate();
	}
	
	function autoInputDate() {
		var today = new Date();
		
		var today_year = today.getFullYear();
		var today_month = today.getMonth()+1;
		var today_day = today.getDate();
		
		var today = today_year + "" + today_month + "" + today_day;	
		
		frm.date.value = today;
	}
	
	function resetForm() {
		frm.reset();
		autoInputDate();
	}

</script>

</head>
<body>

	<%@ include file="../include/header.jsp" %>
	
	<section>
	
		<h1>학생 성적 조회 화면</h1>
		<br>
		
		<form name="frm">
			
			<table>
				<tr>
					<th>번호</th>
					<th>이름</th>
					<th>성별</th>
					<th>국어</th>
					<th>영어</th>
					<th>수학</th>
					<th>총점</th>
					<th>평균</th>
					<th>등급</th>
				</tr>
				<%for(List<String> vo : list){%>
				<tr>
					<td><%=vo.get(0)%></td>
					<td><%=vo.get(1)%></td>
					<td><%=vo.get(2)%></td>
					<td><%=vo.get(3)%></td>
					<td><%=vo.get(4)%></td>
					<td><%=vo.get(5)%></td>
					<td><%=vo.get(6)%></td>
					<td><%=vo.get(7)%></td>
					<td><%=vo.get(8)%></td>
				</tr>
				<%} %>
				
				
				
				
			</table>
			
		</form>
	
	</section>
	
	<%@ include file="../include/footer.jsp" %>
	
	

</body>
</html>