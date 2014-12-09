package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.GameDto;
import java.awt.Font;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Dialog used to display the stats of all games.
 *
 * @author Noé, Julien, Loup.
 */
public class GameStats extends JDialog {

	private JTable table;

	/**
	 * GameStats constructor with one parameter.
	 *
	 * @param parent The parent JFrame.
	 */
	public GameStats(JFrame parent) {
		super(parent, "Games stats", true);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.initComponent();
		this.pack();
	}

	private void initComponent() {
		try {
			Collection<GameDto> games = DanishFacade.getAllGame();
			String[] columnNames = {"Player name", "Victory", "Score", "Number of card played", "Number of opponent"};
			Object[][] data = new Object[games.size()][5];
			int i = 0;
			for (GameDto g : games) {
				data[i][0] = DanishFacade.getPlayer(g.getPlayerId()).getName();
				data[i][1] = g.isVictory();
				data[i][2] = g.getScore();
				data[i][3] = g.getNbCardsPlayed();
				data[i][4] = g.getNbOpponents();
				i++;
			}

			table = new JTable(data, columnNames);
			table.setAutoCreateRowSorter(true);
			table.setEnabled(false);
			table.setPreferredScrollableViewportSize(table.getPreferredSize());
			JScrollPane tmp = new JScrollPane(table);
			table.getTableHeader().setReorderingAllowed(false);
			add(tmp);
			setResizable(false);
		} catch (PersistanceException ex) {
			JLabel label = new JLabel(ex.getMessage());
			label.setFont(label.getFont().deriveFont(Font.PLAIN));
			JOptionPane.showMessageDialog(this, label, "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
	}
}
