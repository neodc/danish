package danish.model;

import java.util.Iterator;
import java.util.NoSuchElementException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardPackTest {

	CardPack cardPack;

	public CardPackTest() {
		cardPack = new CardPack();
	}

	@Test
	public void testPeek() {
		System.out.println("Peek");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		assertNull(cardPack.peek());
		cardPack.add(test1);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		cardPack.add(test2);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		cardPack.add(test3);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
	}

	@Test(expected = NullPointerException.class)
	public void testAdd_NullPointerException() throws NullPointerException {
		System.out.println("Add_NullPointerException");
		cardPack.add(null);
	}

	@Test
	public void testAdd() {
		System.out.println("Add");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		assertTrue(cardPack.isEmpty());
		cardPack.add(test1);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		assertTrue(cardPack.size() == 1);
		cardPack.add(test2);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertTrue(cardPack.size() == 2);
		cardPack.add(test3);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
		assertTrue(cardPack.size() == 3);
	}

	@Test
	public void testOffer() {
		System.out.println("Offer");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		assertTrue(cardPack.isEmpty());
		cardPack.offer(test1);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		assertTrue(cardPack.size() == 1);
		cardPack.offer(test2);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertTrue(cardPack.size() == 2);
		cardPack.offer(test3);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
		assertTrue(cardPack.size() == 3);
	}

	@Test
	public void testClear() {
		System.out.println("Clear");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		assertTrue(cardPack.isEmpty());
		cardPack.add(test1);
		cardPack.clear();
		assertTrue(cardPack.isEmpty());
	}

	/**
	 * Test of isEmpty method, of class CardPack.
	 */
	@Test
	public void testIsEmpty() {
		System.out.println("isEmpty");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		assertTrue(cardPack.isEmpty());
		cardPack.add(test1);
		assertFalse(cardPack.isEmpty());
		cardPack.clear();
		assertTrue(cardPack.isEmpty());
	}

	@Test
	public void testGetNumberSimilarCard() {
		System.out.println("getNumberSimilarCard");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		CardDanish test4 = new CardDanish(Rank.ACE, Suit.SPADE);
		assertTrue(cardPack.getNumberSimilarCard() == 0);
		cardPack.offer(test1);
		assertTrue(cardPack.getNumberSimilarCard() == 1);
		cardPack.offer(test2);
		assertTrue(cardPack.getNumberSimilarCard() == 2);
		cardPack.offer(test3);
		assertTrue(cardPack.getNumberSimilarCard() == 3);
		cardPack.offer(test4);
		assertTrue(cardPack.getNumberSimilarCard() == 4);
		cardPack.clear();
		cardPack.offer(test1);
		cardPack.offer(test2);
		cardPack.offer(new CardDanish(Rank.THREE, Suit.SPADE));
		cardPack.offer(test3);
		cardPack.offer(test4);
		assertTrue(cardPack.getNumberSimilarCard() == 2);

	}

	/**
	 * Test of pour method, of class CardPack.
	 */
	@Test
	public void testPour() {
		System.out.println("pour");
		CardPack tmp = new CardPack();
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		cardPack.pour(tmp);
		assertTrue(tmp.isEmpty());
		assertTrue(cardPack.size() == 3);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.FOUR, Suit.SPADE)));
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		assertTrue(cardPack.containsAll(tmp));

		cardPack.clear();
		tmp.clear();
		cardPack.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.ACE, Suit.DIAMOND));
		tmp.offer(new CardDanish(Rank.TWO, Suit.DIAMOND));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.DIAMOND));

		cardPack.pour(tmp);

		assertTrue(tmp.isEmpty());
		assertTrue(cardPack.size() == 6);
		assertTrue(cardPack.peek().equals(new CardDanish(Rank.FOUR, Suit.DIAMOND)));

		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.ACE, Suit.DIAMOND));
		tmp.offer(new CardDanish(Rank.TWO, Suit.DIAMOND));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.DIAMOND));

		assertTrue(cardPack.containsAll(tmp));

	}

	@Test(expected = NoSuchElementException.class)
	public void testRemove_0args_NoSuchElementException() throws NoSuchElementException {
		System.out.println("Remove_0args_NoSuchElementException");
		cardPack.remove();
	}

	/**
	 * Test of remove method, of class CardPack.
	 */
	@Test
	public void testRemove_0args() {
		System.out.println("remove");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		cardPack.add(test1);
		cardPack.add(test2);
		cardPack.add(test3);
		assertTrue(cardPack.remove().equals(new CardDanish(Rank.ACE, Suit.HEART)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.HEART)));
		assertTrue(cardPack.remove().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertTrue(cardPack.remove().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.CLUB)));
	}

	/**
	 * Test of poll method, of class CardPack.
	 */
	@Test
	public void testPoll() {
		System.out.println("poll");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		cardPack.add(test1);
		cardPack.add(test2);
		cardPack.add(test3);
		assertTrue(cardPack.poll().equals(new CardDanish(Rank.ACE, Suit.HEART)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.HEART)));
		assertTrue(cardPack.poll().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		assertTrue(cardPack.poll().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		assertFalse(cardPack.contains(new CardDanish(Rank.ACE, Suit.CLUB)));
	}

	@Test(expected = NoSuchElementException.class)
	public void testElement_NoSuchElementException() throws NoSuchElementException {
		System.out.println("Element_NoSuchElementException");
		cardPack.element();
	}

	/**
	 * Test of element method, of class CardPack.
	 */
	@Test
	public void testElement() {
		System.out.println("element");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		cardPack.add(test1);
		assertTrue(cardPack.element().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
		cardPack.add(test2);
		assertTrue(cardPack.element().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
		cardPack.add(test3);
		assertTrue(cardPack.element().equals(new CardDanish(Rank.ACE, Suit.HEART)));

	}

	/**
	 * Test of size method, of class CardPack.
	 */
	@Test
	public void testSize() {
		System.out.println("size");
		CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
		CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
		CardDanish test4 = new CardDanish(Rank.ACE, Suit.SPADE);
		assertTrue(cardPack.size() == 0);
		cardPack.offer(test1);
		assertTrue(cardPack.size() == 1);
		cardPack.offer(test2);
		assertTrue(cardPack.size() == 2);
		cardPack.offer(test3);
		assertTrue(cardPack.size() == 3);
		cardPack.offer(test4);
		assertTrue(cardPack.size() == 4);
		cardPack.clear();
		assertTrue(cardPack.size() == 0);
	}

	@Test(expected = NullPointerException.class)
	public void testContains_NullPointerException() throws NullPointerException {
		System.out.println("Contains_NullPointerException");
		cardPack.contains(null);
	}

	@Test(expected = ClassCastException.class)
	public void testContains_NoSuchElementException() throws ClassCastException {
		System.out.println("Contains_ClassCastException");
		int i = 0;
		cardPack.contains(i);
	}

	/**
	 * Test of contains method, of class CardPack.
	 */
	@Test
	public void testContains() {
		System.out.println("contains");
		cardPack.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		assertTrue(cardPack.contains(new CardDanish(Rank.ACE, Suit.SPADE)));
		assertTrue(cardPack.contains(new CardDanish(Rank.TWO, Suit.SPADE)));
		assertTrue(cardPack.contains(new CardDanish(Rank.FOUR, Suit.SPADE)));
	}

	/**
	 * Test of iterator method, of class CardPack.
	 */
	@Test
	public void testIterator() {
		System.out.println("iterator");

		CardDanish c1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.THREE, Suit.CLUB);

		cardPack.add(c3);
		cardPack.add(c2);
		cardPack.add(c1);

		Iterator<CardDanish> i = cardPack.iterator();

		assertTrue(i.hasNext());
		assertEquals(i.next(), c1);

		assertTrue(i.hasNext());
		assertEquals(i.next(), c2);

		assertTrue(i.hasNext());
		assertEquals(i.next(), c3);

		assertFalse(i.hasNext());
	}

	@Test(expected = UnsupportedOperationException.class)
	public void testIteratorRemove() {
		System.out.println("iterator");

		CardDanish c1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.THREE, Suit.CLUB);

		cardPack.add(c3);
		cardPack.add(c2);
		cardPack.add(c1);

		Iterator<CardDanish> i = cardPack.iterator();

		i.remove();
	}

	/**
	 * Test of toArray method, of class CardPack.
	 */
	@Test
	public void testToArray_0args() {
		System.out.println("toArray");

		CardDanish c1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.THREE, Suit.CLUB);

		cardPack.add(c3);
		cardPack.add(c2);
		cardPack.add(c1);

		Object[] toArray = cardPack.toArray();

		assertEquals(c1, toArray[0]);
		assertEquals(c2, toArray[1]);
		assertEquals(c3, toArray[2]);

	}

	/**
	 * Test of toArray method, of class CardPack.
	 */
	@Test
	public void testToArray_GenericType() {
		System.out.println("toArray");

		CardDanish c1 = new CardDanish(Rank.ACE, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.THREE, Suit.CLUB);

		cardPack.add(c3);
		cardPack.add(c2);
		cardPack.add(c1);

		CardDanish[] a = new CardDanish[3];

		assertEquals(a, cardPack.toArray(a));
		assertEquals(3, cardPack.toArray(a).length);

		a = new CardDanish[2];

		assertEquals(3, cardPack.toArray(a).length);

		a = new CardDanish[4];

		assertEquals(a, cardPack.toArray(a));
		assertEquals(4, cardPack.toArray(a).length);
		assertNull(a[3]);
	}

	/**
	 * Test of remove method, of class CardPack.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemove_Object() throws UnsupportedOperationException {
		System.out.println("remove");
		cardPack.remove(null);
	}

	/**
	 * Test of containsAll method, of class CardPack.
	 */
	@Test
	public void testContainsAll() {
		System.out.println("containsAll");
		cardPack.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		cardPack.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		CardPack tmp = new CardPack();
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		assertTrue(cardPack.containsAll(tmp));

	}

	/**
	 * Test of addAll method, of class CardPack.
	 */
	@Test
	public void testAddAll() {
		System.out.println("addAll");
		CardPack tmp = new CardPack();
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.TWO, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.THREE, Suit.SPADE));
		tmp.offer(new CardDanish(Rank.FOUR, Suit.SPADE));
		cardPack.addAll(tmp);
		assertTrue(cardPack.containsAll(tmp));
	}

	/**
	 * Test of removeAll method, of class CardPack.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRemoveAll() throws UnsupportedOperationException {
		System.out.println("removeAll");
		CardPack tmp = new CardPack();
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		cardPack.removeAll(tmp);
	}

	/**
	 * Test of retainAll method, of class CardPack.
	 */
	@Test(expected = UnsupportedOperationException.class)
	public void testRetainAll() throws UnsupportedOperationException {
		System.out.println("retainAll");
		CardPack tmp = new CardPack();
		tmp.offer(new CardDanish(Rank.ACE, Suit.SPADE));
		cardPack.retainAll(tmp);
	}
	
	/**
	 * Test of placeable method, of class CardDanish.
	 */
	@Test
	public void testPlaceable() {
		System.out.println("placeable");
		CardPack cp = new CardPack();
		assertTrue(cp.placeable(new CardDanish(Rank.FOUR, Suit.CLUB)));
		cp.add(new CardDanish(Rank.NINE, Suit.CLUB));
		assertTrue(cp.placeable(new CardDanish(Rank.TEN, Suit.CLUB)));
		assertTrue(cp.placeable(new CardDanish(Rank.TWO, Suit.CLUB)));
		cp.add(new CardDanish(Rank.SEVEN, Suit.CLUB));
		assertTrue(cp.placeable(new CardDanish(Rank.FOUR, Suit.CLUB)));
		assertTrue(cp.placeable(new CardDanish(Rank.TEN, Suit.CLUB)));
		assertFalse(cp.placeable(new CardDanish(Rank.JACK, Suit.CLUB)));
	}

}
