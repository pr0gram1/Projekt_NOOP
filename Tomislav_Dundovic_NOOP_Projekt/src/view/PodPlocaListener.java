package view;
//Imports
import java.util.EventListener;

/**
 * 
 * Interface PodPlocaListener koji extenda EventListener
 * @author tomislav
 * @since ozujak, 2021.
 *
 */

public interface PodPlocaListener extends EventListener {
	
	public void podPlocaEventOccured(PodPlocaEvent podEvent);

}
