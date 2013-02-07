package uk.ac.ncl.erop;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

import org.drools.*;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.compiler.*;
import org.drools.definition.KnowledgePackage;
import org.drools.io.ResourceFactory;
import org.drools.runtime.StatefulKnowledgeSession;

import uk.ac.ncl.xml.CCCResponse;

/**
 * The Class RelevanceEngine. Instances of this class are wrappers around the
 * Drools Rule Engine
 *
 * @author <a href="mailto:i.sfyrakis@ncl.ac.uk">Ioannis Sfyrakis</a>
 * @author last edited by: $$Author: gsfyrakis $$
 * @version $$Revision: 10 $$, $$Date: 2012-07-23 14:25:44 +0100 (Mon, 23 Jul
 *          2012) $$
 */
public class RelevanceEngine {

	private final static Logger log = Logger.getLogger(RelevanceEngine.class.toString());

	// /* Private data
	static KnowledgeBase ruleBase = KnowledgeBaseFactory.newKnowledgeBase();

	static StatefulKnowledgeSession workingMem = ruleBase.newStatefulKnowledgeSession();// null;

	static LinkedList<Event> eventQueue = new LinkedList<Event>();
	static EventLogger eventLogger = null;
	// TimeKeeper timeKeeper = null;

	// For performance testing only
	static boolean performanceTestingOn = false;

	private static Responder responder;

	// default response is non contract compliant otherwise contract compliant
	private static CCCResponse cccResponse = new CCCResponse(false);

	// /* Ancillary methods
	/**
	 * Handle compilation errors.
	 *
	 * @param builder
	 *            the builder
	 * @param fileName
	 *            the file name
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
	 * @param fileName
	 *            the file name
	 * @param el
	 *            the event logger
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 * @throws DroolsParserException
	 *             the drools parser exception
	 */
	public RelevanceEngine(String fileName, EventLogger el) throws IOException, DroolsParserException {
		// Verify that the EventLogger is not null
		if (el == null)
			throw new IllegalArgumentException("EventLogger ref null");
		Reader source = null;
		try {
			source = new FileReader(fileName);
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Exception opening file reader", e);
		}
		// Create PackageBuilder
		KnowledgeBuilder builder = KnowledgeBuilderFactory.newKnowledgeBuilder();
		builder.add(ResourceFactory.newFileResource(fileName), ResourceType.DRL);
		// Check if the compilation was successful
		if (builder.hasErrors()) {
			handleCompilationErrors(builder, fileName);
		}
		// Generate rule package
		Collection<KnowledgePackage> p = builder.getKnowledgePackages();
		// Add Rule Package to current Rule Base
		try {
			ruleBase.addKnowledgePackages(p);
		} catch (Exception e) {
			ErrorMessageManager.fatalErrorMsg("Adding new Rule Package to current Rule Base failed", e);
		}
		// Initialize Stateful Session
		// workingMem = ruleBase.newStatefulSession();
		// Store event logger
		eventLogger = el;
		// TODO: Initialize things necessary to run contract
		// such as ROP sets, and so on (probably by firing rules once
		// or by setting a global with the setGlobal method
		// of the the working memory)
	}
	/**
	 * Initialize contract.
	 *
	 * @param timeKeeper
	 *            the time keeper
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

		responder = new Responder(false);
		workingMem.setGlobal("responder", responder);

		log.info("Initialization complete");
	}

	// * Event Management Methods

	/**
	 * Adds the event.
	 *
	 * @param e
	 *            the e
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

		setCCCResponse(new CCCResponse(false));
		responder.setContractCompliant(false);
		// Check if EventLogger is in place, if not refuse to process event
		// queue
		if (eventLogger == null)
			throw new RuntimeException("EventLogger ref in RelevanceEngine is still null, cannot process events");
		// It is ok, continue with processing
		Event ev = eventQueue.poll();
		// Check if the queue is empty
		if (ev == null)
			return;
		// It is not empty, process event.
		try {
			// Insert new event in working memory
			workingMem.insert(ev);
		} catch (Exception e) {
			ErrorMessageManager.errorMsg("Insertion of new event in working memory failed", e);
			setCCCResponse(new CCCResponse(false));
		}
		log.info("+ Processing event: " + ev.toString());
		// Fire all rules!
		try {
			workingMem.fireAllRules();

			// update response of CCC

			if (responder != null) {
				if (responder.getContractCompliant() == null) {
					setCCCResponse(new CCCResponse(false));
				} else {
					setCCCResponse(new CCCResponse(responder.getContractCompliant()));
				}
			}
		} catch (Exception e) {
			ErrorMessageManager.errorMsg("Exception when firing rules", e);
			setCCCResponse(new CCCResponse(false));
		}

	}

	/**
	 * @return the cccResponse
	 */
	public static CCCResponse getCCCResponse() {
		return cccResponse;
	}

	/**
	 * @param cccResponse
	 *            the cccResponse to set
	 */
	public static void setCCCResponse(CCCResponse cccResponse) {
		RelevanceEngine.cccResponse = cccResponse;
	}

	// /* Testing methods
	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws DroolsParserException
	 *             the drools parser exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	// public static void main(String[] args) throws DroolsParserException,
	// IOException {
	// EventLogger el = new EventLogger(EropDbConstValues.serverlocation,
	// EropDbConstValues.username, EropDbConstValues.password);
	// // RelevanceEngine re = new
	// RelevanceEngine("src/main/resources/TestingContract.drl", el);
	// // Event e1 = new Event("none", "none", "init", "success");
	// // Event e2 = new Event("buyer", "seller", "POrder", "success");
	// // Event e3 = new Event("buyer", "seller", "POAcceptance", "success");
	// // re.addEvent(e1);
	// // re.addEvent(e2);
	// // re.addEvent(e3);
	//
	// // For performance measurement!
	// //RelevanceEngine re = new
	// RelevanceEngine("resources/PerformanceTesting1.drl", el);
	// RelevanceEngine re = new
	// RelevanceEngine("src/main/resources/TestingContract.drl", el);
	//
	// // Add normal events
	// for (int i = 1; i<=2; i++) {
	// Event e = new Event("player", "player", "Normal", "success");
	// re.addEvent(e);
	// }
	// // Add last event
	// Event e = new Event("none", "none", "Last", "success");
	// re.addEvent(e);
	// }
}
