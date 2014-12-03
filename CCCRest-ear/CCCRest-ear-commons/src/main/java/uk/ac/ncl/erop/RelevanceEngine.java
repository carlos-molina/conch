package uk.ac.ncl.erop;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import org.drools.*;
import org.drools.agent.KnowledgeAgent;
import org.drools.agent.KnowledgeAgentConfiguration;
import org.drools.agent.KnowledgeAgentFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.common.InternalWorkingMemoryEntryPoint;
import org.drools.compiler.*;
import org.drools.conf.MBeansOption;
import org.drools.definition.KnowledgePackage;
import org.drools.definition.rule.Global;
import org.drools.io.ResourceChangeScannerConfiguration;
import org.drools.io.ResourceFactory;
import org.drools.logger.KnowledgeRuntimeLogger;
import org.drools.logger.KnowledgeRuntimeLoggerFactory;
import org.drools.runtime.Globals;
import org.drools.runtime.KnowledgeSessionConfiguration;
import org.drools.runtime.StatefulKnowledgeSession;
import org.drools.runtime.conf.ClockTypeOption;
import org.drools.time.SessionPseudoClock;
import org.drools.runtime.rule.WorkingMemoryEntryPoint;

<<<<<<< HEAD:CCCRest-ear/CCCRest-ear-commons/src/main/java/uk/ac/ncl/erop/RelevanceEngine.java
import uk.ac.ncl.util.CustomAgendaEventListener;
import uk.ac.ncl.util.CustomWorkingMemoryEventListener;
=======
import org.drools.compiler.compiler.DroolsParserException;


import org.kie.api.KieServices;
import org.kie.api.builder.*;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import uk.ac.ncl.logging.CCCLogger;
>>>>>>> added file logging configuration:CCCRest-ear-commons/src/main/java/uk/ac/ncl/erop/RelevanceEngine.java
import uk.ac.ncl.xml.CCCResponse;

/**
 * The Class RelevanceEngine. Instances of this class are wrappers around the
 * Drools Rule Engine
 */
public class RelevanceEngine {

    private final static Logger log = Logger.getLogger(RelevanceEngine.class.toString());

    // /* Private data
    static KnowledgeBase ruleBase;

    static StatefulKnowledgeSession workingMem;

    static LinkedList<Event> eventQueue = new LinkedList<Event>();
    static EventLogger eventLogger = null;
    // TimeKeeper timeKeeper = null;

    // For performance testing only
    static boolean performanceTestingOn = false;

    private static Responder responder;

    // default response is non contract compliant otherwise contract compliant
    private static CCCResponse cccResponse = new CCCResponse("",false);
//    private static CCCLogger ccclog;

    /**
     * Handle compilation errors.
     *
     * @param builder  the builder
     * @param fileName the file name
     */
    private static void handleCompilationErrors(KnowledgeBuilder builder, String fileName) {
        KnowledgeBuilderErrors builderErrors = builder.getErrors();
        String[] errorMsg = new String[builderErrors.size() + 1];
        errorMsg[0] = new String("Compilation errors for file " + fileName);
        for (int i = 0; i < builderErrors.size(); i++) {
            errorMsg[i + 1] = builderErrors.iterator().next().getMessage();
        }
        ErrorMessageManager.fatalErrorMsg(errorMsg);
    }

