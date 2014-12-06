package danish.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SequencesDB {

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
