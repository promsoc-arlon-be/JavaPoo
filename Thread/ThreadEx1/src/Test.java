
public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			Thread t = new MyThread();
			t.start();
		}
	}

}
