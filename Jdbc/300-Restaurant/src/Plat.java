import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

/**
 * @author pva
 *
 */
public abstract class Plat {
	// assumes the current class is called MyUtils
	private final static Logger LOGGER = Logger.getLogger(Plat.class.getName());

	Integer id;
	String name;

	/**
	 * Public constructor does not include the id.
	 * 
	 * @param name
	 * @param anniversary
	 */
	public Plat(String name) {
		super();
		this.name = name;
	}

	/**
	 * Private constructor includes the id.
	 * 
	 * @param id
	 * @param name
	 * @param anniversary
	 * @param platId
	 */
	private Plat(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * Empty constructor is protected, because it does not any values to the outside
	 * world.
	 */
	protected Plat() {
	}

	/*******************************************************************/

	protected Plat(int id) {
		this.id = id;
	}

	/**
	 * Save the current plat on the given connection.
	 * 
	 * When a connection is given as a parameter, the transaction is driven outside
	 * of the method.
	 * 
	 * @param con
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int save(Connection con) throws ClassNotFoundException, SQLException {

		System.out.println("Saving plat : " + name);

		String sql = "insert into plat (name) values (?)";

		PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setString(1, name);

		int affectedRows = stmt.executeUpdate();

		if (affectedRows == 0) {
			throw new SQLException("Save failed, no rows affected.");
		}
		int i;
		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				i = generatedKeys.getInt(1);
				LOGGER.fine("\tLastInsertId is: " + i);

			} else {
				throw new SQLException("Save failed, no ID obtained.");
			}
		}
		return i;
	}

	/*******************************************************************/

	@Override
	public String toString() {
		return "Plat [id=" + id + ", name=" + name + "]";
	}

	/*******************************************************************/

	// The select all mechanism is general to all object that we use

	/**
	 * Select all objects from the database
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public ArrayList<Plat> selectAll() throws ClassNotFoundException, SQLException {
		selectAllOnEntry();

		Connection con = MyUtils.getConnection();
		Statement stmt = con.createStatement();

		String query = getSelectSql();
		ResultSet rs = stmt.executeQuery(query);
		ArrayList<Plat> al = new ArrayList<Plat>();
		while (rs.next()) {

			Plat p = newInstance(rs);
			al.add(p);
			System.out.println(p);
		}
		// con.rollback();
		return al;
	}

	/**
	 * Display a message when selecting all the object.
	 */
	protected void selectAllOnEntry() {
		System.out.println("Displaying all " + getClass().getName());
	}

	/*******************************************************************/

	/**
	 * Provide the sql select statement is subclass responsibility
	 * 
	 * @return
	 */
	protected abstract String getSelectSql();

	/**
	 * Create a new instance is subclass responsibility
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	protected abstract Plat newInstance(ResultSet rs) throws SQLException;

	/*******************************************************************/
	/*******************************************************************/
	public void load() throws ClassNotFoundException, SQLException {

		Connection con = MyUtils.getConnection();

		String query = "select p.id, p.name FROM plat p where p.id = ?";

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery(query);
		if (rs.next()) {
			setAttributes(rs);
		}
	}

	protected void setAttributes(ResultSet rs) throws SQLException {
		id = rs.getInt("p.id");
		name = rs.getString("p.name");
	}

}
