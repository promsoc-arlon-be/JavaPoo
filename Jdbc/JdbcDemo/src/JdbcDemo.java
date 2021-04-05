
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcDemo {
	public static void main(String args[]) {
		String dbUrl = "jdbc:mysql://localhost/classicmodels?user=classicmodels&password=classicmodels";
		String dbClass = "org.mariadb.jdbc.Driver";
		String query = "select employeeNumber, firstName, lastName FROM employees";
		try {
			Class.forName(dbClass);
			Connection con = DriverManager.getConnection(dbUrl);
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int employeeNumber = rs.getInt("employeeNumber");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				System.out.println("Employee " + employeeNumber + " " + firstName + " " + lastName);
			}
			// end while
			con.close();
		}
//end try
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// end main
}
// end class
