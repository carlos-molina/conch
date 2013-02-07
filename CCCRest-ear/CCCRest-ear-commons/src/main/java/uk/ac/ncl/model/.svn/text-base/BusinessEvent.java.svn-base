/**
 *
 */
package uk.ac.ncl.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.hibernate.annotations.GenericGenerator;

/**
 * The Class BusinessEvent provides an entity for eventhistory database table.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
@Entity
@Table(name = "eventhistory")
@XmlRootElement(name = "event")
public class BusinessEvent implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Long id;

	@Column(name = "originator")//, columnDefinition = "VARCHAR(40)")
	private String originator;

	@Column(name = "responder")//, columnDefinition = "VARCHAR(40)")
	private String responder;

	@Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;

	@Column(name = "type")//, columnDefinition = "VARCHAR(40)")
	private String type;

	@Column(name = "status")//, columnDefinition = "VARCHAR(40)")
	private String status;


	public BusinessEvent(){

	}
	/**
	 * @param originator
	 * @param responder
	 * @param type
	 * @param status
	 */
	public BusinessEvent(String originator, String responder, String type, String status) {
		super();
		this.originator = originator;
		this.responder = responder;
		this.type = type;
		this.status = status;
	}

	public Long getId() {
		return id;
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

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BusinessEvent [");
		if (id != null)
			builder.append("id=").append(id).append(", ");
		if (originator != null)
			builder.append("originator=").append(originator).append(", ");
		if (responder != null)
			builder.append("responder=").append(responder).append(", ");
		if (timestamp != null)
			builder.append("timestamp=").append(timestamp).append(", ");
		if (type != null)
			builder.append("type=").append(type).append(", ");
		if (status != null)
			builder.append("status=").append(status);
		builder.append("]");
		return builder.toString();
	}

}
