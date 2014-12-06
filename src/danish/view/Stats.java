package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.PlayerDto;
import java.awt.BorderLayout;
import java.awt.Dialog;
import java.util.Collection;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Stats extends JDialog {
	private JTable table;

	public Stats(JFrame parent) {
		super(parent, "Stats", true);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.initComponent();		
		this.pack();

	}

	private void initComponent() {
		try{
			Collection<PlayerDto> p = DanishFacade.getAllPlayer();
			String[] columnNames = {"name","preferredStyle","isReverse","nbGame","nbVictory","averageScore"};
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
