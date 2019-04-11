package test;

class Test implements Runnable{ // Runnable은 다중 상속을 받을 수 있다. (implements-->다중 가능!!)
	public void run() {
		System.out.println("실행!!");
	}
}
//인터페이스는 표준, 설계도 같은 것. 여기서 Runnable는 인터페이스일 뿐이다. 인터페이스 안에 메소드가 여러개 있으면 사용하던 안 하던 다 구현해줘야한다.
//

class Test2 extends Thread { //Thread는 이미 상속을 받아서 더 이상 상속 받을 수 없다. (자바-->단일 상속!!)
	public void run() {
		System.out.println("실행!!");
	}
}

public class RunnableTest {

	public static void mian(String[] args) {
		Runnable r = new ThreadTest();
		//		r.start(); thread가 아니니까 안되
		Thread t = new Thread(r);  //그래서 thread로 한번 감싸준 것 Runnable메모리를 만들어서 Thread를 입힌것 (외부의 Thread)
		t.start();
		
		Thread t2 = new Test2();
		t2.start();
	}
}
