package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddrService;
import service.impl.AddrServiceImpl;
import utils.Command;

public class AddrServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddrService as = new AddrServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		if("list".equals(cmd)) {
			as.selectAddrList(request);
			Command.goPage(request, response, "/views/addr1/list");
			List<Map<String,String>> addrList = as.selectAddrList(request);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
