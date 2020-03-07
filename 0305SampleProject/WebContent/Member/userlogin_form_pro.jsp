<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${login == 'ok'&& empty sublogin}">
	<script type="text/javascript">
		//alert("로그인 성공!");
		location.href="index.do";
	</script>
</c:if>

<c:if test="${login == 'ok'&& !empty sublogin}">
	<script type="text/javascript">
		//alert("로그인 성공!");
		location.href="${returnUri}";
	</script>
</c:if>

<c:if test="${login == 'no'}">
	<script type="text/javascript">
		alert("로그인 실패");
		location.href="${returnUri}";
	</script>
</c:if>