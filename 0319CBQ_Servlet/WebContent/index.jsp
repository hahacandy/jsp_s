<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	footer{
		text-align: center;
		padding:10px;
		background-color: #B7F0B1;
	}

</style>

</head>
<body>
	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>과정평가형 자격 CBQ</h1>
		
		<br>국가직무능력표준(NCS:National Competency Standards)으로 설계된 교육*훈련과정을 체계적으로 이수하고 내외부 평가를 거쳐 취득하는 국가 기술 자격입니다.<br>
		<br>산업현장 중심의 교육평가로 더 커지는 능력!<br>
		<br>알고 있는 것에 할 수 있는 것을 더하는<br>
		<br>과정평가형 자격은<br>
		<br>현장 중심형 인재육성을 지원합니다.<br>
	</section>
	
	<%@ include file="./include/footer.jsp" %>
</body>
</html>