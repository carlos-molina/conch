package uk.ac.ncl.erop;

import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;
import org.drools.compiler.compiler.DroolsParserException;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import uk.ac.ncl.ethereum.BlockChainOperation;
import uk.ac.ncl.logging.CCCLogger;
import uk.ac.ncl.xml.CCCResponse;

/**
 * The Class RelevanceEngine. Instances of this class are wrappers around the Drools Rule Engine
 */
public class RelevanceEngine {

  private final static Logger log = Logger.getLogger(RelevanceEngine.class.toString());

  static KieSession workingMem;

  static LinkedList<Event> eventQueue = new LinkedList<Event>();
  static EventLogger eventLogger = null;
  // TimeKeeper timeKeeper = null;

  // For performance testing only
  static boolean performanceTestingOn = false;

  private static Responder responder;

  // default response is non contract compliant otherwise contract compliant
  private static CCCResponse cccResponse = new CCCResponse("", false);
  private static CCCLogger ccclog;
//    private static CCCLogger ccclog;

  /**
   * Handle compilation errors.
   *
   * @param builder the builder
   * @param fileName the file name
   */
  private static void handleCompilationErrors(KieBuilder builder, String fileName) {
    Results builderErrors = builder.getResults();
    String[] errorMsg = new String[builderErrors.getMessages().size() + 1];
    errorMsg[0] = new String("Compilation errors for file " + fileName);
    for (int i = 0; i < builderErrors.getMessages().size(); i++) {
      errorMsg[i + 1] = builderErrors.getMessages().iterator().next().getText();
    }
    ErrorMessageManager.fatalErrorMsg(errorMsg);
  }

  /**
   * Instantiates a new relevance engine.
   *
   * @param fileName the file name  of the changeset
   * @param el the event logger
   * @throws IOException Signals that an I/O exception has occurred.
   * @throws DroolsParserException the drools parser exception
   */
  public RelevanceEngine(String fileName, EventLogger el)
      throws IOException, DroolsParserException {
    // Verify that the EventLogger is not null
    if (el == null) {
      throw new IllegalArgumentException("EventLogger ref null");
    }
    /* TODO refactor method to use the new kie packages */
    KieServices ks = KieServices.Factory.get();

    KieContainer ruleBase = ks.getKieClasspathContainer();
//        workingMem = ruleBase.newStatelessKieSession("CCCKS");
    workingMem = ruleBase.newKieSession("CCCKS");
    eventLogger = el;

  }

  public static void bootstrapRelevanceEngine() {
    //InputStream schemaIS = getClassLoader().getResourceAsStream("META-INF/kcontract.xml");
  }

  /**
   * Initialize contract.
   *
   * @param timeKeeper the time keeper
   */
  public static void initializeContract(TimeKeeper timeKeeper) {
    log.info("Initializing contract...");
    // Pass global objects to the working memory
    // workingMem.setGlobal("engine", this);
    workingMem.setGlobal("logger", eventLogger);
    /* TODO: Doing this manually here now, but a proper loader needs to be  written! */
    /* TODO: load dynamic properties of an electronic contract -> a bootstrap mechanism */

    BusinessOperation buyRequest = new BusinessOperation("Buy Request");
    BusinessOperation buyReject = new BusinessOperation("Buy Request Rejection");
    BusinessOperation buyConfirm = new BusinessOperation("Buy Confirmation");
    BusinessOperation payment = new BusinessOperation("Payment");
    BusinessOperation cancelation = new BusinessOperation("Cancelation");
    log.info("initialize blockchain operations");
    BlockChainOperation voucher = new BlockChainOperation("Get Voucher");
    BlockChainOperation bcEvent = new BlockChainOperation("Payment On Chain");
    RolePlayer buyer = new RolePlayer("buyer");
    RolePlayer seller = new RolePlayer("seller");
    // Create and pass the timing monitor
    if (performanceTestingOn) {
      TimingMonitor tm = new TimingMonitor();
      workingMem.setGlobal("timingMonitor", tm);
    }
    // Pass the TimeKeeper instance to the ROPSet class
    ROPSet.setTimeKeeper(timeKeeper);
    // Create the ROPSets for buyer and seller
    ROPSet ropBuyer = new ROPSet(new RolePlayer("buyer"));
    ROPSet ropSeller = new ROPSet(new RolePlayer("seller"));

    workingMem.setGlobal("buyer", buyer);
    workingMem.setGlobal("seller", seller);
    workingMem.setGlobal("ropBuyer", ropBuyer);
    workingMem.setGlobal("ropSeller", ropSeller);
    workingMem.setGlobal("buyRequest", buyRequest);
    workingMem.setGlobal("payment", payment);
    workingMem.setGlobal("buyReject", buyReject);
    workingMem.setGlobal("buyConfirm", buyConfirm);
    workingMem.setGlobal("cancelation", cancelation);
    workingMem.setGlobal("voucher", voucher);
    workingMem.setGlobal("bcEvent", bcEvent);

    responder = new Responder("", false);
    workingMem.setGlobal("responder", responder);
    ccclog = new CCCLogger();

    workingMem.setGlobal("cccloger", ccclog);

    log.info("Initialization complete");
  }

  // * Event Management Methods

  /**
   * Adds the event.
   *
   * @param e the e
   */
  public void addEvent(Event e) {
    log.info("- Adding new event to queue");
    eventQueue.offer(e);
    // TODO: Change this, the queue should be watched by an
    // Observer that calls the method below, so as to
    // decouple adding events from processing them
    processEventQueue();

  }

  /**
   * Checks if is queue empty.
   *
   * @return true, if is queue empty
   */
  public boolean isQueueEmpty() {
    return eventQueue.isEmpty();
  }

  /**
   * Process event queue.
   */
  public static void processEventQueue() {

    // Check if EventLogger is in place, if not refuse to process event
    // queue
    if (eventLogger == null) {
      throw new RuntimeException(
          "EventLogger ref in RelevanceEngine is still null, cannot process events");
    }
    // It is ok, continue with processing
    Event ev = eventQueue.poll();
    // Check if the queue is empty

    setCCCResponse(new CCCResponse(ev.getSequenceId(), false));
    responder.setContractCompliant(false);
    responder.setSequenceId(ev.getSequenceId());

    if (ev == null) {
      return;
    }
    // It is not empty, process event.
    try {
      // Insert new event in working memory
      workingMem.insert(ev);
    } catch (Exception e) {
      ErrorMessageManager.errorMsg("Insertion of new event in working memory failed", e);
      setCCCResponse(new CCCResponse(ev.getSequenceId(), false));
    }
    log.info("+ Processing event: " + ev.toString());
    // Fire all rules!
    try {
      workingMem.fireAllRules();

      // update response of CCC
      updateResponseOfCCC(ev);

    } catch (Exception e) {
      ErrorMessageManager.errorMsg("Exception when firing rules", e);
      setCCCResponse(new CCCResponse(ev.getSequenceId(), false));
    }

  }

  private static void updateResponseOfCCC(Event ev) {
    if (responder != null) {
      if (responder.getContractCompliant() == null) {
        setCCCResponse(new CCCResponse(ev.getSequenceId(), false));
      } else {
        setCCCResponse(new CCCResponse(ev.getSequenceId(), responder.getContractCompliant()));
      }
    }
  }

  /**
   * @return the cccResponse
   */
  public static CCCResponse getCCCResponse() {
    return cccResponse;
  }

  /**
   * @param cccResponse the cccResponse to set
   */
  public static void setCCCResponse(CCCResponse cccResponse) {
    RelevanceEngine.cccResponse = cccResponse;
  }

}
