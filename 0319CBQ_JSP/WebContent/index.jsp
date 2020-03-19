<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메인화면</title>

<style type="text/css">
	
	* {
		margin: 0;
		padding: 0;
	}
	
	header {
		padding: 20px;
		background-color: #ABF200;
	}
	
	header h1 {
		text-align: center;
		
	}
	
	menu {
		padding: 20px;
		background: #E4F7BA;
	}
	
	menu li {
		float: left;
		list-style: none;
		padding: 0 30px;
	}
	
	section{
		padding:30px;
		background-color: #CEF279;
	}
	section h1{
		text-align: center;
	}
	
	footer{
		padding: 20px;
		text-align: center;
		background-color: #BCE55C;
	}

</style>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	<%@ include file="./include/menu.jsp" %>
	
	<section>
		<h1>과정 평가형 자격 CBQ</h1>
		<br>국가직무능력표준(NCS:National Competency Standards)으로 설계된 교육훈련과정을 이수하고 내 .외부평가를 거쳐 취득하는 국가 기슬자격입니다.<br>
		<br>산업현장 중심의 교육평가로 더 커지는 능력!<br>
		<br>알고 있는 것에 할 수 있다는 것을 더하는<br>
		<br>과정평가형 자격은<br>
		<br>현장 중심형 인재육성을 지원합니다.<br>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>