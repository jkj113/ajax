package service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AddrService {

	public List<Map<String, String>> selectAddrList(HttpServletRequest request);
    public void selectAddr(HttpServletRequest request);
	public int selectTotalAddrCnt();
//	public int updateAddr(Map<String,String> addr);
	public Map<String,String> updateAddr(HttpServletRequest request) throws IOException;
	public Map<String,String> deleteAddr(HttpServletRequest request) throws IOException;
	public List<String> selectAdSido();
}
