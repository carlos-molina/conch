package uk.ac.ncl.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "event")
public class BusinessEvent implements Serializable {

	private static final long serialVersionUID = 1L;
	private String sequenceId;
	private String originator;
	private String responder;
	private String type;
	private String status;
	//String timeStamp;

	private  String id;

	public BusinessEvent(){

	}

	/**
	 * @param originator
	 * @param responder
	 * @param type
	 * @param status
	 * @param id
	 */
	public BusinessEvent(String sequenceId, String originator, String responder, String type, String status, String id) {
		super();
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
		this.id = id;
	}

	/**
	 * Instantiates a new business event.
	 *
	 * @param type the type
	 */
	public BusinessEvent( String type) {
		super();

		this.type = type;

	}


	/**
	 *
	 * Gets the sequenceId generated form epromela contract
	 *
	 * @return
	 */
	@XmlElement
	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
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
	 * @param origianator the new originator
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "EventDTO [" + (originator != null ? "originator=" + originator + ", " : "")
				+ (responder != null ? "responder=" + responder + ", " : "")
				+ (type != null ? "type=" + type + ", " : "") + (status != null ? "status=" + status + ", " : "")
				+ (id != null ? "id=" + id : "") + "]";
	}


}
