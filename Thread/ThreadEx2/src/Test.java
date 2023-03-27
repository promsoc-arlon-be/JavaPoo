
public class Test {

	public static void main(String[] args) {
		MyClass mc = new MyClass();
		for (int i = 0; i < 1000; i++) {
			Thread t = new Thread(mc);
			t.start();
		}
	}

}
