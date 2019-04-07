<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script>
function changePageCount(obj){
	location.href="/addr/list?page=${page}&pageCount="+obj.value;
} //바뀐 값을 보내야하니까 obj.value
function search(){
	var ad_dong = document.querySelector('#ad_dong').value;
	location.href="/addr/list?pageCount=${pageCount}&ad_dong="+ad_dong;
}
//검색했을 때 결과 값이 현재 페이지로 올 필요는 없지만
//pageCount는 가지고 가야한다. 몇개씩 볼 것인지 정한거니까
</script>

<label for="ad_dong">읍면동 : </label><input type="text" name="ad_dong" id="ad_dong" value="${param.ad_dong}">
<button onclick="search()">검색</button>

<select name="pageCount" onchange="changePageCount(this)">
<option value="10" 
<c:if test="${pageCount==10}">
selected
</c:if>
>10</option>
<option value="20"
<c:if test="${pageCount==20}">
selected
</c:if>
>20</option>
<option value="30"
<c:if test="${pageCount==30}">
selected
</c:if>
>30</option>
<option value="40"
<c:if test="${pageCount==40}">
selected
</c:if>
>40</option>
<option value="50"
<c:if test="${pageCount==50}">
selected
</c:if>
>50</option>
</select>

	<table border="1">
		<tr>
			<th>번호</th>
			<th>시도</th>
			<th>구군</th>
			<th>동</th>
			<th>리</th>
			<th>번지</th>
			<th>호</th>
		</tr>
		<c:forEach items="${list}" var="addr">
			<tr>
				<td>${addr.ad_num}</td>
				<td>${addr.ad_sido}</td>
				<td>${addr.ad_gugun}</td>
				<td>${addr.ad_dong}</td>
				<td>${addr.ad_lee}</td>
				<td>${addr.ad_bunji}</td>
				<td>${addr.ad_ho}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" align="center">
			<c:if test="${page!=1}">
					<a href="/addr/list=${1}&pageCount=${pageCount}">◀</a>
				</c:if> 
				<c:if test="${page>10}">
					<a href="/addr/list?page=${page-10}&pageCount=${pageCount}&ad_dong=${param.ad_dong}">◁</a>
				</c:if>
				 <c:forEach var="p" begin="${fBlock}" end="${lBlock}">   	<!-- 변수명을 p라고 바꾼 이유는 Service에 이미 page를 넣어줘서 자기 자신이랑 비교하니까 -->
					<c:if test="${p!=page}">
						<a href="/addr/list?page=${p}&pageCount=${pageCount}&ad_dong=${param.ad_dong}">[${p}]</a>
					</c:if>
					<c:if test="${p==page}"> 					<!-- 현재 페이지와 p값이 같을 경우는 두껍게 -->
						<b>[${p}]</b>
					</c:if>
				</c:forEach>
				<c:if test="${(totalPageCnt-10)>=page}">
					<a href="/addr/list?page=${page+10}&pageCount=${pageCount}&ad_dong=${param.ad_dong}">▷</a>
					</c:if>
					<c:if test="${totalPageCnt!=page}">
					<a href="/addr/list?page=${totalPageCnt}&pageCount=${pageCount}&ad_dong=${param.ad_dong}">▶</a>
					</c:if>
				</tr>
				<tr>
			<td colspan="7" align="right">총 갯수 : ${totalCnt}</td>
			</td>
		</tr>
	</table>
</body>