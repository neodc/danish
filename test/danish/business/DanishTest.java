package danish.business;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class DanishTest{
	
	public DanishTest(){
	}

	/**
	 * Test of getPlayers method, of class Danish.
	 */
	@Test
	public void testGetPlayers(){
		System.out.println( "getPlayers" );
		
		// vide => return null
		Danish instance = new Danish();
		
		List<Player> result = instance.getPlayers();
		assertEquals( null, result );
		
		// 1 joueurs => invalide => return null
		instance = new Danish();
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
		
		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test2");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test3");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test4");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add("test5");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
	}

	/**
	 * Test of getDeck method, of class Danish.
	 */
	@Test
	public void testGetDeck(){
		System.out.println( "getDeck" );
		Danish instance = new Danish();
		
		List<Card> result = instance.getDeck();
		
		for( Suit s : Suit.values() ){
			for( Rank r : Rank.values() ){
				assertTrue( result.contains( new Card(r, s) ) );
			}
		}
	}

	/**
	 * Test of getStack method, of class Danish.
	 */
	@Test
	public void testGetStack(){
		System.out.println( "getStack" );
		Danish instance = new Danish();
		
		assertTrue(instance.getStack().isEmpty() );
	}

	/**
	 * Test of getGraveyard method, of class Danish.
	 */
	@Test
	public void testGetGraveyard(){
		System.out.println( "getGraveyard" );
		Danish instance = new Danish();
		
		assertTrue(instance.getStack().isEmpty() );
		
	}

	/**
	 * Test of isPlaying method, of class Danish.
	 */
	@Test
	public void testIsPlaying(){
		System.out.println( "isPlaying" );
		Danish instance = new Danish();
		
		assertFalse(instance.isPlaying() );
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		instance.begin();
		
		assertTrue(instance.isPlaying());
	}

	/**
	 * Test of getCurrentPlayer method, of class Danish.
	 */
	@Test
	public void testGetCurrentPlayer(){
		System.out.println( "getCurrentPlayer" );
		Danish instance = new Danish();
		assertEquals( 0, instance.getCurrentPlayer() );
	}

	/**
	 * Test of setPlayers method, of class Danish.
	 */
	@Test
	public void testSetPlayers(){
		System.out.println( "getPlayers" );
		
		// vide => return null
		Danish instance = new Danish();
		
		List<Player> result = instance.getPlayers();
		assertEquals( null, result );
		
		// 1 joueurs => invalide => return null
		instance = new Danish();
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
		
		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test2");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test3");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test4");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add("test5");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
	}

	/**
	 * Test of begin method, of class Danish.
	 */
	@Test
	public void testBegin(){
		System.out.println( "begin" );
		Danish instance = new Danish();
		
		assertFalse(instance.begin() );
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		assertTrue(instance.begin() );
	}

	/**
	 * Test of turn method, of class Danish.
	 */
	@Test
	public void testTurn_List(){
		System.out.println( "turn" );
		List<Card> cards = null;
		Danish instance = new Danish();
		instance.turn( cards );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of turn method, of class Danish.
	 */
	@Test
	public void testTurn_List_int(){
		System.out.println( "turn" );
		List<Card> cards = null;
		int player = 0;
		Danish instance = new Danish();
		instance.turn( cards, player );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of switchCard method, of class Danish.
	 */
	@Test
	public void testSwitchCard_3args_1(){
		System.out.println( "switchCard" );
		int p = 0;
		Card visible;
		Card hand;
		Danish instance = new Danish();
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		visible = instance.getPlayers().get(p).getVisible().get(0);
		hand = instance.getPlayers().get(p).getHand().get(0);
		
		instance.switchCard( p, visible, hand );
		
		assertTrue( instance.getPlayers().get(p).getHand().contains( visible ) );
		assertTrue( instance.getPlayers().get(p).getVisible().contains( hand ) );
	}

	/**
	 * Test of switchCard method, of class Danish.
	 */
	@Test
	public void testSwitchCard_3args_2(){
		System.out.println( "switchCard" );
		Player p;
		Card visible;
		Card hand;
		Danish instance = new Danish();
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		p = instance.getPlayers().get(0);
		
		visible = p.getVisible().get(0);
		hand = p.getHand().get(0);
		
		instance.switchCard( p, visible, hand );
		
		assertTrue( p.getHand().contains( visible ) );
		assertTrue( p.getVisible().contains( hand ) );
	}
}
