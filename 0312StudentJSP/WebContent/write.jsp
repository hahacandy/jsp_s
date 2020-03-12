<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" type="text/css" href="./css/my.css"/>
<script type="text/javascript" src="./js/my.js"></script>


</head>
<body>

	<%@ include file="../include/header.jsp" %>
	
	<section>
	
		<h1>학생 성적 관리 프로그램</h1>
		<br>
		
		<form name="frm" method="post" action="write_pro.jsp">
			
			<table class="table">
				<tr>
					<th>번호</th>
					<td>
						<input type="text" name="bun">(4자리:1101)
					</td>
				</tr>
				
				<tr>
					<th>이름</th>
					<td>
						<input type="text" name="name">
					</td>
				</tr>
				
				<tr>
					<th>성별</th>
					<td>
						<input type="radio" name="gender" value="M">남자
						<input type="radio" name="gender" value="F">여자
					</td>
				</tr>
				
				<tr>
					<th>국어</th>
					<td>
						<input type="text" name="kor">(0~100)
					</td>
				</tr>
				
				<tr>
					<th>영어</th>
					<td>
						<input type="text" name="eng">(0~100)
					</td>
				</tr>
				
				<tr>
					<th>수학</th>
					<td>
						<input type="text" name="mat">(0~100)
					</td>
				</tr>
				
				<tr>
					<th>등록일자</th>
					<td>
						<input type="text" name="regdate" readonly>(0~100)
					</td>
				</tr>
				
				<tr align="center">
					<th colspan="2">
						<input type="button" value="등록하기" onclick="writeForm()">
						<input type="button" value="다시쓰기" onclick="resetForm()">
					</th>
				</tr>
				
			</table>
			
		</form>
	
	</section>
	
	<%@ include file="../include/footer.jsp" %>
	
	

</body>
</html>