package danish.view;
import danish.model.DanishModel;
import danish.model.DanishView;
import danish.model.Player;
import danish.model.PlayerAI;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JComponent;
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
	
	public DanishUI(DanishModel danish) {
		this.danish = danish;
		
		initComponent();
		
		setPlayers( 3, getRandomName());
	}

	@Override
	public void refresh(){
		
		int i = 0;
		
		for( Player p : this.danish.getPlayers() ){
			if( p instanceof PlayerAI ){
				this.opponent[i++].setPlayer(p);
			}else{
				this.current.setPlayer(p);
			}
		}
		
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
		opponent = new OpponentBean[3];
		opponent[0] = new OpponentBean();
		opponent[1] = new OpponentBean();
		opponent[2] = new OpponentBean();
		current = new CurrentPlayerBean();
		deck = new CardCollectionBean();
		stack = new CardCollectionBean();
		graveyard = new CardCollectionBean();
		
		this.opponent[0].setBorder( new LineBorder(Color.black));
		this.opponent[1].setBorder( new LineBorder(Color.black));
		this.opponent[2].setBorder( new LineBorder(Color.black));
		this.current.setBorder( new LineBorder(Color.black));
		this.deck.setBorder( new LineBorder(Color.black));
		this.stack.setBorder( new LineBorder(Color.black));
		this.graveyard.setBorder( new LineBorder(Color.black));
		
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
		c.gridwidth = 2;
		gridbag.setConstraints( this.opponent[0], c);
		
		c.gridx = 2;
		gridbag.setConstraints( this.opponent[1], c);
		
		c.gridx = 4;
		gridbag.setConstraints( this.opponent[2], c);
		
		c.gridy = 1;
		c.gridx = 1;
		c.gridwidth = 1;
		gridbag.setConstraints( this.deck, c);
		
		c.gridx = 2;
		c.gridwidth = 2;
		gridbag.setConstraints( this.stack, c);
		
		c.gridx = 4;
		c.gridwidth = 1;
		gridbag.setConstraints( this.graveyard, c);
		
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 6;
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
		
		names.add( "Player");
		names.add( "Default");
		
		Collections.shuffle( names );
		
		return names.get(0);
	}
}
