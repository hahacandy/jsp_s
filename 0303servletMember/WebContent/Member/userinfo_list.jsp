<%@page import="model.MemberVO"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8" %>

<%
	

	List<MemberVO> list = (List)request.getAttribute("list");

%>

<html>
<head>
<title>회원목록 보여주기</title>

<STYLE TYPE="text/css">
<!--
body { font-family: 돋움, Verdana; font-size: 9pt}
td   { font-family: 돋움, Verdana; font-size: 9pt; text-decoration: none; color: #000000} 
--->
</STYLE>

<script type="text/javascript">

	function moveRegi() {
		location.href("userinfo_insert");
	}
	
	function changeOrder() {
		var search = "${search}";
		var order = search_gubun.value;
		
		if(search.length == 0){
			if(order == "이름")
				location.href("userinfo_list?order=name");
			else if(order == "주소")
				location.href("userinfo_list?order=addr1");
		}else{
			if(order == "이름")
				location.href("userinfo_list?order=name&search=${search}");
			else if(order == "주소")
				location.href("userinfo_list?order=addr1&search=${search}");
		}
		
	}
	
	function search() {
		var search = search_name.value;
		if(search.length > 0){
			location.href("userinfo_list?search="+encodeURIComponent(search));
		}else{
			alert("검색내용을 입력하세요.")
		}
	}
	
</script>

</head>
<body>
<table width="550" border="1" cellspacing="0" cellpadding="2" bordercolorlight="#173E7C" bordercolordark="white">
  <tr>
    <td width=50 align=center>번호</td>
    <td width=50 align=center>ID</td>
    <td width=80 align=center>이름</td>
    <td width=100 align=center>전화번호</td>
    <td width=100 align=center>등록일자</td>
    <td width=100 align=center>최근접속일</td>
    
  </tr>
  
  <%
  MemberVO memberVO = null;
  int cnt = list.size();
  for(int i=0; i<list.size(); i++){ 
 	 memberVO = list.get(i);
  %>
  	
   <tr>
      <td align=center><%=cnt-- %></td>
      <td align=center><%=memberVO.getUserid() %></td>
      <td align=center><%=memberVO.getName() %></td>
      <td align=center><%=memberVO.getTel() %></td>
      <td align=center><%=memberVO.getFirst_time() %></td>
      <td align=center><%=memberVO.getLast_time() %></td>
   </tr>
   
   <%}
  
  	if(list.size() == 0){
   %>
   <tr>
   		<td colspan="6" align="center">등록된 회원 정보가 없습니다.</td>
   </tr>
   
   <%} %>

</table>
<table width=550>
  <tr>
    <td>
      <select name="search_gubun" onchange="changeOrder()">
        <option ${selected[0]}>이름 </option>
        <option ${selected[1]}>주소 </option>
        
    </td>
    
    <td>  찾는이름:
          <input type="text" name="search_name" size=10> &nbsp;
          <a href="javascript:search()">[조회]</a>
     </td>
     
   </tr>
  <tr>
    <td>
    </td>
    <td></td>
   </tr>
   
 	 <tr>
 	 <%
 	 if(session.getAttribute("userid") == null){
 	 %>
    	<td><a href="userlogin_form">로그인 페이지 이동</a></td>
    <%}else{ %>
    	<td><a href="userinfo_modify"><%=session.getAttribute("userid") %></a></td>
    	<td><a href="userLogout">로그아웃</a></td>
    <%} %>
    	<td><a href="javascript:moveRegi()">회원가입페이지 이동</a></td>
   	 </tr>
   	 
</table>    
</body>
</html>
