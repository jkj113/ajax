package service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AddrService {

	public List<Map<String, String>> selectAddrList(HttpServletRequest request);
    public void selectAddr(HttpServletRequest request);
	public int selectTotalAddrCnt();

}
