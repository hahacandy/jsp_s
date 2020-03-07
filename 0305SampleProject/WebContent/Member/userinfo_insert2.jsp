<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>회원등록</title>
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

	function checkId() {
		window.open("MemberIdCheckServlet", "아이디 중복 확인", "width=300 height=250");
	}
	
	function resetForm() {
		jform.reset();
	}

	function send() {
		
		//이름 입력칸
		if(jform.name.value == ""){
			alert("이름을 입력해주세요.");
			jform.name.foucs();
		}else{ 
			var pattern = /(^[가-힣]{2,6}$|^[a-zA-Z]{3,20}$)/;
			if(!pattern.test(jform.name.value)){
				alert("이름은 한글(2~6자) 또는 영어(3~20자) 로 해주세요.");
				jform.name.value=="";
				jform.name.foucs();
			}
		}
		
		//아이디 입력되었나 확인
		if(jform.userid.value == ""){
			alert("아이디를 입력해주세요.");
			jform.userid.foucs();
		}
		
		//비밀번호 입력되었나, 두개의 값이 동일한가
		if(jform.passwd.value == ""){
			alert("비밀번호를 입력해주세요.");
			jform.passwd.foucs();
		}else if(jform.passwd.value != jform.repasswd.value) {
			alert("두개의 비밀번호가 서로 일치하지 않습니다.");
			jform.passwd.value="";
			jform.repasswd.value="";
			jform.passwd.foucs();
		}else{
			var pattern = /^[0-9a-zA-Z]{5,16}$/;
			if(!pattern.test(jform.passwd.value)){
				alert("5~16자 이내의 영문이나 숫자만 가능합니다. ");
				jform.passwd.value="";
				jform.repasswd.value="";
				jform.passwd.foucs();
			}
		}
		
		//전화번호 입력되었나, 제대로 입력되었나 확인
		if(jform.tel.value == ""){
			alert("전화번호를 입력해주세요.");
			jform.tel.foucs();
		}else{ 
			var pattern = /(^010-[0-9]{4}-[0-9]{4}$)/;
			if(!pattern.test(jform.tel.value)){
				alert("전화번호는 010-xxxx-xxxx 형식으로 입력해주세요.");
				jform.tel.value=="";
				jform.tel.foucs();
			}
		}
		
		
		//이메일 입력되었나, 제대로 입력되엇나 확인
		if(jform.email.value == ""){
			alert("이메일을 입력해주세요.");
			jform.email.foucs();
		}else{ 
			var pattern = /(^[a-zA-Z0-9]{4,30}@[a-zA-Z0-9]{1,10}\.[a-zA-Z0-9]{1,10}$)/;
			if(!pattern.test(jform.email.value)){
				alert("이메일은 xxxx@xxx.xxx 형식으로 입력해주세요.");
				jform.email.value=="";
				jform.email.foucs();
			}
		}
		
		
		
		jform.submit();
	}
	
	

</script>
</head>

<body bgcolor="#FFFFFF" LEFTMARGIN=0  TOPMARGIN=0 >
 <%@ include file="../include/topmenu.jsp" %>
 <!-- 탑 메뉴 영역 삽입-->


<table border="0" width="800">
<tr>
  <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">
	<%@ include file="../include/login_form.jsp" %>
	<!--로그인 영역 삽입-->

  </td>
  <td width="80%" valign="top">&nbsp;<img src="Member/img/title1.gif" ><br>    
	<form name=jform method=post action="MemberInsertServlet"">
	<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
		<tr><td align=center><br>                            
			<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>       
				<tr>
					<td bgcolor="#7AAAD5">            
						<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left BORDER="0" HSPACE="0" VSPACE="0"><img src="Member/img/u_b02.gif"></td>
								<td align=center bgcolor="#7AAAD5"><FONT COLOR="#FFFFFF"><b>사용자등록&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></FONT></td>
								<td align=right BORDER="0" HSPACE="0" VSPACE="0"><img src="Member/img/u_b03.gif"></td>
							</tr>
						</table>
						<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>&nbsp;회원 성명<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<input type=text name=name size=16 maxlength=20 value="">성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;회원 ID<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
									<table cellspacing=0 cellpadding=0>
										<tr>
											<td align=absmiddle>
												<input type=text name=userid size=12 maxlength=16 value="" style="width:120" readonly>
											</td>
											<td>
                  								<a href="javascript:checkId()"><img src="Member/img/u_bt01.gif" hspace=2 border=0 name=img1  align=absmiddle></a>
                   								5~16자 이내의 영문이나 숫자만 가능합니다.
                  							</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE>
								<input type=password name=passwd size=8 maxlength=12 style="width:80">
                 					6~12자 이내의 영문이나 숫자만 가능합니다.
								</td>
							</tr>
							<tr>
								<TD BGCOLOR="#EFF4F8">&nbsp;비밀번호확인<font color=red>&nbsp;*</font></td>
								<TD BGCOLOR=WHITE><input type=password name=repasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요. </td>
							</tr>
							
							<tr>
               					<TD BGCOLOR="#EFF4F8">&nbsp;전화번호<font color=red>&nbsp;*</font></td>
                				<TD BGCOLOR=WHITE>
                  					<input type=text name=tel size=13 maxlength=13 value="">
               					</td>
              				</tr>
              				<tr>
                				<TD BGCOLOR="#EFF4F8">&nbsp;E-mail
                					<font color=red>&nbsp;</font>
                				</td>
                				<td bgcolor=WHITE valign=middle>
                  					<input type=text name=email size=30 maxlength=30 value="">
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
                  					<a href="javascript:send()"><img src="Member/img/u_bt06.gif" vspace=3 border=0 name=img3></a>
                  					<a href="javascript:resetForm()"><img src="Member/img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4></a>
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
