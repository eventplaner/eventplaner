package ch.hackathon.eventplaner.logic;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.text.format.DateFormat;
import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.data.Participant;

public class EventManager {
	private Context context;
	
	public EventManager (Context appcontext) {
		context = appcontext;
	}

	public List<Event> getEventsForMainPage() {
		List<Event> eventlist = new ArrayList<Event>();
		
		// Call the API to the the events
		ApiConnector api = new ApiConnector();
		JSONArray result = api.getJsonArrayFromGet("/event", context);
		
		for (int i = 0 ; i < result.length(); i++) {
			try {
				JSONObject jsonEvent = result.getJSONObject(i);
				Event event = new Event(context);
				event.setId(jsonEvent.getInt("id"));
				event.setName(jsonEvent.getString("name"));
				event.setDescription(jsonEvent.getString("description"));
				String startDate = jsonEvent.getString("start");
				String endDate = jsonEvent.getString("end");
				String createdate = jsonEvent.getString("createdate");
				String changeDate = jsonEvent.getString("changeDate");
				event.setStart(jsonParser(startDate));
				event.setEnd(jsonParser(endDate));
				event.setCreateDate(jsonParser(createdate));
				event.setChangeDate(jsonParser(changeDate));
				event.setPosition_latitude(jsonEvent.getString("position_latitude"));
				event.setPosition_longitude(jsonEvent.getString("position_longitude"));
				event.setCreateuser_id(jsonEvent.getInt("createuser_id"));
				eventlist.add(event);
			} catch (JSONException e) {
				// Just ignore it...
			}
		}
		return eventlist;
	}
	
	public Event getEventById(int id) {
		ApiConnector api = new ApiConnector();
		JSONObject result = api.getJsonObjFromGet("/event/" + id, context);
		try {
			Event event = new Event(context);
			event.setId(result.getInt("id"));
			event.setName(result.getString("name"));
			event.setDescription(result.getString("description"));
			String startDate = result.getString("start");
			String endDate = result.getString("end");
			String createdate = result.getString("createdate");
			String changeDate = result.getString("changeDate");
			event.setStart(jsonParser(startDate));
			event.setEnd(jsonParser(endDate));
			event.setCreateDate(jsonParser(createdate));
			event.setChangeDate(jsonParser(changeDate));
			event.setPosition_latitude(result.getString("position_latitude"));
			event.setPosition_longitude(result.getString("position_longitude"));
			event.setCreateuser_id(result.getInt("createuser_id"));
			return event;
		} catch (JSONException e) {
			// Just ignore it ...
		}
		return null;
	}
	

	/**
	 * Saves changes of a event to the database / saves a new event
	 * (if changedEvent.id == -1 -> new event)
	 * @param changedEvent
	 * @return error code (0 if everything works fine)
	 */
	public int saveEvent (Event changedEvent) {
		ApiConnector api = new ApiConnector();
		List<NameValuePair> postData = new ArrayList<NameValuePair>();
		postData.add(new BasicNameValuePair("id", String.valueOf(changedEvent.getId())));
		postData.add(new BasicNameValuePair("name", changedEvent.getName()));
		postData.add(new BasicNameValuePair("description", changedEvent.getDescription()));
		postData.add(new BasicNameValuePair("start", changedEvent.getLocalisedStartDate(context)));
		postData.add(new BasicNameValuePair("end", changedEvent.getLocalisedEndDate(context)));
		postData.add(new BasicNameValuePair("createdate", changedEvent.getLocalisedCreateDate(context)));
		postData.add(new BasicNameValuePair("changedate", changedEvent.getLocalisedChangeDate(context)));
		
		postData.add(new BasicNameValuePair("position_latitude", changedEvent.getPosition_latitude()));
		postData.add(new BasicNameValuePair("position_longitude", changedEvent.getPosition_longitude()));
		SessionManager sessionManager = new SessionManager(context);
		postData.add(new BasicNameValuePair("createuser_id", String.valueOf(sessionManager.getUser().getId())));
		
		// TODO: Implement the other fields
		HttpResponse result = api.sendPostRequest("/event/save", postData, context);
		// TODO: Implement error checking....
		return 0;
	}
	
	public List<Participant> getParticipantsOfEvent(Event event) {
		List<Participant> participants = new ArrayList<Participant>();
		ApiConnector api = new ApiConnector();
		JSONArray result = api.getJsonArrayFromGet("/event/" + event.getId() + "/participants", context);
		
		for (int i = 0 ; i < result.length(); i++) {
			try {
				JSONObject jsonParticipant = result.getJSONObject(i);
				Participant participant = new Participant(context);
				participant.setId(jsonParticipant.getInt("id"));
				participant.setEvent_id(jsonParticipant.getInt("event_id"));
				participant.setUser_id(jsonParticipant.getInt("user_id"));
				participants.add(participant);
			}
			catch (JSONException e) {
				// Just ignore it ...
			}
		}
		return participants;
	}
	
	public int addParticipant(Participant participant) {
		//TODO: Implement with API
		ApiConnector api = new ApiConnector();
		JSONObject result = api.getJsonObjFromGet("/event/"+ participant.getEvent_id() + "/participant/" + participant.getId(), context);
		return 0;
	}
	
	public Boolean getCurrentUserstatusOfEvent(Event event) {
		// TODO: Implement
		return false;
	}
	
	public Date jsonParser(String jsonDate)
	{
		String date = jsonDate.substring(0, 9);
		String time = jsonDate.substring(11, 15);
		String completeDate = String.format("%s %s", date, time);
		SimpleDateFormat formatter = null;
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date newDate = (Date) formatter.parse(completeDate);
			return newDate;
		} catch (ParseException e) {
			return null;
		}
	}
}
