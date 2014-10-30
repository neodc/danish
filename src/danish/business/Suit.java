package danish.business;

/**
 * An enumeration of the 4 possible suits of a card.
 *
 * @author Noé, Julien, Loup.
 */
public enum Suit {

	HEART("Heart"),
	DIAMOND("Diamond"),
	CLUB("Club"),
	SPADE("Spade");

	public final String display;

	/**
	 * Suit constructor with one parameter.
	 *
	 * @param display A string representing the suit of the card.
	 */
	private Suit(String display) {
		this.display = display;
	}

	/**
	 * Getter of the display.
	 *
	 * @return The display.
	 */
	public String getDisplay() {
		return display;
	}

	/**
	 * A textual representation of the suit.
	 *
	 * @return The textual representation of the suit.
	 */
	@Override
	public String toString() {
		return display;
	}
}
