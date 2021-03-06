package danish.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardDanishTest {

	/**
	 * CardDanishTest constructor without parameter.
	 */
	public CardDanishTest() {
	}

	/**
	 * Test of compareTo method, from class CardDanish.
	 */
	@Test
	public void testCompareTo() {
		System.out.println("compareTo");

		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).compareTo(new CardDanish(Rank.ACE, Suit.CLUB)) == 0);
		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).compareTo(new CardDanish(Rank.ACE, Suit.DIAMOND)) == 0);
		assertTrue(new CardDanish(Rank.FIVE, Suit.CLUB).compareTo(new CardDanish(Rank.FOUR, Suit.CLUB)) > 0);
		assertTrue(new CardDanish(Rank.FOUR, Suit.CLUB).compareTo(new CardDanish(Rank.FIVE, Suit.CLUB)) < 0);
	}

	/**
	 * Test of getRealRank method, from class CardDanish.
	 */
	@Test
	public void testGetRealRank() {
		System.out.println("getRealRank");

		CardDanish cardAce = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish cardThree = new CardDanish(Rank.THREE, Suit.CLUB);

		assertEquals(Rank.ACE, cardAce.getRealRank());
		assertEquals(Rank.THREE, cardThree.getRealRank());

		cardThree.setNext(cardAce);

		assertEquals(Rank.ACE, cardThree.getRealRank());
	}

	/**
	 * Test of getNext method, from class CardDanish.
	 */
	@Test
	public void testGetNext() {
		System.out.println("getNext");

		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);

		assertNull(test1.getNext());

		test1.setNext(test2);

		assertEquals(test1.getNext(), test2);
	}

	/**
	 * Test of setNext method, from class CardDanish.
	 */
	@Test
	public void testSetNext() {
		System.out.println("setNext");

		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);

		assertNull(test1.getNext());

		test1.setNext(test2);

		assertEquals(test1.getNext(), test2);
	}

	/**
	 * Test of isJoker method, from class CardDanish.
	 */
	@Test
	public void testIsJoker() {
		System.out.println("isJoker");

		assertTrue(new CardDanish(Rank.ACE, Suit.CLUB).isJoker());
		assertTrue(new CardDanish(Rank.TWO, Suit.CLUB).isJoker());
		assertTrue(new CardDanish(Rank.THREE, Suit.CLUB).isJoker());
		assertTrue(new CardDanish(Rank.TEN, Suit.CLUB).isJoker());
		assertFalse(new CardDanish(Rank.EIGHT, Suit.CLUB).isJoker());
	}
}
