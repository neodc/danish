package danish.business;

/**
 * An enumeration of the 4 possible suits of a card.
 *
 * @author Noé, Julien, Loup.
 */
public enum Suit {

	HEART, DIAMOND, CLUB, SPADE;

	/**
	 * A textual representation of the suit.
	 *
	 * @return The textual representation of the suit.
	 */
	@Override
	public String toString() {
		String suit = null;
		switch (this) {
			case HEART:
				suit = "Heart";
				break;
			case DIAMOND:
				suit = "Diamond";
				break;
			case CLUB:
				suit = "Club";
				break;
			case SPADE:
				suit = "Spade";
				break;
		}
		return suit;
	}
}
