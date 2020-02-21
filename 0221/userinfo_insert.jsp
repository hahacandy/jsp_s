<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
<title>회원등록</title>
<style type="text/css">
		<!--
		body { font-family: 돋움, Verdana; font-size: 9pt}
		td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000; BACKGROUND-POSITION: left top; BACKGROUND-REPEAT: no-repeat;}
		-->
		.formbox {
			BACKGROUND-COLOR: #F0F0F0; FONT-FAMILY: "Verdana", "Arial", "Helvetica", "돋움"; FONT-SIZE:9pt
		}
</style>
<script>
	function send() {

		name = form_name.name.value;
		userid = form_name.userid.value;
		passwd = form_name.passwd.value;
		repasswd = form_name.repasswd.value;
		gubuns = form_name.gubun;
		tel = form_name.tel.value;
		email = form_name.email.value;
		fas = form_name.fa;
		job = form_name.job.value;
		intro = form_name.intro.value;

		//이름 입력 확인
		if(name.length == 0){
			alert("이름을 입력해주세요.");
			form_name.name.focus();
			return;
		}

		//회원id 5~16자 영문이나 숫자
		useridPattern = /^[A-Za-z0-9]{5,16}/;
		if(userid.length == 0){
			alert("ID를 입력해주세요.");
			form_name.userid.focus();
			return;
		}
		else if(!useridPattern.test(userid)){
			alert("ID는 5~16자 이내의 영문이나 숫자만 가능합니다.");
			form_name.userid.value = null;
			form_name.userid.focus();
			return;
		}

		//비밀번호 6~12자 이내 및 서로 같은지 확인
		passwdPattern = /^[A-Za-z0-9]{6,12}/;
		if(passwd.length == 0){
			alert("비밀번호를 입력해주세요.");
			form_name.passwd.focus();
			return;
		}
		else if(!passwdPattern.test(passwd)){
			alert("비밀번호는 6~12자 이내의 영문이나 숫자만 가능합니다.");
			form_name.passwd.focus();
			form_name.passwd.value = null;
			form_name.repasswd.value = null;
			return;
		}else if(passwd != repasswd){
			alert("비밀번호가 서로 일치하지 않습니다.");
			form_name.passwd.focus();
			form_name.passwd.value = null;
			form_name.repasswd.value = null;
			return;
		}

		// 주소구분 선택확인
		for(i=0,flag=1; i<gubuns.length; i++){
			if(gubuns[i].checked)
				flag=0;
		}
		if(flag){
			alert("주소구분을 선택해주세요.");
			return;
		}

		//전화번호 입력, 형식 확인
		telPattern = /010-[0-9]{4}-[0-9]{4}/
		if(tel.length == 0){
			alert("전화번호를 입력해주세요.");
			form_name.tel.focus();
			return;
		}else if(!telPattern.test(tel)){
			alert("전화번호를 다시 입력해주세요.");
			form_name.tel.value = null;
			form_name.tel.focus();
			return;
		}

		// 이메일 입력확인, 형식확인
		emailPattern = /[a-zA-Z0-9]*@[a-zA-Z0-9]*\.[a-zA-Z0-9]{2,3}/;
		if(email.length == 0){
			alert("이메일 값을 입력해주세요.");
			form_name.email.focus();
			return;
		}else if(!emailPattern.test(email)){
			alert("이메일 형식이 올바르지 않습니다.");
			form_name.email.focus();
			form_name.email.value = null;
			return;
		}

		//관심분야 체크 확인
		flag=1;
		for(i=0; i<fas.length; i++){
			if(fas[i].checked)
				flag=0;
		}
		if(flag){
			alert("관심분야를 체크해주세요.");
			return;
		}

		//직업 선택 확인
		if(job.length == 0){
			alert("직업을 선택해주세요.");
			return;
		}

		//자기소개 입력확인
		if(intro.length == 0){
			alert("자기소개를 입력해주세요.");
			form_name.intro.focus();
			return;
		}

		alert("링크 스타트!");
		form_name.action="userinfo_insert_pro.jsp";
		form_name.submit();
	}
	</script>
