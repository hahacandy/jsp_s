<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="db.jsp"%>

<%
	int idx = Integer.valueOf(request.getParameter("idx"));
	String pass = request.getParameter("pass");


	//비밀번호가 맞으면 게시물 삭제, 틀리면 틀리다고 말함
	int row = 0;
	String sql = "DELETE FROM tbl_board WHERE idx=? AND pass=?";
	
	pstmt = conn.prepareStatement(sql);
	pstmt.setInt(1, idx);
	pstmt.setString(2, pass);
	row = pstmt.executeUpdate();

	
	if(row > 0){
%>
		<script>
		alert("삭제 완료")
		location.href="board_list.jsp";
		</script>
<%	}else{%>
		<script>
		alert("비밀번호 맞지 않아서 삭제 실패")
		location.href="board_view.jsp?idx=<%=idx%>";
		</script>
<%	}%>