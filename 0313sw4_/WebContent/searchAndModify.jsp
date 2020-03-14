<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>생산관리 조회 & 수정</title>
	<link rel="stylesheet" type="text/css" href="./css/header.css"/>
	<link rel="stylesheet" type="text/css" href="./css/section/searchAndModify.css"/>
	<script type="text/javascript" src="./js/section/checkInput.js"></script>
	<script type="text/javascript" src="./js/section/searchAndModify.js"></script>
</head>

<c:if test="${!empty mode}">
	<c:if test="${mode == 'search'}">
		<c:if test="${empty vo}">
			<script type="text/javascript">
				alert("조회결과가 없습니다.");
			</script>
		</c:if>
	</c:if>
	
	<c:if test="${mode == 'modify'}">
		<c:if test="${result == false}">
			<script type="text/javascript">
				alert("제품 수정을 실패하였습니다.");
			</script>
		</c:if>
		<c:if test="${result == true}">
			<script type="text/javascript">
				alert("제품 수정을 성공하였습니다.");
			</script>
		</c:if>
	</c:if>
	
	<c:if test="${mode == 'delete'}">
		<c:if test="${result == false}">
			<script type="text/javascript">
				alert("제품 삭제를 실패하였습니다.");
			</script>
		</c:if>
		<c:if test="${result == true}">
			<script type="text/javascript">
				alert("제품 삭제를 성공하였습니다.");
			</script>
		</c:if>
	</c:if>
	
</c:if>

<body>
	<%@include file="./include/header.jsp" %>

	<section>
	
		<form action="" name="frm" method="post">
			<input type="hidden" name="mode" value="">
			<table>
				<tr>
					<td>
						<li>제품코드:</li>
					</td>
					<td><input type="text" name="code" value="${vo.code}"></td>
				</tr>
	
				<tr>
					<td>
						<li>제품이름:</li>
					</td>
	
					<td><input type="text" name="pname" value="${vo.pname}"></td>
				</tr>
	
				<tr>
					<td>
						<li>제품원가:</li>
					</td>
	
					<td><input type="text" name="cost" value="${vo.cost}"></td>
				</tr>
	
				<tr>
					<td>
						<li>목표수량:</li>
					</td>
	
					<td><input type="text" name="pnum" value="${vo.pnum}"></td>
				</tr>
	
				<tr>
					<td>
						<li>재고수량:</li>
					</td>
	
					<td><input type="text" name="jnum" value="${vo.jnum}"></td>
				</tr>
	
				<tr>
					<td>
						<li>출고가:</li>
					</td>
	
					<td><input type="text" name="sale" value="${vo.sale}"></td>
				</tr>
	
				<tr>
					<td>
						<li>그룹이름:</li>
					</td>
					<td>
						<select name="gcode">
								<option value="A" <c:if test='${vo.getGcode() == "A"}'>selected</c:if> >컴퓨터
								<option value="B" <c:if test='${vo.getGcode() == "B"}'>selected</c:if> >모바일
								<option value="C" <c:if test='${vo.getGcode() == "C"}'>selected</c:if> >냉장고부품
						</select>
					</td>
				</tr>
				
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" value="조회" onclick="searchProduct()">
						&nbsp; 
						<input type="button" value="수정" onclick="modifyProduct()">
						&nbsp; 
						<input type="button" value="삭제" onclick="deleteProduct()"> 
						&nbsp; 
						<input type="button" value="메인화면" onclick="location.href='index.do'">
					</td>
				</tr>
	
			</table>
		</form>


	</section>


</body>
</html>