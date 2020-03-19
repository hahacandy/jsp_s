<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${!empty msg}">
	<script type="text/javascript">
		alert("${msg}");
	</script>
</c:if>

<c:if test="${!empty back}">
	<script type="text/javascript">
		history.back();
	</script>
</c:if>

<c:if test="${!empty uri}">
	<script type="text/javascript">
		location.href = "${uri}";
	</script>
</c:if>

<c:if test="${!empty puri}">
	<script type="text/javascript">
		opener.location.href = "${puri}";
		self.close();
	</script>
</c:if>
