/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danish.business;

import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class PlayerTest{
	
	public PlayerTest(){
	}

	/**
	 * Test of getHand method, of class Player.
	 */
	@Test
	public void testGetHand(){
		System.out.println( "getHand" );
		
		Player p = new Player("test");
		CardDanish c = new CardDanish(Rank.TWO, Suit.CLUB );
		
		assertEquals( 0, p.getHand().size());
		p.getHand().add(c);
		assertEquals( 1, p.getHand().size());
		assertEquals( c, p.getHand().get(0));
		p.getHand().remove(c);
		assertEquals( 0, p.getHand().size());
		
	}

	/**
	 * Test of getVisible method, of class Player.
	 */
	@Test
	public void testGetVisible(){
		System.out.println( "getVisible" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getVisible().size());
		p.addVisible(c1);
		assertEquals( 1, p.getVisible().size());
		p.addVisible(c2);
		assertEquals( 2, p.getVisible().size());
		p.addVisible(c3);
		assertEquals( 3, p.getVisible().size());
		p.addVisible(c4);
		assertEquals( 3, p.getVisible().size());
		
		
		p.removeVisible(c3);
		assertEquals( 2, p.getVisible().size());
		p.removeVisible(c2);
		assertEquals( 1, p.getVisible().size());
		p.removeVisible(c1);
		assertEquals( 0, p.getVisible().size());
	}

	/**
	 * Test of getHidden method, of class Player.
	 */
	@Test
	public void testGetHidden(){
		System.out.println( "getHidden" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getHidden().size());
		p.addHidden(c1);
		assertEquals( 1, p.getHidden().size());
		p.addHidden(c2);
		assertEquals( 2, p.getHidden().size());
		p.addHidden(c3);
		assertEquals( 3, p.getHidden().size());
		p.addHidden(c4);
		assertEquals( 3, p.getHidden().size());
		
		
		p.removeHidden(c3);
		assertEquals( 2, p.getHidden().size());
		p.removeHidden(c2);
		assertEquals( 1, p.getHidden().size());
		p.removeHidden(c1);
		assertEquals( 0, p.getHidden().size());
	}

	/**
	 * Test of getName method, of class Player.
	 */
	@Test
	public void testGetName(){
		System.out.println( "getName" );
		
		assertEquals( "test", new Player("test").getName() );
	}

	/**
	 * Test of addVisible method, of class Player.
	 */
	@Test
	public void testAddVisible(){
		System.out.println( "addVisible" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getVisible().size());
		p.addVisible(c1);
		assertEquals( 1, p.getVisible().size());
		p.addVisible(c2);
		assertEquals( 2, p.getVisible().size());
		p.addVisible(c3);
		assertEquals( 3, p.getVisible().size());
		p.addVisible(c4);
		assertEquals( 3, p.getVisible().size());
		
		
		p.removeVisible(c3);
		assertEquals( 2, p.getVisible().size());
		p.removeVisible(c2);
		assertEquals( 1, p.getVisible().size());
		p.removeVisible(c1);
		assertEquals( 0, p.getVisible().size());
	}

	/**
	 * Test of addHidden method, of class Player.
	 */
	@Test
	public void testAddHidden(){
		System.out.println( "addHidden" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getHidden().size());
		p.addHidden(c1);
		assertEquals( 1, p.getHidden().size());
		p.addHidden(c2);
		assertEquals( 2, p.getHidden().size());
		p.addHidden(c3);
		assertEquals( 3, p.getHidden().size());
		p.addHidden(c4);
		assertEquals( 3, p.getHidden().size());
		
		
		p.removeHidden(c3);
		assertEquals( 2, p.getHidden().size());
		p.removeHidden(c2);
		assertEquals( 1, p.getHidden().size());
		p.removeHidden(c1);
		assertEquals( 0, p.getHidden().size());
	}

	/**
	 * Test of removeVisible method, of class Player.
	 */
	@Test
	public void testRemoveVisible(){
		System.out.println( "removeVisible" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getVisible().size());
		p.addVisible(c1);
		assertEquals( 1, p.getVisible().size());
		p.addVisible(c2);
		assertEquals( 2, p.getVisible().size());
		p.addVisible(c3);
		assertEquals( 3, p.getVisible().size());
		p.addVisible(c4);
		assertEquals( 3, p.getVisible().size());
		
		
		p.removeVisible(c3);
		assertEquals( 2, p.getVisible().size());
		p.removeVisible(c2);
		assertEquals( 1, p.getVisible().size());
		p.removeVisible(c1);
		assertEquals( 0, p.getVisible().size());
	}

	/**
	 * Test of removeHidden method, of class Player.
	 */
	@Test
	public void testRemoveHidden(){
		System.out.println( "removeHidden" );
		
		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB );
		
		assertEquals( 0, p.getHidden().size());
		p.addHidden(c1);
		assertEquals( 1, p.getHidden().size());
		p.addHidden(c2);
		assertEquals( 2, p.getHidden().size());
		p.addHidden(c3);
		assertEquals( 3, p.getHidden().size());
		p.addHidden(c4);
		assertEquals( 3, p.getHidden().size());
		
		
		p.removeHidden(c3);
		assertEquals( 2, p.getHidden().size());
		p.removeHidden(c2);
		assertEquals( 1, p.getHidden().size());
		p.removeHidden(c1);
		assertEquals( 0, p.getHidden().size());
	}

	/**
	 * Test of switchCard method, of class Player.
	 */
	@Test
	public void testSwitchCard(){
		System.out.println( "switchCard" );
		
		Player p = new Player("test");
		CardDanish v = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish h = new CardDanish(Rank.THREE, Suit.CLUB );
		
		p.getHand().add(h);
		p.addVisible(v);
		
		p.switchCard(v, h);
		
		assertEquals( v, p.getHand().get(0));
		assertEquals( h, p.getVisible().get(0));
	}

	/**
	 * Test of draw method, of class Player.
	 */
	@Test
	public void testDraw(){
		System.out.println( "draw" );
		
		Player p = new Player("test");
		
		CardDanish h1 = new CardDanish(Rank.TWO, Suit.CLUB );
		CardDanish h2 = new CardDanish(Rank.THREE, Suit.CLUB );
		CardDanish h3 = new CardDanish(Rank.FOUR, Suit.CLUB );
		CardDanish v1 = new CardDanish(Rank.FIVE, Suit.CLUB );
		CardDanish v2 = new CardDanish(Rank.SIX, Suit.CLUB );
		CardDanish v3 = new CardDanish(Rank.SEVEN, Suit.CLUB );
		CardDanish r1 = new CardDanish(Rank.EIGHT, Suit.CLUB );
		CardDanish r2 = new CardDanish(Rank.NINE, Suit.CLUB );
		CardDanish r3 = new CardDanish(Rank.TEN, Suit.CLUB );
		
		p.addHidden(r1);
		p.addHidden(r2);
		p.addHidden(r3);
		p.addVisible(v1);
		p.addVisible(v2);
		p.addVisible(v3);
		p.getHand().add(h1);
		p.getHand().add(h2);
		p.getHand().add(h3);
		
		assertEquals( 3, p.getHidden().size());
		assertEquals( 3, p.getVisible().size());
		assertEquals( 3, p.getHand().size());
		
		assertFalse( p.draw() );
		
		assertEquals( 3, p.getHidden().size());
		assertEquals( 3, p.getVisible().size());
		assertEquals( 3, p.getHand().size());
		
		p.getHand().clear();
		assertFalse( p.draw() );
		
		assertEquals( 3, p.getHidden().size());
		assertEquals( 0, p.getVisible().size());
		assertEquals( 3, p.getHand().size());
		
		p.getHand().clear();
		assertFalse( p.draw() );
		
		assertEquals( 2, p.getHidden().size());
		assertEquals( 0, p.getVisible().size());
		assertEquals( 1, p.getHand().size());
		
		p.getHand().clear();
		assertFalse( p.draw() );
		
		assertEquals( 1, p.getHidden().size());
		assertEquals( 0, p.getVisible().size());
		assertEquals( 1, p.getHand().size());
		
		p.getHand().clear();
		assertFalse( p.draw() );
		
		assertEquals( 0, p.getHidden().size());
		assertEquals( 0, p.getVisible().size());
		assertEquals( 1, p.getHand().size());
		
		p.getHand().clear();
		assertTrue(p.draw() );
		
		assertEquals( 0, p.getHidden().size());
		assertEquals( 0, p.getVisible().size());
		assertEquals( 0, p.getHand().size());
	}
	
}
