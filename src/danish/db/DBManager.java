package danish.db;

import java.sql.*;

/**
 * Offre les outils de connexion et de gestion de transaction.
 */
public class DBManager {

	private static Connection connection;
	private static DBConnection dbConnection;

	/**
	 * Cree une connexion a partir des elements donnes
	 */
	private static void setConnection() throws DBException {
		try {
			dbConnection = DBConnection.JAVADB;
			Class.forName(dbConnection.getDriver());
			connection = DriverManager.getConnection(dbConnection.getUrl(), dbConnection.getUid(), dbConnection.getPsw());
			connection.setAutoCommit(true);
		} catch (ClassNotFoundException | SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Probleme de connexion.  \n " + ex.getMessage()); // TODO trad

		}
	}

	/**
	 * Retourne la connexion etablie ou a defaut, l'etablit
	 *
	 * @return la connexion etablie.
	 * @throws danish.db.DBException
	 */
	public static Connection getConnection() throws DBException {
		if (connection == null) {
			setConnection();
		}
		return connection;
	}

	/**
	 * debute une transaction
	 *
	 * @throws danish.db.DBException
	 */
	public static void startTransaction() throws DBException {
		try {
			getConnection().setAutoCommit(false);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Impossible de demarrer une transaction"); // TODO trad
		}
	}

	/**
	 * valide la transaction courante
	 *
	 * @throws danish.db.DBException
	 */
	public static void valideTransaction() throws DBException {
		try {
			getConnection().commit();
			getConnection().setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Impossible de valider la transaction"); // TODO trad
		}
	}

	/**
	 * annule la transaction courante
	 *
	 * @throws danish.db.DBException
	 */
	public static void annuleTransaction() throws DBException {
		try {
			getConnection().rollback();
			getConnection().setAutoCommit(true);
		} catch (SQLException ex) {
			ex.printStackTrace();
			throw new DBException("Impossible d'annuler la transaction"); // TODO trad
		}
	}
}
