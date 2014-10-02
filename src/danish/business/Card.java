package danish.business;

import java.util.Objects;

/**
 * A class representing a game Card.
 * @author Noé, Julien, Loup.
 */
public class Card implements Comparable{
	
	private final Rank rank;
	private final Suit suit;

        /**
         * Card constructor with two parameters.
         * @param rank The rank of the card (TWO to ACE).
         * @param suit The suit of the card.
         */
	public Card( Rank rank, Suit suit ){
		this.rank = rank;
		this.suit = suit;
	}

        /**
         * Getter of the rank.
         * @return The rank.
         */
	public Rank getRank(){
		return rank;
	}

        /**
         * Getter of the suit.
         * @return The suit.
         */
	public Suit getSuit(){
		return suit;
	}
        
        /**
         * Compare this card object with another card object. two card are 
         * identical if they have the same rank and the same suit.
         * @param obj An other Card, to compare with.
         * @return true if the objects are identical, false if they are not, or
         * if the object is not a card.
         */
	@Override
	public boolean equals(Object obj) {
            if (obj instanceof Card ){
		return this.rank == ((Card)obj).rank && this.suit == ((Card)obj).suit;
            }else{
                return false;
            }
        }
        
        /**
         * Returns a hash code for this card.
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
         * Compare the power of two card 
         * @param o An other Card, to compare with.
         * @return A positive int if this card has more power, 0 if they have 
         * the same, and a negative int the other card has more power
         */
	@Override
	public int compareTo(Object o) {
		int power;
		int oPower;
		switch (this.rank.getValue()){
			case 2: power = 16;break;
			case 3: power = 15;break;
			case 10: power = 17;break;
			default: power = this.rank.getValue();
		}
		switch (((Card)o).rank.getValue()){
			case 2: oPower = 16;break;
			case 3: oPower = 15;break;
			case 10: oPower = 17;break;
			default: oPower = ((Card)o).rank.getValue();
		}
		return power - oPower;
	}

        /**
         * A textual représentation of the card
         * @return The textual représentation of the card
         */
	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}