package danish.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class RankTest {

	public RankTest() {
	}

	/**
	 * Test of getValue method, of class Rank.
	 */
	@Test
	public void testGetValue() {
		System.out.println("getValue");

		assertEquals(2, Rank.TWO.getValue());
		assertEquals(14, Rank.ACE.getValue());
	}

	/**
	 * Test of getDisplay method, of class Rank.
	 */
	@Test
	public void testGetDisplay() {
		System.out.println("getDisplay");

		assertEquals("Two", Rank.TWO.getDisplay());
		assertEquals("Ace", Rank.ACE.getDisplay());
	}

	/**
	 * Test of toString method, of class Rank.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");

		assertEquals("Two", Rank.TWO.toString());
		assertEquals("Ace", Rank.ACE.toString());
	}

}
