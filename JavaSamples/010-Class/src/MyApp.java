
public class MyApp {

	public static void main(String[] args) {
		// Une classe est un type
		// mc est une variable temporaire
		// Le new est l'instanciation
		MyClass mc = new MyClass();
		
		// = c'est l'affectation
		// mystring est l'attribut de MyClass
		mc.myString = "Hello World";
		// Appel du constructeur explicite
		mc.myString = new String("Hello World");
		
		System.out.println(mc.myString);	
		
		mc.setMyString("toto");
		System.out.println(mc.getMyString());
	}

}
