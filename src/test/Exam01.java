package test;

import java.util.Map;

import com.google.gson.Gson;


public class Exam01 {

	public static void main(String[] args) {
		String jsonStr = "{\"key\":1}";
		Gson g = new Gson(); //자바스크립트에서 이 과정을 거치지 않아도 되는 이유는 이미 JSON이 있기때문에. 얘는 없으니까 선언해줘야한다.
		Map<String,Double> m = g.fromJson(jsonStr,Map.class);
		//얘가 Double로 받는다???????????
		System.out.println(m);
	}
}