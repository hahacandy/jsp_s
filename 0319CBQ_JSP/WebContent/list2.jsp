<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="db.jsp" %>

<%
	List<List> list = getGroupSales();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원별 매출 현황</title>

<style type="text/css">
	
	* {
		margin: 0;
		padding: 0;
	}
	
	header {
		padding: 20px;
		background-color: #ABF200;
	}
	
	header h1 {
		text-align: center;
		
	}
	
	menu {
		padding: 20px;
		background: #E4F7BA;
	}
	
	menu li {
		float: left;
		list-style: none;
		padding: 0 30px;
	}
	
	section{
		padding:30px;
		background-color: #CEF279;
	}
	section h1{
		text-align: center;
	}
	section table{
		margin: auto;
	}
	
	section table th,td{
		padding: 0 10px;
		border: 1px solid #000000;
		text-align: center;
	}
	
	footer{
		padding: 20px;
		text-align: center;
		background-color: #BCE55C;
		
	}

</style>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	<%@ include file="./include/menu.jsp" %>
	
	<section>
		<h1>회원별 매출 현황</h1>
		
		<form action="" name="frm" method="post" onsubmit="return false">
			<table>
			
				<tr>
					<th>회원번호</th>
					<th>구매건수</th>
					<th>구매액합계</th>

				</tr>
				<%for(List<String> vo : list){ %>
				<tr>
					<td><%=vo.get(0)%></td>
					<td><%=vo.get(1)%></td>
					<td><%=vo.get(2)%></td>
				</tr>
				<%}%>
				
				<%if(list.size() == 0) {%>
				<tr>
					<td colspan="3">등록된 정보가 없습니다.</td>
				</tr>
				<%}%>
			
			</table>
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>