<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/file" enctype="multipart/form-data">
주소 파일 : <input type="file" name="file"><br>
이름 : <input type="text" name="name"><br>
<button>주소 업로드</button>
</form>

</body>
</html>