    /**
     * Instantiates a new relevance engine.
     *
     * @param fileName the file name  of the changeset
     * @param el       the event logger
     * @throws IOException           Signals that an I/O exception has occurred.
     * @throws DroolsParserException the drools parser exception
     */
    public RelevanceEngine(String fileName, EventLogger el) throws IOException, DroolsParserException {
        // Verify that the EventLogger is not null
        if (el == null)
            throw new IllegalArgumentException("EventLogger ref null");

        // Create KnowledgeBuilder
        KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();

        try {
            builder.add(ResourceFactory.newFileResource(fileName), ResourceType.CHANGE_SET);
        } catch (Exception e) {
            ErrorMessageManager.fatalErrorMsg("Exception opening file resource " + fileName, e);
        }

        // Check if the compilation was successful
        if (builder.hasErrors()) {
            handleCompilationErrors(builder, fileName);
        }
        KnowledgeBaseConfiguration config = KnowledgeBaseFactory.newKnowledgeBaseConfiguration();

        //enable knowledge session monitoring using JMX
        config.setOption(MBeansOption.ENABLED);

        ruleBase = KnowledgeBaseFactory.newKnowledgeBase("CCCbase", config);
        ruleBase.addKnowledgePackages(builder.getKnowledgePackages());
        KnowledgeAgentConfiguration aconf = KnowledgeAgentFactory.newKnowledgeAgentConfiguration();

        /*
        *
        *   false: make the agent keep and incrementally update the existing knowledge base, automatically updating all existing sessions
        *   true: make the agent to create a brand new KnowledgeBase  every time there is a change to the source assets.
        *
        */
        aconf.setProperty("drools.agent.newInstance", "false");

        KnowledgeAgent kagent = KnowledgeAgentFactory.newKnowledgeAgent("CCCagent", ruleBase, aconf);
        kagent.applyChangeSet(ResourceFactory.newFileResource(fileName));
        ruleBase = kagent.getKnowledgeBase();
        ResourceChangeScannerConfiguration sconf = ResourceFactory.getResourceChangeScannerService()
                .newResourceChangeScannerConfiguration();

        // set disc scanning interval in seconds
        sconf.setProperty("drools.resource.scanner.interval", "10");

        ResourceFactory.getResourceChangeScannerService().configure(sconf);

        KnowledgeSessionConfiguration sconfig = KnowledgeBaseFactory.newKnowledgeSessionConfiguration();

        /*
        *
        *   realtime: uses the system clock to determine the current time for timestamps.
        *   pseudo:  for testing temporal rules since it can be controlled by the application.
        *
         */
        sconfig.setOption(ClockTypeOption.get("realtime"));

        workingMem = ruleBase.newStatefulKnowledgeSession(sconfig, null);
        //SessionPseudoClock clock = workingMem.getSessionClock();

        workingMem.addEventListener(new CustomAgendaEventListener());
        workingMem.addEventListener(new CustomWorkingMemoryEventListener());

        //KnowledgeRuntimeLogger rulesLogger = KnowledgeRuntimeLoggerFactory.newConsoleLogger(workingMem);

        ResourceFactory.getResourceChangeNotifierService().start();
        ResourceFactory.getResourceChangeScannerService().start();

        eventLogger = el;

        /*
        *
        *   Creates a file logger that executes in a different thread, where information is written on given intervals (in milliseconds).
        *   Logs the execution of the session for later inspection
        *
         */
        final KnowledgeRuntimeLogger logger = KnowledgeRuntimeLoggerFactory.newThreadedFileLogger(workingMem,
                "ruleLog", 1000);

        Runtime.getRuntime().addShutdownHook(new Thread() {

            public void run() {

                if (logger != null) {

                    logger.close();

                }

            }

        });
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
        // TODO: Doing this manually here now, but a proper loader needs to be
        // written!

        // BusinessOperation buyAcceptance = new
        // BusinessOperation("Buy Acceptance");

        // BusinessOperation finePayment = new
        // BusinessOperation("Fine Payment");

        BusinessOperation buyRequest = new BusinessOperation("Buy Request");
        BusinessOperation buyReject = new BusinessOperation("Buy Request Rejection");
        BusinessOperation buyConfirm = new BusinessOperation("Buy Confirmation");
        BusinessOperation payment = new BusinessOperation("Payment");
        BusinessOperation cancelation = new BusinessOperation("Cancelation");

        // BusinessOperation poAcceptance = new
        // BusinessOperation("Purchase Order Acceptance");
        // BusinessOperation poRejection = new
        // BusinessOperation("Purchase Order Acceptance");
        // BusinessOperation goodsDelivery = new
        // BusinessOperation("Goods Delivery");
        // BusinessOperation anyOperation = new
        // BusinessOperation("Any Operation");
        RolePlayer buyer = new RolePlayer("buyer");
        RolePlayer seller = new RolePlayer("seller");
        // RolePlayer player = new RolePlayer("player");
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

//		Boolean buyReqBF = false;

//		Boolean buyRejBF = false;
//		Boolean buyConfBF = false;
//		Boolean buyPayBF = false;
// 		Boolean buyCancBF = false;
//		// ROPSet ropPlayer = new ROPSet(player);
//		// Add all the globals to the working memory
//
//		workingMem.setGlobal("buyReqBF", buyReqBF);
//		workingMem.setGlobal("buyConfBF", buyConfBF);
//		workingMem.setGlobal("buyPayBF", buyPayBF);
//		workingMem.setGlobal("buyCancBF", buyCancBF);
//		workingMem.setGlobal("buyRejBF", buyRejBF);


        workingMem.setGlobal("buyer", buyer);
        workingMem.setGlobal("seller", seller);
        workingMem.setGlobal("ropBuyer", ropBuyer);
        workingMem.setGlobal("ropSeller", ropSeller);
        workingMem.setGlobal("buyRequest", buyRequest);
        workingMem.setGlobal("payment", payment);
        workingMem.setGlobal("buyReject", buyReject);
        workingMem.setGlobal("buyConfirm", buyConfirm);
        workingMem.setGlobal("cancelation", cancelation);

        // workingMem.setGlobal("cccResponse", cccResponse);

        // workingMem.setGlobal("payment", payment);
        // workingMem.setGlobal("poAcceptance", poAcceptance);
        // workingMem.setGlobal("poRejection", poRejection);
        // workingMem.setGlobal("goodsDelivery", goodsDelivery);

        // Load globals for performance testing
        // workingMem.setGlobal("player", player);
        // workingMem.setGlobal("ropPlayer", ropPlayer);
        // workingMem.setGlobal("anyOperation", anyOperation);
        // Complete

        responder = new Responder("",false);
        workingMem.setGlobal("responder", responder);
//        ccclog = new CCCLogger();

        workingMem.setGlobal("cccloger", CCCLogger.class);

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
        if (eventLogger == null)
            throw new RuntimeException("EventLogger ref in RelevanceEngine is still null, cannot process events");
        // It is ok, continue with processing
        Event ev = eventQueue.poll();
        // Check if the queue is empty

        setCCCResponse(new CCCResponse(ev.getSequenceId(), false));
        responder.setContractCompliant(false);
        responder.setSequenceId(ev.getSequenceId());

        if (ev == null)
            return;
        // It is not empty, process event.
        try {
            // Insert new event in working memory
            workingMem.insert(ev);
        } catch (Exception e) {
            ErrorMessageManager.errorMsg("Insertion of new event in working memory failed", e);
            setCCCResponse(new CCCResponse(ev.getSequenceId(),false));
        }
        log.info("+ Processing event: " + ev.toString());
        // Fire all rules!
        try {
            workingMem.fireAllRules();

            // update response of CCC
            updateResponseOfCCC(ev);

        } catch (Exception e) {
            ErrorMessageManager.errorMsg("Exception when firing rules", e);
            setCCCResponse(new CCCResponse(ev.getSequenceId(),false));
        }

    }

    private static void updateResponseOfCCC(Event ev) {
        if (responder != null) {
            if (responder.getContractCompliant() == null) {
                setCCCResponse(new CCCResponse(ev.getSequenceId(),false));
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
