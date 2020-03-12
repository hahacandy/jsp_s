<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/my.css"/>
<script type="text/javascript" src="./js/list.js"></script>

<script type="text/javascript">

	


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
				<c:forEach var="vo" items="${list }">
					<tr>
						<td>${vo.bun}</td>
						<td>${vo.name}</td>
						<td>${vo.gender}</td>
						<td>${vo.kor}</td>
						<td>${vo.eng}</td>
						<td>${vo.mat}</td>
						<td>${vo.tot}</td>
						<td>${vo.avg}</td>
						<td>${vo.grade}</td>

					</tr>
				</c:forEach>

				
				
			</table>
			
		</form>
	
	</section>
	
	<%@ include file="../include/footer.jsp" %>
	
	

</body>
</html>