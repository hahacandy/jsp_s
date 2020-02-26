<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="db.jsp"%>

<%
	int idx = Integer.valueOf(request.getParameter("idx"));
	String pass = request.getParameter("pass");
	String email = request.getParameter("email");
	String subject = request.getParameter("subject");
	String contents = request.getParameter("contents");
	

	//비밀번호가 맞으면 게시물 수정, 틀리면 틀리다고 말함
	int row = 0;
	String sql = "UPDATE tbl_board SET email=?, subject=?, contents=? WHERE idx=? AND pass=?";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, email);
	pstmt.setString(2, subject);
	pstmt.setString(3, contents);
	pstmt.setInt(4, idx);
	pstmt.setString(5, pass);
	row = pstmt.executeUpdate();
	
	if(row > 0){
%>
		<script>
		alert("수정 완료")
		location.href="board_view.jsp?idx=<%=idx%>";
		</script>
<%	}else{%>
		<script>
		alert("비밀번호가 맞지 않아서 수정 실패")
		location.href="board_view.jsp?idx=<%=idx%>";
		</script>
<%	}%>