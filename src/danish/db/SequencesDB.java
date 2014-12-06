package danish.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class used for communications with the DB that concers the SEQUENCES table.
 *
 * @author Noé, Julien, Loup.
 */
public class SequencesDB {

	/**
	 * Gets the next free id of a chosen table then increments it.
	 *
	 * @param id The table whose next id is needed.
	 * @return The next free id.
	 * @throws DBException If something goes wrong.
	 */
	public synchronized static int getNextId(String id) throws DBException {

		try {
			String q1 = "SELECT valeur FROM sequences WHERE id = ?";
			String q2 = "UPDATE sequences SET valeur=valeur+1 WHERE id = ?";
			Connection connexion = DBManager.getConnection();

			PreparedStatement stmt = connexion.prepareStatement(q1);
			stmt.setString(1, id);

			ResultSet rs = stmt.executeQuery();

			rs.next();

			int ret = rs.getInt("valeur");

			stmt = connexion.prepareStatement(q2);
			stmt.setString(1, id);

			stmt.executeUpdate();

			return ret;

		} catch (DBException | SQLException eSQL) {
			throw new DBException("ERR:\nSQLException: " + eSQL.getMessage());
		}

	}

}
