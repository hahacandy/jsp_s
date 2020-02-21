<%@ page contentType="text/html; charset=UTF-8" %>
<%
request.setCharacterEncoding("utf-8");
String memberName = request.getParameter("name");

String memberID = request.getParameter("userid");

String memberPass = request.getParameter("passwd");
String memberPass2 = request.getParameter("repasswd");

String memberGubun = request.getParameter("gubun");

String memberZip = request.getParameter("zip");
String memberAddr1 = request.getParameter("addr1");
String memberAddr2 = request.getParameter("addr2");

String memberPhone = request.getParameter("tel");

String memberEmail = request.getParameter("email");

String[] memberFa = request.getParameterValues("fa");


%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
	<head>
		<meta charset="utf-8">
			<title></title>
		</head>
		<body>
			이름: <%= memberName %> <br />
			아이디: <%= memberID %> <br />
			비밀번호: <%= memberPass %> <br />
			비밀번호 확인: <%= memberPass2 %> <br />
			구분: <%= memberGubun %> <br />
			우편번호: <%= memberZip %> <br />
			주소: <%= memberAddr1 %> <br />
			나머지 주소: <%= memberAddr2 %> <br />
			전화번호: <%= memberPhone %> <br />
			이메일: <%= memberEmail %><br />

			취미: <%
			for(int i=0; i<memberFa.length; i++){
				out.print(memberFa[i]);
				if(i!=memberFa.length-1)
					out.print(", ");
			} %>

		</body>
	</html>
