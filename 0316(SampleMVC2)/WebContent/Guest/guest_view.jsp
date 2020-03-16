<%@ page contentType="text/html; charset=UTF-8" %>

 <html>
 <head><meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
   <title>방명록 내용 보기</title>
   <link rel="stylesheet" type="text/css" href="/stylesheet.css">
   <style type="text/css">
     td.title { padding:4px; background-color:#e3e9ff }
     td.content { padding:10px; line-height:1.6em; text-align:justify; }
     a.list { text-decoration:none;color:black;font-size:10pt; }
   </style>
 <script language="javascript">
 <!--
 function del_list() {
   var urlString;
    urlString = "guest_delete.jsp" ;
    window.open(urlString, "del_list"," resizable=no,x=150,y=150,width=300,height=170");
 }
 //-->
 
	 function deleteGuest() {
		 var result = confirm("이 게시물을 삭제할까요?");
		 if(result){
			 location.href = "Guest?command=guest_delete&idx=${idx}&page=${page}";
		 }
	}
 
 </script>.
 </head>
 <body topmargin="0" leftmargin="0">
   <table border="0" width="800">
     <tr>
       <td width="20%"  height="500" bgcolor="#ecf1ef" valign="top">

		 <!--  다음에 추가할 부분 -->

	   </td>
       <td width="80%" valign="top">&nbsp;<br>
         <table border="0" width="90%" align="center">
           <tr>
             <td colspan="2"><img src="Guest/img/bullet-01.gif"> 
              <font color="blue" size="3">방 명 록</font><font size="2"> - 글읽기</font></td>
           </tr>
         </table>
       <p>
       <table border="0" width="90%" align="center" cellspacing="0"  style="border-width:1px;border-color:#0066cc;border-style:outset;">
         <tr bgcolor="e3e9ff">
           <td class="title">
             <img src="Guest/img/bullet-04.gif"> <font size="2" face="돋움">
                  ${vo.subject }</font>
           </td>
         </tr>
         <tr>
           <td class="content">
             <p align="right"><font size="2" face="돋움">
              ${vo.name}  / <font size="2" face="돋움">${vo.regdate}/ ${vo.readcnt}번 읽음</font>
             <p>
             ${vo.contents}<p>
           </td>
         </tr>
       </table>

      <!--**** 여기서부터 게시물 내용 아래쪽의 버튼들이 나옵니다. 답변, 수정, 삭제, 목록보기 ****-->
      <p align="center">
      <font size="2">
       <!-- 목록보기 -->
       <a href="Guest?command=guest_list&page=${page}"><img src="Guest/img/list-2.gif" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;
       <a href="Guest?command=guest_modify&idx=${idx}&page=${page}"><img src="Guest/img/edit.gif" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;
       <a href="javascript:deleteGuest()"><img src="Guest/img/del.gif" border="0"></a>&nbsp;&nbsp;&nbsp;&nbsp;
      </font>
    </td>
  </tr>
  </table>
  </body>
  </html>
