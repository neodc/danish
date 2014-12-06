package danish.dto;

/**
 * Class tranferring the informations of a game from the DB to the different
 * layers of the program.
 *
 * @author Noé, Julien, Loup.
 */
public class GameDto {

	private int id;
	private int playerId;
	private boolean victory;
	private int score;
	private int nbCardsPlayed;
	private int nbOpponents;

	/**
	 * GameDto constructor with all the informations in the DB.
	 *
	 * @param id The id of the game.
	 * @param playerId The player's id.
	 * @param victory If the player has won the game.
	 * @param score The player's score.
	 * @param nbCardsPlayed The number of cards that have been played.
	 * @param nbOpponents The number of opponents the player has faced.
	 */
	public GameDto(int id, int playerId, boolean victory, int score, int nbCardsPlayed, int nbOpponents) {
		this.id = id;
		this.playerId = playerId;
		this.victory = victory;
		this.score = score;
		this.nbCardsPlayed = nbCardsPlayed;
		this.nbOpponents = nbOpponents;
	}

	/**
	 * GameDto constructor without the id of a game.
	 *
	 * @param playerId The player's id.
	 * @param victory If the player has won the game.
	 * @param score The player's score.
	 * @param nbCardsPlayed The number of cards that have been played.
	 * @param nbOpponents The number of opponents the player has faced.
	 */
	public GameDto(int playerId, boolean victory, int score, int nbCardsPlayed, int nbOpponents) {
		this(-1, playerId, victory, score, nbCardsPlayed, nbOpponents);
	}

	/**
	 * GameDto constructor without the id of a game nor its player.
	 *
	 * @param victory If the player has won the game.
	 * @param score The player's score.
	 * @param nbCardsPlayed The number of cards that have been played.
	 * @param nbOpponents The number of opponents the player has faced.
	 */
	public GameDto(boolean victory, int score, int nbCardsPlayed, int nbOpponents) {
		this(-1, -1, victory, score, nbCardsPlayed, nbOpponents);
	}

	/**
	 * Returns the id of the game.
	 *
	 * @return The id of the game.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the player's id.
	 *
	 * @return The player's id.
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * Returns if the player won the game.
	 *
	 * @return If the player won the game.
	 */
	public boolean isVictory() {
		return victory;
	}

	/**
	 * Returns the player's score.
	 *
	 * @return The player's score.
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns the number of played cards.
	 *
	 * @return The number of played cards.
	 */
	public int getNbCardsPlayed() {
		return nbCardsPlayed;
	}

	/**
	 * Returns the number of opponents.
	 *
	 * @return The number of opponents.
	 */
	public int getNbOpponents() {
		return nbOpponents;
	}
}
