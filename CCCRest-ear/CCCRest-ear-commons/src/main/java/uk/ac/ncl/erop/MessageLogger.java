/*
 * 
 */
package uk.ac.ncl.erop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.*;

// This class is used to log messages on screen and on file.

/**
 * The Class MessageLogger.
 * 
 * This class is used to log messages on screen and on file.
 * 
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 5 $$, $$Date: 2012-06-13 20:00:59 +0100 (Wed, 13 Jun
 *          2012) $$
 */
public class MessageLogger {

	private static DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

	static private FileWriter file = null;

	/**
	 * Inits the message logger.
	 * 
	 * @param filename
	 *            the filename
	 */
	static public void init(String filename) {
		if (file != null)
			stop();
		try {
			file = new FileWriter(filename);
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Error opening log file", e);
		}
	}

	/**
	 * Inits the message logger.
	 */
	static public void init() {
		if (file != null)
			stop();
		try {
			String fileName = new String("output-" + df.format(new Date()));
			file = new FileWriter(fileName);
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Error opening log file", e);
		}
	}

	/**
	 * Stop the message logger.
	 */
	static public void stop() {
		try {
			file.flush();
			file.close();
			file = null;
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Error closing log file", e);
		}
	}

	/**
	 * Log.
	 * 
	 * @param message
	 *            the message
	 */
	static public void log(String message) {
		System.out.println(message);
		try {
			file.write(message + "\n");
			file.flush();
		} catch (IOException e) {
			ErrorMessageManager.fatalErrorMsg("Error writing to log file", e);
		}
	}

	/**
	 * Log.
	 * 
	 * @param messages
	 *            the messages
	 */
	static public void log(String[] messages) {
		for (String message : messages) {
			System.out.println(message);
			try {
				file.write(message + "\n");
				file.flush();
			} catch (IOException e) {
				ErrorMessageManager.fatalErrorMsg("Error writing to log file", e);
			}
		}
	}

}
