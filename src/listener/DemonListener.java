package listener;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.io.FileUtils;

import service.FileService;
import service.impl.FileServiceImpl;

public class DemonListener implements ServletContextListener, Runnable {
	private Thread t; // 메모리는 생성하진 않았지만 t변수를 사용할 수 있따. 누군가가 DemonListener를 호출하니까(서버 시작할 때 시작됨)
						// t의 초기값은 null
	private static final long BREAK_TIME = 1000 * 60;
	private FileService fs = new FileServiceImpl();  //여기다가 한 이유는 메모리 생성 한번만 하라고. 매번 할 때마다 생성할 필요 없으니까

	public DemonListener() {
		System.out.println("내가 제일 처음!!");

	}

	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("난 생성후 바로 실행되지!!");
		if (t == null) { // DBCon에서 많이 봤지...?? 여기까지 온 것은 메모리 생성이 끝났다.
			t = new Thread(this); // 내부의 Thread로 감싼 것--> DemonListener의 this이다.
			t.setDaemon(true); // 이러면 DemonThread가 된다. 종료될 때 destroy 기다려 준다 작업의 나머지를 처리할 때까지.
		}
		t.start();
	}

	private void readAddrFiles() {
		String path = "D:\\study\\addr\\input";
		String targetPath = "D:\\study\\addr\\input\\ok";
		File targetFolder = new File(targetPath);
		File root = new File(path);
		File[] files = root.listFiles();
		for (File file : files) {
			System.out.println("총 파일 갯수 : " + (files.length-1)); //-1은 OK폴더
			if (!file.isDirectory()) {
				String name = file.getName();
				System.out.println(name + "파일 시작!");
				Map<String, String> rMap = fs.insertAddrFromFile(file);
				int targetCnt = Integer.parseInt(rMap.get("targetCnt"));
				int totalCnt = Integer.parseInt(rMap.get("totalCnt"));
				if (targetCnt == totalCnt) {
					try {
						FileUtils.moveFileToDirectory(file, targetFolder, false);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println(name + "파일 종료!");
				return;
			}
		}
	}

	@Override
	public void run() {
		while (Thread.currentThread() == t) {
		try {
			Thread.sleep(BREAK_TIME);
			readAddrFiles();
//			System.out.println("난 3초마다 실행된다아~");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	}
}
