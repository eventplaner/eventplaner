package ch.hackathon.eventplaner.logic;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import ch.hackathon.eventplaner.data.User;

/**
 * Handle User sessions
 */
public class SessionManager {
	private Context currentContext; // Appcontext
	
	/**
	 * Create a new Sessionmanager instance
	 * @param context the appcontext
	 */
	public SessionManager(Context context) {
		currentContext = context;
	}
	
	/**
	 * Checks if the user in front of the devices is logged in on the API - Server
	 * @return TRUE if the user is authenticated successfull
	 */
	public boolean isUserLoggedIn() {
		//TODO: Implement!
		return true;
	}
	
	/**
	 * Get the user that is currently logged in into the device
	 * @return the user as user object
	 */
	public User getUser() {
		//TODO: Implement!
		User dummy = new User();
		dummy.setId(1);
		dummy.setName("Dummy Muster");
		dummy.setEmail("dummy.muster@example.com");
		return dummy;
	}
	
	/**
	 * @return a list of all Users
	 */
	public List<User> getAllUsers() {
		// Call the API to the the events
		ApiConnector api = new ApiConnector();
		JSONArray result = api.getJsonArrayFromGet("/user", currentContext);
		List<User> userlist = new ArrayList<User>();
		
		for (int i = 0 ; i < result.length(); i++) {
			try {
				JSONObject jsonUser = result.getJSONObject(i);
				User user = new User();
				user.setId(jsonUser.getInt("id"));
				user.setName(jsonUser.getString("name"));
				user.setActive(jsonUser.getBoolean("active"));
				user.setEmail(jsonUser.getString("email"));
				user.setTelnumber(jsonUser.getString("telnumber"));
				userlist.add(user);
			} catch (JSONException e) {
				// Just ignore it...
			}
		}
		return userlist;
	}
}
