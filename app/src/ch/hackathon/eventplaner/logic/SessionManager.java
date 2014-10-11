package ch.hackathon.eventplaner.logic;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<User> getAllUsers() {
		
		List<User> listUsers = new ArrayList<User>();
		
		User dummy = new User();
		dummy.setId(1);
		dummy.setName("Dummy Muster");
		dummy.setEmail("dummy.muster@example.com");
		
		User dummy1 = new User();
		dummy1.setId(2);
		dummy1.setName("Dummy Benj");
		dummy1.setEmail("dummy.Benj@example.com");
		
		User dummy2 = new User();
		dummy2.setId(3);
		dummy2.setName("Jan Dummy");
		dummy2.setEmail("jan.dummy@example.com");
		
		listUsers.add(dummy);
		listUsers.add(dummy1);
		listUsers.add(dummy2);
		
		return listUsers;
	}
}
