package ch.hackathon.eventplaner.logic;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ch.hackathon.eventplaner.data.Event;

public class EventManager {
	public List<Event> getEventsForMainPage() {
		List<Event> eventlist = new ArrayList<Event>();
		
		// TODO: Implement! (correctly)
		Event dummyEvent1 = new Event();
		dummyEvent1.setName("Hackathon Zürich");
		dummyEvent1.setDescription("Great Hackathon");
		dummyEvent1.setStart(new Date());
		dummyEvent1.setEnd(new Date());
		dummyEvent1.setPosition_latitude("47.3843963");
		dummyEvent1.setPosition_longitude("8.5069717");
		eventlist.add(dummyEvent1);
		
		Event dummyEvent2 = new Event();
		dummyEvent2.setName("Great Party");
		dummyEvent2.setDescription("Great Partyyyy!!");
		dummyEvent2.setStart(new Date());
		dummyEvent2.setEnd(new Date());
		dummyEvent2.setPosition_latitude("47.3843963");
		dummyEvent2.setPosition_longitude("8.5069717");
		eventlist.add(dummyEvent2);
		return eventlist;
	}
}
