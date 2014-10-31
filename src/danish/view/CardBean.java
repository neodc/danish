package danish.view;

import javax.swing.JPanel;
import danish.model.Card;
import danish.model.Rank;
import danish.model.Suit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import danish.view.img.Images;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardBean extends JPanel{
	private Card card;
	private boolean hidden;

	public CardBean(){
		this.card = new Card(Rank.TWO, Suit.CLUB );
		this.hidden = false;
	}

	public Card getCard(){
		return card;
	}

	public void setCard( Card card ){
		this.card = card;
	}

	public boolean isHidden(){
		return hidden;
	}

	public void setHidden( boolean hidden ){
		this.hidden = hidden;
	}

	@Override
	protected void paintComponent( Graphics g ){
		super.paintComponent( g );
		Graphics2D g2 = (Graphics2D) g ;
		
		Image i;
		
		int w = getWidth();
		int h = getHeight();
		
		if( (1.0 * getHeight() / getWidth()) > ( 1.0 * Images.getHeight() / Images.getWidth() ) ){
			h = ( Images.getHeight()* getWidth())/Images.getWidth();
		}else{
			w = ( Images.getWidth() * getHeight() )/Images.getHeight();
		}
		
		if( this.hidden ){
			i = Images.getBack();
		}else{
			i = Images.get( card );
		}
		g2.drawImage( i, 0, 0, w, h, this);
	}
	
	
}
