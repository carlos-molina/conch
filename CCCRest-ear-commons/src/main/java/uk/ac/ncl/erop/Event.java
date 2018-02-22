package uk.ac.ncl.erop;

import java.util.Date;
import java.text.DateFormat;

/**
 * The Class Event. Instances of this class represent (composite) events.
 * Instances of this class represent (composite) events.
 *
 */
public class Event {

	// / Private data
	private String sequenceId = null;
	// The role player that originated the event
	private String originator = null;
	// The role player that responded to the event
	private String responder = null;
	// Timestamp
	private Date timestamp = null;
	// The type of an event, represented as a string
	private String type = null;
	// The status of an event
	private String status = null;

	/**
	 * Instantiates a new event.
	 *
	 * This constructor generates an event setting its timestamp so that it
	 * represents the time of initialization.
	 *
	 * @param originator
	 *            the originator
	 * @param responder
	 *            the responder
	 * @param type
	 *            the type
	 * @param status
	 *            the status
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 */
	public Event(String sequenceId, String originator, String responder, String type, String status) throws IllegalArgumentException {
		// Check for the validity of the parameters
		if ((originator == null) || (originator.length() == 0))
			throw new IllegalArgumentException("Originator is null or empty");
		if ((responder == null) || (responder.length() == 0))
			throw new IllegalArgumentException("Responder is null or empty");
		if ((type == null) || (type.length() == 0))
			throw new IllegalArgumentException("Type is null or empty");
		if ((status == null) || (status.length() == 0))
			throw new IllegalArgumentException("Status is null or empty");
		// Arguments are acceptable, use them.
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
		timestamp = new Date();
	}

	/**
	 * Instantiates a new event.
	 *
	 * This constructor generates an event with a given timestamp, specified
	 * with a Date argument.
	 *
	 * @param originator
	 *            the originator
	 * @param responder
	 *            the responder
	 * @param type
	 *            the type
	 * @param status
	 *            the status
	 * @param d
	 *            the date timestamp
	 */
	public Event(String sequenceId, String originator, String responder, String type, String status, Date d) {
		// Check for the validity of the parameters
		if ((originator == null) || (originator.length() == 0))
			throw new IllegalArgumentException("Originator is null or empty");
		if ((responder == null) || (responder.length() == 0))
			throw new IllegalArgumentException("Responder is null or empty");
		if ((type == null) || (type.length() == 0))
			throw new IllegalArgumentException("Type is null or empty");
		if ((status == null) || (status.length() == 0))
			throw new IllegalArgumentException("Status is null or empty");
		if (d == null)
			throw new IllegalArgumentException("Timestamp is null");
		// Arguments are acceptable, use them.
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
		timestamp = d;
	}

	/**
	 * Instantiates a new Reset event with message type reset.
	 *
	 * @param type the type
	 */
	public Event(String type) {
		// Check for the validity of the parameters

		if ((type == null) || (type.length() == 0))
			throw new IllegalArgumentException("Type is null or empty");

		this.type = type;

	}

	/**
	 * Instantiates a new event.
	 *
	 * This constructor generates an event with a given timestamp, specified
	 * with a String argument.
	 *
	 * @param originator
	 *            the originator
	 * @param responder
	 *            the responder
	 * @param type
	 *            the type
	 * @param status
	 *            the status
	 * @param d
	 *            the date in string format
	 */
	public Event(String sequenceId, String originator, String responder, String type, String status, String d) {
		// Check for the validity of the parameters
		if ((originator == null) || (originator.length() == 0))
			throw new IllegalArgumentException("Originator is null or empty");
		if ((responder == null) || (responder.length() == 0))
			throw new IllegalArgumentException("Responder is null or empty");
		if ((type == null) || (type.length() == 0))
			throw new IllegalArgumentException("Type is null or empty");
		if ((status == null) || (status.length() == 0))
			throw new IllegalArgumentException("Status is null or empty");
		if (d == null)
			throw new IllegalArgumentException("Timestamp is null");
		// Arguments are acceptable, use them.
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
		try {
			timestamp = DateParser.parse(d);
		} catch (Exception e) {
			throw new IllegalArgumentException("Timestamp string cannot be parsed");
		}
	}

	/**
	 * Gets the originator.
	 *
	 * @return the originator
	 */
	public String getOriginator() {
		if (originator==null){
			return new String("");
		} else {
			return new String(originator);
		}

	}

