<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 입력</title>

<style type="text/css">

	* {
		margin:0;
		padding:0;
	}
	
	header h1{
		text-align: center;
		padding : 20px;
		background-color: #EAEAEA;
	}
	
	nav {
		padding: 20px;
		background-color: #BDBDBD;
	}
	
	nav ul li{
		float:left;
		list-style: none;
		padding: 0 20px;
	}
	
	section {
		padding: 20px;
		background-color: #8C8C8C;
	}
	
	section h1 {
		text-align: center;
	}
	
	section table{
		margin: auto;
	}
	
	footer {
		padding: 20px;
		background-color: #5D5D5D;
		text-align: center;
	}

</style>

	<script type="text/javascript">
	
		function send() {
			
			if(frm.code.value == ""){
				alert("제품코드 입력!");
				frm.code.focus();
				return;
			}
			
			if(frm.pname.value == ""){
				alert("제품이름 입력!");
				frm.pname.focus();
				return;
			}
			
			if(frm.cost.value == ""){
				alert("제품원가 입력!");
				frm.cost.focus();
				return;
			}
			
			if(frm.pnum.value == ""){
				alert("목표수량 입력!");
				frm.pnum.focus();
				return;
			}
			
			if(frm.jnum.value == ""){
				alert("재고수량 입력!");
				frm.jnum.focus();
				return;
			}
			
			if(frm.sale.value == ""){
				alert("출고가 입력!");
				frm.sale.focus();
				return;
			}
			
			if(frm.gcode.value == ""){
				alert("그룹이름 선택!");
				frm.gcode.focus();
				return;
			}
			
			frm.submit();
			
			
		}
		
		
	</script>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>제품 입력</h1>
		
		<form name="frm" action="Insert" method="post" onsubmit="return false">
		
			<table>
				<tr>
					<th>제품코드</th>
					<td><input type="text" name="code" value=""></td>
				</tr>
				
				<tr>
					<th>제품이름</th>
					<td><input type="text" name="pname" value=""></td>
				</tr>
				
				<tr>
					<th>제품원가</th>
					<td><input type="text" name="cost" value=""></td>
				</tr>
				
				<tr>
					<th>목표수량</th>
					<td><input type="text" name="pnum" value=""></td>
				</tr>
				
				<tr>
					<th>재고수량</th>
					<td><input type="text" name="jnum" value=""></td>
				</tr>
				
				<tr>
					<th>출고가</th>
					<td><input type="text" name="sale" value=""></td>
				</tr>
				
				<tr>
					<th>그룹이름</th>
					<td>
						<select name="gcode">
							<option value="">그룹선택
							 <c:forEach var="vo" items="${list}">
							 	<option value="${vo.gcode}">${vo.gname}
							 </c:forEach>
						</select>
					</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:send()"><input type="button" value="등록하기"></a>
						<a href="Index"><input type="button" value="홈으로"></a>
					</td>
				</tr>	
			</table>
		
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>