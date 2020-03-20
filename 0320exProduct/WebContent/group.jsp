<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>제품 그룹별 재고현황</title>

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
	
	section table th,td{
		padding: 0 10px;
		text-align: center;
		border: 1px solid #000000;
	}
	
	footer {
		padding: 20px;
		background-color: #5D5D5D;
		text-align: center;
	}

</style>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>제품 그룹별 재고현황</h1>
		
		<form name="frm" action="Insert" method="post" onsubmit="return false">
		
			<table>
				<tr>
					<th>그룹코드</th>
					<th>그룹명</th>
					<th>재고수량</th>
				</tr>
				<c:forEach var="vo" items="${list}">
					<tr>
						<td>${vo.gcode}</td>
						<td>${vo.gname}</td>
						<td>${vo.jnum}</td>
					</tr>
				</c:forEach>
				
				<c:if test="${empty list }">
					<tr>
						<td colspan="7">등록된 자료가 없습니다.</td>
					</tr>
				</c:if>
				
			
			</table>
		
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>