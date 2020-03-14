<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>이익 순위 제품</title>
<link rel="stylesheet" type="text/css" href="./css/header.css"/>
<script type="text/javascript">

	window.onload = function () {
		document.getElementById("subject").innerHTML = "이익 순위 제품";
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
				<th>제품이름</th>
				<th>총이익 금액</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.pname}</td>
					<td>
						<fmt:formatNumber value="${vo.allsale}" pattern="#,###"/>원
					</td>
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