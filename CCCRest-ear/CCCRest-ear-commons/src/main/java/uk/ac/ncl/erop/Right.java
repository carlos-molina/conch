/*
 * 
 */
package uk.ac.ncl.erop;

// Instances of this class are rights granted to role players.

import java.util.Date;


/**
 * The Class Right.
 *
 */
public class Right extends ROPEntity {
	
	/**
	 * Instantiates a new right.
	 *
	 * @param bo the bo
	 * @param deadline the deadline
	 */
	public Right(BusinessOperation bo, Date deadline) {
		super(bo, deadline);
		ropType = ROPEntity.RIGHT;
	}

	/**
	 * Instantiates a new right.
	 *
	 * @param bo the bo
	 * @param deadline the deadline
	 */
	public Right(BusinessOperation bo, String deadline) {
		super(bo, deadline);
		ropType = ROPEntity.RIGHT;
	}
}
