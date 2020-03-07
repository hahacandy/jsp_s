<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Login</title>
	
	
	<script type="text/javascript">
		function send() {
			if(login_form.userid.value == ""){
				alert("아이디를 입력해주세요");
				login_form.userid.focus();
				return;
			}
			
			if(login_form.passwd.value == ""){
				alert("비밀번호를 입력해주세요");
				login_form.passwd.focus();
				return;
			}
			
			login_form.returnUri.value = window.location.href;
			
			login_form.submit();
		}
		
		function logout() {
			logout_frm.action = "Member/logout.jsp";
			logout_frm.returnUri.value = window.location.href;
			
			logout_frm.submit();
			
		}
	</script>

</head>

 <body>
 <!-- 로그인 -->
 <c:if test="${empty user }">
	<form name="login_form" action="LoginServlet" method="post">
		<input type="hidden" name="returnUri" value="">
		<input type="hidden" name="sublogin" value="true"> <!-- LoginServlet에서 sub가 있다면 서브창에서 로그인을 한것 -->
		<table width="100%" height="120" border="0">

			<tr>
				<td colspan="2" bgcolor="#6FA0E" height="20" align="center"><fontsize="2" color="white"><b>Member Login</b></font></td>
			</tr>
			
			<tr>
				<td><font size="2">아 이 디</font></td>
				<td><input type="text" size="10" name="userid"></td>
			</tr>
			
			<tr>
				<td><font size="2">비밀번호</font></td>
				<td><input type="password" size="10" name="passwd"></td>
			</tr>
			
			<tr>
			
				<td>
					<a href="javascript:send()"><img src="include/img/login1.gif" border="0"></a>
				</td>
				
				<td>
					<a href="MemberInsertServlet"><img src="include/img/regist.gif" border="0"></a>
				</td>
				
			</tr>

		</table>
	</form>
</c:if>

<!-- 회원정보창 -->
 <c:if test="${!empty user }">
	<table width="100%" height="120" border="0">
		<tr>
			<td bgcolor="#6FA0E" align="center" height="20"><font size="2"
				color="white">${user.name } 님!</font></td>
		</tr>
		<tr>
			<td align="center"><font size="2"> 
				<a href="javascript:logout()">로그오프</a><br><br> 
				<a href="MemberModifyServlet">회원정보수정</a><br><br>  
				<a href="MemberDeleteServlet">회원탈퇴</a>

			</font></td>
		</tr>
	</table>
	<form name="logout_frm" method="post">
		<input type="hidden" name="returnUri" value="">
	</form>
</c:if>
 
 <!--  관리자전용 
 <table width="100%" height="120" border="0">
   <tr>
     <td bgcolor="#6FA0E" align="center" height="20">
       <font size="2" color="white">홍길동 님!</font>
     </td>
   </tr>
   <tr>
     <td align="center">
       <font size="2">
       <a href="adminpage.jsp">관리자 페이지</a><br>
       <a href="logout.jsp">로그오프</a><br>
       <a href="userinfo_view.jsp">회원정보수정</a><br>
       </font>
     </td>
   </tr>
 </table>
-->
 
 </body>
 </html>
