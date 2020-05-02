package be.promsoc.arlon.hibernate.maven;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class Demo {
	User userObj;
	Session sessionObj;
	SessionFactory sessionFactoryObj;

	private SessionFactory buildSessionFactoryDeprecated() {
		// Creating Configuration Instance & Passing Hibernate Configuration File
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");

		// Since Hibernate Version 4.x, ServiceRegistry Is Being Used
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();

		// Creating Hibernate SessionFactory Instance
		sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;
	}

	private SessionFactory buildSessionFactory() {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		return sf;
	}

	public void createClients() {
		System.out.println("Creating clients\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			for (int i = 101; i <= 105; i++) {
				userObj = new User();
				userObj.setUserid(i);
				userObj.setUsername("Editor " + i);
				userObj.setCreatedBy("Administrator");
				userObj.setCreatedDate(new Date());

				sessionObj.save(userObj);
			}

			System.out.println("\nRecords Saved Successfully To The Database\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != sessionObj.getTransaction()) {
				System.out.println("\nTransaction Is Being Rolled Back");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	public void createProductsAndPacks() {
		System.out.println("Creating products and packs.\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();
			Product product;

			for (int i = 101; i <= 105; i++) {
				product = new Product();
				product.setProductid(i);
				product.setProductname("Editor " + i);
				product.setCreatedBy("Administrator");
				product.setCreatedDate(new Date());

				sessionObj.save(product);
			}
			Pack pack;
			for (int i = 101; i <= 105; i++) {
				pack = new Pack();
				pack.setPackid(i);
				pack.setPackname("Editor " + i);
				pack.setCreatedBy("Administrator");
				pack.setCreatedDate(new Date());

				sessionObj.save(pack);
			}
			System.out.println("\nRecords Saved Successfully To The Database.\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (null != sessionObj.getTransaction()) {
				System.out.println("\nTransaction Is Being Rolled Back.");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	public void createUserProducts() {
		System.out.println("Creating user's products.\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			int i = 1001;
			User user;
			user = new User(i, "Editor " + i, "Administrator");
			sessionObj.save(user);

			Product product;
			for (i = 1001; i <= 1005; i++) {
				product = new Product(i, "Product " + i, "Administrator");
				sessionObj.save(product);
				user.addProduct(product);
			}

			// sessionObj.save(product);
			// sessionObj.persist(user);

			System.out.println("\nRecords Saved Successfully To The Database.\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (sessionObj != null && null != sessionObj.getTransaction()) {
				System.out.println("\nTransaction Is Being Rolled Back.");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}

	public void createUserPackWithProducts() {
		System.out.println("Creating user's products.\n");
		try {
			sessionObj = buildSessionFactory().openSession();
			sessionObj.beginTransaction();

			int i = 2001;
			User user;
			user = new User(i, "Editor " + i, "Administrator");
			sessionObj.save(user);

			Pack pack;
			pack = new Pack(i, "Pack " + i, "Administrator");
			sessionObj.save(pack);
			user.addPack(pack);

			Product product;
			for (i = 2001; i <= 2005; i++) {
				product = new Product(i, "Product " + i, "Administrator");
				sessionObj.save(product);
				pack.addProduct(product);
			}

			
			// sessionObj.save(product);
			// sessionObj.persist(user);

			System.out.println("\nRecords Saved Successfully To The Database.\n");

			// Committing The Transactions To The Database
			sessionObj.getTransaction().commit();
		} catch (Exception sqlException) {
			sqlException.printStackTrace();
			if (sessionObj != null && null != sessionObj.getTransaction()) {
				System.out.println("\nTransaction Is Being Rolled Back.");
				sessionObj.getTransaction().rollback();
			}
			sqlException.printStackTrace();
		} finally {
			if (sessionObj != null) {
				sessionObj.close();
			}
		}
	}
}
