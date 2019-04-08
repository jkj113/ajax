package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.UserService;
import service.impl.UserServiceImpl;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String cmd = request.getParameter("cmd");
		// System.out.println(user);
		if ("insert".equals(cmd)) {
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			String uiName = request.getParameter("ui_name");
			String uiEmail = request.getParameter("ui_email");
			Map<String, String> user = new HashMap<>();
			user.put("uiId", uiId);
			user.put("uiPwd", uiPwd);
			user.put("uiName", uiName);
			user.put("uiEmail", uiEmail);
			if (us.insertUser(user) == 1) {
				request.setAttribute("msg", "회원 가입에 성공하였습니다.");
				request.setAttribute("url", "/");
			} // 원래 이런거는 service에서 return된 결과로 뭔가 하는 것은 service에서
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);
		} else if ("login".equals(cmd)) {
//			String uiId = request.getParameter("ui_id");
//			String uiPwd = request.getParameter("ui_pwd");
//			Map<String, String> user = us.loginUser(uiId);
//			request.setAttribute("msg", "아이디나 비밀번호가 잘못되었습니다.");
//			if (user != null) {
//				String id = user.get("uiId");
//				String pwd = user.get("uiPwd");
//				if (uiId.equals(id) && uiPwd.equals(pwd)) {
//					request.setAttribute("msg", "로그인에 성공하였습니다!!");
//					request.setAttribute("url", "/");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
//				} else {
//					request.setAttribute("msg", "비밀번호가 틀렸습니다.");
//					request.setAttribute("url", "/");
//					RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//					rd.forward(request, response);
//				}
//
//			} else {
//				request.setAttribute("msg", "아이디가 없습니다.");
//				request.setAttribute("url", "/");
//
//				RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
//				rd.forward(request, response);
//			}
			String uiId = request.getParameter("ui_id");
			String uiPwd = request.getParameter("ui_pwd");
			Map<String, String> user = us.login(uiId, uiPwd);
			request.setAttribute("msg", "아이디나 비밀번호가 잘못되었습니다."); //실패를 가정한다.
			if (user != null) {
				if(request.getServletContext().getAttribute("userMap")==null) {
					request.getServletContext().setAttribute("userMap", new HashMap<>());
				}
				Map<String,List<String>> userMap = (Map<String,List<String>>) request.getServletContext().getAttribute("userMap");
				userMap.put(user.get("uiId"),new ArrayList<>());
				HttpSession session = request.getSession();
				session.setAttribute("user", user);   //request를 사용하면 index.jsp까지만 사용되고 사라진다.
				request.setAttribute("msg", "로그인에 성공하였습니다.");
			}
			request.setAttribute("url", "/");
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);
		} else if ("logout".equals(cmd)) {
			HttpSession session = request.getSession();
			session.invalidate();  //가지고 있는 것들을 제거한다.
			request.setAttribute("msg", "로그아웃 되었습니다.");
			request.setAttribute("url", "/");
			RequestDispatcher rd = request.getRequestDispatcher("/views/msg/result");
			rd.forward(request, response);
		}
	}
}