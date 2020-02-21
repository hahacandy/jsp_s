<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
  int bun=10;
  String name="홍길동";
  String nn="";
  if(bun<20){
    
  }

  out.print("번호:" +bun + "<br />");
%>
<!DOCTYPE html>
<html lang="en" dir="ltr">
  <head>
    <meta charset="utf-8">
    <title></title>
  </head>
  <body>
번호 : <%= bun %><br>
이름 : <%= name %><br>


  </body>
</html>
