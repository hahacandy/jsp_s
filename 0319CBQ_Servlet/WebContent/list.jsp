<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈쇼핑 회원관리 ver1.0</title>

<style type="text/css">
	* {
		margin:0;
		padding:0;
	}
	
	header h1 {
		background-color: #2F9D27;
		text-align: center;
		padding: 10px;
	}
	
	header nav {
		background-color: #47C83E;
		padding: 10px;
	}
	
	header nav ul{
	  display: table;
	  margin-right: auto;
	  margin-left: auto;
	}
	
	header nav ul li {
		float: left;
		list-style: none;
		margin: 0 50px;
	}
	
	section {
		padding: 20px;
		background-color: #86E57F;
	}
	
	section h1 {
		text-align: center;
	}
	
	section table{
		margin: auto;
	}
	section table th,td{
		border: 1px solid #000000;
		text-align: center;
		padding: 0 10px;
	}
	
	footer{
		text-align: center;
		padding:10px;
		background-color: #B7F0B1;
	}

</style>
<link rel="stylesheet" type="text/css" href="./css/header.css">

</head>
<body>
	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>회원 목록 조회</h1>
		
		<table>
			<tr>
				<th>회원번호</th>
				<th>회원성명</th>
				<th>전화번호</th>
				<th>주소</th>
				<th>가입일자</th>
				<th>고객등급</th>
				<th>거주지역</th>
			</tr>
			
			<c:forEach var="vo" items="${list}">
				<tr>
					<td>${vo.custno}</td>
					<td>${vo.custname}</td>
					<td>${vo.phone}</td>
					<td>${vo.address}</td>
					<td>${vo.joindate.substring(0.10)}</td>
					<td>${vo.grade}</td>
					<td>${vo.city}</td>
				</tr>
			</c:forEach>

			<c:if test="${empty list }">
				<tr>
					<td colspan="7">등록된 회원이 없습니다</td>
				</tr>
			</c:if>

			
		</table>
	</section>
	
	<%@ include file="./include/footer.jsp" %>
</body>
</html>