package danish.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class RankTest {

	/**
	 * RankTest constructor without parameter.
	 */
	public RankTest() {
	}

	/**
	 * Test of getValue method, from class Rank.
	 */
	@Test
	public void testGetValue() {
		System.out.println("getValue");

		assertEquals(2, Rank.TWO.getValue());
		assertEquals(14, Rank.ACE.getValue());
	}

	/**
	 * Test of getDisplay method, from class Rank.
	 */
	@Test
	public void testGetDisplay() {
		System.out.println("getDisplay");

		assertEquals("Two", Rank.TWO.getDisplay());
		assertEquals("Ace", Rank.ACE.getDisplay());
	}

	/**
	 * Test of toString method, from class Rank.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");

		assertEquals("Two", Rank.TWO.toString());
		assertEquals("Ace", Rank.ACE.toString());
	}

}
