<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹별 재고 수량</title>
<link rel="stylesheet" type="text/css" href="./css/header.css"/>
<script type="text/javascript">

	window.onload = function () {
		document.getElementById("subject").innerHTML = "그룹별 재고 수량";
	}

</script>

<style type="text/css">

	section h1{
		padding: 20px;
	}

	section table {
		margin: 0 auto;
		text-align: center;
	}
	
	section table tr th,td{
		padding: 1 10px;
	}

</style>

</head>
<body>
	<%@include file="./include/header.jsp" %>
	
	<section>
	
		<table border="1px">
			<tr>
				<th>그룹이름</th>
				<th>재고 수량</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>
						<c:if test="${vo.gcode == 'A'}">컴퓨터</c:if>
						<c:if test="${vo.gcode == 'B'}">모바일</c:if>
						<c:if test="${vo.gcode == 'C'}">냉장고부품</c:if>
					</td>
					<td>${vo.allsale}개</td>
				</tr>
			</c:forEach>
			
			<c:if test="${empty list}">
				<tr>
					<td colspan="2">해당 제품 없음</td>
				</tr>
			</c:if>

		
		</table>
	</section>
	
</body>
</html>