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

public class MovieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MovieService ms = new MovieServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/"); // /두개 중에서 마지막꺼
		if (idx == 0) {
			throw new ServletException("원하는 서비스가 부정확합니다.");
		} else { // throw하면 밑에꺼 실행안 하니까 else안 해줘도
			String cmd = uri.substring(idx + 1); // list를 빼와야하니까 l부터 시작 +1
			if ("list".equals(cmd)) {
				request.setAttribute("list", ms.selectMovieList());
				RequestDispatcher rd = request.getRequestDispatcher("/views/movie/movie_list");
				rd.forward(request, response);
			}
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		int idx = uri.lastIndexOf("/");
		if (idx == 0) {
			throw new ServletException("원하는 서비스가 부정확합니다.");
		} else {
			String cmd = uri.substring(idx + 1);
			if ("insert".equals(cmd)) {
				HttpSession hs = request.getSession();
				if(hs.getAttribute("user")==null) {
					request.setAttribute("msg", "로그인하세요");
					request.setAttribute("url", "/");
					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
					rd.forward(request, response);
					return;
				}//이런 식으로 처리하면 매 서블릿에 해줘야한다.
				Map<String, String> movie = new HashMap<>();
				movie.put("mi_name", request.getParameter("mi_name"));
				movie.put("mi_year", request.getParameter("mi_year"));
				movie.put("mi_national", request.getParameter("mi_national"));
				movie.put("mi_vendor", request.getParameter("mi_vendor"));
				movie.put("mi_director", request.getParameter("mi_director"));
				request.setAttribute("msg", "영화 등록 실패");
				if (ms.insertMovie(movie) == 1) {
					request.setAttribute("msg", "등록에 성공하였습니다.");
				}
				request.setAttribute("url", "/movie/list"); //최종 목적지
				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
				rd.forward(request, response);
			}else {
				request.setAttribute("msg", "등록에 실패하였습니다.");
				request.setAttribute("url", "/");
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);
			}
		}

	}

