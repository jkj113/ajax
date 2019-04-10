<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table border="1" id="addrTable">
		<tr>
			<th>번호</th>
			<td>${addr.ad_num}</td>
		</tr>
		<tr>
			<th>주소코드</th>
			<td><input type="text" id="ad_code" value="${addr.ad_code}"></td>
		</tr>
		<tr>
			<th>시도</th>
			<td><input type="text" id="ad_sido" value="${addr.ad_sido}"></td>
		</tr>
		<tr>
			<th>구군</th>
			<td><input type="text" id="ad_gugun" value="${addr.ad_gugun}"></td>
		</tr>
		<tr>
			<th>동면읍</th>
			<td><input type="text" id="ad_dong" value="${addr.ad_dong}"></td>
		</tr>
		<tr>
			<th>리</th>
			<td><input type="text" id="ad_lee" value="${addr.ad_lee}"></td>
		</tr>
		<tr>
			<th>번지</th>
			<td><input type="text" id="ad_bunji" value="${addr.ad_bunji}"></td>
		</tr>
		<tr>
			<th>호</th>
			<td><input type="text" id="ad_ho" value="${addr.ad_ho}"></td>
		</tr>
		<tr>
		<td colspan="2" align="center"><button onclick="updateAddr()">수정</button></td>
		</tr>
		<tr>
		<td colspan="2" align="center"><button onclick="deleteAddr()">삭제</button></td>
	</tr>
	</table>
<input type="hidden" id="adNum" value="${param.ad_num}">
</body>
</html>