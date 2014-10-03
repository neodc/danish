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
		names.add("test");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
		
		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test2");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test3");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test4");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add("test5");
		
		instance.setPlayers(names);
		
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
				assertTrue( result.contains( new Card(r, s) ) );
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
		
		assertTrue(instance.getStack().isEmpty() );
	}

	/**
	 * Test of getGraveyard method, of class Danish.
	 */
	@Test
	public void testGetGraveyard(){
		System.out.println( "getGraveyard" );
		Danish instance = new Danish();
		
		assertTrue(instance.getStack().isEmpty() );
		
	}

	/**
	 * Test of isPlaying method, of class Danish.
	 */
	@Test
	public void testIsPlaying(){
		System.out.println( "isPlaying" );
		Danish instance = new Danish();
		
		assertFalse(instance.isPlaying() );
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		instance.begin();
		
		assertTrue(instance.isPlaying());
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
		names.add("test");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
		
		// 2 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test2");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 3 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test3");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 4 joueurs => return le bon nombre de joueurs
		instance = new Danish();
		names.add("test4");
		
		instance.setPlayers(names);
		
		result = instance.getPlayers();
		
		assertEquals( names.size(), result.size() );
		
		// 5 joueurs => invalide => return null
		instance = new Danish();
		names.add("test5");
		
		instance.setPlayers(names);
		
		assertEquals( null, instance.getPlayers() );
	}

	/**
	 * Test of begin method, of class Danish.
	 */
	@Test
	public void testBegin(){
		System.out.println( "begin" );
		Danish instance = new Danish();
		
		assertFalse(instance.begin() );
		
		ArrayList<String> names = new ArrayList<>();
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		assertTrue(instance.begin() );
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
		names.add("test1");
		names.add("test2");
		instance.setPlayers(names);
                instance.begin();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                
                //test the play with one card
                cards.add(p.getHand().get(0));
                instance.turn(cards);
                
                assertTrue(instance.getStack().containsAll(cards)
                        || verifyAceOrTen(instance.getStack(), cards));
                assertTrue(!(p.getHand().contains(cards.get(0))) 
                        || cards.get(0).getRank() == Rank.ACE);
                
                //test the fact that the stack isn't affected when an 
                //ACE is played
                if (cards.get(0).getRank() == Rank.ACE){
                    assertFalse(instance.getStack().contains(cards.get(0)));
                }
                
                
                //test the play with no cards
                cards.clear();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                playerHandSize = p.getHand().size();
                stacksize = instance.getStack().size();
                
                instance.turn(cards);
                
                assertTrue((instance.getStack().isEmpty())&&(p.getHand().size() 
                        == playerHandSize + stacksize));
                
                
		instance = new Danish();
		instance.setPlayers(names);
                cards.clear();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                
                cards.add(p.getHand().get(0));
                
                stacksize = instance.getStack().size();
                //test the play with card we have
                instance.turn(cards);
                assertTrue(instance.getStack().size()== stacksize + cards.size()
                        || verifyAceOrTen(instance.getStack(), cards));
                //test the play with card we don't have (we are playing 
                //the same cards than before)
                instance.turn(cards);
                assertTrue(instance.getStack().size()== stacksize + cards.size()
                        || verifyAceOrTen(instance.getStack(), cards));
                
                
		instance = new Danish();
		instance.setPlayers(names);
                cards.clear();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                
                // playing the hand with diffenrent rank
                cards.addAll(p.getHand());
                
                if (!verifyHand(cards)){
                    instance.turn(cards);
                    //He's playing different ranks
                    assertTrue(instance.getStack().isEmpty());
                }
                
		instance = new Danish();
		instance.setPlayers(names);
                
                /*cards.clear();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                cards.add(p.getHand().get(0));
                instance.turn(cards);
                cards.clear();
                p = instance.getPlayers().get(instance.getCurrentPlayer());
                cards.add(p.getHand().get(0));
                
                while(instance.getRankStack().placeable(cards.get(0).getRank())){
                    instance.turn(cards);
                    p = instance.getPlayers().get(instance.getCurrentPlayer());
                    cards.clear();
                    cards.add(p.getHand().get(0));
                }
                stacksize = instance.getStack().size();
                instance.turn(cards);
                System.out.println(cards.get(0));
                assertTrue(instance.getStack().size() == stacksize);*/
                
	}

	/**
	 * Test of turn method, of class Danish.
	 */
	@Test
	public void testTurn_List_int(){
		/*System.out.println( "turn" );
		List<Card> cards = null;
		int player = 0;
		Danish instance = new Danish();
		instance.turn( cards, player );
		// TODO review the generated test code and remove the default call to fail.
		fail( "The test case is a prototype." );*/
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
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		visible = instance.getPlayers().get(p).getVisible().get(0);
		hand = instance.getPlayers().get(p).getHand().get(0);
		
		instance.switchCard( p, visible, hand );
		
		assertTrue( instance.getPlayers().get(p).getHand().contains( visible ) );
		assertTrue( instance.getPlayers().get(p).getVisible().contains( hand ) );
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
		names.add("test");
		names.add("test2");
		
		instance.setPlayers(names);
		
		p = instance.getPlayers().get(0);
		
		visible = p.getVisible().get(0);
		hand = p.getHand().get(0);
		
		instance.switchCard( p, visible, hand );
		
		assertTrue( p.getHand().contains( visible ) );
		assertTrue( p.getVisible().contains( hand ) );
	}
        
        private boolean verifyAceOrTen(List<Card> stack, ArrayList<Card> cards){
            return cards.get(0).getRank() == Rank.ACE 
                        || stack.isEmpty() && cards.get(0).getRank() == Rank.TEN;
            
        }
        private boolean verifyHand(List<Card> hand){
            Rank rank = null;
            for (Card c : hand) {
                if (rank == null) {
                        rank = c.getRank();
                } else if (c.getRank() != rank) { // He's playing different ranks
                        return false;
                }
            }
            return true;
        }
}
