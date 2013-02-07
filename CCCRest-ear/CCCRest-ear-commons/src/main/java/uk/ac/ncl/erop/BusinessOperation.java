/*
 *
 */
package uk.ac.ncl.erop;

/**
 * The Class BusinessOperation.
 *
 *	An instance of this class represents a type of business operation
 *	(eg, PurchaseOrder and so on), not the execution of a b.o.
 *	This is a skeleton, future versions will have additional features
 *	(eg, checks to prevent duplicate operations, etc)
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 10 $$, $$Date: 2012-09-25 17:00:29 +0100 (Tue, 25 Sep 2012) $$
 */
public class BusinessOperation {
	private String boName = null;

	private Boolean businessFailure = false;

	/**
	 * Instantiates a new business operation.
	 *
	 * @param name the name
	 */
	public BusinessOperation(String name) {
		// Check validity of argument
		// TODO: Check if Bo's name is acceptable?
		if ((name == null) || (name.length() == 0)) {
			throw new IllegalArgumentException("BO name is null or empty");
		}
		boName = new String(name);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new String(boName);
	}

	/**
	 * Equals.
	 *
	 * @param bo the bo
	 * @return true, if successful
	 */
	public boolean equals(BusinessOperation bo) {
		return boName.equalsIgnoreCase((bo.toString()));
	}

	/**
	 * @return the businessFailure
	 */
	public Boolean getBusinessFailure() {
		return businessFailure;
	}

	/**
	 * @param businessFailure the businessFailure to set
	 */
	public void setBusinessFailure(Boolean businessFailure) {
		this.businessFailure = businessFailure;
	}



}
