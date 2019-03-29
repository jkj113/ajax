<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
    <%
    String num1Str = request.getParameter("num1");
    String num2Str = request.getParameter("num2");
    int num1 = Integer.parseInt(num1Str);
    int num2 = Integer.parseInt(num2Str);
    out.print(num1+num2);
    %>