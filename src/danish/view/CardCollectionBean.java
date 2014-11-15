package danish.view;

import javax.swing.JPanel;
import danish.model.CardPack;
import danish.model.Card;
import danish.model.CardDanish;
import java.awt.Component;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CardCollectionBean extends JPanel{
	private Collection<CardDanish> pack;
	private boolean hidden;
	private boolean showSize;
	private int nbCard;
	private final OverlapLayout layoutCard;
	private final JPanel jPanelCard;
	private final JLabel jLabelSize;
	private final MouseAdapter listener;

	public CardCollectionBean(){
		pack = new CardPack();
		hidden = false;
		showSize = false;
		nbCard = 1;
		jLabelSize = new JLabel();
		
		this.listener = new MouseAdapter() {
			@Override
			public void mouseClicked( MouseEvent me ){
				dispatch( me );
			}
		};
		
		jLabelSize.setHorizontalAlignment( SwingConstants.CENTER );
		jLabelSize.setFont( jLabelSize.getFont().deriveFont( 96f ) );
		
		setLayout( new OverlapLayout() );
		
		layoutCard = new OverlapLayout( new Point(25, 0) );
		/*layoutCard.setPopupInsets(new Insets(20, 0, 0, 0));*/
		jPanelCard = new JPanel(layoutCard);
		
		add(jPanelCard);
	}

	public boolean isShowSize(){
		return showSize;
	}

	public void setShowSize( boolean showSize ){
		this.showSize = showSize;
		this.refresh();
	}

	public Collection<CardDanish> getPack(){
		return pack;
	}

	public void setPack( Collection<CardDanish> pack ){
		if( pack == null ){
			throw new NullPointerException();
		}
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
	
	public void setOverlap( Point p ){
		if( p == null ){
			throw new NullPointerException();
		}
		
		this.layoutCard.setOverlapPosition(p);
	}
	
	public void setPopup( Insets i ){
		if( i == null ){
			throw new NullPointerException();
		}
		
		this.layoutCard.setPopupInsets(i);
	}
	
	public void setLayoutAlignmentY(float f){
		layoutCard.setLayoutAlignmentY(f);
	}
	
	public void setLayoutAlignmentX(float f){
		layoutCard.setLayoutAlignmentX(f);
	}
	
	public void refresh(){
		jPanelCard.removeAll();
		
		int i = nbCard;
		
		ArrayList<CardBean> tmp = new ArrayList<>();
		
		for( Card c : pack ){
			if( i-- <= 0 ){
				break;
			}
			
			CardBean cardBean = new CardBean();
			cardBean.setCard(c);
			cardBean.setHidden( hidden );
			cardBean.addMouseListener(this.listener);
			
			tmp.add(0, cardBean);
		}
		
		for( CardBean c : tmp ){
			jPanelCard.add(c);
		}
		
		if( showSize && pack.size() > this.nbCard ){
			jPanelCard.add(jLabelSize);

			OverlapConstraints c = new OverlapConstraints();
			c.overlap = false;
			layoutCard.addLayoutComponent(jLabelSize, c);
			
			jLabelSize.setText( ""+pack.size() );
		}
		
		revalidate();
		repaint();
	}
	
	public void popup(CardBean card, boolean pop){
		OverlapConstraints constraints = layoutCard.getConstraints( card );
		if( constraints == null ){
			constraints = new OverlapConstraints();
		}
		constraints.popup = pop;
		layoutCard.addLayoutComponent( card, constraints );
		card.revalidate();
	}
	
	public boolean togglePopup(CardBean card){
		OverlapConstraints constraints = layoutCard.getConstraints( card );
		if( constraints == null ){
			constraints = new OverlapConstraints();
		}
		
		this.popup( card, !constraints.popup);
		
		return !constraints.popup;
	}
	
	private void dispatch(MouseEvent me){
		if(this.isEnabled()){
			for( MouseListener m : this.getMouseListeners() ){
				m.mouseClicked( me );
			}
		}
	}
}
