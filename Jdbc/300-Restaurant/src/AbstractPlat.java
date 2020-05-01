import java.net.NetworkInterface;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Logger;

public abstract class AbstractPlat extends Plat {
	// assumes the current class is called MyUtils
	private final static Logger LOGGER = Logger.getLogger(MyUtils.class.getName());

	protected Integer platImplId;
	protected Integer platId;

	/*******************************************************************/

	/**
	 * Public constructor does not include the id.
	 * 
	 * @param name
	 * @param anniversary
	 */
	public AbstractPlat(String name, int platId) {
		super(name);
		this.platId = platId;
	}

	/**
	 * @param name
	 */
	public AbstractPlat(String name) {
		super(name);
	}

	/**
	 * Private constructor includes the id.
	 * 
	 * @param id
	 * @param name
	 * @param anniversary
	 * @param platId
	 */
	protected AbstractPlat(int platImplId, String name, int platId) {
		this(name, platId);
		this.platImplId = platImplId;
		this.platId = platId;
	}

	/**
	 * Default construction. Access restricted as much as possible.
	 */
	protected AbstractPlat() {
		super();
	}

	/*******************************************************************/

	protected AbstractPlat(int id) {
		platImplId = id;
	}

	/**
	 * Save the current subclass of plat on the given connection.
	 * 
	 * Commit the transaction inside the method.
	 * 
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public int save() throws ClassNotFoundException, SQLException {
		Connection con = MyUtils.getNewConnection();

		platId = super.save(con);
		platImplId = save(con);

		con.commit();
		return platImplId;
	}

	/**
	 * Save the current subclass of plat on the given connection.
	 * 
	 * When a connection is given as a parameter, the transaction is driven outside
	 * of the method.
	 * 
	 * @see Plat#save(java.sql.Connection)
	 */
	public int save(Connection con) throws ClassNotFoundException, SQLException {
		LOGGER.info("Inserting dessert  : " + name);

		// String sql = "insert into dessert (anniversary, plat_id) values (?, ?)";

		String sql = getInsertSql();
		PreparedStatement stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

		setValues(stmt);
		// stmt.setBoolean(1, anniversary);
		// stmt.setInt(2, platId);

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

	// JDBC method to be able to insert object from database.

	protected abstract String getInsertSql();

	protected abstract void setValues(PreparedStatement stmt) throws SQLException;

	/*******************************************************************/

	@Override
	public String toString() {
		return "" + getClass().getName() + "[id=" + platImplId + ",\t name=" + name + ",\t platId=" + platId + "]";
	}

	public static AbstractPlat loadPlat(int id) throws ClassNotFoundException, SQLException {

		LOGGER.info("Load the plat using the plat id: " + id);

		Connection con = MyUtils.getNewConnection();

		String query = "select p.id, p.name, e.id, e.plat_id, pp.id, pp.plat_id, d.id, d.anniversary, d.plat_id "
				+ "	FROM plat p, entree e, platprincipal pp, dessert d "
				+ "	where ((p.id = e.plat_id) or (p.id = pp.plat_id) or (p.id = d.plat_id)) and p.id = ?";
		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, id);

		ResultSet rs = stmt.executeQuery();
		if (rs.next()) {
			int tmpPlatId = rs.getInt("p.id");
			int tmpEntreeId = rs.getInt("e.id");
			int tmpEntreePlatId = rs.getInt("e.plat_id");
			int tmpPlatprincipalId = rs.getInt("pp.id");
			int tmpPlatprincipalPlatId = rs.getInt("pp.plat_id");
			int tmpDessertId = rs.getInt("d.id");
			int tmpDessertPlatId = rs.getInt("d.plat_id");
			LOGGER.fine("###############");
			LOGGER.fine("Plat id: " + tmpPlatId);
			LOGGER.fine("Entree id: " + tmpEntreeId);
			LOGGER.fine("Entree platid: " + tmpEntreePlatId);
			LOGGER.fine("Platprincipal id: " + tmpPlatprincipalId);
			LOGGER.fine("Platprincipal platid: " + tmpPlatprincipalPlatId);
			LOGGER.fine("Dessert id: " + tmpDessertId);
			LOGGER.fine("Dessert platid: " + tmpDessertPlatId);
			LOGGER.fine("Plat Name: " + rs.getString("p.name"));

			AbstractPlat ap = null;
			if (tmpPlatId == tmpEntreePlatId) {
				ap = new Entree(tmpEntreeId, rs.getString("p.name"), rs.getInt("e.plat_id"));
				LOGGER.info("AP: " + ap);
				return ap;
			}
			if (tmpPlatId == tmpPlatprincipalPlatId) {
				ap = new PlatPrincipal(tmpPlatprincipalId, rs.getString("p.name"), rs.getInt("pp.plat_id"));
				LOGGER.info("AP: " + ap);
				return ap;

			}
			if (tmpPlatId == tmpDessertPlatId) {
				ap = new Dessert(tmpDessertId, rs.getString("p.name"), rs.getBoolean("d.anniversary"),
						rs.getInt("d.plat_id"));
				LOGGER.info("AP: " + ap);
				return ap;

			}

		}
		return null;
	}

	/*******************************************************************/

	public void load() throws ClassNotFoundException, SQLException {

		Connection con = MyUtils.getConnection();

		String query = getSelectByIdSql();

		PreparedStatement stmt = con.prepareStatement(query);
		stmt.setInt(1, platImplId);

		ResultSet rs = stmt.executeQuery();

		if (rs.next()) {
			setAttributes(rs);
		}

	}

	protected abstract String getSelectByIdSql();

	protected void setAttributes(ResultSet rs) throws SQLException {
		name = rs.getString("p.name");
	}
}
