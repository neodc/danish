package danish.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a non-human Player. The player is controlled by an
 * artificial intelligence.
 *
 * @author Noé, Julien, Loup.
 */
public class PlayerAI extends Player {

	private final Danish danish;
	private boolean hasChosenVisible;

	/**
	 * PlayerAI constructor with one parameter.
	 *
	 * @param danish The danish the AI is playing in.
	 */
	public PlayerAI(Danish danish) {
		super("AI");
		this.danish = danish;
		this.hasChosenVisible = false;
	}
	
	/**
	 * Makes the AI choose the visible cards. It'll choose the most powerful ones.
	 */
	public void chooseVisibleCards(){
		if( !this.hasChosenVisible ){
			this.hasChosenVisible = true;
			
			for( int i = 0; i < visible.size(); ++i ){
				for(int j = 0; j < hand.size(); ++j){
					if (hand.get(j).compareTo(visible.get(i)) > 0){
						System.out.println( this.toString() + " > " + visible.get(i) + " <=> " + hand.get(j) );
						switchCard(visible.get(i), hand.get(j));
					}
				}
			}
		}
	}

	/**
	 * Plays a turn of the AI. It will play the least powerful cards he can.
	 */
	public void play() {
		List<CardDanish> cards = new ArrayList<>();

		for (CardDanish c : hand) {
			if (danish.getStack().placeable(c)) {
				if (cards.isEmpty() || cards.get(0).compareTo(c) == 0) {
					cards.add(c);
				} else if (cards.get(0).compareTo(c) > 0) {
					cards.clear();
					cards.add(c);
				}
			}
		}

		if (!cards.isEmpty() && (cards.get(0).getRank() == Rank.ACE || (cards.get(0).getRank() == Rank.THREE && danish.getRankStack() == Rank.ACE))) {
			Player player = null;
			Player oPlayer;
			int i;
			int iPlayer = 0;

			for (i = 0; i < danish.getPlayers().size(); ++i) {
				oPlayer = danish.getPlayers().get(i);

				if (oPlayer == this) {
					continue;
				}

				if (player == null) {
					player = oPlayer;
					iPlayer = i;
				} else if (oPlayer.hidden.size() < player.hidden.size()
						|| oPlayer.visible.size() < player.visible.size()
						|| oPlayer.hand.size() < player.hand.size()) {
					player = oPlayer;
					iPlayer = i;
				}
			}

			danish.turn(cards, iPlayer);
		} else {
			danish.turn(cards);
		}
	}
}
