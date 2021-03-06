package danish.model;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class PlayerAITest {

	/**
	 * PlayerAITest constructor without parameter.
	 */
	public PlayerAITest() {
	}

	/**
	 * Test of play method, from class PlayerAI.
	 */
	@Test
	public void testPlay() {
		System.out.println("play");

		ArrayList<String> names = new ArrayList<>();

		Danish instance = new Danish();
		names.add(null);
		names.add(null);
		instance.setPlayers(names);
		instance.begin();

		int i = 0;

		while (instance.getWinner() == null) {
			((PlayerAI) instance.getPlaying()).play();
			assertTrue(++i < 1000);

		}

		assertFalse(instance.isPlaying());

	}
}
