package uk.ac.ncl.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class CCCResponse implements Serializable{

	private static final long serialVersionUID = 2989890107617783379L;
	private boolean isContractCompliant;
	private String sequenceId;

	public CCCResponse( String sequenceId, boolean isContractCompliant) {
		super();
		this.sequenceId = sequenceId;
		this.setContractCompliant(isContractCompliant);
	}

	/**
	 * 
	 */
	public CCCResponse() {
		// TODO Auto-generated constructor stub
	}

	@XmlElement
	public String getSequenceId() {
		return this.sequenceId;
	}

	public void setSequenceId(String sequenceId){
		this.sequenceId = sequenceId;
	}


	@XmlElement
	public boolean getContractCompliant() {
		return isContractCompliant;
	}

	public void setContractCompliant(boolean isContractCompliant) {
		this.isContractCompliant = isContractCompliant;
	}


	@Override
	public String toString() {
		return "CCCResponse{" +
				"isContractCompliant=" + isContractCompliant +
				", sequenceId='" + sequenceId + '\'' +
				'}';
	}
}
