package logassignment.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class Event {

	private String id;
	private String state;
	private String type;
	private String host;
	private String timestamp;
	private LocalDateTime readableTimestamp;

	public Event() {
		super();
	}

	public Event(String id, String state, String type, String host, String timestamp) {
		super();
		this.id = id;
		this.state = state;
		this.type = type;
		this.host = host;
		this.timestamp = timestamp;
//		Long temp = Long.valueOf(timestamp);
//		LocalDateTime ld= Instant.ofEpochMilli(temp).atZone(ZoneId.systemDefault()).toLocalDateTime();
//		this.readableTimestamp = ld;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public LocalDateTime getReadableTimestamp() {
		return readableTimestamp;
	}

	public void setReadableTimestamp() {
		Long timestampLong = Long.valueOf(this.timestamp);
		LocalDateTime ld= Instant.ofEpochMilli(timestampLong).atZone(ZoneId.systemDefault()).toLocalDateTime();
		this.readableTimestamp = ld;
	}

	@Override
	public String toString() {
		return "Event [id=" + id + ", state=" + state + ", type=" + type + ", host=" + host + ", timestamp=" + timestamp
				+ ", readableTimestamp=" + readableTimestamp + "]";
	}

}
