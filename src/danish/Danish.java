package danish;
import danish.view.DanishUI;

/**
 *
 * @author Noé, Julien, Loup.
 */
public class Danish {

	/**
	 * @param args the command line arguments.
	 */
	public static void main(String[] args) {
		danish.model.Danish danish = new danish.model.Danish();
		
		DanishUI danishUI = new DanishUI(danish);
		//danish.addObserver(danishUI);
		danishUI.setVisible( true );
	}
}