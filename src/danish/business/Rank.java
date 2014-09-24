package danish.business;

public enum Rank{
	ACE(1, "Ace"),
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
	KING(13, "King");

	private int value;
	private String display;

	private Rank (int value, String display) {
		this.value = value;
		this.display = display;
	}

	public int getValue() {
		return this.value;
	}

	public String getDisplay() {
		return this.display;
	}
	
	public boolean placeable( Rank r ){
		return r == ACE || r == TWO || r == THREE || r == TEN || (this == SEVEN && r.value <= this.value) || r.value >= this.value;
	}
}
