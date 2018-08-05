/*
 *
 */
package uk.ac.ncl.erop;

import java.util.List;
import org.jboss.logging.Logger;
import uk.ac.ncl.xml.CCCResponse;

/**
 * The Class ContractComplianceChecker.
 */
public class ContractComplianceChecker {

  private static ContractComplianceChecker instance = null;

  private final static Logger log = Logger.getLogger(ContractComplianceChecker.class.toString());

  /**
   * Creates a new Contract Compliance Checker If a CCC is created return the current instance
   *
   * @param filepath the filepath
   * @return the CCC instance
   */
  public static ContractComplianceChecker createContractComplianceChecker(String filepath) {
    if (instance == null) {
      instance = new ContractComplianceChecker(filepath);
      return instance;
    } else {
      return instance;
    }

  }

  private static Boolean startFlag = false;
  /**
   * The time keeper.
   */
  private TimeKeeper timeKeeper = null;

  /**
   * The relevance engine.
   */
  private RelevanceEngine relevanceEngine = null;

  /**
   * The logger.
   */
  private EventLogger logger = null;

  /**
   * Instantiates a new Contract Compliance Checker
   *
   * @param filepath the filepath
   */
  private ContractComplianceChecker(String filepath) {

    log.info("Initializing objects...");
    log.info("filepath: " + filepath);

    logger = new EventLogger();

    // NOTE: It might fail to find the file
    try {
      relevanceEngine = new RelevanceEngine(filepath, logger);
    } catch (Exception e) {
      ErrorMessageManager.fatalErrorMsg("Failed to initalize Relevance Engine", e);
    }
    try {
      timeKeeper = new TimeKeeper(relevanceEngine, logger);
    } catch (Exception e) {
      ErrorMessageManager.fatalErrorMsg("Failed to initalize Time Keeper", e);
    }
  }


  /**
   * Loop over an array of pre-created events and feed them to the RE and EL // TODO: More
   * sophisticated time management
   *
   * Start simulation.
   *
   * @param events the events
   */
  public void startSimulation(List<Event> events) {
    // Have the Relevance Engine do all the initialization bits
    relevanceEngine.initializeContract(timeKeeper);
    // Init event created and submitted
    Event event = new Event("none", "none", "none", "init", "success");
    logger.logEvent(event);
    relevanceEngine.addEvent(event);
    startFlag = true;
    // Continue with rest of simulation
    continueSimulation(events);
  }

  /**
   * Initialize ccc.
   */
  public void initializeCCC() {

    // Have the Relevance Engine do all the initialization bits
    relevanceEngine.initializeContract(timeKeeper);
    // Init event created and submitted
    Event event = new Event("none", "none", "none", "init", "success");
    //logger.logEvent(event);
    relevanceEngine.addEvent(event);
    startFlag = true;

  }

  /**
   * Simulation started.
   *
   * @return the boolean
   */
  public Boolean cccStarted() {

    return startFlag;
  }

  /**
   * Continue simulation.
   *
   * @param events the events
   */

  public List<CCCResponse> continueSimulation(List<Event> events) {
    List<CCCResponse> response = null;
    // Feed events to RE
    for (Event event : events) {
      // Pause for 4.2 seconds whenever a null comes up
//			if (event == null) {
//				try {
//					Thread.sleep(4200);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					ErrorMessageManager.fatalErrorMsg("Interrupted while waiting", e);
//				}
//
//			} else {
//				logger.logEvent(event);
//				relevanceEngine.addEvent(event);
//			}

      response.add(processEvent(event));
    }
    return response;
  }

  /**
   * Process event.
   *
   * @param event the event
   * @return the cCC response
   */
  public CCCResponse processEvent(Event event) {
    // Feed events to RE

    // Pause for 4.2 seconds whenever a null comes up
    if (event == null) {
      try {
        Thread.sleep(4200);
      } catch (InterruptedException e) {
        ErrorMessageManager.fatalErrorMsg("Interrupted while waiting", e);
      }

    } else {
      logger.logEvent(event);
      relevanceEngine.addEvent(event);
      return RelevanceEngine.getCCCResponse();

    }
    return null;

  }


}
