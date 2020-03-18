<%@page import="model.MemberVO"%>
<%@page import="model.MemberDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
	List<MemberVO> members = MemberDAO.getInstance().getAllMember();
%>
    
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
			
			<%for(MemberVO member : members){ %>
			
			<tr>
				<td><%=member.getCustno()%></td>
				<td><%=member.getCustname()%></td>
				<td><%=member.getPhone()%></td>
				<td><%=member.getAddress()%></td>
				<td><%=member.getJoindate().substring(0,10)%></td>
				<td><%=member.getGrade()%></td>
				<td><%=member.getCity()%></td>
			</tr>

			<%} %>
			
			<% if(members.size() == 0){ %>
			<tr>
				<td colspan="7">등록된 회원이 없습니다</td>
			</tr>
			<%} %>
			
		</table>
	</section>
	
	<%@ include file="./include/footer.jsp" %>
</body>
</html>