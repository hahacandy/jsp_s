<%@page import="model.MemberVO"%>
<%@page import="model.MemberDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

 <%
 	MemberVO vo = null;
 	int custno = 0;
 	if(request.getParameter("custno") != null){
 		custno = Integer.valueOf(request.getParameter("custno"));
 		vo = MemberDAO.getInstance().getMember(custno);
 		if(vo == null){
 		%>
 		<script type="text/javascript">
			alert("검색 결과가 없습니다.");
			location.href="search.jsp";
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
		<form action="modify_pro.jsp" name="frm" method="post" onsubmit="return false">

			<table>
			<%if(vo == null){ %>
			
				<tr>
					<th>회원 번호</th>
					<td>
						<input type="text" name="bun">
					</td>
				</tr>
				
		
			<%}else{ %>
				<tr>
					<th>회원 번호</th>
					<td><input type="text" name="bun" value="<%=custno%>" readonly></td>
				</tr>
				
				<tr>
					<th>회원 성명</th>
					<td><input type="text" name="name" value="<%=vo.getCustname()%>"></td>
				</tr>
				
				<tr>
					<th>회원 전화</th>
					<td><input type="text" name="tel" value="<%=vo.getPhone()%>"></td>
				</tr>
				
				<tr>
					<th>회원 주소</th>
					<td><input type="text" name="addr" value="<%=vo.getAddress()%>"></td>
				</tr>
				
				<tr>
					<th>가입 일자</th>
					<td><input type="text" name="date" value="<%=vo.getJoindate().substring(0,4)%><%=vo.getJoindate().substring(5,7)%><%=vo.getJoindate().substring(8,10)%>"></td>
				</tr>
				
				<tr>
					<th>고객등급(A:VIP, B:일반, C:직원)</th>
					<td><input type="text" name="grade" value="<%=vo.getGrade()%>"></td>
				</tr>
				
				<tr>
					<th>도시 코드</th>
					<td><input type="text" name="code" value="<%=vo.getCity()%>"></td>
				</tr>
			<%}%>
				<tr>
					<td colspan="2" align="center">
				<%if(vo == null){%>
						<a href="javascript:search()"><input type="button" value="조회하기"></a>
						&nbsp;
				<%}%>
				<%if(vo != null){%>
						<a href="search.jsp"><input type="button" value="다시 조회하기"></a>
						&nbsp;
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