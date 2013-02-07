/*
 * 
 */
package uk.ac.ncl.erop;

import java.util.Date;


/**
 * The Class CompositeObligation.
 *
 * Instances of this class are composite obligations: obligations
 * for N business operations in disjunction with one deadline.
 * 
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
public class CompositeObligation extends Obligation {
	// An array of Business Operations in disjunction
	private BusinessOperation[] operations = null;
	private String name = null;
	
	/**
	 * Instantiates a new composite obligation.
	 *
	 * @param name the name
	 * @param ops the ops
	 * @param deadline the deadline
	 */
	public CompositeObligation(String name, BusinessOperation[] ops, Date deadline) {
		super(deadline);
		if((name==null)||(name.length()==0))
			throw new IllegalArgumentException("Name of Composite Obligation is null or empty");
		if((ops==null)||(ops.length==0))
			throw new IllegalArgumentException("List of Business Ops is null or empty");
		if(deadline==null) 
			throw new IllegalArgumentException("Deadline cannot be null for an obligation");
		operations = ops;
		this.name = new String(name);
	}

	/**
	 * Instantiates a new composite obligation.
	 *
	 * @param name the name
	 * @param ops the ops
	 * @param deadline the deadline
	 */
	public CompositeObligation(String name, BusinessOperation[] ops, String deadline) {
		super(DateParser.parse(deadline));
		if((name==null)||(name.length()==0))
			throw new IllegalArgumentException("Name of Composite Obligation is null or empty");
		if((ops==null)||(ops.length==0))
			throw new IllegalArgumentException("List of Business Ops is null or empty");
		if(deadline==null) 
			throw new IllegalArgumentException("Deadline cannot be null for an obligation");
		operations = ops;
		this.name = new String(name);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ncl.erop.ROPEntity#matches(uk.ac.ncl.erop.BusinessOperation)
	 */
	public boolean matches(BusinessOperation bo) {
		for (BusinessOperation operation : operations) {
			if(operation.equals(bo))
				return true;
		}
		return false;
	}
	
	/**
	 * Gets the b os.
	 *
	 * @return the b os
	 */
	public BusinessOperation[] getBOs() {
		return operations;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ncl.erop.ROPEntity#isComposite()
	 */
	public boolean isComposite() {
		return true;
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ncl.erop.ROPEntity#getRopName()
	 */
	public String getRopName() {
		return new String(name);
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ncl.erop.ROPEntity#toString()
	 */
	public String toString() {
		// Figure out deadline representation
		String s = new String(DateParser.format(deadline)+", ");
		// Build representation of the bos
		String bos = new String("[");
		for (BusinessOperation boi : operations) {
			bos = bos.concat(boi.toString()+", ");
		}
		bos = bos.concat("]");
		// Build the string representation
		return new String("ROPEntity - BO Types:"+bos+", Deadline: "+s
				+"ROP Type: Complex Obligation");		
	}
	
	/* (non-Javadoc)
	 * @see uk.ac.ncl.erop.ROPEntity#equals(uk.ac.ncl.erop.ROPEntity)
	 */
	public boolean equals(ROPEntity rop) {
		// The two ROP Entities are not equal if rop is not a composite oblig 
		if((rop==null)||(rop.getRopType()!=ROPEntity.OBLIGATION)
				||(!rop.isComposite()))
			return false;
		CompositeObligation co2 = (CompositeObligation)rop;
		Date secondDline = co2.getDeadline();
		// Compare the deadlines of the two composite obligs, and then 
		// the bos in the two Composite Obligs one by one
		if(deadline.equals(secondDline)) {
			// Everything matches until now. Check that the bos
			// have the same number of items
			if(operations.length!=co2.getBOs().length)
				return false;
			// They are the same length; check bos one by one
			for (BusinessOperation bo : operations) {
				// Equality is false as soon as we find a bo that does not match
				// the second composite obligation
				if(!co2.matches(bo))
					return false;
			}
			// Everything matched; success
			return true;
		} else
			return false;
	}
	
	
}
