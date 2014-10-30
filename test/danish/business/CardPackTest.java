package danish.business;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardPackTest {
        CardPack cardPack;
    	
	public CardPackTest(){
            cardPack = new CardPack();
	}

        
	@Test
	public void testPeek(){
		System.out.println( "Peek" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
                CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
                cardPack.add(test1);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
                cardPack.add(test2);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
                cardPack.add(test3);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
                
	}
        
	@Test 
	public void testAdd(){
		System.out.println( "Add" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
                CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
                //TODO test exeptions
                assertTrue(cardPack.isEmpty());
                cardPack.add(test1);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
                assertTrue(cardPack.size() == 1);
                cardPack.add(test2);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
                assertTrue(cardPack.size() == 2);
                cardPack.add(test3);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
                assertTrue(cardPack.size() == 3);
	}
        
	@Test 
	public void testOffer(){
		System.out.println( "Offer" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
                CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
                //TODO test exeptions
                assertTrue(cardPack.isEmpty());
                cardPack.offer(test1);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.CLUB)));
                assertTrue(cardPack.size() == 1);
                cardPack.offer(test2);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.DIAMOND)));
                assertTrue(cardPack.size() == 2);
                cardPack.offer(test3);
                assertTrue(cardPack.peek().equals(new CardDanish(Rank.ACE, Suit.HEART)));
                assertTrue(cardPack.size() == 3);
	}
        
	@Test
	public void testClear(){
		System.out.println( "Clear" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                assertTrue(cardPack.isEmpty());
                cardPack.add(test1);
                cardPack.clear();
                assertTrue(cardPack.isEmpty());
	}
	@Test
	public void testEmpty(){
		System.out.println( "Empty" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                assertTrue(cardPack.isEmpty());
                cardPack.add(test1);
                assertFalse(cardPack.isEmpty());
                cardPack.clear();
                assertTrue(cardPack.isEmpty());
	}
	@Test
	public void testNumberSimilarCard(){
		System.out.println( "NumberSimilarCard" );
                CardDanish test1 = new CardDanish(Rank.ACE, Suit.CLUB);
                CardDanish test2 = new CardDanish(Rank.ACE, Suit.DIAMOND);
                CardDanish test3 = new CardDanish(Rank.ACE, Suit.HEART);
                CardDanish test4 = new CardDanish(Rank.ACE, Suit.SPADE);
                assertTrue(cardPack.getNumberSimilarCard() == 0);
                cardPack.offer(test1);
                assertTrue(cardPack.getNumberSimilarCard() == 1);
                cardPack.offer(test2);
                assertTrue(cardPack.getNumberSimilarCard() == 2);
                cardPack.offer(test3);
                assertTrue(cardPack.getNumberSimilarCard() == 3);
                cardPack.offer(test4);
                assertTrue(cardPack.getNumberSimilarCard() == 4);
                cardPack.clear();
                
	}
        
        
}
