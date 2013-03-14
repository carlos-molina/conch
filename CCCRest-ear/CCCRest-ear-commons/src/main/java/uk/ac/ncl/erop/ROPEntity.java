/*
 *
 */
package uk.ac.ncl.erop;


import java.util.Date;


/**
 * The Class ROPEntity.
 *
 * Instances of this class are entities stored in ROP Sets:
 * Rights, Obligations, Prohibitions
 *
 */
public class ROPEntity {

	// Static type definers
	public static int UNDEFINED = 0;
	public static int RIGHT = 1;
	public static int OBLIGATION = 2;
	public static int PROHIBITION = 3;

	protected static String[] ropTypeNames = {"Undefined", "Right",
											"Obligation", "Prohibition"};

	// Business operation type
	protected BusinessOperation bo = null;
	// Deadline for the expiry; null if no expiry
	protected Date deadline = null;
	// Type of ROP Entity
	protected int ropType = UNDEFINED;


	/**
	 * Instantiates a new rOP entity.
	 *
	 * This constructor is used for composite versions of
	 * ROP entities, and should not be used by the simple
	 * (not composite) ones.
	 *
	 * @param deadline the deadline
	 */
	public ROPEntity(Date deadline) {
		bo = null;
		this.deadline = deadline;
	}


	/**
	 * Instantiates a new rOP entity.
	 *
	 * Standard protected constructor, with deadline passed as Date
	 * This is not an object normally constructed by the programmer
	 *
	 * @param bo the bo
	 * @param deadline the deadline
	 */
	public ROPEntity(BusinessOperation bo, Date deadline) {
		// Verify validity of arguments
		// deadline *can* be null (no deadline) so not checked
		if(bo==null)
			throw new IllegalArgumentException("Null BusinessOperation received");
				// TODO: verify that deadline makes sense
		this.bo = bo;
		this.deadline = deadline;
	}


	/**
	 * Instantiates a new rOP entity.
	 *
	 * Standard protected constructor, with deadline passed as String
	 * This is not an object normally constructed by the programmer
	 *
	 * @param bo the bo
	 * @param d the d
	 */
	public ROPEntity(BusinessOperation bo, String d) {
		// Verify validity of arguments
		// deadline *can* be null (no deadline) so not checked
		if(bo==null)
			throw new IllegalArgumentException("Null BusinessOperation received");
				// TODO: verify that deadline makes sense
		if(d==null)
			this.deadline = null;
		else {
			try {
				Date dl = DateParser.parse(d);
				this.deadline = dl;
			} catch (Exception e) {
				throw new IllegalArgumentException("Unacceptable deadline received");
			}
		}
		this.bo = bo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		// Figure out deadline representation
		String s = null;
		if(deadline==null)
			s = new String("none, ");
		else
			s = new String(DateParser.format(deadline)+", ");
		// Build the string representation for the ROP Entity
		return new String("ROPEntity - BO Type: "+bo+", Deadline: "+s
				+"ROP Type: "+ropTypeNames[ropType]);
	}

	/**
	 * Gets the rop type.
	 *
	 * @return the rop type
	 */
	public int getRopType() {
		return ropType;
	}

	/**
	 * Gets the rop type name.
	 *
	 * @return the rop type name
	 */
	public String getRopTypeName() {
		return new String(ropTypeNames[ropType]);
	}

	/**
	 * Gets the rop name.
	 *
	 * @return the rop name
	 */
	public String getRopName() {
		return bo.toString();
	}

	/**
	 * Gets the bo.
	 *
	 * @return the bo
	 */
	public BusinessOperation getBO() {
		return bo;
	}

	/**
	 * Gets the deadline.
	 *
	 * @return the deadline
	 */
	public Date getDeadline() {
		return deadline;
	}

	/**
	 * Matches.
	 *
	 * @param businessop the businessop
	 * @return true, if successful
	 */
	public boolean matches(BusinessOperation businessop) {
		return businessop.equals(bo);
	}

	/**
	 * Checks if is composite.
	 *
	 * @return true, if is composite
	 */
	public boolean isComposite() {
		return false;
	}

	/**
	 * Equals.
	 *
	 * @param rop the rop
	 * @return true, if successful
	 */
	public boolean equals(ROPEntity rop) {
		if(rop==null)
			return false;
		Date secondDline = rop.getDeadline();
		// Compare the two ROP Entities: first check if the argument is a
		// composite rop entity. If it is, return false:
		// this comparison can only succeed for non-composite rop entities
		if(rop.isComposite())
			return false;
		else {
			// Verify that bo type and rop type also match
			if((bo.equals(rop.getBO()))&&(ropType==rop.getRopType())){
				// Verify deadlines: either both null, or both equal
				if(
						((deadline==null)&&(secondDline==null))
							||((deadline!=null)&&(deadline.equals(secondDline)))
				)
				return true;
			}
		}
		return false;
	}

	/**
	 * Reset.
	 */
	public void reset() {
		this.bo = null;
		this.deadline = null;
	}



}
