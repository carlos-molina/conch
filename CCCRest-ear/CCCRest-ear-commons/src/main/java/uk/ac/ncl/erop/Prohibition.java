package uk.ac.ncl.erop;


import java.util.Date;


/**
 * The Class Prohibition.
 * Instances of this class are prohibitions granted to role players.
 *
 */
public class Prohibition extends ROPEntity {
	
	/**
	 * Instantiates a new prohibition.
	 *
	 * @param bo the bo
	 * @param deadline the deadline
	 */
	public Prohibition(BusinessOperation bo, Date deadline) {
		super(bo, deadline);
		ropType = ROPEntity.PROHIBITION;
	}

	/**
	 * Instantiates a new prohibition.
	 *
	 * @param bo the bo
	 * @param deadline the deadline
	 */
	public Prohibition(BusinessOperation bo, String deadline) {
		super(bo, deadline);
		ropType = ROPEntity.PROHIBITION;
	}

}
