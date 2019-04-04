package service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;

import dao.AddressDAO;
import dao.impl.AddressDAOImpl;
import service.FileService;
import utils.UploadFile;

public class FileServiceImpl implements FileService {
	private AddressDAO adao = new AddressDAOImpl();

	@Override
	public Map<String, String> parseText(HttpServletRequest request) throws ServletException{
		Map<String,Object> pMap = UploadFile.parseRequest(request);
		Iterator<String> it = pMap.keySet().iterator();    //Iterator로 키값을 전부 다 받아온다.
		while(it.hasNext()) {   
			String key = it.next();
			Object obj = pMap.get(key);                   //받아 온 key값의 value르 가져온다.
			if(obj instanceof FileItem) { //instanceof --> fileitem이야?? 라고 물어보는 것 맞으면 save로직이 들어가야한다.
			try {
				File tFile = UploadFile.writeFile((FileItem)obj);    //value가 fileitem이 맞으면 upload로 넘겨준다.
				return insertAddrFromFile(tFile);
			} catch (Exception e) {
				throw new ServletException(e);
			}
				
			}
		}
		return null;
	}

	@Override
	public Map<String, String> insertAddrFromFile(File file){
		Map<String,String> rMap = new HashMap<>();
		int totalCnt = 0;
		int targetCnt = 0;
		Long sTime = System.currentTimeMillis();
		
		List<String> colList = new ArrayList<>();
		colList.add("ad_code");
		colList.add("ad_sido");
		colList.add("ad_gugun");
		colList.add("ad_dong");
		colList.add("ad_lee");
		colList.add("ad_san");
		colList.add("ad_bunji");
		colList.add("ad_ho");
		colList.add("ad_roadcode");
		colList.add("ad_isbase");
		colList.add("ad_orgnum");
		colList.add("ad_subnum");
		colList.add("ad_jinum");
		colList.add("ad_etc");
		try (FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);){ // try-catch에 사용해서 close되도록
			List<Map<String, String>> addrList = new ArrayList<>();
			String line = "";
			while((line=br.readLine()) != null) {
				String[] addrLines = line.split("\\|");
				Map<String, String> addrMap = new HashMap<>();
				for (int i = 0; i < addrLines.length; i++) {
					addrMap.put(colList.get(i), addrLines[i]);
				}
				addrList.add(addrMap);
			
//				if (addrList.size() == 1000) {
//				int result = adao.insertAddressList(addrList);
//				addrList.clear();
//				System.out.println("반영된 건 수 : " + result);
//			}
//			}
//			int result = adao.insertAddressList(addrList);
//			addrList.clear();
//			System.out.println("반영된 건 수 : " + result);
		if(addrList.size()==10000) {
			totalCnt += adao.insertAddressList(addrList);
			addrList.clear();
			targetCnt+=10000;
			
		}
			}
			totalCnt += adao.insertAddressList(addrList);
			targetCnt += addrList.size();
			addrList.clear();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		rMap.put("targetCnt", targetCnt + "");
		rMap.put("totalCnt", totalCnt + "");
		rMap.put("executeTime", (System.currentTimeMillis()-sTime)+"");
		return rMap;
	}

}
