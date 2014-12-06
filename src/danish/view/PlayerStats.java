package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.PlayerDto;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class PlayerStats extends JDialog {
	private JTable table;

	public PlayerStats(JFrame parent) {
		super(parent, "Player stats", true);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.initComponent();		
		this.pack();
	}

	private void initComponent() {
		try{
			Collection<PlayerDto> p = DanishFacade.getAllPlayer();
			String[] columnNames = {"Name","Preferred style","Sort reversed","Number of game played","Number of victory","Average score"};
			Object[][] data = new Object[p.size()][6];
			int i = 0;
			for( PlayerDto playerDto : p ){
				data[i][0] = playerDto.getName();
				data[i][1] = playerDto.getPreferredStyle();
				data[i][2] = !(playerDto.isReverse());
				data[i][3] = playerDto.getNbGame();
				data[i][4] = playerDto.getNbVictory();
				data[i][5] = playerDto.getAverageScore();
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
