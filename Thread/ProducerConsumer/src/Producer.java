import java.util.Date;
import java.util.List;

public class Producer extends Base implements Runnable {
	public Producer(Object[] list) {
		super(list);
		// TODO Auto-generated constructor stub
	}

	public void run() {
		boolean stop = false;
		while (stop == false) {
			produce();
			try {
				Thread.sleep(DELAY);
			} catch (InterruptedException e1) {
				stop = false;
			}
		}

	}

	public void produce() {
		Date d = new Date();

		boolean added = false;
		while (added == false) {
			try {
				add(d);
				added = true;
				System.out.println("Something has been produced: " + d);
			} catch (ListFullException e) {

				System.out.println("List is full !!!");

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

}
