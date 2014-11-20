package danish.view;

import javax.swing.JPanel;
import danish.model.CardPack;
import danish.model.Card;
import danish.model.CardDanish;
import danish.view.img.Images;
import java.awt.Color;
import java.awt.Font;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

/**
 * A bean containing a list of CardBean and allowing them to be displayed
 * overlapping.
 *
 * @author Noé, Julien, Loup.
 */
public class CardCollectionBean extends JPanel{

	private Collection<CardDanish> pack;
	private boolean hidden;
	private boolean showSize;
	private int nbCard;
	private int nbCardMin;
	private final OverlapLayout layoutCard;
	private final JPanel jPanelCard;
	private final JLabel jLabelSize;
	private final MouseAdapter listener;

	/**
	 * CardCollectionBean constructor without parameter.
	 */
	public CardCollectionBean() {
		pack = new CardPack();
		hidden = false;
		showSize = false;
		nbCard = 1;
		nbCardMin = 0;
		jLabelSize = new JLabel();

		this.listener = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				dispatch(me);
			}
		};

		jLabelSize.setHorizontalAlignment(SwingConstants.CENTER);

		setLayout(new OverlapLayout());

		layoutCard = new OverlapLayout(new Point(25, 0));
		jPanelCard = new JPanel(layoutCard);

		add(jPanelCard);
	}

	/**
	 * Tells if the bean shows how many cards are in the collection.
	 *
	 * @return If the bean shows how many cards are in the collection.
	 */
	public boolean isShowSize() {
		return showSize;
	}

	/**
	 * Sets if the bean shows how many cards are in the collection.
	 *
	 * @param showSize if the bean shows how many cards are in the collection.
	 */
	public void setShowSize(boolean showSize) {
		this.showSize = showSize;
		this.refresh();
	}

	/**
	 * Returns the pack containing the cards of the collection.
	 *
	 * @return the pack containing the cards of the collection.
	 */
	public Collection<CardDanish> getPack() {
		return pack;
	}

	/**
	 * Sets the pack of cards of the bean.
	 *
	 * @param pack the new cards of the bean.
	 */
	public void setPack(Collection<CardDanish> pack) {
		if (pack == null) {
			throw new NullPointerException();
		}
		this.pack = pack;
		this.refresh();
	}

	/**
	 * Tells if the cards are hidden.
	 *
	 * @return if the cards are hidden.
	 */
	public boolean isHidden() {
		return hidden;
	}

	/**
	 * Sets if the cards must be hidden.
	 *
	 * @param hidden if the cards must be hidden.
	 */
	public void setHidden(boolean hidden) {
		this.hidden = hidden;
		this.refresh();
	}

	/**
	 * Tells how many cards can be displayed.
	 *
	 * @return how many cards can be displayed.
	 */
	public int getNbCard() {
		return nbCard;
	}

	/**
	 * Tells how many cards must be displayed.
	 *
	 * @return how many cards must be displayed.
	 */
	public int getNbCardMin() {
		return nbCardMin;
	}

	/**
	 * Sets how many cards can be displayed.
	 *
	 * @param nbCard how many cards can be displayed.
	 */
	public void setNbCard(int nbCard) {
		if (nbCard > 0) {
			this.nbCard = nbCard;
		}
		this.refresh();
	}

	/**
	 * Sets how many cards must be displayed.
	 *
	 * @param nbCardMin how many cards must be displayed.
	 */
	public void setNbCardMin(int nbCardMin) {
		if (nbCardMin >= 0) {
			this.nbCardMin = nbCardMin;
		}
		this.refresh();
	}

	/**
	 * Sets the percentage of the card that will not be overlapped.
	 *
	 * @param p A point with two percentage. The first one is the horizontal
	 * shift; the second one, the vertical shift. A positive percentage shifts
	 * to the right and downwards. A negative shifts to the left and upwards.
	 */
	public void setOverlap(Point p) {
		if (p == null) {
			throw new NullPointerException();
		}

		this.layoutCard.setOverlapPosition(p);
	}

	/**
	 * Sets the popup size of a card.
	 *
	 * @param i the popup size of a card.
	 */
	public void setPopup(Insets i) {
		if (i == null) {
			throw new NullPointerException();
		}

		this.layoutCard.setPopupInsets(i);
	}

	/**
	 * Sets the vertical alignment of the cards.
	 *
	 * @param f 0 for up, 1 for down.
	 */
	public void setLayoutAlignmentY(float f) {
		layoutCard.setLayoutAlignmentY(f);
	}

	/**
	 * Sets the horizontal alignment of the cards.
	 *
	 * @param f 0 for up, 1 for down.
	 */
	public void setLayoutAlignmentX(float f) {
		layoutCard.setLayoutAlignmentX(f);
	}

	/**
	 * Refreshes the card collection.
	 */
	public void refresh() {
		jPanelCard.removeAll();

		int i = nbCard;

		ArrayList<CardBean> tmp = new ArrayList<>();

		for (Card c : pack) {
			if (i-- <= 0) {
				break;
			}

			CardBean cardBean = new CardBean();
			cardBean.setCard(c);
			cardBean.setHidden(hidden);
			cardBean.addMouseListener(this.listener);

			tmp.add(0, cardBean);
		}

		int nbBlank = this.nbCardMin - Math.min(pack.size(), this.nbCard);

		for (i = 0; i < nbBlank; ++i) {
			CardBean cardBean = new CardBean();
			cardBean.addMouseListener(this.listener);
			tmp.add(0, cardBean);
		}

		for (CardBean c : tmp) {
			jPanelCard.add(c);
		}

		jLabelSize.setText("" + pack.size());

		Font labelFont = jLabelSize.getFont();
		String labelText = "00";

		int stringWidth = jLabelSize.getFontMetrics(labelFont).stringWidth(labelText);
		int componentWidth = (int) (jLabelSize.getWidth() * 0.9);

		// Find out how much the font can grow in width.
		double widthRatio = (double) componentWidth / (double) stringWidth;

		int newFontSize = (int) (labelFont.getSize() * widthRatio);
		int componentHeight = (int) (jLabelSize.getHeight() * 0.9);

		// Pick a new font size so it will not be larger than the height of label.
		int fontSizeToUse = Math.min(newFontSize, componentHeight);

		if (fontSizeToUse == 0) {
			fontSizeToUse = 60;
		}

		// Set the label's font size to the newly determined size.
		jLabelSize.setFont(jLabelSize.getFont().deriveFont((float) fontSizeToUse));

		if (showSize && pack.size() > this.nbCard) {
			jPanelCard.add(jLabelSize);

			OverlapConstraints c = new OverlapConstraints();
			c.overlap = false;
			layoutCard.addLayoutComponent(jLabelSize, c);
		}

		revalidate();
		repaint();
	}

	/**
	 * Makes a card of the collection pop or depop.
	 *
	 * @param card The card to pop.
	 * @param pop If the card must be popped.
	 */
	public void popup(CardBean card, boolean pop) {
		OverlapConstraints constraints = layoutCard.getConstraints(card);
		if (constraints == null) {
			constraints = new OverlapConstraints();
		}
		constraints.popup = pop;
		layoutCard.addLayoutComponent(card, constraints);
		card.revalidate();
	}

	/**
	 * Toggles the pop status of a card.
	 *
	 * @param card The card whose pop status is toggled.
	 * @return The new pop status.
	 */
	public boolean togglePopup(CardBean card) {
		OverlapConstraints constraints = layoutCard.getConstraints(card);
		if (constraints == null) {
			constraints = new OverlapConstraints();
		}

		this.popup(card, !constraints.popup);

		return !constraints.popup;
	}

	private void dispatch(MouseEvent me) {
		if (this.isEnabled()) {
			for (MouseListener m : this.getMouseListeners()) {
				m.mouseClicked(me);
			}
		}
	}
}
