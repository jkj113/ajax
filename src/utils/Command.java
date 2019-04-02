package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Command {
	private static final String RESULT_PATH="/views/msg/result";
	public static String getCmd(HttpServletRequest req) throws ServletException{ //req=>변수명 내 맘대로 중요한 것은 그 앞의 데이터타입
		String uri = req.getRequestURI();
		String cmd = req.getParameter("cmd");
		if(cmd!=null) {
			return cmd;
		}
		int idx = uri.lastIndexOf("/");
		if(idx==0 || idx == -1) {
			throw new ServletException("올바르지 않은 요청입니다.");
		}
		return uri.substring(idx+1);
	}
	
	public static void goResultPage(HttpServletRequest request, HttpServletResponse response, String url, String msg) throws ServletException,IOException{
		RequestDispatcher rd = request.getRequestDispatcher(RESULT_PATH);
		request.setAttribute("url", url);
		request.setAttribute("msg", msg);
		rd.forward(request, response);
	}
	
	public static void goPage(HttpServletRequest request, HttpServletResponse response, String url) throws ServletException, IOException{
		RequestDispatcher rd = request.getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	public static Map<String,String> getSingleMap(HttpServletRequest request){ //value가 2개여도 하나만 리턴 key와 value가 1대1이라는 가정에서 만들었다.
		Map<String,String> pMap = new HashMap<>();
		Map<String,String[]> map = request.getParameterMap();
		Iterator<String> it = map.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			String value = map.get(key)[0];
			pMap.put(key,value);
		}
		return pMap;
	}
}
