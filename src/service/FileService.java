package service;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

public interface FileService {

	public Map<String, String> parseText(HttpServletRequest request) throws ServletException;
	public Map<String, String> insertAddrFromFile(File file);
}
