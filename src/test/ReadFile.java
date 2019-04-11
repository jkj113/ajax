package test;
//존재의 시작은 패키지부터 파일도 처음 (드라이브)부터 
//그래서 리네임을 해줘 targetPath경로로 
import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import service.FileService;
import service.impl.FileServiceImpl;

public class ReadFile {

	public static void main(String[] args) {
		FileService fs = new FileServiceImpl();
		String path = "D:\\study\\addr\\input";
		String targetPath = "D:\\study\\addr\\input\\ok";
		File targetFolder = new File(targetPath);
		File root = new File(path);
		File[] files = root.listFiles();
		for (File file : files) {
			if (!file.isDirectory()) {
				String name = file.getName();
				System.out.println(name+"파일 시작!");
				Map<String, String> rMap = fs.insertAddrFromFile(file);
				int targetCnt = Integer.parseInt(rMap.get("targetCnt"));
				int totalCnt = Integer.parseInt(rMap.get("totalCnt"));
				if(targetCnt==totalCnt) {
					try {
						FileUtils.moveFileToDirectory(file, targetFolder, false); //이게 리네임보다 더 간단/ 우리 이미 ok있으니까 false도 괜찮 true는 폴더 없으면 만들어줘
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
//				System.out.println("실행시간 : "+rMap.get("executeTime"));
				System.out.println(name + "파일 종료!");
				return; //파일 하나만 하고 끝낼꺼야
				//실패하면 그 시간 기준으로 롤백 근데 사용자가 원하는대로 일단 다 올리던지 롤백하던지
				//근데 우리는 시간을 안 만들었으니까 번호(입력 갯수)로 
			}
//		System.out.println(file.isDirectory());
//		System.out.println(file.getName());
		}
	}

}
