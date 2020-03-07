<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>회원 삭제</title>
<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
-->
.formbox {
	BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
} 
--->
</STYLE>
<script type="text/javascript">

	function deleteUser() {
		
		//비밀번호 입력되었나, 두개의 값이 동일한가
		if(jform.passwd.value == ""){
			alert("현재 비밀번호를 입력해주세요.");
			jform.passwd.foucs();
			return;
		}else if(jform.passwd.value != jform.repasswd.value) {
			alert("두개의 비밀번호가 서로 일치하지 않습니다.");
			jform.passwd.value="";
			jform.repasswd.value="";
			jform.passwd.foucs();
			return;
		}else{
			var pattern = /^[0-9a-zA-Z]{5,16}$/;
			if(!pattern.test(jform.passwd.value)){
				alert("5~16자 이내의 영문이나 숫자만 가능합니다. ");
				jform.passwd.value="";
				jform.repasswd.value="";
				jform.passwd.foucs();
				return;
			}
		}
		
		var result = confirm("정말로 탈퇴 하실건가요");
		if(result == true){
			jform.submit();
		}else{
			return;
		}
		
		
		
	
	}
	
	

</script>
</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 <%@ include file="../include/topmenu.jsp" %>
 <!-- 탑 메뉴 영역 삽입-->


<table border="0" width="800">
<tr>
       <td width="20%"  height="500" bgcolor="#ecf1ef" valign="top">
		<%@ include file="../include/login_form.jsp" %>
		 <!--  다음에 추가할 부분 -->

	   </td>
  <td width="80%" valign="top">&nbsp;<img src="Member/img/title3.gif" ><br>    
	<form name=jform method=post action="MemberDeleteServlet"">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="Member/img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자 탈퇴&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>현재 비밀번호를 입력후 사용자 탈퇴 버튼을 눌러주세요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="Member/img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="${user.name}" disabled>성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 value="${user.userid}" style="width:120" disabled>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;현재 비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
								<input type=password name=passwd size=8 maxlength=12 style="width:80">
                 					6~12자 이내의 영문이나 숫자만 가능합니다.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;현재 비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=repasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요. </td>
							</tr>
							
							<tr>
               					<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
                				<TD BGCOLOR=WHITE>
                  					<input type=text name=tel size=13 maxlength=13 value="${user.tel}" disabled>
               					</td>
              				</tr>
              				<tr>
                				<TD BGCOLOR="#EFF4F8">&nbsp;E-mail
                					<font color=red>&nbsp;</font>
                				</td>
                				<td bgcolor=WHITE valign=middle>
                  					<input type=text name=email size=30 maxlength=30 value="${user.email}" disabled>
                				</td>
              				</tr>
            			</table>
            			<table cellpadding=0 cellspacing=0 border=0 width=100%>
              				<tr bgcolor=#7AAAD5>
                				<td valign=bottom>
                  					<img src="Member/img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
                				</td>
                				<td align=center></td>
                				<td valign=bottom>
                  					<img src="Member/img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
                				</td>
              				</tr>

              				<tr bgcolor=#ffffff>
                				<td colspan=3 align=center>
                  					<input type="button" onclick="deleteUser()" value="탈퇴">
                  					<input type="button" style="visibility:hidden">
                  					<input type="button" onclick="history.back()" value="취소">
                				</td>
              				</tr>
              				
            			</table> 
          			</td>
        		</tr>
      		</td>
    	</tr>
	</table>
	</form>
	</td>
</tr>
</table>

 <!-- copyright 영역 삽입-->
  

</body>
</html>
