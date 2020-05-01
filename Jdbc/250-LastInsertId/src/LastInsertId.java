import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class LastInsertId {

	public final static String name = "LastInsertId";

	public void animalLastInsertId() throws Exception {

		Connection con;
		con = MyUtils.getConnection(Connection.TRANSACTION_SERIALIZABLE);

		MyUtils.createTable(con);
		System.out.println("Animal table created");

		String y = MyUtils.readKey(name, "Delete all animals ? Type yes");
		if (y.equals("yes"))
			MyUtils.deleteAnimals(con);

		MyUtils.readKey(name, "Ready to insert animals ?");
		MyUtils.insertAnimal("dog", con);
		MyUtils.insertAnimal("cat", con);
		MyUtils.insertAnimal("pingouin", con);
		System.out.println("Three animals inserted");
		con.commit();

		MyUtils.readKey(name, "Ready to display animals ?");
		
		MyUtils.selectAllAnimals(con);

		con.close();
	}

	public static void main(String[] args) {
		LastInsertId lid = new LastInsertId();
		try {
			lid.animalLastInsertId();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
