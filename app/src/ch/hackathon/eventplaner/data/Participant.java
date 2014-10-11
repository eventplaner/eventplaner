package ch.hackathon.eventplaner.data;

import ch.hackathon.eventplaner.logic.SessionManager;

public class Participant {
	private int id;
	private Boolean status;
	private int user_id;
	private int event_id;
	private User user;
	private Event event;
	
	
	public User getUser() {
		// TODO: Implement correctly
		User user = new User();
		user.setName("Test Tester");
		return user;
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
}
