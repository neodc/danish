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
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class NewGame extends JDialog {

	private JRadioButton nbAI1, nbAI2, nbAI3;
	private JTextField nameField;
	private boolean sendInfo;
	private int numberAI;

	/**
	 * NewGame constructor with three parameters.
	 *
	 * @param parent The parent JFrame.
	 * @param title The title of the Dialog.
	 * @param modal If the Dialog is modal.
	 */
	public NewGame(JFrame parent, String title, boolean modal) {
		super(parent, title, modal);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
		this.initComponent();

		sendInfo = false;
		numberAI = 3;
		pack();
	}

	private void initComponent() {
		JPanel nbIA = new JPanel();
		nbIA.setBorder(BorderFactory.createTitledBorder("Number of opponents"));
		nbIA.setPreferredSize(new Dimension(440, 60));

		nbAI1 = new JRadioButton("1");
		nbAI2 = new JRadioButton("2");
		nbAI3 = new JRadioButton("3");
		nbAI3.setSelected(true);

		ButtonGroup bg = new ButtonGroup();
		bg.add(nbAI1);
		bg.add(nbAI2);
		bg.add(nbAI3);
		nbIA.add(nbAI1);
		nbIA.add(nbAI2);
		nbIA.add(nbAI3);

		nbAI1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				numberAI = 1;
			}
		});
		nbAI2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				numberAI = 2;
			}
		});
		nbAI3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				numberAI = 3;
			}
		});
		JButton okBouton = new JButton("OK");
		JButton cancelBouton = new JButton("Cancel");

		JPanel control = new JPanel();
		control.add(okBouton);
		control.add(cancelBouton);
		getRootPane().setDefaultButton(okBouton);

		okBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendInfo = true;
				setVisible(false);
			}
		});

		cancelBouton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				sendInfo = false;
				setVisible(false);
			}
		});

		JPanel name = new JPanel();
		this.nameField = new JTextField("Player", 10);

		name.add(new JLabel("Name : "));
		name.add(this.nameField);

		JPanel content = new JPanel();
		content.add(nbIA);

		add(content, BorderLayout.NORTH);
		add(name, BorderLayout.CENTER);
		add(control, BorderLayout.SOUTH);

	}

	/**
	 * Tells if the informations of the Dialog will be sent.
	 *
	 * @return if the informations of the Dialog will be sent.
	 */
	public boolean isSendInfo() {
		return sendInfo;
	}

	/**
	 * Returns the numbers of AI of the new game.
	 *
	 * @return The numbers of AI of the new game.
	 */
	public int getNumberAI() {
		return numberAI;
	}

	/**
	 * Returns the player's name.
	 *
	 * @return The player's name.
	 */
	public String getPlayerName() {
		return this.nameField.getText();
	}
}
