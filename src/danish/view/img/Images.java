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
public class Images{

	private static final Map<Card, Image> images = new HashMap<>();
	private static final Image back = (new ImageIcon(Images.class.getResource( "Back.png") )).getImage();
	
	public static Image get( Card c ){
		return images.get(c);
	}
	
	public static Image getBack(){
		return back;
	}

	static{
		
		for( Suit s : Suit.values() ){
			for( Rank r : Rank.values() ){
				
				images.put(
						new Card(r, s),
						(new ImageIcon(Images.class.getResource( s.getDisplay()+r.getValue()+".png") )).getImage()
				);
			}
		}
	}

}
