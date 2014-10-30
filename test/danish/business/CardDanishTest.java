package danish.business;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardDanishTest{
	
	public CardDanishTest(){
	}

	/**
	 * Test of getRank method, of class CardDanish.
	 */
	@Test
	public void testGetRank(){
		System.out.println( "getRank" );
		assertEquals(Rank.ACE, new CardDanish(Rank.ACE, Suit.CLUB).getRank() );
		assertEquals(Rank.EIGHT, new CardDanish(Rank.EIGHT, Suit.DIAMOND).getRank() );
		assertEquals(Rank.JACK, new CardDanish(Rank.JACK, Suit.HEART).getRank() );
		assertEquals(Rank.TWO, new CardDanish(Rank.TWO, Suit.SPADE).getRank() );
	}

	/**
	 * Test of getSuit method, of class CardDanish.
	 */
	@Test
	public void testGetSuit(){
		System.out.println( "getSuit" );
		assertEquals(Suit.CLUB, new CardDanish(Rank.ACE, Suit.CLUB).getSuit());
		assertEquals(Suit.DIAMOND, new CardDanish(Rank.EIGHT, Suit.DIAMOND).getSuit() );
		assertEquals(Suit.HEART, new CardDanish(Rank.JACK, Suit.HEART).getSuit() );
		assertEquals(Suit.SPADE, new CardDanish(Rank.TWO, Suit.SPADE).getSuit() );
	}

	/**
	 * Test of equals method, of class CardDanish.
	 */
	@Test
	public void testEquals(){
		System.out.println( "equals" );
		
		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).equals(new CardDanish(Rank.ACE, Suit.CLUB) ) );
		assertFalse(new CardDanish(Rank.ACE, Suit.CLUB).equals(new CardDanish(Rank.ACE, Suit.DIAMOND) ) );
		assertFalse(new CardDanish(Rank.ACE, Suit.CLUB).equals(new CardDanish(Rank.EIGHT, Suit.CLUB) ) );
		assertFalse(new CardDanish(Rank.ACE, Suit.CLUB).equals( null ) );
	}

	/**
	 * Test of hashCode method, of class CardDanish.
	 */
	@Test
	public void testHashCode(){
		System.out.println( "hashCode" );
		
		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).hashCode() == new CardDanish(Rank.ACE, Suit.CLUB).hashCode() );
		assertFalse(new CardDanish(Rank.ACE, Suit.CLUB).hashCode() == new CardDanish(Rank.ACE, Suit.DIAMOND).hashCode() );
		assertFalse(new CardDanish(Rank.ACE, Suit.CLUB).hashCode() == new CardDanish(Rank.EIGHT, Suit.CLUB).hashCode() );
	}

	/**
	 * Test of compareTo method, of class CardDanish.
	 */
	@Test
	public void testCompareTo(){
		System.out.println( "compareTo" );
		
		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).compareTo(new CardDanish(Rank.ACE, Suit.CLUB)) == 0 );
		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).compareTo(new CardDanish(Rank.ACE, Suit.DIAMOND)) == 0 );
		assertTrue(new CardDanish(Rank.FIVE, Suit.CLUB).compareTo(new CardDanish(Rank.FOUR, Suit.CLUB)) > 0 );
		assertTrue(new CardDanish(Rank.FOUR, Suit.CLUB).compareTo(new CardDanish(Rank.FIVE, Suit.CLUB)) < 0 );
		assertTrue(new CardDanish(Rank.FOUR, Suit.CLUB).compareTo(new CardDanish(Rank.FIVE, Suit.CLUB)) < 0 );
	}

	/**
	 * Test of toString method, of class CardDanish.
	 */
	@Test
	public void testToString(){
		System.out.println( "toString" );
		
		assertEquals(Rank.ACE + " of " + Suit.CLUB, new CardDanish(Rank.ACE, Suit.CLUB).toString());
	}
        
	@Test
	public void testNext(){
		System.out.println( "Next" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		
                assertNull(test1.getNext());
                
                CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
                test1.setNext(test2);
		assertEquals(test1.getNext(), new CardDanish(Rank.ACE, Suit.DIAMOND));
		assertNull(test2.getNext());
                
	}
}
