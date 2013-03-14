/*
 * 
 */
package uk.ac.ncl.erop;

import java.util.logging.Logger;

/**
 * The Class ErrorMessageManager.
 * 
 * This class handles error messages, so repetitive blocks to print
 * the same kind of messages can be avoided.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-09-05 23:33:00 +0100 (Wed, 05 Sep 2012) $$
 */
public class ErrorMessageManager {
	private final static Logger log = Logger.getLogger(ErrorMessageManager.class.toString());

	static void errorMsg(String message) {
		log.warning(message);
	}
	
	/**
	 * Error msg.
	 *
	 * @param messages the messages
	 */
	static void errorMsg(String[] messages) {
		for (String string : messages) {
			log.warning(string);
		}
	}
	
	/**
	 * Error msg.
	 *
	 * @param message the message
	 * @param e the e
	 */
	static void errorMsg(String message, Exception e) {
		log.warning("Exception: "+message);
		log.warning(e.getMessage());
		e.printStackTrace();
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param message the message
	 * @param e the e
	 */
	static void fatalErrorMsg(String message, Exception e) {
		log.severe("Fatal exception: "+message);
		log.severe(e.getMessage());
		e.printStackTrace();
		System.exit(0);
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param message the message
	 */
	static void fatalErrorMsg(String message) {
		log.severe("Fatal error: "+message);
		System.exit(0);
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param messages the messages
	 */
	static void fatalErrorMsg(String[] messages) {
		for (String string : messages) {
			log.severe(string);
		}
		System.exit(0);
	}

}
