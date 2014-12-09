package danish;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.db.DBException;
import danish.dto.PlayerDto;
import danish.view.DanishUI;
import danish.view.GameStats;
import danish.view.SelectUser;
import danish.view.Settings;
import danish.view.PlayerStats;
import danish.view.img.Images;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Danish {

	private static JFrame jFrame;
	private static DanishUI danishUI;
	private static Settings settingsUI;
	private static danish.model.Danish danish;
	private static JCheckBoxMenuItem reverseSort;
	private static JMenu style;

	/**
	 * @param args the command line arguments.
	 * @throws danish.db.DBException If something goes wrong with the DB.
	 */
	public static void main(String[] args) throws DBException {
		danish = new danish.model.Danish();
		
		danishUI = new DanishUI(danish);
		danish.addDanishListener(danishUI);

		jFrame = new JFrame();
		jFrame.setPreferredSize(new Dimension(1500, 1000));
		jFrame.setMinimumSize(new Dimension(512, 512));

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Danish");
		
		settingsUI = new Settings(jFrame, "Setings", true);

		JMenuBar menuBar = new JMenuBar();
		jFrame.setJMenuBar(menuBar);

		JMenu play = new JMenu("Play");
		menuBar.add(play);

		JMenu user = new JMenu("User");
		menuBar.add(user);

		JMenu stats = new JMenu("Stats");
		menuBar.add(stats);
		
		JMenu help = new JMenu("Help");
		menuBar.add(help);

		JMenuItem newGame = new JMenuItem("New game");
		newGame.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
		play.add(newGame);

		JMenuItem quit = new JMenuItem("Quit");
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		play.add(quit);

		JMenuItem changeUser = new JMenuItem("Change user");
		changeUser.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.Event.CTRL_MASK));
		user.add(changeUser);

		JMenuItem settings = new JMenuItem("Settings");
		settings.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));
		user.add(settings);

		reverseSort = new JCheckBoxMenuItem("Reverse sort");
		user.add(reverseSort);

		JMenuItem playerStats = new JMenuItem("Players");
		stats.add(playerStats);

		JMenuItem gamesStats = new JMenuItem("Games");
		stats.add(gamesStats);

		style = new JMenu("Style");
		user.add(style);

		updateStyles();

		JMenuItem rules = new JMenuItem("Rules");
		rules.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.Event.CTRL_MASK));
		help.add(rules);

		JMenuItem howToPlay = new JMenuItem("How to play");
		howToPlay.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.CTRL_MASK));
		help.add(howToPlay);

		JMenuItem about = new JMenuItem("About Danish");
		about.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.Event.CTRL_MASK));
		help.add(about);

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		changeUser.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				selectUser(true);
			}
		});

		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				settingsUI.setVisible(true);
				if (settingsUI.isSendInfo()) {
					try {
						PlayerDto currentPlayer = DanishFacade.getCurrentPlayer();
						DanishFacade.updateCurrentPlayer(new PlayerDto(settingsUI.getPlayerName(), currentPlayer.getPreferredStyle(), currentPlayer.isReverse()));
					} catch (PersistanceException ex) {
						JLabel label = new JLabel(ex.getMessage());
						label.setFont(label.getFont().deriveFont(Font.PLAIN));
						JOptionPane.showMessageDialog(jFrame, label, "Error!", JOptionPane.ERROR_MESSAGE);
					}
					newGame();
				}
			}
		});
		
		playerStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				PlayerStats s = new PlayerStats(jFrame);
				s.setVisible( true );
				
			}
		});
		
		gamesStats.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				GameStats s = new GameStats(jFrame);
				s.setVisible( true );
				
			}
		});

		reverseSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				boolean isReverse = !((JCheckBoxMenuItem) ae.getSource()).isSelected();
				danishUI.setReverse(isReverse);

				try {
					PlayerDto currentPlayer = DanishFacade.getCurrentPlayer();
					DanishFacade.updateCurrentPlayer(new PlayerDto(currentPlayer.getName(), currentPlayer.getPreferredStyle(), isReverse));
				} catch (PersistanceException ex) {
					JLabel label = new JLabel(ex.getMessage());
					label.setFont(label.getFont().deriveFont(Font.PLAIN));
					JOptionPane.showMessageDialog(jFrame, label, "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		howToPlay.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String howTo
						= "<html>"
						+ "<body style='width: 500px;'>"
						+ "	<h1>How to play</h1>"
						+ "	<p><i>If you want to see the rules of the Danish, close this window then press CTRL+R</i></p>"
						+ "	<h2>The interface</h2>"
						+ "	<p>"
						+ "		The board is composed of three parts:"
						+ "		<ul>"
						+ "			<li>The upper part, in which your opponents' cards are displayed</li>"
						+ "			<li>The middle part, in which the three piles of cards: The deck the cards are drawn from, the stack where they are played and the graveyard where they go when they are removed from play.</li>"
						+ "			<li>The lower part, in which your cards are displayed.</li>"
						+ "		</ul>"
						+ "		Your opponents and you have three groups of cards. The overlapping cards are the hands. The visible and the hidden cards are...the visible and the hidden cards."
						+ "	</p>"
						+ "	<h2>Switch your hand and your visible cards</h2>"
						+ "	<p>"
						+ "		At the beginning of the game, you can switch your cards from your hand with any of your visible cards. Click on a card from your hand (il will normally pop up) then on a card from your visible cards."
						+ "		Once you are pleased with your cards. Click on the <b>Lock</b> button."
						+ "	</p>"
						+ "	<h2>Play cards</h2>"
						+ "	<p>"
						+ "		The name of the current player is displayed in bold. To play cards, during your turn, click on them to pop them up and click on <b>Play</b>."
						+ "		If you attack an opponent, you have to click instead on the <b>Attack</b> button below the opponent you want to attack."
						+ "		In the situation you can't play, click on the stack of cards to pick them up and skip your turn."
						+ "	</p>"
						+ "</body>"
						+ "</html>";
				JLabel label = new JLabel(howTo);
				label.setFont(label.getFont().deriveFont(Font.PLAIN));
				JOptionPane.showMessageDialog(jFrame, label, "How to play", JOptionPane.PLAIN_MESSAGE);
			}
		});

		rules.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String url = "http://neodc.be/danish/index.html";

				if (Desktop.isDesktopSupported()) {
					Desktop desktop = Desktop.getDesktop();
					try {
						desktop.browse(new URI(url));
					} catch (IOException | URISyntaxException e) {}
				}
			}
		});

		about.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String aboutDanish
						= "<html>"
						+ "<body>"
						+ "	<h1>Danish V3.2.1</h1>"
						+ "	<p>Developed by Noé De Cuyper, Julien Durieux and Loup Vismara.</p>"
						+ "</body>"
						+ "</html>";
				JLabel label = new JLabel(aboutDanish);
				label.setFont(new Font("sans serif", Font.PLAIN, 14));
				JOptionPane.showMessageDialog(jFrame, label, "About", JOptionPane.PLAIN_MESSAGE);
			}
		});

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		jFrame.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		jFrame.add(danishUI, c);

		selectUser(false);

		jFrame.setVisible(true);

		jFrame.pack();
		danishUI.update();
	}

	private static void updateStyles() {
		ButtonGroup styleGroup = new ButtonGroup();
		style.removeAll();

		for (Images.Style s : Images.Style.values()) {
			JRadioButtonMenuItem rb = new JRadioButtonMenuItem(s.getName());

			rb.setActionCommand(s.name());

			if (s.equals(Images.getCurrent())) {
				rb.setSelected(true);
			}

			styleGroup.add(rb);
			style.add(rb);

			rb.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					Images.Style style = Images.Style.valueOf(e.getActionCommand());

					Images.load(style);
					danishUI.update();

					try {
						PlayerDto currentPlayer = DanishFacade.getCurrentPlayer();
						DanishFacade.updateCurrentPlayer(new PlayerDto(currentPlayer.getName(), style, currentPlayer.isReverse()));
					} catch (PersistanceException ex) {
						JLabel label = new JLabel(ex.getMessage());
						label.setFont(label.getFont().deriveFont(Font.PLAIN));
						JOptionPane.showMessageDialog(jFrame, label, "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		}
	}

	private static void newGame() {
		danishUI.setPlayerName(settingsUI.getPlayerName());
		danishUI.setNbOpponent(settingsUI.getNumberAI());
		danish.newGame();
	}

	private static void selectUser(boolean allowCancel) {
		SelectUser selectUser = new SelectUser(jFrame, true);

		boolean ok = false;

		while (!ok) {
			ok = true;
			selectUser.setVisible(true);

			if (!selectUser.isSendInfo()) {

				if (allowCancel) {
					return;
				} else {
					System.exit(0);
				}

			}

			if (selectUser.isNewUser()) {
				try {
					PlayerDto p = DanishFacade.createPlayer(new PlayerDto(selectUser.getNewName()));
					DanishFacade.setCurrentPlayer(p.getId());
				} catch (PersistanceException ex) {
					ok = false;
				}
			} else {
				DanishFacade.setCurrentPlayer(selectUser.getExistingId());
			}

		}

		try {
			PlayerDto currentPlayer = DanishFacade.getCurrentPlayer();

			danishUI.setReverse(currentPlayer.isReverse());
			reverseSort.setSelected(!currentPlayer.isReverse());

			Images.load(currentPlayer.getPreferredStyle());
			updateStyles();

			danishUI.setPlayerName(currentPlayer.getName());
			danish.newGame();

			danishUI.update();
		} catch (PersistanceException ex) {

		}
	}
}
