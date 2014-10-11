package ch.hackathon.eventplaner.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hackathon.eventplaner.data.Event;
import ch.hackathon.eventplaner.data.Participant;

public class EventManager {
	public List<Event> getEventsForMainPage() {
		List<Event> eventlist = new ArrayList<Event>();
		
		// TODO: Implement! (correctly)
		Event dummyEvent1 = new Event();
		dummyEvent1.setId(1);
		dummyEvent1.setName("Hackathon Zürich");
		dummyEvent1.setDescription("Great Hackathon");
		dummyEvent1.setStart(new Date());
		dummyEvent1.setEnd(new Date());
		dummyEvent1.setPosition_latitude("47.3843963");
		dummyEvent1.setPosition_longitude("8.5069717");
		eventlist.add(dummyEvent1);
		
		Event dummyEvent2 = new Event();
		dummyEvent2.setName("Great Party");
		dummyEvent2.setId(2);
		dummyEvent2.setDescription("Great Partyyyy!!");
		dummyEvent2.setStart(new Date());
		dummyEvent2.setEnd(new Date());
		dummyEvent2.setPosition_latitude("47.3843963");
		dummyEvent2.setPosition_longitude("8.5069717");
		eventlist.add(dummyEvent2);
		return eventlist;
	}
	
	public Event getEventById(int id) {
		// TODO: Implement! (correctly)
		if (id == 1) {
			Event dummyEvent1 = new Event();
			dummyEvent1.setId(1);
			dummyEvent1.setName("Hackathon Zürich");
			dummyEvent1.setDescription("Great Hackathon");
			dummyEvent1.setStart(new Date());
			dummyEvent1.setEnd(new Date());
			dummyEvent1.setPosition_latitude("47.3843963");
			dummyEvent1.setPosition_longitude("8.5069717");
			return dummyEvent1;
		}
		else if (id == 2) {
			Event dummyEvent2 = new Event();
			dummyEvent2.setName("Great Party");
			dummyEvent2.setId(2);
			dummyEvent2.setDescription("Great Partyyyy!!");
			dummyEvent2.setStart(new Date());
			dummyEvent2.setEnd(new Date());
			dummyEvent2.setPosition_latitude("47.3843963");
			dummyEvent2.setPosition_longitude("8.5069717");
			return dummyEvent2;
		}
		else {
			return null;
		}
	}
	
	/**
	 * Saves changes of a event to the database / saves a new event
	 * (if changedEvent.id == -1 -> new event)
	 * @param changedEvent
	 * @return error code (0 if everything works fine)
	 */
	public int saveEvent (Event changedEvent) {
		// TODO: Implement!!
		System.out.println("EVENT SAVED: " + changedEvent.getName());
		return 0;
	}
	
	public List<Participant> getParticipantsOfEvent(Event event) {
		// TODO: Implement!!
		List<Participant> participants = new ArrayList<Participant>();
		Participant demo1 = new Participant();
		demo1.setId(1);
		demo1.setEvent_id(event.getId());
		demo1.setUser_id(1);
		demo1.setStatus(true);
		participants.add(demo1);
		
		Participant demo2 = new Participant();
		demo2.setId(2);
		demo2.setEvent_id(event.getId());
		demo2.setUser_id(2);
		demo2.setStatus(true);
		participants.add(demo2);
		
		Participant demo3 = new Participant();
		demo3.setId(3);
		demo3.setEvent_id(event.getId());
		demo3.setUser_id(1);
		demo3.setStatus(false);
		participants.add(demo3);
		
		Participant demo4 = new Participant();
		demo4.setId(4);
		demo4.setEvent_id(event.getId());
		demo4.setUser_id(2);
		demo4.setStatus(true);
		participants.add(demo4);
		
		return participants;
	}
	
	
	public Boolean getCurrentUserstatusOfEvent(Event event) {
		// TODO: Implement
		return false;
	}
}
