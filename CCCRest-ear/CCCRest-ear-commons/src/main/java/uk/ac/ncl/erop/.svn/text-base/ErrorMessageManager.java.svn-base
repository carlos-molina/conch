/*
 * 
 */
package uk.ac.ncl.erop;

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
	static void errorMsg(String message) {
		System.out.println(message);
	}
	
	/**
	 * Error msg.
	 *
	 * @param messages the messages
	 */
	static void errorMsg(String[] messages) {
		for (String string : messages) {
			System.out.println(string);
		}
	}
	
	/**
	 * Error msg.
	 *
	 * @param message the message
	 * @param e the e
	 */
	static void errorMsg(String message, Exception e) {
		System.out.println("Exception: "+message);
		System.out.println(e.getMessage());
		e.printStackTrace();
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param message the message
	 * @param e the e
	 */
	static void fatalErrorMsg(String message, Exception e) {
		System.out.println("Fatal exception: "+message);
		System.out.println(e.getMessage());
		e.printStackTrace();
		System.exit(0);
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param message the message
	 */
	static void fatalErrorMsg(String message) {
		System.out.println("Fatal error: "+message);
		System.exit(0);
	}
	
	/**
	 * Fatal error msg.
	 *
	 * @param messages the messages
	 */
	static void fatalErrorMsg(String[] messages) {
		for (String string : messages) {
			System.out.println(string);
		}
		System.exit(0);
	}

}
