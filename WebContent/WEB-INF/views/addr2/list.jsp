<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<label for="ad_dong">읍면동 : </label><input type="text" name="ad_dong" id="ad_dong">
<button onclick="search()">검색</button>
<select id="pageCount" name="pageCount" onchange="changePageCount(this)">
	<option value="10">10</option>
	<option value="20">20</option>
	<option value="30">30</option>
	<option value="40">40</option>
	<option value="50">50</option>
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
		<th>삭제</th>
	</tr>
	<tbody id="tBody">
	</tbody>

<div id="dView"></div>
<script>
	function search(){
		var ad_dong = document.querySelector('#ad_dong').value;
		location.href="/views/addr2/list?pageCount=${param.pageCount}&ad_dong=" + ad_dong;
	}
	function changePageCount(obj){
		location.href='/views/addr2/list?pageCount=' + obj.value;
	}
	function view(adNum){
		var xhr = new XMLHttpRequest();
		xhr.open('GET','/addr2/view?ad_num=' + adNum);
		xhr.onreadystatechange = function(){
			if(xhr.readyState == 4){
				if(xhr.status == 200){
					document.querySelector('#dView').innerHTML = xhr.response;
				}
			}
		}
		xhr.send();
	}
	function deleteAddr(adNum){
		var xhr = new XMLHttpRequest();
		var inputs = document.querySelectorAll('input[id]');
		var params = {adNum:adNum};
		
		xhr.open('POST','/addr2/delete');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4){
				if(xhr.status===200){
					var res = JSON.parse(xhr.response);
					alert(res.msg);
					if(res.delete==='true'){
						getList();
					}else{
						
					}
				}
			}
		}
		xhr.send(JSON.stringify(params));
	}
	function updateAddr(){
		var xhr = new XMLHttpRequest();
		var inputs = document.querySelectorAll('input[id]');
		var params = {};
		for(var i=0;i<inputs.length;i++){
			var input = inputs[i];
			params[input.id]=input.value;
		}
		xhr.open('POST','/addr2/update');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4){
				if(xhr.status===200){
					var res = JSON.parse(xhr.response);
					alert(res.msg);
					if(res.update==='true'){
						getList();
						view(params.adNum);
					}else{
						
					}
				}
			}
		}
		xhr.send(JSON.stringify(params));
	}
	function getList(){
		var xhr = new XMLHttpRequest();
		xhr.open('GET','/addr2/list?pageCount=${param.pageCount}&page=${param.page}&ad_dong=${param.ad_dong}');
		xhr.onreadystatechange = function(){
			if(xhr.readyState==4){
				if(xhr.status==200){
					console.log(xhr.response);
					var res = JSON.parse(xhr.response);
					document.querySelector('#pageCount').value = res.pageCount;
					var html = '';
					for(var addr of res.list){
						html += '<tr>';
						html += '<td>' + addr.ad_num + '</td>';
						html += '<td>' + addr.ad_sido + '</td>';
						html += '<td>' + addr.ad_gugun + '</td>';
						html += '<td><a href="javascript:view(' + addr.ad_num + ')">' + addr.ad_dong + '</a></td>';
						html += '<td>' + (addr.ad_lee?addr.ad_lee:'') + '</td>';
						html += '<td>' + addr.ad_bunji + '</td>';
						html += '<td>' + addr.ad_ho + '</td>';
						html += '<td><button onclick="deleteAddr(\'' + addr.ad_num + '\')">삭제</button></td>';
						html += '</tr>';
					}
					html +='<tr>';
					html += '<td colspan="8">';
					html += '<a href="/views/addr2/list?page='+(res.page=1)+'&pageCount='+res.pageCount+'&ad_dong=${param.ad_dong}">◀</a>'
					if(res.page<=10){
						html += '<a href="/views/addr2/list?page='+(res.page-(res.page-1))+'&pageCount='+res.pageCount+'&ad_dong=${param.ad_dong}">◁</a>'
					}else{
						html += '<a href="/views/addr2/list?page='+(res.page-10)+'&pageCount='+res.pageCount+'&ad_dong=${param.ad_dong}">◁</a>'
					}
					for(var i=res.fBlock;i<=res.lBlock;i++){
						if(i==res.page){
							html += '<b>[' + i + ']</b>';
						}else{
							html += '<a href="/views/addr2/list?pageCount=' + res.pageCount + '&page=' + i + '">[' + i + ']</a>';
						}
					}
					html += '<a href="/views/addr2/list?page='+(res.page+10)+'&pageCount='+res.pageCount+'&ad_dong=${param.ad_dong}">▷</a>'
					html += '<a href="/views/addr2/list?page='+res.totalPageCnt+'&pageCount='+res.pageCount+'&ad_dong=${param.ad_dong}">▶</a>'
					html += '</td>';
					html +='</tr>';	
					html += '<tr>';
					html += '<td colspan="8" align="right">총 갯수 :'+res.totalPageCnt+'</td>';
					document.querySelector('#tBody').innerHTML = html;
				}
			}
		}
		xhr.send();
	}
	getList();
</script>

</body>
</html>