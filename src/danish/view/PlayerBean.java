package danish.view;

import danish.model.CardDanish;
import javax.swing.JPanel;
import danish.model.Player;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Noé, Julien, Loup.
 */
public abstract class PlayerBean extends JPanel {

	protected Player player = null;
	protected JLabel name;
	protected CardCollectionBean hidden;
	protected CardCollectionBean hand;
	protected CardCollectionBean visible;

	public PlayerBean() {
		this.name = new JLabel();
		this.hidden = new CardCollectionBean();
		this.hand = new CardCollectionBean();
		this.visible = new CardCollectionBean();
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		if (player == null) {
			throw new NullPointerException();
		}
		this.player = player;
		refresh();
	}

	CardCollectionBean getHidden() {
		return hidden;
	}

	CardCollectionBean getHand() {
		return hand;
	}

	CardCollectionBean getVisible() {
		return visible;
	}

	public void refresh() {
		if (player == null) {
			return;
		}

		this.name.setText(this.player.getName());
		this.hidden.setPack(this.player.getHidden());
		
		List<CardDanish> h = this.player.getHand();
		Collections.sort(h);
		this.hand.setPack(h);
		
		this.visible.setPack(this.player.getVisible());
	}
}
