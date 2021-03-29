import java.sql.Connection;

public class TransactionB {

	public final static String name = "TB";

	public void doTB() throws Exception {
		
		Connection con;
		// con = MyUtils.getConnection(Connection.TRANSACTION_NONE);
		// con = MyUtils.getConnection(Connection.TRANSACTION_READ_UNCOMMITTED);
		con = MyUtils.getConnection(Connection.TRANSACTION_READ_COMMITTED);
		// con = MyUtils.getConnection(Connection.TRANSACTION_REPEATABLE_READ);
		// con = MyUtils.getConnection(Connection.TRANSACTION_SERIALIZABLE);

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
		System.out.println(
				"cd ~/eclipse-workspace/IsolationLevel/bin; java -cp .:/usr/share/java/mariadb-java-client.jar TransactionB");
		TransactionB ta = new TransactionB();
		try {
			ta.doTB();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
