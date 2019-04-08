
package service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import dao.AddrDAO;
import dao.impl.AddrDAOImpl;
import service.AddrService;
import utils.Command;

public class AddrServiceImpl implements AddrService {
	private AddrDAO adao = new AddrDAOImpl();	
	
	@Override
	public List<Map<String, String>> selectAddrList(HttpServletRequest request) {
		Map<String,String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		int blockCount = 10;
		if(paramMap.get("page")!=null && !"".equals(paramMap.get("page"))) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null && !"".equals(paramMap.get("pageCount"))){
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		if(paramMap.get("blockCount")!=null) {
			blockCount = Integer.parseInt(paramMap.get("blockCount"));
		}
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("blockCount", blockCount);
		request.setAttribute("page", page);
		
		int lNum = page * pageCount;
		int sNum = lNum - (pageCount - 1);
		paramMap.put("lNum", lNum+"");
		paramMap.put("sNum", sNum + "");
		List<Map<String, String>> addrList = adao.selectAddrList(paramMap);
		request.setAttribute("list", addrList);
		int totalCnt = adao.selectTotalAddrCnt(paramMap);
		request.setAttribute("totalCnt", totalCnt);
		int totalPageCnt = totalCnt/pageCount;
		if(totalCnt%pageCount>0) {
			totalPageCnt ++;
		}
		int lBlock = ((page-1)/blockCount+1) * blockCount;
		int fBlock = lBlock-(blockCount-1);
		if(lBlock>totalPageCnt) {
			lBlock = totalPageCnt;
		}

		request.setAttribute("lBlock", lBlock);
		request.setAttribute("fBlock", fBlock);
		request.setAttribute("totalPageCnt", totalPageCnt);
		return addrList;
	}

	@Override
	public int selectTotalAddrCnt() {
		//return adao.selectTotalAddrCnt();
		return 0;
	}

	@Override
	public void selectAddr(HttpServletRequest request) {
		Map<String,String> paramMap = Command.getSingleMap(request);
		int page = 1;
		int pageCount = 10;
		if(paramMap.get("page")!=null) {
			page = Integer.parseInt(paramMap.get("page"));
		}
		if(paramMap.get("pageCount")!=null){
			pageCount = Integer.parseInt(paramMap.get("pageCount"));
		}
		request.setAttribute("page", page);
		request.setAttribute("pageCount", pageCount);
		request.setAttribute("addr", adao.selectAddr(paramMap));
	}

}
