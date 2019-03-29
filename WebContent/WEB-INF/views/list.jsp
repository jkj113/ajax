<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
${param.board_title}리스트입니다. <br>
<c:forEach items="${list}" var="row">
       번호 : ${row.num}, 제목 : ${row.title} <br>
</c:forEach>
</body>
</html>