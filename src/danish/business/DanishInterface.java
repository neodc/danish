package danish.business;

import java.util.List;

/**
 * The interface for a danish game.
 * @author Noé, Julien, Loup.
 */
public interface DanishInterface {
	public List<Player> getPlayers();
	public List<Card> getDeck();
	public List<Card> getStack();
	public List<Card> getGraveyard();
	public boolean isPlaying();
	public int getCurrentPlayer();
	public void setPlayers( List<String> names );
	public boolean begin();
	public void turn( List<Card> cards );
	public void turn( List<Card> cards, int player );
	public void switchCard( int p, Card visible, Card hand );
	public void switchCard( Player player, Card visible, Card hand );
	public Rank getRankStack();
}
