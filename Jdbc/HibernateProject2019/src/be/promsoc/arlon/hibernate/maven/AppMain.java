package be.promsoc.arlon.hibernate.maven;


public class AppMain {

	public static void main(String[] args) {
		Demo d = new Demo();
		// Chacune des méthodes à un contexte transactionnel
		d.createClients();
		d.createProductsAndPacks();
		d.createUserProducts();
		d.createUserPackWithProducts();
	}
}
