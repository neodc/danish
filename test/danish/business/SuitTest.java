package danish.business;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class SuitTest {

	public SuitTest() {
	}

	/**
	 * Test of toString method, of class Suit.
	 */
	@Test
	public void testToString() {
		System.out.println("toString");

		assertEquals("Heart", Suit.HEART.toString());
		assertEquals("Diamond", Suit.DIAMOND.toString());
		assertEquals("Club", Suit.CLUB.toString());
		assertEquals("Spade", Suit.SPADE.toString());
	}

}
