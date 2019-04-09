/**
 * 
 */
var CommonFunc = function(){
	this.callFunc = function(){
		alert('난 중요한 공통함수!!');
		callFunc2();
	}
	
	var callFunc2 = function(){
		alert('난 외부에서 호출 안 될껄!!');
	}
}
var AjaxUtil = function() {
	var xhr = new XMLHttpRequest();
	//xhr은 AjaxUtil꺼
	
	this.open = function(url, method, async) {
		method = method ? method : 'GET';
		async = async ? async : true;
		xhr.open(method, url);  //open만한다-연결할 준비
		xhr.onreadystatechange = function() {   //2부터 잡힌다. 없는 주소를 쓰면 1에서 바로 4. 처리를 해야 2,3이 나온다
			if (xhr.readyState == 4) {   
				if (xhr.status == 200) {
					this.callback(xhr.response);
				}
			}
		}
	}


	xhr.callback = function(res) {
		console.log(res);
	}
	
	this.setCallback = function(call) {
		xhr.callback = call;
	} //setCallback을 해서 call function을 setCallback에 넣어 줬다.
	
	this.send = function() {
		xhr.send(); //send를 보내는 순간 1 (실제로 연결)
	}
//	this.toString = function(){
//		return '하하 난 ajax객체라구!!';
//	}
	
}