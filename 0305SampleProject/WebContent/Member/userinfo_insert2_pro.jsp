<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:if test="${result > 0 }">
	<script type="text/javascript">
		alert("회원가입 완료");
		location.href="index.do";
	</script>
</c:if>
<c:if test="${result == 0 }">
	<script type="text/javascript">
		alert("회원가입 실패");
		location.href="MemberInsertServlet";
	</script>
</c:if>