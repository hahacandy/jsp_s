<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("회원정보 수정 완료");
		location.href="index.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("회원 정보 수정 실패");
		location.href="MemberModifyServlet";
	</script>
</c:if>