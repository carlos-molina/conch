package uk.ac.ncl.logging;

import org.jboss.logging.Logger;

/**
 * Created by Ioannis Sfyrakis on 02/12/2014.
 */
public class CCCLogger {

    // a JBoss Logging 3 style level (FATAL, ERROR, WARN, INFO, DEBUG, TRACE), or a special level (OFF, ALL).
    private static Logger log = Logger.getLogger(CCCLogger.class.getName());
    static
    {
        logFatal("");
        logError("");
        logWarn("");
        logInfo("");
        logDebug("");
//        logTrace("");
    }

    public static void logFatal(String msg)
    {
        if (log.isEnabled(Logger.Level.FATAL))
        {
            log.fatal(msg);
        }
    }

    public static void logError(String msg)
    {
        if (log.isEnabled(Logger.Level.ERROR))
        {
            log.error(msg);
        }
    }

    public static void logWarn(String msg)
    {
        if (log.isEnabled(Logger.Level.WARN))
        {
            log.warn(msg);
        }
    }

    public static void logInfo(String msg)
    {
        if (log.isEnabled(Logger.Level.INFO))
        {
            log.info(msg);
        }
    }

    public static void logDebug(String msg)
    {
        if (log.isEnabled(Logger.Level.DEBUG))
        {
            log.debug(msg);
        }
    }

    public static void logTrace(String msg)
    {
        if (log.isEnabled(Logger.Level.TRACE))
        {
            log.trace(msg);
        }
    }
}

