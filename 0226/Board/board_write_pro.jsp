<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="db.jsp" %>

<%
	
	String pass = request.getParameter("pass");
	String name = request.getParameter("name");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");
	String ip = request.getRemoteAddr();
	
	String sql = "INSERT INTO tbl_board(idx, pass, name, email, subject, contents, ip) VALUES(tbl_board_seq_idx.NEXTVAL, ?, ?, ?, ?, ?, ?)";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, pass);
	pstmt.setString(2, name);
	pstmt.setString(3, email);
	pstmt.setString(4, subject);
	pstmt.setString(5, contents);
	pstmt.setString(6, ip);
	
	int row = pstmt.executeUpdate();
	
	if(row > 0){
%>
	<script>
		alert("등록 완료")
		location.href="board_list.jsp";
	</script>
<%}else{%>
	<script>
		alert("등록 실패")
		location.href="board_list.jsp";
		</script>
<%}%>