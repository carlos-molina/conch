/*
 *
 */
package uk.ac.ncl.erop;

// ROPSet class
// An instance of this class represent the ROP set of a single role player:
// the set of rights, obligations and prohibitions currently in force
// for that role player.

import java.text.*;
import java.util.*;

/**
 * The Class ROPSet.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 10 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep
 *          2012) $$
 */
public class ROPSet {
	private ArrayList<Right> rights = new ArrayList<Right>();
	private ArrayList<Obligation> obligations = new ArrayList<Obligation>();
	private ArrayList<Prohibition> prohibitions = new ArrayList<Prohibition>();
	private static TimeKeeper timeKeeper = null;

	/** The calendar constants. */
	private int[] calendarConstants = { Calendar.SECOND, Calendar.MINUTE, Calendar.HOUR, Calendar.DATE, Calendar.MONTH,
			Calendar.YEAR };

	private RolePlayer player = null;

	/**
	 * Instantiates a new rOP set. initialize an empty ROP set for a role player
	 *
	 * @param rp
	 *            the rp
	 */
	public ROPSet(RolePlayer rp) {
		if (rp == null)
			throw new IllegalArgumentException("Roleplayer cannot be null in ROPSet constructor");
		player = rp;
	}

	/**
	 * Instantiates a new rOP set. initialize the ROP set with certain rights,
	 * obligs and prohibs for a role player (array version)
	 *
	 *
	 * @param rp
	 *            the Role Player
	 * @param r
	 *            the Right
	 * @param o
	 *            the Obligation
	 * @param p
	 *            the Prohibition
	 */
	public ROPSet(RolePlayer rp, Right[] r, Obligation[] o, Prohibition[] p) {
		// Verify that the arguments are acceptable
		if (rp == null)
			throw new IllegalArgumentException("Roleplayer cannot be null in ROPSet constructor");
		if (r == null)
			throw new IllegalArgumentException("Rights array cannot be null in ROPSet constructor");
		if (o == null)
			throw new IllegalArgumentException("Obligations array cannot be null in ROPSet constructor");
		if (p == null)
			throw new IllegalArgumentException("Prohibitions array cannot be null in ROPSet constructor");
		// Add all rights
		for (Right right : r) {
			rights.add(right);
		}
		// Add all obligations
		for (Obligation oblig : o) {
			obligations.add(oblig);
		}
		// Add all prohibitions
		for (Prohibition prohibition : p) {
			prohibitions.add(prohibition);
		}
		player = rp;
	}

	/**
	 * Instantiates a new rOP set. initialize the ROP set with certain rights,
	 * obligs and prohibs for a role player (array version)
	 *
	 * @param rp
	 *            the rp
	 * @param r
	 *            the r
	 * @param o
	 *            the o
	 * @param p
	 *            the p
	 */
	public ROPSet(RolePlayer rp, ArrayList<Right> r, ArrayList<Obligation> o, ArrayList<Prohibition> p) {
		// Verify that the arguments are acceptable
		if (rp == null)
			throw new IllegalArgumentException("Roleplayer cannot be null in ROPSet constructor");
		if (r == null)
			throw new IllegalArgumentException("Rights array cannot be null in ROPSet constructor");
		if (o == null)
			throw new IllegalArgumentException("Obligations array cannot be null in ROPSet constructor");
		if (p == null)
			throw new IllegalArgumentException("Prohibitions array cannot be null in ROPSet constructor");
		// Add all rights
		rights.addAll(r);
		// Add all obligations
		obligations.addAll(o);
		// Add all prohibitions
		prohibitions.addAll(p);
		player = rp;
	}

	/**
	 * Gets the role player.
	 *
	 * @return the role player
	 */
	public RolePlayer getRolePlayer() {
		return player;
	}

	/**
	 * Matches rights. Match a business operation to the rights in the rights
	 * set
	 *
	 * @param bo
	 *            the bo
	 * @return true, if successful
	 */
	public boolean matchesRights(BusinessOperation bo) {
		for (Right r : rights) {
			if (r.matches(bo))
				return true;
		}
		return false;
	}

	/**
	 * Matches obligations.
	 *
	 * Match a business operation to the obligations in the obligs set
	 *
	 * @param bo
	 *            the bo
	 * @return true, if successful
	 */
	public boolean matchesObligations(BusinessOperation bo) {
		for (Obligation o : obligations) {
			if (o.matches(bo))
				return true;
		}
		return false;
	}

	// Match an obligation name to the obligations in the obligs set
	/**
	 * Matches obligations.
	 *
	 * @param boName
	 *            the bo name
	 * @return true, if successful
	 */
	public boolean matchesObligations(String boName) {
		for (Obligation o : obligations) {
			if (o.getRopName().equals(boName))
				return true;
		}
		return false;
	}

