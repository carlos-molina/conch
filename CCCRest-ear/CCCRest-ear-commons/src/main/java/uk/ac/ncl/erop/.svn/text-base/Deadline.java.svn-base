/*
 *
 */
package uk.ac.ncl.erop;

import java.util.*;

/**
 * The Class Deadline.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-06-13 20:00:59 +0100 (Wed, 13 Jun
 *          2012) $$
 */
public class Deadline extends TimerTask {

	private TimeKeeper timeKeeper = null;
	private ROPEntity rop = null;
	private String originator = null;
	private String responder = null;
	// Flag to identify if this deadline is for an expiry:
	// if true it is, otherwise it's a deadline for a timeout
	private boolean expiryFlag = true;

	/**
	 * Instantiates a new deadline.
	 *
	 * @param tk
	 *            the tk
	 * @param rop
	 *            the rop
	 * @param originator
	 *            the originator
	 * @param responder
	 *            the responder
	 */
	public Deadline(TimeKeeper tk, ROPEntity rop, String originator, String responder) {
		if (tk == null)
			throw new IllegalArgumentException("TimeKeeper ref cannot be null when defining new Deadline object");
		if (rop == null)
			throw new IllegalArgumentException("ROPEntity ref cannot be null when defining new Deadline object");
		if (originator == null)
			throw new IllegalArgumentException(
					"RolePlayer ref to originator cannot be null when defining new Deadline object");
		if (responder == null)
			throw new IllegalArgumentException(
					"RolePlayer ref to responder cannot be null when defining new Deadline object");
		timeKeeper = tk;
		this.rop = rop;
		this.originator = originator;
		this.responder = responder;
		expiryFlag = true;
	}

	/**
	 * Instantiates a new deadline.
	 *
	 * @param tk
	 *            the tk
	 * @param rop
	 *            the rop
	 * @param originator
	 *            the originator
	 * @param responder
	 *            the responder
	 * @param expiryFlag
	 *            the expiry flag
	 */
	public Deadline(TimeKeeper tk, ROPEntity rop, String originator, String responder, boolean expiryFlag) {
		if (tk == null)
			throw new IllegalArgumentException("TimeKeeper ref cannot be null when defining new Deadline object");
		if (rop == null)
			throw new IllegalArgumentException("ROPEntity ref cannot be null when defining new Deadline object");
		if (originator == null)
			throw new IllegalArgumentException(
					"RolePlayer ref to originator cannot be null when defining new Deadline object");
		if (responder == null)
			throw new IllegalArgumentException(
					"RolePlayer ref to responder cannot be null when defining new Deadline object");
		timeKeeper = tk;
		this.rop = rop;
		this.originator = originator;
		this.responder = responder;
		this.expiryFlag = expiryFlag;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		// A deadline expired, take remedy! Inform the TimeKeeper!
		timeKeeper.deadlineCallback(rop, originator, responder, expiryFlag);
	}

	/**
	 * Checks if is expiry.
	 *
	 * @return true, if is expiry
	 */
	public boolean isExpiry() {
		return expiryFlag;
	}

}
