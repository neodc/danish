package danish.model;

import java.util.List;

/**
 * The interface for a Danish game.
 *
 * @author Noé, Julien, Loup.
 */
public interface DanishModel {

	public void addDanishListener(DanishView view);
	
	public void removeDanishListener(DanishView view);
	
	/**
	 * Getter of the players.
	 *
	 * @return The list of the players, null if the list is empty.
	 */
	public List<Player> getPlayers();

	/**
	 * Getter of the deck, the cards to draw.
	 *
	 * @return The deck.
	 */
	public CardPack getDeck();

	/**
	 * Getter of the stack, the played cards of one trick.
	 *
	 * @return The stack.
	 */
	public CardPack getStack();

	/**
	 * Getter of the graveyard, the cards that have been removed from play.
	 *
	 * @return The graveyard.
	 */
	public CardPack getGraveyard();

	/**
	 * Tells if the game has begun.
	 *
	 * @return true if the game has begun, false otherwise.
	 */
	public Player getPlaying();

	/**
	 * Tells if the game has begun.
	 *
	 * @return true, if the game has begun.
	 */
	public boolean isPlaying();

	/**
	 * Getter of the index of the current player in the players list.
	 *
	 * @return The index of the current player.
	 */
	public int getCurrentPlayer();

	/**
	 * Creates the list of players and deals their cards.
	 *
	 * @param names The name of the players.
	 */
	public void setPlayers(List<String> names);

	/**
	 * Makes the match begin.
	 *
	 * @return true, if the match has begun.
	 */
	public boolean begin();

	/**
	 * Resolves a turn.
	 *
	 * @param cards The cards to be played.
	 */
	public void turn(List<CardDanish> cards);

	/**
	 * Resolves an attack.
	 *
	 * @param cards The cards to be played (normally, only aces and threes).
	 * @param player The attacked player.
	 */
	public void turn(List<CardDanish> cards, int player);

	/**
	 * Switches a card in the hand with a visible one.
	 *
	 * @param p The index of the player who wants to switch.
	 * @param visible The visible card to take in hand.
	 * @param hand The card in hand to make visible.
	 */
	public void switchCard(int p, CardDanish visible, CardDanish hand);

	/**
	 * Switches a card in the hand with a visible one.
	 *
	 * @param player The player who wants to switch.
	 * @param visible The visible card to take in hand.
	 * @param hand The card in hand to make visible.
	 */
	public void switchCard(Player player, CardDanish visible, CardDanish hand);

	/**
	 * Check the rank of the stack.
	 *
	 * @return The rank of the stack.
	 */
	public Rank getRankStack();

}
