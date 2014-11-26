package danish;

import danish.view.DanishUI;
import danish.view.Settings;
import danish.view.img.Images;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDialog;
import javax.swing.JFrame;
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

	/**
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		danish = new danish.model.Danish();
		settingsUI = new Settings(null, "NewGame", true);

		danishUI = new DanishUI(danish);
		danish.addDanishListener(danishUI);

		jFrame = new JFrame();
		jFrame.setPreferredSize(new Dimension(1500, 1000));

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Danish");

		JMenuBar menuBar = new JMenuBar();
		JMenu play = new JMenu("Play");

		menuBar.add(play);
		jFrame.setJMenuBar(menuBar);

		JMenuItem newGame = new JMenuItem("New game");
		JMenuItem settings = new JMenuItem("Settings");
		JCheckBoxMenuItem reverseSort = new JCheckBoxMenuItem("Reverse sort");
		JMenuItem help = new JMenuItem("Help");
		JMenuItem quit = new JMenuItem("Quit");

		newGame.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.Event.CTRL_MASK));
		quit.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.Event.CTRL_MASK));
		reverseSort.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.Event.CTRL_MASK));
		help.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.Event.CTRL_MASK));
		settings.setAccelerator(KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.Event.CTRL_MASK));

		play.add(newGame);
		play.add(settings);
		play.add(reverseSort);
		play.add(help);
		play.add(quit);

		JMenu style = new JMenu("Style");
		menuBar.add(style);

		ButtonGroup styleGroup = new ButtonGroup();

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
				}
			});
		}

		newGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				newGame();
			}
		});

		settings.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				settingsUI.setVisible(true);
				if (settingsUI.isSendInfo()) {
					newGame();
				}
			}
		});

		reverseSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				danishUI.toggleReverse();
			}
		});

		help.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				String rules
						= "<html><h1>Règles du jeu</h1>"
						+ "<h2>Déroulement d'une partie</h2>"
						+ "<p>"
						+ "Les cartes sont distribuées une à une. Les cartes en trop constitueront la pioche."
						+ "</p>"
						+ "<p>"
						+ "On commence par donner à chacun une carte qui reste cachée, face contre la table.<br/>"
						+ "Cette étape est répétée de façon à ce que chaque joueur dispose de 3 cartes devant lui,<br/>"
						+ "dont on ignore la valeur et la couleur. Ensuite, chaque joueur reçoit 3 cartes supplémentaires,<br/>"
						+ "toujours distribuées une à une, mais cette fois-ci, la couleur et la valeur de chaque carte<br/>"
						+ "sont montrées à tous. Enfin, on termine en donnant à tous les participants 3 autres cartes qui<br/>"
						+ "seront tenues en main et donc cachées des adversaires. Le reste des cartes constitue la pioche.<br/>"
						+ "Chaque joueur dispose ainsi de 9 cartes : <b>3 faces cachées, 3 faces visibles et 3 en main</b>."
						+ "</p>"
						+ "<p>"
						+ "Avant de commencer la partie, les participants peuvent échanger les cartes qu'ils ont en main<br/>"
						+ "avec leurs cartes visibles. Il n'y a qu'à ce point du jeu que l'on peut changer ses cartes.<br/>"
						+ "Par expérience, on remarque qu'il est préférable de disposer devant soi des cartes qui ont une<br/>"
						+ "forte valeur ou des cartes spéciales (celles-ci seront détaillées plus loin)."
						+ "</p>"
						+ "<p>"
						+ "Au début de la partie, le joueur peut choisir de poser une, deux, trois ou quatre cartes sur<br/>"
						+ "la table, du moment que celles-ci sont de même valeur (la couleur n'importe pas). En outre,<br/>"
						+ "aussi longtemps qu'il reste des cartes dans la pioche, un joueur doit toujours posséder 3 cartes<br/>"
						+ "en main. Ainsi, après avoir déposé ses cartes, le joueur reprend dans la pioche le nombre<br/>"
						+ "nécessaire de cartes afin de compléter sa main. Le joueur suivant doit jouer au moins une carte<br/>"
						+ "de valeur supérieure ou égale à celle(s) posée(s) par le joueur précédent. Lui aussi peut<br/>"
						+ "choisir de poser une ou plusieurs cartes de même valeur. Après avoir joué, ce joueur pioche<br/>"
						+ "également autant de cartes que nécessaire afin d'en posséder à nouveau 3 en main. Et ainsi<br/>"
						+ "de suite pour chaque joueur. Toutefois, il arrivera rapidement un moment où l'un des joueurs<br/>"
						+ "n'aura plus la possibilité de poser de cartes suffisamment grosses. Dans ce cas, celui-ci<br/>"
						+ "<b>ramasse le paquet constitué</b> et dispose désormais de toutes celles-ci pour jouer<br/>"
						+ "(se dit aussi \"qu'il cocogne\"). La main passe au joueur suivant."
						+ "</p>"
						+ "<p>"
						+ "Lorsque la pioche sera épuisée, chaque joueur devra vider sa main afin de pouvoir ramasser<br/>"
						+ "les 3 cartes visibles devant lui. Dès qu'un des joueurs n'a plus de cartes en main, il les<br/>"
						+ "ramasse simplement et continue à jouer comme précédemment. Lorsqu'un joueur n'a de nouveau<br/>"
						+ "plus de cartes en main, il pioche une des trois cartes retournées disposées devant lui. Si<br/>"
						+ "celle-ci convient, il la pose et peut en prendre une autre qu'il posera à son tour si elle<br/>"
						+ "convient. S'il ne peut jouer cette carte, le joueur ramasse le tas et doit éliminer les cartes<br/>"
						+ "qu'il a en main avant de pouvoir à nouveau ramasser une des cartes devant lui."
						+ "</p>"
						+ "<p>"
						+ "<b>Le gagnant est le premier joueur à ne plus avoir de cartes devant lui ni dans sa main.</b>"
						+ "</p>"
						+ "<h2>Cartes spéciales</h2>"
						+ "<p>"
						+ "À part le 7 et le 8, les cartes spéciales peuvent se placer sur n'importe quelle autre."
						+ "<ul>"
						+ "<li>2 : Le jeu repart au 2, signifiant que n'importe quelle carte peut être jouée dessus.</li>"
						+ "<li>3 : La carte est considérée comme une copie de la carte précédemment jouée.</li>"
						+ "<li>7 : Le joueur suivant doit impérativement jouer une (ou plusieurs) cartes de valeur<br/>"
						+ "inférieure ou égale à 7 (quel que soit le nombre de 7 posés).</li>"
						+ "<li>8 : Le joueur suivant passe son tour (si on met plusieurs 8, autant de joueurs que de<br/>"
						+ "8 posés passent leur tour).</li>"
						+ "<li>10 : Les cartes du tas (le 10 compris) sont retirées du jeu (se dit aussi, qu'il bêche).<br/>"
						+ "Elles ne pourront pas revenir dans la partie.</li>"
						+ "<li>As : Cette carte permet d'attaquer le joueur que l'on veut. Le tour passe au joueur<br/>"
						+ "attaqué. Si ce joueur ne peut pas répondre à l'attaque, il prend le tas et finit son tour. Cette<br/>"
						+ "carte ne peut être répondue que par un autre As ou un 3 (provoquant une nouvelle attaque),<br/>"
						+ "un 2 (annulant l'effet de l'attaque), ou un 10 (coupant le jeu).</li>"
						+ "</ul>"
						+ "Lorsque l'on a 4 cartes de valeurs identiques qui se suivent (par exemple 4 Rois, 4 Six,<br/>"
						+ "4 Sept, ...), on obtient le même effet qu'en posant un Dix (ça bêche) : le tas est retiré du jeu,<br/>"
						+ "permet de raccourcir un peu la partie. Quand un joueur bêche (par un 10 comme par un quadruple),<br/>"
						+ "il rejoue directement. Les 3 copiants d'autre cartes ne coupent pas le tas (quatre 3 qui se suivent<br/>"
						+ "le feront cependant)."
						+ "</p></html>";
				JOptionPane.showMessageDialog(jFrame, rules, "Help", JOptionPane.PLAIN_MESSAGE);
			}
		});

		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				System.exit(0);
			}
		});

		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		jFrame.setLayout(gridbag);

		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;

		jFrame.add(danishUI, c);

		jFrame.setVisible(true);

		jFrame.pack();
		danishUI.update();
	}

	private static void newGame() {
		danishUI.setPlayerName(settingsUI.getPlayerName());
		danishUI.setNbOpponent(settingsUI.getNumberAI());
		danish.newGame();
	}
}
