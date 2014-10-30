package danish.business;

import java.util.Collection;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardPackTest{

	CardPack cardPack;

	public CardPackTest(){
		cardPack = new CardPack();
	}

	@Test
	public void testPeek(){
		System.out.println( "Peek" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		CardDanish test2 = new CardDanish( Rank.ACE, Suit.DIAMOND );
		CardDanish test3 = new CardDanish( Rank.ACE, Suit.HEART );
		cardPack.add( test1 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.CLUB ) ) );
		cardPack.add( test2 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.DIAMOND ) ) );
		cardPack.add( test3 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.HEART ) ) );
	}

	@Test
	public void testAdd(){
		System.out.println( "Add" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		CardDanish test2 = new CardDanish( Rank.ACE, Suit.DIAMOND );
		CardDanish test3 = new CardDanish( Rank.ACE, Suit.HEART );
		//TODO test exeptions
		assertTrue( cardPack.isEmpty() );
		cardPack.add( test1 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.CLUB ) ) );
		assertTrue( cardPack.size() == 1 );
		cardPack.add( test2 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.DIAMOND ) ) );
		assertTrue( cardPack.size() == 2 );
		cardPack.add( test3 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.HEART ) ) );
		assertTrue( cardPack.size() == 3 );
	}

	@Test
	public void testOffer(){
		System.out.println( "Offer" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		CardDanish test2 = new CardDanish( Rank.ACE, Suit.DIAMOND );
		CardDanish test3 = new CardDanish( Rank.ACE, Suit.HEART );
		//TODO test exeptions
		assertTrue( cardPack.isEmpty() );
		cardPack.offer( test1 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.CLUB ) ) );
		assertTrue( cardPack.size() == 1 );
		cardPack.offer( test2 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.DIAMOND ) ) );
		assertTrue( cardPack.size() == 2 );
		cardPack.offer( test3 );
		assertTrue( cardPack.peek().equals( new CardDanish( Rank.ACE, Suit.HEART ) ) );
		assertTrue( cardPack.size() == 3 );
	}

	@Test
	public void testClear(){
		System.out.println( "Clear" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		assertTrue( cardPack.isEmpty() );
		cardPack.add( test1 );
		cardPack.clear();
		assertTrue( cardPack.isEmpty() );
	}

	@Test
	public void testEmpty(){
		System.out.println( "Empty" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		assertTrue( cardPack.isEmpty() );
		cardPack.add( test1 );
		assertFalse( cardPack.isEmpty() );
		cardPack.clear();
		assertTrue( cardPack.isEmpty() );
	}

	@Test
	public void testNumberSimilarCard(){
		System.out.println( "NumberSimilarCard" );
		CardDanish test1 = new CardDanish( Rank.ACE, Suit.CLUB );
		CardDanish test2 = new CardDanish( Rank.ACE, Suit.DIAMOND );
		CardDanish test3 = new CardDanish( Rank.ACE, Suit.HEART );
		CardDanish test4 = new CardDanish( Rank.ACE, Suit.SPADE );
		assertTrue( cardPack.getNumberSimilarCard() == 0 );
		cardPack.offer( test1 );
		assertTrue( cardPack.getNumberSimilarCard() == 1 );
		cardPack.offer( test2 );
		assertTrue( cardPack.getNumberSimilarCard() == 2 );
		cardPack.offer( test3 );
		assertTrue( cardPack.getNumberSimilarCard() == 3 );
		cardPack.offer( test4 );
		assertTrue( cardPack.getNumberSimilarCard() == 4 );
		cardPack.clear();

	}

	/**
	 * Test of pour method, of class CardPack.
	 */
	@Test
	public void testPour(){
		System.out.println( "pour" );
		CardPack source = null;
		CardPack instance = new CardPack();
		instance.pour( source );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of getNumberSimilarCard method, of class CardPack.
	 */
	@Test
	public void testGetNumberSimilarCard(){
		System.out.println( "getNumberSimilarCard" );
		CardPack instance = new CardPack();
		int expResult = 0;
		int result = instance.getNumberSimilarCard();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of remove method, of class CardPack.
	 */
	@Test
	public void testRemove_0args(){
		System.out.println( "remove" );
		CardPack instance = new CardPack();
		CardDanish expResult = null;
		CardDanish result = instance.remove();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of poll method, of class CardPack.
	 */
	@Test
	public void testPoll(){
		System.out.println( "poll" );
		CardPack instance = new CardPack();
		CardDanish expResult = null;
		CardDanish result = instance.poll();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of element method, of class CardPack.
	 */
	@Test
	public void testElement(){
		System.out.println( "element" );
		CardPack instance = new CardPack();
		CardDanish expResult = null;
		CardDanish result = instance.element();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of size method, of class CardPack.
	 */
	@Test
	public void testSize(){
		System.out.println( "size" );
		CardPack instance = new CardPack();
		int expResult = 0;
		int result = instance.size();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of isEmpty method, of class CardPack.
	 */
	@Test
	public void testIsEmpty(){
		System.out.println( "isEmpty" );
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.isEmpty();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of contains method, of class CardPack.
	 */
	@Test
	public void testContains(){
		System.out.println( "contains" );
		Object o = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.contains( o );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of iterator method, of class CardPack.
	 */
	@Test
	public void testIterator(){
		System.out.println( "iterator" );
		CardPack instance = new CardPack();
		Iterator<CardDanish> expResult = null;
		Iterator<CardDanish> result = instance.iterator();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of toArray method, of class CardPack.
	 */
	@Test
	public void testToArray_0args(){
		System.out.println( "toArray" );
		CardPack instance = new CardPack();
		Object[] expResult = null;
		Object[] result = instance.toArray();
		assertArrayEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of toArray method, of class CardPack.
	 */
	@Test
	public void testToArray_GenericType(){
		System.out.println( "toArray" );
		Object[] array = null;
		CardPack instance = new CardPack();
		Object[] expResult = null;
		Object[] result = instance.toArray( array );
		assertArrayEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of remove method, of class CardPack.
	 */
	@Test
	public void testRemove_Object(){
		System.out.println( "remove" );
		Object o = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.remove( o );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of containsAll method, of class CardPack.
	 */
	@Test
	public void testContainsAll(){
		System.out.println( "containsAll" );
		Collection c = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.containsAll( c );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of addAll method, of class CardPack.
	 */
	@Test
	public void testAddAll(){
		System.out.println( "addAll" );
		Collection<? extends CardDanish> c = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.addAll( c );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of removeAll method, of class CardPack.
	 */
	@Test
	public void testRemoveAll(){
		System.out.println( "removeAll" );
		Collection clctn = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.removeAll( clctn );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of retainAll method, of class CardPack.
	 */
	@Test
	public void testRetainAll(){
		System.out.println( "retainAll" );
		Collection clctn = null;
		CardPack instance = new CardPack();
		boolean expResult = false;
		boolean result = instance.retainAll( clctn );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

}
