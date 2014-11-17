package danish;

import danish.view.DanishUI;
import danish.view.NewGame;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Danish {

	private static JFrame jFrame;
	private static DanishUI danishUI;
	private static NewGame ng;

	/**
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		final danish.model.Danish danish = new danish.model.Danish();
		ng = new NewGame(null, "NewGame", true);

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
		JCheckBoxMenuItem  reverseSort = new JCheckBoxMenuItem ("Reverse sort");
		JMenuItem quit = new JMenuItem("Quit");
		play.add(newGame);
		play.add(reverseSort);
		play.add(quit);
		
		newGame.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent ae ){
				ng.setVisible(true);
				if (ng.isSendInfo()){
					danishUI.setPlayerName(ng.getPlayerName());
					danishUI.setNbOpponent(ng.getNumberIA());
					danish.newGame();
					
				}
			}
		});
		
		reverseSort.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent ae ){
				danishUI.invertReverse();
			}
		});
		
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed( ActionEvent ae ){
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
		danishUI.refresh();
	}
}
