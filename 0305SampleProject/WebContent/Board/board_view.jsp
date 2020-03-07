<%@ page contentType="text/html; charset=UTF-8" %>

 <html>
 <head><meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
   <title>게시판 내용 보기</title>
   <link rel="stylesheet" type="text/css" href="/stylesheet.css">
   <style type="text/css">
     td.title { padding:4px; background-color:#e3e9ff }
     td.content { padding:10px; line-height:1.6em; text-align:justify; }
     a.list { text-decoration:none;color:black;font-size:10pt; }
   </style>

<script type="text/javascript">

	function deletePost(page, search, key) {
		var result = confirm("해당 게시물을 삭제할까요?")
		var uri = "BoardDeleteServlet?idx=${vo.idx}"
		if(result){
			//게시물을 삭제하고 삭제하기전 페이지로 돌아가기 위함
			if(page != ""){
				uri += "&page=" + page;
			}
			if(search != ""){
				uri += "&search=" + search + "&key=" + key;
			}
			location.href=uri;
		}
	}

</script>
 </head>
 <body topmargin="0" leftmargin="0">
  <%@ include file="../include/topmenu.jsp" %>
   <table border="0" width="800">
     <tr>
       <td width="20%"  height="500" bgcolor="#ecf1ef" valign="top">
		<%@ include file="../include/login_form.jsp" %>
		 <!--  다음에 추가할 부분 -->

	   </td>
       <td width="80%" valign="top">&nbsp;<br>
         <table border="0" width="90%" align="center">
           <tr>
             <td colspan="2"><img src="Board/img/bullet-01.gif"> 
              <font color="blue" size="3">자 유 게 시 판</font><font size="2"> - 글읽기</font></td>
           </tr>
         </table>
       <p>
       <table border="0" width="90%" align="center" cellspacing="0"  style="border-width:1px;border-color:#0066cc;border-style:outset;">
         <tr bgcolor="e3e9ff">
           <td class="title">
             <img src="Board/img/bullet-04.gif"> <font size="2" face="돋움">
                  ${vo.subject }</font>
           </td>
         </tr>
         <tr>
           <td class="content">
             <p align="right"><font size="2" face="돋움">
              <a class="list" href="mailto:ein1027@nate.com">${vo.name }</a> / <font size="2" face="돋움">${vo.regdate } / ${vo.readcnt }번 읽음</font>
             <p>
            ${vo.contents }<p><!--contents의 내용을 <BR>태그로 처리-->
           </td>
         </tr>
       </table>
  
      <!--**** 여기서부터 게시물 내용 아래쪽의 버튼들이 나옵니다. 답변, 수정, 삭제, 목록보기 ****-->
      <p align="center">
	      <font size="2">
	      
	       <!-- 새글쓰기 
	       <c:if test="${!empty user}">
		       <a href=""><img src="Board/img/write.jpg" border="0"></a>&nbsp;&nbsp;
	       </c:if>
	       -->
	       
		   <!-- 답글쓰기
	       <a href="">
	       <img src="Board/img/reply.gif" border="0"></a>&nbsp;&nbsp;
	        -->
	        
		   
		   <c:if test="${user.userid == vo.ip}">
		   		<!-- 수정하기 -->
	       		<a href="BoardModifyServlet?idx=${vo.idx }<c:if test='${!empty page}'>&page=${page}</c:if><c:if test='${!empty search && !empty key}'>&search=${search }&key=${key }</c:if>">
	       		<img src="Board/img/edit.gif" border="0"></a>&nbsp;&nbsp;
	       		
	         	<!-- 삭제하기 -->
	       		<a href="javascript:deletePost(${page}, '${search}', '${key}')"><img src="Board/img/del.gif" border="0"></a>&nbsp;&nbsp;
	       </c:if>
	       
	       <!-- 목록보기 -->
	       <a href="BoardListServlet?<c:if test='${!empty page}'>page=${page}</c:if><c:if test='${!empty search && !empty key}'>&search=${search }&key=${key }</c:if>">
	       <img src="Board/img/list-2.gif" border="0"></a>&nbsp;&nbsp;
	      </font>
      </p>
    </td>
  </tr>
  </table>
  </body>
  </html>

