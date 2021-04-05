import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionA {

	public final static String name = "TA";

	public void doTA() throws Exception {

		Connection con;
		// TRANSACTION NONE is not iplemented !!!
		// con = MyUtils.getConnection(Connection.TRANSACTION_NONE);
		// con = MyUtils.getConnection(Connection.TRANSACTION_READ_UNCOMMITTED);
		// con = MyUtils.getConnection(Connection.TRANSACTION_READ_COMMITTED);
		// con = MyUtils.getConnection(Connection.TRANSACTION_REPEATABLE_READ);
		con = MyUtils.getConnection(Connection.TRANSACTION_SERIALIZABLE);

		System.out.println("TA : reset employee extension to x1234");
		MyUtils.resetEmployeeExtension(con);
		con.commit();

		MyUtils.readKey(name, "ready to start ?");
		MyUtils.selectAllEmployee(con);
		MyUtils.readKey(name, "update extension ?");
		MyUtils.updateEmployeeExtension(name, con);
		MyUtils.readKey(name, "select employee ?");
		MyUtils.selectAllEmployee(con);
		MyUtils.readKey(name, "end ?");
		con.commit();
		con.close();
	}

	public static void main(String[] args) {
		System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		System.out.println("Adapt the bin folder if code is in GIT !!!!!!!!!!!!!!");
		System.out.println("Otherwise you run old code ...");
		System.out.println("");
		System.out.println(
				"cd ~/eclipse-workspace/IsolationLevel/bin; java -cp .:/usr/share/java/mariadb-java-client.jar TransactionA");
		TransactionA ta = new TransactionA();
		try {
			ta.doTA();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
