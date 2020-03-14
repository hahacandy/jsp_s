<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>생산관리 등록</title>
	<link rel="stylesheet" type="text/css" href="./css/header.css"/>
	<link rel="stylesheet" type="text/css" href="./css/section/write.css"/>
	<script type="text/javascript" src="./js/section/checkInput.js"></script>
</head>

<c:if test="${!empty result}">

	<c:if test="${result>0}">
		<script type="text/javascript">
			alert("제품 등록 완료!");
		</script>
	</c:if>
	<c:if test="${result==0}">
		<script type="text/javascript">
			alert("제품 등록 실패!\n이미 존재하는 등록코드");
		</script>
	</c:if>
	<c:if test="${result==-1}">
		<script type="text/javascript">
			alert("제품 등록 실패!\n알 수 없는 오류)");
		</script>
	</c:if>
	
</c:if>

<script type="text/javascript">


	window.onload = function () {
		document.getElementById("subject").innerHTML = "생산관리 등록화면";
	}
	
	function send() {
		
		if(checkInput()){
			frm.submit();
		}

	}
	
</script>

<body>

	<%@include file="./include/header.jsp" %>
	
	<section>
	
		<form action="product_write" name="frm" method="post">
			<table>
				<tr>
					<td>
						<li>제품코드:</li>
					</td>
					<td><input type="text" name="code"></td>
				</tr>
	
				<tr>
					<td>
						<li>제품이름:</li>
					</td>
	
					<td><input type="text" name="pname"></td>
				</tr>
	
				<tr>
					<td>
						<li>제품원가:</li>
					</td>
	
					<td><input type="text" name="cost"></td>
				</tr>
	
				<tr>
					<td>
						<li>목표수량:</li>
					</td>
	
					<td><input type="text" name="pnum"></td>
				</tr>
	
				<tr>
					<td>
						<li>재고수량:</li>
					</td>
	
					<td><input type="text" name="jnum"></td>
				</tr>
	
				<tr>
					<td>
						<li>출고가:</li>
					</td>
	
					<td><input type="text" name="sale"></td>
				</tr>
	
				<tr>
					<td>
						<li>그룹이름:</li>
					</td>
					<td>
						<select name="gcode">
							<option value="A">컴퓨터
							<option value="B">모바일
							<option value="C">냉장고부품
						</select>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="등록" onclick="send()">
						&nbsp;
						<input type="button" value="메인화면" onclick="location.href='index.do'">
					</td>
				</tr>
	
			</table>
		</form>
	
	</section>
		
	
</body>
</html>