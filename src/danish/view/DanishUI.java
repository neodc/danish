package danish.view;
import danish.model.Danish;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.border.LineBorder;

/**
 *
 * @author Julien
 */
public class DanishUI extends javax.swing.JFrame /*implements Observer*/{
	
	private OpponentBean opponent1;
	private OpponentBean opponent2;
	private OpponentBean opponent3;
	private CurrentPlayerBean current;
	private CardCollectionBean deck;
	private CardCollectionBean stack;
	private CardCollectionBean graveyard;
	private Danish danish = null;
	
	public DanishUI(Danish danish) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Danish");
		this.danish = danish;
		initComponent();
		
		
		this.opponent1.setBorder( new LineBorder(Color.black));
		this.opponent2.setBorder( new LineBorder(Color.black));
		this.opponent3.setBorder( new LineBorder(Color.black));
		this.current.setBorder( new LineBorder(Color.black));
		this.deck.setBorder( new LineBorder(Color.black));
		this.stack.setBorder( new LineBorder(Color.black));
		this.graveyard.setBorder( new LineBorder(Color.black));
		//this.setBorder( new LineBorder(Color.black));
		
		add(opponent1);
		add(opponent2);
		add(opponent3);
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
		gridbag.setConstraints( this.opponent1, c);
		
		c.gridx = 2;
		gridbag.setConstraints( this.opponent2, c);
		
		c.gridx = 4;
		gridbag.setConstraints( this.opponent3, c);
		
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
		
		/*current.setPlayer(danish.getPlaying());
		current.refresh();*/
		
		pack();
	}

	/*@Override
	public void update( Observable o, Object o1 ){
		if (o == danish){
		}
	}*/

	private void initComponent(){
		opponent1 = new OpponentBean();
		opponent2 = new OpponentBean();
		opponent3 = new OpponentBean();
		current = new CurrentPlayerBean();
		deck = new CardCollectionBean();
		stack = new CardCollectionBean();
		graveyard = new CardCollectionBean();
	}
}
