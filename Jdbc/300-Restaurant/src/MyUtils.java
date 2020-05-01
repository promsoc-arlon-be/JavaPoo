import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

public class MyUtils {
	// assumes the current class is called MyUtils
	private final static Logger LOGGER = Logger.getLogger(MyUtils.class.getName());

	/**
	 * Main connection for doing select.TRANSACTION_READ_COMMITTED isolation is a
	 * good level, if you don't want to wait.
	 * 
	 */
	static Connection con;

	/**
	 * Return a connection, do lazy initialization to delay connection creation as
	 * much as possible.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static public Connection getConnection() throws ClassNotFoundException, SQLException {
		if (con == null) {
			con = MyUtils.getNewConnection();
		}
		LOGGER.fine("\tConnection: " + con);

		return con;
	}

	/**
	 * Read a key on the keyboard.
	 * 
	 * @param name
	 * @param msg
	 * @return
	 * @throws IOException
	 */
	static public String readKey(String name, String msg) throws IOException {
		System.out.println("\n" + name + " : " + msg + " : Hit enter");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s = br.readLine();
		return s;
	}

	/**
	 * Creates a new connection. Set auto commit to false and set initialization
	 * level to what is given as argument.
	 * 
	 * @param transactionLevel
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static public Connection getNewConnection(int transactionLevel) throws ClassNotFoundException, SQLException {

		String dbUrl = "jdbc:mysql://localhost/restaurant?user=restaurant&password=anotherPassword";
		// String dbClass = "com.mysql.jdbc.Driver";
		String dbClass = "org.mariadb.jdbc.Driver";

		Class.forName(dbClass);
		Connection con = DriverManager.getConnection(dbUrl);
		con.setAutoCommit(false);
		con.setTransactionIsolation(transactionLevel);

		LOGGER.fine("\tConnection: " + con);

		return con;
	}

	/**
	 * Create a new connection. Set the default automatically.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static public Connection getNewConnection() throws ClassNotFoundException, SQLException {
		return getNewConnection(Connection.TRANSACTION_READ_COMMITTED);
	}
}
