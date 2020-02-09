import java.util.Date;
import java.util.List;

// https://www.cs.princeton.edu/courses/archive/spr96/cs333/java/tutorial/java/threads/states.html

// Improvement
// https://en.wikipedia.org/wiki/Circular_buffer

public class Base {
	public final static int DELAY = 2000;

	static protected Object[] list;
	static int size;
	public final static int SIZE = 5;

	public Base(Object[] list) {
		super();
		this.list = list;
	}

	public int modulo(int i) {
		return i % SIZE;
	}

	public void add(Object o) throws ListFullException {
		// Set a breakpoint here, if you want to block the Producer Thread
		// and leave the Consumer running
		System.out.println(this);
		synchronized (Base.class) {
			// Set a breakpoint here, if you want to block the Producer Thread
			// and check that the Consumer thread is also blocked
			if (size < SIZE) {
				list[size] = o;
				size++;
				System.out.println(this);
			} else {
				System.out.println(this);
				throw new ListFullException();
			}
		}
	}

	public Object get() throws ListEmptyException {
		// Set a breakpoint here, if you want to block the Consumer Thread
		// and leave the Producer running
		System.out.println(this);
		Object o = null;
		synchronized (Base.class) {
			// Set a breakpoint here, if you want to block the Consumer Thread
			// and check that the Producer thread is also blocked
			if (size > 0) {
				size--;
				o = list[size];
				list[size] = null;
			} else {
				throw new ListEmptyException();
			}
		}
		System.out.println(this);
		return o;
	}

	@Override
	public String toString() {
		return "Base [size=" + size + "]";
	}

}
