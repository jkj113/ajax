package servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.FileService;
import service.impl.FileServiceImpl;
import utils.Command;
import utils.UploadFile;



public class FileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FileService  fs = new FileServiceImpl();

    public FileServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String,String> rMap = fs.parseText(request);
		Command.printJSON(response, rMap);
		System.out.println(rMap);
	}

}
