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
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

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
		JMenuItem quit = new JMenuItem("Quit");
		play.add(newGame);
		play.add(settings);
		play.add(reverseSort);
		play.add(quit);
		
		
		JMenu style = new JMenu("Style");
		menuBar.add(style);
		
		ButtonGroup styleGroup = new ButtonGroup();
		
		for( Images.Style s : Images.Style.values() ){
			JRadioButtonMenuItem rb = new JRadioButtonMenuItem(s.getName());
			
			rb.setActionCommand( s.name() );
			
			if( s.equals( Images.getActual() ) ){
				rb.setSelected(true);
			}
			
			styleGroup.add(rb);
			style.add(rb);
			
			rb.addActionListener( new ActionListener() {

				@Override
				public void actionPerformed( ActionEvent e ){
					Images.Style style = Images.Style.valueOf(e.getActionCommand());
					
					Images.load( style );
					danishUI.refresh();
				}
			});
		}
		
		newGame.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ){
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
		danishUI.refresh();
	}
	
	private static void newGame(){
		danishUI.setPlayerName(settingsUI.getPlayerName());
		danishUI.setNbOpponent(settingsUI.getNumberAI());
		danish.newGame();
	}
}
