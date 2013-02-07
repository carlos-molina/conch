/*
 *
 */
package uk.ac.ncl.erop;
//

//
// Instances of this class represent an Event Logger entity that receives
// events, stores them in a database and answers queries about them.
//

//import java.util.*;
import java.sql.*;

import javax.persistence.EntityManager;

import org.jboss.logging.Logger;

import uk.ac.ncl.model.BusinessEvent;
import uk.ac.ncl.util.Resources;


/**
 * The Class EventLogger.
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 10 $$, $$Date: 2012-09-25 17:00:29 +0100 (Tue, 25 Sep 2012) $$
 */
public class EventLogger {
	private final static Logger log = Logger.getLogger(EventLogger.class.toString());

	///* Private data

	//* Database information

//	// Database location (URI)
	private String dbLocation = null;
//	// Database username
	private String username = null;
//	// Database password
//	private String password = null;
	// Database connection
	private Connection conn = null;


	/**
	 * Instantiates a new event logger.
	 *
	 * @param dbLocation the db location
	 * @param username the username
	 * @param password the password
	 */
	public EventLogger(String dbLocation, String username, String password) {
		// Verify that parameters are not null or empty strings
		if((dbLocation==null) || (dbLocation.length()==0))
			throw new IllegalArgumentException("Database location is null or empty");
		if((username==null) || (username.length()==0))
			throw new IllegalArgumentException("Username is null or empty");
		if((password==null) || (password.length()==0))
			throw new IllegalArgumentException("Password is null or empty");
		// Parameters are ok, store them.
		this.dbLocation = dbLocation;
		this.username = username;
//		this.password = password;
		// Register JDBC driver
		try {
			//Class.forName("com.mysql.jdbc.Driver").newInstance();
			Class.forName("com.mysql.jdbc.Driver");
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Driver registration failed", e);
		}
		System.out.println("Driver registration success");
		// Attempt to establish connection with database
		try {
			conn = establishConnection(dbLocation, username, password);
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg(
					"Connection NOT established - maybe the server is down?");
		}
		System.out.println("Connection established");
	}


	public EventLogger(){

	}
	// Obtain Connection object to database (static)
	/**
	 * Establish connection.
	 * Obtain Connection object to database (static)
	 *
	 * @param dbLocation the db location
	 * @param username the username
	 * @param password the password
	 * @return the connection
	 * @throws Exception the exception
	 */
	private static Connection establishConnection(String dbLocation,
			String username, String password) throws Exception {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:mysql:"+dbLocation +"?user="+username+"&password="+password);
		} catch (Exception e) {
			ErrorMessageManager.errorMsg("Generation of Connection object failed", e);
			c = null;
			throw e;
		}
		return c;
	}

	/**
	 * Execute insert query.
	 *
	 * @param query the query
	 */
	private void executeInsertQuery(String query) {
		try {
			Statement st = conn.createStatement();
			st.executeUpdate(query);
		} catch (Exception e) {
			ErrorMessageManager.errorMsg("Insert statement failed\n "
					+query, e);
		}
	}

	/**
	 * Execute historical query.
	 * Perform historical (SELECT) query
	 *
	 * @param query the query
	 * @return true, if successful
	 */
	private boolean executeHistoricalQuery(String query) {
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			return rs.next();
		} catch(Exception e) {
			ErrorMessageManager.errorMsg("Exception occurred while executing historical query"
					+"\n  "+query, e);
			return false;
		}
	}

	/**
	 * Perform counting historical query
	 * Execute counting query.
	 *
	 * @param query the query
	 * @return the int
	 */
	private int executeCountingQuery(String query) {
		ResultSet rs = null;
		int t = -1;
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(query);
			rs.next();
			t = rs.getInt(1);
		} catch(Exception e) {
			ErrorMessageManager.fatalErrorMsg("Exception occurred while executing historical query"
					+"\n  "+query, e);
		}
		return t;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new String("Event Logger - DB Location:"+dbLocation+", Username:"
				+username+", Connection:"+conn);
	}

	/**
	 * Log an Event in the database.
	 *
	 * @param ev the ev
	 * @throws IllegalArgumentException the illegal argument exception
	 */
	public void logEvent(Event ev) throws IllegalArgumentException {
		// Verify validity of the received event.
		if(ev==null)
			throw new IllegalArgumentException("Null event passed for logging.");
		// TODO: Perform other tests such as consistency, etc.

		EntityManager em;
		em = Resources.getEntityManager();


		BusinessEvent bEvent = new BusinessEvent(ev.getOriginator(),ev.getResponder(),ev.getType(),ev.getStatus());

		em.persist(bEvent);

	}


	/**
	 * Happened.
	 *
	 * Query the historical database
	 * NOTE: I assume that the String timeConstraints is in acceptable
	 * format an SQL query.
	 *
	 * @param eventType the event type
	 * @param timeConstraints the time constraints
	 * @param originator the originator
	 * @param responder the responder
	 * @param status the status
	 * @return true, if successful
	 */
	public boolean happened(String eventType, String timeConstraints,
			String originator, String responder, String status) {
		// Verify validity of the parameters
		if((eventType==null)||(eventType.length()==0)
				//||(timeConstraints==null)||(timeConstraints.length()==0)
				||(originator==null)||(originator.length()==0)
				||(responder==null)||(responder.length()==0)
				||(status==null)||(status.length()==0))
			throw new IllegalArgumentException("Null or empty arguments passed");
		// Build query
		String query = "SELECT * FROM eventhistory WHERE "
			+"type='"+eventType+"' AND "
			+"originator='"+originator+"' AND ";
			// TODO: Parsing of time constraints
			// If timeConstraints is empty or null, no filtering by timestamp
			if((timeConstraints!=null)&&(timeConstraints.length()>0))
				query = query.concat(timeConstraints+" AND ");
			// COntinue with the construction of the query string
			query = query.concat("responder='"+responder+"' AND "
					+"status='"+status+"'");
		// Execute query and return result
		return executeHistoricalQuery(query);
	}


	/**
	 * Count happened.
	 * Query the historical database
	 * NOTE: I assume that the String timeConstraints is in acceptable
	 * format an SQL query.
	 *
	 * @param eventType the event type
	 * @param timeConstraints the time constraints
	 * @param originator the originator
	 * @param responder the responder
	 * @param status the status
	 * @return the int
	 */
	public int countHappened(String eventType, String timeConstraints,
			String originator, String responder, String status) {
		// Verify validity of the parameters
		if((eventType==null)||(eventType.length()==0)
				//||(timeConstraints==null)||(timeConstraints.length()==0)
				||(originator==null)||(originator.length()==0)
				||(responder==null)||(responder.length()==0)
				||(status==null)||(status.length()==0))
			throw new IllegalArgumentException("Null or empty arguments passed");
		// Build query
		String query = "SELECT COUNT(*) FROM eventhistory WHERE "
			+"type='"+eventType+"' AND "
			+"originator='"+originator+"' AND ";
			// TODO: Parsing of time constraints
			// If timeConstraints is empty or null, no filtering by timestamp
			if((timeConstraints!=null)&&(timeConstraints.length()>0))
				query = query.concat(timeConstraints+" AND ");
			// COntinue with the construction of the query string
			query = query.concat("responder='"+responder+"' AND "
					+"status='"+status+"'");
		// Execute query and return result
		return executeCountingQuery(query);
	}


}
