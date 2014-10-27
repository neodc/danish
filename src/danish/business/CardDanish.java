package danish.business;

/**
 * A class representing a game CardDanish.
 *
 * @author Noé, Julien, Loup.
 */
public class CardDanish extends Card implements Comparable {

	private CardDanish next;

	/**
	 * Card constructor with two parameters.
	 *
	 * @param rank The rank of the card (TWO to ACE).
	 * @param suit The suit of the card.
	 */
	public CardDanish(Rank rank, Suit suit) {
		super(rank, suit);
		this.next = null;
	}
	
	public CardDanish(CardDanish c){
		this(c, false);
	}
	
	CardDanish(CardDanish c, boolean copynext){
		super(c);
		if( copynext && c.next != null ){
			this.next = new CardDanish(c.next, copynext);
		}else{
			this.next = null;
		}
	}
	
	public Rank getRealRank(){
		if( this.getRank() != Rank.THREE || this.next == null ){
			return this.getRank();
		}else{
			return this.next.getRealRank();
		}
	}

	/**
	 * Compares the power of two cards
	 *
	 * @param o An other CardDanish, to compare with.
	 * @return A positive int if this card has more power, 0 if they have the same, and a negative int the other card has more power.
	 */
	@Override
	public int compareTo(Object o) {
		return this.getPower() - ((CardDanish) o).getPower();
	}
	
	private int getPower(){
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

	CardDanish getNext(){
		return next;
	}

	void setNext( CardDanish next ){
		this.next = next;
	}
}
