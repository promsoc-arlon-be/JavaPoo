
public class MyThread extends Thread {

	 public void run() {
		System.out.println("Hello world from thread id:\t" + this);
		Thread t = Thread.currentThread();
		System.out.println("Running in thread id:\t\t" + t);
		try {
			sleep(15);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
