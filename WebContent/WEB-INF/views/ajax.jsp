<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="/js/common.js"></script>
</head>
<body>
	<script>
	function callback(res){
		var res = JSON.parse(res);
		for(var addr of res.list){
			document.write(addr);
	//		document.write(addr.ad_num + "," +addr.si_do); 하면은 글자가 나와요.
		}
		//document.write 하면 걔 .toString을 하는데 특별히 데이터 타입이 없으면 object가 나온다???
		
		var xhr = new XMLHttprequest();
xhr.open('GET','/addr2/list');
xhr.onreadystatechange = function() { 
	if (xhr.readyState == 4) {   
		if (xhr.status == 200) {
			callback(xhr.response);
		}
	}
}
xhr.send();
	}
		var au = new AjaxUtil();
		au.open('/addr2/list');
		au.setCallback(callback);
//		alert(au);
		au.send();
	
/*		var CPU = function() {
			this.core = 2;
			this.callback = function() {
				alert(1);
			}
		}

		var Computer = function() {
			this.cpu = new CPU();
			this.callback = function() {
				alert(this.cpu.core);
			}
			//			this.callback;
			this.cpu.callback = this.callback;
		}

		var c = new Computer();
		//		alert(c.cpu.core);
		//		c.callback();
		c.cpu.callback();
	*/
		</script>
</body>
</html>