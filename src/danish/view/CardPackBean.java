package danish.view;

import javax.swing.JPanel;
import danish.model.CardPack;
import danish.model.Card;
import java.awt.Point;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardPackBean extends JPanel{
	private CardPack pack;
	private boolean hidden;
	private boolean showSize;
	private int nbCard;
	private final OverlapLayout layout;
	private final OverlapLayout layoutCard;
	private final JPanel jPanelCard;
	private final JLabel jLabelSize;

	public CardPackBean(){
		pack = new CardPack();
		hidden = false;
		showSize = false;
		nbCard = 1;
		jLabelSize = new JLabel();
		
		jLabelSize.setHorizontalAlignment( SwingConstants.CENTER );
		jLabelSize.setFont( jLabelSize.getFont().deriveFont( 96f ) );
		
		layout = new OverlapLayout();
		setLayout( layout );
		
		layoutCard = new OverlapLayout( new Point(25, 0) );
		/*layoutCard.setPopupInsets(new Insets(20, 0, 0, 0));*/
		jPanelCard = new JPanel(layoutCard);
		
		add(jPanelCard);
		add(jLabelSize);
	}

	public boolean isShowSize(){
		return showSize;
	}

	public void setShowSize( boolean showSize ){
		this.showSize = showSize;
		this.refresh();
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
		jPanelCard.removeAll();
		
		int i = nbCard;
		
		for( Card c : pack ){
			if( i-- <= 0 ){
				break;
			}
			
			CardBean cardBean = new CardBean();
			cardBean.setCard(c);
			cardBean.setHidden( hidden );
			jPanelCard.add(cardBean);
		}
		if( showSize ){
			jLabelSize.setText( ""+pack.size() );
		}else{
			jLabelSize.setText( "" );
		}
	}
}
