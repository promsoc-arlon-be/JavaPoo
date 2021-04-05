
public class Vehicule {

	// Ceci est une constante
	public final static int COLOR = 3;

	static int compteur;

	Vehicule() {
		compteur++;
	}

	public static void main(String[] args) {
		Vehicule v = new Vehicule();
		System.out.println(v.compteur);
		Vehicule v1 = new Vehicule();
		System.out.println("" + v + " " + v.compteur);
		System.out.println("" + v1 + " " + v.compteur);
		
		int i = Vehicule.COLOR;
	}
}