	/**
	 * Gets the responder.
	 *
	 * @return the responder
	 */
	public String getResponder() {
		if (responder==null) {
			return new String("");
		} else {
			return new String(responder);
		}

	}

	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */

	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * Before. Returns true if the timestamp of this event is strictly before
	 * the timestamp of e
	 *
	 * @param e
	 *            the e
	 * @return true, if successful
	 */
	public boolean before(Event e) {
		if (e == null)
			throw new IllegalArgumentException("Supplied event is null");
		Date t = e.getTimestamp();
		return timestamp.before(t);
	}

	/**
	 * Before.
	 *
	 * Returns true if the timestamp of this event is strictly before Date t
	 *
	 * @param t
	 *            the t
	 * @return true, if successful
	 */
	public boolean before(Date t) {
		if (t == null)
			throw new IllegalArgumentException("Supplied timestamp is null");
		return timestamp.before(t);
	}

	/**
	 * Before.
	 *
	 * Returns true if the timestamp of this event is strictly before the date
	 * represented by the string s
	 *
	 * @param s
	 *            the date in string format
	 * @return true, if successful
	 */
	public boolean before(String s) {
		if (s == null)
			throw new IllegalArgumentException("Supplied timestamp is null");
		Date t = DateParser.parse(s);
		if (t == null)
			throw new IllegalArgumentException("Supplied timestamp string is meaningless");
		return timestamp.before(t);
	}

	/**
	 * After.
	 *
	 * Returns true if the timestamp of this event is strictly after Date t
	 *
	 * @param t
	 *            the t
	 * @return true, if successful
	 */
	public boolean after(Date t) {
		if (t == null)
			throw new IllegalArgumentException("Timestamp is null");
		return timestamp.after(t);
	}

	/**
	 * After.
	 *
	 * Returns true if the timestamp of this event is strictly after Event t
	 *
	 * @param e
	 *            the e
	 * @return true, if successful
	 */
	public boolean after(Event e) {
		if (e == null)
			throw new IllegalArgumentException("Timestamp is null");
		Date t = e.getTimestamp();
		return timestamp.after(t);
	}

	/**
	 * After. Returns true if the timestamp of this event is strictly after the
	 * date represented by the string s
	 *
	 * @param s
	 *            the s
	 * @return true, if successful
	 */
	public boolean after(String s) {
		if (s == null)
			throw new IllegalArgumentException("Supplied timestamp is null");
		Date t = DateParser.parse(s);
		if (t == null)
			throw new IllegalArgumentException("Supplied timestamp string is meaningless");
		return timestamp.after(t);
	}

	/**
	 * Compare timestamp.
	 *
	 * Returns 0 if the timestamp of this event is the same of the one of e, <0
	 * if it is before e, >0 if it is after e.
	 *
	 * @param e
	 *            the e
	 * @return the int
	 */
	public int compareTimestamp(Event e) {
		if (e == null)
			throw new IllegalArgumentException("Supplied event is null");
		return timestamp.compareTo(e.getTimestamp());
	}

	/**
	 * Compare timestamp.
	 *
	 * Returns 0 if the timestamp of this event equals t, <0 if it is before t,
	 * >0 if it is after t.
	 *
	 * @param t
	 *            the t
	 * @return the int
	 */
	public int compareTimestamp(Date t) {
		if (t == null)
			throw new IllegalArgumentException("Timestamp is null");
		return timestamp.compareTo(t);
	}

	/**
	 * Compare timestamp.
	 *
	 * Returns 0 if the timestamp of this event equals t, <0 if it is before t,
	 * >0 if it is after t.
	 *
	 * @param s
	 *            the s
	 * @return the int
	 */
	public int compareTimestamp(String s) {
		if (s == null)
			throw new IllegalArgumentException("Timestamp is null");
		Date t = DateParser.parse(s);
		if (t == null)
			throw new IllegalArgumentException("Supplied timestamp string is meaningless");
		return timestamp.compareTo(t);
	}

	/**
	 * Gets the type. Return the type of the event.
	 *
	 * @return the type
	 */
	public String getType() {
		if (type == null)
			throw new IllegalArgumentException("message type is null");
		else
			return new String(type);
	}

	/**
	 * Gets the status. Return the status of an event.
	 *
	 * @return the status
	 */
	public String getStatus() {
		if (status== null )
			return new String("");
		else
			return new String(status);
	}



	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	@Override
	public String toString() {
		return "Event{" +
				"sequenceId='" + sequenceId + '\'' +
				", originator='" + originator + '\'' +
				", responder='" + responder + '\'' +
				", timestamp=" + timestamp +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
