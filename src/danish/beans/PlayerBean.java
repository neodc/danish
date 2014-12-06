package danish.beans;

import danish.model.CardDanish;
import javax.swing.JPanel;
import danish.model.Player;
import java.awt.Font;
import java.util.Collections;
import java.util.List;
import javax.swing.JLabel;

/**
 *
 * @author Noé, Julien, Loup.
 */
public abstract class PlayerBean extends JPanel {

	/**
	 * The player used for the bean.
	 */
	protected Player player = null;
	/**
	 * The name of the player.
	 */
	protected JLabel name;
	/**
	 * The bean used for the player's hidden cards.
	 */
	protected CardCollectionBean hidden;
	/**
	 * The bean used for the player's hand.
	 */
	protected CardCollectionBean hand;
	/**
	 * The bean used for the player's visible cards.
	 */
	protected CardCollectionBean visible;
	/**
	 * Indicates if the sort of the hand is reversed.
	 */
	private boolean reverse;

	/**
	 * PlayerBean constructor without parameter.
	 */
	public PlayerBean() {
		this.name = new JLabel();
		this.hidden = new CardCollectionBean();
		this.hand = new CardCollectionBean();
		this.visible = new CardCollectionBean();
		this.reverse = true;
	}

	/**
	 * Returns the player.
	 *
	 * @return The player.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Sets a player for the bean to be displayed.
	 *
	 * @param player The player the bean will display.
	 */
	public void setPlayer(Player player) {
		if (player == null) {
			throw new NullPointerException();
		}
		this.player = player;
		refresh();
	}

	/**
	 * Returns the bean displaying the player's hidden cards.
	 *
	 * @return The bean displaying the player's hidden cards.
	 */
	public CardCollectionBean getHidden() {
		return hidden;
	}

	/**
	 * Returns the bean displaying the player's hand.
	 *
	 * @return The bean displaying the player's hand.
	 */
	public CardCollectionBean getHand() {
		return hand;
	}

	/**
	 * Returns the bean displaying the player's visible cards.
	 *
	 * @return The bean displaying the player's visible cards.
	 */
	public CardCollectionBean getVisible() {
		return visible;
	}

	/**
	 * Sets the type of text of the player's name depending on if he's playing.
	 *
	 * @param current If this is the player's turn.
	 */
	public void setCurrent(boolean current) {
		if (current) {
			this.name.setFont(this.name.getFont().deriveFont(Font.BOLD));
		} else {
			this.name.setFont(this.name.getFont().deriveFont(Font.PLAIN));
		}
	}

	/**
	 * Refreshes the bean.
	 */
	public void refresh() {
		if (player == null) {
			return;
		}

		this.name.setText(this.player.getName());
		this.hidden.setPack(this.player.getHidden());

		List<CardDanish> h = this.player.getHand();
		Collections.sort(h);
		if (reverse) {
			Collections.reverse(h);
		}
		this.hand.setPack(h);

		this.visible.setPack(this.player.getVisible());
	}

	public void setReverse(boolean reverse) {
		this.reverse = reverse;
	}

}
