package logassignment.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import logassignment.domain.Event;

/**
 * This service is used for calculating duration against eventIds and flagging
 * them if consuming more than 4ms.
 */
public class LogDurationService {

	// stores filepath for logs
	private String logFilepath;

	// stores events with respect to eventids.
	// <eventId, ArrayList<Event>)
	private Map eventMapById;

	// stores eventid with respect to duration of time taken.
	// <eventId, Integer (millisec.)>
	private Map durationMapById;

	public LogDurationService() {
		eventMapById = new HashMap();
		durationMapById = new HashMap<>();
	}

	public String getLogFilepath() {
		return logFilepath;
	}

	public void setLogFilepath(String logFilepath) {
		this.logFilepath = logFilepath;
	}

	public void readLogFileContents() {
		Path path = Paths.get(this.logFilepath);

		try (Stream<String> stream = Files.lines(path)) {

			stream.forEach((eventJsonString) -> {
				if (!eventJsonString.isBlank()) {
					Event event = this.parseEvent(eventJsonString);
					if (null == eventMapById.get(event.getId())) {
						eventMapById.put(event.getId(), new ArrayList());
					}

					ArrayList<Event> eventListById = (ArrayList<Event>) eventMapById.get(event.getId());
					eventListById.add(event);
					eventMapById.put(event.getId(), eventListById);

				}
			});

		} catch (IOException e) {
			System.out.println("IOException : " + e);
		}

	}

	public void calculateDuration() {
		eventMapById.forEach((key, value) -> {
			ArrayList eventListById = (ArrayList) value;

			// sort events with respect to list of timestamps
			Collections.sort(eventListById, (Event e1, Event e2) -> {
				if (e1.getReadableTimestamp().isBefore(e2.getReadableTimestamp())) {
					return -1;
				} else {
					return 1;
				}
			});

			// calculate duration taken
			LocalDateTime l1 = ((Event) eventListById.get(0)).getReadableTimestamp();
			LocalDateTime l2 = ((Event) eventListById.get(1)).getReadableTimestamp();
			Duration duration = Duration.between(l1, l2);

			// store duration against eventid
			durationMapById.put(key, duration.toMillis());
		});
	}

	private Event parseEvent(String eventJsonString) {
		ObjectMapper mapper = new ObjectMapper();
		Event event = null;
		try {
			event = mapper.readValue(eventJsonString, Event.class);
			event.setReadableTimestamp();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return event;
	}

	public void printEventDurations() {
		// print eventid, duration and true if more than 4ms
		durationMapById.forEach((key, value) -> {
			System.out.println();
			System.out.print(key + " : " + value);
			if (Integer.valueOf(value.toString()) >= 4) {
				System.out.print(" true");
			} else {
				System.out.print(" false");
			}
		});
	}

}
