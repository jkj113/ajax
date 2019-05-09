package test;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest extends Thread{

	class Horse extends Thread{
		private long limit = 3000;
		private String name;
		private int number;
		private int speed;
		
		public Horse(String name, int number, int speed) {
			this.name = name;
			this.number = number;
			this.speed = speed;
		}
	
//	public ThreadTest(String name) {
//		super(name);
//	}
   public void run() {
//	   for(int i = 1;i<10;i++) {
//	   System.out.println("start()를 호출하면 내가 실행됨!!");
//		   System.out.println(super.currentThread().getName()+"번째 실행됨!!");
//		try {
//			Thread.sleep(1000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}   
//	   }
	   while(limit!=0) {
		   limit -= speed;
		   if(limit<0 || limit==0) {
			   limit = 0;
			   System.out.println(number + "말("+ name +")도착");
		   }else {
			   System.out.println(number + "말("+name+")남은 거리 : "+limit);
		   }
	   try {
		   Thread.sleep(500);
	   }catch(InterruptedException e) {
		   e.printStackTrace();
	   }
	   }
   }
   
//   public static void main(String[] args) {
//	   List<Thread> tList = new ArrayList<>();
//	   for(int i = 1;i<5;i++) {
//		   Thread t = new ThreadTest(i +"");tList.add(t);
//		   
//	   }
//	   for(Thread t : tList) {
//	   t.start();
//   }
//   }
}
}
