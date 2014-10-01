package danish.business;

public enum Suit{
	HEART, DIAMOND, CLUB, SPADE;
	
	@Override	
	public String toString() {
		String suit = null;
		switch (this){
			case HEART: suit = "Heart"; break;
			case DIAMOND: suit = "Diamond"; break;
			case CLUB: suit = "Club"; break;
			case SPADE: suit = "Spade"; break;
		}
		return suit;
	}
}
