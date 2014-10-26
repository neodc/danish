package danish.business;

import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author neodc
 */
public class PlayerAITest{
	
	public PlayerAITest(){
	}

	/**
	 * Test of play method, of class PlayerAI.
	 */
	@Test
	public void testPlay(){
		System.out.println( "play" );
		
		ArrayList<String> names = new ArrayList<>();
		
		Danish instance = new Danish();
		names.add( null );
		names.add( null );
		instance.setPlayers( names );
		instance.begin();
		
		int i = 0;
		
		while(instance.getWinner() == null){
			((PlayerAI)instance.getPlayers().get( instance.getCurrentPlayer() )).play();
			assertTrue( ++i < 1000 );
			
		}
		
		assertFalse( instance.isPlaying() );
		
	}
}
