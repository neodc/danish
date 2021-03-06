package danish.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class PlayerTest {

	/**
	 * PlayerTest constructor without parameter.
	 */
	public PlayerTest() {
	}

	/**
	 * Test of getHand method, from class Player.
	 */
	@Test
	public void testGetHand() {
		System.out.println("getHand");

		Player p = new Player("test");
		CardDanish c = new CardDanish(Rank.TWO, Suit.CLUB);

		assertEquals(0, p.getHand().size());
		p.hand.add(c);
		assertEquals(1, p.getHand().size());
		assertEquals(c, p.getHand().get(0));
		p.hand.remove(c);
		assertEquals(0, p.getHand().size());

	}

	/**
	 * Test of getVisible method, from class Player.
	 */
	@Test
	public void testGetVisible() {
		System.out.println("getVisible");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.getVisible().size());
		p.addVisible(c1);
		assertEquals(1, p.getVisible().size());
		p.addVisible(c2);
		assertEquals(2, p.getVisible().size());
		p.addVisible(c3);
		assertEquals(3, p.getVisible().size());
		p.addVisible(c4);
		assertEquals(3, p.getVisible().size());

		p.removeVisible(c3);
		assertEquals(2, p.getVisible().size());
		p.removeVisible(c2);
		assertEquals(1, p.getVisible().size());
		p.removeVisible(c1);
		assertEquals(0, p.getVisible().size());
	}

	/**
	 * Test of getHidden method, from class Player.
	 */
	@Test
	public void testGetHidden() {
		System.out.println("getHidden");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.getHidden().size());
		p.addHidden(c1);
		assertEquals(1, p.getHidden().size());
		p.addHidden(c2);
		assertEquals(2, p.getHidden().size());
		p.addHidden(c3);
		assertEquals(3, p.getHidden().size());
		p.addHidden(c4);
		assertEquals(3, p.getHidden().size());

		p.removeHidden(c3);
		assertEquals(2, p.getHidden().size());
		p.removeHidden(c2);
		assertEquals(1, p.getHidden().size());
		p.removeHidden(c1);
		assertEquals(0, p.getHidden().size());
	}

	/**
	 * Test of getName method, from class Player.
	 */
	@Test
	public void testGetName() {
		System.out.println("getName");

		assertEquals("test", new Player("test").getName());
	}

	/**
	 * Test of addVisible method, from class Player.
	 */
	@Test
	public void testAddVisible() {
		System.out.println("addVisible");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.visible.size());
		p.addVisible(c1);
		assertEquals(1, p.visible.size());
		p.addVisible(c2);
		assertEquals(2, p.visible.size());
		p.addVisible(c3);
		assertEquals(3, p.visible.size());
		p.addVisible(c4);
		assertEquals(3, p.visible.size());

		p.removeVisible(c3);
		assertEquals(2, p.visible.size());
		p.removeVisible(c2);
		assertEquals(1, p.visible.size());
		p.removeVisible(c1);
		assertEquals(0, p.visible.size());
	}

	/**
	 * Test of addHidden method, from class Player.
	 */
	@Test
	public void testAddHidden() {
		System.out.println("addHidden");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.hidden.size());
		p.addHidden(c1);
		assertEquals(1, p.hidden.size());
		p.addHidden(c2);
		assertEquals(2, p.hidden.size());
		p.addHidden(c3);
		assertEquals(3, p.hidden.size());
		p.addHidden(c4);
		assertEquals(3, p.hidden.size());

		p.removeHidden(c3);
		assertEquals(2, p.hidden.size());
		p.removeHidden(c2);
		assertEquals(1, p.hidden.size());
		p.removeHidden(c1);
		assertEquals(0, p.hidden.size());
	}

	/**
	 * Test of removeVisible method, from class Player.
	 */
	@Test
	public void testRemoveVisible() {
		System.out.println("removeVisible");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.visible.size());
		p.addVisible(c1);
		assertEquals(1, p.visible.size());
		p.addVisible(c2);
		assertEquals(2, p.visible.size());
		p.addVisible(c3);
		assertEquals(3, p.visible.size());
		p.addVisible(c4);
		assertEquals(3, p.visible.size());

		p.removeVisible(c3);
		assertEquals(2, p.visible.size());
		p.removeVisible(c2);
		assertEquals(1, p.visible.size());
		p.removeVisible(c1);
		assertEquals(0, p.visible.size());
	}

	/**
	 * Test of removeHidden method, from class Player.
	 */
	@Test
	public void testRemoveHidden() {
		System.out.println("removeHidden");

		Player p = new Player("test");
		CardDanish c1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish c2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish c3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish c4 = new CardDanish(Rank.FIVE, Suit.CLUB);

		assertEquals(0, p.hidden.size());
		p.addHidden(c1);
		assertEquals(1, p.hidden.size());
		p.addHidden(c2);
		assertEquals(2, p.hidden.size());
		p.addHidden(c3);
		assertEquals(3, p.hidden.size());
		p.addHidden(c4);
		assertEquals(3, p.hidden.size());

		p.removeHidden(c3);
		assertEquals(2, p.hidden.size());
		p.removeHidden(c2);
		assertEquals(1, p.hidden.size());
		p.removeHidden(c1);
		assertEquals(0, p.hidden.size());
	}

	/**
	 * Test of switchCard method, from class Player.
	 */
	@Test
	public void testSwitchCard() {
		System.out.println("switchCard");

		Player p = new Player("test");
		CardDanish v = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish h = new CardDanish(Rank.THREE, Suit.CLUB);

		p.hand.add(h);
		p.addVisible(v);

		p.switchCard(v, h);

		assertEquals(v, p.hand.get(0));
		assertEquals(h, p.visible.get(0));
	}

	/**
	 * Test of draw method, from class Player.
	 */
	@Test
	public void testDraw() {
		System.out.println("draw");

		Player p = new Player("test");

		CardDanish h1 = new CardDanish(Rank.TWO, Suit.CLUB);
		CardDanish h2 = new CardDanish(Rank.THREE, Suit.CLUB);
		CardDanish h3 = new CardDanish(Rank.FOUR, Suit.CLUB);
		CardDanish v1 = new CardDanish(Rank.FIVE, Suit.CLUB);
		CardDanish v2 = new CardDanish(Rank.SIX, Suit.CLUB);
		CardDanish v3 = new CardDanish(Rank.SEVEN, Suit.CLUB);
		CardDanish r1 = new CardDanish(Rank.EIGHT, Suit.CLUB);
		CardDanish r2 = new CardDanish(Rank.NINE, Suit.CLUB);
		CardDanish r3 = new CardDanish(Rank.TEN, Suit.CLUB);

		p.addHidden(r1);
		p.addHidden(r2);
		p.addHidden(r3);
		p.addVisible(v1);
		p.addVisible(v2);
		p.addVisible(v3);
		p.hand.add(h1);
		p.hand.add(h2);
		p.hand.add(h3);

		assertEquals(3, p.hidden.size());
		assertEquals(3, p.visible.size());
		assertEquals(3, p.hand.size());

		assertFalse(p.draw());

		assertEquals(3, p.hidden.size());
		assertEquals(3, p.visible.size());
		assertEquals(3, p.hand.size());

		p.hand.clear();
		assertFalse(p.draw());

		assertEquals(3, p.hidden.size());
		assertEquals(0, p.visible.size());
		assertEquals(3, p.hand.size());

		p.hand.clear();
		assertFalse(p.draw());

		assertEquals(2, p.hidden.size());
		assertEquals(0, p.visible.size());
		assertEquals(1, p.hand.size());

		p.hand.clear();
		assertFalse(p.draw());

		assertEquals(1, p.hidden.size());
		assertEquals(0, p.visible.size());
		assertEquals(1, p.hand.size());

		p.hand.clear();
		assertFalse(p.draw());

		assertEquals(0, p.hidden.size());
		assertEquals(0, p.visible.size());
		assertEquals(1, p.hand.size());

		p.hand.clear();
		assertTrue(p.draw());

		assertEquals(0, p.hidden.size());
		assertEquals(0, p.visible.size());
		assertEquals(0, p.hand.size());
	}

}
