package danish.view;

import danish.business.DanishFacade;
import danish.business.PersistanceException;
import danish.dto.PlayerDto;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class SelectUser extends JDialog {
	
	private JPanel selector;
	private JComboBox<Item> existingUser;
	private JTextField newUser;
	private JCheckBox isNew;
	
	public SelectUser(JFrame parent, boolean modal) {
		super(parent, "Select user", modal);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

		this.initComponent();
		refresh();
		
		this.isNew.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ){
				refresh();
			}
		});

		pack();
	}
	
	public boolean isNewUser(){
		return this.isNew.isSelected();
	}
	
	public String getNewName(){
		return newUser.getText();
	}
	
	public int getExistingId(){
		return ((Item)existingUser.getSelectedItem()).getId();
	}

	private void initComponent() {
		JPanel bottom = new JPanel(new BorderLayout());
		add(bottom, BorderLayout.SOUTH);
		
		isNew = new JCheckBox("New ?");
		bottom.add(isNew, BorderLayout.WEST);
		
		JButton ok = new JButton("OK");
		bottom.add(ok, BorderLayout.EAST);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent e ){
				setVisible(false);
			}
		});
		
		selector = new JPanel();
		selector.setBorder(BorderFactory.createTitledBorder("Who are you?"));
		selector.setPreferredSize(new Dimension(300, 60));
		add(selector, BorderLayout.CENTER);
		
		newUser = new JTextField(20);
		existingUser = new JComboBox( getItems() );
		
		selectItem();
	}
	
	private void refresh(){
		selector.removeAll();
		if( isNew.isSelected() ){
			selector.add(newUser);
		}else{
			selector.add(existingUser);
		}
		revalidate();
		repaint();
	}
	
	private Item[] getItems(){
		Collection<Item> items = new ArrayList<>();
		
		try{
			for( PlayerDto p : DanishFacade.getAllPlayer() ){
				items.add( new Item(p.getName(), p.getId()) );
			}
		}catch( PersistanceException ex ){
			System.out.println( "ERR" ); // TODO
		}
		return items.toArray( new Item[0] );
	}
	
	private void selectItem(){
		PlayerDto currentPlayer = null;
		try{
			currentPlayer = DanishFacade.getCurrentPlayer();
		}catch( PersistanceException ex ){}
		if( currentPlayer == null ){
			return;
		}
		existingUser.setSelectedItem( new Item(currentPlayer.getName(), currentPlayer.getId()) );
	}
	
	private class Item{
		private String name;
		private int id;

		public Item( String name, int id ){
			this.name = name;
			this.id = id;
		}

		public String getName(){
			return name;
		}

		public int getId(){
			return id;
		}

		@Override
		public String toString(){
			return getName();
		}

		@Override
		public int hashCode(){
			return id;
		}

		@Override
		public boolean equals( Object obj ){
			if( obj == null || getClass() != obj.getClass() ){
				return false;
			}
			return this.id == ((Item)obj).id;
		}
	}
}
