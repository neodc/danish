package danish;
import danish.view.DanishUI;
import javax.swing.JFrame;

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
		danish.addDanishListener(danishUI);
		
		JFrame jFrame = new JFrame();
		
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setTitle("Danish");
		
		jFrame.add(danishUI);
		
		jFrame.setVisible(true);
		jFrame.pack();
	}
}