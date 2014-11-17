package danish.view.img;

import danish.model.Card;
import java.awt.Image;
import java.util.HashMap;
import java.util.Map;

import danish.model.Suit;
import danish.model.Rank;
import javax.swing.ImageIcon;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Images {

	private static final Map<Card, Image> IMAGES = new HashMap<>();
	private static final Image BACK = (new ImageIcon(Images.class.getResource("Back.png"))).getImage();
	private static final Image BLANK = (new ImageIcon(Images.class.getResource("Blank.png"))).getImage();
	private static final int HEIGHT = BACK.getHeight(null);
	private static final int WIDTH = BACK.getWidth(null);

	/**
	 * Returns the image of a card.
	 *
	 * @param c The card whose image is needed.
	 * @return The image of the card.
	 */
	public static Image get(Card c) {
		return IMAGES.get(c);
	}

	/**
	 * Returns the image of the back of a card.
	 *
	 * @return The image of the back of a card.
	 */
	public static Image getBack() {
		return BACK;
	}

	/**
	 * Returns the image of a neutral/blank card.
	 *
	 * @return The image of a neutral/blank card.
	 */
	public static Image getBlank() {
		return BLANK;
	}

	/**
	 * Returns the height of the images.
	 *
	 * @return The height of the images.
	 */
	public static int getHeight() {
		return HEIGHT;
	}

	/**
	 * Returns the width of the images.
	 *
	 * @return The width of the images.
	 */
	public static int getWidth() {
		return WIDTH;
	}

	static {

		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {

				IMAGES.put(
						new Card(r, s),
						(new ImageIcon(Images.class.getResource(s.getDisplay() + r.getValue() + ".png"))).getImage()
				);
			}
		}
	}

}
