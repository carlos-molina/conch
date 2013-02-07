/*
 * 
 */
package uk.ac.ncl.erop;


/**
 * The Class RolePlayer.
 * 
 * INstances of this class represent role players in the system
 *
 * @author <a href="mailto:i.sfyrakis@nlc.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
public class RolePlayer {
	private String name = null;
	
	/**
	 * Instantiates a new role player.
	 *
	 * @param n the n
	 */
	public RolePlayer(String n) {
		if((n==null)||(n.length()==0)) 
			throw new IllegalArgumentException("RolePlayer name is null or empty");
		name = n;
	}
	
	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return new String(name);
	}
	
	/**
	 * Equals.
	 *
	 * @param rp the rp
	 * @return true, if successful
	 */
	public boolean equals(RolePlayer rp) {
		return name.equalsIgnoreCase((rp.getName()));
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Roleplayer "+name;
	}
}
