/*
 *
 */
package uk.ac.ncl.erop;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * The Class DateParser.
 *
 * This is a utility class, with methods to parse strings
 * into dates according to a specific format, thus avoiding
 * rewriting the same code over and over again
 *
 */
public class DateParser {

	private static DateFormat df = DateFormat.getInstance(); // new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//DateFormat.getInstance();

	/**
	 * Parses the date.
	 *
	 * @param d the d
	 * @return the date
	 */
	public static Date parse(String d) {
		try {
			return df.parse(d);
		} catch (Exception e) {
			ErrorMessageManager.errorMsg("Parsing error", e);
			throw new IllegalArgumentException("String cannot be parsed as a date");
		}
	}

	/**
	 * Format. dd/MM/yyyy HH:mm:ss
	 *
	 * @param d the date
	 * @return the string
	 */
	public static String format(Date d) {
		return df.format(d);
	}
}
