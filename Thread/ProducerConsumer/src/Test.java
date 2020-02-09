import java.util.ArrayList;
import java.util.List;

public class Test {
	Producer p;
	Consumer c;

	public static void main(String[] args) {
		Test t = new Test();

		t.setup();
	}

	public void setup() {
		// Set a breakpoint here, in order to control the start
		Object[] list = new Object[Base.SIZE];
		p = new Producer(list);
		Thread tp = new Thread(p);
		c = new Consumer(list);
		Thread tc = new Thread(c);

		tc.start();
		tp.start();

	}

	public void testException() {
		for (int i = 1; i < 5; i++) {
			p.produce();
			try {
				Thread.sleep(Base.DELAY);
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
