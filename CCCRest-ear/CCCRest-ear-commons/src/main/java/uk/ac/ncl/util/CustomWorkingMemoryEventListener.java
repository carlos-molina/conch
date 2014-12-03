
package uk.ac.ncl.util;

import java.util.logging.Logger;

import org.kie.api.event.rule.ObjectDeletedEvent;
import org.kie.api.event.rule.ObjectInsertedEvent;
import org.kie.api.event.rule.ObjectUpdatedEvent;
import org.kie.api.event.rule.RuleRuntimeEventListener;
import uk.ac.ncl.logging.CCCLogger;

public class CustomWorkingMemoryEventListener implements RuleRuntimeEventListener {
	
	private final static Logger logger = Logger.getLogger(CustomWorkingMemoryEventListener.class.toString());

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectInserted(org.drools
	 * .event.rule.ObjectInsertedEvent)
	 */
	@Override
	public void objectInserted(ObjectInsertedEvent event) {
		logger.info("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
//		CCCLogger.logTrace("Object Inserted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectRetracted(org.
	 * drools.event.rule.ObjectRetractedEvent)
	 */
	@Override
	public void objectDeleted(ObjectDeletedEvent event) {
		logger.info("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
//		CCCLogger.logTrace("Object Retracted: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.drools.event.rule.WorkingMemoryEventListener#objectUpdated(org.drools
	 * .event.rule.ObjectUpdatedEvent)
	 */
	@Override
	public void objectUpdated(ObjectUpdatedEvent event) {
		logger.info("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());
//		CCCLogger.logTrace("Object Updated: " + event.getFactHandle() + " Knowledge Runtime: " + event.getKieRuntime());

	}
}
