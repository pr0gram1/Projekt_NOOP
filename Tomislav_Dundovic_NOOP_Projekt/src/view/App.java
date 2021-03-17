package view;
//Imports
import javax.swing.SwingUtilities;

/**
 * Klasa app
 * Glavni dio aplikacije
 * @author tomislav
 * @since ozujak, 2021.
 */
public class App {
	
public static void main(String[] args) {
	
	SwingUtilities.invokeLater(new Runnable() {
		
		@Override
		public void run() {
			new AppProzor();
			
		}
	});
}

}
