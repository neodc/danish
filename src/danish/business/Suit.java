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

	private Suit(String display) {
		this.display = display;
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
