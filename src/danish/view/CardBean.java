package danish.view;

import javax.swing.JPanel;
import danish.model.Card;
import danish.model.Rank;
import danish.model.Suit;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import danish.view.img.Images;
import java.awt.Dimension;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardBean extends JPanel{
	private Card card;
	private boolean hidden;

	public CardBean(){
//		this.card = new Card(Rank.TWO, Suit.CLUB );
		this.card = null;
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
		
		if( this.card == null ){
			i = Images.getBlank();
		}else if( this.hidden ){
			i = Images.getBack();
		}else{
			i = Images.get( card );
		}
		g2.drawImage( i, 0, 0, getWidth(), getHeight(), this);
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(Images.getWidth(), Images.getHeight());
	}

	@Override
	public void setSize( Dimension d ){
		this.setSize( d.width, d.height );
	}

	@Override
	public void setSize( int w, int h ){
		if( (1.0 * h / w) > ( 1.0 * Images.getHeight() / Images.getWidth() ) ){
			h = ( Images.getHeight()* w)/Images.getWidth();
		}else{
			w = ( Images.getWidth() * h )/Images.getHeight();
		}
		
		super.setSize(w, h);
	}
}
