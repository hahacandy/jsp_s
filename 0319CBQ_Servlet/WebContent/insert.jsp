<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈쇼핑 회원등록</title>

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
	
	section table {
		margin: auto;
		padding: 20px;
	}
	
	section table th,td{
		border: 1px solid #000000;
	}
	
	footer{
		text-align: center;
		padding:10px;
		background-color: #B7F0B1;
	}

</style>

<script type="text/javascript">

	window.onload = function(){
		frm.bun.value = "${nextCustNo}";
	}

	function insert() {
		if(frm.name.value == ""){
			alert("이름을 입력해주세요.");
			frm.name.focus();
			return;
		}
		
		if(frm.tel.value == ""){
			alert("전화번호를 입력해주세요.");
			frm.tel.focus();
			return;
		}
		
		if(frm.date.value == ""){
			alert("가입일자를 입력해주세요.");
			frm.date.focus();
			return;
		}
		
		if(frm.grade.value == ""){
			alert("고객등급을 입력해주세요.");
			frm.grade.focus();
			return;
		}
		
		if(frm.code.value == ""){
			alert("도시코드를 입력해주세요.");
			frm.code.focus();
			return;
		}
		
		var result = confirm("회원을 등록할까요?")
		if(result){
			frm.submit();
		}
	}
	
	function reset() {
		var result = confirm("다시 작성 하시겠습니까?");
		if(result){
			frm.reset();
		}
	}

</script>

</head>
<body>
	<%@ include file="./include/header.jsp" %>
	
	
	<section>
		<h1>홈쇼핑 회원등록</h1>
		<form action="Insert" name="frm" method="post">
		
			<table>
				<tr>
					<th>회원 번호(자동발생)</th>
					<td><input type="text" name="bun" readonly></td>
				</tr>
				
				<tr>
					<th>회원 성명</th>
					<td><input type="text" name="name"></td>
				</tr>
				
				<tr>
					<th>회원 전화</th>
					<td><input type="text" name="tel"></td>
				</tr>
				
				<tr>
					<th>회원 주소</th>
					<td><input type="text" name="addr"></td>
				</tr>
				
				<tr>
					<th>가입 일자(예:20101010)</th>
					<td><input type="text" name="date"></td>
				</tr>
				
				<tr>
					<th>고객등급(A:VIP, B:일반, C:직원)</th>
					<td><input type="text" name="grade"></td>
				</tr>
				
				<tr>
					<th>도시 코드</th>
					<td><input type="text" name="code"></td>
				</tr>
				
				
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:insert()"><input type="button" value="등록하기"></a>
						&nbsp;
						<a href="javascript:reset()"><input type="button" value="다시쓰기"></a>
					</td>
				</tr>
			</table>
		
		</form>
		
	</section>
	
	<%@ include file="./include/footer.jsp" %>
</body>
</html>
