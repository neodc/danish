package danish.view;

import javax.swing.JPanel;
import danish.model.Player;

/**
 *
 * @author Noé, Julien, Loup.
 */
public abstract class PlayerBean extends JPanel{
	
	protected Player player = null;

	public Player getPlayer(){
		return player;
	}

	public void setPlayer( Player player ){
		if( player == null ){
			throw new NullPointerException();
		}
		this.player = player;
		refresh();
	}
	
	public abstract void refresh();
}
