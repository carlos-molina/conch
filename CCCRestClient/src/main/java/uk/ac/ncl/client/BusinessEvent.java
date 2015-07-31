package uk.ac.ncl.client;
/**
 *
 */

import java.io.Serializable;
import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;


/**
 * The Class BusinessEvent provides an entity for eventhistory database table.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: b1049501 $$
 * @version $$Revision: 398 $$, $$Date: 2012-09-25 16:59:05 +0100 (Tue, 25 Sep 2012) $$
 */

@XmlRootElement(name = "event")
public class BusinessEvent implements Serializable {


	private static final long serialVersionUID = 1L;
	private String sequenceId;
	private Long id;
	private String originator;
	private String responder;
	private Date timestamp;
	private String type;
	private String status;


	public BusinessEvent(){

	}
	/**
	 * @param originator
	 * @param responder
	 * @param type
	 * @param status
	 */
	public BusinessEvent(String sequenceId, String originator, String responder, String type, String status) {
		super();
		this.sequenceId = sequenceId;
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;

	}


	public BusinessEvent(String type) {
		super();
		this.type = type;
	}

	public Long getId() {
		return id;
	}


	/**
	 *
	 * @return sequenceId the id for a particular sequence generated from epromela
	 */
	@XmlElement(name = "sequenceId")
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	/**
	 * @return the originator
	 */
	@XmlElement(name = "originator")
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	public String getOriginator() {
		return originator;
	}

	/**
	 * @param originator
	 *            the originator to set
	 */
	public void setOriginator(String originator) {
		this.originator = originator;
	}

	/**
	 * @return the responder
	 */
	@XmlElement(name = "responder")
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	public String getResponder() {
		return responder;
	}

	/**
	 * @param responder
	 *            the responder to set
	 */
	public void setResponder(String responder) {
		this.responder = responder;
	}

	/**
	 * @return the timestamp
	 */
	public Date getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp
	 *            the timestamp to set
	 */
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the type
	 */
	@XmlElement(name = "type")
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the status
	 */
	@XmlElement(name = "status")
	@XmlJavaTypeAdapter(NormalizedStringAdapter.class)
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "BusinessEvent{" +
				"sequenceId='" + sequenceId + '\'' +
				", id=" + id +
				", originator='" + originator + '\'' +
				", responder='" + responder + '\'' +
				", timestamp=" + timestamp +
				", type='" + type + '\'' +
				", status='" + status + '\'' +
				'}';
	}
}
