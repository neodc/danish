package danish.business;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The class representing the whole Danish game.
 *
 * @author Noé, Julien, Loup.
 */
public class Danish implements DanishInterface {

	private List<Player> players;

	private CardPack deck;
	private CardPack stack;
	private CardPack graveyard;

	private boolean playing;
	private int currentPlayer;
	private Player winner;

	/**
	 * Getter of the winner.
	 * 
	 * @return The winner, null if not over.
	 */
	public Player getWinner(){
		return winner;
	}

	/**
	 * Danish constructor without parameter.
	 */
	public Danish() {
		this.players = null;

		this.initDeck();
		this.stack = new CardPack();
		this.graveyard = new CardPack();

		this.playing = false;
		this.currentPlayer = 0;
		this.winner = null;
	}

	/**
	 * Getter of the players.
	 *
	 * @return The list of the players, null if the list is empty.
	 */
	@Override
	public List<Player> getPlayers() {
		if (this.players == null) {
			return null;
		}
		return new ArrayList<>(players);
	}

	/**
	 * Getter of the deck, the cards to draw.
	 *
	 * @return The deck.
	 */
	@Override
	public CardPack getDeck() {
		return new CardPack(deck);
	}

	/**
	 * Getter of the stack, the played cards of one trick.
	 *
	 * @return The stack.
	 */
	@Override
	public CardPack getStack() {
		return new CardPack(stack);
	}

	/**
	 * Getter of the graveyard, the cards that have been removed from play.
	 *
	 * @return The graveyard.
	 */
	@Override
	public CardPack getGraveyard() {
		return new CardPack(graveyard);
	}

	/**
	 * Tells if the game has begun.
	 *
	 * @return true if the game has begun, false otherwise.
	 */
	@Override
	public boolean isPlaying() {
		return playing;
	}

	/**
	 * Getter of the index of the current player in the players list.
	 *
	 * @return The index of the current player.
	 */
	@Override
	public int getCurrentPlayer() {
		return currentPlayer;
	}

	/**
	 * Creates the list of players and deals their cards.
	 * Any null name will create an AI player.
	 *
	 * @param names The name of the players.
	 */
	@Override
	public void setPlayers(List<String> names) {
		if (players == null && names.size() >= 2 && names.size() <= 4) {

			players = new ArrayList<>(names.size());
			Player p;

			for (String name : names) {
				if (name == null) {
					p = new PlayerAI(this);
				} else {
					p = new Player(name);
				}

				for (int i = 0; i < 3; ++i) {
					p.hand.add(deck.poll());
					p.addHidden(deck.poll());
					p.addVisible(deck.poll());
				}

				players.add(p);
			}

			Collections.shuffle(players);
		}
	}

	/**
	 * Makes the match begin.
	 * It cannot begin if the player list is null or if there is a winner.
	 *
	 * @return true if the match has successfully begun, false otherwise.
	 */
	@Override
	public boolean begin() {
		if (players != null && winner == null) {
			playing = true;
		}
		return playing;
	}

	/**
	 * Resolves a turn.
	 * It will check if the cards are valid and will change the current player if the turn has been played without problem.
	 *
	 * @param cards The cards to be played.
	 */
	@Override
	public void turn(List<CardDanish> cards) {

		if (cards.isEmpty()) { // The player takes because he doesn't play anything
			take(getPlaying());
			return;
		}

		if (!getPlaying().hand.containsAll(cards)) { // He plays cards he doesn't have
			return;
		}

		Rank rank = null;

		for (CardDanish c : cards) {
			if (rank == null) {
				rank = c.getRank();
			} else if (c.getRank() != rank) { // He's playing different ranks
				return;
			}
		}

		if (rank == Rank.ACE) { // He should declare who he's attacking
			return;
		}

		if (!stack.isEmpty() && !getRankStack().placeable(rank)) { // Unplayable cards
			return;
		}

		// End of tests => The turn is now resolved
		getPlaying().hand.removeAll(cards);

		stack.addAll(cards);

		draw();

		if (doesCut()) {
			return;
		}

		int i = 1;
		if (rank == Rank.EIGHT) {
			i += cards.size();
		}

		currentPlayer = (currentPlayer + i) % players.size();
	}

	/**
	 * Resolves an attack.
	 *
	 * @param cards The cards to be played (normally, only aces and threes).
	 * @param player The attacked player.
	 */
	@Override
	public void turn(List<CardDanish> cards, int player) {

		if (cards.isEmpty() || !getPlaying().hand.containsAll(cards)) {	// The player's playing nothing
			return;															// or cards he doesn't have
		}

		Rank rank = null;

		for (CardDanish c : cards) {
			if (rank == null) {
				rank = c.getRank();
			} else if (c.getRank() != rank) { // Different ranks
				return;
			}
		}

		if (rank != Rank.ACE && (rank != Rank.THREE || getRankStack() != Rank.ACE)) {	// He doesn't play an ACE
			return;																		// or a THREE above an ACE
		}

		if (player < 0 || player >= players.size() || player == currentPlayer) {	// He attacks himself
			return;																	// or a non-existing player
		}

		// End of tests => The turn is now resolved
		getPlaying().hand.removeAll(cards);

		stack.addAll(cards);

		draw();

		if (doesCut()) {
			return;
		}

		currentPlayer = player;
	}

	/**
	 * Switches a card in the hand with a visible one.
	 *
	 * @param p The index of the player who wants to switch.
	 * @param visible The visible card to take in hand.
	 * @param hand The card in hand to make visible.
	 */
	@Override
	public void switchCard(int p, CardDanish visible, CardDanish hand) {
		if ((p >= 0 || p < players.size()) && players != null) {
			switchCard(players.get(p), visible, hand);
		}
	}

	/**
	 * Switches a card in the hand with a visible one.
	 *
	 * @param player The player who wants to switch.
	 * @param visible The visible card to take in hand.
	 * @param hand The card in hand to make visible.
	 */
	@Override
	public void switchCard(Player player, CardDanish visible, CardDanish hand) {
		if (!playing) {
			player.switchCard(visible, hand);
		}
	}

	/**
	 * Checks the rank of the last card of the stack.
	 *
	 * @return The rank of the last card of the stack.
	 */
	@Override
	public Rank getRankStack() {
		if (stack.isEmpty()) {
			return Rank.TWO;
		}
		
		return stack.peek().getRealRank();
	}

	public Player getPlaying() {
		return players.get(currentPlayer);
	}

	private void initDeck() {
		ArrayList<CardDanish> list = new ArrayList<>();

		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				list.add(new CardDanish(r, s));
			}
		}

		Collections.shuffle(list);
		
		deck = new CardPack(list);
	}

	private void take(Player p) {
		p.hand.addAll(stack);
		stack.clear();
		currentPlayer = (currentPlayer + 1) % players.size();
	}

	private boolean doesCut() {
		System.out.print( "<"+this.getRankStack()+" - "+stack.getNumberSimilarCard() );
		
		if( this.getRankStack() == Rank.TEN || stack.getNumberSimilarCard() >= 4){
			graveyard.pour(stack);
			System.out.println( ">" );
			return true;
		}
		System.out.println( ">" );
		return false;
	}

	private void draw() {

		while (!deck.isEmpty() && getPlaying().hand.size() < 3) {
			getPlaying().hand.add(deck.poll());
		}

		if (getPlaying().draw()) {
			winner = getPlaying();
			playing = false;
		}
	}
}
