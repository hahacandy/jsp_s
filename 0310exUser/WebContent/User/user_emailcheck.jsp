<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인증코드 입력</title>

<script type="text/javascript">

	function send() {
		if(frm.code.value == ""){
			alert("인증코드를 입력해주세요.");
			return;
		}
		
		if(frm.code.value != "${code}"){
			alert("인증코드가 맞지않습니다.");
			return;
		}
		opener.frm_email.email_ok.value = "true";
		self.close();
	}

</script>

</head>
<body>

<form action="" name="frm" method="post">

	<h5>입력한 메일주소로 인증번호를 보냈습니다.<br/>인증코드를 확인하고 인증코드를 입력해주세요.</h5>
	<label for="code">인증코드 입력: </label>
	<input type="text" id="code" name="code" size="6">
	<input type="button" value="확인" onclick="send()">

</form>

</body>
</html>