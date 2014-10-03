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
	 * Test of values method, of class Suit.
	 */
	@Test
	public void testValues(){
		System.out.println( "values" );
		Suit[] expResult = null;
		Suit[] result = Suit.values();
		assertArrayEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of valueOf method, of class Suit.
	 */
	@Test
	public void testValueOf(){
		System.out.println( "valueOf" );
		String name = "";
		Suit expResult = null;
		Suit result = Suit.valueOf( name );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of toString method, of class Suit.
	 */
	@Test
	public void testToString(){
		System.out.println( "toString" );
		Suit instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}
	
}
