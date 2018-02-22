package uk.ac.ncl.erop;

/**
 * The Class Responder.
 *
 */

public class Responder {
	private String sequenceId;
	private Boolean isContractCompliant = false;

	public Responder() {

	}

	public String getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(String sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Responder(String sequenceId, boolean contractCompliant) {
		this.sequenceId = sequenceId;
		this.isContractCompliant = contractCompliant;

	}

	public void setContractCompliant(Boolean isContractCompliant) {
		this.isContractCompliant = isContractCompliant;
	}

	public Boolean getContractCompliant() {
		return this.isContractCompliant;

	}

}
