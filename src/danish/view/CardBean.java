package danish.view;

import javax.swing.JPanel;
import danish.model.Card;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import danish.view.img.Images;
import java.awt.Dimension;

/**
 * A bean that only contains a card and allows to display it with an image.
 *
 * @author Noé, Julien, Loup.
 */
public class CardBean extends JPanel {

	private Card card;
	private boolean hidden;

	/**
	 * CardBean constructor without parameter.
	 */
	public CardBean() {
//		this.card = new Card(Rank.TWO, Suit.CLUB );
		this.card = null;
		this.hidden = false;
	}

	/**
	 * Returns the card of the bean.
	 *
	 * @return the card of the bean.
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Sets the card of the bean.
	 *
	 * @param card the new card of the bean.
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Returns if the card is hidden.
	 *
	 * @return if the card is hidden.
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Sets if the card must be hidden.
	 *
	 * @param hidden if the card must be hidden.
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	/**
	 * Paints the component.
	 *
	 * @param g
	 */
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;

		Image i;

		if (this.card == null) {
			i = Images.getBlank();
		} else if (this.hidden) {
			i = Images.getBack();
		} else {
			i = Images.get(card);
		}
		g2.drawImage(i, 0, 0, getWidth(), getHeight(), this);
	}

	/**
	 * Returns the preferred size of the bean.
	 *
	 * @return the preferred size of the bean.
	 */
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(Images.getWidth(), Images.getHeight());
	}

	/**
	 * Sets the dimension of the bean.
	 *
	 * @param d The new dimensions of the bean.
	 */
	@Override
	public void setSize(Dimension d) {
		this.setSize(d.width, d.height);
	}

	/**
	 * Sets the dimension of the bean.
	 *
	 * @param w The new width of the bean.
	 * @param h The new height of the bean.
	 */
	@Override
	public void setSize(int w, int h) {
		if ((1.0 * h / w) > (1.0 * Images.getHeight() / Images.getWidth())) {
			h = (Images.getHeight() * w) / Images.getWidth();
		} else {
			w = (Images.getWidth() * h) / Images.getHeight();
		}

		super.setSize(w, h);
	}
}
