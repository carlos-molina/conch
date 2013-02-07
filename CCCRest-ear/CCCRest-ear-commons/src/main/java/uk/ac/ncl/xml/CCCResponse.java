package uk.ac.ncl.xml;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class CCCResponse implements Serializable{

	
	private static final long serialVersionUID = 2989890107617783379L;
	
	private boolean isContractCompliant;

	public CCCResponse(boolean isContractCompliant) {
		super();
		this.setContractCompliant(isContractCompliant);
	}

	/**
	 * 
	 */
	public CCCResponse() {
		// TODO Auto-generated constructor stub
	}

	@XmlElement
	public boolean getContractCompliant() {
		return isContractCompliant;
	}

	public void setContractCompliant(boolean isContractCompliant) {
		this.isContractCompliant = isContractCompliant;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CCCResponse [isContractCompliant=").append(isContractCompliant).append("]");
		return builder.toString();
	}
	
	

}
