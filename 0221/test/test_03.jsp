<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%
  //jsp 내장 객체 : request(요청), response(응답), out(출력), application, context
  String age = request.getParameter("age");

%>
  <!DOCTYPE html>
  <html lang="en" dir="ltr">
    <head>
      <meta charset="utf-8">
        <title></title>
      </head>
      <body>
        결과는: <%= age %>
        결과는: <%= age %>
        결과는: <%= age %>
      </body>
    </html>
