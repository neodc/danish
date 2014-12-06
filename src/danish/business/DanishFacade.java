package danish.business;

import danish.db.DBException;
import danish.db.DBManager;
import danish.db.GameDB;
import danish.db.PlayerDB;
import danish.dto.GameDto;
import danish.dto.PlayerDto;
import java.util.Collection;

/**
 * Facade Class used for interactions between the view and the database.
 *
 * @author Noé, Julien, Loup.
 */
public class DanishFacade {

	private static int currentPlayer = -1;

	/**
	 * Sets the current player.
	 *
	 * @param currentPlayer The current player's id.
	 */
	public static void setCurrentPlayer(int currentPlayer) {
		DanishFacade.currentPlayer = currentPlayer;
	}

	/**
	 * Gives a PlayerDto using the given id.
	 *
	 * @param id The player's id.
	 * @return The player's informations in a PlayerDto.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static PlayerDto getPlayer(int id) throws PersistanceException {

		try {
			DBManager.startTransaction();

			PlayerDto ret = PlayerDB.getPlayer(id);

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Gets the current player's informations.
	 *
	 * @return The current player's informations in a PlayerDto.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static PlayerDto getCurrentPlayer() throws PersistanceException {
		if (currentPlayer >= 0) {
			return getPlayer(currentPlayer);
		}

		return null;
	}

	/**
	 * Gives all the players' informations.
	 *
	 * @return A collection of PlayerDto containing the players' informations.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static Collection<PlayerDto> getAllPlayer() throws PersistanceException {

		try {
			DBManager.startTransaction();

			Collection<PlayerDto> ret = PlayerDB.getAllPlayer();

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Gets the informations of a game.
	 *
	 * @param id The id of the researched game.
	 * @return The informations of a game in a GameDto.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static GameDto getGame(int id) throws PersistanceException {

		try {
			DBManager.startTransaction();

			GameDto ret = GameDB.getGame(id);

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Gives the informations of all games.
	 *
	 * @return The informations of all games in a collection of GameDto.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static Collection<GameDto> getAllGame() throws PersistanceException {

		try {
			DBManager.startTransaction();

			Collection<GameDto> ret = GameDB.getAllGame();

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Creates a new player from a PlayerDto.
	 *
	 * @param p The PlayerDto that contains the informations needed.
	 * @return A PlayerDto containing the informations of the new player.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static PlayerDto createPlayer(PlayerDto p) throws PersistanceException {

		try {
			DBManager.startTransaction();

			PlayerDto ret = PlayerDB.createPlayer(p);

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Updates the current player's informations from a PlayerDto.
	 *
	 * @param p The PlayerDto containing the updated informations.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static void updateCurrentPlayer(PlayerDto p) throws PersistanceException {
		if (currentPlayer >= 0) {
			try {
				DBManager.startTransaction();

				PlayerDB.updatePlayer(currentPlayer, p);

				DBManager.valideTransaction();
			} catch (DBException ex1) {
				String msg = ex1.getMessage();
				try {
					DBManager.annuleTransaction();
				} catch (DBException ex) {
					msg = ex.getMessage() + "\n" + msg;
				} finally {
					throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
				}
			}
		}
	}

	/**
	 * Creates a new game from a GameDto.
	 *
	 * @param g The GameDto that contains the informations needed.
	 * @return A GameDto containing the informations of the new game.
	 * @throws PersistanceException If something goes wrong.
	 */
	public static GameDto createGame(GameDto g) throws PersistanceException {

		try {
			DBManager.startTransaction();

			GameDto ret = GameDB.createGame(g);

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}

	/**
	 * Creates a new game played by the current player.
	 *
	 * @param g The GameDto that contains the informations needed.
	 * @return A GameDto containing the informations of the new game.
	 * @throws PersistanceException
	 */
	public static GameDto createGame4current(GameDto g) throws PersistanceException {

		try {
			DBManager.startTransaction();

			PlayerDto p = getCurrentPlayer();
			GameDto tmp = new GameDto(p.getId(), g.isVictory(), g.getScore(), g.getNbCardsPlayed(), g.getNbOpponents());
			GameDto ret = GameDB.createGame(tmp);

			DBManager.valideTransaction();

			return ret;
		} catch (DBException ex1) {
			String msg = ex1.getMessage();
			try {
				DBManager.annuleTransaction();
			} catch (DBException ex) {
				msg = ex.getMessage() + "\n" + msg;
			} finally {
				throw new PersistanceException("Impossible de modifier les stats du mot \n" + msg); // TODO
			}
		}

	}
}
