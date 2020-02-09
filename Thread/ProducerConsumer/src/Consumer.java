import java.util.List;

public class Consumer extends Base implements Runnable {

	public Consumer(Object[] list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		boolean stop = false;
		while (stop == false) {
			consume();
			try {
				Thread.sleep(DELAY / 4);
			} catch (InterruptedException e) {
				e.printStackTrace();
				stop = true;
			}
		}
	}

	public void consume() {
		Object o = null;
		try {
			o = get();
			System.out.println("Something has been consumed: " + o);
		} catch (ListEmptyException e) {
			System.out.println("List is empty !!!");
			Thread.yield();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
