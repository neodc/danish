/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package danish.business;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class RankTest{
	
	public RankTest(){
	}

	/**
	 * Test of getValue method, of class Rank.
	 */
	@Test
	public void testGetValue(){
		System.out.println( "getValue" );
		
		assertEquals( 2, Rank.TWO.getValue() );
		assertEquals( 14, Rank.ACE.getValue() );
	}

	/**
	 * Test of getDisplay method, of class Rank.
	 */
	@Test
	public void testGetDisplay(){
		System.out.println( "getDisplay" );
		
		assertEquals( "Two", Rank.TWO.getDisplay() );
		assertEquals( "Ace", Rank.ACE.getDisplay() );
	}

	/**
	 * Test of placeable method, of class Rank.
	 */
	@Test
	public void testPlaceable(){
		System.out.println( "placeable" );
		
		assertTrue( Rank.NINE.placeable( Rank.TEN ) );
		assertTrue( Rank.NINE.placeable( Rank.TWO ) );
		assertTrue( Rank.SEVEN.placeable( Rank.FOUR ) );
		assertTrue( Rank.SEVEN.placeable( Rank.TEN ) );
		assertFalse(Rank.SEVEN.placeable( Rank.JACK ) );
	}

	/**
	 * Test of isJoker method, of class Rank.
	 */
	@Test
	public void testIsJoker(){
		System.out.println( "isJoker" );
		
		assertTrue( Rank.ACE.isJoker() );
		assertTrue( Rank.TWO.isJoker() );
		assertTrue( Rank.THREE.isJoker() );
		assertTrue( Rank.TEN.isJoker() );
		assertFalse(Rank.EIGHT.isJoker() );
	}

	/**
	 * Test of toString method, of class Rank.
	 */
	@Test
	public void testToString(){
		System.out.println( "toString" );
		
		assertEquals( "Two", Rank.TWO.toString() );
		assertEquals( "Ace", Rank.ACE.toString() );
	}
	
}
