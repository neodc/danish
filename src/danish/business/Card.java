package danish.business;

import java.util.Objects;

public class Card{
	private final Rank rank;
	private final Suit suit;
	
	/**
	 * Card constructor with two parameters.
	 *
	 * @param rank The rank of the card (TWO to ACE).
	 * @param suit The suit of the card.
	 */
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public Card( Card c ){
		this(c.rank, c.suit);
	}
	
	/**
	 * Getter of the rank.
	 *
	 * @return The rank.
	 */
	public Rank getRank() {
		return rank;
	}

	/**
	 * Getter of the suit.
	 *
	 * @return The suit.
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Compares this card object with another card object.
	 * Two cards are identical if they have the same rank and the same suit.
	 *
	 * @param obj An other CardDanish, to compare with.
	 * @return true if the objects are identical, false if they are not or if the object is not a card.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Card) {
			return this.rank == ((Card) obj).rank && this.suit == ((Card) obj).suit;
		} else {
			return false;
		}
	}

	/**
	 * Returns a hash code for this card.
	 *
	 * @return a hash code value for this card.
	 */
	@Override
	public int hashCode() {
		int hash = 3;
		hash = 13 * hash + Objects.hashCode(this.rank);
		hash = 13 * hash + Objects.hashCode(this.suit);
		return hash;
	}

	/**
	 * A textual representation of the card.
	 *
	 * @return The textual representation of the card.
	 */
	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}
