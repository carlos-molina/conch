/*
 * 
 */
package uk.ac.ncl.erop;

import java.util.*;

/**
 * The Class TimeKeeper.
 *
 */
public class TimeKeeper {

	// /* Private data
	// References to other relevant components of the CCC
	private RelevanceEngine relevanceEngine = null;
	private EventLogger logger = null;
	// HashMap of scheduled Deadlines, kept to track them if
	// they have to be cancelled.
	private HashMap<String, Timer> timerMap = new HashMap<String, Timer>();

	/**
	 * Instantiates a new time keeper.
	 *
	 * @param re the re
	 * @param el the el
	 */
	public TimeKeeper(RelevanceEngine re, EventLogger el) {
		// Verify that arguments are acceptable
		if (re == null)
			throw new IllegalArgumentException("Relevance Engine ref cannot be null");
		if (el == null)
			throw new IllegalArgumentException("Event Logger ref cannot be null");
		relevanceEngine = re;
		logger = el;
		System.out.println("TimeKeeper successfully instantiated");
	}

	/**
	 * Adds the deadline.
	 *
	 * @param rop the rop
	 * @param originator the originator
	 * @param responder the responder
	 * @param dl the dl
	 */
	public void addDeadline(ROPEntity rop, String originator, String responder, Date dl) {
		// Verify that arguments are acceptable
		if (rop == null)
			throw new IllegalArgumentException("ROPEntity cannot be null in addDeadline()");
		if ((originator == null) || (originator.length() == 0))
			throw new IllegalArgumentException("Originator name cannot be null or empty in addDeadline()");
		if ((responder == null) || (responder.length() == 0))
			throw new IllegalArgumentException("Responder name cannot be null or empty in addDeadline()");
		if (dl == null)
			throw new IllegalArgumentException("Date for deadline cannot be null in addDeadline()");
		// Create new Deadline object
		Deadline deadline = null;
		if (rop.getRopType() == ROPEntity.OBLIGATION)
			// Create a deadline for an expiry, not a timeout
			deadline = new Deadline(this, rop, originator, responder, false);
		else
			// Create a deadline for a timeout
			deadline = new Deadline(this, rop, originator, responder);
		// Create new Timer object
		Timer timer = new Timer();
		// And now schedule the deadline with it
		timer.schedule(deadline, dl);
		// And now build a key for it, and store it for retrieval!
		String key = new String(rop.getRopName() + "-" + rop.getRopTypeName() + "-" + originator + "-" + responder);
		// +"-"+DateParser.format(dl)); Not possible to store the deadline
		// easily outside of tk
		timerMap.put(key, timer);
		System.out.println("  Deadline added: " + key);
	}

	/**
	 * Removes the deadline.
	 *
	 * @param rop the rop
	 * @param originator the originator
	 * @param responder the responder
	 * @return true, if successful
	 */
	public boolean removeDeadline(ROPEntity rop, String originator, String responder) {
		// And now build a key for it, and store it for retrieval!
		String key = new String(rop.getRopName() + "-" + rop.getRopTypeName() + "-" + originator + "-" + responder);
		// +"-"+DateParser.format(dl));
		// Remove timer from HashMap
		Timer t = timerMap.remove(key);
		if (t == null)
			return false;
		// Timer ref is present, remove pending Deadlines (only one anyway)
		t.cancel();
		t.purge();
		System.out.println("  Deadline removed: " + key);
		return true;
	}

	
	/**
	 * Deadline callback.
	 * Callback method, called when a deadline expires.
	 *
	 * @param rop the rop
	 * @param originator the originator
	 * @param responder the responder
	 * @param isExpiry the is expiry
	 */
	public void deadlineCallback(ROPEntity rop, String originator, String responder, boolean isExpiry) {
		// Create deadline event, differentiating between timeouts and expiries
		String name = new String(rop.getRopName());
		if (isExpiry) {
			name = name.concat(" Expiry");
		} else {
			name = name.concat(" Timeout");
		}
		Event ev = new Event("sequenceId",originator, responder, name, "timeout", new Date());
		// Pump the event in the queue and log it
		logger.logEvent(ev);
		relevanceEngine.addEvent(ev);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String s = new String("TimeKeeper instance - Current deadlines:\n");
		Set<String> keys = timerMap.keySet();
		for (String k : keys) {
			s = s.concat(k + " => ");
			Timer tm = timerMap.get(k);
			s = s.concat(tm.toString() + "\n");
		}
		return s;
	}



}
