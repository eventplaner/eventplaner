package ch.hackathon.eventplaner.data;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import ch.hackathon.eventplaner.logic.ApiConnector;


public class Participant {
	private int id;
	private Boolean status;
	private int user_id;
	private int event_id;
	private User user;
	private Event event;
	private Context context;
	
	public Participant (Context appcontext) {
		context = appcontext;
	}
	
	public User getUser() {
		ApiConnector api = new ApiConnector();
		JSONObject result = api.getJsonObjFromGet("/user/" + id, context);
		try {
			User user = new User();
			user.setId(result.getInt("id"));
			user.setName(result.getString("name"));
			user.setActive(result.getBoolean("active"));
			user.setEmail(result.getString("email"));
			user.setTelnumber(result.getString("telnumber"));
			this.user = user;
			return user;
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Boolean isStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getEvent_id() {
		return event_id;
	}
	public void setEvent_id(int event_id) {
		this.event_id = event_id;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
