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

	public static Image get(Card c) {
		return IMAGES.get(c);
	}

	public static Image getBack() {
		return BACK;
	}

	public static Image getBlank() {
		return BLANK;
	}

	public static int getHeight() {
		return HEIGHT;
	}

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
