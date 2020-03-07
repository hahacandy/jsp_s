<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
 <head><title>Web Programming Test</title>
 <link rel="stylesheet" type="text/css" href="/stylesheet.css">
 <style type="text/css">
   .menu, td {font-size:9pt;}
 </style>
 
 <script type="text/javascript">
 
	function logout2() {
		logout_frm2.action = "Member/logout.jsp";
		logout_frm2.returnUri.value = window.location.href;
		
		logout_frm2.submit();
		
	}
	
 </script>
 
</head>
 <body text="black" link="blue" vlink="purple" alink="red" bgcolor="white" topmargin="0" leftmargin="0">
 <table border="0" cellspacing="2" bgcolor="#ecf1ef" width="900" bordercolordark="#ffffff" bordercolorlight="#000000">
   <tr>
     <td width="20%" rowspan="4" valign="bottom" bgcolor="#000000">
       <a href="index.do"><img src="include/img/ceo.jpg" width="158" height="83" border="0"></a>
       <font style="font-size:9pt;color:white;font-family:돋움"><b>Total 136,489
       <font color="yellow"></font>
       | Now 178
       <font color="yellow"></font>
       </b></font>
     </td>
     <td bgcolor="#9966ff" width="10%" height="25" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">

		<c:if test="${empty user}">
			<p align="center"><font color="white" size="2"><b><a href="LoginServlet" class="white">로그인</A></b></font></p>
		</c:if>
     	<c:if test="${!empty user}">
     		<p align="center"><font color="white" size="2"><b><a href="javascript:logout2()" class="white">로그아웃</A></b></font></p>
     		<form name="logout_frm2" method="post">
				<input type="hidden" name="returnUri" value="">
			</form>
     	</c:if>

     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     
     	<c:if test="${empty user}">
			 <p align="center"><font color="white" size="2"><b><a href="MemberInsertServlet" class="white">회원가입</a></b></font></p>
		</c:if>
		
     	<c:if test="${!empty user}">
     		<p align="center"><font color="white" size="2"><b><a href="MemberModifyServlet" class="white">정보수정</a></b></font></p>
     	</c:if>

     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b>공지사항</b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="BoardListServlet">게시판</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="" class="white">자료실</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b><a href="GuestListServlet" class="white">방명록</a></b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b>겔러리</b></font></p>
     </td>
     <td bgcolor="#9966ff" width="10%" onmouseover="style.backgroundColor='#2772D3'" onmouseout="style.backgroundColor=''">
     <p align="center"><font color="white" size="2"><b>관리자</b></font></p>
     </td>
   </tr>                   
   <tr>
     <td colspan="8">
     <p><img src="include/img/bar-01.gif" width="100%" height="1" border="0"></p>
     </td>
   </tr>
   <tr>
     <td colspan="8"><p><img src="include/img/bar-01.gif" width="100%" height="1" border="0"></p></td>
   </tr>
   <tr>
     <td><p><b><span style="font-size:15pt;">&nbsp;Search</span></b></p></td>
     <form action="" method="post">
     <td colspan="5">
     <p>&nbsp;
       <Select name="genre" size="1">
         <option value="종합">공지사항
         <option value="종합">게시판
         <option value="종합">방명록
         <option value="종합">자료실
       </Select>
       <Select name="item" size="1">
         <option value="name">작성자
         <option value="subject">제목
         <option value="contents">제목
       </Select>
         <input type="text" name="keyword" size="12">
         <input type="image" src="include/img/search2.gif">
     </td>
     </form>
   </tr>
 </table>  
 </body>
 </html>
