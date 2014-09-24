package danish.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Danish{

	List<Player> players;
	
	LinkedList<Card> deck;
	LinkedList<Card> stack;
	LinkedList<Card> graveyard;
	
	boolean finish;
	boolean playing;
	int currentPlayer;

	public Danish(){
		players = new ArrayList<>();
		
		this.initDeck();
		stack = new LinkedList<>();
		graveyard = new LinkedList<>();
		
		finish = false;
		playing = false;
		currentPlayer = 0;
	}

	public List<Player> getPlayers(){
		return new ArrayList<>(players);
	}

	public List<Card> getDeck(){
		return new ArrayList<>(deck);
	}

	public List<Card> getStack(){
		return new ArrayList<>(stack);
	}

	public List<Card> getGraveyard(){
		return new ArrayList<>(graveyard);
	}

	public boolean isFinish(){
		return finish;
	}

	public boolean isPlaying(){
		return playing;
	}

	public int getCurrentPlayer(){
		return currentPlayer;
	}
	
	public boolean addPlayer( String name ){
		if( !playing && players.size() < 4 ){
			return players.add( new Player(name) );
		}
		return false;
	}
	
	public boolean begin(){
		if( players.size() > 0 ){
			Collections.shuffle(players);
			playing = true;
		}
		return playing;
	}
	
	public void turn( List<Card> cards ){
		
		if( cards.isEmpty() ){ // il prend
			take( playing() );
			return;
		}
		
		if( !playing().getHand().containsAll( cards ) ){ // on joue des carte que l'on a pas
			return;
		}
		
		Rank rank = null;
		
		for( Card c : cards ){
			if( rank == null ){
				rank = c.getRank();
			}else if( c.getRank() != rank ){ // différent rank
				return;
			}
		}
		
		if( rank == Rank.ACE ){ // devrait indiquer qui il attaque
			return;
		}
		
		if( !stack.isEmpty() && !getRankStack().placeable(rank) ){ // carte non jouable
			return;
		}
		
		// fin des test => on effectue
		
		playing().getHand().removeAll(cards);
		
		stack.addAll(cards);
		
		draw();
		
		if( doesCut() ){
			return;
		}
		
		int i = 1;
		if( rank == Rank.EIGHT ){
			i += cards.size();
		}
		
		currentPlayer = (currentPlayer+i)%players.size();
		
	}
	
	public void turn( List<Card> cards, int player ){
		
		// TODO
		
	}
	
	
	private void initDeck(){
		deck = new LinkedList<>();
		
		for( Suit s : Suit.values() ){
			for( Rank r : Rank.values() ){
				deck.add( new Card(r, s) );
			}
		}
	}
	
	private Player playing(){
		return players.get(currentPlayer);
	}
	
	private void take( Player p ){
		p.getHand().addAll( stack );
		stack.clear();
	}
	
	private Rank getRankStack(){
		Iterator<Card> i = stack.descendingIterator();
		Rank r = null;
		
		while(i.hasNext() && (r = i.next().getRank()) == Rank.THREE){}
		
		return r;
	}
	
	private boolean doesCut(){
		Iterator<Card> i = stack.descendingIterator();
		Rank r = null;
		Rank ir;
		int cpt = 0;
		
		while(i.hasNext()){
			ir = i.next().getRank();
			
			if( r == null ){
				r = ir;
			}else if( r != ir ){
				return false;
			}
			
			if( r == Rank.TEN || ++cpt == 4 ){
				graveyard.addAll( stack );
				stack.clear();
				
				return true;
			}
		}
		
		return false;
	}
	
	private void draw(){
		
		while( !deck.isEmpty() && playing().getHand().size() < 3 ){
			playing().getHand().add( deck.poll() );
		}
		
	}
	
	public static void main( String[] args ){
		
	}
	
}
