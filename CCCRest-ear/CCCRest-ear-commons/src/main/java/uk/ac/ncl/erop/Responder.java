package uk.ac.ncl.erop;

/**
 * The Class Responder.
 * 
 * @author <a href="mailto:giannis.sfyrakis@cazoomi.com">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 7 $$, $$Date: 2012-09-05 23:21:41 +0100 (Wed, 05 Sep
 *          2012) $$
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
