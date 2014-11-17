package danish.view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class OpponentBean extends PlayerBean {

	private final JButton attack;

	/**
	 * OpponentBean constructor without parameter.
	 */
	public OpponentBean() {
		super();
		this.attack = new JButton("Attack");

		this.name.setHorizontalAlignment(JLabel.CENTER);
		/*
		 this.attack.setBorder( new LineBorder(Color.black));
		 this.hidden.setBorder( new LineBorder(Color.black));
		 this.hand.setBorder( new LineBorder(Color.black));
		 this.visible.setBorder( new LineBorder(Color.black));
		 this.name.setBorder( new LineBorder(Color.black));
		 this.setBorder( new LineBorder(Color.black));
		 */
		this.hidden.setNbCard(3);
		this.hidden.setOverlap(new Point(110, 0));
		this.hidden.setHidden(true);
		this.hidden.setShowSize(true);
		this.hidden.setNbCardMin(3);
		//this.hidden.setLayoutAlignmentX(1f);

		this.hand.setNbCard(3);
		this.hand.setHidden(true);
		this.hand.setShowSize(true);
		this.hand.setOverlap(new Point(20, 0));
		this.hand.setNbCardMin(1);
		//this.hand.setLayoutAlignmentX(0f);

		this.visible.setNbCard(3);
		this.visible.setOverlap(new Point(110, 0));
		this.visible.setNbCardMin(3);

		add(this.name);
		add(this.hidden);
		add(this.hand);
		add(this.visible);
		add(this.attack);

		/* But :
		 name
		 h => hidden | hand
		 visible
		 attack
		 */
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();

		setLayout(gridbag);

		c.insets = new Insets(5, 5, 5, 5);
		c.fill = GridBagConstraints.BOTH;
		c.gridwidth = 2;
		c.gridx = 0;

		c.gridy = 0;
		gridbag.setConstraints(this.name, c);

		c.weightx = 1;
		c.weighty = 1;
		c.gridy = 2;
		gridbag.setConstraints(this.visible, c);

		c.weightx = 0;
		c.weighty = 0;
		c.gridy = 3;
		c.insets.left = 100;
		c.insets.right = 100;
		gridbag.setConstraints(this.attack, c);

		c.weightx = 1;
		c.weighty = 1;
		c.gridwidth = 1;
		c.gridy = 1;
		c.insets.left = 5;
		c.insets.right = 5;

		gridbag.setConstraints(this.hidden, c);

		c.gridx = 1;
		gridbag.setConstraints(this.hand, c);
	}

	void disableButton(boolean b) {
		this.attack.setEnabled(!b);
	}

	void addActionListener(ActionListener a) {
		this.attack.addActionListener(a);
	}
}
