import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Logger;

public class Menu {
	// assumes the current class is called MyUtils
	private final static Logger LOGGER = Logger.getLogger(Menu.class.getName());

	Integer id;
	String name;

	ArrayList<AbstractPlat> plats = new ArrayList<AbstractPlat>();

	/*******************************************************************/

	public Menu(String name) {
		this.name = name;
	}

	protected Menu(int id, String name) {
		this(name);
		this.id = id;
	}

	protected Menu(int id) {
		super();
		this.id = id;
	}

	/*******************************************************************/

	/**
	 * Save the current menu on the given connection
	 * 
	 * @param con
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int save(Connection con) throws ClassNotFoundException, SQLException {

		LOGGER.info("Saving menu : " + name);

		String sql = "insert into menu (name) values (?)";

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

	/**
	 * Save the current menu on the given connection.
	 * 
	 * Commit the transaction inside the method.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int save() throws ClassNotFoundException, SQLException {
		Connection con = MyUtils.getNewConnection();

		id = save(con);
		savePlats(con);

		con.commit();
		return id;
	}

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
		LOGGER.info("Displaying all menus using a simple static method");
		Connection con = MyUtils.getNewConnection();
		Statement stmt = con.createStatement();

		String query = "select m.id, m.name, mp.menu_id, mp.plat_id FROM menu m, menu_plat mp where m.id = mp.menu_id";
		ResultSet rs = stmt.executeQuery(query);
		int oldId = -1;
		int id;
		while (rs.next()) {

			Menu m = new Menu(rs.getInt("m.id"), rs.getString("m.name"));
			id = rs.getInt("mp.plat_id");
			if (id != oldId) {
				AbstractPlat ap = AbstractPlat.loadPlat(id);
				m.addPlat(ap);
			}

			System.out.println(m);

		}
	}

	/*******************************************************************/

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("" + getClass().getName() + "[id=" + id + ",\t name=" + name);
		Iterator<AbstractPlat> i = plats.iterator();
		sb.append("\n");
		while (i.hasNext()) {
			AbstractPlat ap = i.next();
			sb.append("\t");
			sb.append(ap);
			sb.append("\n");
		}
		sb.append("]");
		return sb.toString();
	}

	/*******************************************************************/

	static public int rand(int min, int max) {
		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		return randomNum;
	}

	public void addPlat(AbstractPlat p) {
		plats.add(p);
	}

	public void savePlats(Connection con) throws ClassNotFoundException, SQLException {
		Iterator<AbstractPlat> i = plats.iterator();
		while (i.hasNext()) {
			AbstractPlat ap = i.next();
			savePlat(ap, con);
		}
	}

	public int savePlat(AbstractPlat ap, Connection con) throws ClassNotFoundException, SQLException {

		LOGGER.fine("Saving plat in menu : " + name);

		String sql = "insert into menu_plat (menu_id, plat_id) values (?,?)";

		PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		stmt.setInt(1, id);
		stmt.setInt(2, ap.platId);

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

	public void load() throws ClassNotFoundException, SQLException {

		Connection con = MyUtils.getConnection();

		String query = getSelectByIdSql();

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();

		while (rs.next()) {
			LOGGER.fine("Menu id: " + rs.getInt("m.id"));
			LOGGER.fine("Menu name: " + rs.getString("m.name"));
			LOGGER.fine("Plat id: " + rs.getInt("mp.plat_id"));

			name = rs.getString("m.name");
			int platId = rs.getInt("mp.plat_id");
			AbstractPlat ap = AbstractPlat.loadPlat(platId);
			addPlat(ap);
		}
	}

	protected String getSelectByIdSql() {
		String query = "select m.id, m.name, mp.menu_id, mp.plat_id FROM menu m, menu_plat mp where m.id = mp.menu_id and m.id = ?";

		return query;
	}
}
