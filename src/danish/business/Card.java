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

	@Override
	public boolean equals(Object obj) {
		return this.rank == ((Card)obj).rank && this.suit == ((Card)obj).suit;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 13 * hash + Objects.hashCode(this.rank);
		hash = 13 * hash + Objects.hashCode(this.suit);
		return hash;
	}

	
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

	@Override
	public String toString() {
		return rank + " of " + suit;
	}
}
