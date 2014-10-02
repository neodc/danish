package danish.business;

import java.util.ArrayList;
import java.util.List;

/**
 * The class representing a non-human Player.
 * @author Noé, Julien, Loup.
 */
public class PlayerAI extends Player{
	
	private final Danish danish;
        
        /**
         * Player constructor with one parameter.
         * @param danish The danish where the AI is playing.
         */
	public PlayerAI(Danish danish) {
		super("AI");
		this.danish = danish;
	}
	
        /**
         * Play a turn of the AI.
         */
	public void play(){
		List<Card> cards = new ArrayList<>();
		
		for (Card c : hand){
			if (c.getRank().placeable(danish.getRankStack())){
				if (cards.isEmpty() || cards.get(0).compareTo(c) == 0){
					cards.add(c);
				} else if (cards.get(0).compareTo(c) > 0){
					cards.clear();
					cards.add(c);
				}
			}
		}
		
		if (cards.get(0).getRank() == Rank.ACE){
			Player player = null;
			Player oPlayer;
			int i;
			int iPlayer = 0;
			
			for (i = 0; i < danish.getPlayers().size(); ++i){
				oPlayer = danish.getPlayers().get(i);
				iPlayer = i;
				if (player == null){
					player = oPlayer;
				} else if (	oPlayer.getHidden().size() < player.getHidden().size()
							|| oPlayer.getVisible().size() < player.getVisible().size()
							|| oPlayer.getHand().size() < player.getHand().size()	){
						player = oPlayer;
				}
			}
			
			danish.turn(cards, iPlayer);	
		} else {
			danish.turn(cards);
		}
	}
}
