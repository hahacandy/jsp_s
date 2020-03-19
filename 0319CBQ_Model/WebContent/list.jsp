<%@page import="Model.SalesVO"%>
<%@page import="Model.SalesDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<SalesVO> list = SalesDAO.getInstance().getAllSales();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원매출 조회 및 수정</title>

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
		<h1>회원매출 조회 및 수정</h1>
		
		<form action="" name="frm" method="post" onsubmit="return false">
			<table>
			
				<tr>
					<th>회원번호</th>
					<th>판매번호</th>
					<th>단가</th>
					<th>수량</th>
					<th>가격</th>
					<th>상품코드</th>
					<th>판매일자</th>
				</tr>
				<% for(SalesVO vo : list){ %>
				<tr>
				
					<td><%=vo.getCustno()%></td>
					<td><a href="modify.jsp?saleno=<%=vo.getSaleno()%>"><%=vo.getSaleno()%></a></td>
					<td><%=vo.getPcost()%></td>
					<td><%=vo.getAmount()%></td>
					<td><%=vo.getPrice()%></td>
					<td><%=vo.getPcode()%></td>
					<td><%=vo.getSdate().substring(0,4)+"년"+vo.getSdate().substring(5,7)+"월"+vo.getSdate().substring(8,10)+"일"%></td>
				</tr>
				<%} %>
				
				<%if(list.size() == 0){ %>
				<tr>
					<td colspan="7">등록된 정보가 없습니다.</td>
				</tr>
				<%} %>
			
			</table>
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>