</head>
<body bgcolor="#FFFFFF" leftmargin=0 topmargin=0>
<!-- 탑 메뉴 영역 삽입-->
<table border="0" width="800">
<tr>
	<td width="20%" bgcolor="#ecf1ef" valign="top" style="padding-left:0;">
		<!--로그인 영역 삽입-->
	</td>
	<td width="80%" valign="top">
		 &nbsp;<img src="./img/title1.gif"><br>
		<form name=form_name method=post>
			<table border=0 cellpadding=0 cellspacing=0 border=0 width=730 valign=top>
			<tr>
				<td align=center>
					<br>
					<table cellpadding=0 cellspacing=0 border=0 width=650 align=center>
					<tr>
						<td bgcolor="#7AAAD5">
							<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td align=left border="0" hspace="0" vspace="0">
									<img src="./img/u_b02.gif">
								</td>
								<td align=center bgcolor="#7AAAD5">
									<font color="#FFFFFF"><b>사용자등록&nbsp;</b><font color=black>(</font><font color=red>&nbsp;*&nbsp;</font><font color=black>표시항목은 반드시 입력하십시요.)</font></font>
								</td>
								<td align=right border="0" hspace="0" vspace="0">
									<img src="./img/u_b03.gif">
								</td>
							</tr>
							</table>
							<table cellpadding=3 cellspacing=1 border=0 width=100%>
							<tr>
								<td width=110 bgcolor=#EFF4F8>
									 &nbsp;회원 성명<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE>
									<input type=text name=name size=16 maxlength=20 value="">성명은 빈칸없이 입력하세요.
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;회원 ID<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE>
									<table cellspacing=0 cellpadding=0>
									<tr>
										<td align=absmiddle>
											<input type=text name=userid size=12 maxlength=16 value="" style="width:120">
										</td>
										<td>
											<img src="./img/u_bt01.gif" hspace=2 border=0 name=img1 align=absmiddle>
											5~16자 이내의 영문이나 숫자만 가능합니다.
										</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;비밀번호<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE>
									<input type=password name=passwd size=8 maxlength=12 style="width:80">
									6~12자 이내의 영문이나 숫자만 가능합니다.
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;비밀번호확인<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE>
									<input type=password name=repasswd size=8 maxlength=12 value="" style="width:80">비밀번호 확인을 위해서 비밀번호를 한번 더 입력해주세요.
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;주소구분<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<input type=radio name=gubun value="직장">직장&nbsp;&nbsp; <input type=radio name=gubun value="자택">자택
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;우편번호<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<table cellspacing=0 cellpadding=0>
									<tr>
										<td>
											<input type=text name=zip size=6 maxlength=6>
										</td>
										<td>
											<img src="./img/u_bt07.gif" hspace=2 border=0 name=img2 align=absmiddle>
										</td>
									</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;주소<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<input type=text name=addr1 size=50 maxlength=100 value="">
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;나머지 주소<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<input type=text name=addr2 size=50 maxlength=100 value="">
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;전화번호<font color=red>&nbsp;*</font>
								</td>
								<td bgcolor=WHITE>
									<input type=text name=tel size=13 maxlength=13 value="">
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;E-mail <font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type=text name=email size=30 maxlength=30 value="">
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;관심분야 <font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE valign=middle>
									<input type="checkbox" name="fa" value="건강">건강 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="fa" value="문화예술">문화예술 &nbsp;&nbsp; <input type="checkbox" name="fa" value="경제">경제 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="fa" value="연예오락">연예오락 &nbsp; <input type="checkbox" name="fa" value="뉴스">뉴스 <br>
									<input type="checkbox" name="fa" value="여행레져">여행레져 &nbsp;&nbsp; <input type="checkbox" name="fa" value="생활">생활 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="fa" value="스포츠">스포츠 &nbsp;&nbsp; <input type="checkbox" name="fa" value="교육">교육 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input type="checkbox" name="fa" value="컴퓨터">컴퓨터 &nbsp;&nbsp; <input type="checkbox" name="fa" value="학문">학문 &nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;직업<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<select name=job class="formbox">
										<option value="">선택하세요 ---
										<option value="회사원">회사원
										<option value="연구전문직">연구전문직
										<option value="교수학생">교수학생
										<option value="일반자영업">일반자영업
										<option value="공무원">공무원
										<option value="의료인">의료인
										<option value="법조인">법조인
										<option value="종교,언론,에술인">종교.언론/예술인
										<option value="농,축,수산,광업인">농/축/수산/광업인
										<option value="주부">주부
										<option value="무직">무직
										<option value="기타">기타
									</select>
								</td>
							</tr>
							<tr>
								<td bgcolor="#EFF4F8">
									 &nbsp;자기소개<font color=red>&nbsp;</font>
								</td>
								<td bgcolor=WHITE>
									<textarea cols=65 rows=5 name="intro"></textarea>
								</td>
							</tr>
							</table>
							<table cellpadding=0 cellspacing=0 border=0 width=100%>
							<tr bgcolor=#7AAAD5>
								<td valign=bottom>
									<img src="./img/u_b04.gif" align=left hspace=0 vspace=0 border=0>
								</td>
								<td align=center>
								</td>
								<td valign=bottom>
									<img src="./img/u_b05.gif" align=right hspace=0 vspace=0 border=0>
								</td>
							</tr>
							<tr bgcolor=#ffffff>
								<td colspan=3 align=center>
									<a href="javascript:send()"><img src="./img/u_bt06.gif" vspace=3 border=0 name=img3></a>
									<img src="./img/u_bt05.gif" border=0 hspace=10 vspace=3 name=img4>
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
