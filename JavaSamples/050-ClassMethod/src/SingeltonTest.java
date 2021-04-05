
public class SingeltonTest {

	public static void main(String[] args) {

		// Constructor is private
		// Singelton s = new Singelton();

		Singelton s = Singelton.getSingelton();
		System.out.println(s);
		Singelton s1 = Singelton.getSingelton();
		System.out.println("prem "+s);
		System.out.println("sec "+ s1);
	}

}
