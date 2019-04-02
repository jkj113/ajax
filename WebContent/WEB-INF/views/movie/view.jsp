<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${movie==null}">
조회하신 번호는 없는 번호입니다.
<a href="/movie/list">리스트로 돌아가기</a>
	</c:if>
	<c:if test="${movie!=null}">
		<table border="1">
			<tr>
				<th>번호</th>
				<td>${movie.mi_num}</td>
			</tr>
			<tr>
				<th>영화명</th>
				<td>${movie.mi_name}</td>
			</tr>
			<tr>
				<th>년도</th>
				<td>${movie.mi_year}</td>
			</tr>
			<tr>
				<th>국가</th>
				<td>${movie.mi_national}</td>
			</tr>
			<tr>
				<th>제작사</th>
				<td>${movie.mi_vendor}</td>
			</tr>
			<tr>
				<th>감독</th>
				<td>${movie.mi_director}</td>
			</tr>
		</table>
	<c:if test="${sessionScope.user!=null}">
	<form method="post" action="/movie/delete">
	<input type="hidden" name="mi_num" value="${movie.mi_num}">
	<button>삭제</button>
	</form>
				<a href="/movie/list">리스트로 돌아가기</a>
	</c:if>
	</c:if>
</body>
</html>