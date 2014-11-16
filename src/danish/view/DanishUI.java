package danish.view;

import danish.model.DanishModel;
import danish.model.DanishView;
import danish.model.Player;
import danish.model.PlayerAI;
import danish.model.Card;
import danish.model.CardDanish;
import danish.model.Rank;
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
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class DanishUI extends JComponent implements DanishView {

	private int nbOpponent = 3;
	private boolean warningWinner = true;
	private OpponentBean[] opponent;
	private CurrentPlayerBean current;
	private CardCollectionBean deck;
	private CardCollectionBean stack;
	private CardCollectionBean graveyard;
	private DanishModel danish;

	GridBagLayout gridbag = new GridBagLayout();
	GridBagConstraints c = new GridBagConstraints();

	private List<CardBean> selectedCards;

	public DanishUI(DanishModel danish) {
		this.danish = danish;
		selectedCards = new ArrayList<>();

		initComponent();

		setPlayers(nbOpponent, getRandomName());

		this.stack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getComponent() instanceof CardBean) {
					take();
				}
			}

		});

		this.current.getHand().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				Component tmp = me.getComponent();
				if (tmp instanceof CardBean) {
					toggleCardSelection((CardBean) tmp);
				}
			}

		});

		this.current.getVisible().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				Component tmp = me.getComponent();
				if (tmp instanceof CardBean) {
					switchCard((CardBean) tmp);
				}
			}

		});

		this.current.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				clickPlay();
			}
		});
		for (OpponentBean o : this.opponent) {
			o.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ae) {
					clickAttack(((OpponentBean) ((JButton) ae.getSource()).getParent()).getPlayer());
				}
			});
		}
	}

	@Override
	public void refresh() {
		refreshButtons();
		if (danish.getWinner() == null){
			warningWinner = true;
		}
		if (danish.getPlayers() == null){
			this.setPlayers( nbOpponent, getRandomName());
		}
		
		this.current.getHand().setEnabled(!this.danish.isPlaying() || !(this.danish.getPlaying() instanceof PlayerAI));

		int p = 0;

		while (this.danish.getPlayers().get(p) instanceof PlayerAI) {
			++p;
		}

		this.current.setPlayer(this.danish.getPlayers().get(p));
		this.current.setCurrent(this.danish.getPlaying().equals(this.danish.getPlayers().get(p)));

		int size = this.danish.getPlayers().size();

		for (int i = 0; i < size; ++i) {
			if (i != p) {
				int index = ((i - p + size) % (size)) - 1; // calcule la position de l'IA par rapport au player
				Player player = this.danish.getPlayers().get(i);

				this.opponent[index].setPlayer(player);
				this.opponent[index].setCurrent(this.danish.getPlaying().equals(player));
			}
		}
		for (int i = size-1; i < 3; ++i) {
			this.opponent[i].setPlayer(new PlayerAI(null));
			this.opponent[i].setCurrent(false);
		}
		
		this.deck.setPack(this.danish.getDeck());
		this.graveyard.setPack(this.danish.getGraveyard());
		this.stack.setPack(this.danish.getStack());

		this.revalidate();

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				if (danish.isPlaying() && (danish.getPlaying() instanceof PlayerAI)) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException ex) {
					}
					((PlayerAI) danish.getPlaying()).play();
				}
			}
		});
		
		if (!danish.isPlaying() && danish.getWinner() != null && warningWinner){
			JOptionPane.showMessageDialog(this, "The Winner is ... " + danish.getWinner().getName(),"Winner" , JOptionPane.INFORMATION_MESSAGE);
			warningWinner = false;
		}
	}

	@Override
	public void removeNotify() {
		super.removeNotify();
		if (this.danish != null) {
			danish.removeDanishListener(this);
			this.danish = null;
		}
	}

	private void initComponent() {
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
		this.deck.setNbCardMin(1);

		this.stack.setOverlap(new Point(20, 0));
		this.stack.setNbCard(4);
		this.stack.setNbCardMin(1);

		this.graveyard.setShowSize(true);
		this.graveyard.setNbCardMin(1);

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

		/*GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();*/

		setLayout(gridbag);

		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridy = 0;
		c.gridx = 0;
		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		gridbag.setConstraints(this.opponent[0], c);

		c.gridx = 1;
		gridbag.setConstraints(this.opponent[1], c);

		c.gridx = 2;
		gridbag.setConstraints(this.opponent[2], c);

		c.gridy = 1;
		c.gridx = 0;
		c.gridwidth = 1;
		gridbag.setConstraints(this.deck, c);

		c.gridx = 1;
		c.gridwidth = 1;
		gridbag.setConstraints(this.stack, c);

		c.gridx = 2;
		c.gridwidth = 1;
		gridbag.setConstraints(this.graveyard, c);

		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 3;
		gridbag.setConstraints(this.current, c);
	}

	private void setPlayers(int nbIA, String playerName) {
		if (nbIA < 4 && nbIA > 0) {
			ArrayList<String> players = new ArrayList<>();

			players.add(playerName);
			for (int i = 0; i < nbIA; ++i) {
				players.add(null);
			}

			this.danish.setPlayers(players);
		}
	}

	private String getRandomName() {
		ArrayList<String> names = new ArrayList<>();

		names.add("Player");
		names.add("Default");

		Collections.shuffle(names);

		return names.get(0);
	}

	private void toggleCardSelection(CardBean card) {
		if (this.selectedCards.contains(card)) {
			this.selectedCards.remove(card);
		} else if (!this.danish.isPlaying()) {

			unselectCards();

			this.selectedCards.add(card);

		} else if (canBeSelected(card.getCard())) {
			this.selectedCards.add(card);
		} else {
			return;
		}

		refreshButtons();

		this.current.getHand().togglePopup(card);
	}

	private void unselectCards() {
		for (CardBean c : this.selectedCards) {
			this.current.getHand().popup(c, false);
		}
		this.selectedCards.clear();
		refreshButtons();
	}

	private boolean canBeSelected(Card c) {
		return this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() == c.getRank();
	}

	private void refreshButtons() {
		if (this.danish.isPlaying()) {
			this.current.setButtonText("Play");

			this.current.DisableButton(this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() == Rank.ACE || (this.selectedCards.get(0).getCard().getRank() == Rank.THREE && this.danish.getRankStack() == Rank.ACE));
		} else {
			this.current.setButtonText("Lock");
			this.current.DisableButton(false);
		}

		for (OpponentBean o : this.opponent) {
			o.DisableButton(!this.danish.isPlaying() || this.selectedCards.isEmpty() || (this.selectedCards.get(0).getCard().getRank() != Rank.ACE && !(this.selectedCards.get(0).getCard().getRank() == Rank.THREE && this.danish.getRankStack() == Rank.ACE)));
		}
	}

	private void switchCard(CardBean card) {
		if (this.selectedCards.size() == 1) {
			this.danish.switchCard(this.current.getPlayer(), (CardDanish) card.getCard(), (CardDanish) this.selectedCards.get(0).getCard());
			this.selectedCards.clear();
		}
	}

	private void clickPlay() {

		if (this.danish.isPlaying()) {

			ArrayList<CardDanish> tmp = new ArrayList<>();

			for (CardBean c : this.selectedCards) {
				tmp.add((CardDanish) (c.getCard()));
			}

			this.danish.turn(tmp);

		} else {
			this.danish.begin();
		}

		unselectCards();
	}

	private void clickAttack(Player p) {

		ArrayList<CardDanish> tmp = new ArrayList<>();

		for (CardBean c : this.selectedCards) {
			tmp.add((CardDanish) (c.getCard()));
		}

		this.danish.turn(tmp, p);

		unselectCards();
	}

	private void take() {
		if (this.danish.isPlaying() && !(this.danish.getPlaying() instanceof PlayerAI)) {
			this.danish.turn(new ArrayList<CardDanish>());
			unselectCards();
		}
	}

	public void setNbOpponent( int nbOpponent ){
		this.nbOpponent = nbOpponent;
		
		remove( this.opponent[0] );
		remove( this.opponent[1] );
		remove( this.opponent[2] );
		for( int i = 0; i < nbOpponent; i++ ){
			add( this.opponent[i] );
		}
		GridBagConstraints c = new GridBagConstraints();
		
		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		
		if (nbOpponent == 1){
			c.gridy = 0;
			c.gridx = 1;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 1;
			
			gridbag.setConstraints(this.opponent[0], c);
		}else if (nbOpponent == 2){
			c.gridy = 0;
			c.gridx = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 1;
			gridbag.setConstraints(this.opponent[0], c);
			c.gridx = 2;
			gridbag.setConstraints(this.opponent[1], c);
			
			
		}else if (nbOpponent == 3){
			c.gridy = 0;
			c.gridx = 0;
			c.weightx = 1;
			c.weighty = 1;
			c.gridwidth = 1;
			gridbag.setConstraints(this.opponent[0], c);

			c.gridx = 1;
			gridbag.setConstraints(this.opponent[1], c);

			c.gridx = 2;
			gridbag.setConstraints(this.opponent[2], c);
		}
		
		this.repaint();
		this.revalidate();
	}
}
