import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlatPrincipal extends AbstractPlat {

	/**
	 * Public constructor does not include the id.
	 * 
	 * @param name
	 * @param anniversary
	 */

	public PlatPrincipal(String name) {
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

	protected PlatPrincipal(int platPrincipalId, String name, int platId) {
		super(platPrincipalId, name, platId);
	}

	/**
	 * Empty constructor is protected, because it does not any values to the outside
	 * world.
	 */
	protected PlatPrincipal() {
	}

	protected PlatPrincipal(int i) {
		super(i);
	}

	/*******************************************************************/

	static public void selectAllStatic() throws ClassNotFoundException, SQLException {
		PlatPrincipal pp = new PlatPrincipal();
		ArrayList<Plat> al = pp.selectAll();
		System.out.println("Size : " + al.size());
	}

	/*******************************************************************/

	@Override
	protected String getSelectSql() {
		return "select p.id, p.name, pp.id, pp.plat_id FROM plat p, platprincipal pp where p.id = pp.plat_id";
	}

	protected String getSelectByIdSql() {
		return "select p.id, p.name, pp.id, pp.plat_id FROM plat p, platprincipal pp where p.id = pp.plat_id and pp.id = ?";
	}

	@Override
	protected Plat newInstance(ResultSet rs) throws SQLException {
		return new PlatPrincipal(rs.getInt("pp.id"), rs.getString("p.name"), rs.getInt("pp.plat_id"));
	}

	/*******************************************************************/

	@Override
	protected String getInsertSql() {
		String sql = "insert into platprincipal (plat_id) values (?)";
		return sql;
	}

	@Override
	protected void setValues(PreparedStatement stmt) throws SQLException {
		stmt.setInt(1, platId);
	}

	/*******************************************************************/
	protected void setAttributes(ResultSet rs) throws SQLException {
		super.setAttributes(rs);
		// The method is needed just to have the correct column names !!!
		platImplId = rs.getInt("pp.id");
		platId = rs.getInt("pp.plat_id");
	}

	/*******************************************************************/
}