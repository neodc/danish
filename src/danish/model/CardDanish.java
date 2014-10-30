package danish.model;

/**
 * A class representing a game CardDanish.
 *
 * @author Noé, Julien, Loup.
 */
public class CardDanish extends Card implements Comparable {

	private CardDanish next;

	/**
	 * CardDanish constructor with two parameters.
	 *
	 * @param rank The rank of the card (TWO to ACE).
	 * @param suit The suit of the card.
	 */
	public CardDanish(Rank rank, Suit suit) {
		super(rank, suit);
		this.next = null;
	}

	/**
	 * CardDanish copy constructor with the attibute next set to null.
	 *
	 * @param c The card to copy.
	 */
	public CardDanish(CardDanish c) {
		this(c, false);
	}

	/**
	 * CardDanish copy constructor that can use a copy of the next CardDanish.
	 *
	 * @param c The card to copy.
	 * @param copynext If the attibute next gets a copy of the next CardDanish.
	 * Is null otherwise.
	 */
	CardDanish(CardDanish c, boolean copynext) {
		super(c);
		if (copynext && c.next != null) {
			this.next = new CardDanish(c.next, copynext);
		} else {
			this.next = null;
		}
	}

	/**
	 * Checks the rank of the card considering a three.
	 *
	 * @return The rank of the card. If the rank is THREE, the rank of its next
	 * card. If the rank is THREE and has no next card, the rank is THREE.
	 */
	public Rank getRealRank() {
		if (this.getRank() != Rank.THREE || this.next == null) {
			return this.getRank();
		} else {
			return this.next.getRealRank();
		}
	}

	/**
	 * Checks if a card is placeable on this card.
	 *
	 * @param c The card to put on this card.
	 * @return true if the card is placeable, false otherwise.
	 */
	public boolean placeable(CardDanish c) {
		return c.isJoker() || (this.getRank() == Rank.SEVEN && c.getRank().getValue() <= this.getRank().getValue()) || (this.getRank() != Rank.SEVEN && c.getRank().getValue() >= this.getRank().getValue());
	}

	/**
	 * Checks if the card is a joker.
	 *
	 * @return true if it's a joker (Ace, Two, Three or Ten), false otherwise.
	 */
	public boolean isJoker() {
		return this.getRank() == Rank.ACE || this.getRank() == Rank.TWO || this.getRank() == Rank.THREE || this.getRank() == Rank.TEN;
	}

	/**
	 * Compares the power of two cards.
	 *
	 * @param o An other CardDanish, to compare with.
	 * @return A positive int if this card has more power, 0 if they have the
	 * same, and a negative int the other card has more power.
	 */
	@Override
	public int compareTo(Object o) {
		return this.getPower() - ((CardDanish) o).getPower();
	}

	private int getPower() {
		int power = this.getRank().getValue();

		switch (this.getRank()) {
			case TWO:
				power = 16;
				break;
			case THREE:
				power = 15;
				break;
			case TEN:
				power = 17;
				break;
		}

		return power;
	}

	/**
	 * Gets the CardDanish following this CardDanish.
	 *
	 * @return the next CardDanish.
	 */
	CardDanish getNext() {
		return next;
	}

	/**
	 * Sets the CardDanish following this CardDanish.
	 *
	 * @param next The new next CardDanish.
	 */
	void setNext(CardDanish next) {
		this.next = next;
	}
}
