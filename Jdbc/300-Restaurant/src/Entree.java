import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Entree extends AbstractPlat {

	/**
	 * Public constructor does not include the id.
	 * 
	 * @param name
	 * @param anniversary
	 */

	public Entree(String name) {
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

	protected Entree(int entreeId, String name, int platId) {
		super(entreeId, name, platId);
	}

	/**
	 * Empty constructor is protected, because it does not any values to the outside
	 * world.
	 */
	protected Entree() {
	}

	protected Entree(int i) {
		super(i);
	}

	/*******************************************************************/

	static public void selectAllStatic() throws ClassNotFoundException, SQLException {
		Entree e = new Entree();
		ArrayList<Plat> al = e.selectAll();
		System.out.println("\tSize : " + al.size());
	}

	/*******************************************************************/

	@Override
	protected String getSelectSql() {
		return "select p.id, p.name, e.id, e.plat_id FROM plat p, entree e where p.id = e.plat_id";
	}

	protected String getSelectByIdSql() {
		return "select p.id, p.name, e.id, e.plat_id FROM plat p, entree e where p.id = e.plat_id and e.id = ?";
	}

	@Override
	protected Plat newInstance(ResultSet rs) throws SQLException {
		return new Entree(rs.getInt("e.id"), rs.getString("p.name"), rs.getInt("e.plat_id"));
	}

	/*******************************************************************/

	@Override
	protected String getInsertSql() {
		String sql = "insert into entree (plat_id) values (?)";
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
		platImplId = rs.getInt("e.id");
		platId = rs.getInt("e.plat_id");
	}
	/*******************************************************************/

}
