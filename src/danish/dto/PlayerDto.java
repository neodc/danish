package danish.dto;

import danish.view.img.Images;

/**
 * Class tranferring the informations of a player from the DB to the different
 * layers of the program.
 *
 * @author Noé, Julien, Loup.
 */
public class PlayerDto {

	private final int id;
	private final String name;
	private final Images.Style preferredStyle;
	private final boolean reverse;

	private final int nbGame;
	private final int nbVictory;
	private final float averageScore;

	/**
	 * PlayerDto constructor with all the informations in the DB.
	 *
	 * @param id The player's id.
	 * @param name The player's name.
	 * @param preferredStyle The player's preferred style.
	 * @param isReverse If the sort of the hand is reversed.
	 * @param nbGame The number of games the player played.
	 * @param nbVictory The number of games the players won.
	 * @param averageScore The player's average score.
	 */
	public PlayerDto(int id, String name, Images.Style preferredStyle, boolean isReverse, int nbGame, int nbVictory, float averageScore) {
		this.id = id;
		this.name = name;
		this.preferredStyle = preferredStyle;
		this.reverse = isReverse;
		this.nbGame = nbGame;
		this.nbVictory = nbVictory;
		this.averageScore = averageScore;
	}

	/**
	 * PlayerDto constructor without an id.
	 *
	 * @param name The player's name.
	 * @param preferredStyle The player's preferred style.
	 * @param isReverse If the sort of the hand is reversed.
	 * @param nbGame The number of games the player played.
	 * @param nbVictory The number of games the players won.
	 * @param averageScore The player's average score.
	 */
	public PlayerDto(String name, Images.Style preferredStyle, boolean isReverse, int nbGame, int nbVictory, float averageScore) {
		this(-1, name, preferredStyle, isReverse, nbGame, nbVictory, averageScore);
	}

	/**
	 * PlayerDto constructor without game stats.
	 *
	 * @param id The player's id.
	 * @param name The player's name.
	 * @param preferredStyle The player's preferred style.
	 * @param isReverse If the sort of the hand is reversed.
	 */
	public PlayerDto(int id, String name, Images.Style preferredStyle, boolean isReverse) {
		this(id, name, preferredStyle, isReverse, -1, -1, -1);
	}

	/**
	 * PlayerDto constructor without id nor game stats.
	 *
	 * @param name The player's name.
	 * @param preferredStyle The player's preferred style.
	 * @param isReverse If the sort of the hand is reversed.
	 */
	public PlayerDto(String name, Images.Style preferredStyle, boolean isReverse) {
		this(-1, name, preferredStyle, isReverse);
	}

	/**
	 * PlayerDto constructor with only an id.
	 *
	 * @param name The player's name.
	 */
	public PlayerDto(String name) {
		this(name, Images.Style.PONY, false);
	}

	/**
	 * Returns the player's id.
	 *
	 * @return The player's id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Returns the player's name.
	 *
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the player's preferred style.
	 *
	 * @return The player's preferred style.
	 */
	public Images.Style getPreferredStyle() {
		return preferredStyle;
	}

	/**
	 * Returns if the sort must be reversed.
	 *
	 * @return If the sort must be reversed.
	 */
	public boolean isReverse() {
		return reverse;
	}

	/**
	 * Returns the number of games the player played.
	 *
	 * @return The number of games the player played.
	 */
	public int getNbGame() {
		return nbGame;
	}

	/**
	 * Returns the number of games the player won.
	 *
	 * @return The number of games the player won.
	 */
	public int getNbVictory() {
		return nbVictory;
	}

	/**
	 * Returns the player's average score.
	 *
	 * @return The player's average score.
	 */
	public float getAverageScore() {
		return averageScore;
	}
}
