package danish.business;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a non-human Player.
 * The player is controlled by an artificial intelligence.
 *
 * @author Noé, Julien, Loup.
 */
public class PlayerAI extends Player {

	private final Danish danish;

	/**
	 * PlayerAI constructor with one parameter.
	 *
	 * @param danish The danish the AI is playing in.
	 */
	public PlayerAI(Danish danish) {
		super("AI");
		this.danish = danish;
	}

	/**
	 * plays a turn of the AI.
	 * It will play the least powerful cards he can.
	 */
	public void play() {
		List<Card> cards = new ArrayList<>();

		for (Card c : hand) {
			if (c.getRank().placeable(danish.getRankStack())) {
				if (cards.isEmpty() || cards.get(0).compareTo(c) == 0) {
					cards.add(c);
				} else if (cards.get(0).compareTo(c) > 0) {
					cards.clear();
					cards.add(c);
				}
			}
		}

		if (cards.get(0).getRank() == Rank.ACE || cards.get(0).getRank() == Rank.THREE && danish.getRankStack() == Rank.ACE) {
			Player player = null;
			Player oPlayer;
			int i;
			int iPlayer = 0;

			for (i = 0; i < danish.getPlayers().size(); ++i) {
				oPlayer = danish.getPlayers().get(i);
				iPlayer = i;
				if (player == null) {
					player = oPlayer;
				} else if (oPlayer.getHidden().size() < player.getHidden().size()
						|| oPlayer.getVisible().size() < player.getVisible().size()
						|| oPlayer.getHand().size() < player.getHand().size()) {
					player = oPlayer;
				}
			}

			danish.turn(cards, iPlayer);
		} else {
			danish.turn(cards);
		}
	}
}
