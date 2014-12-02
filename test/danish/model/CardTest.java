package danish.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardTest {

	/**
	 * CardTest constructor without parameter.
	 */
	public CardTest() {
	}

	/**
	 * Test of getRank method, from class Card.
	 */
	@Test
	public void testGetRank() {
		System.out.println("getRank");
		assertEquals(Rank.ACE, new Card(Rank.ACE, Suit.CLUB).getRank());
		assertEquals(Rank.EIGHT, new Card(Rank.EIGHT, Suit.DIAMOND).getRank());
		assertEquals(Rank.JACK, new Card(Rank.JACK, Suit.HEART).getRank());
		assertEquals(Rank.TWO, new Card(Rank.TWO, Suit.SPADE).getRank());
	}

	/**
	 * Test of getSuit method, from class Card.
	 */
	@Test
	public void testGetSuit() {
		System.out.println("getSuit");
		assertEquals(Suit.CLUB, new Card(Rank.ACE, Suit.CLUB).getSuit());
		assertEquals(Suit.DIAMOND, new Card(Rank.EIGHT, Suit.DIAMOND).getSuit());
		assertEquals(Suit.HEART, new Card(Rank.JACK, Suit.HEART).getSuit());
		assertEquals(Suit.SPADE, new Card(Rank.TWO, Suit.SPADE).getSuit());
	}

	/**
	 * Test of equals method, from class Card.
	 */
	@Test
	public void testEquals() {
		System.out.println("equals");

		assertTrue(new Card(Rank.ACE, Suit.CLUB).equals(new Card(Rank.ACE, Suit.CLUB)));
		assertFalse(new Card(Rank.ACE, Suit.CLUB).equals(new Card(Rank.ACE, Suit.DIAMOND)));
		assertFalse(new Card(Rank.ACE, Suit.CLUB).equals(new Card(Rank.EIGHT, Suit.CLUB)));
		assertFalse(new Card(Rank.ACE, Suit.CLUB).equals(null));
	}

	/**
	 * Test of hashCode method, from class Card.
	 */
	@Test
	public void testHashCode() {
		System.out.println("hashCode");

		assertTrue(new Card(Rank.ACE, Suit.CLUB).hashCode() == new Card(Rank.ACE, Suit.CLUB).hashCode());
		assertFalse(new Card(Rank.ACE, Suit.CLUB).hashCode() == new Card(Rank.ACE, Suit.DIAMOND).hashCode());
		assertFalse(new Card(Rank.ACE, Suit.CLUB).hashCode() == new Card(Rank.EIGHT, Suit.CLUB).hashCode());
	}

	/**
	 * Test of toString method, from class Card.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");

		assertEquals(Rank.ACE + " of " + Suit.CLUB, new Card(Rank.ACE, Suit.CLUB).toString());
	}

}
