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
	
	boolean playing;
	int currentPlayer;
	Player winner;

	public Danish(){
		players = null;
		
		this.initDeck();
		stack = new LinkedList<>();
		graveyard = new LinkedList<>();
		
		playing = false;
		currentPlayer = 0;
		winner = null;
	}

	public List<Player> getPlayers(){
		if( players == null ){
			return null;
		}
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

	public boolean isPlaying(){
		return playing;
	}

	public int getCurrentPlayer(){
		return currentPlayer;
	}
	
	public void setPlayers( List<String> names ){
		if(players == null && names.size() >= 2 && names.size() <= 4){
			
			players = new ArrayList<>(names.size());
			Player p;
			
			for( String name : names ){
				p = new Player(name);
				
				for( int i = 0; i < 3; ++i ){
					p.getHand().add(deck.poll());
					p.addHidden(deck.poll());
					p.addVisible(deck.poll());
				}
				
				players.add(p);
			}
			
			Collections.shuffle(players);
		}
	}
	
	public boolean begin(){
		if( players != null && winner == null ){
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
		
		if( cards.isEmpty() || !playing().getHand().containsAll( cards ) ){ // on ne joue pas de carte ou on joue des carte que l'on a pas
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
		
		if( rank != Rank.ACE && (rank != Rank.THREE || getRankStack() != Rank.ACE) ){ // soit on joue un as soit on en copie un
			return;
		}
		
		if( player < 0 || player >= players.size() || player == currentPlayer ){ // player invalide ou actuel
			return;
		}
		
		// fin des test => on effectue
		
		playing().getHand().removeAll(cards);
		
		stack.addAll(cards);
		
		draw();
		
		if( doesCut() ){
			return;
		}
		
		currentPlayer = player;
	}
	
	public void switchCard( int p, Card visible, Card hand ){
		if( (p >= 0 || p < players.size()) && players != null && !playing ){
			switchCard(players.get(p), visible, hand);
		}
	}
	
	public void switchCard( Player player, Card visible, Card hand ){
		if( !playing ){
			player.switchCard(visible, hand);
		}
	}
	
	
	
	private void initDeck(){
		deck = new LinkedList<>();
		
		for( Suit s : Suit.values() ){
			for( Rank r : Rank.values() ){
				deck.add( new Card(r, s) );
			}
		}
		
		Collections.shuffle( deck );
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
		
		if( playing().draw() ){
			winner = playing();
			playing = false;
		}
	}
	
	public static void main( String[] args ){
		
	}
	
}
