
public class MyClassAttribute {

	
	// attribut de classe
	// unique pour la classe !!!
	private static String myString;

	public static void main(String[] args) {
		MyClassAttribute mca = new MyClassAttribute();
		mca.myString = "Hello";
		System.out.println(mca.myString);
		MyClassAttribute mca2 = new MyClassAttribute();
		mca2.myString = "Hello mca2";
		System.out.println(mca2.myString);
		System.out.println(mca.myString);
		mca2.myString = "Hello mca2 again";
		System.out.println(mca2.myString);
		System.out.println(mca.myString);
		
		System.out.println(MyClassAttribute.myString);
		
	}
}
