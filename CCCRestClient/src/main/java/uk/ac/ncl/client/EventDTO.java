package uk.ac.ncl.client;



import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * The Class Event.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: b1049501 $$
 * @version $$Revision: 393 $$, $$Date: 2012-09-12 13:06:33 +0100 (Wed, 12 Sep 2012) $$
 */
@XmlRootElement(name = "event")
public class EventDTO implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private  String sequenceId;
	private String originator;
	private String responder;
	private String type;
	private String status;
	//String timeStamp;

	private  String id;

	public EventDTO(){

	}

	/**
	 * @param originator
	 * @param responder
	 * @param type
	 * @param status
	 * @param id
	 */
	public EventDTO(String sequenceId, String originator, String responder, String type, String status, String id) {
		super();
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
		this.id = id;
	}



	/**
	 * Gets the originator.
	 *
	 * @return the originator
	 */
	@XmlElement
	public String getOriginator() {
		return originator;
	}

	/**
	 * Sets the originator.
	 *
	 * @param originator the new originator
	 */
	public void setOriginator(String originator) {
		this.originator = originator;
	}

	/**
	 * Gets the responder.
	 *
	 * @return the responder
	 */
	@XmlElement
	public String getResponder() {
		return responder;
	}

	/**
	 * Sets the responder.
	 *
	 * @param responder the new responder
	 */
	public void setResponder(String responder) {
		this.responder = responder;
	}



	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	@XmlElement
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	@XmlElement
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

//	/**
//	 * Gets the time stamp.
//	 *
//	 * @return the timeStamp
//	 */
//	@XmlElement
//	public String getTimeStamp() {
//		return timeStamp;
//	}
//
//	/**
//	 * Sets the time stamp.
//	 *
//	 * @param timeStamp the timeStamp to set
//	 */
//	public void setTimeStamp(String timeStamp) {
//		this.timeStamp = timeStamp;
//	}

	@XmlAttribute
	public String getEventId() {
		return id;
	}

	public void setEventId(String id) {
		this.id = id;
	}


	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	@Override
	public String toString() {
		return "EventDTO{" +
				"sequenceId='" + sequenceId + '\'' +
				", originator='" + originator + '\'' +
				", responder='" + responder + '\'' +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				", id='" + id + '\'' +
				'}';
	}
}