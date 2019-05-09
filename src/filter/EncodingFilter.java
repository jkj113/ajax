package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;


public class EncodingFilter implements Filter {
	private String encoding = "";
	
	public EncodingFilter() { //제일 먼저 실행
		System.out.println("난 무조건 처음!!");
	}

	public void destroy() {
		System.out.println("난 종료되야 호출!!");
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { //무조건 거쳐서 간다.
		request.setCharacterEncoding(encoding);
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {//두번째 실행 그리고나서 다시 호출하지 않는다. 메모리 생성하고 나서 딱 한번만 그래서 초기화라고 한다.
		encoding = fConfig.getInitParameter("encoding");
		System.out.println("난 생성된 다음에 호출!!");
	}

}
