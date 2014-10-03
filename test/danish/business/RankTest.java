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
	 * Test of values method, of class Rank.
	 */
	@Test
	public void testValues(){
		System.out.println( "values" );
		
		Rank[] expResult = null;
		Rank[] result = Rank.values();
		assertArrayEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of valueOf method, of class Rank.
	 */
	@Test
	public void testValueOf(){
		System.out.println( "valueOf" );
		String name = "";
		Rank expResult = null;
		Rank result = Rank.valueOf( name );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of getValue method, of class Rank.
	 */
	@Test
	public void testGetValue(){
		System.out.println( "getValue" );
		Rank instance = null;
		int expResult = 0;
		int result = instance.getValue();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of getDisplay method, of class Rank.
	 */
	@Test
	public void testGetDisplay(){
		System.out.println( "getDisplay" );
		Rank instance = null;
		String expResult = "";
		String result = instance.getDisplay();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of placeable method, of class Rank.
	 */
	@Test
	public void testPlaceable(){
		System.out.println( "placeable" );
		Rank r = null;
		Rank instance = null;
		boolean expResult = false;
		boolean result = instance.placeable( r );
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of isJoker method, of class Rank.
	 */
	@Test
	public void testIsJoker(){
		System.out.println( "isJoker" );
		Rank instance = null;
		boolean expResult = false;
		boolean result = instance.isJoker();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}

	/**
	 * Test of toString method, of class Rank.
	 */
	@Test
	public void testToString(){
		System.out.println( "toString" );
		Rank instance = null;
		String expResult = "";
		String result = instance.toString();
		assertEquals( expResult, result );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );
	}
	
}
