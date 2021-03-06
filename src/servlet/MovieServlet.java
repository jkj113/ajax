package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.MovieService;
import service.impl.MovieServiceImpl;
import utils.Command;

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		// Command 클래스를 만들어서 이제 필요없다.
//		String uri = request.getRequestURI();
//		int idx = uri.lastIndexOf("/"); // /두개 중에서 마지막꺼
//		if (idx == 0) {
//			throw new ServletException("원하는 서비스가 부정확합니다.");
//		} else { // throw하면 밑에꺼 실행안 하니까 else안 해줘도
//			String cmd = uri.substring(idx + 1); // list를 빼와야하니까 l부터 시작 +1
		if ("list".equals(cmd)) {
			request.setAttribute("list", ms.selectMovieList());
			Command.goPage(request,response,"/views/movie/movie_list");
		} else {
			try {
				int miNum = Integer.parseInt(cmd);
				request.setAttribute("movie", ms.selectMovieByMiNum(miNum));
				Command.goPage(request,response,"/views/movie/view");
			} catch (Exception e) {
				throw new ServletException("올바은 상세조회 값이 아닙니다.");
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = Command.getCmd(request);
			if ("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				if(hs.getAttribute("user")==null) {
				Command.goResultPage(request,response,"/","로그인하세요");
					return;
				}//이런 식으로 처리하면 매 서블릿에 해줘야한다.
				Map<String, String> movie = Command.getSingleMap(request);
				String msg = "영화 등록 실패";
				String url = "/movie/list";
				if (ms.insertMovie(movie) == 1) {
					msg = "등록에 성공하였습니다.";
					url = "/movie/list"; 
				}
				Command.goResultPage(request,response,url,msg);
//				request.setAttribute("url", "/movie/list"); //최종 목적지
//				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//				rd.forward(request, response);
//			  }else {
//				request.setAttribute("msg", "등록에 실패하였습니다.");
//				request.setAttribute("url", "/");
//				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//				rd.forward(request, response);
			} else if("delete".equals(cmd)){
					HttpSession hs = request.getSession();
					if(hs.getAttribute("user")==null) {
						Command.goResultPage(request,response,"/","로그인하세요");
						return;
					
		}
					int miNum = Integer.parseInt(request.getParameter("mi_num"));
					String msg = "삭제에 실패하였습니다";
					String url = "/movie/"+miNum;
					if(ms.deleteMovie(miNum)==1) {
						 msg= "삭제에 성공하였습니다.";
						url = "/movie/list";
					}
					Command.goResultPage(request,response,url,msg);
			}
	}
}
