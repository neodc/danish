package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.PlayerDto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class SelectUser extends JDialog {

	private JPanel selector;
	private JComboBox<Item> existingUser;
	private JTextField newUser;
	private JCheckBox isNew;
	private boolean sendInfo;
	private JButton ok;

	/**
	 * SelectUser constructor with two parameters.
	 *
	 * @param parent The parent JFrame.
	 * @param modal If the Dialog is modal.
	 */
	public SelectUser(JFrame parent, boolean modal) {
		super(parent, "Select user", modal);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);

		this.sendInfo = false;

		this.initComponent();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				sendInfo = false;
			}

		});

		if (existingUser.getItemCount() == 0) {
			isNew.setSelected(true);
			isNew.setEnabled(false);
		}

		refresh();

		this.isNew.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});

		pack();
	}

	/**
	 * Tells if the user is a new user.
	 *
	 * @return If the user is a new user.
	 */
	public boolean isNewUser() {
		return this.isNew.isSelected();
	}

	/**
	 * Returns the name of the new user.
	 *
	 * @return The name of the new user.
	 */
	public String getNewName() {
		return newUser.getText();
	}

	/**
	 * Returns the id of the existing selected user.
	 *
	 * @return The id of the existing selected user.
	 */
	public int getExistingId() {
		return ((Item) existingUser.getSelectedItem()).getId();
	}

	/**
	 * Tells if the informations of the Dialog will be sent.
	 *
	 * @return if the informations of the Dialog will be sent.
	 */
	public boolean isSendInfo() {
		return sendInfo;
	}

	private void initComponent() {
		JPanel bottom = new JPanel(new BorderLayout());
		add(bottom, BorderLayout.SOUTH);

		isNew = new JCheckBox("New ?");
		bottom.add(isNew, BorderLayout.WEST);

		ok = new JButton("OK");
		bottom.add(ok, BorderLayout.EAST);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				sendInfo = true;
				setVisible(false);
			}
		});

		getRootPane().setDefaultButton(ok);

		selector = new JPanel();
		selector.setBorder(BorderFactory.createTitledBorder("Who are you?"));
		selector.setPreferredSize(new Dimension(300, 60));
		add(selector, BorderLayout.CENTER);

		newUser = new JTextField(20);
		existingUser = new JComboBox(getItems());

		selectItem();
	}

	private void refresh() {
		selector.removeAll();

		if (isNew.isSelected()) {
			selector.add(newUser);
		} else {
			selector.add(existingUser);
		}

		revalidate();
		repaint();
	}

	private Item[] getItems() {
		Collection<Item> items = new ArrayList<>();

		try {
			for (PlayerDto p : DanishFacade.getAllPlayer()) {
				items.add(new Item(p.getName(), p.getId()));
			}
		} catch (PersistanceException ex) {
			JLabel label = new JLabel(ex.getMessage());
			label.setFont(label.getFont().deriveFont(Font.PLAIN));
			JOptionPane.showMessageDialog(this, label, "Error!", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
		}
		return items.toArray(new Item[0]);
	}

	private void selectItem() {
		PlayerDto currentPlayer = null;
		try {
			currentPlayer = DanishFacade.getCurrentPlayer();
		} catch (PersistanceException ex) {
		}
		if (currentPlayer == null) {
			return;
		}
		existingUser.setSelectedItem(new Item(currentPlayer.getName(), currentPlayer.getId()));
	}

	private class Item {

		private final String name;
		private final int id;

		public Item(String name, int id) {
			this.name = name;
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public int getId() {
			return id;
		}

		@Override
		public String toString() {
			return getName();
		}

		@Override
		public int hashCode() {
			return id;
		}

		@Override
		public boolean equals(Object obj) {
			if (obj == null || getClass() != obj.getClass()) {
				return false;
			}
			return this.id == ((Item) obj).id;
		}
	}
}
