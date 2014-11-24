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

	public enum Style {
		
		PONY("Pony V2", "ponyV2"),
		PONYOLD("Pony", "pony"),
		OLD("Classic", "old");

		private final String name;
		private final String directory;

		private Style(String name, String directory) {
			this.name = name;
			this.directory = directory;
		}

		public String getName() {
			return name;
		}
		
		public String getDirectory() {
			return directory;
		}

		/**
		 * A textual representation of the suit.
		 *
		 * @return The textual representation of the suit.
		 */
		@Override
		public String toString() {
			return name;
		}
	}
	
	private final static Map<Card, Image> images = new HashMap<>();
	private static Image back;
	private static Image blank;
	private static int height;
	private static int width;
	private static Style current;

	/**
	 * Returns the image of a card.
	 *
	 * @param c The card whose image is needed.
	 * @return The image of the card.
	 */
	public static Image get(Card c) {
		return images.get(c);
	}

	/**
	 * Returns the image of the back of a card.
	 *
	 * @return The image of the back of a card.
	 */
	public static Image getBack() {
		return back;
	}

	/**
	 * Returns the image of a neutral/blank card.
	 *
	 * @return The image of a neutral/blank card.
	 */
	public static Image getBlank() {
		return blank;
	}

	/**
	 * Returns the height of the images.
	 *
	 * @return The height of the images.
	 */
	public static int getHeight() {
		return height;
	}

	/**
	 * Returns the width of the images.
	 *
	 * @return The width of the images.
	 */
	public static int getWidth() {
		return width;
	}
	
	public static void load( Style style ){
		if( current != style ){
			current = style;

			images.clear();

			for (Suit s : Suit.values()) {
				for (Rank r : Rank.values()) {
					images.put(
						new Card(r, s),
						(new ImageIcon(Images.class.getResource(style.getDirectory()+ "/" + s.getDisplay() + r.getValue() + ".png"))).getImage()
					);
				}
			}

			back = (new ImageIcon(Images.class.getResource(style.getDirectory() + "/Back.png"))).getImage();
			blank = (new ImageIcon(Images.class.getResource(style.getDirectory() + "/Blank.png"))).getImage();

			height = back.getHeight(null);
			width = back.getWidth(null);
		}
	}
	
	public static Style getCurrent(){
		return current;
	}

	static {
		load(Style.PONY);
	}

}
