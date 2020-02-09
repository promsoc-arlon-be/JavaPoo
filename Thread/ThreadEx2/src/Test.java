
public class Test {

	public static void main(String[] args) {
		for (int i = 0; i < 1000; i++) {
			MyClass mc = new MyClass();
			Thread t = new Thread(mc);
			t.start();
		}
	}

}
