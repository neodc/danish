package danish.view;
import danish.model.DanishModel;
import danish.model.DanishView;
import danish.model.Player;
import danish.model.PlayerAI;
import danish.model.Card;
import danish.model.CardDanish;
import danish.model.Rank;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class DanishUI extends JComponent implements DanishView{
	
	private OpponentBean[] opponent;
	private CurrentPlayerBean current;
	private CardCollectionBean deck;
	private CardCollectionBean stack;
	private CardCollectionBean graveyard;
	private DanishModel danish;
	
	private List<CardBean> selectedCards;
	
	public DanishUI(DanishModel danish) {
		this.danish = danish;
		
		selectedCards = new ArrayList<>();
		
		initComponent();
		
		setPlayers( 3, getRandomName());
		
		this.stack.addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked( MouseEvent me ){
				if( me.getComponent() instanceof CardBean ){
					take();
				}
			}
			
		});
		
		this.current.getHand().addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked( MouseEvent me ){
				Component tmp = me.getComponent();
				if( tmp instanceof CardBean ){
					toggleCardSelection((CardBean)tmp);
				}
			}
			
		});
		
		this.current.getVisible().addMouseListener( new MouseAdapter() {

			@Override
			public void mouseClicked( MouseEvent me ){
				Component tmp = me.getComponent();
				if( tmp instanceof CardBean ){
					switchCard((CardBean)tmp);
				}
			}
			
		});
		
		this.current.addActionListener( new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent ae ){
				clickPlay();
			}
		});
	}

	@Override
	public void refresh(){
		refreshButtons();
		
		this.current.getHand().setEnabled( !this.danish.isPlaying() || !(this.danish.getPlaying() instanceof PlayerAI) );
		
		int i = 0;
		
		for( Player p : this.danish.getPlayers() ){
			if( p instanceof PlayerAI ){ // TODO opti
				this.opponent[i].setPlayer(p);
				this.opponent[i++].setCurrent( this.danish.getPlaying().equals(p) );
			}else{
				this.current.setPlayer(p);
				this.current.setCurrent( this.danish.getPlaying().equals(p) );
			}
		}
		
		this.deck.setPack( this.danish.getDeck() );
		this.graveyard.setPack( this.danish.getGraveyard());
		this.stack.setPack( this.danish.getStack());
		
		this.revalidate();
		
		SwingUtilities.invokeLater( new Runnable() {
			@Override
			public void run(){
				if( danish.isPlaying() && (danish.getPlaying() instanceof PlayerAI) ){
					try{
						Thread.sleep(2000);
					}catch( InterruptedException ex ){}
					((PlayerAI)danish.getPlaying()).play();
				}
			}
		});
	}
	
	@Override
	public void removeNotify() {
		super.removeNotify();
		if( this.danish != null ){
			danish.removeDanishListener(this);
			this.danish = null;
		}
	}

	private void initComponent(){
		this.opponent = new OpponentBean[3];
		this.opponent[0] = new OpponentBean();
		this.opponent[1] = new OpponentBean();
		this.opponent[2] = new OpponentBean();
		this.current = new CurrentPlayerBean();
		this.deck = new CardCollectionBean();
		this.stack = new CardCollectionBean();
		this.graveyard = new CardCollectionBean();
		
		this.deck.setHidden(true);
		this.deck.setShowSize(true);
		
		this.stack.setOverlap(new Point(20, 0));
		this.stack.setNbCard(4);
		
		this.graveyard.setShowSize(true);
		
		/*
		this.opponent[0].setBorder( new LineBorder(Color.black, 5));
		this.opponent[1].setBorder( new LineBorder(Color.black, 5));
		this.opponent[2].setBorder( new LineBorder(Color.black, 5));
		this.current.setBorder( new LineBorder(Color.black, 5));
		this.deck.setBorder( new LineBorder(Color.black, 5));
		this.stack.setBorder( new LineBorder(Color.black, 5));
		this.graveyard.setBorder( new LineBorder(Color.black, 5));
		*/
		add(opponent[0]);
		add(opponent[1]);
		add(opponent[2]);
		add(current);
		add(deck);
		add(stack);
		add(graveyard);
		

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(gridbag);
		
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		gridbag.setConstraints( this.opponent[0], c);
		
		c.gridx = 1;
		gridbag.setConstraints( this.opponent[1], c);
		
		c.gridx = 2;
		gridbag.setConstraints( this.opponent[2], c);
		
		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		gridbag.setConstraints( this.deck, c);
		
		c.gridx = 1;
		c.gridwidth = 1;
		gridbag.setConstraints( this.stack, c);
		
		c.gridx = 2;
		c.gridwidth = 1;
		gridbag.setConstraints( this.graveyard, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		gridbag.setConstraints( this.current, c);
	}
	
	private void setPlayers( int nbIA, String playerName){
		if( nbIA < 4 && nbIA > 0 ){
			ArrayList<String> players = new ArrayList<>();
			
			players.add(playerName);
			for( int i = 0; i < nbIA ; ++i ){
				players.add(null);
			}
			
			this.danish.setPlayers(players);
		}
	}
	
	private String getRandomName(){
		ArrayList<String> names = new ArrayList<>();
		
		names.add("Player");
		names.add("Default");
		
		Collections.shuffle( names );
		
		return names.get(0);
	}
	
	private void toggleCardSelection( CardBean card ){
		if( this.selectedCards.contains(card) ){
			this.selectedCards.remove(card);
		}else if( !this.danish.isPlaying() ){
			
			unselectCards();
			
			this.selectedCards.add(card);
			
		}else if( canBeSelected(card.getCard()) ){
			this.selectedCards.add( card );
		}else{
			return;
		}
		
		refreshButtons();
		
		this.current.getHand().togglePopup(card);
	}
	
	private void unselectCards(){
		for( CardBean c : this.selectedCards ){
			this.current.getHand().popup(c, false);
		}
		this.selectedCards.clear();
		refreshButtons();
	}
	
	private boolean canBeSelected(Card c){
		return this.selectedCards.isEmpty()  || this.selectedCards.get(0).getCard().getRank() == c.getRank();
	}
	
	private void refreshButtons(){
		if( this.danish.isPlaying() ){
			this.current.setButtonText("Play");
			
			this.current.DisableButton( this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() == Rank.ACE );
		}else{
			this.current.setButtonText("Lock");
		}
		
		for( OpponentBean o : this.opponent ){
			o.DisableButton( !this.danish.isPlaying() || this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() != Rank.ACE );
		}
	}
	
	private void switchCard( CardBean card ){
		if( this.selectedCards.size() == 1 ){
			this.danish.switchCard(this.current.getPlayer(), (CardDanish)card.getCard(), (CardDanish)this.selectedCards.get(0).getCard());
			this.selectedCards.clear();
		}
	}
	
	private void clickPlay(){
		
		if( this.danish.isPlaying() ){
			
			ArrayList<CardDanish> tmp = new ArrayList<>();
			
			for( CardBean c : this.selectedCards ){
				tmp.add( (CardDanish)(c.getCard()) );
			}
			
			this.danish.turn( tmp );
			
		}else{
			this.danish.begin();
		}
		
		unselectCards();
	}
	
	private void take(){
		if( this.danish.isPlaying() && !(this.danish.getPlaying() instanceof PlayerAI) ){
			this.danish.turn( new ArrayList<CardDanish>() );
		}
	}
}
