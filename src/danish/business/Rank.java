package danish.business;

/**
 * An enumeration of the 13 possible values of a card.
 * @author Noé, Julien, Loup.
 */
public enum Rank{
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
         * @param value A number representing the power of the card.
         * @param display A .
         */
	private Rank (int value, String display) {
		this.value = value;
		this.display = display;
	}
        
        /**
         * Getter of the value.
         * @return The value.
         */
	public int getValue() {
		return this.value;
	}
        
        /**
         * Getter of the display.
         * @return The display.
         */
	public String getDisplay() {
		return this.display;
	}
	
        /**
         * Check if the given rank is placeable on the stack.
         * @param r The rank to check
         * @return true, if the rank is placeable.
         */
	public boolean placeable( Rank r ){
		return r.isJoker() || (this == SEVEN && r.value <= this.value) || r.value >= this.value;
	}
	
        /**
         * Check if the instance is a joker card.
         * @return true, if it's a joker card.
         */
	public boolean isJoker(){
		return this == ACE || this == TWO || this == THREE || this == TEN;
	}

        /**
         * A textual représentation of the rank
         * @return The textual représentation of the rank
         */
	@Override
	public String toString() {
		return display;
	}
}
