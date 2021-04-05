
public class Singelton {

	// Je ne veux pas qu'une autre classe
	// puisse faire un new.
	// Constructeur -> private
	private Singelton() {

	}

	private static Singelton singelton;

	public static Singelton getSingelton() {

		// lazy initialization
		if (singelton == null)
			singelton = new Singelton();

		return singelton;
	}
}
