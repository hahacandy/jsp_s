<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그룹코드 등록</title>

<style type="text/css">

	* {
		margin:0;
		padding:0;
	}
	
	header h1{
		text-align: center;
		padding : 20px;
		background-color: #EAEAEA;
	}
	
	nav {
		padding: 20px;
		background-color: #BDBDBD;
	}
	
	nav ul li{
		float:left;
		list-style: none;
		padding: 0 20px;
	}
	
	section {
		padding: 20px;
		background-color: #8C8C8C;
	}
	
	section h1 {
		text-align: center;
	}
	
	section table{
		margin: auto;
	}
	
	footer {
		padding: 20px;
		background-color: #5D5D5D;
		text-align: center;
	}

</style>

	<script type="text/javascript">
	
		function send() {
			
			if(frm.gcode.value == ""){
				alert("그룹코드 입력!");
				frm.gcode.focus();
				return;
			}
			
			if(frm.gname.value == ""){
				alert("그룹이름 입력!");
				frm.gname.focus();
				return;
			}
			
			frm.submit();
			
			
		}
		
		
	</script>

</head>
<body>

	<%@ include file="./include/header.jsp" %>
	
	<section>
		<h1>그룹코드 등록</h1>
		
		<form name="frm" action="InsertGroup" method="post" onsubmit="return false">
		
			<table>
				<tr>
					<th>그룹 코드</th>
					<td><input type="text" name="gcode" value=""></td>
				</tr>
				
				<tr>
					<th>그룹 이름</th>
					<td><input type="text" name="gname" value=""></td>
				</tr>
				
				<tr>
					<td colspan="2" align="center">
						<a href="javascript:send()"><input type="button" value="등록하기"></a>
						<a href="Index"><input type="button" value="홈으로"></a>
					</td>
				</tr>	
			</table>
		
		</form>
	</section>
	
	<%@ include file="./include/footer.jsp" %>

</body>
</html>