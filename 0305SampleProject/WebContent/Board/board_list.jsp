<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head><title>게시판 읽기</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<style type="text/css">
  a.list {text-decoration:none;color:black;font-size:10pt;}
  a {text-decoration:none;}
</style>

<script type="text/javascript">

	function search() {
		if(frm_search.key.value == ""){
			alert("검색할 값을 입력하세요");
			frm_search.key.focus();
			return;
		}

		frm_search.submit();
			
	}

</script>

</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<%@ include file="../include/topmenu.jsp" %>
<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">

	<%@ include file="../include/login_form.jsp" %>
	<!-- 다음에 추가할 부분 --></td>

	<td width="80%" valign="top">	
		<br>
    <table border="0" cellspacing="1" width="100%" align="center">
      <tr>
        <td colspan="7" align="center" valign="center" height="20">
        <font size="4" face="돋움" color="blue">
        <img src="Board/img/bullet-01.gif"> <b>자 유 게 시 판</b></font></td></tr>
      <tr>
        <td colspan="5" align="right" valign="middle" height="20">
		<font size="2" face="고딕">전체 : <b>${allPostCnt}</b>건 - ${currentPage}/ ${allPage} Pages</font></td></tr>
 	   <tr bgcolor="e3e9ff">
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">번 호</font></td>
 	      <td width="50%" align="center" height="20"><font face="돋움" size="2">제 목</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
 	   </tr>
 	   
<!-- 게시글 객체 부분 -->
	<c:if test="${!empty list }">
	
		<c:forEach var="vo" items="${list}">
		
			<tr onMouseOver="style.backgroundColor='#D1EEEE'"
				onMouseOut="style.backgroundColor=''">
				<td align="center" height="25"><font face="돋움" size="2"
					color="#000000">${postCnt }</font></td>
					
				<td align="left" height="20">&nbsp; <font face="돋움" size="2"color="#000000"> 
					<a class="list" href="BoardViewServlet?idx=${vo.idx }
					<c:if test="${!empty currentPage}">&page=${currentPage}</c:if>
					<c:if test="${!empty search && !empty key}">&search=${search }&key=${key }</c:if>">
					${vo.subject}
					</a>
				</td>
				
				<td align="center" height="20"><font face="돋움" size="2">
						<a class="list" href="mailto:ein1027@nate.com">${vo.name }</a>
				</font></td>
				<td align="center" height="20"><font face="돋움" size="2">${vo.regdate.substring(0,10) }</font></td>
				<td align="center" height="20"><font face="돋움" size="2">
						${vo.readcnt}</font></td>
			</tr>
			
			<c:set var="postCnt" value="${postCnt-1}"></c:set>
		
		</c:forEach>
	
	</c:if>
	
	<c:if test="${empty list }">
	
		<tr onMouseOver="style.backgroundColor='#D1EEEE'"
			onMouseOut="style.backgroundColor=''">

			<td align="center" height="20" colspan="5">&nbsp; <font face="돋움" size="2"
			color="#000000">등록된 게시물이 없습니다.</td>

		</tr>
			
	</c:if>
	
<!-- /게시글 객체 부분 -->

			
			
<!-- 코멘트 부분 
	<tr onMouseOver="style.backgroundColor='#D1EEEE'"
		onMouseOut="style.backgroundColor=''">
		<td align="center" height="25"><font face="돋움" size="2"
			color="#000000">5</font></td>
		<td align="left" height="20">&nbsp; <img
			src="Board/img/reply2.gif"> <font face="돋움" size="2"
			color="#000000"> <a class="list" href="">제목부분입니다2</a></td>
		<td align="center" height="20"><font face="돋움" size="2">
				<a class="list" href="mailto:ein1027@nate.com">나종민</a>
		</font></td>
		<td align="center" height="20"><font face="돋움" size="2">2007-10-22</font></td>
		<td align="center" height="20"><font face="돋움" size="2">
				3</font></td>
	</tr>
-->


					<div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">      
             	${pageButton} 
			  </td>
			 </tr>
		</table>

	<table width="600">
		<tr>
			<td width="25%"> &nbsp;</td>
			<td width="50%" align="center">
				<form name="frm_search" action="BoardListServlet" method="post">
					<table>
					<!-- 검색어를 이용하여 글제목, 작성자, 글내용 중에 하나를 입력 받아 처리하기 위한 부분 -->
						
						<tr>
							<td>
								<select name="search2">
									<option value="subject" <c:if test="${search== 'subject'}">selected</c:if>>글제목</option>
									<option value="name" <c:if test="${search== 'name'}">selected</c:if>>작성자</option>
									<option value="contents" <c:if test="${search== 'contents'}">selected</c:if>>글내용</option>
								</select>
							</td>
							<td> <input type="text" size=20 name="key" value="${key}"></td>
							<td> <a href="javascript:search()"><img src="Board/img/search2.gif" border="0"></a></td>
						</tr>
					</table>
				</form>
			</td>
			<td width="25%" align="right">
			<a href="BoardWriteServlet"><img src="Board/img/write.gif" border="0"></a>
			</td>
		</tr>
	</table>
</body>
</html>

