/*
 * 
 */
package uk.ac.ncl.erop;

// Instances of this class are obligations granted to role players.

import java.util.Date;


/**
 * The Class Obligation.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
public class Obligation extends ROPEntity {
	
	/**
	 * Instantiates a new obligation.
	 *
	 * @param bo the business operation
	 * @param deadline the date of the deadline
	 */
	public Obligation(BusinessOperation bo, Date deadline) {
		super(bo, deadline);
		// Verify deadline: it cannot be null for an obligation
		if(deadline==null)
			throw new IllegalArgumentException("Deadline cannot be null for obligations");
		// Set the type of ROP entity
		ropType = ROPEntity.OBLIGATION;
	}

	/**
	 * Instantiates a new obligation.
	 *
	 * @param bo the business operation
	 * @param deadline the deadline in String
	 */
	public Obligation(BusinessOperation bo, String deadline) {
		super(bo, deadline);
		// Verify deadline: it cannot be null for an obligation
		if(deadline==null)
			throw new IllegalArgumentException("Deadline cannot be null for obligations");
		// Set the type of ROP entity
		ropType = ROPEntity.OBLIGATION;
	}
	
	/**
	 * Instantiates a new obligation.
	 *
	 * @param deadline the date of the deadline
	 */
	public Obligation(Date deadline) {
		super(deadline);
		ropType = ROPEntity.OBLIGATION;
	}
}
