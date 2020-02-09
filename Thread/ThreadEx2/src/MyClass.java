
public class MyClass implements Runnable {

	public void run() {
		System.out.println("Hello world from thread id:\t" + this);
		Thread t = Thread.currentThread();
		System.out.println("Running in thread id:\t\t" + t);
	}
}
