<%@page import="Model.SalesDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%

	if(request.getParameter("custno")!=null && 
			request.getParameter("saleno")!=null &&
			request.getParameter("pcost")!=null &&
			request.getParameter("amount")!=null &&
			request.getParameter("price")!=null &&
			request.getParameter("pcode")!=null &&
			request.getParameter("sdate")!=null){
		
		int custno = Integer.valueOf(request.getParameter("custno"));
		int saleno = Integer.valueOf(request.getParameter("saleno"));
		int pcost = Integer.valueOf(request.getParameter("pcost"));
		int amount = Integer.valueOf(request.getParameter("amount"));
		int price = Integer.valueOf(request.getParameter("price"));
		String pcode = request.getParameter("pcode");
		String sdate = request.getParameter("sdate");

		boolean result = SalesDAO.getInstance().insertSales(custno, saleno, pcost, amount, price, pcode, sdate);
		
		if(result){%>
		<script type="text/javascript">
			alert("등록 성공");
			location.href="list.jsp";
		</script>	
		<%}else{%>
		<script type="text/javascript">
			alert("등록 실패");
			location.href="list.jsp";
		</script>	
		<%}
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원매출 등록</title>

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
	
	footer{
		padding: 20px;
		text-align: center;
		background-color: #BCE55C;
	}

</style>

<script type="text/javascript">

	function send() {
		if(frm.custno.value == ""){
			alert("회원번호를 입력해주세요.");
			frm.custno.focus();
			return;
		}
		
		if(frm.saleno.value == ""){
			alert("판매번호를 입력해주세요.");
			frm.saleno.focus();
			return;
		}
		
		if(frm.pcost.value == ""){
			alert("판매번호를 입력해주세요.");
			frm.pcost.focus();
			return;
		}
		
		if(frm.amount.value == ""){
			alert("수량을 입력해주세요.");
			frm.amount.focus();
			return;
		}
		
		if(frm.price.value == ""){
			alert("가격을 입력해주세요.");
			frm.price.focus();
			return;
		}
		
		if(frm.pcode.value == ""){
			alert("상품코드를 입력해주세요.");
			frm.pcode.focus();
			return;
		}
		
		if(frm.sdate.value == ""){
			alert("일자를 입력해주세요.");
			frm.sdate.focus();
			return;
		}
		
		alert("회원매출정보를 등록합니다.");
		frm.submit();
	}
	
	function clear() {
		alert("모든정보를 지우고 다시 입력합니다.");
		frm.reset();
	}

</script>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	<%@ include file="./include/menu.jsp" %>
	
	<section>
		<h1>회원매출 등록</h1>
		
		<form action="insert.jsp" name="frm" method="post" onsubmit="return false">
			<table>
			
				<tr>
					<th>회원번호</th>
					<td><input type="text" name="custno" value="">예)100001</td>
				</tr>
				
				<tr>
					<th>판매번호</th>
					<td><input type="text" name="saleno" value="">예)2020001</td>
				</tr>
				
				<tr>
					<th>단가</th>
					<td><input type="text" name="pcost" value=""></td>
				</tr>
				
				<tr>
					<th>수량</th>
					<td><input type="text" name="amount" value=""></td>
				</tr>
				
				<tr>
					<th>가격</th>
					<td><input type="text" name="price" value=""></td>
				</tr>
				
				<tr>
					<th>상품코드</th>
					<td><input type="text" name="pcode" value=""></td>
				</tr>
				
				<tr>
					<th>판매일자</th>
					<td><input type="text" name="sdate" value="">예)20101010</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:send()"><input type="button" value="등록하기"></a>
						<a href="javascript:clear()" ><input type="button" value="다시 입력"></a>
					</td>
				</tr>
			
			</table>
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>