package danish.view;

import danish.beans.CardBean;
import danish.beans.CardCollectionBean;
import danish.beans.OpponentBean;
import danish.beans.HumanPlayerBean;
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
import javax.swing.Timer;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class DanishUI extends JComponent implements DanishView {

	private int nbOpponent;
	private boolean warningWinner;
	private String playerName;
	private OpponentBean[] opponent;
	private HumanPlayerBean humanPlayer;
	private CardCollectionBean deck;
	private CardCollectionBean stack;
	private CardCollectionBean graveyard;
	private DanishModel danish;

	private GridBagLayout gridbag;

	private final List<CardBean> selectedCards;

	private Timer timerAI;

	/**
	 * DanishUI constructor with one parameter.
	 *
	 * @param danish The danish game the UI will display.
	 */
	public DanishUI(DanishModel danish) {
		this.danish = danish;
		selectedCards = new ArrayList<>();

		this.nbOpponent = 3;
		this.warningWinner = true;
		this.playerName = "Player";

		initComponent();

		setPlayers();

		this.stack.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				if (me.getComponent() instanceof CardBean && ((CardBean) me.getComponent()).getCard() != null) {
					take();
				}
			}

		});

		this.humanPlayer.getHand().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				Component tmp = me.getComponent();
				if (tmp instanceof CardBean) {
					toggleCardSelection((CardBean) tmp);
				}
			}

		});

		this.humanPlayer.getVisible().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent me) {
				Component tmp = me.getComponent();
				if (tmp instanceof CardBean) {
					switchCard((CardBean) tmp);
				}
			}

		});

		this.humanPlayer.addActionListener(new ActionListener() {

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
	public void update() {
		refreshButtons();
		if (danish.getWinner() == null) {
			warningWinner = true;
		}
		if (danish.getPlayers() == null) {
			this.setPlayers();
		}

		this.humanPlayer.getHand().setEnabled(!this.danish.isPlaying() || !(this.danish.getPlaying() instanceof PlayerAI));

		int p = 0;

		while (this.danish.getPlayers().get(p) instanceof PlayerAI) {
			++p;
		}

		this.humanPlayer.setPlayer(this.danish.getPlayers().get(p));
		this.humanPlayer.setCurrent(this.danish.getWinner() == null && this.danish.getPlaying().equals(this.danish.getPlayers().get(p)));

		int size = this.danish.getPlayers().size();

		for (int i = 0; i < size; ++i) {
			if (i != p) {
				int index = ((i - p + size) % (size)) - 1; // calcule la position de l'IA par rapport au player
				Player player = this.danish.getPlayers().get(i);

				this.opponent[index].setPlayer(player);
				this.opponent[index].setCurrent(this.danish.getWinner() == null && this.danish.getPlaying().equals(player));
			}
		}

		this.deck.setPack(this.danish.getDeck());
		this.graveyard.setPack(this.danish.getGraveyard());
		this.stack.setPack(this.danish.getStack());

		this.revalidate();

		timerAI.restart();

		if (!danish.isPlaying() && danish.getWinner() != null && warningWinner) {
			JOptionPane.showMessageDialog(this, "The Winner is ... " + danish.getWinner().getName(), "Winner", JOptionPane.INFORMATION_MESSAGE);
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
		this.humanPlayer = new HumanPlayerBean();
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
		 this.humanPlayer.setBorder( new LineBorder(Color.black, 5));
		 this.deck.setBorder( new LineBorder(Color.black, 5));
		 this.stack.setBorder( new LineBorder(Color.black, 5));
		 this.graveyard.setBorder( new LineBorder(Color.black, 5));
		 */
		add(opponent[0]);
		add(opponent[1]);
		add(opponent[2]);
		add(humanPlayer);
		add(deck);
		add(stack);
		add(graveyard);

		gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

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
		gridbag.setConstraints(this.humanPlayer, c);

		timerAI = new Timer(0, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				if (danish.isPlaying() && (danish.getPlaying() instanceof PlayerAI)) {
					((PlayerAI) danish.getPlaying()).play();
				}
			}
		});

		timerAI.setInitialDelay(2000);
		timerAI.setRepeats(false);
	}

	private void setPlayers() {
		ArrayList<String> players = new ArrayList<>();

		players.add(this.playerName);
		for (int i = 0; i < this.nbOpponent; ++i) {
			players.add(null);
		}

		this.danish.setPlayers(players);

		List<String> names = getRandomNames();

		for (Player p : this.danish.getPlayers()) {
			if (p instanceof PlayerAI) {
				p.setName(names.remove(0));
			}
		}
	}

	private List<String> getRandomNames() {
		ArrayList<String> names = new ArrayList<>();

		names.add("Twilight Sparkle");
		names.add("Applejack");
		names.add("Fluttershy");
		names.add("Rarity");
		names.add("Pinkie Pie");
		names.add("Rainbow Dash");
		names.add("Spike");
		names.add("Apple Bloom");
		names.add("Scootaloo");
		names.add("Sweetie Belle");
		names.add("Babs Seed");
		names.add("Princess Celestia");
		names.add("Princess Luna");
		names.add("Princess Cadance");
		names.add("Shining Armor");
		names.add("Prince Blueblood");
		names.add("Granny Smith");
		names.add("Big McIntosh");
		names.add("Braeburn");
		names.add("Diamond Tiara");
		names.add("Silver Spoon");
		names.add("Snips");
		names.add("Snails");
		names.add("Pipsqueak");
		names.add("Featherweight");
		names.add("Nightmare Moon");
		names.add("Discord");
		names.add("Queen Chrysalis");
		names.add("King Sombra");
		names.add("Sunset Shimmer");
		names.add("Lord Tirek");
		names.add("Adagio Dazzle");
		names.add("Aria Blaze");
		names.add("Sonata Dusk");
		names.add("Trixie");
		names.add("Daring Do");
		names.add("Spitfire");
		names.add("Soarin");
		names.add("Cheerilee");
		names.add("Lightning Dust");
		names.add("Flash Sentry");
		names.add("Coco Pommel");
		names.add("Maud Pie");
		names.add("Tom");
		names.add("Berry Punch");
		names.add("Caramel");
		names.add("Dr. Hooves");
		names.add("Carrot Top");
		names.add("Octavia Melody");
		names.add("Roseluck");
		names.add("Bon Bon");
		names.add("Derpy");
		names.add("Amethyst Star");
		names.add("Vinyl Scratch");
		names.add("Lyra Heartstrings");
		names.add("Colgate");
		names.add("Button Mash");
		names.add("Snowflake");

		Collections.shuffle(names);

		return names;
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

		this.humanPlayer.getHand().togglePopup(card);
	}

	private void unselectCards() {
		for (CardBean c : this.selectedCards) {
			this.humanPlayer.getHand().popup(c, false);
		}
		this.selectedCards.clear();
		refreshButtons();
	}

	private boolean canBeSelected(Card c) {
		return this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() == c.getRank();
	}

	private void refreshButtons() {
		if (this.danish.isPlaying()) {
			this.humanPlayer.setButtonText("Play");

			this.humanPlayer.disableButton(this.selectedCards.isEmpty() || this.selectedCards.get(0).getCard().getRank() == Rank.ACE || (this.selectedCards.get(0).getCard().getRank() == Rank.THREE && this.danish.getRankStack() == Rank.ACE));
		} else if (this.danish.getWinner() != null) {
			this.humanPlayer.setButtonText("Play");
			this.humanPlayer.disableButton(true);
		} else {
			this.humanPlayer.setButtonText("Lock");
			this.humanPlayer.disableButton(false);
		}

		for (OpponentBean o : this.opponent) {
			o.disableButton(!this.danish.isPlaying() || this.selectedCards.isEmpty() || (this.selectedCards.get(0).getCard().getRank() != Rank.ACE && !(this.selectedCards.get(0).getCard().getRank() == Rank.THREE && this.danish.getRankStack() == Rank.ACE)));
		}
	}

	private void switchCard(CardBean card) {
		if (this.selectedCards.size() == 1) {
			this.danish.switchCard(this.humanPlayer.getPlayer(), (CardDanish) card.getCard(), (CardDanish) this.selectedCards.get(0).getCard());
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

	/**
	 * Sets the number of opponents the player will play with.
	 *
	 * @param nbOpponent The number of opponent.
	 */
	public void setNbOpponent(int nbOpponent) {
		if (nbOpponent < 4 && nbOpponent > 0) {
			this.nbOpponent = nbOpponent;

			remove(this.opponent[0]);
			remove(this.opponent[1]);
			remove(this.opponent[2]);

			for (int i = 0; i < nbOpponent; i++) {
				add(this.opponent[i]);
			}

			GridBagConstraints c = new GridBagConstraints();

			c.insets = new Insets(5, 5, 5, 5);
			c.fill = GridBagConstraints.BOTH;

			if (nbOpponent == 1) {
				c.gridy = 0;
				c.gridx = 1;
				c.weightx = 1;
				c.weighty = 1;
				c.gridwidth = 1;
				gridbag.setConstraints(this.opponent[0], c);
			} else if (nbOpponent == 2) {
				c.gridy = 0;
				c.gridx = 0;
				c.weightx = 1;
				c.weighty = 1;
				c.gridwidth = 1;
				gridbag.setConstraints(this.opponent[0], c);

				c.gridx = 2;
				gridbag.setConstraints(this.opponent[1], c);
			} else if (nbOpponent == 3) {
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

	/**
	 * Sets the player's name.
	 *
	 * @param playerName The player's name.
	 */
	public void setPlayerName(String playerName) {
		if (playerName != null) {
			this.playerName = playerName;
		}
	}

	/**
	 * Reverses the sort the player's hand.
	 */
	public void toggleReverse() {
		this.humanPlayer.toggleReverse();
		this.update();
	}
}
