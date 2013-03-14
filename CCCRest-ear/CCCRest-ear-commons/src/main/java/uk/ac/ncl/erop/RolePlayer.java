/*
 * 
 */
package uk.ac.ncl.erop;


/**
 * The Class RolePlayer.
 * 
 * INstances of this class represent role players in the system
 *
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
