import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MyUtils {
	static public String readKey(String name, String msg) throws IOException {
		System.out.println(name + " : " + msg + " : Hit enter");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;

	}

	static public Connection getConnection(int transactionLevel) throws ClassNotFoundException, SQLException {
		String dbUrl = "jdbc:mysql://localhost/classicmodels?user=classicmodels&password=classicmodels";
		String dbClass = "org.mariadb.jdbc.Driver";
		Class.forName(dbClass);
		Connection con = DriverManager.getConnection(dbUrl);
		con.setAutoCommit(false);
		con.setTransactionIsolation(transactionLevel);

		return con;
	}

	static public void selectAllEmployee(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		String query = "select employeeNumber, firstName, lastName, extension FROM employees";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			int employeeNumber = rs.getInt("employeeNumber");
			String firstName = rs.getString("firstName");
			String lastName = rs.getString("lastName");
			String extension = rs.getString("extension");

			System.out.println("Employee " + employeeNumber + " " + firstName + " " + lastName + " " + extension);
		}

	}

	static public void updateEmployeeExtension(String msg, Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		String query = "update employees set extension  = concat(extension,'" + msg
				+ "') where firstName='Martin' and lastName='Gerard'";
		int i = stmt.executeUpdate(query);

	}

	static public void resetEmployeeExtension(Connection con) throws ClassNotFoundException, SQLException {

		Statement stmt = con.createStatement();
		String query = "update employees set extension  = 'x1234' where firstName='Martin' and lastName='Gerard'";
		int i = stmt.executeUpdate(query);

	}

}
