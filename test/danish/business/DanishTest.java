package danish.business;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class DanishTest{

	public DanishTest(){
	}

	/**
	 * Test of getPlayers method, of class Danish.
	 */
	@Test
	public void testGetPlayers(){
		System.out.println( "getPlayers" );

		// vide => return null
		Danish instance = new Danish();

		List<Player> result = instance.getPlayers();
		assertEquals( null, result );

		// 1 joueurs => invalide => return null
		instance = new Danish();
		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );

		instance.setPlayers( names );

		assertEquals( null, instance.getPlayers() );

		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test2" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test3" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test4" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add( "test5" );

		instance.setPlayers( names );

		assertEquals( null, instance.getPlayers() );
	}

	/**
	 * Test of getDeck method, of class Danish.
	 */
	@Test
	public void testGetDeck(){
		System.out.println( "getDeck" );
		Danish instance = new Danish();

		List<Card> result = instance.getDeck();

		for( Suit s : Suit.values() ){
			for( Rank r : Rank.values() ){
				assertTrue( result.contains( new Card( r, s ) ) );
			}
		}
	}

	/**
	 * Test of getStack method, of class Danish.
	 */
	@Test
	public void testGetStack(){
		System.out.println( "getStack" );
		Danish instance = new Danish();

		assertTrue( instance.getStack().isEmpty() );
	}

	/**
	 * Test of getGraveyard method, of class Danish.
	 */
	@Test
	public void testGetGraveyard(){
		System.out.println( "getGraveyard" );
		Danish instance = new Danish();

		assertTrue( instance.getStack().isEmpty() );

	}

	/**
	 * Test of isPlaying method, of class Danish.
	 */
	@Test
	public void testIsPlaying(){
		System.out.println( "isPlaying" );
		Danish instance = new Danish();

		assertFalse( instance.isPlaying() );

		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );
		names.add( "test2" );

		instance.setPlayers( names );
		instance.begin();

		assertTrue( instance.isPlaying() );
	}

	/**
	 * Test of getCurrentPlayer method, of class Danish.
	 */
	@Test
	public void testGetCurrentPlayer(){
		System.out.println( "getCurrentPlayer" );
		Danish instance = new Danish();
		assertEquals( 0, instance.getCurrentPlayer() );
	}

	/**
	 * Test of setPlayers method, of class Danish.
	 */
	@Test
	public void testSetPlayers(){
		System.out.println( "getPlayers" );

		// vide => return null
		Danish instance = new Danish();

		List<Player> result = instance.getPlayers();
		assertEquals( null, result );

		// 1 joueurs => invalide => return null
		instance = new Danish();
		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );

		instance.setPlayers( names );

		assertEquals( null, instance.getPlayers() );

		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test2" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test3" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add( "test4" );

		instance.setPlayers( names );

		result = instance.getPlayers();

		assertEquals( names.size(), result.size() );

		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add( "test5" );

		instance.setPlayers( names );

		assertEquals( null, instance.getPlayers() );
	}

	/**
	 * Test of begin method, of class Danish.
	 */
	@Test
	public void testBegin(){
		System.out.println( "begin" );
		Danish instance = new Danish();

		assertFalse( instance.begin() );

		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );
		names.add( "test2" );

		instance.setPlayers( names );

		assertTrue( instance.begin() );
	}

	/**
	 * Test of turn method, of class Danish.
	 */
	@Test
	public void testTurn_List(){
		System.out.println( "turn" );
		Player p;
		int stacksize;
		int playerHandSize;
		ArrayList<Card> cards = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		Danish instance = new Danish();
		names.add( "test1" );
		names.add( "test2" );
		names.add( "test3" );
		names.add( "test4" );
		instance.setPlayers( names );
		instance.begin();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );

		//test the play with one card
		cards.add( p.getHand().get( 0 ) );
		stacksize = instance.getStack().size();
		instance.turn( cards );

		assertTrue( instance.getStack().containsAll( cards )
				|| verifyAceOrTen( instance.getStack(), cards, stacksize ) );
		assertTrue( !( p.getHand().contains( cards.get( 0 ) ) )
				|| cards.get( 0 ).getRank() == Rank.ACE );

		//test the fact that the stack isn't affected when an 
		//ACE is played
		if( cards.get( 0 ).getRank() == Rank.ACE ){
			assertFalse( instance.getStack().contains( cards.get( 0 ) ) );
			assertTrue( p.getHand().contains( cards.get( 0 ) ) );
		}

		//test the play with no cards
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		playerHandSize = p.getHand().size();
		stacksize = instance.getStack().size();

		instance.turn( cards );

		assertTrue( ( instance.getStack().isEmpty() ) && ( p.getHand().size() == playerHandSize + stacksize ) );

		instance = new Danish();
		instance.setPlayers( names );
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );

		cards.add( p.getHand().get( 0 ) );

		stacksize = instance.getStack().size();
		//test the play with card we have
		instance.turn( cards );
		assertTrue( instance.getStack().size() == stacksize + cards.size()
				|| verifyAceOrTen( instance.getStack(), cards, stacksize ) );
		//test the play with card we don't have (we are playing the same cards than before)
		instance.turn( cards );
		assertTrue( instance.getStack().size() == stacksize + cards.size()
				|| verifyAceOrTen( instance.getStack(), cards, stacksize ) );

		instance = new Danish();
		instance.setPlayers( names );
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );

		// playing the hand with diffenrent rank
		cards.addAll( p.getHand() );

		if( !verifyHand( cards ) ){
			instance.turn( cards );
			//He's playing different ranks
			assertTrue( instance.getStack().isEmpty() );
		}

		instance = new Danish();
		instance.setPlayers( names );

		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		cards.add( p.getHand().get( 0 ) );

		// play until an unplacable card is found
		while( instance.getRankStack().placeable( cards.get( 0 ).getRank() ) ){
			if( cards.get( 0 ).getRank() == Rank.ACE ){
				instance.turn( cards, ( instance.getCurrentPlayer() + 1 ) % names.size() );
			}else{
				instance.turn( cards );
			}
			p = instance.getPlayers().get( instance.getCurrentPlayer() );
			cards.clear();
			cards.add( p.getHand().get( 0 ) );
		}
		// test the play with an unplaceable card
		stacksize = instance.getStack().size();
		instance.turn( cards );
		assertTrue( instance.getStack().size() == stacksize );

		instance = new Danish();
		instance.setPlayers( names );
		Card diamond = new Card( Rank.EIGHT, Suit.DIAMOND );
		Card spade = new Card( Rank.EIGHT, Suit.SPADE );
		Card club = new Card( Rank.EIGHT, Suit.CLUB );
		Card heart = new Card( Rank.EIGHT, Suit.HEART );
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		// play while the player don't have a eight
		while( !( p.getHand().contains( diamond )
				 || p.getHand().contains( spade )
				 || p.getHand().contains( club )
				 || p.getHand().contains( heart ) ) ){
			cards.clear();
			cards.add( p.getHand().get( 0 ) );
			Player nextP = instance.getPlayers().get( ( instance.getCurrentPlayer() + 1 ) % names.size() );
            // if the card is not playable, or if the next player have a eight, we take the stack
			if( !( instance.getRankStack().placeable( cards.get( 0 ).getRank() ) )
					|| ( nextP.getHand().contains( diamond )
					|| nextP.getHand().contains( spade )
					|| nextP.getHand().contains( club )
					|| nextP.getHand().contains( heart ) ) ){
				cards.clear();
				instance.turn( cards );
			}else if( cards.get( 0 ).getRank() == Rank.ACE ){
				instance.turn( cards, ( instance.getCurrentPlayer() + 1 ) % names.size() );
			}else{
				instance.turn( cards );
			}
			p = instance.getPlayers().get( instance.getCurrentPlayer() );
		}
		cards.clear();
		// construct the cards to play with the eight the player has
		if( p.getHand().contains( diamond ) ){
			cards.add( diamond );
		}
		if( p.getHand().contains( spade ) ){
			cards.add( spade );
		}
		if( p.getHand().contains( club ) ){
			cards.add( club );
		}
		if( p.getHand().contains( heart ) ){
			cards.add( heart );
		}

		int oldPlayer = instance.getCurrentPlayer();
		instance.turn( cards );
		assertTrue( instance.getCurrentPlayer() == ( oldPlayer + 1 + cards.size() ) % names.size() );
	}

	/**
	 * Test of turn method, of class Danish, the attack version.
	 */
	@Test
	public void testTurn_List_int(){
		System.out.println( "turn-attack" );
		Player p;
		int nextP;
		int currentP;
		int stacksize;
		int playerHandSize;
		ArrayList<Card> cards = new ArrayList<>();
		ArrayList<String> names = new ArrayList<>();
		Danish instance = new Danish();
		names.add( "test1" );
		names.add( "test2" );
		names.add( "test3" );
		names.add( "test4" );
		instance.setPlayers( names );
		instance.begin();

		//test the attack with no cards
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();
		playerHandSize = p.getHand().size();
		stacksize = instance.getStack().size();
		currentP = instance.getCurrentPlayer();

		instance.turn( cards, nextP );

		instance.turn( cards, nextP );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );

		cards.add( p.getHand().get( 0 ) );

		//test the play with card we have
		if( cards.get( 0 ).getRank() == Rank.ACE ){
			instance.turn( cards, nextP );
		}else{
			instance.turn( cards );
		}
		nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();
		playerHandSize = p.getHand().size();
		stacksize = instance.getStack().size();
		currentP = instance.getCurrentPlayer();

        //test the attack with card we don't have (we are playing 
		//the same cards than before)
		instance.turn( cards, nextP );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );

		instance = new Danish();
		instance.setPlayers( names );
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();

		// playing the hand with diffenrent rank
		cards.addAll( p.getHand() );
		instance.turn( cards, nextP );

		// He's playing different ranks
		if( !verifyHand( cards ) ){
			assertTrue( instance.getStack().isEmpty() );
		}

		instance = new Danish();
		instance.setPlayers( names );
		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();

		cards.add( p.getHand().get( 0 ) );
		if( cards.get( 0 ).getRank() == Rank.ACE ){
			instance.turn( cards, nextP );
		}else{
			instance.turn( cards );
		}

		cards.clear();
		p = instance.getPlayers().get( instance.getCurrentPlayer() );
		nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();
		cards.add( p.getHand().get( 0 ) );

		// attack the next player while we have ACE or THREE over ACE
		while( cards.get( 0 ).getRank() == Rank.ACE
				|| ( cards.get( 0 ).getRank() == Rank.THREE
					&& instance.getRankStack() == Rank.ACE ) ){
			instance.turn( cards, nextP );
			p = instance.getPlayers().get( instance.getCurrentPlayer() );
			nextP = ( instance.getCurrentPlayer() + 1 ) % names.size();
			cards.clear();
			cards.add( p.getHand().get( 0 ) );
		}
        // attack the next player with no ACE or THREE over ACE (else it would still be in the while)
		playerHandSize = p.getHand().size();
		stacksize = instance.getStack().size();
		currentP = instance.getCurrentPlayer();
		instance.turn( cards, nextP );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );

		// play while we don't have a ACE or a THREE over ACE
		while( cards.get( 0 ).getRank() != Rank.ACE
				&& ( cards.get( 0 ).getRank() != Rank.THREE
					|| instance.getRankStack() != Rank.ACE ) ){
			if( !instance.getRankStack().placeable( cards.get( 0 ).getRank() ) ){
				cards.clear();
				instance.turn( cards );
			}else{
				instance.turn( cards );
			}
			p = instance.getPlayers().get( instance.getCurrentPlayer() );
			cards.clear();
			cards.add( p.getHand().get( 0 ) );
		}

		playerHandSize = p.getHand().size();
		stacksize = instance.getStack().size();
		currentP = instance.getCurrentPlayer();

		// attack himself
		instance.turn( cards, instance.getCurrentPlayer() );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );
		// attack invalid player
		instance.turn( cards, -1 );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );
		instance.turn( cards, 5 );
		assertTrue( ( instance.getStack().size() == stacksize )
				&& ( p.getHand().size() == playerHandSize )
				&& currentP == instance.getCurrentPlayer() );

		// attack correct player
		nextP = ( instance.getCurrentPlayer() + 2 ) % names.size();
		instance.turn( cards, nextP );

		assertTrue( instance.getStack().containsAll( cards )
				&& !instance.getPlayers().get( currentP ).getHand().containsAll( cards )
				&& instance.getCurrentPlayer() == nextP );
	}

	/**
	 * Test of switchCard method, of class Danish.
	 */
	@Test
	public void testSwitchCard_3args_1(){
		System.out.println( "switchCard" );
		int p = 0;
		Card visible;
		Card hand;
		Danish instance = new Danish();

		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );
		names.add( "test2" );

		instance.setPlayers( names );

		visible = instance.getPlayers().get( p ).getVisible().get( 0 );
		hand = instance.getPlayers().get( p ).getHand().get( 0 );

		instance.switchCard( p, visible, hand );

		assertTrue( instance.getPlayers().get( p ).getHand().contains( visible ) );
		assertTrue( instance.getPlayers().get( p ).getVisible().contains( hand ) );
	}

	/**
	 * Test of switchCard method, of class Danish.
	 */
	@Test
	public void testSwitchCard_3args_2(){
		System.out.println( "switchCard" );
		Player p;
		Card visible;
		Card hand;
		Danish instance = new Danish();

		ArrayList<String> names = new ArrayList<>();
		names.add( "test" );
		names.add( "test2" );

		instance.setPlayers( names );

		p = instance.getPlayers().get( 0 );

		visible = p.getVisible().get( 0 );
		hand = p.getHand().get( 0 );

		instance.switchCard( p, visible, hand );

		assertTrue( p.getHand().contains( visible ) );
		assertTrue( p.getVisible().contains( hand ) );
	}

	private boolean verifyAceOrTen( List<Card> stack, ArrayList<Card> cards, int stacksize ){
		return stacksize == stack.size() && cards.get( 0 ).getRank() == Rank.ACE
				|| stack.isEmpty() && cards.get( 0 ).getRank() == Rank.TEN;

	}

	private boolean verifyHand( List<Card> hand ){
		Rank rank = null;
		for( Card c : hand ){
			if( rank == null ){
				rank = c.getRank();
			}else if( c.getRank() != rank ){ // He's playing different ranks
				return false;
			}
		}
		return true;
	}
}
