package dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AddrDAO {
	
	public List<Map<String,String>> selectAddrList(Map<String,String> addr);
	public Map<String,String> selectAddr(Map<String,String> addr);
	public int selectTotalAddrCnt(Map<String,String> addr);
	public int updateAddr(Map<String,String> addr);
	public int deleteAddr(Map<String, String> addr);
}
