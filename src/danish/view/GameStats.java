package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.GameDto;
import danish.dto.PlayerDto;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GameStats extends JDialog {
	private JTable table;

	public GameStats(JFrame parent) {
		super(parent, "Games stats", true);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.initComponent();		
		this.pack();
	}

	private void initComponent() {
		try{
			Collection<GameDto> games = DanishFacade.getAllGame();
			String[] columnNames = {"Player name","Vistory","Score","Number of game played","Number of opponent"};
			Object[][] data = new Object[games.size()][5];
			int i = 0;
			for( GameDto g : games ){
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
		}catch( PersistanceException ex ){
			//TODO
		}
	}
}
