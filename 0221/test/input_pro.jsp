<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
  request.setCharacterEncoding("utf-8");
  String bun = request.getParameter("hakbun");
  String name = request.getParameter("name");
  String phone1 = request.getParameter("phone1");
  String phone2 = request.getParameter("phone2");
  String phone3 = request.getParameter("phone3");
%>

<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
    학번: <%= bun  %> <br />
    이름: <%= name %> <br />
    전화: <%= phone1%>-<%= phone2%>-<%= phone3%> <br />

  </body>
</html>
