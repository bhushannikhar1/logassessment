package logassignment.domain;

import java.util.List;

public class EventList {

	private List<Event> eventList;

	public EventList() {
		super();
	}

	public EventList(List<Event> eventList) {
		super();
		this.eventList = eventList;
	}

	public List<Event> getEventList() {
		return eventList;
	}

	public void setEventList(List<Event> eventList) {
		this.eventList = eventList;
	}

	
	
}
