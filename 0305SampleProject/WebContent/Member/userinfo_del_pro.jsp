<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("회원정보 삭제 완료");
		location.href="Member/logout.jsp";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("비밀번호가 틀렸습니다.");
		location.href="MemberDeleteServlet";
	</script>
</c:if>