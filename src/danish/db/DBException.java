package danish.db;

/**
 * Exception lancée lors d'un probléme d'accès au gestionnaire de persistance
 *
 * @author Noé, Julien, Loup.
 */
public class DBException extends Exception {

	public DBException() {
	}

	public DBException(String msg) {
		super(msg);
	}
}
