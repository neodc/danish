package danish.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.LineBorder;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class OpponentBean extends PlayerBean{
	
	private JLabel name;
	private CardCollectionBean hidden;
	private CardCollectionBean hand;
	private CardCollectionBean visible;
	private JButton attack;

	public OpponentBean(){
		this.name = new JLabel();
		this.hidden = new CardCollectionBean();
		this.hand = new CardCollectionBean();
		this.visible = new CardCollectionBean();
		this.attack = new JButton("Attack");
		
		this.attack.setBorder( new LineBorder(Color.black));
		this.hidden.setBorder( new LineBorder(Color.black));
		this.hand.setBorder( new LineBorder(Color.black));
		this.visible.setBorder( new LineBorder(Color.black));
		this.name.setBorder( new LineBorder(Color.black));
		this.setBorder( new LineBorder(Color.black));
		
		this.hidden.setNbCard(1);
		this.hidden.setHidden(true);
		this.hidden.setShowSize(true);
		
		this.hand.setNbCard(3);
		this.hand.setHidden(true);
		this.hand.setShowSize(true);
		
		this.visible.setNbCard(3);
		
		add(this.name);
		add(this.hidden);
		add(this.hand);
		add(this.visible);
		add(this.attack);
		
		/* But :
			name
			h => hidden | hand
			visible
			attack
		*/
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		gridbag.layoutContainer(this);
		
		setLayout(gridbag);
		
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridx = 0;
		
		c.gridy = 0;
		gridbag.setConstraints( this.name, c);
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 2;
		gridbag.setConstraints( this.visible, c);
		
		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 3;
		gridbag.setConstraints( this.attack, c);
		
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		
		gridbag.setConstraints( this.hidden, c);
		
		c.gridx = 1;
		gridbag.setConstraints( this.hand, c);
	}
	
	@Override
	public void refresh(){
		if( player == null ){
			return;
		}
		
		this.name.setText( this.player.getName() );
		this.hidden.setPack( this.player.getHidden() );
		this.hand.setPack( this.player.getHand());
		this.visible.setPack( this.player.getVisible());
	}
	
}
