package danish;
import danish.model.CardDanish;
import danish.view.*;
import java.util.ArrayList;

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
		/*AbstractControler controler = new CalculetteControler(calc);*/
		
		DanishUI danishUI = new DanishUI(danish);
		//danish.addObserver(danishUI);
		danishUI.setVisible( true );
	}
}