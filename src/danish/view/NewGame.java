package danish.view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class NewGame extends JDialog {
	private JRadioButton nbIA1, nbIA2, nbIA3;
	private boolean sendInfo;
	private int numberIA;
	
	public NewGame(JFrame parent, String title, boolean modal){
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		this.initComponent();
		
		sendInfo = false;
		numberIA = 3;
		pack();
	}
	
	private void initComponent(){
		JPanel nbIA = new JPanel();    
		nbIA.setBorder(BorderFactory.createTitledBorder("Number of opponents"));
		nbIA.setPreferredSize(new Dimension(440, 60));
		
		nbIA1 = new JRadioButton("1");
		nbIA2 = new JRadioButton("2");
		nbIA3 = new JRadioButton("3");
		nbIA3.setSelected(true);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(nbIA1);
		bg.add(nbIA2);
		bg.add(nbIA3);
		nbIA.add(nbIA1);
		nbIA.add(nbIA2);
		nbIA.add(nbIA3);
		
		nbIA1.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {    
				numberIA = 1;
			}
		});
		nbIA2.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {    
				numberIA = 2;
			}
		});
		nbIA3.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {    
				numberIA = 3;
			}
		});
		JPanel control = new JPanel();
		
		JButton okBouton = new JButton("OK");
		okBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendInfo = true;
				setVisible(false);
			}      
		});
		
		JButton cancelBouton = new JButton("Cancel");
		cancelBouton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendInfo = false;
				setVisible(false);
			}      
		});

		JPanel content = new JPanel();
		content.add(nbIA);
		control.add(okBouton);
		control.add(cancelBouton);
		
		add(content, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);
		
	}

	public boolean isSendInfo(){
		return sendInfo;
	}

	public int getNumberIA(){
		return numberIA;
	}
	
}
