package danish.business;

/**
 * A class representing a game Card.
 * @author Noé, Julien, Loup.
 */
public class Card{
	
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
	
}
