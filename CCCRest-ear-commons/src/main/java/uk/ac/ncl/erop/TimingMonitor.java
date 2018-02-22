/*
 * 
 */
package uk.ac.ncl.erop;

import java.util.*;

/**
 * The Class TimingMonitor.
 *
 */
public class TimingMonitor {
	private Calendar calendar = null;
	private ArrayList<Long> timeRecords = new ArrayList<Long>();


	public TimingMonitor() {
		
	}
	
	/**
	 * Notify event reception.
	 */
	public void notifyEventReception() {
		System.out.println("** In notifyEventReception");
		// If calendar is null, it's the first event - initialize it
		if(calendar==null) {
			calendar = Calendar.getInstance();
			System.out.println("First event received");
		} else {
			// Calculate time difference of current time with current calendar
			Calendar now = Calendar.getInstance();
			long diff = now.getTimeInMillis() - calendar.getTimeInMillis();
			// Store current time for the next event
			calendar = now;
			// Store difference in milliseconds
			timeRecords.add(new Long(diff));
			System.out.println("Normal event received");
		}
	}
	
	/**
	 * Prints the report.
	 */
	public void printReport() {
		System.out.println("*** Timing performance report\n");
		int counter = 1;
		for (Long  l : timeRecords) {
			System.out.println("Time difference for event "+counter+": "+l);
			counter++;
		}
		System.out.println("Number of events: "+counter);
		System.out.println("Average time: "+calculateAverageTime());
		System.out.println("Total time: "+calculateTotalTime());
	}
	
	/**
	 * Calculate total time.
	 *
	 * @return the long
	 */
	private long calculateTotalTime() {
		long t = 0;
		for (Long  l : timeRecords) {
			t = t+l;
		}
		return t;
	}
	
	/**
	 * Calculate average time.
	 *
	 * @return the long
	 */
	private long calculateAverageTime() {
		return calculateTotalTime()/timeRecords.size();
	}
}
