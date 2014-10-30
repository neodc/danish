package danish.model;

/**
 * An enumeration of the 13 possible values of a card.
 *
 * @author Noé, Julien, Loup.
 */
public enum Rank {

	TWO(2, "Two"),
	THREE(3, "Three"),
	FOUR(4, "Four"),
	FIVE(5, "Five"),
	SIX(6, "Six"),
	SEVEN(7, "Seven"),
	EIGHT(8, "Eight"),
	NINE(9, "Nine"),
	TEN(10, "Ten"),
	JACK(11, "Jack"),
	QUEEN(12, "Queen"),
	KING(13, "King"),
	ACE(14, "Ace");

	private final int value;
	private final String display;

	/**
	 * Rank constructor with two parameters.
	 *
	 * @param value A number representing the power of the card.
	 * @param display A string representing the name of the rank.
	 */
	private Rank(int value, String display) {
		this.value = value;
		this.display = display;
	}

	/**
	 * Getter of the value.
	 *
	 * @return The value.
	 */
	public int getValue() {
		return this.value;
	}

	/**
	 * Getter of the display.
	 *
	 * @return The display.
	 */
	public String getDisplay() {
		return this.display;
	}

	/**
	 * A textual representation of the rank.
	 *
	 * @return The textual representation of the rank.
	 */
	@Override
	public String toString() {
		return display;
	}
}