	/**
	 * Matches prohibitions. Match a business operation to the prohibitions in
	 * the prohibitions set
	 *
	 *
	 * @param bo
	 *            the bo
	 * @return true, if successful
	 */
	public boolean matchesProhibitions(BusinessOperation bo) {
		for (Prohibition p : prohibitions) {
			if (p.matches(bo))
				return true;
		}
		return false;
	}

	// / Manipulation of the ROP set

	/**
	 * Add a right, given a business operation and a deadline in the form of a
	 * Date (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addRight(BusinessOperation bo, RolePlayer responder, Date d) {
		// First remove the old instance of that right, if present
		removeRight(bo, responder);
		// Create and add the new right
		Right r = new Right(bo, d);
		rights.add(r);
		// Notify TimeKeeper if this right has a deadline
		if ((timeKeeper != null) && (d != null))
			timeKeeper.addDeadline(r, player.getName(), responder.getName(), d);
	}

	/**
	 * Add a right, given a business operation and a deadline in the form of a
	 * String (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addRight(BusinessOperation bo, RolePlayer responder, String d) {
		Date deadline = null;
		if (d != null) {
			try {
				deadline = DateFormat.getTimeInstance().parse(d);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Unacceptable deadline string: " + d);
			}
		}
		// First remove the old instance of that right, if present
		removeRight(bo, responder);
		// Create and add the new right
		Right r = new Right(bo, deadline);
		rights.add(r);
		// Notify TimeKeeper if this right has a deadline
		if ((timeKeeper != null) && (d != null))
			timeKeeper.addDeadline(r, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Add a right, given a business operation and a relative deadline in the
	 * form of a sequence of N ints that cannot be null. Position 0 will be
	 * seconds, position 1 will be minutes, Position 2 will be hours, position 3
	 * days, and so on.
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param relativeTime
	 *            the relative time
	 */
	public void addRight(BusinessOperation bo, RolePlayer responder, int... relativeTime) {
		if ((relativeTime == null) || (relativeTime.length == 0) || (relativeTime.length > 6))
			throw new IllegalArgumentException("Unacceptable relative time: " + relativeTime);
		// First remove the old instance of that right, if present
		removeRight(bo, responder);
		// Create and modify a Calendar object to determine the
		// new deadline
		GregorianCalendar cal = new GregorianCalendar(); // Current time!
		for (int i = 0; i < relativeTime.length; i++) {
			cal.add(calendarConstants[i], relativeTime[i]);
		}
		// Create and add the new right
		Date deadline = cal.getTime();
		Right r = new Right(bo, deadline);
		rights.add(r);
		// Notify TimeKeeper (this right always has a deadline)
		if (timeKeeper != null)
			timeKeeper.addDeadline(r, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Removes the right.
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @return true, if successful
	 */
	public boolean removeRight(BusinessOperation bo, RolePlayer responder) {
		for (Right r : rights) {
			if (r.matches(bo)) {
				rights.remove(r);
				// Notify TimeKeeper if present
				if (timeKeeper != null)
					timeKeeper.removeDeadline(r, player.getName(), responder.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Add an obligation, given a business operation and a deadline in the form
	 * of a Date (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addObligation(BusinessOperation bo, RolePlayer responder, Date d) {
		// First remove the old instance of that obligation, if present
		removeObligation(bo, responder);
		// Create and add the new obligation
		Obligation ob = new Obligation(bo, d);
		obligations.add(ob);
		// Notify TimeKeeper if present
		if (timeKeeper != null)
			timeKeeper.addDeadline(ob, player.getName(), responder.getName(), d);
	}

	/**
	 * Add a obligation, given a business operation and a deadline in the form
	 * of a String (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addObligation(BusinessOperation bo, RolePlayer responder, String d) throws ParseException {
		Date deadline = null;
		if (d != null) {
			deadline = DateParser.parse(d);//DateFormat.getTimeInstance().parse(d);
		}
		// First remove the old instance of that obligation, if present
		removeObligation(bo, responder);
		// Create and add the new obligation
		Obligation ob = new Obligation(bo, d);
		obligations.add(ob);
		// Notify TimeKeeper if present
		if (timeKeeper != null)
			timeKeeper.addDeadline(ob, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Add a composite obligation, given an array of business operations and a
	 * deadline in the form of a String (which can be null)
	 *
	 * @param name
	 *            the name
	 * @param bos
	 *            the bos
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addObligation(String name, BusinessOperation[] bos, RolePlayer responder, String d) throws ParseException {
		if ((name == null) || (name.length() == 0))
			throw new IllegalArgumentException("Unacceptable name for Composite Obligation");
		Date deadline = null;
		if (d != null) {
			deadline = DateParser.parse(d);//deadline = DateFormat.getTimeInstance().parse(d);
		}
		// First remove the old instance of that obligation, if present
		removeObligation(name, responder);
		// Create and add the new obligation
		Obligation ob = new CompositeObligation(name, bos, d);
		obligations.add(ob);
		// Notify TimeKeeper if present
		if (timeKeeper != null)
			timeKeeper.addDeadline(ob, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Add a obligation, given a business operation and a relative deadline in
	 * the form of a sequence of N ints that cannot be null. Position 0 will be
	 * seconds, position 1 will be minutes, Position 2 will be hours and so on.
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param relativeTime
	 *            the relative time
	 */
	public void addObligation(BusinessOperation bo, RolePlayer responder, int... relativeTime) {
		if ((relativeTime == null) || (relativeTime.length == 0) || (relativeTime.length > 6))
			throw new IllegalArgumentException("Unacceptable relative time array: " + relativeTime);
		// First remove the old instance of that obligation, if present
		removeObligation(bo, responder);
		// Create and modify a Calendar object to determine the
		// new deadline
		GregorianCalendar cal = new GregorianCalendar(); // Current time!
		for (int i = 0; i < relativeTime.length; i++) {
			cal.add(calendarConstants[i], relativeTime[i]);
		}
		// Create and add the new obligation
		Date deadline = cal.getTime();
		Obligation ob = new Obligation(bo, deadline);
		obligations.add(ob);
		// Notify TimeKeeper if present
		if (timeKeeper != null) {
			if (ob == null)
				System.out.println("Obligation argument is null");
			if (player == null)
				System.out.println("Obliged Role Player argument is null");
			if (responder == null)
				System.out.println("Responder Role Player argument is null");
			if (deadline == null)
				System.out.println("Deadline argument is null");
			timeKeeper.addDeadline(ob, player.getName(), responder.getName(), deadline);
		}
	}

	/**
	 * Add a obligation, given a business operation and a relative deadline in
	 * the form of a sequence of N ints that cannot be null. Position 0 will be
	 * seconds, position 1 will be minutes, Position 2 will be hours and so on.
	 *
	 * @param name
	 *            the name
	 * @param bos
	 *            the bos
	 * @param responder
	 *            the responder
	 * @param relativeTime
	 *            the relative time
	 */
	public void addObligation(String name, BusinessOperation[] bos, RolePlayer responder, int... relativeTime) {
		if ((relativeTime == null) || (relativeTime.length == 0) || (relativeTime.length > 6))
			throw new IllegalArgumentException("Unacceptable relative time array: " + relativeTime);
		// First remove the old instance of that obligation, if present
		removeObligation(name, responder);
		// Create and modify a Calendar object to determine the
		// new deadline
		GregorianCalendar cal = new GregorianCalendar(); // Current time!
		for (int i = 0; i < relativeTime.length; i++) {
			cal.add(calendarConstants[i], relativeTime[i]);
		}
		// Create and add the new obligation
		Date deadline = cal.getTime();
		Obligation ob = new CompositeObligation(name, bos, deadline);
		obligations.add(ob);
		// Notify TimeKeeper if present
		if (timeKeeper != null) {
			if (ob == null)
				System.out.println("Obligation argument is null");
			if (player == null)
				System.out.println("Obliged Role Player argument is null");
			if (responder == null)
				System.out.println("Responder Role Player argument is null");
			if (deadline == null)
				System.out.println("Deadline argument is null");
			timeKeeper.addDeadline(ob, player.getName(), responder.getName(), deadline);
		}
	}

	/**
	 * Remove an obligation by Business Operation
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @return true, if successful
	 */
	public boolean removeObligation(BusinessOperation bo, RolePlayer responder) {
		for (Obligation ob : obligations) {
			if (ob.matches(bo)) {
				obligations.remove(ob);
				// Notify TimeKeeper if present
				if (timeKeeper != null)
					timeKeeper.removeDeadline(ob, player.getName(), responder.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Remove an obligation by name
	 *
	 * @param obName
	 *            the ob name
	 * @param responder
	 *            the responder
	 * @return true, if successful
	 */
	public boolean removeObligation(String obName, RolePlayer responder) {
		for (Obligation ob : obligations) {
			if (ob.getRopName().equals(obName)) {
				obligations.remove(ob);
				// Notify TimeKeeper if present
				if (timeKeeper != null)
					timeKeeper.removeDeadline(ob, player.getName(), responder.getName());
				return true;
			}
		}
		return false;
	}

	/**
	 * Add a prohibition, given a business operation and a deadline in the form
	 * of a Date (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addProhibition(BusinessOperation bo, RolePlayer responder, Date d) {
		// First remove the old instance of that prohibition, if present
		removeProhibition(bo, responder);
		// Create and add the new prohibition
		Prohibition p = new Prohibition(bo, d);
		prohibitions.add(p);
		// Notify TimeKeeper if this prohibition has a deadline
		if ((timeKeeper != null) && (d != null))
			timeKeeper.addDeadline(p, player.getName(), responder.getName(), d);
	}

	/**
	 * Add a prohibition, given a business operation and a deadline in the form
	 * of a String (which can be null)
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param d
	 *            the d
	 */
	public void addProhibition(BusinessOperation bo, RolePlayer responder, String d) {
		Date deadline = null;
		if (d != null) {
			try {
				deadline = DateFormat.getTimeInstance().parse(d);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Unacceptable deadline string: " + d);
			}
		}
		// First remove the old instance of that obligation, if present
		removeProhibition(bo, responder);
		// Create and add the new obligation
		Prohibition p = new Prohibition(bo, d);
		prohibitions.add(p);
		// Notify TimeKeeper if this prohibition has a deadline
		if ((timeKeeper != null) && (d != null))
			timeKeeper.addDeadline(p, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Add a prohibition, given a business operation and a relative deadline in
	 * the form of a sequence of N ints that cannot be null. Position 0 will be
	 * seconds, position 1 will be minutes, Position 2 will be hours and so on.
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @param relativeTime
	 *            the relative time
	 */
	public void addProhibition(BusinessOperation bo, RolePlayer responder, int... relativeTime) {
		if ((relativeTime == null) || (relativeTime.length == 0) || (relativeTime.length > 6))
			throw new IllegalArgumentException("Unacceptable relative time array: " + relativeTime);
		// First remove the old instance of that right, if present
		removeProhibition(bo, responder);
		// Create and modify a Calendar object to determine the
		// new deadline
		GregorianCalendar cal = new GregorianCalendar(); // Current time!
		for (int i = 0; i < relativeTime.length; i++) {
			cal.add(calendarConstants[i], relativeTime[i]);
		}
		// Create and add the new prohibition
		Date deadline = cal.getTime();
		Prohibition p = new Prohibition(bo, deadline);
		prohibitions.add(p);
		// Notify TimeKeeper if this prohibition has a deadline
		if (timeKeeper != null)
			timeKeeper.addDeadline(p, player.getName(), responder.getName(), deadline);
	}

	/**
	 * Removes the prohibition.
	 *
	 * @param bo
	 *            the bo
	 * @param responder
	 *            the responder
	 * @return true, if successful
	 */
	public boolean removeProhibition(BusinessOperation bo, RolePlayer responder) {
		for (Prohibition p : prohibitions) {
			if (p.matches(bo)) {
				prohibitions.remove(p);
				// Notify TimeKeeper if present
				if (timeKeeper != null)
					timeKeeper.removeDeadline(p, player.getName(), responder.getName());
				return true;
			}
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String ps = new String("Prohibitions: ");
		for (Prohibition p : prohibitions) {
			ps = ps.concat(p + ", ");
		}
		String os = new String("Obligations: ");
		for (Obligation o : obligations) {
			os = os.concat(o + ", ");
		}
		String rs = new String("Rights: ");
		for (Right r : rights) {
			rs = rs.concat(r + ", ");
		}
		return new String("ROP Set for " + player + " - " + rs + " - " + os + " - " + ps);
	}

	/**
	 * Gets the rights.
	 *
	 * @return the rights
	 */
	public ArrayList<Right> getRights() {
		return rights;
	}

	/**
	 * Gets the obligations.
	 *
	 * @return the obligations
	 */
	public ArrayList<Obligation> getObligations() {
		return obligations;
	}

	/**
	 * Gets the prohibitions.
	 *
	 * @return the prohibitions
	 */
	public ArrayList<Prohibition> getProhibitions() {
		return prohibitions;
	}

	/**
	 * Sets the time keeper.
	 *
	 * @param tk
	 *            the new time keeper
	 */
	public static void setTimeKeeper(TimeKeeper tk) {
		timeKeeper = tk;
	}


	public boolean checkRopSet() {
		if (this.obligations.size() > 0 || this.prohibitions.size() >0 || this.rights.size() >0) {
			return true;

		}

		return false;
	}

	/**
	 * Reset Rop Sets.
	 */
	public void reset() {
		this.obligations.removeAll(obligations);
		this.rights.removeAll(rights);
		this.prohibitions.removeAll(prohibitions);
		timeKeeper = null;

	}

}
