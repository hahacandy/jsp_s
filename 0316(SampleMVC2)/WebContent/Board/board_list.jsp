<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head><title>게시판 읽기</title>
<link rel="stylesheet" type="text/css" href="/stylesheet.css">
<style type="text/css">
  a.page {text-decoration:none;color:black;font-size:13pt;}
</style>

<script type="text/javascript">

	function search() {
		if(frm.key.value == ""){
			alert("검색어를 입력하세요.");
			return;
		}
		frm.submit();
	}
	
	function enterView2(idx){
		location.href="/Board?command=board_view&idx="+idx+"&page=${page}&search=${search}&key="+encodeURI("${key}");
	}

</script>

</head>
<body bgcolor="#FFFFFF" topmargin="0" leftmargin="0">
<table border="0" width="800">
  <tr>
    <td width="20%" height="500" valign="top" bgcolor="#ecf1ef">

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
		<font size="2" face="고딕">전체 : <b>${allCnt}</b>건 - ${currentPage} / ${allPage} Pages</font></td></tr>
 	   <tr bgcolor="e3e9ff">
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">번 호</font></td>
 	      <td width="50%" align="center" height="20"><font face="돋움" size="2">제 목</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">글쓴이</font></td>
 	      <td width="15%" align="center" height="20"><font face="돋움" size="2">작성일</font></td>
 	      <td width="10%" align="center" height="20"><font face="돋움" size="2">조회수</font></td>
 	   </tr>

		<c:forEach var="vo" items="${list}">
		
			<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
	        	<td align="center" height="25">
	        		<font face="돋움" size="2" color="#000000">${currentCnt}</font>
	        	</td>
	        	
				<td align="left" height="20">&nbsp;
					<font face="돋움" size="2" color="#000000">
					
						<c:if test="${empty search and empty key}">
							<a class="list" href="/Board?command=board_view&idx=${vo.idx}&page=${page}">${vo.subject}</a>
						</c:if>
						
						<c:if test="${!empty search and !empty key}">
							<a class="list" href="javascript:enterView2(${vo.idx})">${vo.subject}</a>
						</c:if>
						
					</font>
				</td>
				
				<td align="center" height="20">
					<font face="돋움" size="2">
						<a class="list" href="mailto:ein1027@nate.com">${vo.name}</a>
					</font>
				</td>
					
				<td align="center" height="20">
					<font face="돋움" size="2">${vo.regdate}</font>
				</td>
					
				<td align="center" height="20">
					<font face="돋움" size="2">${vo.readcnt}</font>
				</td>
				
				<c:set var="currentCnt" value="${currentCnt-1}"></c:set>
				
			</tr>
		
		</c:forEach>
		
		<c:if test="${empty list}">
			<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
	        	<td align="center" height="25" colspan="5">
	        		<font face="돋움" size="2" color="#000000">등록된 자료가 없습니다.</font>
	        	</td>
			</tr>
		</c:if>

		
			
		<!-- 
		<tr onMouseOver="style.backgroundColor='#D1EEEE'" onMouseOut="style.backgroundColor=''">
        <td align="center" height="25">
        <font face="돋움" size="2" color="#000000">5</font></td>
			<td align="left" height="20">&nbsp;
				<img src="Board/img/reply2.gif">
				<font face="돋움" size="2" color="#000000">
				<a class="list" href="">제목부분입니다</a></td>
					<td align="center" height="20"><font face="돋움" size="2">
					<a class="list" href="mailto:ein1027@nate.com">나종민</a></font></td>
				<td align="center" height="20"><font face="돋움" size="2">2007-10-22</font></td>
				<td align="center" height="20"><font face="돋움" size="2">
				3</font></td>
			</tr>
		 -->


	 <div align="center">
        <table width="700" border="0" cellspacing="0" cellpadding="5">
          <tr>&nbsp;</tr><tr>
             <td colspan="5">        
                <div align="center">${pageButton}</div>
			  </td>
			 </tr>
		</table>

	<table width="600">
		<tr>
			<td width="25%"> &nbsp;</td>
			<td width="50%" align="center">
				<form action="/Board" name="frm" method="get">	
					<input type="hidden" name="command" value="board_list">
					<input type="hidden" name="page" value="1">
					<table>
						
						<!-- 검색어를 이용하여 글제목, 작성자, 글내용 중에 하나를 입력 받아 처리하기 위한 부분 -->
							<tr>
								<td>
									<select name="search">
										<option value="subject" <c:if test="${search=='subject'}">selected</c:if>>글제목</option>
										<option value="name" <c:if test="${search=='name'}">selected</c:if>>작성자</option>
										<option value="contents" <c:if test="${search=='contents'}">selected</c:if>>글내용</option>
									</select>
								</td>
								<td> <input type="text" size=20 name="key" value="${key}"></td>
								<td> <a href="javascript:search()"><img src="Board/img/search2.gif" border="0"></a></td>
							</tr>
						
					</table>
				</form>
			</td>
			<td width="25%" align="right">
			<a href="/Board?command=board_write"><img src="Board/img/write.gif" border="0"></a>
			</td>
		</tr>
	</table>
</body>
</html>

