package ch.hackathon.eventplaner.logic;

import android.content.Context;
import ch.hackathon.eventplaner.data.User;

/**
 * Handle User sessions
 */
public class SessionManager {
	private Context currentContext;
	
	public SessionManager(Context context) {
		currentContext = context;
	}
	
	public boolean isUserLoggedIn() {
		//TODO: Implement!
		return true;
	}
	
	public User getUser() {
		//TODO: Implement!
		User dummy = new User();
		dummy.setId(1);
		dummy.setName("Dummy Muster");
		dummy.setEmail("dummy.muster@example.com");
		return dummy;
	}
}
