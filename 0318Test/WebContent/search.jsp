<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ include file="db.jsp" %>
 <%
 	List<String> infos = null;
 	int custno = 0;
 	if(request.getParameter("custno") != null){
 		custno = Integer.valueOf(request.getParameter("custno"));
 		infos = getMember(custno);
 		if(infos == null){
 		%>
 		<script type="text/javascript">
			alert("검색 결과가 없습니다.");
		</script>
 		<%
 		}
 	}

 %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홈쇼핑 회원 정보 검색</title>

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


	function search(){
		if(frm.bun.value == ""){
			alert("번호를 입력해주세요");
			frm.bun.focus();
			return ;
		}
		
		location.href="search.jsp?custno="+frm.bun.value;
	}
	
	function modify(){
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
		
		var result = confirm("회원을 수정할까요?")
		if(result){
			frm.submit();
		}
	}
	
	function home(){
		location.href="index.jsp";
	}

</script>

</head>
<body>
	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>홈쇼핑 정보 검색</h1>
		<form action="modify_pro.jsp" name="frm" method="post">
		
			<table>
			<%if(infos == null){ %>
				<tr>
					<th>회원 번호</th>
					<td><input type="text" name="bun"></td>
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
					<th>가입 일자</th>
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
			<%}else{ %>
<tr>
					<th>회원 번호</th>
					<td><input type="text" name="bun" value="<%=custno%>"></td>
				</tr>
				
				<tr>
					<th>회원 성명</th>
					<td><input type="text" name="name" value="<%=infos.get(0)%>"></td>
				</tr>
				
				<tr>
					<th>회원 전화</th>
					<td><input type="text" name="tel" value="<%=infos.get(1)%>"></td>
				</tr>
				
				<tr>
					<th>회원 주소</th>
					<td><input type="text" name="addr" value="<%=infos.get(2)%>"></td>
				</tr>
				
				<tr>
					<th>가입 일자</th>
					<td><input type="text" name="date" value="<%=infos.get(3).substring(0,4)%><%=infos.get(3).substring(5,7)%><%=infos.get(3).substring(8,10)%>"></td>
				</tr>
				
				<tr>
					<th>고객등급(A:VIP, B:일반, C:직원)</th>
					<td><input type="text" name="grade" value="<%=infos.get(4)%>"></td>
				</tr>
				
				<tr>
					<th>도시 코드</th>
					<td><input type="text" name="code" value="<%=infos.get(5)%>"></td>
				</tr>
			<%}%>
				
				
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:search()"><input type="button" value="조회하기"></a>
						&nbsp;
				<%if(infos != null){%>
						<a href="javascript:modify()"><input type="button" value="수정하기"></a>
						&nbsp;
				<%}%>
						<a href="javascript:home()"><input type="button" value="홈으로"></a>
					</td>
				</tr>
			</table>
		
		</form>
		
	</section>
	
	<%@ include file="./include/footer.jsp" %>
</body>
</html>