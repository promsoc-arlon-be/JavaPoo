import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dessert extends AbstractPlat {

	private boolean anniversary;

	/**
	 * Public constructor does not include the id.
	 * 
	 * @param name
	 * @param anniversary
	 */
	public Dessert(String name, boolean anniversary) {
		super(name);
		this.anniversary = anniversary;
	}

	/**
	 * Private constructor includes the id.
	 * 
	 * @param id
	 * @param name
	 * @param anniversary
	 * @param platId
	 */
	protected Dessert(int dessertId, String name, boolean anniversary, int platId) {
		super(dessertId, name, platId);
		this.anniversary = anniversary;
	}

	/**
	 * Empty constructor is protected, because it does not any values to the outside
	 * world.
	 */
	protected Dessert() {
		super();
	}

	protected Dessert(int i) {
		super(i);
	}

	/*******************************************************************/

	// The following to methods have been generalized to AbstractPlat
	//
	//
	// public int save() throws ClassNotFoundException, SQLException {
	// Connection con = MyUtils.getConnection();
	//
	// platId = super.save(con);
	// dessertId = save(con);
	//
	// con.commit();
	// return dessertId;
	// }

	// public int save(Connection con) throws ClassNotFoundException, SQLException {
	// System.out.println("Inserting dessert : " + name);
	//
	// String sql = "insert into dessert (anniversary, plat_id) values (?, ?)";
	//
	// PreparedStatement stmt = con.prepareStatement(sql,
	// Statement.RETURN_GENERATED_KEYS);
	//
	// stmt.setBoolean(1, anniversary);
	// stmt.setInt(2, platId);
	//
	// int affectedRows = stmt.executeUpdate();
	//
	// if (affectedRows == 0) {
	// throw new SQLException("Save failed, no rows affected.");
	// }
	// int i;
	// try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	// if (generatedKeys.next()) {
	// i = generatedKeys.getInt(1);
	// System.out.println("LastInsertId is: " + i);
	// } else {
	// throw new SQLException("Save failed, no ID obtained.");
	// }
	// }
	// return i;
	// }

	/*******************************************************************/

	/**
	 * How can I generalize the selectAllStaticClassic static method ?
	 * 
	 * Option 1 : Can I call super in a static method ? No
	 * 
	 * Option 2 : Can I use subclass delegation with static method ? No
	 * 
	 * Take care that abstract method cannot be static in java !!!
	 * 
	 * When a call to a static method, only the method of the current class can be
	 * used. There is not way to invoke the one of the subclass !!!
	 * 
	 * @return
	 */

	static public void selectAllStaticClassic() throws ClassNotFoundException, SQLException {
		System.out.println("Displaying all desserts using a simple static method");
		Connection con = MyUtils.getNewConnection();
		Statement stmt = con.createStatement();

		String query = "select p.id, p.name, d.id, d.anniversary, d.plat_id FROM plat p, dessert d where p.id = d.plat_id";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {

			Dessert f = new Dessert(rs.getInt("d.id"), rs.getString("p.name"), rs.getBoolean("d.anniversary"),
					rs.getInt("d.plat_id"));
			System.out.println(f);
		}
	}

	/**
	 * Activate the "// super.selectAllBasic();" code and see if compiler is happy
	 * ;-(.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static public void selectAllBasicStaticBad() throws ClassNotFoundException, SQLException {
		System.out.println("Displaying all dessert");
		// Heritance cannot be used when working with static !!!
		//
		// super.selectAllBasic();
		Connection con = MyUtils.getConnection();
		Statement stmt = con.createStatement();

		String query = "select p.id, p.name, d.id, d.anniversary, d.plat_id FROM plat p, dessert d where p.id = d.plat_id";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {

			Dessert f = new Dessert(rs.getInt("d.id"), rs.getString("p.name"), rs.getBoolean("d.anniversary"),
					rs.getInt("d.plat_id"));
			System.out.println(f);
		}
	}

	/*******************************************************************/

	/**
	 * In order to use AbstractPlat.selectAll, we need to instanciate a Dessert
	 * object.
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	static public void selectAllStatic() throws ClassNotFoundException, SQLException {
		Dessert d = new Dessert();
		ArrayList<Plat> al = d.selectAll();
		System.out.println("\tSize : " + al.size());
	}

	/*******************************************************************/

	protected String getSelectSql() {
		return "select p.id, p.name, d.id, d.anniversary, d.plat_id FROM plat p, dessert d where p.id = d.plat_id";
	}

	protected String getSelectByIdSql() {
		return "select p.id, p.name, d.id, d.anniversary, d.plat_id FROM plat p, dessert d where p.id = d.plat_id and d.id = ?";
	}

	@Override
	protected Plat newInstance(ResultSet rs) throws SQLException {

		return new Dessert(rs.getInt("d.id"), rs.getString("p.name"), rs.getBoolean("d.anniversary"),
				rs.getInt("d.plat_id"));
	}

	/*******************************************************************/

	@Override
	protected String getInsertSql() {
		String sql = "insert into dessert (anniversary, plat_id) values (?, ?)";
		return sql;
	}

	@Override
	protected void setValues(PreparedStatement stmt) throws SQLException {
		stmt.setBoolean(1, anniversary);
		stmt.setInt(2, platId);
	}

	/*******************************************************************/
	@Override
	public String toString() {
		return "" + getClass().getName() + "[id=" + platImplId + ",\t name=" + name + ",\t anniversary=" + anniversary
				+ ",\t platId=" + platId + "]";
	}

	/*******************************************************************/
	protected void setAttributes(ResultSet rs) throws SQLException {
		super.setAttributes(rs);
		// The method is needed just to have the correct column names !!!
		platImplId = rs.getInt("d.id");
		platId = rs.getInt("d.plat_id");
		anniversary = rs.getBoolean("d.anniversary");
	}
}
