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
	var num1 = 1;
	var num2 = '1';
	alert(num1==num2);
	alert(num1===num2);
	//true가 나온다. 자바스크립트에서는 ==은 2개의 값이 같은지 비교한다. 데이터타입이 같은지 비교하고싶으면 ===
		//부정도 !==이렇게
	
		var config = {
			name : '홍길동',
			info : function() {
				alert('인포메이션!');
			}
		}
	
		
		config.test = function(){
			alert('나도 됨!');
		}
		config.test();
		
		config.toString = function(){
			alert('나 출력하면 이게 나옴!!');
		}
		config.info();
		alert(config);
		
		for(var key in config){ //Iterator 비슷
			console.log(key);
		}
	</script>
</body>
</html>