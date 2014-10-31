package danish.view;

import javax.swing.JPanel;
import danish.model.CardPack;
import danish.model.Card;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.Point;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardPackBean extends JPanel{
	private CardPack pack;
	private boolean hidden;
	private int nbCard;
	private OverlapLayout layout;

	public CardPackBean(){
		pack = new CardPack();
		hidden = false;
		nbCard = 1;
		layout = new OverlapLayout( new Point(25, 0) );
		layout.setPopupInsets(new Insets(20, 0, 0, 0));
		setLayout( layout );
	}

	public CardPack getPack(){
		return pack;
	}

	public void setPack( CardPack pack ){
		this.pack = pack;
		this.refresh();
	}

	public boolean isHidden(){
		return hidden;
	}

	public void setHidden( boolean hidden ){
		this.hidden = hidden;
		this.refresh();
	}

	public int getNbCard(){
		return nbCard;
	}

	public void setNbCard( int nbCard ){
		if( nbCard > 0 ){
			this.nbCard = nbCard;
		}
		this.refresh();
	}
	
	public void refresh(){
		removeAll();
		
		int i = nbCard;
		
		for( Card c : pack ){
			if( i-- <= 0 ){
				break;
			}
			
			CardBean cardBean = new CardBean();
			cardBean.setCard(c);
			cardBean.setHidden( hidden );
			add(cardBean);
		}
	}
	
	@Override
	protected void paintComponent( Graphics g ){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g ;
		
		/*add( new JLabel( "test" ));*/
	}
}
