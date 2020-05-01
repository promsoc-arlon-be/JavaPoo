import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Restaurant {
	// assumes the current class is called MyUtils
	private final static Logger LOGGER = Logger.getLogger(Restaurant.class.getName());

	public final static String name = "Restaurant";

	public static void main(String[] args) {
		setLogLevel(Level.INFO);
		Restaurant restaurant = new Restaurant();

		// restaurant.createDessert();
		//restaurant.loadData();
		restaurant.createMenu();
		// Demonstrate the load method
		// restaurant.loadOneOfEach();

	}

	public static void setLogLevel(Level l) {
		// Set the log level to FINE
		Logger.getLogger("").setLevel(Level.FINE);
		Logger.getLogger("").getHandlers()[0].setLevel(Level.FINE);
		// Set the log level to INFO
		Logger.getLogger("").setLevel(Level.INFO);
		Logger.getLogger("").getHandlers()[0].setLevel(Level.INFO);

	}

	public void createDessert() {
		LOGGER.info("Entering createDessert");
		try {
			Dessert d = new Dessert("My dessert", true);
			// Save is commiting automatically
			d.save();
			Dessert.selectAllStatic();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createMenu() {
		LOGGER.info("Entering createMenu");

		try {
			Menu m = new Menu("My menu");

			Entree e = new Entree("Menu starter " + new Date().getTime());
			e.save();
			PlatPrincipal pp = new PlatPrincipal("Menu pp " + new Date().getTime());
			pp.save();
			Dessert d = new Dessert("Menu dessert " + new Date().getTime(), true);
			d.save();

			m.addPlat(e);
			m.addPlat(pp);
			m.addPlat(d);

			// Save is commiting automatically
			int menuId = m.save();

			// Menu.selectAllStaticClassic();
			System.out.println(m);

			Menu m2 = new Menu(menuId);
			m2.load();
			System.out.println(m2);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void loadData() {
		LOGGER.info("Entering loadData");

		try {
			createEntrees();
			createPlats();
			createDesserts();

			// Display all the data in the database
			Entree.selectAllStatic();
			PlatPrincipal.selectAllStatic();

			// Display the dessert using the two available methods
			Dessert.selectAllStaticClassic();
			Dessert.selectAllStatic();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	private void createEntrees() throws ClassNotFoundException, SQLException {
		LOGGER.info("Entering createEntrees");

		for (int i = 0; i < 5; i++) {
			Entree e = new Entree("The starter " + new Date().getTime());
			e.save();
		}
	}

	private void createPlats() throws ClassNotFoundException, SQLException {
		LOGGER.info("Entering createPlats");

		for (int i = 0; i < 5; i++) {
			PlatPrincipal pp = new PlatPrincipal("The main dish " + new Date().getTime());
			pp.save();
		}
	}

	private void createDesserts() throws ClassNotFoundException, SQLException {
		LOGGER.info("Entering createDesserts");

		for (int i = 0; i < 5; i++) {

			Boolean b;
			if (i % 2 == 0)
				b = true;
			else
				b = false;
			Dessert d = new Dessert("The dessert " + new Date().getTime(), b);
			d.save();
		}
	}

	/**
	 * This test demonstrate the load method.
	 */
	private void loadOneOfEach() {
		LOGGER.info("Entering loadOneOfEach");

		try {
			Entree e = new Entree(1);
			e.load();
			System.out.println(e);
			PlatPrincipal pp = new PlatPrincipal(1);
			pp.load();
			System.out.println(pp);
			Dessert d = new Dessert(1);
			d.load();
			System.out.println(d);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}

	}

}
