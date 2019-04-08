
package servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.AddrService;
import service.impl.AddrServiceImpl;
import utils.Command;

public class AddrServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AddrService as = new AddrServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = Command.getCmd(request);
		if("list".equals(cmd)) {
			as.selectAddrList(request);
			Map<String,Object> rMap = new HashMap<>();
			rMap.put("list", request.getAttribute("list"));
			rMap.put("page", request.getAttribute("page"));
			rMap.put("pageCount", request.getAttribute("pageCount"));
			rMap.put("blockCount",request.getAttribute("blockCount"));
			rMap.put("ad_dong", request.getParameter("ad_dong"));
			rMap.put("totalCnt", request.getAttribute("totalCnt"));
			rMap.put("fBlock", request.getAttribute("fBlock"));
			rMap.put("lBlock", request.getAttribute("lBlock"));
			Command.printJSON(response, rMap);
		}else if("view".equals(cmd)) {
			as.selectAddr(request);
			Command.goPage(request, response, "/views/addr2/view");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
