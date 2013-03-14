package uk.ac.ncl.erop;

/**
 * The Class Responder.
 *
 */

public class Responder {
	private Boolean isContractCompliant = false;

	public Responder() {

	}

	public Responder(boolean contractCompliant) {
		this.isContractCompliant = contractCompliant;

	}

	public void setContractCompliant(Boolean isContractCompliant) {
		this.isContractCompliant = isContractCompliant;
	}

	public Boolean getContractCompliant() {
		return this.isContractCompliant;

	}

}
