package danish;

import danish.view.DanishUI;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JFrame;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Danish {

	private static JFrame jFrame;
	private static DanishUI danishUI;

	/**
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		danish.model.Danish danish = new danish.model.Danish();

		danishUI = new DanishUI(danish);
		danish.addDanishListener(danishUI);

		jFrame = new JFrame();
		jFrame.setPreferredSize(new Dimension(1500, 1000));

		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Danish");

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
