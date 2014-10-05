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
public class SuitTest{
	
	public SuitTest(){
	}

	/**
	 * Test of toString method, of class Suit.
	 */
	@Test
	public void testToString(){
		System.out.println( "toString" );
		
		assertEquals( "Heart", Suit.HEART.toString() );
		assertEquals( "Diamond", Suit.DIAMOND.toString() );
		assertEquals( "Club", Suit.CLUB.toString() );
		assertEquals( "Spade", Suit.SPADE.toString() );
	}
	
}
