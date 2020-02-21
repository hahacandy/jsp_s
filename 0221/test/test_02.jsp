<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%!
	int s=0;
	int sum(int a, int b){
		for(int x=a; x<b; x++){
			s+=x;
		}
		return s;
	}

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
결과는 <%= sum(1,10) %>
</body>
</html>
