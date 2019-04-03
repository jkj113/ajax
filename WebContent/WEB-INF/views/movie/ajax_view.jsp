<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="rDiv"></div>
	<c:if test="${sessionScope.user!=null}">
		<button onclick="deleteMovie()">삭제</button>
	</c:if>
	<script>
	function deleteMovie(){
		xhr.open('POST', '/am/delete');
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var result = JSON.parse(xhr.response);
				alert(result.msg);
				if(result.url){
					location.href=result.url;
				}
			}
		}
		xhr.send('mi_num=${param.miNum}');
	}
		var xhr = new XMLHttpRequest();
		xhr.open('GET', '/am/${param.miNum}');
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4 && xhr.status == 200) {
				var movie = JSON.parse(xhr.response);
				//	var html = "";
				//		html +='<tr>';
				//		html +='<td>' + movie['mi_num']+'</td>';
				//		html +='<td>' + movie['mi_name']+'</td>';
				//		html +='<td>' + movie['mi_year']+'</td>';
				//		html +='<td>' + movie['mi_national']+'</td>';
				//		html +='<td>' + movie['mi_vendor']+'</td>';
				//		html +='<td>' + movie['mi_director']+'</td>';
				//		html += '</tr>';
				//	}
				//	document.querySelector('#tbody').innerHTML = html;
				//}
				var html = '영화 번호 : ' + movie.mi_num + '<br>';
				html += '영화 제목 : ' + movie.mi_name + '<br>';
				html += '제작 연도 : ' + movie.mi_year + '<br>';
				html += '제작 국가 : ' + movie.mi_national + '<br>';
				html += '제작사 : ' + movie.mi_vendor + '<br>';
				html += '감독 : ' + movie.mi_director + '<br>';
			}
			document.querySelector('#rDiv').innerHTML = html;
		}
		xhr.send();
	</script>
</body>
</html>