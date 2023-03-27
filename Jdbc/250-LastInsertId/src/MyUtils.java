import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.PreparedStatement;

public class MyUtils {
	static public String readKey(String name, String msg) throws IOException {
		System.out.println("\n" + name + " : " + msg + " : Hit enter");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;

	}

	static public Connection getConnection(int transactionLevel) throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/classicmodels?user=classicmodels&password=classicmodels";
		// String dbClass = "com.mysql.jdbc.Driver";
		String dbClass = "org.mariadb.jdbc.Driver";

		Class.forName(dbClass);
		Connection con = DriverManager.getConnection(dbUrl);
		con.setAutoCommit(false);
		con.setTransactionIsolation(transactionLevel);

		return con;
	}

	static public void createTable(Connection con) {
		System.out.println("Creating animals table");
		Statement stmt;
		try {
			stmt = con.createStatement();
			String query = "CREATE TABLE animals (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, PRIMARY KEY (id))";
			stmt.execute(query);
		} catch (SQLException e) {
			// Ignore if table already exist
		}
	}

	static public void selectAllAnimals(Connection con) throws ClassNotFoundException, SQLException {
		System.out.println("Displaying all animals");

		Statement stmt = con.createStatement();
		String query = "select id, name FROM animals";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");

			System.out.println("\t" + id + " " + name);
		}

	}

	static public void insertAnimal(String name, Connection con) throws ClassNotFoundException, SQLException {
		System.out.println("Inserting an animal: " + name);

		String sql = "insert into animals " + "	(name) " + " values (?)";

		PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, name);

		int affectedRows = stmt.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Creating user failed, no rows affected.");
		}

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				System.out.println("LastInsertId is: " + generatedKeys.getLong(1));
			} else {
				throw new SQLException("Creating user failed, no ID obtained.");
			}
		}
	}

	static public void deleteAnimals(Connection con) {
		System.out.println("Deleting all animals");
		Statement stmt;
		try {
			stmt = con.createStatement();
			String query = "delete from animals";
			stmt.execute(query);
		} catch (SQLException e) {
			// Ignore any problem
			e.printStackTrace();
		}
	}

}
