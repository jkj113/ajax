<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/user">
	<table border="1">
		<tr>
			<th>아이디</th>
			<td><input type="text" name="ui_id" id="ui_id"></td>
			</tr>
			<tr>
			<th>비밀번호</th>
			<td><input type="password" name="ui_pwd" id="ui_pwd"></td>
			</tr>
			<tr>
			<td colspan="2"><button>로그인</button>
			</tr>
	</table>
	<a href="/views/user/join">회원가입</a>
	<input type="hidden" name="cmd" value="login">
	</form>
</body>
</html>