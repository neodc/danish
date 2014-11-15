/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package danish.view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class CurrentPlayerBean extends PlayerBean{
	
	private JLabel name;
	private CardCollectionBean hidden;
	private CardCollectionBean hand;
	private CardCollectionBean visible;
	private JButton play;

	public CurrentPlayerBean(){
		this.name = new JLabel();
		this.hidden = new CardCollectionBean();
		this.hand = new CardCollectionBean();
		this.visible = new CardCollectionBean();
		this.play = new JButton("Play");
		
		this.name.setHorizontalAlignment( JLabel.CENTER );
		
		this.hidden.setNbCard(3);
		this.hidden.setNbCardMin(3);
		this.hidden.setHidden(true);
		this.hidden.setShowSize(true);
		this.hidden.setOverlap(new Point(110, 0));
		
		this.hand.setNbCard(Integer.MAX_VALUE);
		this.hand.setNbCardMin(1);
		this.hand.setPopup( new Insets(20, 0, 0, 0));
		
		this.visible.setNbCard(3);
		this.visible.setNbCardMin(3);
		this.visible.setOverlap(new Point(110, 0));
		
		add(this.hidden);
		add(this.name);
		add(this.play);
		add(this.visible);
		add(this.hand);
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		setLayout(gridbag);		
		
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		
		c.gridy = 1;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridheight = 1;
		
		gridbag.setConstraints(this.hidden, c);
		
		c.gridy = 2;
		c.gridx = 0;
		c.gridheight = 1;
		
		gridbag.setConstraints(this.visible, c);
		
		c.weighty = 0;
		
		c.gridy = 0;
		c.gridx = 1;
		
		gridbag.setConstraints( this.play, c);
		
		c.gridy = 0;
		c.gridx = 2;
		
		gridbag.setConstraints( this.name, c);
		
		c.weighty = 1;
		c.weightx = 1;
		
		c.gridwidth = 2;
		c.gridheight = 2;
		
		c.gridy = 1;
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
	
	public void setCurrent(boolean current){
		if( current ){
			this.name.setFont( this.name.getFont().deriveFont( Font.BOLD ));
		}else{
			this.name.setFont( this.name.getFont().deriveFont( Font.PLAIN ));
		}
	}

	CardCollectionBean getHidden(){
		return hidden;
	}

	CardCollectionBean getHand(){
		return hand;
	}

	CardCollectionBean getVisible(){
		return visible;
	}
	
	void setButtonText(String s){
		this.play.setText(s);
	}
	
	void DisableButton(boolean b){
		this.play.setEnabled(!b);
	}
	
	void addActionListener( ActionListener a ){
		this.play.addActionListener(a);
	}
}
